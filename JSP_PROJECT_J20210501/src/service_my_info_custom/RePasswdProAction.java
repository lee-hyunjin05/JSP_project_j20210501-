package service_my_info_custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;
import service.CommandProcess;

public class RePasswdProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<RePasswdProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String cur_pw = request.getParameter("cur_pw");
			String new_pw1 = request.getParameter("new_pw1");
			String new_pw2 = request.getParameter("new_pw2");
			
			System.out.println("RePasswdProAction id : " + id);
			System.out.println("RePasswdProAction cur_pw : " + cur_pw);
			System.out.println("RePasswdProAction new_pw1 : " + new_pw1);
			System.out.println("RePasswdProAction new_pw2 : " + new_pw2);
			System.out.println();

			Member_dao md = Member_dao.getInstance();
			int result = md.repasswd(id, cur_pw, new_pw1, new_pw2);

			request.getSession().invalidate();
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/rePasswdPro.jsp";
	}

}
