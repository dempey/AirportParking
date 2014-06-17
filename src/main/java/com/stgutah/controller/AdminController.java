package com.stgutah.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stgutah.form.JsonUserTypes;
import com.stgutah.form.JsonUsers;
import com.stgutah.model.UserType;
import com.stgutah.service.ModelService;

@Controller
@RequestMapping("/administrator")
public class AdminController
{
	private static Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	private ModelService modelService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String /*ModelAndView*/ getAdminPage()
	{
		logger.debug("Received request to show admin page");

		// Do your work here. Whatever you like
		// i.e call a custom service to do your business
		// Prepare a model to be used by the JSP page
//		GregorianCalendar calendar = new GregorianCalendar();
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
//		String currentTime = sdf.format(calendar.getTime());

//		Map<String, Object> modelObjects = new HashMap<String, Object>();
//		modelObjects.put("message", currentTime);
		
//		return new ModelAndView("adminpage", modelObjects);
		
		return "adminpage";

		// This will resolve to /WEB-INF/jsp/adminpage.jsp
		// return "adminpage";
	}
	
	@RequestMapping(value = "/userlist.json", method = RequestMethod.GET)
	public JsonUsers getAllUsers()
	{
		String[][] dbUsersArray = modelService.getAllUsersArray();
		JsonUsers jsonUsers = new JsonUsers();
		jsonUsers.setaData(dbUsersArray);
		return jsonUsers;
	}
	
	@RequestMapping(value = "/usertypelist.json", method = RequestMethod.GET)
	public JsonUserTypes getAllUserTypes() throws Exception
	{
		Map<Integer, String> userTypesMap = (HashMap<Integer, String>)modelService.getJsonUserTypesMap();
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonData = mapper.writeValueAsString(userTypesMap);
		JsonUserTypes jsonUserTypes = new JsonUserTypes();
		jsonUserTypes.setaData(jsonData);

		return jsonUserTypes;
	}
	
	@RequestMapping(value = "/usertypeedit", method = RequestMethod.POST)
	public @ResponseBody String setUserTypeChange(HttpServletRequest request, ModelMap model)
	{
		String selectedValue = request.getParameter("value");
		String column = request.getParameter("column");
		String userId = request.getParameter("row_id");
		
		logger.info("Received update request for userId " + userId + " from column " + column + ".  New value is '" + selectedValue + "'");
		
		//get the userType by userTypeId
		UserType userType = modelService.getUserTypeById(Integer.parseInt(selectedValue));
		
		modelService.updateUserTypeByUserId(Integer.parseInt(userId), Integer.parseInt(selectedValue));
		
		return userType.getDescription();
	}
	
	@RequestMapping(value = "/companynameedit", method = RequestMethod.POST)
	public @ResponseBody String setCompanyNameChange(HttpServletRequest request, ModelMap model)
	{
		String companyName = request.getParameter("value");
		String column = request.getParameter("column");
		String userId = request.getParameter("row_id");
		
		logger.info("Received update request for userId " + userId + " from column " + column + ".  New value is '" + companyName + "'");
		
		modelService.updateUserCompanyNameByUserId(Integer.parseInt(userId), companyName);
		
		return companyName;
	}
}
