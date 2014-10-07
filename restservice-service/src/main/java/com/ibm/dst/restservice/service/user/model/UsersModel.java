/**
 * 
 */
package com.ibm.dst.restservice.service.user.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibm.dst.restservice.service.core.BaseModel;

/**
 * @author 
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersModel extends BaseModel {
	private List<UserModel> users = null;
	
	public UsersModel() {}

	public List<UserModel> getUsers() {
		return users;
	}
	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
}
