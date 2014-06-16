package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE")
public class Vehicle
{
	@Id
	@Column(name = "VEHICLE_ID")
	@GeneratedValue
	private Integer vehicleId;
	
	@Column(name = "MAKE", nullable = false)
	private String make;
	
	@Column(name = "MODEL", nullable = false)
	private String model;
	
	@Column(name = "COLOR")
	private String color;
	
	@Column(name = "PLATE_NUMBER", nullable = false)
	private String plateNumber;
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, referencedColumnName="USER_ID")
	private User user;

	public Integer getVehicleId()
	{
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId)
	{
		this.vehicleId = vehicleId;
	}

	public String getMake()
	{
		return make;
	}

	public void setMake(String make)
	{
		this.make = make;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getPlateNumber()
	{
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber)
	{
		this.plateNumber = plateNumber;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
