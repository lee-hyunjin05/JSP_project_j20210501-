package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import service_board.DeduplicationUtils;

public class PostMember_dao {
	private static PostMember_dao instance;

	private PostMember_dao() {

	}

	public static PostMember_dao getInstance() {
		if (instance == null)
			instance = new PostMember_dao();

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

	// 일반 게시판3종 글 리스트
	public List<PostMember> list(int startRow, int endRow, int board_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PostMember> list = new ArrayList<PostMember>();

		String sql = "SELECT * "
				   + "FROM (SELECT rownum rn, a.* "
				   		 + "FROM (SELECT * "
				   		 	   + "FROM post NATURAL JOIN member "
				   		 	   + "WHERE board_no = ? "
				   		 	   + "AND ref = post_no "
				   		 	   + "ORDER BY ref DESC, re_step) a ) "
				   + "WHERE (rn BETWEEN ? AND ?) AND board_no = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			pstmt.setInt(4, board_no);
			rs = pstmt.executeQuery();
			
			System.out.println("DAO list sql : " + sql);
			System.out.println("DAO list startRow : " + startRow);
			System.out.println("DAO list endRow : " + endRow);
			System.out.println("DAO list board_no : " + board_no);
			System.out.println();

			while (rs.next()) {

				PostMember postmember = new PostMember();

				postmember.setMember_id(rs.getString("member_id"));
				postmember.setBoard_no(rs.getInt("board_no"));
				postmember.setPost_no(rs.getInt("post_no"));
				postmember.setPost_title(rs.getString("post_title"));
				postmember.setNickname(rs.getString("nickname"));
				postmember.setHits(rs.getInt("hits"));
				postmember.setAttach(rs.getString("attach"));
				postmember.setRecommend(rs.getInt("recommend"));
				postmember.setRef(rs.getInt("ref"));
				postmember.setRe_step(rs.getInt("re_step"));
				postmember.setRe_level(rs.getInt("re_level"));
				list.add(postmember);
				
				System.out.println("DAO list member_id : " + rs.getString("member_id"));
				System.out.println("DAO list board_no : " + rs.getInt("board_no"));
				System.out.println("DAO list post_no : " + rs.getInt("post_no"));
				System.out.println("DAO list post_title : " + rs.getString("post_title"));
				System.out.println("DAO list nickname : " + rs.getString("nickname"));
				System.out.println("DAO list hits : " + rs.getInt("hits"));
				System.out.println("DAO list attach : " + rs.getString("attach"));
				System.out.println("DAO list recommend : " + rs.getInt("recommend"));
				System.out.println("DAO list ref : " + rs.getInt("ref"));
				System.out.println("DAO list re_step : " + rs.getInt("re_step"));
				System.out.println("DAO list re_level : " + rs.getInt("re_level"));
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

	// 일반 게시판(3종) 리스트 갯수
	public int getTotalCnt(int board_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;
		
		String sql = "SELECT COUNT(*) FROM post WHERE board_no = ? AND re_step = 0";
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

	// 조회수 기능
	public void hits(int board_no, int post_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE post SET hits = hits + 1 WHERE board_no = ? AND post_no = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.setInt(2, post_no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		
		return;
	}

	// 추천 기능
	public int recommend(String member_id, int board_no, int post_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;

		String sql1 = "SELECT * FROM recommend WHERE member_id = ? AND board_no = ? AND post_no = ?";
		String sql2 = "INSERT INTO recommend (member_id, board_no, post_no) VALUES (?, ?, ?)";
		String sql3 = "UPDATE post SET recommend = recommend + 1 WHERE board_no = ? AND post_no = ?";
		String sql4 = "DELETE FROM recommend WHERE member_id = ? AND board_no = ? AND post_no = ?";
		String sql5 = "UPDATE post SET recommend = recommend -1 WHERE board_no = ? AND post_no = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, board_no);
			pstmt.setInt(3, post_no);
			rs = pstmt.executeQuery();

			// 이미 추천이 되어있다면 추천 취소
			if (rs.next()) {
				rs.close();
				pstmt.close();
				pstmt = conn.prepareStatement(sql4);
				pstmt.setString(1, member_id);
				pstmt.setInt(2, board_no);
				pstmt.setInt(3, post_no);
				int result = pstmt.executeUpdate();
				if (result != 0) {
					pstmt.close();
					pstmt = conn.prepareStatement(sql5);
					pstmt.setInt(1, board_no);
					pstmt.setInt(2, post_no);
					int result2 = pstmt.executeUpdate();
					if (result2 != 0)
						check = 0;
				}
			} else {
				rs.close();
				pstmt.close();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, member_id);
				pstmt.setInt(2, board_no);
				pstmt.setInt(3, post_no);
				int result = pstmt.executeUpdate();
				if (result != 0) {
					pstmt.close();
					pstmt = conn.prepareStatement(sql3);
					pstmt.setInt(1, board_no);
					pstmt.setInt(2, post_no);
					int result2 = pstmt.executeUpdate();
					if (result2 != 0)
						check = 1;
				}
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
		
		return check;
	}

	// 게시글
	public PostMember select(int board_no, int post_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostMember postmember = new PostMember();

		String sql = "SELECT * FROM post WHERE board_no = ? and post_no = ? ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.setInt(2, post_no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				postmember.setBoard_no(rs.getInt("board_no"));
				postmember.setPost_no(rs.getInt("post_no"));
				postmember.setCategory(rs.getString("category"));
				postmember.setPost_title(rs.getString("post_title"));
				postmember.setMember_id(rs.getString("member_id"));
				postmember.setContent(rs.getString("content"));
				postmember.setAttach(rs.getString("attach"));
				postmember.setPasswd(rs.getString("passwd"));
				postmember.setWrite_date(rs.getDate("write_date"));
				postmember.setHits(rs.getInt("hits"));
				postmember.setRecommend(rs.getInt("recommend"));
				postmember.setRef(rs.getInt("ref"));
				postmember.setRe_step(rs.getInt("re_step"));
				postmember.setRe_level(rs.getInt("re_level"));
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

		return postmember;
	}

	// 글 수정
	public int update(PostMember postmember) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "UPDATE post "
				   + "SET post_title = ?, category = ?, content = ?, attach = ?, passwd = ? "
				   + "WHERE board_no = ? "
				   + "AND post_no = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postmember.getPost_title());
			pstmt.setString(2, postmember.getCategory());
			pstmt.setString(3, postmember.getContent());
			pstmt.setString(4, postmember.getAttach());
			pstmt.setString(5, postmember.getPasswd());
			pstmt.setInt(6, postmember.getBoard_no());
			pstmt.setInt(7, postmember.getPost_no());

			result = pstmt.executeUpdate();
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

	// 글 삭제
	public int delete(int board_no, int post_no, String passwd, int re_step) throws SQLException, IOException {
		System.out.println("delete 들어옴");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;

		String sql1 = "SELECT passwd FROM post WHERE board_no = ? AND post_no = ?";
		String sql2 = "DELETE FROM bookmark WHERE board_no = ? AND post_no = ?";
		String sql3 = "DELETE FROM recommend WHERE board_no = ? AND post_no = ?";
		String sql4 = "DELETE FROM post WHERE board_no = ? AND post_no = ?";
		String sql5 = "UPDATE post SET content = '삭제된 글입니다.' WHERE board_no = ? AND post_no = ?";
		try {
			System.out.println("try 들어옴");
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, board_no);
			pstmt.setInt(2, post_no);
			rs = pstmt.executeQuery();

			String DBPasswd = "";
			if (rs.next()) {
				DBPasswd = rs.getString(1);
				
				int check1 = -1;
				int check2 = -1;
				if (DBPasswd.equals(passwd)) {
					rs.close();
					pstmt.close();
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, board_no);
					pstmt.setInt(2, post_no);
					check1 = pstmt.executeUpdate();
					
					System.out.println("check1 수행 : " + check1);
					
					rs.close();
					pstmt.close();
					pstmt = conn.prepareStatement(sql3);
					pstmt.setInt(1, board_no);
					pstmt.setInt(2, post_no);
					check2 = pstmt.executeUpdate();
					
					System.out.println("check2 수행 : " + check2);
					
					if (check1 != -1 && check2 != -1) {
						if (re_step == 0) {
							System.out.println("댓글시...");
							pstmt = conn.prepareStatement(sql4);
							pstmt.setInt(1, board_no);
							pstmt.setInt(2, post_no);
							result = pstmt.executeUpdate();
						} else {
							System.out.println("대댓글시...");
							pstmt = conn.prepareStatement(sql5);
							pstmt.setInt(1, board_no);
							pstmt.setInt(2, post_no);
							result = pstmt.executeUpdate();
						}
					} else {
						pstmt = conn.prepareStatement(sql4);
						pstmt.setInt(1, board_no);
						pstmt.setInt(2, post_no);
						result = pstmt.executeUpdate();
					}
				} else
					result = 0;
			} else
				result = -1;
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
		
		return result;
	}

	// 글 작성
	public int insert(PostMember postmember) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int board_no = postmember.getBoard_no();
		int post_no = postmember.getPost_no();
		int result = 0;

		// 게시글
		String sql1 = "SELECT NVL(MAX(post_no), 0) FROM post WHERE board_no = ?";
		String sql2 = "INSERT INTO post (board_no, post_no, category, post_title, member_id, content, attach, passwd, write_date, hits, recommend, ref, re_step, re_level) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?)";
		// 댓글
		String sql3 = "UPDATE post SET re_step = re_step + 1 WHERE ref = ? AND re_step > ?";
		try {
			conn = getConnection();

			// 댓글
			if (post_no != 0) {
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, postmember.getRef());
				pstmt.setInt(2, postmember.getRe_step());
				pstmt.executeUpdate();
				pstmt.close();
				
				postmember.setRe_step(postmember.getRe_step() + 1);
				postmember.setRe_level(postmember.getRe_level() + 1);
			}

			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, postmember.getBoard_no());
			System.out.println("Post_dao sql1 post.getBoard_no()->" + postmember.getBoard_no());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				int post_no_num = rs.getInt(1) + 1;
				System.out.println("Post_dao sql1 number : " + post_no_num);
				if (post_no == 0)
					postmember.setRef(post_no_num);
				
				rs.close();
				pstmt.close();
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, board_no);
				pstmt.setInt(2, post_no_num);
				pstmt.setString(3, postmember.getCategory());
				pstmt.setString(4, postmember.getPost_title());
				pstmt.setString(5, postmember.getMember_id());
				pstmt.setString(6, postmember.getContent());
				pstmt.setString(7, postmember.getAttach());
				pstmt.setString(8, postmember.getPasswd());
				pstmt.setInt(9, postmember.getHits());
				pstmt.setInt(10, postmember.getRecommend());
				pstmt.setInt(11, postmember.getRef());
				pstmt.setInt(12, postmember.getRe_step());
				pstmt.setInt(13, postmember.getRe_level());
				result = pstmt.executeUpdate();
				
				System.out.println("board_no : " + board_no);
				System.out.println("post_no : " + post_no_num);
				System.out.println("getCategory : " + postmember.getCategory());
				System.out.println("getPost_title : " + postmember.getPost_title());
				System.out.println("getMember_id : " + postmember.getMember_id());
				System.out.println("getContent : " + postmember.getContent());
				System.out.println("getAttach : " + postmember.getAttach());
				System.out.println("getPasswd : " + postmember.getPasswd());
				System.out.println("getHits : " + postmember.getHits());
				System.out.println("getRecommend : " + postmember.getRecommend());
				System.out.println("getRef : " + postmember.getRef());
				System.out.println("getRe_step : " + postmember.getRe_step());
				System.out.println("getRe_level : " + postmember.getRe_level());
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
		
		return result;
	}

	// 즐겨찾기
	public int bookmark(int board_no, int post_no, String member_id) throws SQLException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = -1;

		String sql1 = "SELECT * FROM bookmark WHERE member_id = ? AND board_no = ? AND post_no = ?";
		String sql2 = "INSERT INTO BOOKMARK (member_id, board_no, post_no, book_date) VALUES (?, ?, ?, sysdate)";
		String sql3 = "DELETE FROM bookmark WHERE member_id = ? AND board_no = ? AND post_no= ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, board_no);
			pstmt.setInt(3, post_no);
			rs = pstmt.executeQuery();
			
			// 이미 즐겨찾기를 해놓았다면 즐겨찾기 취소
			if (rs.next()) {
				rs.close();
				pstmt.close();
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, member_id);
				pstmt.setInt(2, board_no);
				pstmt.setInt(3, post_no);
				int result = pstmt.executeUpdate();
				if (result != 0)
					check = 0;
			} else {
				pstmt.close();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, member_id);
				pstmt.setInt(2, board_no);
				pstmt.setInt(3, post_no);
				int result = pstmt.executeUpdate();
				if (result != 0)
					check = 1;
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

		return check;
	}

	// 베스트 게시판 리스팅 액션
	public List<PostMember> bestlist(int startRow, int endRow) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PostMember> bestlist = new ArrayList<PostMember>();

		String sql = "SELECT * "
				   + "FROM (SELECT rownum rn, a.* "
				   		 + "FROM (SELECT * "
				   		 	   + "FROM post NATURAL JOIN member "
				   		 	   + "WHERE recommend >= 20 "
				   		 	   + "ORDER BY recommend DESC, ref, re_step) a ) "
				   + "WHERE (rn BETWEEN ? AND ?) ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			System.out.println("DAO list sql->" + sql);
			System.out.println("DAO list startRow->" + startRow);
			System.out.println("DAO list endRow->" + endRow);
			System.out.println();			

			while (rs.next()) {
				PostMember postmember2 = new PostMember();

				postmember2.setMember_id(rs.getString("member_id"));
				postmember2.setBoard_no(rs.getInt("board_no"));
				postmember2.setPost_no(rs.getInt("post_no"));
				postmember2.setPost_title(rs.getString("post_title"));
				postmember2.setHits(rs.getInt("hits"));
				postmember2.setRecommend(rs.getInt("recommend"));
				postmember2.setWrite_date(rs.getDate("write_date"));
				postmember2.setAttach(rs.getString("attach"));
				postmember2.setRef(rs.getInt("ref"));
				postmember2.setRe_step(rs.getInt("re_step"));
				postmember2.setRe_level(rs.getInt("re_level"));
				postmember2.setNickname(rs.getString("nickname"));
				bestlist.add(postmember2);
				
				System.out.println("DAO list member_id : " + rs.getString("member_id"));
				System.out.println("DAO list board_no : " + rs.getInt("board_no"));
				System.out.println("DAO list post_no : " + rs.getInt("post_no"));
				System.out.println("DAO list post_title : " + rs.getString("post_title"));
				System.out.println("DAO list hits : " + rs.getInt("hits"));
				System.out.println("DAO list recommend : " + rs.getInt("recommend"));
				System.out.println("DAO list ref : " + rs.getInt("ref"));
				System.out.println("DAO list re_step : " + rs.getInt("re_step"));
				System.out.println("DAO list re_level : " + rs.getInt("re_level"));
				System.out.println("DAO list nickname : " + rs.getString("nickname"));
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
		
		return bestlist;
	}

	// 베스트게시판 글 갯수 Count
	public int getBestTotalCnt() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int besttot = 0;
		
		String sql = "SELECT COUNT(*) FROM post WHERE recommend >= 20";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				besttot = rs.getInt(1);
			
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
		
		return besttot;
	}

	// 통합검색 액션
	public List<PostMember> search(int startRow, int endRow, int board_no, String search_category, String search_text)
			throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PostMember> searchlist = new ArrayList<PostMember>();
		List<PostMember> disticnt_searchlist = new ArrayList<PostMember>();
		
		String sql = "SELECT * "
				   + "FROM (SELECT rownum rn, a.* "
				   		 + "FROM (SELECT * "
				   		 	   + "FROM post NATURAL JOIN member "
				   		 	   + "WHERE board_no = ? "
				   		 	   + "AND category LIKE '%' || ? || '%' "
				   		 	   + "AND (post_title LIKE '%' || ? || '%' OR content LIKE '%' || ? || '%') "
				   		 	   + "ORDER BY ref DESC, re_step) a ) "
				   + "WHERE (rn BETWEEN ? AND ?) "
				   + "AND board_no = ? ";
		try {
			System.out.println("DAO search search_category : " + search_category);
			String[] tokens = search_category.split(" ");
			for (int i = 0; i < tokens.length; i++)
				System.out.println("tokens[" + i + "] = " + tokens[i]);
			System.out.println();
			System.out.println("DAO search sql : " + sql);
			System.out.println("DAO search board_no : " + board_no);
			System.out.println("DAO search search_category : " + search_category);
			System.out.println("DAO search search_text : " + search_text);
			System.out.println("DAO search startRow : " + startRow);
			System.out.println("DAO search endRow : " + endRow);
			System.out.println();
			
			conn = getConnection();
			
			for (int i = 0; i < tokens.length; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board_no);
				pstmt.setString(2, tokens[i]);
				pstmt.setString(3, search_text);
				pstmt.setString(4, search_text);
				pstmt.setInt(5, startRow);
				pstmt.setInt(6, endRow);
				pstmt.setInt(7, board_no);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					PostMember postmember = new PostMember();
					
					postmember.setPost_title(rs.getString("post_title"));
					postmember.setBoard_no(rs.getInt("board_no"));
					postmember.setPost_no(rs.getInt("post_no"));
					postmember.setHits(rs.getInt("hits"));
					postmember.setNickname(rs.getString("nickname"));
					postmember.setAttach(rs.getString("attach"));
					searchlist.add(postmember);
					
					System.out.println("DAO list post_title : " + rs.getString("post_title"));
					System.out.println("DAO list board_no : " + rs.getInt("board_no"));
					System.out.println("DAO list post_no : " + rs.getInt("post_no"));
					System.out.println("DAO list hits : " + rs.getInt("hits"));
					System.out.println("DAO list nickname : " + rs.getString("nickname"));
					System.out.println("DAO list attach : " + rs.getString("attach"));
					System.out.println();
				}
				disticnt_searchlist = DeduplicationUtils.deduplication(searchlist, PostMember::getPost_no);
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
		
		return disticnt_searchlist;
	}

	// 통합검색 글 리스팅 갯수 count
	public int getSearchTotalCnt(int board_no, String search_category, String search_text) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) "
				   + "FROM post "
				   + "WHERE board_no = ? " 
				   + "AND category LIKE '%' || ? || '%' "
				   + "AND (post_title LIKE '%' || ? || '%' OR content like '%' || ? || '%')";
		try {
			System.out.println("DAO search search_category : " + search_category);
			String[] tokens = search_category.split(" ");
			for (int i = 0; i < tokens.length; i++)
				System.out.println("tokens[" + i + "] = " + tokens[i]);
			System.out.println();
			System.out.println("getSearchTotalCnt board_no : " + board_no);
			System.out.println("getSearchTotalCnt search_category : " + search_category);
			System.out.println("getSearchTotalCnt search_text : " + search_text);
			System.out.println();
			
			conn = getConnection();
			
			for (int i = 0; i < tokens.length; i++) {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, board_no);
				pstmt.setString(2, tokens[i]);
				pstmt.setString(3, search_text);
				pstmt.setString(4, search_text);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					result = rs.getInt(1);
					cnt = cnt + 1;
				}
				
				System.out.println("getSearchTotalCnt result" + i + " : " + result);
			}
			System.out.println();
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
		
		return cnt;
	}

	// 댓글 리스트, int startRow, int endRow
	public List<PostMember> comment_list(int board_no, int post_no) throws SQLException {
		List<PostMember> comment_list = new ArrayList<PostMember>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * "
				   + "FROM (SELECT rownum rn, a.* "
				   		 + "FROM (SELECT * "
				   		 	   + "FROM post NATURAL JOIN member "
				   		 	   + "WHERE board_no=? "
				   		 	   + "AND ref = ? "
				   		 	   + "ORDER BY re_step, re_level DESC) a ) "
				   + "WHERE re_step > 0 ";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.setInt(2, post_no);
			rs = pstmt.executeQuery();
			
			System.out.println("DAO list sql : " + sql);
			System.out.println("DAO list board_no : " + board_no);
			System.out.println("DAO list post_no : " + post_no);

			while (rs.next()) {
				PostMember postmember = new PostMember();

				postmember.setBoard_no(rs.getInt("board_no"));
				postmember.setPost_no(rs.getInt("post_no"));
				postmember.setPost_title(rs.getString("post_title"));
				postmember.setHits(rs.getInt("hits"));
				postmember.setRecommend(rs.getInt("recommend"));
				postmember.setRef(rs.getInt("ref"));
				postmember.setRe_step(rs.getInt("re_step"));
				postmember.setRe_level(rs.getInt("re_level"));
				postmember.setContent(rs.getString("content"));
				postmember.setNickname(rs.getString("nickname"));
				comment_list.add(postmember);
				
				System.out.println("DAO list board_no : " + rs.getInt("board_no"));
				System.out.println("DAO list post_no : " + rs.getInt("post_no"));
				System.out.println("DAO list post_title : " + rs.getString("post_title"));
				System.out.println("DAO list hits : " + rs.getInt("hits"));
				System.out.println("DAO list recommend : " + rs.getInt("recommend"));
				System.out.println("DAO list ref : " + rs.getInt("ref"));
				System.out.println("DAO list re_step : " + rs.getInt("re_step"));
				System.out.println("DAO list re_level : " + rs.getInt("re_level"));
				System.out.println("DAO list content : " + rs.getString("content"));
				System.out.println("DAO list nickname : " + rs.getString("nickname"));
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
		
		return comment_list;
	}

	// 댓글 갯수 카운트
	public int getTotalReplyCnt(int board_no, int post_no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int tot = 0;
		String sql = "SELECT COUNT(*) FROM post WHERE board_no = ? AND ref= ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			pstmt.setInt(2, post_no);
			rs = pstmt.executeQuery();

			if (rs.next())
				tot = rs.getInt(1) - 1; // 원글은 제외하고 댓글 갯수만 세야 하므로 -1
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

}
