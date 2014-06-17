package com.stgutah.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name = "USER")
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
public class User
{
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue
	private Integer userId;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "STREET")
	private String street;
	
	@Column(name = "CITY", nullable = false)
	private String city;
	
	@ManyToOne
	@JoinColumn(name = "STATE_ID",  insertable= true, updatable = false, nullable = false, referencedColumnName="STATE_ID")
	private State state;
	
	@Column(name = "ZIP_CODE")
	private String zipCode;
	
	@Column(name = "USER_NAME", nullable = false)
	private String userName;
	
	@Column(name = "PASSWORD_MD5", nullable = false, length = 32)
	private String passwordMd5;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "USER_TYPE_ID", insertable= true, updatable = false, nullable = false, referencedColumnName="USER_TYPE_ID")
	private UserType userType;
	
	@ManyToOne
	@JoinColumn(name = "PRIMARY_PHONE_ID", insertable= true, updatable = true, referencedColumnName = "PHONE_ID")
	private Phone primaryPhone;
	
	@ManyToOne
	@JoinColumn(name = "SECONDARY_PHONE_ID", insertable= true, updatable = true, referencedColumnName = "PHONE_ID")
	private Phone secondaryPhone;
	
	@ManyToOne
	@JoinColumn(name = "PRIMARY_EMAIL_ID", insertable= true, updatable = true, referencedColumnName = "EMAIL_ID")
	private Email primaryEmail;
	
	@ManyToOne
	@JoinColumn(name = "SECONDARY_EMAIL_ID", insertable= true, updatable = true, referencedColumnName = "EMAIL_ID")
	private Email secondaryEmail;
	
	@Column(name = "DATE_CREATED", nullable = false)
	private Date dateCreated;

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
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

	public void setState(State state)
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
	
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPasswordMd5()
	{
		return passwordMd5;
	}

	public void setPasswordMd5(String passwordMd5)
	{
		this.passwordMd5 = passwordMd5;
	}

	public UserType getUserType()
	{
		return userType;
	}

	public void setUserType(UserType userType)
	{
		this.userType = userType;
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

	public Email getPrimaryEmail()
	{
		return primaryEmail;
	}

	public void setPrimaryEmail(Email primaryEmail)
	{
		this.primaryEmail = primaryEmail;
	}

	public Email getSecondaryEmail()
	{
		return secondaryEmail;
	}

	public void setSecondaryEmail(Email secondaryEmail)
	{
		this.secondaryEmail = secondaryEmail;
	}

	public Date getDateCreated()
	{
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}
}
