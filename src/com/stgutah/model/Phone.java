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
@Table(name = "PHONE")
public class Phone
{
	@Id
	@Column(name = "PHONE_ID")
	@GeneratedValue
	private Integer phoneId;
	
	@Column(name = "PHONE_NUMBER", nullable = false)
	private String phoneNumber;
	
	@ManyToOne
	@JoinColumn(name = "PHONE_TYPE_ID", nullable = false)
	@ForeignKey(name = "PHONE_TYPE_FK")
	private PhoneType phoneType;
	
	@Column(name = "ACCEPTS_TEXT")
	private Boolean acceptsText;

	public Phone()
	{
		super();
	}

	public Phone(String phoneNumber, PhoneType phoneType, Boolean acceptsText)
	{
		super();
		this.phoneNumber = phoneNumber;
		this.phoneType = phoneType;
		this.acceptsText = acceptsText;
	}

	public Integer getPhoneId()
	{
		return phoneId;
	}

	public void setPhoneId(Integer phoneId)
	{
		this.phoneId = phoneId;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public PhoneType getPhoneType()
	{
		return phoneType;
	}

	public void setPhoneType(PhoneType phoneType)
	{
		this.phoneType = phoneType;
	}

	public Boolean getAcceptsText()
	{
		return acceptsText;
	}

	public void setAcceptsText(Boolean acceptsText)
	{
		this.acceptsText = acceptsText;
	}
}
