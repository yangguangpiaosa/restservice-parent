/**
 * 
 */
package com.ibm.dst.restservice.test.util;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import com.ibm.dst.restservice.test.core.Message;

/**
 * @author 
 *
 */
public class JaxbUtilTest {
	@Test
	public void testToXml() {
		Message msg = new Message();
    	msg.setCode(10000);
    	msg.setMsg("xxxxxx");
    	String xml = JaxbUtil.toXML(msg);
    	Message re = JaxbUtil.fromXML(xml, Message.class);
    	Assert.assertEquals(10000, re.getCode());
    	Assert.assertEquals("xxxxxx", re.getMsg());
	}
	
	@Test
	public void testToXmlFile() {
		String xmlpath = JaxbUtil.class.getResource("/").getPath()+"com/ibm/dst/restservice/test/util/msg.xml";
		Message msg = new Message();
		msg.setCode(10000);
		msg.setMsg("xxxxxx");
    	JaxbUtil.toXMLFile(msg, xmlpath);
    	Message eb = JaxbUtil.fromXMLFile(xmlpath, Message.class);
		Assert.assertEquals(10000, eb.getCode());
    	Assert.assertEquals("xxxxxx", eb.getMsg());
    	File f = new File(xmlpath);
    	if(f.exists()) {
    		f.delete();
    	}
	}
	
	@Test
	public void testFromXml() {
		Message msg = new Message();
		msg.setCode(10000);
		msg.setMsg("xxxxxx");
    	String xml = JaxbUtil.toXML(msg);
    	Message re = JaxbUtil.fromXML(xml, Message.class);
    	Assert.assertEquals(10000, re.getCode());
    	Assert.assertEquals("xxxxxx", re.getMsg());
	}
	
	@Test
	public void testFromXmlFile() {
		String xmlpath = JaxbUtil.class.getResource("/").getPath()+"com/ibm/dst/restservice/test/util/message.xml";
		Message eb = JaxbUtil.fromXMLFile(xmlpath, Message.class);
		Assert.assertEquals(10000, eb.getCode());
    	Assert.assertEquals("xxxxxx", eb.getMsg());
	}
	
}
