package service_board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import dao.PostMember;
import dao.PostMember_dao;
import service.CommandProcess;

public class MatchingAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<MatchingAction Start...>");

		try {
			int board_no = 1;
			String id = (String) request.getSession().getAttribute("id");

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();

			PostMember_dao pmd = PostMember_dao.getInstance();
			int totCnt = pmd.getTotalCnt(board_no);

			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals(""))
				pageNum = "1";

			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10;
			int blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int startNum = totCnt - startRow + 1;

			List<PostMember> list = pmd.list(startRow, endRow, board_no);
			System.out.println("InfoTravelAction list.size() : " + list.size());

			int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;

			if (endPage > pageCnt)
				endPage = pageCnt;

			request.setAttribute("board_no", board_no);
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("member", member);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "board/matching.jsp";
	}

}
