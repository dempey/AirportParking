package com.stgutah.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class LoginLogoutController
{
	private static Logger logger = Logger.getLogger(LoginLogoutController.class);
	
	/**
	 * Handles and retrieves the login JSP page
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error, ModelMap model)
	{
		logger.info("Called getLoginPage() method");
		
		if(error == true)
		{
			model.put("error", "You have entered an invalid username or password!");
		}
		else
		{
			model.put("error", "");
		}
		
		// This will resolve to /WEB-INF/jsp/loginpage.jsp
		return "loginpage";
	}
	
	/**
	 * @deprecated
	 * 
	 * Deprecated because a non-admin user will not even be shown the link to the admin page(s).
	 * 
	 * Handles and retrieves the denied JSP page.  This is shown whenever a regular
	 * user tries to access an admin-only page.
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage()
	{
		logger.debug("Received request to show denied page");
		
		//This will resolve to /WEB-INF/jsp/deniedpage.jsp
		return "deniedpage";
	}
}