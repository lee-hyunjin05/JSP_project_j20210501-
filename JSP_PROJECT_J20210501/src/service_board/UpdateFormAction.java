package service_board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import dao.PostMember;
import dao.PostMember_dao;
import service.CommandProcess;

public class UpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<UpdateFormAction Start...>");

		try {
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			int post_no = Integer.parseInt(request.getParameter("post_no"));
			String id = (String) request.getSession().getAttribute("id");
			String pageNum = request.getParameter("pageNum");
			int re_step = Integer.parseInt(request.getParameter("re_step"));
			int ref = Integer.parseInt(request.getParameter("ref"));

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();

			PostMember_dao pd = PostMember_dao.getInstance();
			PostMember postmember = pd.select(board_no, post_no);

			System.out.println("id : " + id);
			System.out.println("postmember.getMember_id : " + postmember.getMember_id());
			System.out.println();
			
			int check = 0;
			if (postmember.getContent().equals("삭제된 글입니다."))
				check = 1;

			request.setAttribute("check", check);
			request.setAttribute("id", id);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("postmember", postmember);
			request.setAttribute("member", member);
			request.setAttribute("re_step", re_step);
			request.setAttribute("ref", ref);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "board/updateForm.jsp";
	}

}
