package com.mitrais.rms.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.ProjectDao;
import com.mitrais.rms.model.ListPIC;
import com.mitrais.rms.model.ListSkill;
import com.mitrais.rms.model.Project;


public class ProjectDaoImpl implements ProjectDao
{
	public Optional<Project> find(Integer id) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> findAll() 
	{
		List<Project> result = new ArrayList<>();
	    try (Connection connection = DataSourceFactory.getConnection())
	    {
	        String sql = "Select * from para_project  where project_status=0 ";
	        PreparedStatement pstm = connection.prepareStatement(sql);
	        ResultSet rs = pstm.executeQuery();
	        while (rs.next())
	        {
	            int id = rs.getInt("project_id");
	            String name = rs.getString("project_name");
	            Date startDate = rs.getDate("project_start_date");
	            Date endDate = rs.getDate("project_end_date");
	            int status = rs.getInt("project_status");
	            String req = rs.getString("project_requirement");
	            int emplId = rs.getInt("empl_id");
	            String emplName = rs.getString("empl_name");
	            Project emp = new Project(id,name,startDate,endDate,status,req,emplId,emplName);
	            result.add(emp);
	        }
	    }
	    catch (SQLException ex)
	    {
	        ex.printStackTrace();
	    }
	    return result;
	}

	@Override
	 public boolean save(Project prj)
    {
        try (Connection connection = DataSourceFactory.getConnection())
        {
        	int id = getNewId(connection);
    		String sql = "insert into para_project values (?, ?, ?, ?, ?,?,?,?)";
    		PreparedStatement pstm = connection.prepareStatement(sql);
    		pstm.setInt(1,id);
	        pstm.setString(2, prj.getProjectName());
	        pstm.setDate(3,(Date) prj.getProjectStartDate());
	        pstm.setDate(4,(Date) prj.getProjectEndDate());
	        pstm.setInt(5, prj.getProjectStatus());
	        pstm.setString(6, prj.getProjectRequirement());
	        pstm.setInt(7, 0);
	        pstm.setString(8, "-");
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
	public boolean update(Project prj) 
	{
		 try (Connection connection = DataSourceFactory.getConnection())
	        {
			 	String sql = "Update para_project set project_name =?, project_start_date=?,project_end_date=?,project_requirement=? where project_id=? and project_status=0";
			    PreparedStatement pstm = connection.prepareStatement(sql);
		        pstm.setString(1, prj.getProjectName());
		        pstm.setDate(2, (Date) prj.getProjectStartDate());
		        pstm.setDate(3, (Date) prj.getProjectEndDate());
		        pstm.setString(4, prj.getProjectRequirement());
		        pstm.setInt(5, prj.getProjectId());
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
	public boolean delete(String id) 
	{
		try (Connection connection = DataSourceFactory.getConnection())
        {
			String sql = "Delete From para_project where project_id= ?";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, id);
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

	public int getNewId(Connection con) throws SQLException 
	{
		int id = 0;
    	int idCore = 8000;
    	String sql = "Select MAX(project_id) as project_id From para_project";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next())
        {
            id = rs.getInt("project_id");
        }
        if (id == 0) {
        	return idCore+1;
        }else
        {
        	return id+1;
        }
	}
	
	@Override
	public List<String> getSpecificProject(String id) 
	{
		List<String> list = new ArrayList<String>();
		try (Connection connection = DataSourceFactory.getConnection())
        {
			  String sql = "Select * from para_project  where project_status=0 and project_id=?";
			  PreparedStatement pstm = connection.prepareStatement(sql);
		      pstm.setString(1,id);
		      ResultSet rs = pstm.executeQuery();
		      while (rs.next()) 
		      {
		    	  String idProject = String.valueOf(rs.getInt("project_id"));
		          String name = rs.getString("project_name");
		          String start_date = rs.getDate("project_start_date").toString();
		          String end_date = rs.getDate("project_end_date").toString();
		          String status =String.valueOf(rs.getInt("project_status"));
		          String req = rs.getString("project_requirement");
		          String pic = rs.getString("empl_name");
		            
		          list.add(idProject);
		          list.add(name);
		          list.add(start_date);
		          list.add(end_date);
		          list.add(status);
		          list.add(req);
		          list.add(pic);
		      }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
       return list;
	}

	@Override
	public boolean delete(Project o) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<ListSkill> getListSelectedSkillNames(String id) 
	{
		List<ListSkill> list = new ArrayList<ListSkill>();
		try (Connection connection = DataSourceFactory.getConnection())
        {
	        String sql =
	        		" select (SELECT skill_id from para_skill where skill_name in "
	        		+ "( select project_requirement from para_project where project_id=?)" + 
	        		") as skill_id,project_requirement as skill_name from para_project where project_id=?" + 
	        		" UNION " + 
	        		"Select skill_id,skill_name from para_skill  "
	        		+ "where skill_status=0 and skill_name not in (select project_requirement from para_project where project_id=?)";
	 
	        PreparedStatement pstm = connection.prepareStatement(sql);
	        pstm.setString(1, id);
	        pstm.setString(2, id);
	        pstm.setString(3, id);
	        ResultSet rs = pstm.executeQuery();
	        while (rs.next())
	        {
	        	int skillId =rs.getInt("skill_id");
	            String skillName = rs.getString("skill_name");
	            ListSkill emp = new ListSkill(skillId,skillName);
	            list.add(emp);
	        }
	    }
		catch (SQLException ex)
		{
            ex.printStackTrace();
		}
        return list;
	}
	
	@Override
	public boolean resetProjectPIC(int projectId)
	{
		try (Connection connection = DataSourceFactory.getConnection())
        {
			String sql = "Update para_project set empl_id=0,empl_name='-' where project_id=? and project_status=0";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, projectId);
			int i = pstm.executeUpdate();
	        if(i == 1) 
	        {
	        	return true;
	        }
        }
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
    }
	 
	@Override
	public List<ListSkill> getListSkillNames() 
	{
		List<ListSkill> list = new ArrayList<ListSkill>();
		try (Connection connection = DataSourceFactory.getConnection())
        {
			String sql = "Select skill_id,skill_name from para_skill  where skill_status=0 ";
			PreparedStatement pstm = connection.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
	        while (rs.next()) 
	        {
	        	int skill_id =rs.getInt("skill_id");
	            String skill_name = rs.getString("skill_name");
	            ListSkill emp = new ListSkill(skill_id,skill_name);
	            list.add(emp);
	        }
        }catch(SQLException e) {
        	e.printStackTrace();
        }
		return list;
	}
	
	@Override
	public List<ListPIC> getRelatedPIC(String projectId, String emplName) 
	{
		String sql;
        List<ListPIC> list = new ArrayList<ListPIC>();
		PreparedStatement pstm ;
		try (Connection connection = DataSourceFactory.getConnection())
        {
			if (emplName.equalsIgnoreCase("-") || emplName==null)
			{
				sql = "Select empl_id,empl_name from empl_assigned_skill where skill_status=0 and skill_name in"
		       		+ " (select project_requirement from para_project where project_id=?)";
				pstm = connection.prepareStatement(sql);
				pstm.setString(1,projectId);
			} else
			{
				sql=  "select (SELECT empl_id from para_employee where empl_name in " + 
		       	"        		 ( select empl_name from para_project where project_id=?)  " + 
		       	"        		) as empl_id," + 
		       	"empl_name  from para_project where project_id=? " + 
		       	" UNION   " + 
		       	"        Select empl_id,empl_name from empl_assigned_skill where skill_status=0 and skill_name in" + 
		       	"        		  (select project_requirement from para_project where project_id=?)" + 
		       	"		and empl_name not in(select empl_name from para_project where project_id=?)" ; 
				pstm = connection.prepareStatement(sql);
				pstm.setString(1,projectId);
				pstm.setString(2,projectId);
				pstm.setString(3,projectId);
				pstm.setString(4,projectId);
			}
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) 
			{
				String id = String.valueOf(rs.getInt("empl_id"));
				String name = rs.getString("empl_name");
				ListPIC emp = new ListPIC(id,name);
				list.add(emp);
			}
        }catch(SQLException e) 
		{
        	e.printStackTrace();
        }
        return list;
	}
	
	@Override
	public boolean updateProjectPIC(int projectId, String emplName) 
	{
		try (Connection connection = DataSourceFactory.getConnection())
        {
			String sql = "Update para_project set empl_id=(select empl_id from para_employee where empl_name=? and empl_status=0),"
	        		+ "empl_name=? where project_id=? and project_status=0";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1,emplName);
	        pstm.setString(2, emplName);
	        pstm.setInt(3,projectId);
			int i = pstm.executeUpdate();
	        if(i == 1) 
	        {
	        	return true;
	        }
        }
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	private static class SingletonHelper
	{
	    private static final ProjectDaoImpl INSTANCE = new ProjectDaoImpl();
	}

	public static ProjectDao getInstance()
	{
	    return SingletonHelper.INSTANCE;
	}

	
	

	
}
