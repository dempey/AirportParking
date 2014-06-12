package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "PARKING_LOT")
public class ParkingLot
{
	@Id
	@Column(name = "PARKING_LOT_ID")
	@GeneratedValue
	private Integer parkingLotId;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "STREET", nullable = false)
	private String street;
	
	@Column(name = "CITY", nullable = false)
	private String city;
	
	@ManyToOne
	@JoinColumn(name = "STATE_ID", insertable = true, updatable = true, nullable = false, referencedColumnName = "STATE_ID")
	@ForeignKey(name = "PARKING_LOT_STATE_FK")
	private State state; //FK reference to STATE table
	
	@Column(name = "ZIP_CODE")
	private String zipCode;
	
	@ManyToOne
	@JoinColumn(name = "PRIMARY_PHONE_ID", insertable = true, updatable = true, referencedColumnName = "PHONE_ID")
	@ForeignKey(name = "PARKING_LOT_PRIMARY_PHONE_FK")
	private Phone primaryPhone; //FK reference to PHONE table
	
	@ManyToOne
	@JoinColumn(name = "SECONDARY_PHONE_ID", insertable = true, updatable = true, referencedColumnName = "PHONE_ID")
	@ForeignKey(name = "PARKING_LOT_SECONDARY_PHONE_FK")
	private Phone secondaryPhone; //FK reference to PHONE table
	
	@ManyToOne
	@JoinColumn(name = "EMAIL_ID")
	@ForeignKey(name = "PARKING_LOT_EMAIL_FK")
	private Email email; //FK reference to EMAIL table

	public Integer getParkingLotId()
	{
		return parkingLotId;
	}

	public void setParkingLotId(Integer parkingLotId)
	{
		this.parkingLotId = parkingLotId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public State getState()
	{
		return state;
	}

	public void setStateId(State state)
	{
		this.state = state;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public Phone getPrimaryPhone()
	{
		return primaryPhone;
	}

	public void setPrimaryPhone(Phone primaryPhone)
	{
		this.primaryPhone = primaryPhone;
	}

	public Phone getSecondaryPhone()
	{
		return secondaryPhone;
	}

	public void setSecondaryPhone(Phone secondaryPhone)
	{
		this.secondaryPhone = secondaryPhone;
	}

	public Email getEmail()
	{
		return email;
	}

	public void setEmail(Email email)
	{
		this.email = email;
	}
}
