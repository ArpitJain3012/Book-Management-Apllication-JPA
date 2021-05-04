package com.cg.bookstore.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.BookOrders;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.repository.IOrderRepository;
import com.cg.bookstore.repository.IOrderRepositoryImpl;

public class IOrderServiceImpl implements IOrderService {
	private IOrderRepository ior;

	public IOrderServiceImpl() {
		ior = new IOrderRepositoryImpl();

	}

	/*
	 * validation of all order detials
	 */
	public boolean isValidorderId(int orderId) {
		return orderId != 0 && orderId > 0;
	}

	public boolean isValidorderDate(LocalDate orderDate) {
		return orderDate != null;
	}

	public boolean isValidorderTotal(BigInteger ordertotal) {
		return ordertotal != null;
	}

	public boolean isValidstatus(String status) {
		return status != null && (status.length() >= 3 || status.length() <= 20);
	}

	public boolean isValidpaymentMethod(String paymentMethod) {
		return paymentMethod != null && (paymentMethod.length() >= 3 || paymentMethod.length() <= 20);
	}

	public boolean isValidrecipientName(String recipientName) {
		return recipientName != null && (recipientName.length() >= 3 || recipientName.length() <= 20);
	}

	public boolean isValidrecipientPhone(String recipientPhone) {
		return recipientPhone != null && recipientPhone.matches("[1-9][0-9]{9}");
	}

	public boolean isValidBookOrders(BookOrders bo) throws BMSException {
		List<String> errorMessages = new ArrayList<>();
		boolean isValid = true;
		if (bo != null) {
			if (!isValidorderId(bo.getOrderId())) {
				isValid = false;
				errorMessages.add("order id cannot be blank and must be a posiitive number");
			}
			if (bo != null) {
				if (!isValidorderDate(bo.getOrderDate())) {
					isValid = false;
					errorMessages.add("orderDate cannot be blank and must be a valid one ");
				}

				if (!isValidorderTotal(bo.getOrderTotal())) {
					isValid = false;
					errorMessages.add("orderTotal cannot be blank and must be a posiitive number");
				}
				if (!isValidstatus(bo.getStatus())) {
					isValid = false;
					errorMessages.add("status cannot be blank and must be 3 to 20 characters long");
				}
				if (!isValidpaymentMethod(bo.getPaymentMethod())) {
					isValid = false;
					errorMessages.add(
							"paymentMethod cannot be blank and length should be 8-20 include only (@_) in spl. characters");
				}
				if (!isValidrecipientName(bo.getRecipientName())) {
					isValid = false;
					errorMessages.add(
							" recipientName cannot be blank and of atleast 20 to 30 charaters including alphanumeric values");
				}
				if (!isValidrecipientPhone(bo.getRecipientphone())) {
					isValid = false;
					errorMessages.add("recipientPhone cannot be blank and should be of length 10");
				}

				if (!errorMessages.isEmpty()) {
					throw new BMSException("Invalid contact :" + errorMessages);
				}
			} else {
				isValid = false;
				throw new BMSException("BookOrders details are not supplied");
			}

		}
		return isValid;

	}

	/*
	 * calling repository methods
	 */
	@Override
	public List<BookOrders> listAllOrders() throws BMSException {
		return ior.listAllOrders();

	}

	@Override
	public List<BookOrders> listOrderByCustomer(Customer cs) throws BMSException {
		return ior.listOrderByCustomer(cs);
	}

	@Override
	public BookOrders viewOrderForCustomer(Customer c) throws BMSException {
		return ior.viewOrderForCustomer(c);
	}

	@Override
	public BookOrders viewOrderForAdmin(BookOrders od) throws BMSException {

		return ior.viewOrderForAdmin(od);
	}

	@Override
	public boolean cancelOrder(BookOrders od) throws BMSException {
		ior.cancelOrder(od);
		return true;
	}

	@Override
	public BookOrders addOrder(BookOrders od) throws BMSException {
		if (isValidBookOrders(od))
			ior.addOrder(od);
		return od;
	}

	@Override
	public BookOrders updateOrder(BookOrders od) throws BMSException {

		return ior.updateOrder(od);
	}

	@Override
	public List<BookOrders> viewOrderByBook(Book book) throws BMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listBestSellingBook() throws BMSException {
		// TODO Auto-generated method stub
		return null;
	}
}