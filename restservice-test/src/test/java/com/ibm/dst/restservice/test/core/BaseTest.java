/**
 * 
 */
package com.ibm.dst.restservice.test.core;

import static com.ibm.dst.restservice.test.util.PropertyUtil.getProperty;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;

/**
 * @author 
 *
 */
public class BaseTest {
	
	private static final Logger logger = Logger.getLogger(BaseTest.class);
	
	private static String DB_DRIVER = getProperty("db.driver");
	
	private static String DB_URL = getProperty("db.url");
	
	private static String DB_USERNAME = getProperty("db.username");
	
	private static String DB_PASSWORD = getProperty("db.password");
	
	private static String DATA_BASE_DIR = BaseTest.class.getResource("/").getPath() + "com/ibm/dst/restservice/test/testdata/";
	
	protected String [] SPECIAL_CHARS = new String[] {
			"~", "`", "!", "@", //"#", 
			"$", "%", "^", //"&", 
			"*", "(", ")",
			"-", "_", "+", "[", "]", //"{", "}", 
			"\\", "|", //";", 
			":", "'",
			"\"", ",", ".", "<", ">"//, "/", "?"
		};
	
	/**
	 * instance of javax.ws.rs.client.Client
	 */
	protected Client client = null;
	
	protected String DEFAULT_APP_ID = getProperty("api.appId");
	
	protected String DEFAULT_APP_KEY = getProperty("api.appKey");
	
	protected String HTTP_GET = "GET";
	
	protected String HTTP_POST = "POST";
	
	protected String HTTP_PUT = "PUT";
	
	protected String HTTP_DELETE = "DELETE";
	
	/**
	 * the base url of resource service
	 */
	protected String BASE_RESOURCE_URL  = getProperty("resource.baseurl");
	
	protected static Map<String, String> RESOURCES_MAP = Constants.propertyMap;
	
	/**
	 * Define the operation should be done before test.<br><p>
	 * Initial the instance of javax.ws.rs.client.Client and javax.ws.rs.client.WebTarget.<br>
	 * Every subclass should call this method in its initial method or method with annotation @Before.<br>
	 */
	protected void before() {
		logger.info("Base Test init...");
		//org.codehaus.jackson.jaxrs.JacksonJsonProvider
		client = ClientBuilder.newClient();//.register(JacksonJsonProvider.class);
	}
	
	/**
	 * Define the operation should be done after test.It contains opposite operation of init().<br><p>
	 * Set variables defined in init() to null.<br>
	 * Every subclass should call this method in its destroy method or method with annotation @After.<br>
	 */
	protected void after() {
		logger.info("Base Test destroy.");
		if(null != client)
			client.close();
		client = null;
	}
	
	/**
	 * Get DBUnit DatabaseConnection
	 * @return IDdatabaseConnection
	 */
	private static IDatabaseConnection getConnection() {
		IDatabaseConnection connection = null;
		try {
			Class.forName(DB_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			connection = new DatabaseConnection(conn, null);
			connection.getConfig().setFeature(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
		} catch (ClassNotFoundException e) {
			logger.error("Error getting database connection, ClassNotFoundException: ", e);
		} catch (SQLException e) {
			logger.error("Error getting database connection, SQLException: ", e);
		}
		return connection;
	}
	
	/**
	 * Set test data to database before test
	 * @param files
	 */
	protected static void prepareData(String file) {
		logger.info("Prepare Test Data");
		if(null == file || "".equals(file)) 
			return;
		IDatabaseConnection connection = null;
		try {
			connection = getConnection();
			//set test data
			if(null != connection) {
				IDataSet dataset = new FlatXmlDataSet(new File(DATA_BASE_DIR + file));
				DatabaseOperation.CLEAN_INSERT.execute(connection, dataset);
			} else {
				logger.error("Error setting test data, connection is null.");
			}
		} catch (DataSetException e) {
			logger.error("Error setting test data, DataSetException: ", e);
		} catch (IOException e) {
			logger.error("Error setting test data, IOException: ", e);
		} catch (DatabaseUnitException e) {
			logger.error("Error setting test data, DatabaseUnitException: ", e);
		} catch (SQLException e) {
			logger.error("Error setting test data, SQLException: ", e);
		} finally {
			try {
				//close database connection
				if(null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Error closing database connection, SQLException: ", e);
			}
		}
	}
	
	/**
	 * Delete test data after test
	 * @param files
	 */
	protected static void deleteData(String file) {
		logger.info("Delete Test Data");
		if(null == file || "".equals(file)) 
			return;
		IDatabaseConnection connection = null;
		try {
			connection = getConnection();
			//delete test data
			if(null != connection) {
				IDataSet dataset = new FlatXmlDataSet(new File(DATA_BASE_DIR + file));  
				DatabaseOperation.DELETE.execute(connection, dataset);
			} else {
				logger.error("Error deleting test data, connection is null.");
			}
		} catch (DataSetException e1) {
			logger.error("Error deleting test data, DataSetException: ", e1);
		} catch (IOException e1) {
			logger.error("Error deleting test data, IOException: ", e1);
		} catch (DatabaseUnitException e1) {
			logger.error("Error deleting test data, DatabaseUnitException: ", e1);
		} catch (SQLException e1) {
			logger.error("Error deleting test data, SQLException: ", e1);
		} finally {
			try {
				//close database connection
				if(null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				logger.error("Error closing database connection, SQLException: ", e);
			}
		}
	}
	
	protected Response sendRequest(  String resource, 
							Map<String, String> pathParams, 
							Map<String, String> queryParams, 
							String mediaType, 
							String httpMethod,
							Object entity) {
		WebTarget target = client.target(BASE_RESOURCE_URL).path(resource);
		if(null != pathParams) {
			Set<Map.Entry<String, String>> pathParamSet = pathParams.entrySet();
			for(Iterator<Map.Entry<String, String>> pathParameters = pathParamSet.iterator();pathParameters.hasNext();) {
				Map.Entry<String, String> pathParameter = pathParameters.next();
				target = target.resolveTemplate(pathParameter.getKey(), pathParameter.getValue());
			}
		}
		if(null != queryParams) {
			Set<Map.Entry<String, String>> queryParamSet = queryParams.entrySet();
			for(Iterator<Map.Entry<String, String>> queryParameters = queryParamSet.iterator();queryParameters.hasNext();) {
				Map.Entry<String, String> queryParameter = queryParameters.next();
				target = target.queryParam(queryParameter.getKey(), queryParameter.getValue());
			}
		}
		Builder builder = target.request(mediaType);
		builder.header("AppId", DEFAULT_APP_ID);
		builder.header("AppKey", DEFAULT_APP_KEY);
		Invocation inv = null;
		if(httpMethod.equals(HTTP_GET))
			inv = builder.buildGet();
		else if(httpMethod.equals(HTTP_PUT))
			inv = builder.buildPut(Entity.json(entity));
		else if(httpMethod.equals(HTTP_DELETE))
			inv = builder.buildDelete();
		else
			inv = builder.buildGet();
		return inv.invoke();
	}
	
}
