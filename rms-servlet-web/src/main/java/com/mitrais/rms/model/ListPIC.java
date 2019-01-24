package com.mitrais.rms.model;

public class ListPIC {
	private String emplId;
	private String emplName;
	
	public ListPIC(String emplId, String emplName) 
	{
		super();
		this.setEmplId(emplId);
		this.setEmplName(emplName);
	}

	public String getEmplName()
	{
		return emplName;
	}

	public void setEmplName(String emplName) 
	{
		this.emplName = emplName;
	}

	public String getEmplId()
	{
		return emplId;
	}

	public void setEmplId(String emplId)
	{
		this.emplId = emplId;
	}
}
