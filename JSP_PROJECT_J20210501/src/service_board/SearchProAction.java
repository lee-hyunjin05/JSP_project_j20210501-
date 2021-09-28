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

public class SearchProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("SearchProAction Start...");

		try {
			request.setCharacterEncoding("utf-8");
			String id = (String) request.getSession().getAttribute("id");
			int kind = Integer.parseInt(request.getParameter("kind"));
			int board_no = kind;
			String[] category = request.getParameterValues("category");
			String search_text = request.getParameter("search_text");
			String search_category = "";
			for (int i = 0; i < category.length; i++)
				search_category = search_category + category[i] + " ";
			System.out.println("search_category : " + search_category);
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals("")) {
				pageNum = "1";
			}

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();

			PostMember_dao pmd = PostMember_dao.getInstance();

			int searchtotCnt = pmd.getSearchTotalCnt(board_no, search_category, search_text);
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10;
			int blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int startNum = searchtotCnt - startRow + 1;

			List<PostMember> searchlist = pmd.search(startRow, endRow, board_no, search_category, search_text);
			System.out.println("SearchProAction searchlist.size() : " + searchlist.size());

			int pageCnt = (int) Math.ceil((double) searchtotCnt / pageSize);
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;
			if (endPage > pageCnt)
				endPage = pageCnt;

			System.out.println("board_no : " + kind);
			System.out.println("search_text : " + search_text);
			System.out.println("category : " + search_category);
			System.out.println("searchtotCnt : " + searchtotCnt);
			System.out.println("searchlist : " + searchlist);
			System.out.println("==============================");
			for (PostMember postList : searchlist) {
				System.out.println("PostMember getPost_title : " + postList.getPost_title());
			}
			System.out.println("==============================");
			System.out.println("pageNum : " + pageNum);
			System.out.println("currentPage : " + currentPage);
			System.out.println("blockSize : " + blockSize);
			System.out.println("pageCnt : " + pageCnt);
			System.out.println("startPage : " + startPage);
			System.out.println("endPage : " + endPage);
			System.out.println("startNum : " + startNum);
			System.out.println("SearchPro searchlist.size() : " + searchlist.size());
			System.out.println();
			
			request.setAttribute("searchtotCnt", searchtotCnt);
			request.setAttribute("board_no", kind);
			request.setAttribute("search_text", search_text);
			request.setAttribute("search_category", search_category);
			request.setAttribute("searchtotCnt", searchtotCnt);
			request.setAttribute("searchlist", searchlist);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("member", member);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "board/searchPro.jsp";
	}

}
