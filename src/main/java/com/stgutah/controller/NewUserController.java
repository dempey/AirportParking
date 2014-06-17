package com.stgutah.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.servlet.ModelAndView;

import com.stgutah.form.NewUserForm;
import com.stgutah.model.State;
import com.stgutah.service.ModelService;

@Controller
@SessionAttributes
@RequestMapping("/anonymous")
public class NewUserController
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@Autowired
	protected ModelService modelService;
	
	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String getNewUserPage(ModelMap model)
	{
		logger.debug("Received request to show new user page");

		// Do your work here. Whatever you like
		// i.e call a custom service to do your business
		// Prepare a model to be used by the JSP page
		
		//Add the form object
		NewUserForm newUser = new NewUserForm();
		model.addAttribute("newuser", newUser);
		
		
		//Get the list of states
		List<State> states = modelService.getStates();
		model.addAttribute("states", states);
		
//		GregorianCalendar calendar = new GregorianCalendar();
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
//		String currentTime = sdf.format(calendar.getTime());

//		Map<String, Object> modelObjects = new HashMap<String, Object>();
//		modelObjects.put("message", currentTime);
		
//		return new ModelAndView("newuser", modelObjects);userService
		
			//Then in jsp use this syntax: <p>Current Time: ${message}</p>
			// or like this:
			//	<th>
		  //  Type: <form:errors path="type" cssClass="errors"/>
		  //  <br/>
		  //  <form:select path="type" items="${types}"/>
		  //  </th>
		
		// This will resolve to /WEB-INF/jsp/newuserpage.jsp
		
		return "newuser";
	}
	
	@RequestMapping(value = "/submitnewuser", method = RequestMethod.POST)
	public String /*ModelAndView*/ submitNewUserInformation(@ModelAttribute("newUserForm") NewUserForm newUserForm, BindingResult result, HttpServletRequest request, ModelMap model)
//	public String submitNewUserInformation(@Valid @ModelAttribute("newUserForm") NewUserForm newUserForm, BindingResult result, HttpServletRequest request, ModelMap model)
	{
		logger.debug("New username is: " + newUserForm.getUsername());
		
		String submittedUsername = newUserForm.getUsername();
		
		//Check to see if the username exists - must be unique
		boolean usernameExists = modelService.userExistsByUsername(submittedUsername);
		
		if(usernameExists)
		{
			//Get the list of states
			List<State> states = modelService.getStates();
			model.addAttribute("states", states);
			
			model.addAttribute("newuser", newUserForm);
			//The username already exists so add an error message to model and return to newuser.jsp
			model.put("error", "User name '" + submittedUsername + "' is already in use.");
			//return new ModelAndView("newuser");
			return "newuser";
		}
		
		modelService.addNewUser(newUserForm);
		
		//return new ModelAndView("loginpage");
		return "loginpage";
	}
}
