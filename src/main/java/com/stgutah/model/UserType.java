package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_TYPE")
public class UserType
{
	@Id
	@Column(name = "USER_TYPE_ID")
	@GeneratedValue
	private Integer userTypeId;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	public Integer getUserTypeId()
	{
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId)
	{
		this.userTypeId = userTypeId;
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
