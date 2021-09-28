package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Member;
import dao.Member_dao;
import dao.PostMember;
import dao.PostMember_dao;
import dao.Title_no_dao;

public class MainAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<MainAction Start...>");
		
		String id = null;
		int auth = 0;
		try {
			if (request.getSession().getAttribute("id") != null) {
				id = (String)request.getSession().getAttribute("id");
				
				Member_dao md = Member_dao.getInstance();
				Member member = md.select(id);
				auth = md.a_user(id);
				
				Title_no_dao tnd = Title_no_dao.getInstance();
				String user_title = tnd.title_view(id);
				
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("auth", auth);
				httpSession.setAttribute("user_title", user_title);
				
				String profileName = "";
				profileName = "profile_img/" + member.getProfile_img();
	            
	            request.setAttribute("profileName", profileName);
				request.setAttribute("member", member);
			}
				PostMember_dao pmd = PostMember_dao.getInstance();
				
				int totCnt1 = pmd.getTotalCnt(1);
				int totCnt2 = pmd.getTotalCnt(2);
				int totCnt3 = pmd.getTotalCnt(3);
				int besttotCnt = pmd.getBestTotalCnt();
				
				request.setAttribute("totCnt1", totCnt1);
				request.setAttribute("totCnt2", totCnt2);
				request.setAttribute("totCnt3", totCnt3);
				request.setAttribute("besttotCnt", besttotCnt);
					
				List<PostMember> list1 = pmd.list(1, 5, 1);
				List<PostMember> list2 = pmd.list(1, 5, 2);
				List<PostMember> list3 = pmd.list(1, 5, 3);
				List<PostMember> bestlist = pmd.bestlist(1, 4);
				
				for (int i = 0; i < bestlist.size(); i++) {
					System.out.println(bestlist.get(i).getAttach());
				}
				
				System.out.println("MatchingAction list1.size() : " + list1.size());
				System.out.println("MatchingAction list2.size() : " + list2.size());
				System.out.println("MatchingAction list3.size() : " + list3.size());
				System.out.println("MatchingAction bestlist.size() : " + bestlist.size());
				
				request.setAttribute("list1", list1);
				request.setAttribute("list2", list2);
				request.setAttribute("list3", list3);
				request.setAttribute("bestlist", bestlist);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "main.jsp";
	}

}
