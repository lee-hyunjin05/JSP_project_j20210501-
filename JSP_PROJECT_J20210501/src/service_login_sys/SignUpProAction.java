package service_login_sys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import service.CommandProcess;

public class SignUpProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<SignUpProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			Member member = new Member();
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String nickname = request.getParameter("nickname");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			if(Integer.parseInt(month) < 10)
				month = "0" + month;
			String day = request.getParameter("day");
			if(Integer.parseInt(day) < 10)
				day = "0" + day;
			String birth = year + month + day;
			String tel1 = request.getParameter("tel1");
			String tel2 = request.getParameter("tel2");
			String tel3 = request.getParameter("tel3");
			String tel = tel1 + "-" + tel2 + "-" + tel3;
			String intro = request.getParameter("intro");
			
			System.out.println("SignUpProAction id : " + id);
			System.out.println("SignUpProAction pw : " + pw);
			System.out.println("SignUpProAction name : " + name);
			System.out.println("SignUpProAction gender : " + gender);
			System.out.println("SignUpProAction nickname : " + nickname);
			System.out.println("SignUpProAction birth : " + birth);
			System.out.println("SignUpProAction tel : " + tel);
			System.out.println("SignUpProAction intro : " + intro);
			System.out.println();
			
			member.setMember_id(id);
			member.setMember_pw(pw);
			member.setMember_name(name);
			member.setGender(gender);
			member.setNickname(nickname);
			member.setbirth(birth);
			member.setTel(tel);
			member.setIntro(intro);

			Member_dao md = Member_dao.getInstance();
			int result = md.insert(member);

			request.setAttribute("result", result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "login_sys/signUpPro.jsp";
	}

}
