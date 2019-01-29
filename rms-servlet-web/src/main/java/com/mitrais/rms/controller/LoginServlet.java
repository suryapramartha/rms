package com.mitrais.rms.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends AbstractController
{

	private static final long serialVersionUID = 1L;
	private final String userID = "Admin";
	private final String password = "password";
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String path = getTemplatePath(req.getServletPath());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String user = request.getParameter("username");
		String pwd = request.getParameter("userpass");
		if(userID.equals(user) && password.equals(pwd))
		{
			HttpSession oldSession = request.getSession(false);
			if (oldSession != null) 
			{
               oldSession.invalidate();
			}
			HttpSession session = request.getSession(true);
			session.setAttribute("user", "Admin");
			session.setMaxInactiveInterval(3*60); //setting session to expired in 3 minutes
			
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(3*60);
			response.addCookie(userName);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
		}else
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/loginFailed.html");
			PrintWriter out= response.getWriter();
			out.println("<h4 align='center'><font color=red>Login Failed, Either user name or password is wrong.</font></h4>");
			out.println("<h3 align='center'><a href='login'>Try Again</a></h3>");
			rd.include(request, response);
		}
    }
}
