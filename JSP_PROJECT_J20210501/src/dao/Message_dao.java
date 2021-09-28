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

public class Message_dao {
	private static Message_dao instance;

	private Message_dao() {

	}

	public static Message_dao getInstance() {
		if (instance == null)
			instance = new Message_dao();

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

	public int getDMTotalCnt(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int tot = 0;

		String sql = "SELECT COUNT(*) FROM message WHERE receiver_id = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
		System.out.println("tot : " + tot);
		System.out.println();
		
		return tot;
	}

	public List<Message> list(int startRow, int endRow, String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Message> list = new ArrayList<Message>();
		
		String sql = "SELECT * "
				   + "FROM (SELECT rownum rn, a.* "
				   		 + "FROM (SELECT * "
				   		 	   + "FROM message me, member mb "
				   		 	   + "WHERE mb.member_id = me.sender_id "
				   		 	   + "AND receiver_id = ? "
				   		 	   + "ORDER BY send_time desc) a ) "
				   + "WHERE (rn BETWEEN ? AND ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			System.out.println("DAO list sql : "+sql);
			System.out.println("DAO list receiver_id : " + id);
			System.out.println("DAO list startRow : " + startRow);
			System.out.println("DAO list endRow : " + endRow);
			System.out.println();
			
			while (rs.next()) {
				Message message = new Message();

				message.setSender_id(rs.getString("sender_id"));
				message.setReceiver_id(rs.getString("receiver_id"));
				message.setNickname(rs.getString("nickname"));
				message.setContent(rs.getString("content"));
				message.setSend_time(rs.getDate("send_time"));
				list.add(message);
				
				System.out.println("DAO list sender_id : " + rs.getString("sender_id"));
				System.out.println("DAO list receiver_id : " + rs.getString("receiver_id"));
				System.out.println("DAO list nickname : " + rs.getString("nickname"));
				System.out.println("DAO list content : " + rs.getString("content"));
				System.out.println("DAO list send_time : " + rs.getDate("send_time"));
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

	public int dminsert(Message message) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		System.out.println("try 전");
		
		String sql = "INSERT INTO MESSAGE (sender_id, receiver_id, message_num, content, send_time) VALUES (?, ?, MESSAGE_SEQ.NEXTVAL, ?, sysdate)";
		try {
			System.out.println("try 안");
			
			conn =getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getSender_id());
			pstmt.setString(2, message.getReceiver_id());
			pstmt.setString(3, message.getContent());
			result = pstmt.executeUpdate();
			
			System.out.println("result : " + result);
			
			System.out.println("MessageDAO sql : " + sql);			
			System.out.println("MessageDAO insert board.getSender_id() : " + message.getSender_id());
			System.out.println("MessageDAO insert board.getReceiver_id() : " + message.getReceiver_id());
			System.out.println("MessageDAO insert board.getContent() : " + message.getContent());										
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
		
		return result;
	}
}
