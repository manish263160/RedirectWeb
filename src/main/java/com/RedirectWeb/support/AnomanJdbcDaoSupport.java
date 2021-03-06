package com.RedirectWeb.support;


import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * 
 * @author manishm
 * 
 * Wrapper class for DataSource
 *
 */
public class AnomanJdbcDaoSupport {
	
    private static final Logger logger = Logger.getLogger(AnomanJdbcDaoSupport.class);
	
	@Autowired private DataSource dataSource;

	/**
	 * @return
	 */
	protected JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * @return
	 */
	protected NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	
	
}
