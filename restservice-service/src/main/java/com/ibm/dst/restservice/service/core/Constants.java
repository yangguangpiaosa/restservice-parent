/**
 * 
 */
package com.ibm.dst.restservice.service.core;

import java.util.Map;

/**
 * @author 
 *
 */
public class Constants {
	public Map<String, String> messageMap = null;
	public Map<String, String> authorityMap = null;
	public Map<String, String> getMessageMap() {
		return messageMap;
	}
	public void setMessageMap(Map<String, String> messageMap) {
		this.messageMap = messageMap;
	}
	public Map<String, String> getAuthorityMap() {
		return authorityMap;
	}
	public void setAuthorityMap(Map<String, String> authorityMap) {
		this.authorityMap = authorityMap;
	}
}
