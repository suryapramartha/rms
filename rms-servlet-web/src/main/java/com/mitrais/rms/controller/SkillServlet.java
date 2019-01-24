package com.mitrais.rms.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mitrais.rms.dao.SkillDao;
import com.mitrais.rms.dao.impl.SkillDaoImpl;
import com.mitrais.rms.model.Skill;

/**
 * Servlet implementation class SkillServlet
 */
@WebServlet("/skill/*")
public class SkillServlet extends AbstractController
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkillServlet() {
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
				getAllSkill(request,response,path);
				break;
			case "DELETE":
				deleteSkill(request,response,path);
				break;
			case "EDIT":
				getSelectedSkill(request,response,path);
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
				getAllSkill(request,response,path);
				break;
			case "UPDATE":
				updateSkill(request,response,path);
				break;
			case "CREATE":
				createSkill(request,response,path);
				break;
			default:
				break;
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void createSkill(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String nama = request.getParameter("skillNameForm");
		String desc = request.getParameter("skillDescForm");
		Skill skillObj = new Skill(0, nama,desc, 0);
		SkillDao SkillDao = SkillDaoImpl.getInstance();
        Boolean sk = SkillDao.save(skillObj);
        if(sk) 
        {
        	getAllSkill(request,response,path);
        }	
	}

	private void updateSkill(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String skillId =  request.getParameter("skillIdForm");
		String skillName = request.getParameter("skillNameForm");
		String skillDesc = request.getParameter("skillDescForm");
		int idInt = Integer.valueOf(skillId);
		Skill skillObj = new Skill(idInt, skillName, skillDesc,0);
		
		SkillDao SkillDao = SkillDaoImpl.getInstance();
        Boolean sk = SkillDao.update(skillObj);
        if(sk) 
        {
        	getAllSkill(request,response,path);
        }
	}

	private void getSelectedSkill(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException 
	{
		String skillId = request.getParameter("skillId");
		SkillDao SkillDao = SkillDaoImpl.getInstance();
        List<String> sk = SkillDao.getSpecificSkill(skillId);
        request.setAttribute("skillSelectedAttribute", sk);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
	}

	private void deleteSkill(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException 
	{
		String empId = request.getParameter("skillId");
		
		SkillDao skillDao = SkillDaoImpl.getInstance();
        Boolean isSkill = skillDao.delete(empId);
        if(isSkill) 
        {
        	getAllSkill(request,response,path);
        }
	}

	private void getAllSkill(HttpServletRequest request, HttpServletResponse response,String path) throws ServletException, IOException 
	{
		SkillDao SkillDao = SkillDaoImpl.getInstance();
        List<Skill> skill = SkillDao.findAll();
        request.setAttribute("skillAttribute", skill);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
	}
}
