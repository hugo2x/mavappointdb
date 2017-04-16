package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

public class RegisterUser extends SQLCmd{

	String email;
	String role;
	String password;
	
	
	public RegisterUser(String email, String role, String password) {
		super();
		this.email = email;
		this.role = role;
		this.password = password;
	}

	@Override
	public void queryDB() {
		
	}

	@Override
	public void processResult() {try{
		String command = "insert user(email,password,role) VALUES(email=?,password=?,role=?)";
		PreparedStatement statement = conn.prepareStatement(command);
		statement.setString(1,email);
		statement.setString(2,password);
		statement.setString(3,role);
		res = statement.executeQuery();
		}
		catch(Exception e){
			System.out.println(e);
		}}

	
}
