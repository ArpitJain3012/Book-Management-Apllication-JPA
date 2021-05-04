package com.cg.bookstore.service;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.User;

public interface ILoginService {
	public User addUser(User user )throws BMSException;
	public User removedUser(User user )throws BMSException;
	public User updateUser(User user )throws BMSException;
	
}
