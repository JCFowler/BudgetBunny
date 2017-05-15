package com.revature.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.UserDAOImpl;

public class Test extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAOImpl ud = new UserDAOImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("Handling a Post request");
		
		String color = null;
		
		color = req.getParameter("color");
		
		System.out.println("The color requested was "+color);
		
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("<!DOCTYPE html><html><head><meta charset=\"ISO-8859-1\"><title>HelloWorld</title></head><body>");
		
		printWriter.write("<div><div style=\"background-color:"+color+"\"><h4>HELLO WORLD</h4></div></div>");

		
		printWriter.write("</body></html>");


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("We received a get request.");
		System.out.println(ud.getById(1));
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("<!DOCTYPE html><html><head><meta charset=\"ISO-8859-1\"><title>HelloWorld</title></head><body>");
		printWriter.write("<div><div style=\"background-color:aqua\"><h4>HELLO WORLD</h4></div></div>");
		printWriter.write("</body></html>");
	}
}
