package com.stgutah.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.stgutah.model.Amount;
import com.stgutah.model.Email;
import com.stgutah.model.EmailType;
import com.stgutah.model.Language;
import com.stgutah.model.ParkingLot;
import com.stgutah.model.ParkingSlot;
import com.stgutah.model.Phone;
import com.stgutah.model.PhoneType;
import com.stgutah.model.Reservation;
import com.stgutah.model.State;
import com.stgutah.model.User;
import com.stgutah.model.UserType;
import com.stgutah.model.Vehicle;

@Configuration
public class ModelDaoImpl
{
	@Autowired
	private SessionFactory sessionFactory;

	public void addNewUser(User newUser)
	{
		Session session = sessionFactory.openSession();
		session.save(newUser);
		session.flush();
	}

	public User getUserByUsername(String username)
	{
		Session session = sessionFactory.openSession(); //.getCurrentSession();

		String queryString = "from User u where u.userName = :username";
		Query queryObject = session.createQuery(queryString);
		queryObject.setString("username", username);
		queryObject.setCacheable(true);
		User user = (User)queryObject.uniqueResult();

		// Alternative syntax: User user = (User)session.createQuery(query).setString("username", username).setCacheable(true).uniqueResult();

		return user;
	}

	public void updateUserTypeByUserId(Integer userId, Integer userTypeId)
	{
		Session session = sessionFactory.openSession();
		//Use SQL update statement because can't get update to re-point to new UserType object.
		String queryString = "update USER u set u.USER_TYPE_ID = :userTypeId where u.USER_ID = :userId";
		Query query = session.createSQLQuery(queryString);
		query.setInteger("userTypeId", userTypeId);
		query.setInteger("userId", userId);
		query.executeUpdate();

		session.flush();
	}

	public void updateUserCompanyNameByUserId(Integer userId, String companyName)
	{
		Session session = sessionFactory.openSession();
		User user = getUserByUserId(userId);
		user.setCompanyName(companyName);
		session.update(user);
		session.flush();
	}

	public Amount getDailyParkingRateByParkingLot(ParkingLot parkingLot)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from Amount a " +
				"where a.parkingLot.parkingLotId = :parkingLotId " +
				"and a.amountType.description = :amountTypeDescription";

		Query queryObject = session.createQuery(queryString);
		queryObject.setInteger("parkingLotId", parkingLot.getParkingLotId());
		queryObject.setString("amountTypeDescription", "DAILY_RATE");
		queryObject.setCacheable(true);
		Amount amount = (Amount)queryObject.uniqueResult();

		return amount;
	}

	public Amount getTaxRateByParkingLot(ParkingLot parkingLot)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from Amount a " +
				"where a.parkingLot.parkingLotId = :parkingLotId " +
				"and a.amountType.description = :amountTypeDescription";

		Query queryObject = session.createQuery(queryString);
		queryObject.setInteger("parkingLotId", parkingLot.getParkingLotId());
		queryObject.setString("amountTypeDescription", "TAX_RATE");
		queryObject.setCacheable(true);
		Amount amount = (Amount)queryObject.uniqueResult();

		return amount;
	}

	public void addEmail(Email email)
	{
		Session session = sessionFactory.openSession();
		session.save(email);
	}

	public EmailType getEmailTypeByDescription(String description)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from EmailType t where t.description = :description";
		Query queryObject = session.createQuery(queryString);
		queryObject.setString("description", description);
		queryObject.setCacheable(true);
		EmailType emailType = (EmailType)queryObject.uniqueResult();

		return emailType;
	}

	public ParkingLot getParkingLotByParkingSlot(ParkingSlot parkingSlot)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from ParkingLot l where l.parkingLotId = :parkingLotId";
		Query queryObject = session.createQuery(queryString);
		queryObject.setInteger("parkingLotId", parkingSlot.getParkingLot().getParkingLotId());
		queryObject.setCacheable(true);
		ParkingLot parkingLot = (ParkingLot)queryObject.uniqueResult();

		return parkingLot;
	}

	public ParkingSlot getParkingSlotBySlotNumber(String parkingSlotNumber)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from ParkingSlot s where s.number = :slotNumber";
		Query queryObject = session.createQuery(queryString);
		queryObject.setString("slotNumber", parkingSlotNumber);
		queryObject.setCacheable(true);
		ParkingSlot parkingSlot = (ParkingSlot)queryObject.uniqueResult();

		return parkingSlot;
	}

	public void addPhone(Phone phone)
	{
		Session session = sessionFactory.openSession();
		session.save(phone);
	}

	public PhoneType getPhoneTypeByDescription(String description)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from PhoneType t where t.description = :description";
		Query queryObject = session.createQuery(queryString);
		queryObject.setString("description", description);
		queryObject.setCacheable(true);
		PhoneType phoneType = (PhoneType)queryObject.uniqueResult();

		return phoneType;
	}

	public Reservation getReservationByDateRangeAndSlotNumber(Date beginDate, Date endDate, String slotNumber)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from Reservation r where r.beginDate = :beginDate and r.endDate = :endDate and r.parkingSlot.number = :parkingSlot";
		Query queryObject = session.createQuery(queryString);
		queryObject.setDate("beginDate", beginDate);
		queryObject.setDate("endDate", endDate);
		queryObject.setString("parkingSlot", slotNumber);
		queryObject.setCacheable(false);
		Reservation reservation = (Reservation)queryObject.uniqueResult();

		return reservation;
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationsByUser(User user)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from Reservation r where r.vehicle.user.userId = :userId";
		Query queryObject = session.createQuery(queryString);
		queryObject.setInteger("userId", user.getUserId());
		queryObject.setCacheable(true);
		List<Reservation> reservations = (List<Reservation>)queryObject.list();

		return reservations;
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getReservationsByDateRange(Date fromDate, Date toDate)
	{
		Session session = sessionFactory.openSession();

		String queryString =
				"from Reservation r " +
				"where " +
				"r.beginDate between :fromDate and :toDate " +
				"AND " +
				"r.endDate between :fromDate and :toDate";

		Query queryObject = session.createQuery(queryString);
		queryObject.setDate("fromDate", fromDate);
		queryObject.setDate("toDate", toDate);
		queryObject.setCacheable(true);
		List<Reservation> reservations = (List<Reservation>)queryObject.list();

		return reservations;
	}

	public void addReservation(Reservation reservation)
	{
		Session session = sessionFactory.openSession();
		session.save(reservation);
	}

	public void deleteReservation(Reservation reservation)
	{
		Session session = sessionFactory.openSession();
		session.delete(reservation);
		session.flush();
	}

	@SuppressWarnings("unchecked")
	public List<State> getStates()
	{
		Session session = sessionFactory.openSession();

		String queryString = "from State";
		Query queryObject = session.createQuery(queryString);
		queryObject.setCacheable(true);
		List<State> states = (List<State>)queryObject.list();

		return states;
	}

	public State getStateByCode(String stateCode)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from State s where s.code = :stateCode";
		Query queryObject = session.createQuery(queryString);
		queryObject.setString("stateCode", stateCode);
		queryObject.setCacheable(true);
		State state = (State)queryObject.uniqueResult();

		return state;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers()
	{
		Session session = sessionFactory.openSession();

		String queryString = "from User";
		Query queryObject = session.createQuery(queryString);
		queryObject.setCacheable(true);
		List<User> users = (List<User>)queryObject.list();

		return users;
	}

	public User getUserByUserId(Integer userId)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from User u where u.userId = :userId";
		Query queryObject = session.createQuery(queryString);
		queryObject.setInteger("userId", userId);
		queryObject.setCacheable(false);
		User user = (User)queryObject.uniqueResult();

		return user;
	}

	@SuppressWarnings("unchecked")
	public List<UserType> getUserTypes()
	{
		Session session = sessionFactory.openSession();

		String queryString = "from UserType";
		Query queryObject = session.createQuery(queryString);
		queryObject.setCacheable(true);
		List<UserType> userTypes = (List<UserType>)queryObject.list();

		return userTypes;
	}

	public UserType getUserTypeById(Integer userTypeId)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from UserType t where t.userTypeId = :userTypeId";
		Query queryObject = session.createQuery(queryString);
		queryObject.setInteger("userTypeId", userTypeId);
		queryObject.setCacheable(true);
		UserType userType = (UserType)queryObject.uniqueResult();

		return userType;
	}

	public UserType getUserTypeByDescription(String description)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from UserType t where t.description = :description";
		Query queryObject = session.createQuery(queryString);
		queryObject.setString("description", description);
		queryObject.setCacheable(true);
		UserType userType = (UserType)queryObject.uniqueResult();

		return userType;
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> getVehiclesByUser(User user)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from Vehicle v where v.user.userId = :userId";
		Query queryObject = session.createQuery(queryString);
		queryObject.setInteger("userId", user.getUserId());
		queryObject.setCacheable(true);
		List<Vehicle> vehicles = (List<Vehicle>)queryObject.list();

		return vehicles;
	}

	public void deleteVehicle(Vehicle vehicle)
	{
		Session session = sessionFactory.openSession();
		session.delete(vehicle);
		session.flush();
	}

	public Vehicle getVehicleByTag(String plateNumber)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from Vehicle v where v.plateNumber = :plateNumber";
		Query queryObject = session.createQuery(queryString);
		queryObject.setString("plateNumber", plateNumber);
		queryObject.setCacheable(true);
		Vehicle vehicle = (Vehicle)queryObject.uniqueResult();

		return vehicle;
	}

	public Vehicle getVehicleByVehicleId(Integer vehicleId)
	{
		Session session = sessionFactory.openSession();

		String queryString = "from Vehicle v where v.vehicleId = :vehicleId";
		Query queryObject = session.createQuery(queryString);
		queryObject.setInteger("vehicleId", vehicleId);
		queryObject.setCacheable(true);
		Vehicle vehicle = (Vehicle)queryObject.uniqueResult();

		return vehicle;
	}

	public void addVehicle(Vehicle newVehicle)
	{
		Session session = sessionFactory.openSession();
		session.save(newVehicle);
	}

	@SuppressWarnings("unchecked")
	public List<Language> getLanguages()
	{
		Session session = sessionFactory.openSession();

		String queryString = "from Language";
		Query queryObject = session.createQuery(queryString);
		queryObject.setCacheable(true);
		List<Language> languages = (List<Language>)queryObject.list();

		return languages;
	}
}