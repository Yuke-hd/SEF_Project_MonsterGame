package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import monsterGame.User;

public class SQL {
	final static String driver = "org.hsqldb.jdbc.JDBCDriver";
	final static String dbPath = "jdbc:hsqldb:file:database/User";

	public static ArrayList<User> importUser(){
		Connection con = null;
		Statement stmt = null;
		ResultSet result = null;
		ArrayList<User> user = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dbPath, "SA", "");
			stmt = con.createStatement();
			result = stmt.executeQuery("SELECT * FROM user");

			while (result.next()) {
			String u=result.getString(1);
			char[] pwd = result.getString(2).toCharArray();
			int s = result.getInt(3);
			User k = new User(u, pwd,s);
			user.add(k);
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		System.out.println(user.size());
		return user;
	}

	public static void insertData(User user) {
		Connection con = null;
		String sql = "INSERT INTO User (username, password, score) "
				+ " Values (?,?,?)";
		Statement stmt = null;
		int result = 0;
		
		String username = user.getUserName();
		String password = user.getPwdString();
		int score = user.getScore();
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dbPath, "SA", "");
			stmt = con.createStatement();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setInt(3, score);
			result = pstmt.executeUpdate();
			con.commit();
			
			System.out.println(result + " rows effected");
			System.out.println("Rows inserted successfully");
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
	}
	
	public static void update(String username, int score) {
		String sql = "UPDATE User SET score = ? where username = ?";
		try (Connection con = DriverManager.getConnection(dbPath, "SA", "");
				PreparedStatement stmt = con.prepareStatement(sql);) {
			stmt.setString(2, username);
			stmt.setInt(1, score);
			stmt.executeUpdate();

			System.out.println("Database updated successfully ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
