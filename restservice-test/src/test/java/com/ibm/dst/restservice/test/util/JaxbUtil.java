/**
 * 
 */
package com.ibm.dst.restservice.test.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author 
 *
 */
public class JaxbUtil {
	public static String toXML(Object obj) {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");//encoding
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// formatted the xml string or not
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// omit the declaration information or not
            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings({ "unchecked" })
    public static <T> T fromXML(String xml, Class<T> valueType) {
        try {
            JAXBContext context = JAXBContext.newInstance(valueType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public static void toXMLFile(Object obj,String filePath) {
    	FileOutputStream fos = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");//encoding
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// formatted the xml string or not
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// omit the declaration information or not
            fos = new FileOutputStream(filePath);
            marshaller.marshal(obj, fos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        	try {
        		if(null != fos)
        			fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    @SuppressWarnings({ "unchecked" })
	public static <T> T fromXMLFile(String filePath, Class<T> valueType) {
    	FileInputStream fis = null;
        try {
            JAXBContext context = JAXBContext.newInstance(valueType);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            fis = new FileInputStream(filePath);
            return (T) unmarshaller.unmarshal(fis);
        } catch (Exception e) {
        	e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
        	try {
				if(null != fis)
					fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
