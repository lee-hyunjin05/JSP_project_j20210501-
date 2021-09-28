<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/aside.css" type="text/css" />
</head>
<body>
	<div id="wrapper">
		<c:if test="${id == null }">
			<div id="side_bar">
				<div id="login_box">
					<a href="loginForm.do" id="login_btn">로그인</a>
				</div>
			</div>
		</c:if>
		<c:if test="${id != null }">
			<div id="side_bar">
				<div id="login_box">
					<div id="box123">
						<c:if test="${member.profile_img == null }">
							<c:if test="${member.gender == '남자' }">
								<img id="user" src="images/profile_img_m.png" width="80px" height="80px" />
							</c:if>
							<c:if test="${member.gender == '여자' }">
								<img id="user" src="images/profile_img_f.png" width="80px" height="80px" />
							</c:if>
						</c:if>
						<c:if test="${member.profile_img != null }">
							<img id="user" src="${profileName }" />
						</c:if>

					</div>
					<p id="nickname">${member.nickname }</p>
					<p id="name">${member.member_name }</p>
					<p id="rec">총 추천수 : ${member.rec }</p>
					<p id="title">칭호 : ${user_title }</p>
					<a href="logout.do" id="logout_btn">로그아웃</a>
				</div>
				<c:if test="${auth == 1 }">
					<div id="my_menu">
						<p>
							<a class="white" href="mypageLoginForm.do"> <span class="bg"></span>
								<span class="base"></span> <span class="text">마이페이지</span>
							</a>
						</p>
						<p>
							<a class="white" href="mypostForm.do"> <span class="bg"></span>
								<span class="base"></span> <span class="text">내가 쓴 글</span>
							</a>
						</p>
						<p>
							<a class="white" href="myreply.do"> <span class="bg"></span>
								<span class="base"></span> <span class="text">댓글 단 글</span>
							</a>
						</p>
						<p>
							<a class="white" href="bookmark.do"> <span class="bg"></span>
								<span class="base"></span> <span class="text">즐겨찾기</span>
							</a>
						</p>
						<p>
							<a class="white" href="dmlist.do"> <span class="bg"></span> <span
								class="base"></span> <span class="text">내게 온 쪽지</span>
							</a>
						</p>
						<p>
							<a class="white" href="manageForm.do"> <span class="bg"></span>
								<span class="base"></span> <span class="text">회원관리</span>
							</a>
						</p>
					</div>
				</c:if>

				<c:if test="${auth != 1 }">
					<div id="my_menu">
						<p>
							<a class="white" href="mypageLoginForm.do"> <span class="bg"></span>
								<span class="base"></span> <span class="text">마이페이지</span>
							</a>
						</p>
						<p>
							<a class="white" href="mypostForm.do"> <span class="bg"></span>
								<span class="base"></span> <span class="text">내가 쓴 글</span>
							</a>
						</p>
						<p>
							<a class="white" href="myreply.do"> <span class="bg"></span>
								<span class="base"></span> <span class="text">댓글 단 글</span>
							</a>
						</p>
						<p>
							<a class="white" href="bookmark.do"> <span class="bg"></span>
								<span class="base"></span> <span class="text">즐겨찾기</span>
							</a>
						</p>
						<p>
							<a class="white" href="dmlist.do"> <span class="bg"></span> <span
								class="base"></span> <span class="text">내게 온 쪽지</span>
							</a>
						</p>
					</div>
				</c:if>

			</div>
		</c:if>
	</div>
</body>
</html>