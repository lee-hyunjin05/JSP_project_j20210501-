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

public class Mypost_dao {
	private static Mypost_dao instance;

	private Mypost_dao() {

	}

	public static Mypost_dao getInstance() {
		if (instance == null)
			instance = new Mypost_dao();

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

	public List<Mypost> mypost_list(int startRow, int endRow, String member_id) throws SQLException {
		List<Mypost> list = new ArrayList<Mypost>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * "
				   + "FROM (SELECT rownum rn, a.* "
				   		 + "FROM (SELECT p.post_title, m.nickname, p.hits, p.board_no, p.post_no, p.attach, p.recommend "
				   		 	   + "FROM post p, member m "
				   		 	   + "WHERE p.member_id = ? "
				   		 	   + "AND p.member_id = m.member_id "
				   		 	   + "AND re_step = 0"
				   		 	   + "ORDER BY write_date DESC) a) "
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

			while (rs.next()) {
				Mypost mypost = new Mypost();

				mypost.setBoard_no(rs.getInt("board_no"));
				mypost.setPost_no(rs.getInt("post_no"));
				mypost.setPost_title(rs.getString("post_title"));
				mypost.setAttach(rs.getString("attach"));
				mypost.setNickname(rs.getString("nickname"));
				mypost.setRecommend(rs.getInt("recommend"));
				mypost.setHits(rs.getInt("hits"));
				list.add(mypost);

				System.out.println("DAO list board_no : " + rs.getInt("board_no"));
				System.out.println("DAO list post_no : " + rs.getInt("post_no"));
				System.out.println("DAO list post_title : " + rs.getString("post_title"));
				System.out.println("DAO list attach : " + rs.getString("attach"));
				System.out.println("DAO list recommend : " + rs.getInt("recommend"));
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

	public int getPostTotalCnt(String member_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int post_tot = 0;
		
		String sql = "SELECT COUNT(*) FROM post WHERE member_id = ? AND re_step = 0";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				post_tot = rs.getInt(1);
			
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
		
		return post_tot;
	}

	public int getMyreplyTotalCnt(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int myreply_tot = 0;
		
		String sql = "SELECT COUNT(*) FROM post WHERE member_id = ? AND re_level != 0 and re_step != 0 AND content != '삭제된 글입니다.'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				myreply_tot = rs.getInt(1);
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
		
		return myreply_tot;
	}

	public List<Mypost> myreply_list(int startRow, int endRow, String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Mypost> list = new ArrayList<Mypost>();
		
		String sql = "SELECT * "
				   + "FROM (SELECT rownum rn, a.* "
				   		 + "FROM (SELECT p.post_title, m.nickname, p.hits, p.post_no, p.board_no, p.ref ,p.content "
				   		 	   + "FROM post p, member m "
				   		 	   + "WHERE p.member_id = ? "
				   		 	   + "AND re_level != 0 "
				   		 	   + "AND re_step! = 0 "
				   		 	   + "AND p.member_id = m.member_id "
				   		 	   + "AND p.content != '삭제된 글입니다.' "
				   		 	   + "ORDER BY write_date DESC) a ) "
				   + "WHERE (rn BETWEEN ? AND ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			System.out.println("DAO list sql : " + sql);
			System.out.println("DAO list id : " + id);
			System.out.println("DAO list startRow : " + startRow);
			System.out.println("DAO list endRow : " + endRow);

			while (rs.next()) {
				Mypost mypost = new Mypost();

				mypost.setPost_title(rs.getString("post_title"));
				mypost.setNickname(rs.getString("nickname"));
				mypost.setHits(rs.getInt("hits"));
				mypost.setBoard_no(rs.getInt("board_no"));
				mypost.setPost_no(rs.getInt("post_no"));
				mypost.setContent(rs.getString("content"));
				mypost.setRef(rs.getInt("ref"));
				list.add(mypost);
				
				System.out.println("DAO list post_title : " + rs.getString("post_title"));
				System.out.println("DAO list nickname : " + rs.getString("nickname"));
				System.out.println("DAO list hits : " + rs.getInt("hits"));
				System.out.println("DAO list board_no : " + rs.getInt("board_no"));
				System.out.println("DAO list post_no : " + rs.getInt("post_no"));
				System.out.println("DAO list content : " + rs.getString("content"));
				System.out.println("DAO list ref : " + rs.getInt("ref"));
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
		
		return list;
	}
}
