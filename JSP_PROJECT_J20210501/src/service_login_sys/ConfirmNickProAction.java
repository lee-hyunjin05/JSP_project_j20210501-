package service_login_sys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;
import service.CommandProcess;

public class ConfirmNickProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<ConfirmNickProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String nickname = request.getParameter("nickname");
			
			System.out.println("ConfirmNickProAction nickname : " + nickname);
			System.out.println();
			
			Member_dao md = Member_dao.getInstance();
			int result = md.confirm_nick(nickname);
			
			request.setAttribute("nickname", nickname);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "login_sys/confirmNickPro.jsp";
	}

}
