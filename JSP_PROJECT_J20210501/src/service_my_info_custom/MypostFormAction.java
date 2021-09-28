package service_my_info_custom;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import dao.Mypost;
import dao.Mypost_dao;
import service.CommandProcess;

public class MypostFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<MypostFormAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = (String) request.getSession().getAttribute("id");

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();
            
			Mypost_dao mpd = Mypost_dao.getInstance();

			int posttotCnt = mpd.getPostTotalCnt(id); //
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null || pageNum.equals(""))
				pageNum = "1";

			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10;
			int blockSize = 10;
			int startRow = (currentPage - 1) * pageSize + 1;
			int endRow = startRow + pageSize - 1;
			int startNum = posttotCnt - startRow + 1;

			List<Mypost> postlist = mpd.mypost_list(startRow, endRow, id);
			System.out.println("MatchingAction list.size() : " + postlist.size());

			int pageCnt = (int) Math.ceil((double) posttotCnt / pageSize);
			int startPage = (int) (currentPage - 1) / blockSize * blockSize + 1;
			int endPage = startPage + blockSize - 1;

			if (endPage > pageCnt)
				endPage = pageCnt;
			
			for(Mypost post : postlist ) {
				System.out.println("MypostFormAction post.board.no() : " + post.getBoard_no());
				System.out.println("MypostFormAction post.post.no() : " + post.getPost_no());
				System.out.println("MypostFormAction post.getNickname() : " + post.getNickname());
				System.out.println("MypostFormAction post.getPost_title() : " + post.getPost_title());
				System.out.println("MypostFormAction post.getAttach() : " + post.getAttach());
				System.out.println();
			}

			request.setAttribute("id", id);
			request.setAttribute("posttotCnt", posttotCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("postlist", postlist);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
			System.out.println("id : " + id);
			System.out.println("posttotCnt : " + posttotCnt);
			System.out.println("pageNum : " + pageNum);
			System.out.println("currentPage : " + currentPage);
			System.out.println("startNum : " + startNum);
			System.out.println("postlist : " + postlist);
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

		return "member_custom_info/mypostForm.jsp";
	}

}