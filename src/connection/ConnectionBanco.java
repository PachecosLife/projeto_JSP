package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBanco {
	
	private static String banco = "jdbc:postgresql://localhost:5433/projeto-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public ConnectionBanco() {
		conectar();
	}
	
	
	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);
			} else {

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
