package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PARKING_SLOT")
public class ParkingSlot
{
	@Id
	@Column(name = "PARKING_SLOT_ID")
	@GeneratedValue
	private Integer parkingSlotId;
	
	@Column(name = "NUMBER", nullable = false)
	private String number;
	
	@ManyToOne
	@JoinColumn(name = "PARKING_LOT_ID", nullable = false, referencedColumnName="PARKING_LOT_ID")
	private ParkingLot parkingLot;

	public Integer getParkingSlotId()
	{
		return parkingSlotId;
	}

	public void setParkingSlotId(Integer parkingSlotId)
	{
		this.parkingSlotId = parkingSlotId;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public ParkingLot getParkingLot()
	{
		return parkingLot;
	}

	public void setParkingLot(ParkingLot parkingLot)
	{
		this.parkingLot = parkingLot;
	}
}
