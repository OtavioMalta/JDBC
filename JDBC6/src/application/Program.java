package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("ROW1: " + rows1);
			System.out.println("ROW2: " + rows2);

		}
		catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException("[ERROR Transaction rolles back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("[ERROR] Error trying to rollbacl! Caused by: " + e1.getMessage());
			}
		}finally {
			DB.closeStatment(st);
			DB.closeConnection();
		}
	}
}
