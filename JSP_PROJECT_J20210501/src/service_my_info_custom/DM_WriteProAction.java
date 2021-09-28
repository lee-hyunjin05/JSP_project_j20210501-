package service_my_info_custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import dao.Message;
import dao.Message_dao;
import service.CommandProcess;

public class DM_WriteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DM_WriteProAction Start...");

		try {
			request.setCharacterEncoding("utf-8");
			String id = (String) request.getSession().getAttribute("id");
			String sender_id = request.getParameter("sender_id");
			String receiver_id = request.getParameter("receiver_id");
			String content = request.getParameter("content");
			
			Message_dao md = Message_dao.getInstance();
			Message message = new Message();

			message.setSender_id(sender_id);
			message.setReceiver_id(receiver_id);
			message.setContent(content);
			int result = md.dminsert(message);

			Member_dao mmd = Member_dao.getInstance();			
			Member receiver_member = mmd.select(request.getParameter("receiver_id"));
			
			System.out.println("sender_id : " + request.getParameter("sender_id"));
			System.out.println("receiver_id : " + request.getParameter("receiver_id"));
			System.out.println("content : " + request.getParameter("content"));

			request.setAttribute("id", id);
			request.setAttribute("result", result);
			request.setAttribute("receiver_member", receiver_member);
			request.setAttribute("message", message);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/dm_writePro.jsp";
	}

}
