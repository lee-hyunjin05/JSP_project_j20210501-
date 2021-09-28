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

public class BookmarkProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<BookmarkProAction Start...>");
		
		try {
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			int post_no = Integer.parseInt(request.getParameter("post_no"));
			String id = (String) request.getSession().getAttribute("id");
			String pageNum = request.getParameter("pageNum");

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);

			PostMember_dao pmd = PostMember_dao.getInstance();
			int check = pmd.bookmark(board_no, post_no, id);
			PostMember postmember = new PostMember();
			postmember = pmd.select(board_no, post_no);

			System.out.println("id : " + id);
			System.out.println("board_no : " + board_no);
			System.out.println("post_no : " + post_no);
			System.out.println("pageNum : " + pageNum);
			System.out.println("check : " + check);
			System.out.println();

			request.setAttribute("id", id);
			request.setAttribute("board_no", board_no);
			request.setAttribute("psost_no", post_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("postmember", postmember);
			request.setAttribute("check", check);
			request.setAttribute("member", member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "board/bookmarkPro.jsp";
	}

}
