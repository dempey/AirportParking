package com.stgutah.form;

public class NewReservationForm
{
	private Integer vehicleId;
	private String fromDate;
	private String toDate;
	private String slotNumber;

	public Integer getVehicleId()
	{
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId)
	{
		this.vehicleId = vehicleId;
	}

	public String getFromDate()
	{
		return fromDate;
	}

	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	public String getToDate()
	{
		return toDate;
	}

	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	public String getSlotNumber()
	{
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber)
	{
		this.slotNumber = slotNumber;
	}

}
