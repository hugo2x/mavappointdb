package uta.mav.appoint.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.visitor.AddAppointmentTypeVisitor;
import uta.mav.appoint.visitor.Visitor;


public class ReportBugSubmit  extends HttpServlet {
	private static final long serialVersionUID = 2L;
HttpSession session;
String header;

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String reportbug=request.getParameter("reportbug");
	System.out.println("feedback"+reportbug);
	request.setAttribute("includeHeader", header);
	request.getRequestDispatcher("/reportbugmsg.jsp").forward(request,response);
}}
