package com.mitrais.rms.model;

public class ListSkill 
{
	private int skillId;
	private String skillName;
	
	public ListSkill(int skillId, String skillName) 
	{
		super();
		this.setSkillId(skillId);
		this.setSkillName(skillName);
	}

	public String getSkillName() 
	{
		return skillName;
	}

	public void setSkillName(String skillName) 
	{
		this.skillName = skillName;
	}

	public int getSkillId() 
	{
		return skillId;
	}

	public void setSkillId(int skillId)
	{
		this.skillId = skillId;
	}
}
