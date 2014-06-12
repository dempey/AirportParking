package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMAIL_TYPE")
public class EmailType
{
	@Id
	@Column(name = "EMAIL_TYPE_ID")
	@GeneratedValue
	private Integer emailTypeId;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	public EmailType()
	{
		super();
	}
	
	public EmailType(String description)
	{
		super();
		this.description = description;
	}

	public Integer getEmailTypeId()
	{
		return emailTypeId;
	}

	public void setEmailTypeId(Integer emailTypeId)
	{
		this.emailTypeId = emailTypeId;
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
