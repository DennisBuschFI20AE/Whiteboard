package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.Db;
import com.validation.Password;

@WebServlet("/log")
public class Login extends HttpServlet {
	
	private PrintWriter out;
	private Db db;
	private static HttpSession session;
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		out = response.getWriter();
		db 	= new Db();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		try 
		{
			if( db.getUser(username,password) != null)
			{
				db.addUser(username, password);
				session = request.getSession();
				session.setMaxInactiveInterval(84600);
				session.setAttribute("username", username);
				session.setAttribute("loginTime" , LocalDateTime.now());
				
				out.append(String.format( "Hallo %s %s", username, password));
				out.append("<br>Du hast dich erfolgreich eingeloggt, bist nun drin");
			}
			else
			{
				out.append("<br>User existiert bereits oder Passwort ist zu lahm!<a href='http://localhost:8080/Whiteboard'>zurueck</a>");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static HttpSession getSession() {
		return Login.session;
	}
}
