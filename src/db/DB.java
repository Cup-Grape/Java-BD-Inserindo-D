package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class DB {

	private static Connection conn = null; // Faz a conexão com o banco de dados
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
				
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//Carrega os arquivos do db.properties
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs); // Faz a leitura do arquivo db.properties
			return props;
		}
		catch (Exception e) {
			throw new DbException(e.getMessage());
		}
	}
	
	
	
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			
		}
	}
	
	public static void closeResultSet(ResultSet rt) {
		if(rt != null) {
			try {
				rt.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			
		}
	}
	
}
