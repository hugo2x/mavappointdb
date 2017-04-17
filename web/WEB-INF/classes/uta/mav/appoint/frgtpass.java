/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.mav.appoint;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.db.DatabaseManager;

/**
 *
 * @author Pooja Endapally
 */
public class frgtpass extends HttpServlet {

        
    String email;
    String password;
    String security;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.getRequestDispatcher("/frgtpass.jsp").forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resultMessage="";
        email = request.getParameter("emailAddress");
		System.out.println("email"+email);
		password = request.getParameter("pass");
                security=request.getParameter("sec");
                GetSet set = new GetSet();
		//System.out.println("emaillll@@@@"+email);
		//email=getmd5(email);
		set.setEmailAddress(email);
                System.out.println(set.getEmailAddress());
		//password=getmd5(password);
		set.setPassword(password);
                System.out.println(set.getPassword());
                set.setSecurity(security);
                System.out.println(set.getSecurity());
                try{
                DatabaseManager dbm = new DatabaseManager();
        
            int check = dbm.frgtpassuser(set);
                if (check > 0){
                    response.sendRedirect("success.jsp");
                }
                else 
                {
                    response.sendRedirect("login.jsp");
                }
                }
                
        catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
}
