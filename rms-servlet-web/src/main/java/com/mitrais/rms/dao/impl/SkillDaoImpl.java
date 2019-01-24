package com.mitrais.rms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.SkillDao;
import com.mitrais.rms.model.Skill;

public class SkillDaoImpl implements SkillDao
{
	@Override
	public Optional<Skill> find(Integer id) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Skill> findAll() 
	{
		List<Skill> result = new ArrayList<>();
	    try (Connection connection = DataSourceFactory.getConnection())
	    {
	      	 String sql = "Select * from para_skill  where skill_status=0 ";
	         PreparedStatement pstm = connection.prepareStatement(sql);
	         ResultSet rs = pstm.executeQuery();
	         while (rs.next()) 
	         {
	             int id = rs.getInt("skill_id");
	             String name = rs.getString("skill_name");
	             String desc = rs.getString("skill_desc");          
	             int status = rs.getInt("skill_status");
	             Skill sk = new Skill(id,name,desc,status);
	             result.add(sk);
	         }
	    }
	    catch (SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	    return result;
	}

	@Override
	 public boolean save(Skill skill)
    {
        try (Connection connection = DataSourceFactory.getConnection())
        {
        	int id = getNewId(connection);
    		String sql = "insert into para_skill values (?, ?, ?, ?)";
    		PreparedStatement pstm = connection.prepareStatement(sql);
    		pstm.setInt(1,id);
	        pstm.setString(2, skill.getSkillName());
	        pstm.setString(3,skill.getSkillDesc());
	        pstm.setInt(4, skill.getSkillStatus());
            int i = pstm.executeUpdate();
            
            if(i == 1) 
            {
                return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }


	@Override
	public boolean update(Skill skill) 
	{
		 try (Connection connection = DataSourceFactory.getConnection())
	        {
			 	String sql = "Update para_skill set skill_name =?, skill_desc=? where skill_id=? and skill_status=0";
			 	PreparedStatement pstm = connection.prepareStatement(sql);
		        pstm.setString(1, skill.getSkillName());
		        pstm.setString(2, skill.getSkillDesc());
		        pstm.setInt(3, skill.getSkillId());
	            int i = pstm.executeUpdate();
	            if(i == 1) 
	            {
	            	updateOnAssignedSkill(skill);
	                return true;
	            }
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	        return false;
	}

	private void updateOnAssignedSkill(Skill skill)
	{
		try (Connection connection = DataSourceFactory.getConnection())
        {
	        String sql = "Update empl_assigned_skill set skill_name =? where skill_id=?";
		 	PreparedStatement pstm = connection.prepareStatement(sql);
	        pstm.setString(1, skill.getSkillName());
	        pstm.setInt(2, skill.getSkillId());
            pstm.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
	}

	@Override
	public boolean delete(String id) 
	{
		try (Connection connection = DataSourceFactory.getConnection())
        {
			String sql = "Delete From para_skill where skill_id= ?";
	        PreparedStatement pstm = connection.prepareStatement(sql);
	        pstm.setString(1, id);
            int i = pstm.executeUpdate();
            if(i == 1) 
            {
                deleteOnAssignedSkill(id);
            	return true;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
	}

	private void deleteOnAssignedSkill(String id) 
	{
		try (Connection connection = DataSourceFactory.getConnection())
        {
	        String sql = "update empl_assigned_skill set skill_status=1 where skill_id=?";
		 	PreparedStatement pstm = connection.prepareStatement(sql);
		    pstm.setString(1,id);
            pstm.executeUpdate();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
	}

	public int getNewId(Connection con) throws SQLException 
	{
		int id = 0;
    	int idCore = 10;
    	String sql = "Select MAX(skill_id) as skill_id From para_skill";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) 
        {
            id = rs.getInt("skill_id");
        }

        if (id==0) 
        {
        	return idCore+1;
        }else 
        {
        	return id+1;
        }
	}
	
	@Override
	public List<String> getSpecificSkill(String id) 
	{
		List<String> list = new ArrayList<String>();
		try (Connection connection = DataSourceFactory.getConnection())
        {
			 	String sql = "Select * from para_skill  where skill_status=0 and skill_id=?";
			 	PreparedStatement pstm = connection.prepareStatement(sql);
		        pstm.setString(1,id);
		        ResultSet rs = pstm.executeQuery();
		        while (rs.next()) 
		        {
		        	String idSkill =String.valueOf(rs.getInt("skill_id"));
		            String name = rs.getString("skill_name");
		            String desc = rs.getString("skill_desc");          
		            String status =String.valueOf(rs.getInt("skill_status"));
		            
		            list.add(idSkill);
		            list.add(name);
		            list.add(desc);
		            list.add(status);
		        }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
       return list;
	}

	@Override
	public boolean delete(Skill o) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	private static class SingletonHelper
	{
	    private static final SkillDaoImpl INSTANCE = new SkillDaoImpl();
	}

	public static SkillDao getInstance()
	{
	    return SingletonHelper.INSTANCE;
	}

}
