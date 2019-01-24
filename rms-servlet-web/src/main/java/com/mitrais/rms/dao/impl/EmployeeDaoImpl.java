package com.mitrais.rms.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.EmployeeDao;
import com.mitrais.rms.model.Employee;
import com.mitrais.rms.model.EmployeeCompetency;
import com.mitrais.rms.model.ListSkill;



public class EmployeeDaoImpl implements EmployeeDao
{
	@Override
	public Optional<Employee> find(Integer id) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() 
	{
		List<Employee> result = new ArrayList<>();
	    try (Connection connection = DataSourceFactory.getConnection())
	    {
	      	 String sql = "Select * from para_employee  where empl_status=0 ";
	         PreparedStatement pstm = connection.prepareStatement(sql);
	         ResultSet rs = pstm.executeQuery();
	         while (rs.next()) 
	         {
	             int id = rs.getInt("empl_id");
	             String name = rs.getString("empl_name");
	             int status = rs.getInt("empl_status");
	             int age = rs.getInt("empl_age");
	             String adress = rs.getString("empl_address");
	             Employee emp = new Employee(id,name,status,age,adress);
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
	 public boolean save(Employee emp)
    {
        try (Connection connection = DataSourceFactory.getConnection())
        {
        	int id = getNewId(connection);
    		String sql = "INSERT INTO para_employee VALUES (?, ?, ?, ?, ?)";
    		PreparedStatement pstm = connection.prepareStatement(sql);
    		pstm.setInt(1,id);
	        pstm.setString(2, emp.getEmplName());
	        pstm.setInt(3, emp.getEmplAge());
	        pstm.setInt(4,emp.getEmplStatus());
	        pstm.setString(5, emp.getEmplAddress());
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
	public boolean update(Employee emp) 
	{
		 try (Connection connection = DataSourceFactory.getConnection())
	        {
			 	String sql = "Update para_employee set empl_name =?, empl_age=?, empl_address=? where empl_id=? and empl_status=0";
			 	PreparedStatement pstm = connection.prepareStatement(sql);
		        pstm.setString(1, emp.getEmplName());
		        pstm.setInt(2, emp.getEmplAge());
		        pstm.setString(3, emp.getEmplAddress());
		        pstm.setInt(4, emp.getEmplId());
		        pstm.executeUpdate();
	            int i = pstm.executeUpdate();
	            if(i == 1) 
	            {	
	            	updateOnAssignedSkill(emp);
	            	updateOnProject(emp);
	                return true;
	            }
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	        return false;
	}

	private void updateOnAssignedSkill(Employee emp)
	{
		try (Connection connection = DataSourceFactory.getConnection())
        {
		    String sql = "Update empl_assigned_skill set empl_name =? where empl_id=?";
		    PreparedStatement pstm = connection.prepareStatement(sql);
		    pstm.setString(1, emp.getEmplName());
		    pstm.setInt(2, emp.getEmplId());
		    pstm.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void updateOnProject(Employee emp)
	{
		try (Connection connection = DataSourceFactory.getConnection())
        {
			 String sql = "Update para_project set empl_name =? where empl_id=?";
			 PreparedStatement pstm = connection.prepareStatement(sql);
		     pstm.setString(1, emp.getEmplName());
		     pstm.setInt(2, emp.getEmplId());
		     pstm.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean delete(String id) 
	{	
		try (Connection connection = DataSourceFactory.getConnection())
        {
			String sql = "Delete From para_employee where empl_id= ?";
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
			 String sql = "Delete From empl_assigned_skill where empl_id= ?";
			 PreparedStatement pstm = connection.prepareStatement(sql);
		     pstm.setInt(1,Integer.valueOf(id));
		     pstm.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public int getNewId(Connection con) throws SQLException 
	{
		int id = 0;
    	int idCore = 10000;
    	String sql = "Select MAX(empl_id) as empl_id From para_employee";
        PreparedStatement pstm = con.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next())
        {
            id = rs.getInt("empl_id");
        }

        if (id == 0) 
        {
        	return idCore+1;
        }else {
        	return id+1;
        }
	}
	
	@Override
	public List<String> getSpecificEmployee(String id) 
	{
		List<String> list = new ArrayList<String>();
		try (Connection connection = DataSourceFactory.getConnection())
        {
			 String sql = "Select * from para_employee  where empl_status=0 and empl_id=?";
			 PreparedStatement pstm = connection.prepareStatement(sql);
		     pstm.setString(1,id);
		     ResultSet rs = pstm.executeQuery();
		     while (rs.next()) 
		     {
		    	 String emplId = rs.getString("empl_id");
		         String name = rs.getString("empl_name");
		         int status = rs.getInt("empl_status");
		         int age = rs.getInt("empl_age");
		         String adress = rs.getString("empl_address");
		            
		         list.add(emplId);
		         list.add(name);
		         list.add(String.valueOf(status));
		         list.add(String.valueOf(age));
		         list.add(adress);
		    }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
       return list;
	}

	@Override
	public boolean delete(Employee o)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<String> getEmployee(String emplId) 
	{
		List<String> list = new ArrayList<String>();
		try (Connection connection = DataSourceFactory.getConnection())
	    {
			String sql = "Select * from para_employee  where empl_id=? and empl_status=0";
			PreparedStatement pstm = connection.prepareStatement(sql);
		    pstm.setString(1, emplId);
		    ResultSet rs = pstm.executeQuery();
		
		    while (rs.next())
			{
				String empId =String.valueOf(rs.getInt("empl_id"));
			    String empName = rs.getString("empl_name");
			    String empAge = String.valueOf(rs.getInt("empl_age"));
			    String empAddress = rs.getString("empl_address");

			    list.add(empId);
			    list.add(empName);
			    list.add(empAge);
			    list.add(empAddress);
			}
	   }
	    catch (SQLException ex)
	    {
	       	ex.printStackTrace();
	    }
		return list;
	}
	

	@Override
	public List<EmployeeCompetency> getAllAssignedSkill(String emplId) 
	{
		List<EmployeeCompetency> list = new ArrayList<EmployeeCompetency>();
		try (Connection connection = DataSourceFactory.getConnection())
	    {
	        String sql = "Select * from empl_assigned_skill  where empl_id=? and skill_status=0";
	        PreparedStatement pstm = connection.prepareStatement(sql);
	        pstm.setString(1, emplId);
	 
	        ResultSet rs = pstm.executeQuery();
	        while (rs.next()) 
	        {
	            int empId = rs.getInt("empl_id");
	            String empName = rs.getString("empl_name");
	            int skillId = rs.getInt("skill_id");
	            String skillName = rs.getString("skill_name");
	            int skillStatus = rs.getInt("skill_status");
	            
	            EmployeeCompetency emp = new EmployeeCompetency(empId,empName,skillId,skillName,skillStatus);
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
	public boolean deleteCompetency(String emplId, String skillId)
	{
		try (Connection connection = DataSourceFactory.getConnection())
        {
	        String sql = "Delete From empl_assigned_skill where empl_id=? and skill_id= ?";
	        PreparedStatement pstm = connection.prepareStatement(sql);
	        pstm.setInt(1,Integer.valueOf(emplId));
	        pstm.setInt(2, Integer.valueOf(skillId));
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
	public List<ListSkill> getListSkillNames(String emplId) 
	{
		List<ListSkill> list = new ArrayList<ListSkill>();
		try (Connection connection = DataSourceFactory.getConnection())
	    {
			String sql = "Select skill_id,skill_name from para_skill  where skill_status=0 and skill_id not in "
		        	+ "(select skill_id from empl_assigned_skill where empl_id=? and skill_status=0)";
			PreparedStatement pstm = connection.prepareStatement(sql);
		    pstm.setString(1, emplId);
		    ResultSet rs = pstm.executeQuery();
		    while (rs.next()) 
		    {
		    	int skill_id =rs.getInt("skill_id");
		        String skill_name = rs.getString("skill_name");
		        ListSkill emp = new ListSkill(skill_id,skill_name);
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
	 public String getSkillNames(String skill_id)
	{
		 String skillName=null;
		 try (Connection connection = DataSourceFactory.getConnection())
	        {
		        String sql = "Select skill_name from para_skill where skill_id=?";
		        PreparedStatement pstm = connection.prepareStatement(sql);
		        pstm.setString(1, skill_id);
		        ResultSet rs = pstm.executeQuery();
		        while (rs.next())
		        {
		        	skillName = rs.getString("skill_name");
		        }
	        }
	        catch (SQLException ex)
	        {
	            ex.printStackTrace();
	        }
	        return skillName;
	}
	
	@Override
	public boolean insertAssignedSkill(EmployeeCompetency sk)  
	{
        try (Connection connection = DataSourceFactory.getConnection())
        {
        	String sql = "insert into empl_assigned_skill values (?, ?, ?, ?,?)";
    		PreparedStatement pstm = connection.prepareStatement(sql);
    		pstm.setInt(1,sk.getEmplId());
	        pstm.setString(2, sk.getEmplName());
	        pstm.setInt(3, sk.getSkillId());
	        pstm.setString(4,sk.getSkillName());
	        pstm.setInt(5,0);
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

	
	private static class SingletonHelper
	{
	    private static final EmployeeDaoImpl INSTANCE = new EmployeeDaoImpl();
	}

	public static EmployeeDao getInstance()
	{
	    return SingletonHelper.INSTANCE;
	}
}
