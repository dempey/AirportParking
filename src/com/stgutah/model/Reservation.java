package com.stgutah.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESERVATION")
public class Reservation
{
	@Id
	@Column(name = "RESERVATION_ID")
	@GeneratedValue
	private Integer reservationId;
	
	@Column(name = "BEGIN_DATE", nullable = false)
	private Date beginDate;
	
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;
	
	@ManyToOne
	@JoinColumn(name = "VEHICLE_ID", nullable = false, referencedColumnName="VEHICLE_ID")
	private Vehicle vehicle;
	
	@ManyToOne
	@JoinColumn(name = "PARKING_SLOT_ID", nullable = false, referencedColumnName="PARKING_SLOT_ID")
	private ParkingSlot parkingSlot;

	public Integer getReservationId()
	{
		return reservationId;
	}

	public void setReservationId(Integer reservationId)
	{
		this.reservationId = reservationId;
	}

	public Date getBeginDate()
	{
		return beginDate;
	}

	public void setBeginDate(Date beginDate)
	{
		this.beginDate = beginDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	public Vehicle getVehicle()
	{
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle)
	{
		this.vehicle = vehicle;
	}

	public ParkingSlot getParkingSlot()
	{
		return parkingSlot;
	}

	public void setParkingSlot(ParkingSlot parkingSlot)
	{
		this.parkingSlot = parkingSlot;
	}
}
