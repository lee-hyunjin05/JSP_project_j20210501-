package service_my_info_custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import service.CommandProcess;

public class MypageLoginFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<MypageLoginFormAction Start...>");
		
		try {
			request.setCharacterEncoding("utf-8");
			String id = (String) request.getSession().getAttribute("id");
			
			System.out.println("MypageLoginFormAction id : " + id);
			System.out.println();
			
			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();
            			
			request.setAttribute("member", member);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "member_custom_info/mypageLoginForm.jsp";
	}

}
