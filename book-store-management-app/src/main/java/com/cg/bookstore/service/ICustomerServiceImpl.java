package com.cg.bookstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.repository.ICustomerRepository;
import com.cg.bookstore.repository.ICustomerRepositoryImpl;

public class ICustomerServiceImpl implements ICustomerService {

	private ICustomerRepository icr;

	public ICustomerServiceImpl() {
		icr = new ICustomerRepositoryImpl();
	}

	/*
	 * validation for all customer data inside tables
	 */
	public boolean isValidCustomerId(int custId) {
		return custId != 0 && custId > 0;
	}

	public boolean isValidFullName(String fullName) {
		return fullName != null && (fullName.length() >= 3 || fullName.length() <= 20);
	}

	public boolean isValidRegisteron(LocalDate register) {
		return register != null;
	}

	public boolean isValidMobile(String mobile) {
		return mobile != null && mobile.matches("[1-9][0-9]{9}");
	}

	public boolean isValidEmail(String email) {
		return email != null && email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
	}

	public boolean isValidPassword(String pass) {
		return pass != null && pass.matches("[a-zA-Z0-9 @_]{8,20}");
	}

	public boolean isValidCustomer(Customer cust) throws BMSException {
		List<String> errorMessages = new ArrayList<>();
		boolean isValid = true;

		if (cust != null) {
			if (!isValidCustomerId(cust.getCustomerId())) {
				isValid = false;
				errorMessages.add("customer id cannot be blank and must be a posiitive number");
			}
			if (!isValidFullName(cust.getFullName())) {
				isValid = false;
				errorMessages.add("full name cannot be blank and must be 3 to 20 characters long");
			}
			if (!isValidRegisteron(cust.getRegisterOn())) {
				isValid = false;
				errorMessages.add("Registration date cannot be blank and must be a valid one");
			}
			if (!isValidMobile(cust.getMobileNumber())) {
				isValid = false;
				errorMessages.add("mobile number cannot be blank and should be of length 10");
			}
			if (!isValidEmail(cust.getEmail())) {
				isValid = false;
				errorMessages.add(
						"Email id cannot be blank and of atleast 20 to 30 charaters including alphanumeric values");
			}
			if (!isValidPassword(cust.getPassword())) {
				isValid = false;
				errorMessages
						.add("password cannot be blank and length should be 8-20 include only (@_) in spl. characters");
			}
			if (!errorMessages.isEmpty()) {
				throw new BMSException("Invalid contact :" + errorMessages);
			}
		} else {
			isValid = false;
			throw new BMSException("Customer details are not supplied");
		}
		return isValid;
	}

	/*
	 * calling repository methods
	 */

	@Override
	public Customer createCustomer(Customer c) throws BMSException {
		if (isValidCustomer(c))
			icr.createCustomer(c);
		return c;
	}

	@Override
	public List<Customer> listCustomers() throws BMSException {
		return icr.listCustomers();
	}

	@Override
	public Customer deleteCustomer(Customer c) throws BMSException {
		icr.deleteCustomer(c);
		return c;
	}

	@Override
	public Customer updateCustomer(Customer c) throws BMSException {

		return icr.updateCustomer(c);
	}

	@Override
	public Customer viewCustomer(Customer c) throws BMSException {
		return icr.viewCustomer(c);
	}

	@Override
	public List<Customer> listAllCustomersByBook(Book book) throws BMSException {
		return icr.listAllCustomersByBook(book);
	}

}
