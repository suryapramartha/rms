package com.mitrais.rms.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mitrais.rms.dao.ProjectDao;
import com.mitrais.rms.dao.impl.ProjectDaoImpl;
import com.mitrais.rms.model.ListPIC;
import com.mitrais.rms.model.ListSkill;
import com.mitrais.rms.model.Project;

/**
 * Servlet implementation class ProjectServlet
 */
@WebServlet("/project/*")
public class ProjectServlet extends AbstractController 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProjectServlet() {
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
			if(step==null)
			{
				step = "LOAD_ALL";
			}
		
			switch (step)
			{
			case "LOAD_ALL":
				getAllProject(request,response,path);
				break;
			case "DELETE":
				deleteProject(request,response,path);
				break;
			case "NEW":
				newProject(request,response,path);
				break;
			case "NEWPIC":
				newPIC(request,response,path);
				break;
			case "EDIT":
				getSelectedProject(request,response,path);
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
				getAllProject(request,response,path);
				break;
			case "UPDATE":
				updateProject(request,response,path);
				break;
			case "CREATE":
				createSkill(request,response,path);
				break;
			case "CREATEPIC":
				updateProjectPIC(request,response,path);
				break;
			default:
				break;
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void updateProjectPIC(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String projectId = request.getParameter("projectIdForm");
		String emplName = request.getParameter("ListPIC");
		if(emplName==null) 
		{
			emplName = "";
		}
		int projectIdInt = Integer.valueOf(projectId);
		if(!emplName.equalsIgnoreCase(""))
		{
			ProjectDao projectDao = ProjectDaoImpl.getInstance();
			boolean prj = projectDao.updateProjectPIC(projectIdInt, emplName);
			if(prj) 
			{
				getAllProject(request,response,path);	
			}	
		}else 
		{
			getAllProject(request,response,path);		
		}
	}

	private void newPIC(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{	
		String projectId = request.getParameter("projectId");
		String emplName = request.getParameter("empl_name");
		List<String> sk = null;
		List<ListPIC> empl = null;
		List<ListSkill> skillNames = null;

		ProjectDao projectDao = ProjectDaoImpl.getInstance();
		skillNames = projectDao.getListSelectedSkillNames(projectId);
		sk = projectDao.getSpecificProject(projectId);
		empl = projectDao.getRelatedPIC(projectId,emplName);
		
		request.setAttribute("listSkillAttribute", skillNames);
		request.setAttribute("projectSelectedAttribute", sk);
		request.setAttribute("listPICAttribute", empl);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
	
	private void newProject(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException 
	{	
		List<ListSkill> skillNames = null;
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sm.format(new java.util.Date());
		
		ProjectDao projectDao = ProjectDaoImpl.getInstance();
		skillNames = projectDao.getListSkillNames();
		
		request.setAttribute("listSkillAttribute", skillNames);
		request.setAttribute("currDate",strDate);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
	    requestDispatcher.forward(request, response);
	}
	
	private void createSkill(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String nama = request.getParameter("projectNameForm");
		String startDate = request.getParameter("projectStartForm");
		String endDate = request.getParameter("projectEndForm");
		String req = request.getParameter("ListSkill");
		Date startDate1 =  Date.valueOf(startDate);
		Date endDate1 =  Date.valueOf(endDate);
		
		Project ProjectObj = new Project(0, nama,startDate1, endDate1,0,req,0,"-");
		ProjectDao projectDao = ProjectDaoImpl.getInstance();
        Boolean sk = projectDao.save(ProjectObj);
        if(sk) 
        {
        	getAllProject(request,response,path);
        }
		
	}

	private void updateProject(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String projectId = request.getParameter("projectIdForm");
		String projectName = request.getParameter("projectNameForm");
		String projectStartDate = request.getParameter("startDateForm");
		String projectEndDate = request.getParameter("endDateForm");
		String projectRequirement = request.getParameter("ListSkill");
		String projectPIC = request.getParameter("picForm");
		String currRequirement = request.getParameter("currReq");
		int projectId1 = Integer.valueOf(projectId);
		Date startDate1 =  Date.valueOf(projectStartDate);
		Date endDate1 =  Date.valueOf(projectEndDate);
		int emplId1=0;
		Project projectObj = new Project(projectId1, projectName,startDate1, endDate1,0,projectRequirement,emplId1,projectPIC);

		ProjectDao projectDao = ProjectDaoImpl.getInstance();
        Boolean prj = projectDao.update(projectObj);
        if(prj) 
        {
        	if(!currRequirement.equalsIgnoreCase(projectRequirement)) 
        	{
        		projectDao.resetProjectPIC(projectId1);
			}
        	getAllProject(request,response,path);
        }
	}

	private void getSelectedProject(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		List<String> sk = null;
		List<ListSkill> skillNames = null;

		String projectId = request.getParameter("projectId");
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sm.format(new java.util.Date());
		
		ProjectDao projectDao = ProjectDaoImpl.getInstance();
        sk = projectDao.getSpecificProject(projectId);
		skillNames = projectDao.getListSelectedSkillNames(projectId);
        request.setAttribute("projectSelectedAttribute", sk);
		request.setAttribute("listSkillAttribute", skillNames);
		request.setAttribute("currDate",strDate);
		
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
	}

	private void deleteProject(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException 
	{
		String projectId = request.getParameter("projectId");
		
		ProjectDao projectDao = ProjectDaoImpl.getInstance();
        Boolean isProject = projectDao.delete(projectId);
        if(isProject) 
        {
        	getAllProject(request,response,path);
        }
	}

	private void getAllProject(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException 
	{
		ProjectDao projectDao = ProjectDaoImpl.getInstance();
        List<Project> project = projectDao.findAll();
        request.setAttribute("projectAttribute", project);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
	}
}
