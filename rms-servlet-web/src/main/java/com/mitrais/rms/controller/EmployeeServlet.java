package com.mitrais.rms.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mitrais.rms.dao.EmployeeDao;
import com.mitrais.rms.dao.impl.EmployeeDaoImpl;
import com.mitrais.rms.model.Employee;
import com.mitrais.rms.model.EmployeeCompetency;
import com.mitrais.rms.model.ListSkill;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/employee/*")
public class EmployeeServlet extends AbstractController 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path = getTemplatePath(request.getServletPath()+request.getPathInfo()); 
		try 
		{
			String step = request.getParameter("command");
			if(step == null)
			{
				step = "LOAD_ALL";
			}
			
			switch (step)
			{
			case "LOAD_ALL":
				getAllEmployee(request,response,path);
				break;
			case "DELETE":
				deleteEmployee(request,response,path);
				break;
			case "DELETE_CMP":
				deleteEmployeeCompetency(request,response,path);
				break;
			case "NEW_CMP":
				NewEmployeeCompetency(request,response,path);
				break;
			case "EDIT":
				getSelectedEmployee(request,response,path);
				break;
			case "VIEW":
				ViewEmployeeCompetency(request,response,path);
				break;
			default:
				break;
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path = getTemplatePath(request.getServletPath()+request.getPathInfo()); 
		try 
		{
			String step = request.getParameter("command");
			if(step==null)
			{
				step = "LOAD_ALL";
			}
			
			switch (step)
			{
			case "LOAD_ALL":
				getAllEmployee(request,response,path);
				break;
			case "UPDATE":
				updateEmployee(request,response,path);
				break;
			case "CREATE":
				createEmployee(request,response,path);
				break;
			case "CREATE_CMP":
				createEmployeeCompetency(request,response,path);
				break;
			default:
				break;
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	private void createEmployeeCompetency(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String emplId = request.getParameter("emplIdForm");
		String emplName = request.getParameter("emplNameForm");
		String skillId = request.getParameter("ListSkill");
		int emplIdInt = Integer.valueOf(emplId);
		if(skillId == null)
		{
			skillId = "";
		}
		if(!skillId.equalsIgnoreCase("")) 
		{
			int skillIdInt = Integer.valueOf(skillId);
			EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
			String skillName = employeeDao.getSkillNames(skillId);
			EmployeeCompetency cmpObj = new EmployeeCompetency(emplIdInt, emplName,skillIdInt,skillName,0);
			Boolean employee = employeeDao.insertAssignedSkill(cmpObj);
			if(employee) 
			{
				ViewEmployeeCompetency(request,response,path);
			}
		}else
		{
			ViewEmployeeCompetency(request,response,path);
		}
	}

	private void NewEmployeeCompetency(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		List<String> emp = null;
		List<ListSkill> skillNames = null;
		String id = request.getParameter("employeeId");
		EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
		emp = employeeDao.getEmployee(id);
		skillNames = employeeDao.getListSkillNames(id);
		request.setAttribute("currEmployeeAttribute",emp);
		request.setAttribute("listSkillAttribute", skillNames);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	private void deleteEmployeeCompetency(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String skillId = request.getParameter("skillId");
		String emplId = request.getParameter("employeeId");
		EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
        Boolean employee = employeeDao.deleteCompetency(emplId, skillId);
        if(employee) 
        {
			ViewEmployeeCompetency(request,response,path);
        }
	}
	
	private void ViewEmployeeCompetency(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		List<EmployeeCompetency> sk = null;
		List<String> emp = null;
		String id = request.getParameter("employeeId");
		if(id==null) 
		{
			id=request.getParameter("emplIdForm");
		}
		
		EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
		sk = employeeDao.getAllAssignedSkill(id);
		emp = employeeDao.getEmployee(id);
		request.setAttribute("assignedSkillAttribute", sk);
		request.setAttribute("currEmployeeAttribute",emp);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	private void createEmployee(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String nama = request.getParameter("emplNameForm");
		String age = request.getParameter("emplAgeForm");
		String address = request.getParameter("emplAddressForm");
		int ageInt = Integer.valueOf(age);
		Employee emplList = new Employee(0, nama, ageInt, 0, address);
		EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
        Boolean employee = employeeDao.save(emplList);
        if(employee) 
        {
        	getAllEmployee(request,response,path);
        }		
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String empId =  request.getParameter("emplIdForm");
		String empName = request.getParameter("emplNameForm");
		String empAge = request.getParameter("emplAgeForm");
		String empAddress = request.getParameter("emplAddressForm");
		int id = Integer.valueOf(empId);
		int empAgeInt = Integer.valueOf(empAge);
		Employee emplList = new Employee(id, empName, 0, empAgeInt, empAddress);
		
		EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
        Boolean employee = employeeDao.update(emplList);
        if(employee) 
        {
        	getAllEmployee(request,response,path);
        }
	}

	private void getSelectedEmployee(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String empId = request.getParameter("employeeId");
		EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
        List<String> employee = employeeDao.getSpecificEmployee(empId);
        request.setAttribute("employeeSelectedAttribute", employee);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException 
	{
		String empId = request.getParameter("employeeId");
		
		EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
        Boolean employee = employeeDao.delete(empId);
        if(employee) 
        {
        	getAllEmployee(request,response,path);
        }
	}

	private void getAllEmployee(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException 
	{
		EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
        List<Employee> employee = employeeDao.findAll();
        request.setAttribute("employeeAttribute", employee);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
	}
}
