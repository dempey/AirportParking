package com.stgutah.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATE")
public class State
{
	@Id
	@Column(name = "STATE_ID")
	@GeneratedValue
	private Integer stateId;
	
	@Column(name = "CODE", nullable = false)
	private String code;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	public Integer getStateId()
	{
		return stateId;
	}
	
	public void setStateId(Integer stateId)
	{
		this.stateId = stateId;
	}
	
	public String getCode()
	{
		return code;
	}
	
	public void setCode(String code)
	{
		this.code = code;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}
