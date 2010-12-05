
public class SimpleMySQLConnect {
	public boolean existDriver(){
		boolean ret=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ret=true;
		} catch (ClassNotFoundException e) {
			ret=false;
		}
		
		return ret;
	}
	
}
