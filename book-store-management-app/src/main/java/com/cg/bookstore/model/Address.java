package com.cg.bookstore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Embeddable
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "address", length = 30)
	private String address;

	@Column(name = "city", length = 20)
	private String city;

	@Column(name = "country", length = 20)
	private String country;

	@Column(name = "pincode", length = 10)
	private String pincode;

	public Address() {
		// no implementation
	}

	public Address(String address, String city, String country, String pincode) {
		super();
		this.address = address;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return String.format("address=%s, city=%s, country=%s, pincode=%s", address, city, country, pincode);
	}
}
