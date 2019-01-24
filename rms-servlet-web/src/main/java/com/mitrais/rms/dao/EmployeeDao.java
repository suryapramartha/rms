package com.mitrais.rms.dao;

import java.util.List;
import com.mitrais.rms.model.Employee;
import com.mitrais.rms.model.EmployeeCompetency;
import com.mitrais.rms.model.ListSkill;


public interface EmployeeDao extends Dao<Employee, Integer> 
{
	boolean delete(String id);
	boolean deleteCompetency(String emplId,String skillId);
	boolean insertAssignedSkill(EmployeeCompetency sk); 
	String getSkillNames(String skill_id);
	
	List<String> getSpecificEmployee(String id);
	List<String> getEmployee(String emplId);
	List<EmployeeCompetency> getAllAssignedSkill(String emplId);
	List<ListSkill> getListSkillNames(String emplId);
}
