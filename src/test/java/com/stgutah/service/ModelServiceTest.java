package com.stgutah.service;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.stgutah.dao.ModelDao;
import com.stgutah.form.NewUserForm;
import com.stgutah.model.EmailType;
import com.stgutah.model.EmailTypeEnum;
import com.stgutah.model.ParkingLot;
import com.stgutah.model.ParkingSlot;
import com.stgutah.model.PhoneType;
import com.stgutah.model.PhoneTypeEnum;
import com.stgutah.model.State;
import com.stgutah.model.User;
import com.stgutah.model.UserType;
import com.stgutah.model.UserTypeEnum;
import com.stgutah.util.SecurityMD5Util;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;

public class ModelServiceTest
{
	
	private static User testUser = mock(User.class);

	private static State testState = mock(State.class);
	
	private static EmailType testEmailType = mock(EmailType.class);
	
	private static PhoneType testPhoneType = mock(PhoneType.class);
	
	private static UserType testUserType = mock(UserType.class);
	
	private static ParkingLot testParkingLot = mock(ParkingLot.class);
	
	private static ParkingSlot testParkingSlot = mock(ParkingSlot.class);
	
	private ModelService modelService;
	
	@Mock
	private ModelDao modelDao;
	
	@Mock
	private SecurityMD5Util securityUtil;
	
	@BeforeTest
	public void setupBeforeEachTestCase()
	{
		MockitoAnnotations.initMocks(this);
		modelService = new ModelService();
		modelService.setModelDao(modelDao);
		modelService.setSecurityUtil(securityUtil);
	}
	
	@Test
	public void testGetUserByUsername()
	{
		stub(modelDao.getUserByUsername("bjohnson")).toReturn(testUser);
		final User resultUser = modelService.getUserByUsername("bjohnson");
		Assert.assertEquals(resultUser, testUser);
	}
	
	@Test
	public void testGetParkingLotBySlotNumber()
	{
		stub(modelDao.getParkingSlotBySlotNumber("C12")).toReturn(testParkingSlot);
		stub(modelDao.getParkingLotByParkingSlot(testParkingSlot)).toReturn(testParkingLot);
		final ParkingLot resultParkingLot = modelService.getParkingLotBySlotNumber("C12");
		Assert.assertEquals(resultParkingLot, testParkingLot);
	}
	
	@Test
	public void testAddNewUser()
	{
		stub(modelDao.getStateByCode("UT")).toReturn(testState);
		stub(modelDao.getEmailTypeByDescription(EmailTypeEnum.PERSONAL.toString())).toReturn(testEmailType);
		stub(modelDao.getPhoneTypeByDescription(PhoneTypeEnum.PERSONAL.toString())).toReturn(testPhoneType);
		stub(modelDao.getUserTypeByDescription(UserTypeEnum.CUSTOMER.toString())).toReturn(testUserType);
		stub(securityUtil.getMD5Value("gateshead")).toReturn("df2f12d1f3a4b3c467579e240107e83f");
		
		NewUserForm testUserForm = mock(NewUserForm.class);
		modelService.addNewUser(testUserForm);
	}
	
}
