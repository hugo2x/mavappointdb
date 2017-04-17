package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

import uta.mav.appoint.beans.AddToWaitlistBean;

public class AddToWaitlist extends SQLCmd{
	int userid;
	String email;
	String name;
	Boolean b;
	
	public AddToWaitlist(int a,AddToWaitlistBean ca){
                userid = a;
		email = ca.getEmail();
		name = ca.getPname();
		b = false;
	}
	
	@Override
	public void queryDB() {
		try{
			String command = "INSERT INTO wait_list (id,name,email,validated)"
							+" values(?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(command);
                        statement.setInt(1,userid);
                        statement.setString(2,name);
			statement.setString(3,email);
			statement.setInt(4,1);
			statement.executeUpdate();
			b = true;
                        System.out.println(">>query to waitlist");
			}
		catch(Exception e){
			System.out.println(e);
		}
	}

	@Override
	public void processResult() {
		result.add(b);	
	}

		
}
