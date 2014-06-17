package com.stgutah.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stgutah.form.JsonUserReservations;
import com.stgutah.service.ModelService;

@Controller
@RequestMapping("/reservationmanage")
public class ManageReservationController
{
	private static Logger logger = Logger.getLogger(ManageReservationController.class);
	
	@Autowired
	private ModelService modelService;
	
	@RequestMapping(value = "/managereservations", method = RequestMethod.GET)
	public String getReservationManagePage()
	{
		String username = modelService.getCurrentSessionUser().getUserName();
		
		logger.debug("Received request to show " + username + "'s reservation management page");
		
		return "reservationmanage";
	}
	
	@RequestMapping(value = "/reservationlist.json", method = RequestMethod.GET)
	public @ResponseBody JsonUserReservations getAllReservationsByUser() throws Exception
	{
		JsonUserReservations reservations = modelService.getAllReservationsAsJsonObject();
		return reservations;
	}
	
	@RequestMapping(value="/deletereservation", method = RequestMethod.POST)
	public void deleteSelectedReservation(HttpServletRequest request, ModelMap model) throws Exception
	{
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		String slotNumber = request.getParameter("slotNumber");
		logger.info("Received delete request for period " + beginDate + " to " + endDate + " for slot number " + slotNumber + ".");
		
		modelService.deleteReservationByDatesAndSlotNumber(beginDate, endDate, slotNumber);
	}
}

