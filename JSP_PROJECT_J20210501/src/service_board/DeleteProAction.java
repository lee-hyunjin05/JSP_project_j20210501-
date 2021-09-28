package service_board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PostMember_dao;
import service.CommandProcess;

public class DeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<DeleteProAction Start...>");

		try {
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			int post_no = Integer.parseInt(request.getParameter("post_no"));
			String pageNum = request.getParameter("pageNum");
			String passwd = request.getParameter("passwd");
			int re_step = Integer.parseInt(request.getParameter("re_step"));
			int ref = Integer.parseInt(request.getParameter("ref"));

			PostMember_dao pd = PostMember_dao.getInstance();
			int result = pd.delete(board_no, post_no, passwd, re_step);
			
			request.setAttribute("board_no", board_no);
			request.setAttribute("post_no", post_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("re_step", re_step);
			request.setAttribute("ref", ref);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "board/deletePro.jsp";
	}

}
