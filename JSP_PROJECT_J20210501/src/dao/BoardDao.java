package dao;

import java.io.IOException;
import java.sql.*;

import javax.sql.*;
import javax.naming.*;

public class BoardDao {
	private static BoardDao instance;

	private BoardDao() {
	}

	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}
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

	public int getTotalCnt(int board_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		
		String sql = "SELECT COUNT(*) FROM post WHERE board_no = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();
			if (rs.next())
				tot = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		
		return tot;
	}

	public int delete(int board_no, int post_no, String passwd) throws SQLException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		String sql1 = "SELECT passwd FROM post WHERE post_no = ? AND board_no = ?";
		String sql2 = "DELETE FROM post WHERE post_no = ? AND board_no = ?";
		try {
			String dbPasswd = "";

			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, post_no);
			pstmt.setInt(2, board_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbPasswd = rs.getString(1);
				if (dbPasswd.equals(passwd)) {
					rs.close();
					pstmt.close();
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, post_no);
					pstmt.setInt(2, board_no);
					result = pstmt.executeUpdate();
				} else
					result = 0;
			} else
				result = -1;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		
		return result;
	}

}