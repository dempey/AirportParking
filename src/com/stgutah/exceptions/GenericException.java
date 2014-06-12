package com.stgutah.exceptions;

public class GenericException extends RuntimeException
{
	private static final long serialVersionUID = 298862748846717103L;
	
	private String customMessage;
	
	public String getCustomMessage()
	{
		return customMessage;
	}

	public void setCustomMessage(String customMessage)
	{
		this.customMessage = customMessage;
	}

	public GenericException(String customMessage)
	{
		this.customMessage = customMessage;
	}
}
