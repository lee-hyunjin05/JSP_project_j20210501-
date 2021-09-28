package service_my_info_custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import service.CommandProcess;

public class Admin_withdrawalFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<Admin_withdrawalFormAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			
			System.out.println("Admin_withdrawalProAction id : " + id);
			System.out.println();
			
			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();
            
            request.setAttribute("profileName", profileName);
			request.setAttribute("member", member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/admin_withdrawalForm.jsp";
	}

}
