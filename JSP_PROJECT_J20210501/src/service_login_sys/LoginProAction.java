package service_login_sys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;
import service.CommandProcess;

public class LoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<LoginProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			System.out.println("LoginProAction id : " + id);
			System.out.println("LoginProAction pw : " + pw);
			System.out.println();

			Member_dao md = Member_dao.getInstance();
			int result = md.check(id, pw);

			if(result == 1) {
				request.getSession().setAttribute("id", id);
			}
			
			request.setAttribute("id", id);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "login_sys/loginPro.jsp";
	}

}
