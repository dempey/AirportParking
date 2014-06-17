package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AMOUNT_TYPE")
public class AmountType
{
	@Id
	@Column(name = "AMOUNT_TYPE_ID")
	@GeneratedValue
	private Integer amountTypeId;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public Integer getAmountTypeId()
	{
		return amountTypeId;
	}

	public void setAmountTypeId(Integer amountTypeId)
	{
		this.amountTypeId = amountTypeId;
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
