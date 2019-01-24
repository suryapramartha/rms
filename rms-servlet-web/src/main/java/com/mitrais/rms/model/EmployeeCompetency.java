package com.mitrais.rms.model;

public class EmployeeCompetency
{
	private int emplId ;	
	private String emplName; 	
	private int skillId ;	
	private String skillName ;
	private int skillStatus;
	
	public EmployeeCompetency(int emplId, String emplName, int skillId, String skillName, int skillStatus)
	{
		super();
		this.emplId = emplId;
		this.emplName = emplName;
		this.skillId = skillId;
		this.skillName = skillName;
		this.skillStatus = skillStatus;
	}

	public int getEmplId()
	{
		return emplId;
	}

	public void setEmplId(int emplId) 
	{
		this.emplId = emplId;
	}

	public String getEmplName()
	{
		return emplName;
	}

	public void setEmplName(String emplName) 
	{
		this.emplName = emplName;
	}

	public int getSkillId()
	{
		return skillId;
	}

	public void setSkillId(int skillId) 
	{
		this.skillId = skillId;
	}

	public String getSkillName()
	{
		return skillName;
	}

	public void setSkillName(String skillName)
	{
		this.skillName = skillName;
	}

	public int getSkillStatus()
	{
		return skillStatus;
	}

	public void setSkillStatus(int skillStatus) 
	{
		this.skillStatus = skillStatus;
	}
}
