package com.mitrais.rms.model;

public class Skill 
{
	private int skillId; 	
	private String skillName; 
	private String skillDesc; 
	private int skillStatus;
	
	public Skill(int skillId, String skillName, String skillDesc, int skillStatus) 
	{
		super();
		this.setSkillId(skillId);
		this.setSkillName(skillName);
		this.setSkillDesc(skillDesc);
		this.setSkillStatus(skillStatus);
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

	public String getSkillDesc() 
	{
		return skillDesc;
	}

	public void setSkillDesc(String skillDesc) 
	{
		this.skillDesc = skillDesc;
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
