package com.ibm.dst.restservice.service.core;

import javax.ws.rs.core.EntityTag;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlTransient
public abstract class BaseModel {
	
	@XmlTransient
	protected EntityTag eTag = null;

	public EntityTag getETag() {
		return eTag;
	}

	public void setETag(EntityTag eTag) {
		this.eTag = eTag;
	}
	
}
