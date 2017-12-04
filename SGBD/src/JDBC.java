import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class JDBC {

	static JDBC jdbc = null;
	Connection connection;
	
	private JDBC() throws SQLException, ClassNotFoundException{
		Class.forName("oracle.jdbc.driver.OracleDriver");

		ParserXPath xpath = new ParserXPath();
		ArrayList<String> str = new ArrayList<String>();
		try {
			str = xpath.getInfo();
			String url = str.get(1);
			String user = str.get(2);
			String mdp = str.get(3);
			connection = DriverManager.getConnection(url, user, mdp);
			connection.setAutoCommit(true);
		}catch(Exception z) {
			z.printStackTrace();
		}
	}
	
	public static JDBC getInstance() throws SQLException, ClassNotFoundException{
		if(jdbc==null) jdbc = new JDBC();
		return jdbc;
	}
	
	public Connection getConnection(){
		return connection;
	}
}
