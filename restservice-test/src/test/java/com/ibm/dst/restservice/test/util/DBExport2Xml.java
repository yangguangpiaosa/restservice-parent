package com.ibm.dst.restservice.test.util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DBExport2Xml {
	
	public static void export2Xml(String table, String file) throws ClassNotFoundException, Exception {
		Class.forName("com.ibm.db2.jcc.DB2Driver");
	    Connection conn = DriverManager.getConnection("jdbc:db2://server:port/dbname","username","passoword"); 
        IDatabaseConnection connection = new DatabaseConnection(conn,"schema");
        QueryDataSet dataSet = new QueryDataSet(connection);
        dataSet.addTable(table);
        //export data to xml file
        FlatXmlDataSet.write(dataSet,new FileOutputStream(file));
	}

	/**
	 * @param args
	 * @throws Exception 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, Exception {
		
		DBExport2Xml.export2Xml("SCHEMA.TABLE_NAME", "c:\\testdata\\test.xml");
		
	}

}
