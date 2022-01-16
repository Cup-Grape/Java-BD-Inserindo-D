package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		java.sql.PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"INSERT INTO seller " + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
							+ "VALUES"
							+ "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, "Pedro");
			st.setString(2, "pedro@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("01/04/2001").getTime()));
			st.setDouble(4, 3000.00);
			st.setInt(5, 4);
			
			int receberResult = st.executeUpdate();
			
			if(receberResult > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("PRONTO!  id= " + id);
				}
			}
			else {
				System.out.println("Nem uma linha foi alterada");
			}
	
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
