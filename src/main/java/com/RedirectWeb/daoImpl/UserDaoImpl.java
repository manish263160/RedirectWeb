package com.RedirectWeb.daoImpl;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.RedirectWeb.dao.UserDao;
import com.RedirectWeb.models.UploadedImage;
import com.RedirectWeb.models.UploadedVideo;
import com.RedirectWeb.models.User;
import com.RedirectWeb.support.AnomanJdbcDaoSupport;

@Repository
public class UserDaoImpl extends AnomanJdbcDaoSupport implements UserDao {

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	private static final String GET_USER = "select u.* from user u " + " where u.email=? and u.password=?";

	
	
	
	
	public User validateUser(final String emailId, final String password) {
		logger.debug("validateUser() email: " + emailId);
		User user = null;
		try {
			user = null;
		} catch (EmptyResultDataAccessException e) {
			logger.error(" validateUser() EmptyResultDataAccessException");
		} catch (DataAccessException e) {
			logger.error(" validateUser() DataAccessException");
		}
		return user;
	}

	@Override
	public <T> T getImageByImgId(int editImageInfo, String tableName) {

		if (tableName.equals("uploaded_image")) {
			UploadedImage umg = null;
			try {
				String query = "select * from uploaded_image where id=?;";
				umg = getJdbcTemplate().queryForObject(query,
						new BeanPropertyRowMapper<UploadedImage>(UploadedImage.class), editImageInfo);
			} catch (EmptyResultDataAccessException e) {
				logger.error(" getImageByImgId() EmptyResultDataAccessException");
			} catch (DataAccessException e) {
				logger.error(" getImageByImgId() DataAccessException");
			}

			return (T) umg;
		}
		if (tableName.equals("uploaded_video")) {
			UploadedVideo umg = null;
			try {
				String query = "select * from uploaded_video where id=?;";
				umg = getJdbcTemplate().queryForObject(query,
						new BeanPropertyRowMapper<UploadedVideo>(UploadedVideo.class), editImageInfo);
			} catch (EmptyResultDataAccessException e) {
				logger.error(" getImageByImgId() EmptyResultDataAccessException");
			} catch (DataAccessException e) {
				logger.error(" getImageByImgId() DataAccessException");
			}
			return (T) umg;
		}

		return null;
	}	
}
