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

public class ContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<ContentAction Start...>");

		try {
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			int post_no = Integer.parseInt(request.getParameter("post_no"));
			String id = (String) request.getSession().getAttribute("id");
			String pageNum = request.getParameter("pageNum");

			PostMember_dao pmd = PostMember_dao.getInstance();
			int replytotCnt = pmd.getTotalReplyCnt(board_no, post_no); //
			pmd.hits(board_no, post_no);

			PostMember postmember = new PostMember();
			postmember = pmd.select(board_no, post_no);
			List<PostMember> list = pmd.comment_list(board_no, post_no);

			String upLoadFilename = "";
			String save_path = "/file_upload";
			String realPath = request.getServletContext().getRealPath(save_path);
			System.out.println("realPath : " + realPath);
			upLoadFilename = "file_upload/" + postmember.getAttach();

			request.setAttribute("realPath", realPath);
			request.setAttribute("upLoadFilename", upLoadFilename);
			System.out.println("upLoadFilename : " + upLoadFilename);

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();

			System.out.println("board_no : " + board_no);
			System.out.println("post_no : " + post_no);
			System.out.println("id : " + id);
			System.out.println("category : " + postmember.getCategory());
			System.out.println("category.length : " + postmember.getCategory().length());
			System.out.println("pageNum : " + pageNum);
			System.out.println("replytotCnt : " + replytotCnt);
			System.out.println();

			request.setAttribute("replytotCnt", replytotCnt);
			request.setAttribute("board_no", board_no);
			request.setAttribute("post_no", post_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("id", id);
			request.setAttribute("postmember", postmember);
			request.setAttribute("list", list);
			request.setAttribute("member", member);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "board/content.jsp";
	}

}
