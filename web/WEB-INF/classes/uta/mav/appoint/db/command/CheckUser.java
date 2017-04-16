package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.FacultyUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;

public class CheckUser extends SQLCmd{
	String email;
	String password;
	String pname;
	
	public CheckUser(String e, String p){
		email = e;
		password = p;
	}
	
	@Override
	public void queryDB(){
		try{System.out.println("enteredquerydb");
			String command = "SELECT COUNT(*),ROLE,pname FROM USER,advisor_settings WHERE user.userid=advisor_settings.userid AND user.EMAIL=? AND user.PASSWORD=?";
			PreparedStatement statement = conn.prepareStatement(command); 
			statement.setString(1,email);
			statement.setString(2,password);
			res = statement.executeQuery();
			while(res.next()){
				pname = res.getString(3);
			}
			command = "SELECT ROLE,email,password FROM user WHERE user.email=? AND user.password=?";
                        //String command = "SELECT email,password FROM USER ";
			statement = conn.prepareStatement(command); 
			statement.setString(1,email);
			statement.setString(2,password);
			res = statement.executeQuery();
                        while(res.next()){
                            System.out.println(res.getString(1));
                             System.out.println(res.getString(2));
                              System.out.println(res.getString(3));
                        }
			}
		catch (Exception e){
			System.out.println(e);	
		}
		
	}
	
	//@Override
        @Override
	public void processResult(){
		LoginUser user = null;
               System.out.println("enterprocessresult");
		try{
                   String command = "SELECT ROLE,email,password FROM user WHERE user.email=? AND user.password=?";
                        //String command = "SELECT email,password FROM USER ";
			PreparedStatement statement = conn.prepareStatement(command); 
			statement.setString(1,email);
			statement.setString(2,password);
			res = statement.executeQuery();
                    System.out.println("intry");
                    while(res.next()){
                         System.out.println(res.getString(1));
                    if (!(res.getString(1).equals(null))){
                         System.out.println(res.getString(1));
                    if (res.getString(1).toLowerCase().equals("advisor")){
                    user = new AdvisorUser(email,pname);
                    }
                    else if (res.getString(1).toLowerCase().equals("student")){
                    user = new StudentUser(email);
                    }
                    else if (res.getString(1).toLowerCase().equals("admin")){
                    user = new AdminUser(email);
                    }
                    else {
                    user = new FacultyUser(email);
                    }
                    }
                    result.add(user);
                   
			} 
			
                }
		catch(Exception e){
			System.out.println(e);
		}
		

}
}
