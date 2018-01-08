package com.RedirectWeb.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.RedirectWeb.models.UploadedImage;
import com.RedirectWeb.models.UploadedVideo;
import com.RedirectWeb.service.UserService;

@Controller
public class ShareController {

	@Autowired
	UserService userService;
	
	private @Autowired VelocityEngine velocityEngine;
	
	private static final Logger logger = Logger.getLogger(ShareController.class);
	@RequestMapping(value = "/PrerenderWeb}", method = RequestMethod.GET)
	public String defaultMethod(ModelMap model ) {
		logger.info("default page call");
			return "defautPage";
		}
		
	@RequestMapping(value = "/PrerenderWeb/{specific}/{id}", method = RequestMethod.GET)
	public String sayHelloAgain(ModelMap model,@PathVariable String specific , @PathVariable String id ) {
		
	logger.info("id got heree iss specific ====="+specific+"===================================="+id);
	int idfor;
	if(id != null && !id.equals("index")) {
		idfor = Integer.parseInt(id);
	}else {
		return "defautPage";
	}
	logger.info("the value of Idfor ====="+idfor);
	String hostUrl= "http://showoff.tv/";
	String tableName ="";
	Map<String, String> ogmap = new HashMap<String, String>();
		if(specific.equals("specificVideo")) {
			tableName ="uploaded_video";
			UploadedVideo vidsdata = userService.getImageByImgId(idfor , tableName , false);
			ogmap.put("ogurl", hostUrl+"/"+specific+"/"+id);
			ogmap.put("ogtitle", vidsdata.getTitle());
			ogmap.put("ogimage", vidsdata.getVideoThumbnail());
			ogmap.put("ogdescription", vidsdata.getDescription());
		}else if(specific.equals("specificStory")) {
			tableName ="uploaded_image";
			 UploadedImage imgdata = userService.getImageByImgId(idfor , tableName , false);
			 ogmap.put("ogurl", hostUrl+"/"+specific+"/"+id);
				ogmap.put("ogtitle", imgdata.getImageName() != null ? imgdata.getImageName() : "Showoff.tv shared Image");
				ogmap.put("ogimage", imgdata.getImageUrl());
				ogmap.put("ogdescription", imgdata.getImageLink()!=null ?imgdata.getImageLink() : "Showoff.tv" );
		}
		model.addAttribute("ogmap", ogmap);
		return "sharedpaage";
	}
	/*@RequestMapping(value = "/robots.txt", method = RequestMethod.GET)
	@ResponseBody
	public String robot(HttpServletResponse response) {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"Templates/robots.vm", "UTF-8", null);
	}*/
	
}
