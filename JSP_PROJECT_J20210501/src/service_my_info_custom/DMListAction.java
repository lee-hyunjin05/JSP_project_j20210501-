package service_my_info_custom;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import dao.Message;
import dao.Message_dao;
import service.CommandProcess;

public class DMListAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<DMListAction Start...>");

		try {
			String id = (String) request.getSession().getAttribute("id");
			System.out.println("id : " + id);

			Message_dao md = Message_dao.getInstance();
			Member_dao mmd = Member_dao.getInstance();

			int totCnt = md.getDMTotalCnt(id);
			request.setAttribute("totCnt", totCnt);
			System.out.println("totCnt : " + totCnt);			

			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals(""))
				pageNum = "1";

			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10;
			int blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int startNum = totCnt - startRow + 1;

			Member member = mmd.select(id);
			List<Message> list = md.list(startRow, endRow, id);
			System.out.println("DMLIST list.size() : " + list.size());
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();

			int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;

			if (endPage > pageCnt)
				endPage = pageCnt;

			System.out.println("pageNum : " + pageNum);
			System.out.println("currentPage : " + currentPage);
			System.out.println("startNum : " + startNum);
			System.out.println("list : " + list);
			System.out.println("blockSize : " + blockSize);
			System.out.println("pageCnt : " + pageCnt);
			System.out.println("startPage : " + startPage);
			System.out.println("endPage : " + endPage);
			System.out.println("member : " + member);
			System.out.println();
			
			request.setAttribute("id", id);
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

		return "member_custom_info/dmlist.jsp";
	}

}
