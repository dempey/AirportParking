package com.stgutah.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stgutah.form.JsonUserVehicles;
import com.stgutah.service.ModelServiceImpl;

@Controller
@RequestMapping("/vehiclemanage")
public class ManageVehicleController
{
	private static Logger logger = Logger.getLogger(ManageVehicleController.class);
	
	@Autowired
	private ModelServiceImpl modelService;
	
	@RequestMapping(value = "/managevehicles", method = RequestMethod.GET)
	public String getVehicleManagePage()
	{
		String username = modelService.getCurrentSessionUser().getUserName();
		
		logger.debug("received request to show " + username + "'s vehicle management page");
		
		return "vehiclemanage";
	}
	
	@RequestMapping(value = "/vehiclelist.json", method = RequestMethod.GET)
	public @ResponseBody JsonUserVehicles getAllVehiclesByUser() throws Exception
	{
		JsonUserVehicles vehicles = modelService.getAllVehiclesAsJsonObject();
		return vehicles;
	}
	
	@RequestMapping(value="/deletevehicle", method = RequestMethod.POST)
	public void deleteSelectedVehicle(HttpServletRequest request, ModelMap model)
	{
		String carMake = request.getParameter("make");
		String carModel = request.getParameter("model");
		String plateNumber = request.getParameter("plateNumber");
		
		logger.info("Received delete request for vehicle " + carMake + " " + carModel + " with plate number " + plateNumber + ".");
		
		modelService.deleteVehicleByMakeModelPlate(plateNumber);
	}
}
