/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.RedirectWeb.dao;

import com.RedirectWeb.models.User;

public interface UserDao {
	
	User validateUser(String arg0, String arg1);

	<T> T getImageByImgId(int arg0, String tableName);
}