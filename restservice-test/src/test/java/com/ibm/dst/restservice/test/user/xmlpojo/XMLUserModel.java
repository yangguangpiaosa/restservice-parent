/**
 * 
 */
package com.ibm.dst.restservice.test.user.xmlpojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibm.dst.restservice.test.core.BaseModel;

/**
 * @author 
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLUserModel extends BaseModel {
	private Integer id = null;
	private String name = null;
	private String email = null;
	public XMLUserModel() {}
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
