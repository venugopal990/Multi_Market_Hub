package com.multimarkethub.orderservice.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimarkethub.orderservice.beans.AddToCartResponse;
import com.multimarkethub.orderservice.beans.Cart;
import com.multimarkethub.orderservice.beans.CartPrice;
import com.multimarkethub.orderservice.beans.AddToCartRequest;
import com.multimarkethub.orderservice.beans.ProductReponse;
import com.multimarkethub.orderservice.entity.CartitemsEntity;
import com.multimarkethub.orderservice.entity.CartsEntity;
import com.multimarkethub.orderservice.exception.NotFoundException;
import com.multimarkethub.orderservice.proxy.ProductServiceProxy;
import com.multimarkethub.orderservice.repository.CartRepository;
import com.multimarkethub.orderservice.repository.CartitemsRepository;


@Service
public class CartServiceImpl implements CartService{
	
	private final CartitemsRepository cartitemsRepository;
	private final CartRepository cartRepository;
	private final ProductServiceProxy productServiceProxy;
	
	@Autowired
	public CartServiceImpl(CartitemsRepository cartitemsRepository,CartRepository cartRepository,ProductServiceProxy productServiceProxy) {
		this.cartitemsRepository =  cartitemsRepository;
		this.cartRepository =  cartRepository;
		this.productServiceProxy =  productServiceProxy;
	}

	@Override
	public AddToCartResponse addItemToCart(AddToCartRequest addToCartRequest) {
		
		List<ProductReponse> productReponseList = productServiceProxy.getProducts(addToCartRequest.getStoreId(), addToCartRequest.getProductId());
		if(!productReponseList.isEmpty()) {
			ProductReponse productReponse= productReponseList.get(0);
			if(addToCartRequest.getQuantity()<=productReponse.getProductStockQuantity()) {
				Integer cartId ;
				CartsEntity cartsEntity = cartRepository.findByCustomerIdAndStoreId(addToCartRequest.getCustomerId(),addToCartRequest.getStoreId());
				Double unitPrice= calcluateProductPrice(addToCartRequest.getQuantity(), productReponse.getProductPrice());
				if(cartsEntity!=null) {
					cartId = cartsEntity.getCartId();
				}else {
					cartId = saveToCart(addToCartRequest);       
				}
				CartitemsEntity cartitemsEntity = cartitemsRepository.findByProductIdAndCartId(addToCartRequest.getProductId(),cartId);
				if(cartitemsEntity!=null) {
					cartitemsRepository.updateCartItems(addToCartRequest.getQuantity(), unitPrice, cartitemsEntity.getCartItemId());
				}else {
					saveToCartItems(cartId, unitPrice, addToCartRequest);
				}
				cartitemsRepository.findByCartId(cartId);
				List<CartitemsEntity> cartitemsEntityList = cartitemsRepository.findByCartId(cartId);
				return new AddToCartResponse(cartId, unitPrice, getTotalPriceOfTheCart(cartitemsEntityList)); 
			}else {
				throw new NotFoundException("OutOfStock Exception");
			}
			
		}else {
			throw new NotFoundException("product with ID " + addToCartRequest.getProductId() + " not found.");
		}
	}
	
	private Double getTotalPriceOfTheCart(List<CartitemsEntity> cartitemsEntityList) {
		Double totalPrice = 0.0;
		for (CartitemsEntity cartitemsEntity : cartitemsEntityList) {
			totalPrice += cartitemsEntity.getUnitPrice();
		}
		return totalPrice;
	}
	
	private Double calcluateProductPrice(Integer quantity, Double price) {
		return price*quantity;
	}
	
	private void saveToCartItems(Integer cartId, Double unitPrice,  AddToCartRequest cart) {
		Timestamp timeStamp = cartRepository.findCurrentTimeStamp();
		cartitemsRepository.save(new CartitemsEntity(cartId, cart.getProductId(), cart.getQuantity(), unitPrice, timeStamp, timeStamp));
		
	}
	
	
	private Integer saveToCart(AddToCartRequest cart) {
		Timestamp timeStamp = cartRepository.findCurrentTimeStamp();
		
		CartsEntity cartsEntityToSave = new CartsEntity(cart.getCustomerId(), cart.getStoreId(), timeStamp, timeStamp);
		CartsEntity cartsEntity = cartRepository.save(cartsEntityToSave);
		
		return cartsEntity.getCartId();
		
	}



	@Override
	public Cart getItemsFromCart(Integer storeId, Integer customerId) {
		CartsEntity cartsEntity = cartRepository.findByCustomerIdAndStoreId(customerId,storeId);
		if(cartsEntity!=null) {
			List<ProductReponse> productReponseList = new ArrayList<>();
			List<CartitemsEntity> cartitemsEntityList = cartitemsRepository.findByCartId(cartsEntity.getCartId());
			for (CartitemsEntity cartitemsEntity : cartitemsEntityList) {
				List<ProductReponse> productReponseListFromProxy = productServiceProxy.getProducts(storeId, cartitemsEntity.getProductId());
				ProductReponse productReponse = productReponseListFromProxy.get(0);
				productReponse.setProductCartPrice(cartitemsEntity.getUnitPrice());
				productReponse.setProductCartQuantity(cartitemsEntity.getQuantity());
				productReponseList.add(productReponse);
			}
			return new Cart(cartitemsEntityList.size(), getTotalPriceOfTheCart(cartitemsEntityList), productReponseList);
			
		}else {
			throw new NotFoundException("No products found in the cart.");
		}
				
	}


	@Override
	public CartPrice removeItemFromCart(Integer storeId, Integer customerId, Integer productId) {
		CartsEntity cartsEntity = cartRepository.findByCustomerIdAndStoreId(customerId,storeId);
		if(cartsEntity!=null) {
			Integer cartId = cartsEntity.getCartId();
			cartitemsRepository.deleteByCartIdAndProductId(cartId, productId);
			List<CartitemsEntity> cartitemsList = cartitemsRepository.findByCartId(cartId);
			if(cartitemsRepository.findByCartId(cartId).isEmpty()) {
				cartRepository.deleteByStoreIdAndCustomerId(storeId, customerId);
			}
			return new CartPrice(getTotalPriceOfTheCart(cartitemsList));
		}else {
			throw new NotFoundException("No products found in the cart.");
		}
	}
	

}
