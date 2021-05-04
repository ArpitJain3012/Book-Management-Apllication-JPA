package com.cg.bookstore.repository;

import com.cg.bookstore.BMSException.BMSException;
import com.cg.bookstore.entities.User;

public interface ILoginRepository {
	public User addUser(User user)throws BMSException;
	public boolean removeUser(User user)throws BMSException;
	public User updateUser(User user)throws BMSException;
	

}
