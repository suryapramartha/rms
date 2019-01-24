package com.mitrais.rms.model;

import java.util.Date;

public class Project 
{
	private int projectId;
	private String projectName;
	private Date projectStartDate;
	private Date projectEndDate;
	private int projectStatus;
	private String projectRequirement;
	private int emplId;
	private String emplName;
	
	public Project(int projectId, String projectName, Date projectStartDate, Date projectEndDate, int projectStatus,
			String projectRequirement, int emplId, String emplName) 
	{
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.projectStatus = projectStatus;
		this.projectRequirement = projectRequirement;
		this.emplId = emplId;
		this.emplName = emplName;
	}

	public int getProjectId()
	{
		return projectId;
	}

	public void setProjectId(int projectId) 
	{
		this.projectId = projectId;
	}

	public String getProjectName() 
	{
		return projectName;
	}

	public void setProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	public Date getProjectStartDate()
	{
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectEndDate()
	{
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate)
	{
		this.projectEndDate = projectEndDate;
	}

	public int getProjectStatus()
	{
		return projectStatus;
	}

	public void setProjectStatus(int projectStatus)
	{
		this.projectStatus = projectStatus;
	}

	public String getProjectRequirement()
	{
		return projectRequirement;
	}

	public void setProjectRequirement(String projectRequirement)
	{
		this.projectRequirement = projectRequirement;
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
}
