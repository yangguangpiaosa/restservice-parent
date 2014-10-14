/**
 * 
 */
package com.ibm.dst.restservice.test.user.model;

import com.ibm.dst.restservice.test.core.BaseModel;

/**
 * @author 
 *
 */
public class UserModel extends BaseModel {
	private Integer id = null;
	private String name = null;
	private String email = null;
	public UserModel() {}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
