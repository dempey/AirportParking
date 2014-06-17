package com.stgutah.service;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.stgutah.dao.ModelDao;
import com.stgutah.model.ParkingLot;
import com.stgutah.model.ParkingSlot;
import com.stgutah.model.User;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class ModelServiceTest
{
	
	private static User testUser;
	static {
		testUser = new User();
		testUser.setCity("Fruit Heights");
		testUser.setCompanyName("STG");
		testUser.setFirstName("Brian");
		testUser.setLastName("Johnson");
		testUser.setUserId(30);
		testUser.setUserName("bjohnson");
		testUser.setPasswordMd5("df2f12d1f3a4b3c467579e240107e83f");
	}

	private static ParkingLot testParkingLot;
	static {
		testParkingLot = new ParkingLot();
		testParkingLot.setCity("Salt Lake City");
		testParkingLot.setName("Park-n-Jet of Salt Lake City");
		testParkingLot.setParkingLotId(20);
		testParkingLot.setZipCode("84117");
	}
	
	private static ParkingSlot testParkingSlot;
	static {
		testParkingSlot = new ParkingSlot();
		testParkingSlot.setNumber("C12");
		testParkingSlot.setParkingLot(testParkingLot);
		testParkingSlot.setParkingSlotId(62);
	}
	
	private ModelService modelService;
	
	@Mock
	private ModelDao modelDao;
	
	@BeforeTest
	public void setupBeforeEachTestCase()
	{
		MockitoAnnotations.initMocks(this);
		modelService = new ModelService();
		modelService.setModelDao(modelDao);
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
	
}
