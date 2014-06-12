package com.stgutah.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController
{
	private static Logger logger = Logger.getLogger(MainController.class);
	
	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String getCommonPage()
	{
		logger.debug("Received request to show common page");
		
		return "commonpage";
	}
}
