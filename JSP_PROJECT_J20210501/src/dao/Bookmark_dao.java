package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Bookmark_dao {
	private static Bookmark_dao instance;

	private Bookmark_dao() {

	}

	public static Bookmark_dao getInstance() {
		if (instance == null)
			instance = new Bookmark_dao();

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

	public int getBookTotalCnt(String member_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int book_tot = 0;

		String sql = "SELECT COUNT(*) FROM bookmark WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();

			if (rs.next())
				book_tot = rs.getInt(1);

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
		
		return book_tot;
	}

	public List<Bookmark> book_list(int startRow, int endRow, String member_id) throws SQLException {
		List<Bookmark> list = new ArrayList<Bookmark>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * "
				   + "FROM (SELECT rownum rn, a.* "
				         + "FROM (SELECT p.post_title, m.nickname, p.hits, p.board_no, p.post_no, p.member_id, p.recommend, p.attach "
				         	   + "FROM bookmark b, post p, member m "
				         	   + "WHERE b.member_id = ? "
				         	   + "AND b.board_no = p.board_no "
				         	   + "AND b.POST_NO = p.post_no "
				         	   + "AND p.member_id = m.member_id) a) "
				   + "WHERE (rn BETWEEN ? AND ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();

			System.out.println("DAO list sql : " + sql);
			System.out.println("DAO list member_id : " + member_id);
			System.out.println("DAO list startRow : " + startRow);
			System.out.println("DAO list endRow : " + endRow);
			System.out.println();

			while (rs.next()) {
				Bookmark bookmark = new Bookmark();

				bookmark.setBoard_no(rs.getInt("board_no"));
				bookmark.setPost_no(rs.getInt("post_no"));
				bookmark.setMember_id(rs.getString("member_id"));
				bookmark.setPost_title(rs.getString("post_title"));
				bookmark.setNickname(rs.getString("nickname"));
				bookmark.setAttach(rs.getString("attach"));
				bookmark.setRecommend(rs.getInt("recommend"));
				bookmark.setNickname(rs.getString("nickname"));
				bookmark.setHits(rs.getInt("hits"));
				list.add(bookmark);

				System.out.println("DAO list board_no : " + rs.getString("board_no"));
				System.out.println("DAO list post_no : " + rs.getString("post_no"));
				System.out.println("DAO list member_id : " + rs.getString("member_id"));
				System.out.println("DAO list post_title : " + rs.getString("post_title"));
				System.out.println("DAO list attach : " + rs.getString("attach"));
				System.out.println("DAO list recommend : " + rs.getString("recommend"));
				System.out.println("DAO list nickname : " + rs.getString("nickname"));
				System.out.println("DAO list hits : " + rs.getInt("hits"));
				System.out.println();
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
		System.out.println();

		return list;
	}

}
