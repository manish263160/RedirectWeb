package com.RedirectWeb.service;

import com.RedirectWeb.exception.GenericException;
import com.RedirectWeb.models.User;

public interface UserService {
	User userLogin(String arg0, String arg1) throws GenericException;
	<T> T getImageByImgId(int imageId,String tableName, boolean token);
}