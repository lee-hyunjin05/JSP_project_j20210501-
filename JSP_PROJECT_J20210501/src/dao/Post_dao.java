package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Post_dao {
	private static Post_dao instance;

	private Post_dao() {

	}

	public static Post_dao getInstance() {
		if (instance == null)
			instance = new Post_dao();

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

	public Post select(int board_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Post post = new Post();

		String sql = "SELECT * FROM post WHERE board_no = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				post.setBoard_no(rs.getInt("board_no"));
				post.setPost_no(rs.getInt("post_no"));
				post.setCategory(rs.getString("category"));
				post.setPost_title(rs.getString("post_title"));
				post.setMember_id(rs.getString("member_id"));
				post.setContent(rs.getString("content"));
				post.setAttach(rs.getString("attach"));
				post.setPasswd(rs.getString("passwd"));
				post.setWrite_date(rs.getDate("write_date"));
				post.setHits(rs.getInt("hits"));
				post.setRecommend(rs.getInt("recommend"));
				post.setRef(rs.getInt("ref"));
				post.setRe_step(rs.getInt("re_step"));
				post.setRe_level(rs.getInt("re_level"));
			}
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
		
		return post;
	}
}
