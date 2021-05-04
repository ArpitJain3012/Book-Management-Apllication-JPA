package com.cg.bookstore.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cg.bookstore.model.OrderDetails;

@Entity
@Table(name = "BookOrder")
public class BookOrders implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "orderId")
	private int orderId;

	@Column(name = "OrderDate", length = 20)
	private LocalDate OrderDate;

	@Column(name = "OrderTotal", length = 20)
	private BigInteger OrderTotal;

	@Column(name = "status", length = 20)
	private String status;

	@Column(name = "paymentMethod", length = 10)
	private String paymentMethod;

	@Column(name = "recipientphone")
	private String recipientphone;

	@Column(name = "recipientName")
	private String recipientName;
	/*
	 * embedding order details
	 */
	@Embedded
	private OrderDetails od;
	/*
	 * using many to one association for mapping with customer entity
	 */
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer cust;

	public BookOrders() {
		// no implementation
	}

	public BookOrders(int orderId, LocalDate orderDate, BigInteger orderTotal, String status, String paymentMethod,
			String recipientphone, String recipientName, OrderDetails od) {
		super();
		this.orderId = orderId;
		OrderDate = orderDate;
		OrderTotal = orderTotal;
		this.status = status;
		this.paymentMethod = paymentMethod;
		this.recipientphone = recipientphone;
		this.recipientName = recipientName;
		this.od = od;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public LocalDate getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		OrderDate = orderDate;
	}

	public BigInteger getOrderTotal() {
		return OrderTotal;
	}

	public void setOrderTotal(BigInteger orderTotal) {
		OrderTotal = orderTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getRecipientphone() {
		return recipientphone;
	}

	public void setRecipientphone(String recipientphone) {
		this.recipientphone = recipientphone;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public OrderDetails getOd() {
		return od;
	}

	public void setOd(OrderDetails od) {
		this.od = od;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	@Override
	public String toString() {
		return String.format(
				"orderId=%s,OrderDate=%s,OrderTotal=%s,status=%s, paymentMethod=%s,recipientphone=%s,recipientName=%s",
				orderId, OrderDate, OrderTotal, status, paymentMethod, recipientphone, recipientName);
	}

}
