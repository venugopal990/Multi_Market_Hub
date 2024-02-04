package com.multimarkethub.orderservice.services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multimarkethub.orderservice.beans.CheckOutResponse;
import com.multimarkethub.orderservice.beans.OrderedProduct;
import com.multimarkethub.orderservice.beans.Orders;
import com.multimarkethub.orderservice.beans.PaymentDetails;
import com.multimarkethub.orderservice.beans.PaymentType;
import com.multimarkethub.orderservice.entity.CartitemsEntity;
import com.multimarkethub.orderservice.entity.CartsEntity;
import com.multimarkethub.orderservice.entity.OrderItemsEntity;
import com.multimarkethub.orderservice.entity.OrdersEntity;
import com.multimarkethub.orderservice.entity.PaymentsEntity;
import com.multimarkethub.orderservice.exception.NotFoundException;
import com.multimarkethub.orderservice.proxy.ProductServiceProxy;
import com.multimarkethub.orderservice.repository.OrderItemsRepository;
import com.multimarkethub.orderservice.repository.OrdersRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrdersRepository ordersRepository;
	private final OrderItemsRepository orderItemsRepository;
	private final CartService cartService;
	private final PaymentService paymentService;
	private final ProductServiceProxy productServiceProxy;
	
	@Autowired
	public OrderServiceImpl(OrdersRepository ordersRepository, OrderItemsRepository orderItemsRepository,CartService cartService,PaymentService paymentService,
			ProductServiceProxy productServiceProxy) {
		this.ordersRepository =  ordersRepository;
		this.orderItemsRepository =  orderItemsRepository;
		this.cartService = cartService;
		this.paymentService = paymentService;
		this.productServiceProxy =  productServiceProxy;
	}

	@Override
	public CheckOutResponse cartCheckOut(Integer storeId, Integer customerId, PaymentType paymentType) {
		CartsEntity cartsEntity = cartService.getByCustomerIdAndStoreId(customerId,storeId);
		if(cartsEntity==null)
			throw new NotFoundException("No products found in the cart.");
		Double cartTotalAmount = CartServiceImpl.getTotalPriceOfTheCart(cartsEntity.getCartItems());
		Integer orderId = saveOrders(cartsEntity,cartTotalAmount);
		paymentService.savePayments(orderId, storeId, cartTotalAmount, paymentType);
		updateProductStock(storeId, cartsEntity.getCartItems());
		cartService.removeItemFromCart(storeId, customerId, 0);
		return new CheckOutResponse(orderId, "Success", "Thank you for ordering!");
	}
	
	private void updateProductStock(Integer storeId,List<CartitemsEntity> cartitemsEntityList) {
		for(CartitemsEntity cartitemsEntity:cartitemsEntityList) {
			Integer  updatedProductQuantity = cartitemsEntity.getProductsEntity().getProductStockQuantity() - cartitemsEntity.getQuantity();
			productServiceProxy.updateProductStock(storeId, cartitemsEntity.getProductId(), updatedProductQuantity);
		}
	}
	
	
	private Integer saveOrders(CartsEntity cartsEntity,Double cartTotalAmount) {
		
		List<CartitemsEntity> cartItemsList =  cartsEntity.getCartItems();
		java.util.Date today=new java.util.Date();
		Timestamp timeStamp = ordersRepository.findCurrentTimeStamp();
		OrdersEntity ordersEntity = new OrdersEntity(cartsEntity.getCustomerId(), new java.sql.Date (today.getTime ()), cartTotalAmount, cartsEntity.getStoreId(),
				2, 2, timeStamp, timeStamp);
		Integer orderId = ordersRepository.save(ordersEntity).getOrderId();
		List<OrderItemsEntity> orderItemsEntitiesList = new ArrayList<>();
		for (CartitemsEntity cartitemsEntity : cartItemsList) {
			OrderItemsEntity orderItemsEntity = new OrderItemsEntity(orderId, cartitemsEntity.getProductId(), cartitemsEntity.getQuantity(), cartitemsEntity.getUnitPrice(), 
					cartsEntity.getStoreId(), timeStamp, timeStamp);
			orderItemsEntitiesList.add(orderItemsEntity);
		}
		orderItemsRepository.saveAll(orderItemsEntitiesList);
		
		return orderId;
		
	}

	@Override
	public List<Orders> getOrders(Integer storeId, Integer customerId) {
		List<OrdersEntity> ordersEntityList = ordersRepository.findByStoreIdAndCustomerId(storeId, customerId);
		List<Orders> ordersList = new ArrayList<>();
		if(ordersEntityList!=null) {
			for(OrdersEntity ordersEntity: ordersEntityList) {
				Orders orders = new Orders();
				orders.setOrderId(ordersEntity.getOrderId());
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				orders.setOrderDateAndTime(sdf.format(ordersEntity.getOrderCreatedAt()));
				orders.setOrderTotalAmount(ordersEntity.getTotalAmount());
				orders.setOrderStatus(ordersEntity.getOrderStatusesEntity().getStatusName());
				orders.setOrderDeliveryStatus(ordersEntity.getDeliveryStatusesEntity().getStatusName());
				PaymentsEntity paymentsEntity = ordersEntity.getPaymentsEntity();
				orders.setPaymentDetails(new PaymentDetails(paymentsEntity.getPaymentId(), sdf.format(paymentsEntity.getPaymentDate()), 
						paymentsEntity.getPaymentStatusEntity().getStatusName(),paymentsEntity.getPaymentMethodsEntity().getMethodName()));
				List<OrderedProduct> orderedProductList = new ArrayList<>();
				for(OrderItemsEntity orderItemsEntity : ordersEntity.getOrdersItems()) {
					OrderedProduct orderedProduct = new OrderedProduct();
					orderedProduct.setProductId(orderItemsEntity.getProductId());
					orderedProduct.setPrice(orderItemsEntity.getUnitPrice());
					orderedProduct.setQuantity(orderItemsEntity.getQuantity());
					orderedProduct.setProductName(orderItemsEntity.getProductsEntity().getProductName());
					orderedProduct.setProductImageUrl(orderItemsEntity.getProductsEntity().getProductImageUrl());
					orderedProductList.add(orderedProduct);
				}
				orders.setOrderedProductList(orderedProductList);
				ordersList.add(orders);
			}

		}else {
			throw new NotFoundException("Orders not found.");
		}



		return ordersList;

	}

}
