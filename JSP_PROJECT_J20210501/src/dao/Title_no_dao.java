package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Title_no_dao {
	private static Title_no_dao instance;

	private Title_no_dao() {

	}

	public static Title_no_dao getInstance() {
		if (instance == null)
			instance = new Title_no_dao();

		return instance;
	}

	private Connection getConnection() {
		Connection conn = null;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return conn;
	}

	public String title_view(String member_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String title_name = "";

		String sql = "SELECT a.title_no, title_name, member_id FROM title_type a, title_user b WHERE a.title_no = b.title_no AND member_id= ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			System.out.println("Title_view_userID : " + member_id);

			if (rs.next())
				title_name = rs.getString(2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
		}		
		
		System.out.println("title_name : " + title_name);
		System.out.println();

		return title_name;
	}
}
