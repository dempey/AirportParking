package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PHONE_TYPE")
public class PhoneType
{
	@Id
	@Column(name = "PHONE_TYPE_ID")
	@GeneratedValue
	private Integer phoneTypeId;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public PhoneType()
	{
		super();
	}

	public PhoneType(String description)
	{
		super();
		this.description = description;
	}

	public Integer getPhoneTypeId()
	{
		return phoneTypeId;
	}

	public void setPhoneTypeId(Integer phoneTypeId)
	{
		this.phoneTypeId = phoneTypeId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
