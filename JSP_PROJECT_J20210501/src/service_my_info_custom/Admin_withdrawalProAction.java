package service_my_info_custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;
import service.CommandProcess;

public class Admin_withdrawalProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<Admin_withdrawalProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String wd = request.getParameter("wd");

			System.out.println("Admin_withdrawalProAction id : " + id);
			System.out.println("Admin_withdrawalProAction pw : " + pw);
			System.out.println("Admin_withdrawalProAction wd : " + wd);
			System.out.println();

			Member_dao md = Member_dao.getInstance();

			int result = -1;
			if (wd.equals("N")) {
				result = md.withdraw(id, pw);
			} else {
				result = md.restore(id, pw);
			}

			request.setAttribute("wd", wd);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/admin_withdrawalPro.jsp";
	}

}
