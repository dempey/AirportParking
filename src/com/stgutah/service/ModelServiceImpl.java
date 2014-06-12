package com.stgutah.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.stgutah.dao.ModelDaoImpl;
import com.stgutah.form.JsonLanguages;
import com.stgutah.form.JsonUserReservations;
import com.stgutah.form.JsonUserVehicles;
import com.stgutah.form.NewReservationForm;
import com.stgutah.form.NewUserForm;
import com.stgutah.form.NewVehicleForm;
import com.stgutah.model.Amount;
import com.stgutah.model.Email;
import com.stgutah.model.EmailType;
import com.stgutah.model.EmailTypeEnum;
import com.stgutah.model.Language;
import com.stgutah.model.ParkingLot;
import com.stgutah.model.ParkingSlot;
import com.stgutah.model.Phone;
import com.stgutah.model.PhoneType;
import com.stgutah.model.Reservation;
import com.stgutah.model.State;
import com.stgutah.model.User;
import com.stgutah.model.UserType;
import com.stgutah.model.UserTypeEnum;
import com.stgutah.model.Vehicle;
import com.stgutah.util.SecurityMD5UtilImpl;

@Configuration
public class ModelServiceImpl {
	@Autowired
	private ModelDaoImpl modelDao;

	@Autowired
	private SecurityMD5UtilImpl securityUtil;

	@Transactional
	public void addNewUser(NewUserForm newUserForm) {
		User user = new User();

		// Do all database selects first
		State state = modelDao.getStateByCode(newUserForm.getState());
		EmailType emailType = modelDao
				.getEmailTypeByDescription(EmailTypeEnum.PERSONAL.toString()); // TODO
																				// replace
																				// with
																				// content
																				// from
																				// dropdown
																				// selection
		PhoneType phoneType = modelDao
				.getPhoneTypeByDescription(EmailTypeEnum.PERSONAL.toString()); // TODO
																				// replace
																				// with
																				// content
																				// from
																				// dropdown
																				// selection
		// By default all new users get a type of 'CUSTOMER'
		UserType userType = modelDao
				.getUserTypeByDescription(UserTypeEnum.CUSTOMER.toString());
		user.setUserType(userType);
		// end database selects

		user.setCity(newUserForm.getCity());
		user.setCompanyName(newUserForm.getCompanyName());
		user.setDateCreated(new Date());
		user.setFirstName(newUserForm.getFirstName());
		user.setLastName(newUserForm.getLastName());
		user.setPasswordMd5(securityUtil.getMD5Value(newUserForm.getPassword()));

		Email primaryEmail = new Email(newUserForm.getPrimaryEmail(), emailType);
		modelDao.addEmail(primaryEmail);
		user.setPrimaryEmail(primaryEmail);

		Phone primaryPhone = new Phone(newUserForm.getPrimaryPhone(),
				phoneType, newUserForm.isPrimaryPhoneAcceptsText());
		modelDao.addPhone(primaryPhone);
		user.setPrimaryPhone(primaryPhone);

		if (newUserForm.getSecondaryEmail() != null) {
			Email secondaryEmail = new Email(newUserForm.getSecondaryEmail(),
					emailType);
			modelDao.addEmail(secondaryEmail);
			user.setSecondaryEmail(secondaryEmail);
		}

		if (newUserForm.getSecondaryPhone() != null) {
			Phone secondaryPhone = new Phone(newUserForm.getSecondaryPhone(),
					phoneType, newUserForm.isSecondaryPhoneAcceptsText());
			modelDao.addPhone(secondaryPhone);
			user.setSecondaryPhone(secondaryPhone);
		}

		user.setState(state);
		user.setStreet(newUserForm.getStreet());
		user.setUserName(newUserForm.getUsername());
		user.setZipCode(newUserForm.getZipCode());

		modelDao.addNewUser(user);
	}

	
	public boolean userExistsByUsername(String username) {
		boolean userExists = false;

		User user = modelDao.getUserByUsername(username);

		if (user != null) {
			userExists = true;
		}

		return userExists;
	}

	public User getUserByUsername(String username) {
		User user = modelDao.getUserByUsername(username);
		return user;
	}

	public User getCurrentSessionUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = authentication.getName();
		User user = getUserByUsername(username);

		return user;
	}

	
	@Transactional
	public void updateUserTypeByUserId(Integer userId, Integer userTypeId) {
		modelDao.updateUserTypeByUserId(userId, userTypeId);
	}

	
	@Transactional
	public void updateUserCompanyNameByUserId(Integer userId, String companyName) {
		modelDao.updateUserCompanyNameByUserId(userId, companyName);
	}

	
	public Amount getDailyParkingRateByParkingLot(ParkingLot parkingLot) {
		return modelDao.getDailyParkingRateByParkingLot(parkingLot);
	}

	
	public Amount getTaxRateByParkingLot(ParkingLot parkingLot) {
		return modelDao.getTaxRateByParkingLot(parkingLot);
	}

	
	public Integer getNumberDaysReserved(Date beginDate, Date endDate) {
		int days = Days.daysBetween(new DateTime(beginDate),
				new DateTime(endDate)).getDays();
		return days;
	}

	
	public ParkingLot getParkingLotBySlotNumber(String parkingSlotNumber) {
		ParkingSlot parkingSlot = modelDao
				.getParkingSlotBySlotNumber(parkingSlotNumber);

		ParkingLot parkingLot = modelDao
				.getParkingLotByParkingSlot(parkingSlot);

		return parkingLot;
	}

	
	public List<Reservation> getReservationsByDateRange(Date fromDate,
			Date toDate) {
		List<Reservation> reservations = modelDao.getReservationsByDateRange(
				fromDate, toDate);
		return reservations;
	}

	@Transactional
	public void addNewReservation(NewReservationForm newReservationForm)
			throws ParseException {
		Reservation reservation = new Reservation();

		// incoming date string looks like 'Tue Jul 24 2012 00:00:00 GMT-0600
		// (MDT)'
		DateFormat formatter = new SimpleDateFormat(
				"EEE MMM dd yyyy hh:mm:ss 'GMT'Z (z)");

		reservation.setBeginDate(formatter.parse(newReservationForm
				.getFromDate()));
		reservation.setEndDate(formatter.parse(newReservationForm.getToDate()));

		Vehicle vehicle = getVehicleByVehicleId(newReservationForm
				.getVehicleId());
		reservation.setVehicle(vehicle);

		ParkingSlot slot = modelDao
				.getParkingSlotBySlotNumber(newReservationForm.getSlotNumber());
		reservation.setParkingSlot(slot);

		modelDao.addReservation(reservation);
	}

	
	@Transactional
	public void deleteReservationByDatesAndSlotNumber(String beginDate,
			String endDate, String slotNumber) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Reservation reservation = modelDao
				.getReservationByDateRangeAndSlotNumber(
						formatter.parse(beginDate), formatter.parse(endDate),
						slotNumber);
		modelDao.deleteReservation(reservation);
	}

	
	public List<State> getStates() {
		List<State> states = modelDao.getStates();

		return states;
	}

	
	public String[][] getAllUsersArray() {
		List<User> users = modelDao.getUsers();
		String[][] userArrays = new String[users.size()][];

		for (int i = 0; i < users.size(); i++) {
			// We're going to load each column (except password_MD5) of the User
			// table so size the array accordingly
			String[] userArray = new String[15];

			User user = users.get(i);
			userArray[0] = user.getUserId().toString();
			userArray[1] = user.getUserName();
			userArray[2] = user.getCompanyName();
			userArray[3] = user.getFirstName();
			userArray[4] = user.getLastName();
			userArray[5] = user.getStreet();
			userArray[6] = user.getCity();
			userArray[7] = user.getState().getCode();
			userArray[8] = user.getZipCode();
			userArray[9] = user.getPrimaryEmail() != null ? user
					.getPrimaryEmail().getEmailAddress() : "";
			userArray[10] = user.getSecondaryEmail() != null ? user
					.getSecondaryEmail().getEmailAddress() : "";
			userArray[11] = user.getPrimaryPhone() != null ? user
					.getPrimaryPhone().getPhoneNumber() : "";
			userArray[12] = user.getSecondaryPhone() != null ? user
					.getSecondaryPhone().getPhoneNumber() : "";

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateCreated = formatter.format(user.getDateCreated());
			userArray[13] = dateCreated;

			userArray[14] = user.getUserType().getDescription();

			userArrays[i] = userArray;
		}

		return userArrays;
	}

	
	public JsonUserReservations getAllReservationsAsJsonObject() {
		JsonUserReservations userReservations = new JsonUserReservations();

		List<Reservation> reservations = modelDao
				.getReservationsByUser(getCurrentSessionUser());

		List<Map<String, String>> reservationMaps = new ArrayList<>();

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		for (Reservation reservation : reservations) {
			Map<String, String> reservationMap = new HashMap<>();

			String beginDate = formatter.format(reservation.getBeginDate());
			String endDate = formatter.format(reservation.getEndDate());
			reservationMap.put("beginDate", beginDate);
			reservationMap.put("endDate", endDate);
			reservationMap.put("parkingSlot", reservation.getParkingSlot()
					.getNumber());

			String vehicleMake = reservation.getVehicle().getMake();
			String vehicleModel = reservation.getVehicle().getModel();
			String vehiclePlate = reservation.getVehicle().getPlateNumber();

			reservationMap.put("vehicle", vehicleMake + " " + vehicleModel
					+ " (" + vehiclePlate + ")");

			reservationMaps.add(reservationMap);
		}

		userReservations.setsEcho("1");
		userReservations.setiTotalDisplayRecords(String.valueOf(reservations
				.size()));
		userReservations.setiTotalRecords(String.valueOf(reservations.size()));
		userReservations.setaaData(reservationMaps);

		return userReservations;
	}

	
	public JsonUserVehicles getAllVehiclesAsJsonObject() {
		JsonUserVehicles userVehicles = new JsonUserVehicles();
		User user = getCurrentSessionUser();
		List<Vehicle> vehicles = modelDao.getVehiclesByUser(user);

		List<Map<String, String>> vehicleMaps = new ArrayList<>();

		for (Vehicle vehicle : vehicles) {
			Map<String, String> vehicleMap = new HashMap<>();

			vehicleMap.put("make", vehicle.getMake());
			vehicleMap.put("model", vehicle.getModel());
			vehicleMap.put("color", vehicle.getColor());
			vehicleMap.put("plateNumber", vehicle.getPlateNumber());

			vehicleMaps.add(vehicleMap);
		}

		userVehicles.setsEcho("1");
		userVehicles.setiTotalDisplayRecords(String.valueOf(vehicles.size()));
		userVehicles.setiTotalRecords(String.valueOf(vehicles.size()));
		userVehicles.setaaData(vehicleMaps);

		return userVehicles;
	}

	
	public User getUserByUserId(Integer userId) {
		User user = modelDao.getUserByUserId(userId);
		return user;
	}

	
	public Map<Integer, String> getJsonUserTypesMap() {
		List<UserType> userTypes = modelDao.getUserTypes();

		Map<Integer, String> userTypesMap = new HashMap<Integer, String>();
		for (UserType userType : userTypes) {
			userTypesMap.put(userType.getUserTypeId(),
					userType.getDescription());
		}

		return userTypesMap;
	}

	
	public UserType getUserTypeById(Integer userTypeId) {
		UserType userType = modelDao.getUserTypeById(userTypeId);
		return userType;
	}

	
	public UserType getUserTypeByDescription(String description) {
		UserType userType = modelDao.getUserTypeByDescription(description);
		return userType;
	}

	
	public List<Vehicle> getVehiclesByUser(User user) {
		List<Vehicle> vehicles = modelDao.getVehiclesByUser(user);
		return vehicles;
	}

	public Boolean vehicleExistsByTag(String plateNumber) {
		boolean vehicleExists = false;
		Vehicle vehicle = modelDao.getVehicleByTag(plateNumber);
		if (vehicle != null) {
			vehicleExists = true;
		}
		return vehicleExists;
	}

	public Vehicle getVehicleByVehicleId(Integer vehicleId) {
		Vehicle vehicle = modelDao.getVehicleByVehicleId(vehicleId);
		return vehicle;
	}

	
	@Transactional
	public void deleteVehicleByMakeModelPlate(String plateNumber) {
		Vehicle vehicle = modelDao.getVehicleByTag(plateNumber);
		
		// Because there is a foreign key from reservation(s) to vehicle, we have to first
		// delete any reservations for this vehicle to avoid violating the foreign key constraint
		List<Reservation> reservations = modelDao.getReservationsByVehicleId(vehicle);
		if(reservations.size() > 0)
		{
			modelDao.deleteReservations(reservations);
		}
		
		modelDao.deleteVehicle(vehicle);
	}

	@Transactional
	public void addNewVehicle(NewVehicleForm newVehicleForm) {
		// get the current user
		User user = getCurrentSessionUser();

		Vehicle newVehicle = new Vehicle();
		newVehicle.setColor(newVehicleForm.getColor());
		newVehicle.setMake(newVehicleForm.getMake());
		newVehicle.setModel(newVehicleForm.getModel());
		newVehicle.setPlateNumber(newVehicleForm.getPlateNumber());
		newVehicle.setUser(user);

		modelDao.addVehicle(newVehicle);
	}

	public JsonLanguages getLanguagesAsJsonObject() {
		List<Language> languages = modelDao.getLanguages();
		return new JsonLanguages(languages);
	}
}
