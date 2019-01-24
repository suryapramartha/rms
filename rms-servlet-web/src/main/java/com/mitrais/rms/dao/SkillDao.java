package com.mitrais.rms.dao;

import java.util.List;

import com.mitrais.rms.model.Skill;

public interface SkillDao extends Dao<Skill, Integer>
{
	boolean delete(String id);
	List<String> getSpecificSkill(String id);
}
