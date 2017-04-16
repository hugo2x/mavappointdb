package uta.mav.appoint;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.db.DatabaseManager;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String email;
	String password;
	String rpassword;
	HttpSession session;
	String role;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/register.jsp").forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		email = request.getParameter("emailAddress");
		System.out.println("email"+email);
		password = request.getParameter("password");
		rpassword = request.getParameter("repeatPassword");
		role = "student";
		//need to add check for maverick email address		
//need to add check that both passwords match

		//need to redirect back to register with correct error message
		GetSet set = new GetSet();
		//System.out.println("emaillll@@@@"+email);
		//email=getmd5(email);
		set.setEmailAddress(email);
		//password=getmd5(password);
		set.setPassword(password);
		//role=getmd5(role);
		set.setRole(role);
		try{
			DatabaseManager dbm = new DatabaseManager();
			int check = dbm.addUser(set);
                        System.out.println(check);
			if (check > 0){
				//if adduser successful, log in as added user and redirect
				//back to start
				//session.setAttribute("role", "1");
				//session.setAttribute("emailAddress", email);
				response.sendRedirect("login.jsp");
			}
			else{
				//if unable to log in, add error message and redirect back to register
				request.setAttribute("error","Unable to add user");
				request.getRequestDispatcher("register.jsp").forward(request,response);
			}
		}
		catch(Exception e){
			
		}
		
		
	}

	private String getmd5(String input) {  try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);

        return hashtext;
    }
    catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }	}

}
