package com.multimarkethub.orderservice.entity;

import java.sql.Timestamp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.multimarkethub.orderservice.utils.JsonTimestampSerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartitems")
public class CartitemsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cartitems_cart_item_id_seq")
	@SequenceGenerator(name ="cartitems_cart_item_id_seq", sequenceName="cartitems_cart_item_id_seq", allocationSize=1)
	@Column(name="cart_item_id")
	private Integer cartItemId;

	@Column(name = "cart_id")
	private Integer cartId;

	@Column(name="product_id")
	private Integer productId;

	@Column(name="quantity")
	private Integer quantity;

	@Column(name="unit_price")
	private Double unitPrice;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="cart_item_created_at")
	private Timestamp cartItemCreatedAt;

	@JsonSerialize(using = JsonTimestampSerializer.class)
	@Column(name="cart_item_updated_at")
	private Timestamp cartItemUpdatedAt;
	
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
	private ProductsEntity productsEntity;
	
	
    @ManyToOne
    @JoinColumn(name="cart_id", insertable = false, updatable = false)
    private CartsEntity cart;

	public Integer getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(Integer cartItemId) {
		this.cartItemId = cartItemId;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Timestamp getCartItemCreatedAt() {
		return cartItemCreatedAt;
	}

	public void setCartItemCreatedAt(Timestamp cartItemCreatedAt) {
		this.cartItemCreatedAt = cartItemCreatedAt;
	}

	public Timestamp getCartItemUpdatedAt() {
		return cartItemUpdatedAt;
	}

	public void setCartItemUpdatedAt(Timestamp cartItemUpdatedAt) {
		this.cartItemUpdatedAt = cartItemUpdatedAt;
	}



	public CartsEntity getCart() {
		return cart;
	}

	public void setCart(CartsEntity cart) {
		this.cart = cart;
	}

	public CartitemsEntity(Integer cartId, Integer productId, Integer quantity, Double unitPrice,
			Timestamp cartItemCreatedAt, Timestamp cartItemUpdatedAt) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.cartItemCreatedAt = cartItemCreatedAt;
		this.cartItemUpdatedAt = cartItemUpdatedAt;
	}

	public CartitemsEntity() {
	}

	@Override
	public String toString() {
		return "CartitemsEntity [cartItemId=" + cartItemId + ", cartId=" + cartId + ", productId=" + productId
				+ ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", cartItemCreatedAt=" + cartItemCreatedAt
				+ ", cartItemUpdatedAt=" + cartItemUpdatedAt + ", cartsentity=" + cart + "]";
	}

	public ProductsEntity getProductsEntity() {
		return productsEntity;
	}

	public void setProductsEntity(ProductsEntity productsEntity) {
		this.productsEntity = productsEntity;
	}
	
	
}