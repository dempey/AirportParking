package com.stgutah.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.stgutah.form.NewVehicleForm;
import com.stgutah.service.ModelServiceImpl;

@Controller
@RequestMapping("/vehicle")
public class AddVehicleController
{
	private static Logger logger = Logger.getLogger(AddVehicleController.class);
	
	@Autowired
	private ModelServiceImpl modelService;
	
	@RequestMapping(value = "/addvehicle", method = RequestMethod.GET)
	public String getVehiclePage(ModelMap model)
	{
		logger.info("Displaying addvehicle page");
		
		NewVehicleForm newVehicleForm = new NewVehicleForm();
		model.addAttribute("newvehicle", newVehicleForm);
		
		//Create a list of colors - this could be replaced by something more elegant/configurable
		List<String> colors = new ArrayList<String>();
		colors.add("PURPLE");
		colors.add("ORANGE");
		colors.add("BLACK");
		colors.add("RED");
		colors.add("GREEN");
		colors.add("DARK BLUE");
		colors.add("YELLOW");
		colors.add("LIGHT BLUE");
		colors.add("PINK");
		colors.add("GRAY");
		colors.add("WHITE");
		colors.add("TAN");
		model.addAttribute("colors", colors);
		
		return "addvehicle";
	}
	
	@RequestMapping(value = "/submitnewvehicle", method = RequestMethod.POST)
	public ModelAndView submitNewVehicleInformation(@ModelAttribute("newVehicleForm") NewVehicleForm newVehicleForm, BindingResult result, HttpServletRequest request, ModelMap model)
	{
		String plateNumber = newVehicleForm.getPlateNumber();
		
		//Check to see if the tag exists - must be unique
		boolean vehicleExists = modelService.vehicleExistsByTag(plateNumber);
		
		if(vehicleExists)
		{
			//Create a list of colors - this could be replaced by something more elegant/configurable
			List<String> colors = new ArrayList<String>();
			colors.add("PURPLE");
			colors.add("ORANGE");
			colors.add("BLACK");
			colors.add("RED");
			colors.add("GREEN");
			colors.add("DARK BLUE");
			colors.add("YELLOW");
			colors.add("LIGHT BLUE");
			colors.add("PINK");
			colors.add("GRAY");
			colors.add("WHITE");
			colors.add("TAN");
			model.addAttribute("colors", colors);
			
			model.addAttribute("newvehicle", newVehicleForm);
			//The vehicle already exists so add an error message to model and return to addvehicle.jsp
			model.put("error", "Plate Number '" + plateNumber + "' is already in use.");
			return new ModelAndView("addvehicle");
		}
		
		logger.debug("New vehicle is: " + newVehicleForm.getMake() + " " + newVehicleForm.getModel() + ", with plate number " + plateNumber);
		
		modelService.addNewVehicle(newVehicleForm);
		
		return new ModelAndView("commonpage");
	}
}

