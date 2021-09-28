package service_board;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.Member;
import dao.Member_dao;
import dao.PostMember;
import dao.PostMember_dao;
import service.CommandProcess;

public class WriteProAction implements CommandProcess {
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("<WriteProAction Start...>");

		try {
			request.setCharacterEncoding("utf-8");

			// 파일 업로드 부분
			String filename = "";
//			String upLoadFilename = "";
			int maxSize = 10 * 1024 * 1024; // 10MB
			String save_path = "/file_upload";
			String realPath = request.getServletContext().getRealPath(save_path);
			System.out.println("realPath : " + realPath);
			MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
			
			String id = (String) request.getSession().getAttribute("id");
			int board_no = Integer.parseInt(multi.getParameter("board_no"));
			int post_no = Integer.parseInt(multi.getParameter("post_no"));
			String pageNum = multi.getParameter("pageNum");
			String[] category = multi.getParameterValues("category");
			String category_str = "";
			String post_title = multi.getParameter("post_title");
			String content = multi.getParameter("content");
			String attach = multi.getParameter("attach");
			String passwd = multi.getParameter("passwd");

			System.out.println("WriteProAction category.length : " + category.length);

			for (int i = 0; i < category.length; i++) {
				System.out.printf("WriteProAction %d, %s \n", i, category[i]);
				category_str = category_str + category[i] + " ";
			}
			System.out.println();
			System.out.println("category_str : " + category_str);

			
			Enumeration<?> en = multi.getFileNames();
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
				System.out.println("파라메타 이름 : " + filename1);
				System.out.println("실제 파일 이름 : " + orignal);
				System.out.println("저장된 파일 이름 : " + filename);
				System.out.println("파일 타입 : " + type);
			}

			Member_dao md = Member_dao.getInstance();
			Member member = md.select(id);

			PostMember postmember = new PostMember();
			PostMember_dao pmd = PostMember_dao.getInstance();

			postmember.setMember_id(id);
			postmember.setBoard_no(board_no);
			postmember.setPost_no(post_no);
			postmember.setCategory(category_str);
			postmember.setPost_title(post_title);
			postmember.setContent(content);
			postmember.setAttach(filename);
			postmember.setPasswd(passwd);
			int result = pmd.insert(postmember);

			System.out.println("id : " + id);
			System.out.println("board_no : " + board_no);
			System.out.println("post_no : " + post_no);
			System.out.println("post_title : " + post_title);
			System.out.println("content : " + content);
			System.out.println("attach : " + attach);
			System.out.println("passwd : " + passwd);
			System.out.println("result : " + result);
			System.out.println();

			request.setAttribute("board_no", board_no);
			request.setAttribute("post_no", post_no);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("category", category_str);
			request.setAttribute("result", result);
			request.setAttribute("member", member);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "board/writePro.jsp";
	}
}