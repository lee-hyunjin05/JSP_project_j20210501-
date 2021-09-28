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

public class MyReplyFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<MyReplyFormAction Start...>");
		
		try {
			String id = (String) request.getSession().getAttribute("id");
			System.out.println("id : " + id);
			
			Member_dao md = Member_dao.getInstance();
			Mypost_dao mpd = Mypost_dao.getInstance();
			
			int myreplytotCnt = mpd.getMyreplyTotalCnt(id);
			
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null || pageNum.equals(""))
				pageNum = "1";
			
			int currentPage = Integer.parseInt(pageNum);
			int pageSize = 10;
			int blockSize = 10;
			int startRow = (currentPage - 1)*pageSize+1;
			int endRow = startRow + pageSize -1;
			int startNum = myreplytotCnt - startRow +1;
			
			Member member = md.select(id);
			List<Mypost> replylist = mpd.myreply_list(startRow, endRow, id);
			System.out.println("MatchingAction list.size() : " + replylist.size());
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();
			
			int pageCnt = (int)Math.ceil((double)myreplytotCnt/pageSize);
			int startPage = (int)(currentPage -1 )/blockSize*blockSize +1; 
			int endPage = startPage + blockSize -1;
			
			if (endPage > pageCnt)
				endPage = pageCnt; 
			
			System.out.println("id : " + id);
			System.out.println("myreplytotCnt : " + myreplytotCnt);
			System.out.println("pageNum : " + pageNum);
			System.out.println("currentPage : " + currentPage);
			System.out.println("startNum : " + startNum);
			System.out.println("replylist : " + replylist);
			System.out.println("blockSize : " + blockSize);
			System.out.println("pageCnt : " + pageCnt);
			System.out.println("startPage : " + startPage);
			System.out.println("endPage : " + endPage);
			System.out.println("member : " + member);
			System.out.println();
			
			request.setAttribute("id", id);
			request.setAttribute("myreplytotCnt", myreplytotCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("replylist", replylist);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("member", member);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "member_custom_info/myreplyForm.jsp";
	}
}
