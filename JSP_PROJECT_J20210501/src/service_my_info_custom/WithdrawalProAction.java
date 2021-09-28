package service_my_info_custom;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member_dao;
import service.CommandProcess;

public class WithdrawalProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<WithdrawProAction Start...>");
		
		try {
			request.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			System.out.println("WithdrawProAction id : " + id);
			System.out.println("WithdrawProAction pw : " + pw);
			System.out.println();
			
			Member_dao md = Member_dao.getInstance();
			int result = md.withdraw(id, pw);
			
			request.getSession().invalidate();
			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "member_custom_info/withdrawalPro.jsp";
	}

}
