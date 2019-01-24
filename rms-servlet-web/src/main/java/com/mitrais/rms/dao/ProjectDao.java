package com.mitrais.rms.dao;

import java.util.List;
import com.mitrais.rms.model.ListPIC;
import com.mitrais.rms.model.ListSkill;
import com.mitrais.rms.model.Project;



public interface ProjectDao extends Dao<Project, Integer> 
{
	boolean delete(String id);
	boolean resetProjectPIC(int projectId);
	boolean updateProjectPIC(int projectId,String emplName);
	List<String> getSpecificProject(String id);
	List<ListSkill> getListSelectedSkillNames(String id);
	List<ListSkill> getListSkillNames();
	List<ListPIC> getRelatedPIC(String projectId,String emplName); 
}
