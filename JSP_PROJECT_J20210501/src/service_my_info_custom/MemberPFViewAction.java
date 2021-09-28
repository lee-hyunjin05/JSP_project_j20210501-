package service_my_info_custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Member;
import dao.Member_dao;
import dao.Title_no_dao;
import service.CommandProcess;

public class MemberPFViewAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("<MemberPFViewAction Start...>");

		try {
			String id = (String) request.getSession().getAttribute("id");
			String other_id = request.getParameter("member_id");
			System.out.println("id : " + id);
			System.out.println("other_id : " + other_id);

			Member_dao md = Member_dao.getInstance();
			Member other_member = md.select(other_id);
			Title_no_dao tnd = Title_no_dao.getInstance();
			String user_title = tnd.title_view(other_id);
			System.out.println("user_title : " + user_title);

			String upLoadFilename = "";
			upLoadFilename = "profile_img/" + other_member.getProfile_img();

			request.setAttribute("id", id);
			request.setAttribute("other_id", other_id);
			request.setAttribute("user_title", user_title);
			request.setAttribute("other_member", other_member);
			request.setAttribute("upLoadFilename", upLoadFilename);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/memberprofilepage.jsp";
	}

}
