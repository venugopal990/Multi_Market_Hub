package com.multimarkethub.orderservice.services;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.multimarkethub.orderservice.beans.CheckOutResponse;
import com.multimarkethub.orderservice.beans.Customer;
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
import com.multimarkethub.orderservice.proxy.StoreServiceProxy;
import com.multimarkethub.orderservice.proxy.UserServiceProxy;
import com.multimarkethub.orderservice.repository.OrderItemsRepository;
import com.multimarkethub.orderservice.repository.OrdersRepository;
import com.multimarkethub.orderservice.utils.Utils;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final OrdersRepository ordersRepository;
	private final OrderItemsRepository orderItemsRepository;
	private final CartService cartService;
	private final PaymentService paymentService;
	private final ProductServiceProxy productServiceProxy;
	private final Utils utils;
	private final UserServiceProxy userServiceProxy;
	private final StoreServiceProxy storeServiceProxy;
	private final EmailService emailService;
	
	@Autowired
	public OrderServiceImpl(OrdersRepository ordersRepository, OrderItemsRepository orderItemsRepository,CartService cartService,PaymentService paymentService,
			ProductServiceProxy productServiceProxy, Utils utils, UserServiceProxy userServiceProxy,StoreServiceProxy storeServiceProxy,EmailService emailService) {
		this.ordersRepository =  ordersRepository;
		this.orderItemsRepository =  orderItemsRepository;
		this.cartService = cartService;
		this.paymentService = paymentService;
		this.productServiceProxy =  productServiceProxy;
		this.utils = utils;
		this.userServiceProxy = userServiceProxy;
		this.storeServiceProxy = storeServiceProxy;
		this.emailService = emailService;
	}
	
	private Boolean isProductsInStock(List<CartitemsEntity> cartitemsEntityList) {
		for(CartitemsEntity cartitemsEntity: cartitemsEntityList) {
			if(cartitemsEntity.getQuantity()>cartitemsEntity.getProductsEntity().getProductStockQuantity()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public CheckOutResponse cartCheckOut(Integer storeId, Integer customerId, PaymentType paymentType) {
		CartsEntity cartsEntity = cartService.getByCustomerIdAndStoreId(customerId,storeId);
		if(cartsEntity==null)
			throw new NotFoundException("No products found in the cart.");
		if(Boolean.TRUE.equals(isProductsInStock(cartsEntity.getCartItems()))) {
			emailService.getVerifiedEmails();
			Double cartTotalAmount = CartServiceImpl.getTotalPriceOfTheCart(cartsEntity.getCartItems());
			Integer orderId = saveOrders(cartsEntity,cartTotalAmount);
			paymentService.savePayments(orderId, storeId, cartTotalAmount, paymentType);
			updateProductStock(storeId, cartsEntity.getCartItems());
			cartService.removeItemFromCart(storeId, customerId, 0);
			HashMap<String, String> orderEmailMap  = sendOrderEmail(getOrders(storeId, customerId, orderId), storeId, customerId,1);
			if(!orderEmailMap.isEmpty()) {
				String subject = "Your Order Confirmation - ["+orderEmailMap.get("orderId")+"]";
				emailService.sendEmail(orderEmailMap.get("toEmail"), orderEmailMap.get("html"), subject);
			}
			return new CheckOutResponse(orderId, "Success", "Thank you for ordering!");
		}else {
			throw new NotFoundException("Product OutOfStock.");
		}
	}
	
	@Async
	private HashMap<String, String> sendOrderEmail(List<Orders> orderList,Integer storeId, Integer customerId, Integer templateId) {
		HashMap<String, String> orderEmailMap =  new HashMap<String, String>();
		String htmlFile = "";
		try {
			List<Customer> customerList = userServiceProxy.getCustomers(customerId, storeId);
			if(customerList.get(0).isEmailIsVerified()) {
				htmlFile = utils.readHTMLFile(templateId);
				htmlFile = htmlFile.replace("[Customer's Name]", customerList.get(0).getFirstName()+" "+customerList.get(0).getLastName());
				htmlFile = htmlFile.replace("[Customer's Shipping Address]", customerList.get(0).getAddress());
				htmlFile = htmlFile.replace("[Order Number]", orderList.get(0).getOrderId().toString());
				htmlFile = htmlFile.replace("[Order Date]", orderList.get(0).getOrderDateAndTime());
						htmlFile = htmlFile.replace("[Delivered Date]", orderList.get(0).getOrderUpdatedDateAndTime());
				htmlFile = htmlFile.replace("[Subtotal]", orderList.get(0).getOrderTotalAmount().toString());
				StringBuilder sf = new StringBuilder();
				for(OrderedProduct orderedProduct : orderList.get(0).getOrderedProductList()) {
					sf.append("<tr><td>"+orderedProduct.getProductName()+"</td><td>"+orderedProduct.getQuantity()+"</td><td>$"+orderedProduct.getProductOriginalprice()+"</td><td>$"+orderedProduct.getPrice()+"</td></tr>");
				}
				htmlFile = htmlFile.replace("[products]", sf.toString());
				htmlFile = htmlFile.replace("[Payment Method Name]", orderList.get(0).getPaymentDetails().getPaymentMethod());
				htmlFile = htmlFile.replace("[Payment Status]", orderList.get(0).getPaymentDetails().getPaymentStatus());
				htmlFile = htmlFile.replace("[store name]", storeServiceProxy.getStore(storeId).get(0).getName());
				orderEmailMap.put("html", htmlFile);
				orderEmailMap.put("toEmail", customerList.get(0).getEmail());
				orderEmailMap.put("orderId", orderList.get(0).getOrderId().toString());
				return orderEmailMap;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return orderEmailMap;
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
				2, 1, timeStamp, timeStamp);
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
	public List<Orders> getOrders(Integer storeId, Integer customerId, Integer orderId) {
		List<OrdersEntity> ordersEntityList;
		if(orderId == 0)
			ordersEntityList = ordersRepository.findByStoreIdAndCustomerId(storeId, customerId);
		else
			ordersEntityList = ordersRepository.findByStoreIdAndCustomerIdAndOrderId(storeId, customerId,orderId);
		List<Orders> ordersList = new ArrayList<>();
		if(ordersEntityList!=null) {
			for(OrdersEntity ordersEntity: ordersEntityList) {
				Orders orders = new Orders();
				orders.setOrderId(ordersEntity.getOrderId());
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
				orders.setOrderDateAndTime(sdf.format(ordersEntity.getOrderCreatedAt()));
				orders.setOrderUpdatedDateAndTime(sdf.format(ordersEntity.getOrderUpdatedAt()));
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
					orderedProduct.setProductOriginalprice(orderItemsEntity.getProductsEntity().getProductPrice());
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

	@Override
	public List<Orders> updateOrder(Integer storeId, Integer customerId, Integer orderId, Integer deliveryStatusId) {
		ordersRepository.updateDeliveryStatus(orderId, deliveryStatusId, storeId, customerId);
		if(deliveryStatusId == 4) {
			paymentService.updatePaymentStatus(orderId, 2, storeId);
			HashMap<String, String> orderEmailMap  = sendOrderEmail(getOrders(storeId, customerId, orderId), storeId, customerId,2);
			if(!orderEmailMap.isEmpty()) {
				String subject = "Your Order Successfully Delivered - Thank You!";
				emailService.sendEmail(orderEmailMap.get("toEmail"), orderEmailMap.get("html"), subject);
			}
		}
		return getOrders(storeId, customerId, orderId);
	}



}
