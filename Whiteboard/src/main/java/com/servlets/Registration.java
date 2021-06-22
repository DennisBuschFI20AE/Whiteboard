package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Db;
import com.validation.Valid;


@WebServlet("/reg")
public class Registration extends HttpServlet {
	
	private PrintWriter out;
	private Db db;
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in reg drin");
		if(Login.getSession() != null) 
		{
			db = new Db();
			String username = request.getParameter("username");
			String password = request.getParameter("passwort");
			if(Valid.isPasswordValid(password)) 
			{
				if( db.getUser(username, password) != null) 
				{
					db.addUser(username, password);
					out.append("Du hast dich erfolgreich registriert");
				}
			}
			else 
			{
				out.append("Ne");
			}
		}
		else 
		{
			out.append("Du bist bereits registriert!");
		}
	}

}
