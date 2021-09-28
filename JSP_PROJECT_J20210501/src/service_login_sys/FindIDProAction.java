package service_login_sys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;
import service.CommandProcess;

public class FindIDProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<FindIDProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			String name = request.getParameter("name");
			String birth = request.getParameter("birth");
			String tel1 = request.getParameter("tel1");
			String tel2 = request.getParameter("tel2");
			String tel3 = request.getParameter("tel3");
			String tel = tel1 + "-" + tel2 + "-" + tel3;

			System.out.println("FindIDProAction name : " + name);
			System.out.println("FindIDProAction birth : " + birth);
			System.out.println("FindIDProAction tel : " + tel);
			System.out.println();

			Member_dao md = Member_dao.getInstance();
			String id = md.find_id(name, birth, tel);

			request.setAttribute("id", id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "login_sys/findIDPro.jsp";
	}

}
