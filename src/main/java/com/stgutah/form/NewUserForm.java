package com.stgutah.form;

public class NewUserForm
{
	private String companyName;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	
//	@NotEmpty
//	@Size(min=8, max=32, message="username must be between 8 and 32 characters")
	private String username;
	
//	@NotEmpty
//	@Size(min=8, max=32, message="password must be between 8 and 32 characters")
	private String password;
	
	private Integer userTypeId;
	private String primaryPhone;
	private String primaryPhoneType;
	private boolean primaryPhoneAcceptsText;
	private String secondaryPhone;
	private String secondaryPhoneType;
	private boolean secondaryPhoneAcceptsText;
	private String primaryEmail;
	private String primaryEmailType;
	private String secondaryEmail;
	private String secondaryEmailType;
	
	public NewUserForm()
	{
		super();
	}

	public NewUserForm(String companyName, String firstName, String lastName, String street, String city, String state,
			String zipCode, String username, String password, Integer userTypeId, String primaryPhone,
			String primaryPhoneType, boolean primaryPhoneAcceptsText, String secondaryPhone, String secondaryPhoneType,
			boolean secondaryPhoneAcceptsText, String primaryEmail, String primaryEmailType, String secondaryEmail,
			String secondaryEmailType)
	{
		super();
		this.companyName = companyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.username = username;
		this.password = password;
		this.userTypeId = userTypeId;
		this.primaryPhone = primaryPhone;
		this.primaryPhoneType = primaryPhoneType;
		this.primaryPhoneAcceptsText = primaryPhoneAcceptsText;
		this.secondaryPhone = secondaryPhone;
		this.secondaryPhoneType = secondaryPhoneType;
		this.secondaryPhoneAcceptsText = secondaryPhoneAcceptsText;
		this.primaryEmail = primaryEmail;
		this.primaryEmailType = primaryEmailType;
		this.secondaryEmail = secondaryEmail;
		this.secondaryEmailType = secondaryEmailType;
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

	public String getState()
	{
		return state;
	}

	public void setState(String state)
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

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Integer getUserTypeId()
	{
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId)
	{
		this.userTypeId = userTypeId;
	}

	public String getPrimaryPhone()
	{
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone)
	{
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone()
	{
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone)
	{
		this.secondaryPhone = secondaryPhone;
	}

	public String getPrimaryEmail()
	{
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail)
	{
		this.primaryEmail = primaryEmail;
	}

	public String getSecondaryEmail()
	{
		return secondaryEmail;
	}

	public void setSecondaryEmail(String secondaryEmail)
	{
		this.secondaryEmail = secondaryEmail;
	}

	public String getPrimaryPhoneType()
	{
		return primaryPhoneType;
	}

	public void setPrimaryPhoneType(String primaryPhoneType)
	{
		this.primaryPhoneType = primaryPhoneType;
	}

	public String getSecondaryPhoneType()
	{
		return secondaryPhoneType;
	}

	public void setSecondaryPhoneType(String secondaryPhoneType)
	{
		this.secondaryPhoneType = secondaryPhoneType;
	}

	public boolean isPrimaryPhoneAcceptsText()
	{
		return primaryPhoneAcceptsText;
	}

	public void setPrimaryPhoneAcceptsText(boolean primaryPhoneAcceptsText)
	{
		this.primaryPhoneAcceptsText = primaryPhoneAcceptsText;
	}

	public boolean isSecondaryPhoneAcceptsText()
	{
		return secondaryPhoneAcceptsText;
	}

	public void setSecondaryPhoneAcceptsText(boolean secondaryPhoneAcceptsText)
	{
		this.secondaryPhoneAcceptsText = secondaryPhoneAcceptsText;
	}

	public String getPrimaryEmailType()
	{
		return primaryEmailType;
	}

	public void setPrimaryEmailType(String primaryEmailType)
	{
		this.primaryEmailType = primaryEmailType;
	}

	public String getSecondaryEmailType()
	{
		return secondaryEmailType;
	}

	public void setSecondaryEmailType(String secondaryEmailType)
	{
		this.secondaryEmailType = secondaryEmailType;
	}
}