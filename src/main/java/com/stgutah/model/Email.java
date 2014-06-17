package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMAIL")
public class Email
{
	@Id
	@Column(name = "EMAIL_ID")
	@GeneratedValue
	private Integer emailId;
	
	@Column(name = "EMAIL_ADDRESS", nullable = false)
	private String emailAddress;
	
	@ManyToOne
	@JoinColumn(name = "EMAIL_TYPE_ID", nullable = false, referencedColumnName="EMAIL_TYPE_ID")
	private EmailType emailType;
	
	public Email()
	{
		super();
	}
	
	public Email(String emailAddress, EmailType emailType)
	{
		super();
		this.emailAddress = emailAddress;
		this.emailType = emailType;
	}

	public Integer getEmailId()
	{
		return emailId;
	}

	public void setEmailId(Integer emailId)
	{
		this.emailId = emailId;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public EmailType getEmailType()
	{
		return emailType;
	}

	public void setEmailType(EmailType emailType)
	{
		this.emailType = emailType;
	}
}
