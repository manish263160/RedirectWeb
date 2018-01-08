package com.RedirectWeb.utils;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.RedirectWeb.support.AnomanJdbcDaoSupport;

public class ApplicationProperties extends AnomanJdbcDaoSupport {

	private static final Logger logger = Logger.getLogger(ApplicationProperties.class);
	
	private class ApplicationProperty {
		private String key;
		private String value;


		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}

		/**
		 * @param key the key to set
		 */
		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	private Map<String, String> properties = new HashMap<String, String>();
	
//	private static List<String>  swearWords=new ArrayList<String>();
	
	/**
	 * loads all properties
	 */
	
	public void init() {
		logger.info("ApplicationProperties.init() LOADING ALL PROPERTIES...");
		String sql ="select ap.name,ap.value from application_properties ap";
		List<ApplicationProperty> allPropertiesFromDb = getJdbcTemplate().query(sql, new RowMapper<ApplicationProperty>() {
			public ApplicationProperty mapRow(ResultSet rs, int rowNum) throws SQLException {
				ApplicationProperty applicationProperty = new ApplicationProperty();
				applicationProperty.setKey(rs.getString("name"));
				applicationProperty.setValue(rs.getString("value"));
				return applicationProperty;
			}
		});
		
//		sectors.clear();
		
		properties.clear();
//		swearWords.clear();
		
		
		for (ApplicationProperty applicationProperty : allPropertiesFromDb) {
			properties.put(applicationProperty.getKey(), applicationProperty.getValue());
		}
		
		logger.info( "ApplicationProperties.init() LOADING ALL PROPERTIES... DONE");
	}
	
	
/**
 *  method to get property by passing propertyName
 * @param propertyName -application property name
 * @return propertyValue
 */
	public String getProperty(String propertyName) {
		logger.debug( "ApplicationProperties.getProperty() looking property: " + propertyName);
		
		String propertyValue = properties.get(propertyName);
		logger.debug( "ApplicationProperties.getProperty() property value: " + propertyValue);
		
		return propertyValue;
	}
/**
 * overridden method to get property by passing propertyName and Default
 * @param propertyName-application property name
 * @param Default-default property
 * @return propertyValue
 */
	public String getProperty(String propertyName, String Default) {
		logger.debug( "ApplicationProperties.getProperty(Default) looking property: " + propertyName);
		
		String propertyValue = properties.get(propertyName) == null? Default : properties.get(propertyName);
		logger.debug( "ApplicationProperties.getProperty(Default) property value: " + propertyValue);
		
		return propertyValue;
	}
	
	
}
