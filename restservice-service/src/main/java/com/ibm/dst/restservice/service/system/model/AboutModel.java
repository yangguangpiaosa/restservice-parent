package com.ibm.dst.restservice.service.system.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibm.dst.restservice.service.core.BaseModel;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AboutModel extends BaseModel {
	private String author = null;
	private String releaseDate = null;
	private String description = null;
	private String contactInfo = null;
	
	public AboutModel() {}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
}
