package service_my_info_custom;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import service.CommandProcess;

public class ManageFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<ManageFormAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = (String) request.getSession().getAttribute("id");

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();
            
			int totCnt = md.getTotalCnt();
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals(""))
				pageNum = "1";
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10, blockSize = 10;
			// page = 2 -> startRow == 11, endRow == 20
			// page = 3 -> startRow == 21, endRow == 30
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			List<Member> list = md.list(startRow, endRow);
			int startNum = totCnt - startRow + 1;
			int pageCnt = (int) Math.ceil((double) totCnt / pageSize);
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;
			if (endPage > pageCnt)
				endPage = pageCnt;

			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			System.out.println("==========================================");
			System.out.println("totCnt		: " + totCnt);
			System.out.println("startRow	: " + startRow);
			System.out.println("endRow		: " + endRow);
			System.out.println("startNum	: " + startNum);
			System.out.println("currentPage	: " + currentPage);
			System.out.println("blockSize	: " + blockSize);
			System.out.println("pageSize	: " + pageSize);
			System.out.println("pageCnt		: " + pageCnt);
			System.out.println("startPage	: " + startPage);
			System.out.println("endPage		: " + endPage);
			System.out.println();

			request.setAttribute("id", id);
			request.setAttribute("member", member);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			e.getMessage();
		}

		return "member_custom_info/manageForm.jsp";
	}

}
