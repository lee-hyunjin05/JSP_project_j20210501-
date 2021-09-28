package service_my_info_custom;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Member;
import dao.Member_dao;
import service.CommandProcess;

public class ReInfoProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<ReInfoProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");
			Member member = new Member();

			String filename = "";
			String upLoadFilename = "";
			int maxSize = 10 * 1024 * 1024; // 10MB
			String save_path = "/profile_img";
			String realPath = request.getServletContext().getRealPath(save_path);
			MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			Enumeration<?> en = multi.getFileNames();

			String context = request.getContextPath();
			String id = multi.getParameter("id");
			String name = multi.getParameter("name");
			String nickname = multi.getParameter("nickname");
			String tel1 = multi.getParameter("tel1");
			String tel2 = multi.getParameter("tel2");
			String tel3 = multi.getParameter("tel3");
			String tel = tel1 + "-" + tel2 + "-" + tel3;
			String intro = multi.getParameter("intro");
//			String profile_img = multi.getParameter("profile_img");

			while (en.hasMoreElements()) {
				// input 태그의 속성이 file인 태그의 name 속성값 : 파라미터 이름
				String filename1 = (String) en.nextElement();
				// 서버에 저장된 파일 이름
				filename = multi.getFilesystemName(filename1);
				// 전송전 원래의 파일 이름
				String orignal = multi.getOriginalFileName(filename1);
				// 전송된 파일의 내용 타입
				String type = multi.getContentType(filename1);
				// 전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일 객체 생성
//				File file = multi.getFile(filename1);
				
				System.out.println("real Path : " + realPath);
				System.out.println("변수 이름 : " + filename1);
				System.out.println("실제 파일 이름 : " + orignal);
				System.out.println("저장된 파일 이름 : " + filename);
				System.out.println("파일 타입 : " + type);
			}
			
			System.out.println("realPath : " + realPath);

			System.out.println("ReInfoProAction id : " + id);
			System.out.println("ReInfoProAction name : " + name);
			System.out.println("reInfoProAction profile_img : " + filename);
			System.out.println("ReInfoProAction nickname : " + nickname);
			System.out.println("ReInfoProAction tel : " + tel);
			System.out.println("ReInfoProAction intro : " + intro);
			upLoadFilename = realPath + "\\" + filename;
			System.out.println("전달 upLoadFilename->" + upLoadFilename);
			System.out.println("request.getContextPath()->" + request.getContextPath());
			System.out.println();

			member.setMember_id(id);
			member.setMember_name(name);
			member.setProfile_img(filename);
			member.setNickname(nickname);
			member.setTel(tel);
			member.setIntro(intro);

			Member_dao md = Member_dao.getInstance();
			int result = md.update(member);

			request.setAttribute("id", id);
			request.setAttribute("result", result);
			request.setAttribute("filename", "profile_img\\" + filename);
			request.setAttribute("upLoadFilename", upLoadFilename);
			request.setAttribute("context", context);
			request.setAttribute("member", member);
			request.setAttribute("realPath", realPath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "member_custom_info/reInfoPro.jsp";
	}

}
