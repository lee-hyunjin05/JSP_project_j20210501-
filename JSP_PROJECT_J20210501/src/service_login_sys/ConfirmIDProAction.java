package service_login_sys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;
import service.CommandProcess;

public class ConfirmIDProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<ConfirmIDProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			
			System.out.println("ConfirmIDProAction id : " + id);
			System.out.println();
			
			Member_dao md = Member_dao.getInstance();
			int result = md.confirm_id(id);
			
			request.setAttribute("id", id);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "login_sys/confirmIDPro.jsp";
	}

}
