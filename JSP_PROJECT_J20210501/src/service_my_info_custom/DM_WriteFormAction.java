package service_my_info_custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import service.CommandProcess;

public class DM_WriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DM WriteFormAction Start...");

		try {
			String id = (String) request.getSession().getAttribute("id");
			String other_id = request.getParameter("other_id");
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null)
				pageNum = "1";

			System.out.println("id : " + id);
			System.out.println("other_id : " + other_id);
			System.out.println("pageNum : " + pageNum);

			Member_dao md = Member_dao.getInstance();

			Member member_sender = md.select(id);
			Member member_receiver = md.select(other_id);

			request.setAttribute("id", id);
			request.setAttribute("other_id", other_id);
			request.setAttribute("member_sender", member_sender);
			request.setAttribute("member_receiver", member_receiver);
			request.setAttribute("pageNum", pageNum);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/dm_writeForm.jsp";
	}

}
