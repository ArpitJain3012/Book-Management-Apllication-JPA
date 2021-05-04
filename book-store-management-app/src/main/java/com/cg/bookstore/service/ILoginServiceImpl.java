package com.cg.bookstore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.User;
import com.cg.bookstore.repository.ILoginRepository;
import com.cg.bookstore.repository.ILoginRepositoryImpl;

public class ILoginServiceImpl implements ILoginService {

	private ILoginRepository ilr;

	public ILoginServiceImpl() {
		ilr = new ILoginRepositoryImpl();
	}
	/*
	 * validating login details inside tables
	 */

	public boolean isValidUserId(int user) {
		return user != 0 && user > 0;
	}

	public boolean isValidEmail(String email) {
		return email != null && email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
	}

	public boolean isValidPassword(String pass) {
		return pass != null && pass.matches("[a-zA-Z0-9 @_]{8,20}");
	}

	public boolean isValidFullRole(String role) {
		return role != null && (role.length() >= 3 || role.length() <= 20);
	}

	public boolean isValidUser(User user) throws BMSException {
		List<String> errorMessages = new ArrayList<>();
		boolean isValid = true;

		if (user != null) {
			if (!isValidUserId(user.getUserId())) {
				isValid = false;
				errorMessages.add("customer id cannot be blank and must be a posiitive number");
			}

			if (!isValidEmail(user.getEmail())) {
				isValid = false;
				errorMessages.add(
						"Email id cannot be blank and of atleast 20 to 30 charaters including alphanumeric values");
			}
			if (!isValidPassword(user.getPassword())) {
				isValid = false;
				errorMessages
						.add("password cannot be blank and length should be 8-20 include only (@_) in spl. characters");
			}
			if (!isValidFullRole(user.getRole())) {
				isValid = false;
				errorMessages.add("Role cannot be blank and must be greater than 3 characters");
			}

			if (!errorMessages.isEmpty()) {
				throw new BMSException("Invalid user:" + errorMessages);
			}
		} else {
			isValid = false;
			throw new BMSException("User details are not supplied");
		}
		return isValid;
	}
	/*
	 * calling repository methods
	 */

	@Override
	public User addUser(User user) throws BMSException {
		if (isValidUser(user))
			ilr.addUser(user);

		return user;
	}

	@Override
	public User removedUser(User user) throws BMSException {
		ilr.removeUser(user);
		return user;
	}

	@Override
	public User updateUser(User user) throws BMSException {
		ilr.updateUser(user);
		return user;

	}

}
