package uta.mav.appoint.db.command;

import java.sql.PreparedStatement;

public class GetWaitlist extends SQLCmd{

	String output;
	
	public GetWaitlist(){
	}
	@Override
	public void queryDB() {
		try{
			String command = "SELECT * FROM wait_list";
			PreparedStatement statement = conn.prepareStatement(command);
			res = statement.executeQuery();
                        System.out.println("RES=" + res.getFetchSize());
                            for(int i =0; i< res.getFetchSize(); i++){
                                output = res.getString(i);
                                System.out.println("output["+i+"]="+res.getString(i));
                            }
                            
			}
			catch(Exception e){
				System.out.println(e);
			}
	}

	@Override
	public void processResult() {
		try{
			while (res.next()){
				result.add(res.getInt(1));
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
        public String getOutput(){
            return output;   
        }
}
