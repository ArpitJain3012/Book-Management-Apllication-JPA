package com.cg.bookstore.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cg.bookstore.model.Address;

@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "customerId")
	private int customerId;

	@Column(name = "email", length = 30)
	private String email;

	@Column(name = "fullname", length = 20)
	private String fullName;

	@Column(name = "password", length = 20)
	private String password;

	@Column(name = "mobileNumber", length = 10)
	private String mobileNumber;

	@Column(name = "registerOn")
	private LocalDate registerOn;

	/*
	 * embedding address model class in customer
	 */

	@Embedded
	private Address address1;

	/*
	 * using one to many association for mapping with BookOrders entity
	 */

	@OneToMany(mappedBy = "cust", cascade = CascadeType.PERSIST)
	private Set<BookOrders> bo;

	/*
	 * using one to many association for mapping with review entity
	 */

	@OneToMany(mappedBy = "cust1", cascade = CascadeType.PERSIST)
	private Set<Review> rev;

	public Customer() {
		// no implementation
	}

	public Customer(int customerId, String email, String fullName, String password, String mobileNumber,
			LocalDate registerOn, Address address1) {
		super();
		this.customerId = customerId;
		this.email = email;
		this.fullName = fullName;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.registerOn = registerOn;
		this.address1 = address1;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public LocalDate getRegisterOn() {
		return registerOn;
	}

	public void setRegisterOn(LocalDate registerOn) {
		this.registerOn = registerOn;
	}

	public Address getAddress1() {
		return address1;
	}

	public void setAddress1(Address address1) {
		this.address1 = address1;
	}

	public Set<BookOrders> getBo() {
		return bo;
	}

	public void setBo(Set<BookOrders> bo) {
		this.bo = bo;
	}

	public Set<Review> getRev() {
		return rev;
	}

	public void setRev(Set<Review> rev) {
		this.rev = rev;
	}

	@Override
	public String toString() {
		return String.format("customerId=%s, email=%s, fullName=%s, password=%s, mobileNumber=%s, registerOn=%s",
				customerId, email, fullName, password, mobileNumber, registerOn);

	}
}
