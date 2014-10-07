/**
 * 
 */
package com.ibm.dst.restservice.service.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.EntityTag;

import org.springframework.stereotype.Repository;

import com.ibm.dst.restservice.service.user.dao.UserDao;
import com.ibm.dst.restservice.service.user.model.UserModel;
import com.ibm.dst.restservice.service.user.model.UsersModel;

/**
 * @author 
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Override
	public UsersModel getUsers() {
		// TODO Auto-generated method stub
		UserModel user1 = new UserModel();
		user1.setId(1);
		user1.setName("test1");
		user1.setEmail("test1@cn.ibm.com");
		user1.setETag(new EntityTag(String.valueOf(user1.getId().hashCode())));
		UserModel user2 = new UserModel();
		user2.setId(2);
		user2.setName("test2");
		user2.setEmail("test2@cn.ibm.com");
		user2.setETag(new EntityTag(String.valueOf(user2.getId().hashCode())));
		List<UserModel> userList = new ArrayList<UserModel> ();
		userList.add(user1);
		userList.add(user2);
		UsersModel users = new UsersModel();
		users.setUsers(userList);
		return users;
	}

}
