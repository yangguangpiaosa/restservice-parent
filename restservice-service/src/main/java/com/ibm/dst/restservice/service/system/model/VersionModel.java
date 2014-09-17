package com.ibm.dst.restservice.service.system.model;

import javax.ws.rs.core.EntityTag;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.ibm.dst.restservice.service.core.BaseModel;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class VersionModel extends BaseModel {
	private String versionNumber = null;
	
	public VersionModel() {}
	
	public VersionModel(String versionNumber) {
		this.versionNumber = versionNumber;
		this.eTag = new EntityTag(String.valueOf(this.versionNumber.hashCode()));
	}

	public String getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
}
