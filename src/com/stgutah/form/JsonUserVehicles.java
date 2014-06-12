package com.stgutah.form;

import java.util.List;
import java.util.Map;

public class JsonUserVehicles
{
	private String sEcho;
	private String iTotalRecords;
	private String iTotalDisplayRecords;
	private List<Map<String, String>> aaData;

	public String getsEcho()
	{
		return sEcho;
	}

	public void setsEcho(String sEcho)
	{
		this.sEcho = sEcho;
	}

	public String getiTotalRecords()
	{
		return iTotalRecords;
	}

	public void setiTotalRecords(String iTotalRecords)
	{
		this.iTotalRecords = iTotalRecords;
	}

	public String getiTotalDisplayRecords()
	{
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(String iTotalDisplayRecords)
	{
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public List<Map<String, String>> getaaData()
	{
		return aaData;
	}

	public void setaaData(List<Map<String, String>> aaData)
	{
		this.aaData = aaData;
	}
}
