package com.stgutah.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stgutah.form.JsonReservations;
import com.stgutah.form.NewReservationForm;
import com.stgutah.model.Amount;
import com.stgutah.model.ParkingLot;
import com.stgutah.model.Reservation;
import com.stgutah.model.User;
import com.stgutah.model.Vehicle;
import com.stgutah.service.ModelServiceImpl;

@Controller
@RequestMapping("/reservation")
public class ReservationController
{
	@Autowired
	private ModelServiceImpl modelService;
	
	private static Logger logger = Logger.getLogger(ReservationController.class);
	
	@RequestMapping(value = "/addreservation", method = RequestMethod.GET)
	public String startReservation(ModelMap modelMap)
	{
		logger.info("Starting a new reservation");
		
		//Add the form object
		NewReservationForm newReservation = new NewReservationForm();
		modelMap.addAttribute("newreservation", newReservation);
		
		//Get a list of the customer's vehicles so they can select the one they want
		User currentUser = modelService.getCurrentSessionUser();
		List<Vehicle> vehicles = modelService.getVehiclesByUser(currentUser);
		Map<Integer, String> vehicleMap = new HashMap<Integer, String>();
		for(Vehicle vehicle : vehicles)
		{
			vehicleMap.put(vehicle.getVehicleId(), vehicle.getMake() + " " + vehicle.getModel());
		}
		modelMap.addAttribute("vehicles", vehicleMap);
		
		return "reservation";
	}
	
	@RequestMapping(value = "/selectslot", method = RequestMethod.POST)
	public String setSlotSelection(@ModelAttribute("newReservationForm") NewReservationForm newReservationForm,
			BindingResult result, HttpServletRequest request, ModelMap modelMap)
	{
		logger.info("Starting selection of parking slot");
		
		modelMap.addAttribute("newreservation", newReservationForm);
		
		//TODO: add list of available parking slots to the modelMap
		
		return "slotselection";
	}
	
	@RequestMapping(value = "/validation", method = RequestMethod.POST)
	public String getReservationValidation(@ModelAttribute("newReservationForm") NewReservationForm newReservationForm,
			BindingResult result, HttpServletRequest request, ModelMap modelMap) throws Exception
	{
		logger.info("Starting reservation validation");
		
		String submittedSlotNumber = newReservationForm.getSlotNumber();
		
		logger.info("User requested slot number " + submittedSlotNumber);
		
		//Get vehicle details
		Vehicle vehicle = modelService.getVehicleByVehicleId(newReservationForm.getVehicleId());
		modelMap.addAttribute("vehicleMake", vehicle.getMake());
		modelMap.addAttribute("vehicleModel", vehicle.getModel());
		modelMap.addAttribute("vehiclePlate", vehicle.getPlateNumber());
		
		//Get Parking lot details
		ParkingLot parkingLot = modelService.getParkingLotBySlotNumber(newReservationForm.getSlotNumber());
		modelMap.addAttribute("parkingLotName", parkingLot.getName());
		modelMap.addAttribute("parkingLotStreet", parkingLot.getStreet());
		modelMap.addAttribute("parkingLotCity", parkingLot.getCity());
		modelMap.addAttribute("parkingLotState", parkingLot.getState().getName());
		modelMap.addAttribute("parkingLotZip", parkingLot.getZipCode());
		
		String fromDateString = newReservationForm.getFromDate();
		String toDateString = newReservationForm.getToDate();
		
		modelMap.addAttribute("newreservation", newReservationForm);
		modelMap.addAttribute("parkingSlot", newReservationForm.getSlotNumber());
		modelMap.addAttribute("fromDate", getFormattedDateString(fromDateString));
		modelMap.addAttribute("toDate", getFormattedDateString(toDateString));
		
		Integer numberDays = modelService.getNumberDaysReserved(getFormattedDateObject(fromDateString), getFormattedDateObject(toDateString));
		modelMap.addAttribute("numberDays", numberDays);
		Amount pricePerDayAmount = modelService.getDailyParkingRateByParkingLot(parkingLot);
		modelMap.addAttribute("pricePerDay", pricePerDayAmount.getAmount());
		Float basePrice = numberDays * pricePerDayAmount.getAmount();
		modelMap.addAttribute("basePrice", basePrice);
		Amount taxRateAmount = modelService.getTaxRateByParkingLot(parkingLot);
		modelMap.addAttribute("taxRate", taxRateAmount.getAmount());
		Float totalTax = taxRateAmount.getAmount() * basePrice;
		modelMap.addAttribute("totalTax", totalTax);
		modelMap.addAttribute("totalPrice", totalTax + basePrice);
		
		return "validation";
	}
	
	@RequestMapping(value = "/receipt", method = RequestMethod.POST)
	public String getReservationReceipt(@ModelAttribute("newReservationForm") NewReservationForm newReservationForm,
			BindingResult result, HttpServletRequest request, ModelMap modelMap)  throws Exception
	{
		logger.info("Persisting reservation and starting reservation receipt");
		
		//Get vehicle details
		Vehicle vehicle = modelService.getVehicleByVehicleId(newReservationForm.getVehicleId());
		modelMap.addAttribute("vehicleMake", vehicle.getMake());
		modelMap.addAttribute("vehicleModel", vehicle.getModel());
		modelMap.addAttribute("vehiclePlate", vehicle.getPlateNumber());
		
		//Get Parking lot details
		ParkingLot parkingLot = modelService.getParkingLotBySlotNumber(newReservationForm.getSlotNumber());
		modelMap.addAttribute("parkingLotName", parkingLot.getName());
		modelMap.addAttribute("parkingLotStreet", parkingLot.getStreet());
		modelMap.addAttribute("parkingLotCity", parkingLot.getCity());
		modelMap.addAttribute("parkingLotState", parkingLot.getState().getName());
		modelMap.addAttribute("parkingLotZip", parkingLot.getZipCode());
		
		String fromDateString = newReservationForm.getFromDate();
		String toDateString = newReservationForm.getToDate();
		
		modelMap.addAttribute("newreservation", newReservationForm);
		modelMap.addAttribute("parkingSlot", newReservationForm.getSlotNumber());
		modelMap.addAttribute("fromDate", getFormattedDateString(fromDateString));
		modelMap.addAttribute("toDate", getFormattedDateString(toDateString));
		
		Integer numberDays = modelService.getNumberDaysReserved(getFormattedDateObject(fromDateString), getFormattedDateObject(toDateString));
		modelMap.addAttribute("numberDays", numberDays);
		Amount pricePerDayAmount = modelService.getDailyParkingRateByParkingLot(parkingLot);
		modelMap.addAttribute("pricePerDay", pricePerDayAmount.getAmount());
		Float basePrice = numberDays * pricePerDayAmount.getAmount();
		modelMap.addAttribute("basePrice", basePrice);
		Amount taxRateAmount = modelService.getTaxRateByParkingLot(parkingLot);
		modelMap.addAttribute("taxRate", taxRateAmount.getAmount());
		Float totalTax = taxRateAmount.getAmount() * basePrice;
		modelMap.addAttribute("totalTax", totalTax);
		modelMap.addAttribute("totalPrice", totalTax + basePrice);
		
		//actually persist the reservation to the database
		modelService.addNewReservation(newReservationForm);
		
		return "receipt";
	}
	
	@RequestMapping(value = "/existingReservations.json", method = RequestMethod.GET)
	public JsonReservations getJsonReservations(HttpServletRequest request, ModelMap model) throws Exception
	{
		DateFormat parser = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss 'GMT'Z (z)");
		
		Date fromDate = parser.parse(request.getParameter("fromDate"));
		Date toDate = parser.parse(request.getParameter("toDate"));
		
		List<Reservation> reservations= modelService.getReservationsByDateRange(fromDate, toDate);
		
		int reservationCount = reservations.size();
		String[] reservationArray = new String[reservationCount];
		for(int i = 0; i < reservationCount; i++)
		{
			Reservation reservation = reservations.get(i);
			reservationArray[i] = reservation.getParkingSlot().getNumber();
		}
		
		JsonReservations jsonReservations = new JsonReservations();
		jsonReservations.setaData(reservationArray);
		
		return jsonReservations;
	}
	
	private String getFormattedDateString(String dateString) throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss 'GMT'Z (z)");
		
		Date myDate = formatter.parse(dateString);
		
		DateFormat simplerDateFormatter = new SimpleDateFormat("MMMM dd, yyyy (EEE)");
		
		String formattedDate = simplerDateFormatter.format(myDate);
		
		return formattedDate;
	}
	
	private Date getFormattedDateObject(String dateString) throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat("EEE MMM dd yyyy hh:mm:ss 'GMT'Z (z)");
		
		Date myDate = formatter.parse(dateString);
		
		return myDate;
	}
}