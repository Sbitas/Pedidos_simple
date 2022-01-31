package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	public static void main(String[] args) throws SQLException {}
	
	String url = "jdbc:mysql://localhost:3306/pedidos_simple";
	String uName = "root";
	String uPassword = "";
	Connection connection;
	Statement statement;
	
	// Getters
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getuName() {
		return uName;
	}


	public void setuName(String uName) {
		this.uName = uName;
	}


	public String getuPassword() {
		return uPassword;
	}


	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}


	public Connection getConnection() {
		return connection;
	}


	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	public Statement getStatement() {
		return statement;
	}


	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	// Constructor
	public DbConnection(){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			this.setConnection(DriverManager.getConnection(url, uName, uPassword));
			this.setStatement(this.getConnection().createStatement());
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		
	}
	
}
