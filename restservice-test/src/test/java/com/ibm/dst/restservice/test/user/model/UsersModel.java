/**
 * 
 */
package com.ibm.dst.restservice.test.user.model;

import java.util.List;

import com.ibm.dst.restservice.test.core.BaseModel;

/**
 * @author 
 *
 */
public class UsersModel extends BaseModel {
	
	private List<UserModel> users = null;
	
	public List<UserModel> getUsers() {
		return users;
	}
	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
}
