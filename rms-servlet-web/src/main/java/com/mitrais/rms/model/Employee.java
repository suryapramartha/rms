package com.mitrais.rms.model;

public class Employee 
{
	private int emplId;
	private String emplName;
	private int emplStatus;
	private int emplAge;
	private String emplAddress;
	
	public Employee(int emplId, String emplName, int emplStatus, int emplAge, String emplAddress) 
	{
		super();
		this.emplId = emplId;
		this.emplName = emplName;
		this.emplStatus = emplStatus;
		this.emplAge = emplAge;
		this.emplAddress = emplAddress;
	}

	public int getEmplId() 
	{
		return emplId;
	}

	public void setEmplId(int emplId)
	{
		this.emplId = emplId;
	}

	public String getEmplName() {
		return emplName;
	}

	public void setEmplName(String emplName) 
	{
		this.emplName = emplName;
	}

	public int getEmplStatus() 
	{
		return emplStatus;
	}

	public void setEmplStatus(int emplStatus) 
	{
		this.emplStatus = emplStatus;
	}

	public int getEmplAge()
	{
		return emplAge;
	}

	public void setEmplAge(int emplAge)
	{
		this.emplAge = emplAge;
	}

	public String getEmplAddress()
	{
		return emplAddress;
	}

	public void setEmplAddress(String emplAddress)
	{
		this.emplAddress = emplAddress;
	}
}
