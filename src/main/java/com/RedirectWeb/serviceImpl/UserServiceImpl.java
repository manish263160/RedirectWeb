package com.RedirectWeb.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RedirectWeb.dao.UserDao;
import com.RedirectWeb.exception.GenericException;
import com.RedirectWeb.models.UploadedImage;
import com.RedirectWeb.models.UploadedVideo;
import com.RedirectWeb.models.User;
import com.RedirectWeb.service.UserService;
import com.RedirectWeb.utils.ApplicationConstants;
import com.RedirectWeb.utils.ApplicationProperties;


@Service("usrsrvc")
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	private @Autowired ApplicationProperties applicationProperties;
	
	public User userLogin(String email, String password) throws GenericException {
		return userDao.validateUser(email, password);
	}
	@Override
	public <T> T getImageByImgId(int editImageInfo,String tableName, boolean token) {
		if(tableName.equals("uploaded_image")){
		UploadedImage uploadiMg= userDao.getImageByImgId(editImageInfo,tableName);
		if(!token){
		uploadiMg.setImageUrl(applicationProperties.getProperty(ApplicationConstants.SITE_NAME) + uploadiMg.getUserId()
		+ applicationProperties.getProperty(ApplicationConstants.UPLOADED_IMAGE) + uploadiMg.getImageUrl());
		}
		return (T) uploadiMg;
		}
		if(tableName.equals("uploaded_video")){
			UploadedVideo uploadiMg= userDao.getImageByImgId(editImageInfo,tableName);
			if(!token){
			uploadiMg.setVideoThumbnail(applicationProperties.getProperty(ApplicationConstants.SITE_NAME) + uploadiMg.getUserId()
			+ applicationProperties.getProperty(ApplicationConstants.UPLOADED_VIDEO) + uploadiMg.getVideoThumbnail());
			}
			return (T) uploadiMg;
		}
		
		return null;
	}
}
