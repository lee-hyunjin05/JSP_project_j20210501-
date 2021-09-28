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

public class Member_dao {
	private static Member_dao instance;

	private Member_dao() {

	}

	public static Member_dao getInstance() {
		if (instance == null)
			instance = new Member_dao();

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

	public int check(String id, String pw) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;

		String sql = "SELECT member_pw FROM member WHERE member_id = ? AND withdrawal = 'N'";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println("check pw : " + pw);

			if (rs.next()) {
				String DBpasswd = rs.getString(1);

				System.out.println("check DBpasswd : " + DBpasswd);

				if (DBpasswd.equals(pw))
					result = 1;
				else
					result = 0;
			} else {
				result = -1;
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
		System.out.println("result : " + result);
		System.out.println();

		return result;
	}

	public String find_id(String name, String birth, String tel) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String member_id = "";

		String sql = "SELECT member_id FROM member WHERE member_name = ? AND birth = ? AND tel = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, tel);
			rs = pstmt.executeQuery();

			System.out.println("find_id name : " + name);
			System.out.println("find_id birth : " + birth);
			System.out.println("fing_id tel : " + tel);

			if (rs.next()) {
				member_id = rs.getString(1);

				System.out.println("find_id member_id : " + member_id);
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

		return member_id;
	}

	public String find_pw(String id, String name, String birth, String tel) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String member_pw = "";

		String sql = "SELECT member_pw FROM member WHERE member_id = ? AND member_name = ? AND birth = ? AND tel = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, birth);
			pstmt.setString(4, tel);
			rs = pstmt.executeQuery();

			System.out.println("find_pw id : " + id);
			System.out.println("find_pw name : " + name);
			System.out.println("find_pw birth : " + birth);
			System.out.println("find_pw tel : " + tel);

			if (rs.next()) {
				member_pw = rs.getString(1);

				System.out.println("find_pw member_pw : " + member_pw);
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

		return member_pw;
	}

	public int confirm_id(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		String sql = "SELECT member_id FROM member WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println("confirm_id id : " + id);

			if (rs.next())
				result = 1;
			else
				result = 0;
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
		System.out.println("result : " + result);
		System.out.println();

		return result;
	}

	public int confirm_nick(String nickname) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		String sql = "SELECT nickname FROM member WHERE nickname = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();

			System.out.println("confirm_nick nickname : " + nickname);

			if (rs.next())
				result = 1;
			else
				result = 0;
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
		System.out.println("result : " + result);
		System.out.println();

		return result;
	}

	public int insert(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		String sql1 = "INSERT INTO member VALUES (?, ?, ?, ?, NULL, ?, TO_DATE(?, 'RR/MM/DD'), ?, ?, sysdate, 0, 'N')";
		String sql2 = "INSERT INTO auth_user VALUES (?, 2)";
		String sql3 = "INSERT INTO title_user VALUES (?, 5)";
		int cnt1 = 0;
		int cnt2 = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getMember_pw());
			pstmt.setString(3, member.getMember_name());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getNickname());
			pstmt.setString(6, member.getbirth());
			pstmt.setString(7, member.getTel());
			pstmt.setString(8, member.getIntro());

			cnt1 = pstmt.executeUpdate();
			System.out.println("cnt1 : " + cnt1);

			if (cnt1 != 0) {
				pstmt.close();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, member.getMember_id());

				cnt2 = pstmt.executeUpdate();
				System.out.println("cnt2 : " + cnt2);
				
				if (cnt2 != 0) {
					pstmt.close();
					pstmt = conn.prepareStatement(sql3);
					pstmt.setString(1, member.getMember_id());

					result = pstmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		System.out.println("result : " + result);
		System.out.println();

		return result;
	}
	
	public Member select(String userID) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = new Member();

		String sql = "SELECT * FROM member WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setMember_id(rs.getString(1));
				member.setMember_pw(rs.getString(2));
				member.setMember_name(rs.getString(3));
				member.setGender(rs.getString(4));
				member.setProfile_img(rs.getString(5));
				member.setNickname(rs.getString(6));
				member.setbirth(String.format("%1$tY-%1$tm-%1$td", rs.getDate(7)));
				member.setTel(rs.getString(8));
				member.setIntro(rs.getString(9));
				member.setReg_date(rs.getDate(10));
				member.setRec(rs.getInt(11));
				member.setWithdrawal(rs.getString(12));
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

		return member;
	}

	public int update(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;

		String sql = "UPDATE member SET member_name = ?, profile_img = ?, nickname = ?, tel = ?, intro = ? WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMember_name());
			pstmt.setString(2, member.getProfile_img());
			pstmt.setString(3, member.getNickname());
			pstmt.setString(4, member.getTel());
			pstmt.setString(5, member.getIntro());
			pstmt.setString(6, member.getMember_id());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		System.out.println("result : " + result);
		System.out.println();

		return result;
	}

	public int repasswd(String id, String cur_pw, String new_pw1, String new_pw2) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -2;

		String sql1 = "SELECT member_pw FROM member WHERE member_id = ?";
		String sql2 = "UPDATE member SET member_pw = ? WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println("repasswd id : " + id);
			System.out.println("repasswd cur_pw : " + cur_pw);
			System.out.println("repasswd new_pw1 : " + new_pw1);
			System.out.println("repasswd new_pw2 : " + new_pw2);

			if (rs.next()) {
				String DBpasswd = rs.getString(1);

				System.out.println("repasswd DBpasswd : " + DBpasswd);

				if (DBpasswd.equals(cur_pw)) {
					rs.close();
					pstmt.close();

					if (new_pw1.equals(new_pw2)) {
						pstmt = conn.prepareStatement(sql2);
						pstmt.setString(1, new_pw1);
						pstmt.setString(2, id);
						result = pstmt.executeUpdate();
					} else {
						result = 0;
					}
				} else {
					result = -1;
				}
			} else {
				result = -2;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
			}
		System.out.println("result : " + result);
		System.out.println();
		
		
		
		return result;
	}

	public int withdraw(String id, String pw) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;

		String sql1 = "SELECT member_pw FROM member WHERE member_id = ?";
		String sql2 = "UPDATE member SET withdrawal = 'Y' WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println("withdraw id : " + id);
			System.out.println("withdraw pw : " + pw);

			if (rs.next()) {
				String DBpasswd = rs.getString(1);

				System.out.println("withdraw DBpasswd : " + DBpasswd);

				if (DBpasswd.equals(pw)) {
					rs.close();
					pstmt.close();

					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, id);
					result = pstmt.executeUpdate();
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
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
		System.out.println("result : " + result);
		System.out.println();

		return result;
	}

	public int restore(String id, String pw) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;

		String sql1 = "SELECT member_pw FROM member WHERE member_id = ?";
		String sql2 = "UPDATE member SET withdrawal = 'N' WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println("restore id : " + id);
			System.out.println("restore pw : " + pw);

			if (rs.next()) {
				String DBpasswd = rs.getString(1);

				System.out.println("restore DBpasswd : " + DBpasswd);

				if (DBpasswd.equals(pw)) {
					rs.close();
					pstmt.close();

					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, id);
					result = pstmt.executeUpdate();
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
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
		System.out.println("result : " + result);
		System.out.println();

		return result;
	}

	public int a_user(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;

		String sql = "SELECT auth_no FROM auth_user WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println("a_user id : " + id);

			if (rs.next()) {
				int auth_no = rs.getInt(1);

				System.out.println("a_user auth_no : " + auth_no);

				result = auth_no;
			}
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
		
		System.out.println("result : " + result);
		System.out.println();

		
		return result;
	}
	
	public int t_user(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;

		String sql = "SELECT title_no FROM title_user WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println("t_user id : " + id);

			if (rs.next()) {
				int title_no = rs.getInt(1);

				System.out.println("t_user title_no : " + title_no);

				result = title_no;
			}
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
		System.out.println("result : " + result);
		System.out.println();

		return result;
	}

	public int getTotalCnt() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;

		String sql = "SELECT COUNT(*) FROM member";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				tot = rs.getInt(1);
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
		System.out.println("tot : " + tot);

		return tot;
	}

	public List<Member> list(int startRow, int endRow) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Member> al = new ArrayList<Member>();

		String sql = "SELECT * FROM (SELECT rownum rn, a.* FROM (SELECT * FROM member ORDER BY reg_date DESC) a) WHERE rn BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member member = new Member();

				member.setMember_id(rs.getString("member_id"));
				member.setMember_name(rs.getString("member_name"));
				member.setGender(rs.getString("gender"));
				member.setNickname(rs.getString("nickname"));
				member.setbirth(String.format("%1$tY-%1$tm-%1$td", rs.getDate("birth")));
				member.setTel(rs.getString("tel"));
				member.setReg_date(rs.getDate("reg_date"));
				member.setRec(rs.getInt("rec"));
				member.setWithdrawal(rs.getString("withdrawal"));
				al.add(member);
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

		return al;
	}

	public int rec_update(String member_id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rec_tot = 0;
		
		String sql1 = "SELECT SUM(recommend) FROM post WHERE member_id = ?";
		String sql2 = "UPDATE member SET rec = ? WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rec_tot = rs.getInt(1);
				
				rs.close();
				pstmt.close();
				
				System.out.println("DAO_rec_tot : " + rec_tot);
				System.out.println();
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, rec_tot);
				pstmt.setString(2, member_id);
				pstmt.executeUpdate();
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
		
		return rec_tot;
	}

	public int title_update(String member_id) throws SQLException {
		Connection conn = null;	
		PreparedStatement pstmt= null; 
		ResultSet rs = null;
		int rec_tot = 0;
		int title_grade=0;
		
		String sql1="SELECT rec FROM member WHERE member_id = ?";
		String sql2="UPDATE title_user SET title_no = ? WHERE member_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			 
			if(rs.next()) {
				rec_tot=rs.getInt(1);
				
				rs.close();
				pstmt.close();
				
				System.out.println("DAO_rec_tot : " + rec_tot);
				System.out.println();
				
				if (rec_tot > 250)
					title_grade = 2;
				else if (rec_tot > 90 && rec_tot <= 250)
					title_grade = 3;
				else if (rec_tot > 30 && rec_tot <= 90)
					title_grade = 4;
				else
					title_grade = 5;
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, title_grade);
				pstmt.setString(2, member_id);
				pstmt.executeUpdate();
			}
		} catch(Exception e) {	
			System.out.println(e.getMessage()); 
		} finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}
		
		return title_grade;
	}

}
