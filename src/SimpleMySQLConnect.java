import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleMySQLConnect {
	private static boolean driverExist=false;
	public boolean existDriver(){
		boolean ret=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ret=true;
		} catch (ClassNotFoundException e) {
			ret=false;
		}
		driverExist=ret;
		return ret;
	}
	public Connection Connect(String serverName,String databaseName, String userName,String passwd) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Connection conn=null;
		if(driverExist){
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try {
				conn = DriverManager.getConnection("jdbc:mysql://"+serverName+"/"+databaseName+"?user="+userName+"&password="+passwd);
			} catch (SQLException e) {
				conn=null;
			}
			
		}
		return conn;
	}
	
}
