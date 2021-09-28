package service_my_info_custom;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bookmark;
import dao.Bookmark_dao;
import dao.Member;
import dao.Member_dao;
import service.CommandProcess;

public class BookmarkFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<BookmarkFormAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = (String) request.getSession().getAttribute("id");

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();
            
			Bookmark_dao bmd = Bookmark_dao.getInstance();

			int booktotCnt = bmd.getBookTotalCnt(id); //
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals(""))
				pageNum = "1";

			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10;
			int blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int startNum = booktotCnt - startRow + 1;

			List<Bookmark> booklist = bmd.book_list(startRow, endRow, id);
			System.out.println("MatchingAction list.size() : " + booklist.size());

			int pageCnt = (int) Math.ceil((double) booktotCnt / pageSize);
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;

			if (endPage > pageCnt)
				endPage = pageCnt;
			
			for(Bookmark book : booklist ) {
				System.out.println("BookmarkFormAction book.board.no() : " + book.getBoard_no());
				System.out.println("BookmarkFormAction book.post.no() : " + book.getPost_no());
				System.out.println("BookmarkFormAction book.member_id() : " + book.getMember_id());
				System.out.println("BookmarkFormAction book.getNickname() : " + book.getNickname());
				System.out.println("BookmarkFormAction book.getPost_title() : " + book.getPost_title());
				System.out.println();
			}

			request.setAttribute("id", id);
			request.setAttribute("booktotCnt", booktotCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("booklist", booklist);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);

			System.out.println("id : " + id);
			System.out.println("booktotCnt : " + booktotCnt);
			System.out.println("pageNum : " + pageNum);
			System.out.println("currentPage : " + currentPage);
			System.out.println("startNum : " + startNum);
			System.out.println("booklist : " + booklist);
			System.out.println("blockSize : " + blockSize);
			System.out.println("pageCnt : " + pageCnt);
			System.out.println("startPage : " + startPage);
			System.out.println("endPage : " + endPage);
			System.out.println();
			
			request.setAttribute("member", member);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/bookmarkForm.jsp";
	}

}
