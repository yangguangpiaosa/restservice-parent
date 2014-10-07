/**
 * 
 */
package com.ibm.dst.restservice.service.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.dst.restservice.service.user.dao.UserDao;
import com.ibm.dst.restservice.service.user.model.UsersModel;
import com.ibm.dst.restservice.service.user.service.UserService;

/**
 * @author 
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao = null;
	
	@Override
	public UsersModel getUsers() {
		// TODO Auto-generated method stub
		return userDao.getUsers();
	}
	
}
