package uta.mav.appoint;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.beans.AddToWaitlistBean;
/**
 * Servlet implementation class ViewAppointmentServlet
 */
public class AddToWaitlistServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    HttpSession session;   
    String header;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n>>doGet servlet\n");
                session = request.getSession();
		LoginUser user = (LoginUser)session.getAttribute("user");
		if (user == null){
				user = new LoginUser();
				session.setAttribute("user", user);
				response.sendRedirect("/login.jsp");		
		}
		else{
			try{
				header = "templates/" + user.getHeader() + ".jsp";
			}
			catch(Exception e){
				System.out.printf( e.toString());
			}
		}
		
		request.setAttribute("includeHeader", header);
		request.getRequestDispatcher("/add_to_waitlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n>>doPost servlet\n");                
		try{
                        DatabaseManager dbm = new DatabaseManager();
			AddToWaitlistBean ca = new AddToWaitlistBean();
			ca.setEmail(request.getParameter("emailAddress"));
			ca.setPname(request.getParameter("pname"));
                        Boolean AddToWaitlistTemp = dbm.AddToWaitlist(ca);
			response.setContentType("text/plain");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setCharacterEncoding("UTF-8");
                        response.setHeader("Refresh","2; URL=advising");
                        request.getRequestDispatcher("/success.jsp").forward(request,response);
                        response.sendRedirect("/advising");
                        System.out.println("finished post-block");
			}
		catch(Exception e){
			System.out.printf(e.toString());
		}
	}
}

