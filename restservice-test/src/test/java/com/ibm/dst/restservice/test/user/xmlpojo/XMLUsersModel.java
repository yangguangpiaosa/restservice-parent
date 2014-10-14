/**
 * 
 */
package com.ibm.dst.restservice.test.user.xmlpojo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibm.dst.restservice.test.core.BaseModel;

/**
 * @author 
 *
 */
@XmlRootElement(name="usersModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLUsersModel extends BaseModel {
	
	@XmlElementWrapper(name="users")
	@XmlElement(name="user")
	private List<XMLUserModel> users = null;
	
	public List<XMLUserModel> getUsers() {
		return users;
	}
	public void setUsers(List<XMLUserModel> users) {
		this.users = users;
	}
}
