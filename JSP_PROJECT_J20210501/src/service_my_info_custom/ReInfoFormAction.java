package service_my_info_custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import service.CommandProcess;

public class ReInfoFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<ReInfoFormAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = (String) request.getSession().getAttribute("id");

			System.out.println("ReInfoFormAction id : " + id);
			System.out.println();

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);
			
			String[] tokens = member.getTel().split("-");
			String tel1 = tokens[0];
			String tel2 = tokens[1];
			String tel3 = tokens[2];
			
			String profileName = "";
			profileName = "profile_img/" + member.getProfile_img();
            
			request.setAttribute("member", member);
			request.setAttribute("tel1", tel1);
			request.setAttribute("tel2", tel2);
			request.setAttribute("tel3", tel3);
			request.setAttribute("profileName", profileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/reInfoForm.jsp";
	}

}
