package service_board;

import java.io.IOException;
//import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.oreilly.servlet.requestpartRequest;
//import com.oreilly.servlet.requestpart.DefaultFileRenamePolicy;

import dao.Member;
import dao.Member_dao;
import dao.PostMember;
import dao.PostMember_dao;
import service.CommandProcess;

public class UpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<UpdateProAction Start...>");

		try {
//			// 파일 업로드 부분
//			String filename = "";
//			int maxSize = 10 * 1024 * 1024; // 10MB
//			String save_path = "/file_upload";
//			String realPath = request.getServletContext().getRealPath(save_path);
//			System.out.println("realPath : " + realPath);
//			requestpartRequest request = new requestpartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());

			request.setCharacterEncoding("utf-8");
			String pageNum = request.getParameter("pageNum");
			String id = (String) request.getSession().getAttribute("id");
			
			String category_str = "";
			String[] category = {};
			if (request.getParameterValues("category") == null)
				category_str = null;
			else
			category = request.getParameterValues("category");
			for (int i = 0; i < category.length; i++) {
				System.out.printf("WriteProAction %d, %s \n", i, category[i]);
				category_str = category_str + category[i] + " ";
			}
			System.out.println();
			System.out.println("category_str : " + category_str);

//			Enumeration<?> en = request.getFileNames();
//			while (en.hasMoreElements()) {
//				// input 태그의 속성이 file인 태그의 name 속성값 : 파라미터 이름
//				String filename1 = (String) en.nextElement();
//				// 서버에 저장된 파일 이름
//				filename = request.getFilesystemName(filename1);
//				// 전송전 원래의 파일 이름
//				String orignal = request.getOriginalFileName(filename1);
//				// 전송된 파일의 내용 타입
//				String type = request.getContentType(filename1);
//				// 전송된 파일속성이 file인 태그의 name 속성값을 이용해 파일 객체 생성
////							File file = request.getFile(filename1);
//				System.out.println("real Path : " + realPath);
//				System.out.println("파라메타 이름 : " + filename1);
//				System.out.println("실제 파일 이름 : " + orignal);
//				System.out.println("저장된 파일 이름 : " + filename);
//				System.out.println("파일 타입 : " + type);
//			}

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);

			PostMember postmember = new PostMember();
			
			System.out.println("attach : " + postmember.getAttach());
			
			postmember.setBoard_no(Integer.parseInt(request.getParameter("board_no")));
			postmember.setPost_no(Integer.parseInt(request.getParameter("post_no")));
			postmember.setNickname(request.getParameter("Nickname"));
			postmember.setCategory(category_str);
			postmember.setPost_title(request.getParameter("post_title"));
//			postmember.setAttach(filename);
			postmember.setAttach(postmember.getAttach());
			postmember.setPasswd(request.getParameter("passwd"));
			postmember.setContent(request.getParameter("content"));
			
			int re_step = Integer.parseInt(request.getParameter("re_step"));
			int ref = Integer.parseInt(request.getParameter("ref"));

			PostMember_dao pd = PostMember_dao.getInstance();
			int result = pd.update(postmember);

			request.setAttribute("result", result);
			request.setAttribute("post_no", postmember.getPost_no());
			request.setAttribute("board_no", postmember.getBoard_no());
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("re_step", re_step);
			request.setAttribute("ref", ref);
			request.setAttribute("member", member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "board/updatePro.jsp";
	}

}
