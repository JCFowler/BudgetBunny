package com.revature.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.bean.Budget;
import com.revature.bean.User;
import com.revature.dao.BudgetDAOImpl;
import com.revature.dao.UserDAOImpl;

public class Test extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDAOImpl ud = new UserDAOImpl();
	private BudgetDAOImpl bd = new BudgetDAOImpl();

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
//		User u = ud.getById(1);
//		System.out.println(u.getBudget().getBudgetId());
		
//		Budget b = bd.save(new Budget(0, 777, 0));
//		User u = new User(0,"test3","password","Test3",b);
//		ud.save(u);
		
//		System.out.println("Is Username john Taken? " + ud.isUsernameTaken("john"));
//		System.out.println("Is Username john1 Taken? " + ud.isUsernameTaken("john1"));
		
//		System.out.println(ud.login("john", "password1"));
		User loggedIn = ud.login("john", "password");
		System.out.println(loggedIn.getBudget().getBudgetId());
//		System.out.println("Userid: " + loggedIn.getUserId() + " and BudgetId: " + loggedIn.getBudget().getBudgetId());
		
		System.out.println(bd.getById(loggedIn.getBudget().getBudgetId()));
		
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("<!DOCTYPE html><html><head><meta charset=\"ISO-8859-1\"><title>HelloWorld</title></head><body>");
		printWriter.write("<div><div style=\"background-color:aqua\"><h4>HELLO WORLD</h4></div></div>");
		printWriter.write("</body></html>");
	}
}
