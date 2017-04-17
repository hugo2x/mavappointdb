package uta.mav.appoint;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.swing.JOptionPane;

import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.db.DatabaseManager;
import uta.mav.appoint.login.LoginUser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           
                session = request.getSession();
                request.getRequestDispatcher("/login.jsp").forward(request,response);
               // String emailAddress = request.getParameter("emailAddress");
                //String password = request.getParameter("password");
                
            
                

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            session = request.getSession();
                         

		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
                
		GetSet sets = new GetSet();
		sets.setEmailAddress(emailAddress);
		sets.setPassword(password);
                
		//session.setMaxInactiveInterval(10);
		 if (emailAddress != null && emailAddress.trim().length() > 0 && password != null && password.trim().length() > 0) {
		      System.out.println(emailAddress + ":" + password);
		     // boolean success = Auth.authenticate(userName.trim(), password.trim());
		   
		        if (request.getParameter("remember") != null) {
		          String remember = request.getParameter("remember");
		          System.out.println("remember : " + remember);
		          Cookie cUserName = new Cookie("cookuser", emailAddress.trim());
		          Cookie cPassword = new Cookie("cookpass", emailAddress.trim());
		          Cookie cRemember = new Cookie("cookrem", remember.trim());
		          cUserName.setMaxAge(60 * 60 * 24 * 15);//15 days
		          cPassword.setMaxAge(60 * 60 * 24 * 15);
		          cRemember.setMaxAge(60 * 60 * 24 * 15);
		          response.addCookie(cUserName);
		          response.addCookie(cPassword);
		          response.addCookie(cRemember);
		        }
		        HttpSession httpSession = request.getSession();
		        httpSession.setAttribute("sessuser", emailAddress.trim());
		       // response.sendRedirect("login");
		       // RequestDispatcher requestDispatcher = request.getRequestDispatcher("/home.jsp");
		       // requestDispatcher.forward(request, response);
		       
		    } else {
		      System.out.println("Username and Password are required fields.");
		      request.setAttribute("msg", "Username and Password are required fields.");
		      //response.sendRedirect("index");
		      //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
		      //requestDispatcher.forward(request, response);
		    }
		  
		try{
			//call db manager and authenticate user, return value will be 0 or
			//an integer indicating a role
			DatabaseManager dbm = new DatabaseManager();
                        
			LoginUser user = dbm.checkUser(sets);
                    
                                      
         
                    if(user != null)
                    {
                    session.setAttribute("user", user);
                    
                    
                     
                    response.sendRedirect("index");
                    
			
                    }
                    
                    else{
                       
                        Integer counter = (Integer)session.getAttribute("counter");
                        if(counter==null)
        {
            counter=1;
            session.setAttribute("counter",counter);
        }
        else 
        {
            session.setAttribute("counter",counter+1);
        }
                        
         
        
        System.out.println(counter);
        if(counter>2){
            JOptionPane.showMessageDialog(null, "completed maximum login attempts try after 2 minutes", "Error",
                                    JOptionPane.ERROR_MESSAGE);
            System.out.println("incounterif");
             long lastAccessedTime = session.getLastAccessedTime();
             System.out.println(lastAccessedTime);
             Date date;
                date = new Date();
                long currentTime = date.getTime();
                System.out.println(currentTime);
                long timeDiff = currentTime - lastAccessedTime;
                System.out.println(timeDiff);
                // 20 minutes in milliseconds  
                if (timeDiff >= 120000)
                {
                    //invalidate user session, so they can try again
                    GetSet sets2=new GetSet();
                        sets2.setEmailAddress(emailAddress);
                        sets2.setCounter(0);
                        dbm.updateflagUser(sets2);
                    //session.invalidate();
                    
                }
                else
                {
                     // Error message 
                    GetSet sets3=new GetSet();
                        sets3.setEmailAddress(emailAddress);
                        sets3.setCounter(counter);
                        dbm.updateflagUser(sets3);
                    
                    
                
			
                    }
                    
                    session.setAttribute("message","You have exceeded the 3 failed login attempt. Please try loggin in in 20 minutes, or call our customer service center at 1-800 555-1212.");
                } 
        response.sendRedirect("login.jsp");
        }
        
			
                                }
                  
	
		

catch(Exception e){
			System.out.println(e);
			response.sendRedirect("login");
		}
	}
}


