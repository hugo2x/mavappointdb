package uta.mav.appoint.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import uta.mav.appoint.TimeSlotComponent;
import uta.mav.appoint.beans.AllocateTime;
import uta.mav.appoint.beans.Appointment;
import uta.mav.appoint.beans.AppointmentType;
import uta.mav.appoint.beans.CreateAdvisorBean;
import uta.mav.appoint.beans.GetSet;
import uta.mav.appoint.login.AdminUser;
import uta.mav.appoint.login.AdvisorUser;
import uta.mav.appoint.login.LoginUser;
import uta.mav.appoint.login.StudentUser;
import uta.mav.appoint.beans.AddToWaitlistBean;


public class DatabaseManager {
		DBImplInterface imp = new RDBImpl();
	
		
	public Connection connectDB(){
		try
	    {
	    Class.forName("com.mysql.jdbc.Driver").newInstance();
	    String jdbcUrl = "jdbc:mysql://localhost/mavappointdb";
	    String userid = "root";
	    String password = "root";
	    Connection conn = DriverManager.getConnection(jdbcUrl,userid,password);
	    return conn;
	    }
	    catch (Exception e){
	        System.out.println(e.toString());
	    }
	    return null;
	}	
	//user login checking, check username and password against database
	//then return role if a match is found
        
	public Boolean createAdvisor(CreateAdvisorBean ca) throws SQLException{
		return imp.createAdvisor(ca);
	}
		
	public LoginUser checkUser(GetSet set) throws SQLException{
		return imp.checkUser(set);
		}
	
	public int addUser(GetSet set) throws SQLException{
		return imp.addUser(set);
	}
	
        
	public ArrayList<String> getAdvisors() throws SQLException{
		return imp.getAdvisors();
	}
	
	public ArrayList<TimeSlotComponent> getAdvisorSchedule(String name) throws SQLException{
		return imp.getAdvisorSchedule(name);
	}

	public Boolean createAppointment(Appointment a,String email) throws SQLException{
		return imp.createAppointment(a,email);
	}

	public ArrayList<Object> getAppointments(LoginUser user) throws SQLException{
		if (user instanceof AdvisorUser){
			return imp.getAppointments((AdvisorUser)user);
		}
		else if (user instanceof StudentUser){
			return imp.getAppointments((StudentUser)user);
		}
		else if (user instanceof AdminUser){
			return imp.getAppointments((AdminUser)user);
		}
		else
			return null;
	}

	public Boolean cancelAppointment(int id) throws SQLException{
		return imp.cancelAppointment(id);
	}
	
	public String addTimeSlot(AllocateTime at) throws SQLException{
		return imp.addTimeSlot(at);
	}
	
	public ArrayList<AppointmentType> getAppointmentTypes(String pname) throws SQLException{
		return imp.getAppointmentTypes(pname);
	}
	
	public Boolean updateAppointment(Appointment a) throws SQLException{
		return imp.updateAppointment(a);
	}

	public Boolean deleteTimeSlot(AllocateTime at) throws SQLException{
		return imp.deleteTimeSlot(at);
	}

	public Appointment getAppointment(String date, String email) throws SQLException{
		return imp.getAppointment(date,email);
	}

	public String addAppointmentType(AdvisorUser user, AppointmentType at) throws SQLException{
		return imp.addAppointmentType(user, at);
	}

    public int frgtpassuser(GetSet set)throws SQLException{
            return imp.frgtpassuser(set);
        }
    public int flagUser(GetSet set)throws SQLException{
                          
             return imp.flagUser(set);
                
        }
        public int updateflagUser(GetSet set)throws SQLException{
                          
             return imp.updateflaguser(set);
                
        }
    public String GetWaitlist() throws SQLException{
            return imp.GetWaitlist();
        }
	 public Boolean AddToWaitlist(AddToWaitlistBean ca) throws SQLException{
        return imp.AddToWaitlist(ca);
         }
 }


