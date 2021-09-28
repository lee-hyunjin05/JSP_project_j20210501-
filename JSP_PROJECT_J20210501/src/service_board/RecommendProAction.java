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

public class RecommendProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<M_RecommendAction Start...>");

		try {
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			int post_no = Integer.parseInt(request.getParameter("post_no"));
			String id = (String) request.getSession().getAttribute("id");
			String pageNum = request.getParameter("pageNum");

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);

			PostMember_dao pmd = PostMember_dao.getInstance();
			int check = pmd.recommend(id, board_no, post_no);
			PostMember postmember = new PostMember();
			postmember = pmd.select(board_no, post_no);
			
			int rec_tot = md.rec_update(postmember.getMember_id());
			int title_grade = md.title_update(postmember.getMember_id());

			System.out.println("id : " + id);
			System.out.println("board_no : " + board_no);
			System.out.println("post_no : " + post_no);
			System.out.println("pageNum : " + pageNum);
			System.out.println("postmember.getMember_id() : " + postmember.getMember_id());
			System.out.println("rec_tot : " + rec_tot);
			System.out.println("title_grade : " + title_grade);
			System.out.println("check : " + check);
			System.out.println();
			
			request.setAttribute("id", id);
			request.setAttribute("post_no", post_no);
			request.setAttribute("board_no", board_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("postmember", postmember);
			request.setAttribute("check", check);
			request.setAttribute("title_grade", title_grade);
			request.setAttribute("rec_tot", rec_tot);
			request.setAttribute("member", member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "board/recommendPro.jsp";
	}

}
