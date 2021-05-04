package com.cg.bookstore.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrders;
import com.cg.bookstore.entities.Category;

@Embeddable
public class OrderDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "quantity", length = 20)
	private int quantity;

	@Column(name = "subtotal", length = 10)
	private double subtotal;
	
	public OrderDetails() {
		// no implementation
	}

	public OrderDetails(int quantity, double subtotal) {
		super();
		
		this.quantity = quantity;
		this.subtotal = subtotal;
		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return String.format("quantity=%s, subtotal=%s", quantity, subtotal) ;
	}
}
