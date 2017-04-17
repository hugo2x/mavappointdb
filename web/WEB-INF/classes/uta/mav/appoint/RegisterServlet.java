package uta.mav.appoint;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.db.DatabaseManager;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String email;
	String password;
	String rpassword;
	HttpSession session;
	String role;
        String security;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
        private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/register.jsp").forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		email = request.getParameter("emailAddress");
		System.out.println("email"+email);
		password = request.getParameter("password");
		rpassword = request.getParameter("repeatPassword");
		role = "student";
                security=request.getParameter("sec");
                System.out.println(security);
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
                set.setSecurity(security);
                System.out.println(set.getSecurity());
                
		String subject = "Registration with mavappoint";
		
                String message = "You are registered with mavaapoint";
               String resultMessage="";
		

		try{   
                    ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	        reCaptcha.setPrivateKey("6LerBR0UAAAAAJ0goFehATkhuASyqnbV_UmO7rqH");
	 
	        String challenge = request.getParameter("recaptcha_challenge_field");
	        String uresponse = request.getParameter("recaptcha_response_field");
                 String remoteAddr = request.getRemoteAddr();
	        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
                
                    EmailUtility.sendEmail(host, port, user, pass, email, subject,message);
                
			DatabaseManager dbm = new DatabaseManager();
			int check = dbm.addUser(set);
                        System.out.println(check);
			if (check > 0){
                            if (reCaptchaResponse.isValid()) 
                            {//if adduser successful, log in as added user and redirect
				//back to start
				//session.setAttribute("role", "1");
				//session.setAttribute("emailAddress", email);
				response.sendRedirect("login.jsp");
                            GetSet sets1=new GetSet();
                sets1.setEmailAddress(email);
                sets1.setCounter(0);
                DatabaseManager dbm1 = new DatabaseManager();
                dbm1.flagUser(sets1);}
			}
			else{
				//if unable to log in, add error message and redirect back to register
				request.setAttribute("error","Unable to add user");
				request.getRequestDispatcher("register.jsp").forward(request,response);
			}
                        
                       
                }
	 
	        
		catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} 
		
		
	}
        
                
	private String getmd5(String input) { 
            try{
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
