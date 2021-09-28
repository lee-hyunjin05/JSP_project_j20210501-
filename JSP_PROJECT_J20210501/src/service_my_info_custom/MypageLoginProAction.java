package service_my_info_custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;
import service.CommandProcess;

public class MypageLoginProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<MypageLoginProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String id = (String) request.getSession().getAttribute("id");
			String pw = request.getParameter("pw");

			System.out.println("MypageLoginProAction id : " + id);
			System.out.println("MypageLoginProAction pw : " + pw);
			System.out.println();

			Member_dao md = Member_dao.getInstance();
			int result = md.check(id, pw);

			request.setAttribute("id", id);
			request.setAttribute("pw", pw);
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/mypageLoginPro.jsp";
	}

}
