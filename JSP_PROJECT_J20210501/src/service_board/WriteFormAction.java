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

public class WriteFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("WriteFormAction Start...");

		try {
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			int post_no = 0, ref = 0, re_level = 0, re_step = 0;
			String id = (String) request.getSession().getAttribute("id");
			String pageNum = request.getParameter("pageNum");

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();
            
			if (pageNum == null)
				pageNum = "1";

			// 댓글용도
			if (request.getParameter("post_no") != null) {
				board_no = Integer.parseInt(request.getParameter("board_no"));
				post_no = Integer.parseInt(request.getParameter("post_no"));

				PostMember_dao pd = PostMember_dao.getInstance();
				PostMember postmember = pd.select(board_no, post_no);
				
				ref = postmember.getRef();
				re_level = postmember.getRe_level();
				re_step = postmember.getRe_step();
			}
			
			System.out.println("id : " + id);
			System.out.println("board_no : " + board_no);
			System.out.println("post_no : " + post_no);
			System.out.println("pageNum : " + pageNum);
			System.out.println("ref : " + ref);
			System.out.println("re_level : " + re_level);
			System.out.println("re_step : " + re_step);
			System.out.println();

			request.setAttribute("id", id);
			request.setAttribute("board_no", board_no);
			request.setAttribute("post_no", post_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("ref", ref);
			request.setAttribute("re_level", re_level);
			request.setAttribute("re_step", re_step);
			request.setAttribute("member", member);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "board/writeForm.jsp";
	}

}
