<%@page import="dao.Post"%>
<%@page import="java.util.List"%>
<%@page import="dao.Post_dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/post_style.css" type="text/css">
</head>
<body>
	<div id="wrapper">
		<section id="section">
			<div id="post_title">
				<h1><a href="matching.do">매칭 게시판</a></h1>
			</div>
			<table class="post_list">
				<tr>
					<th class="title_width2">제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>추천수</th>
				</tr>
				<c:if test="${totCnt > 0 }">
					<c:forEach var="postmember" items="${list }">
						<tr>
							<td class="left">
								<c:if test="${postmember.recommend >= 20 }">
									<img src="images/hot.gif">
								</c:if>
								<c:if test="${postmember.re_level > 0 }">
									<img src="images/level.gif" width="${post.re_level*10 }">
									<img src="images/re.gif">
								</c:if>
								<a href="content.do?board_no=${postmember.board_no }&post_no=${postmember.post_no }&pageNum=${currentPage }">${postmember.post_title }
									<c:if test="${postmember.attach != null}">
									<img alt="" src="images/picture.png" width="20px">
								</c:if>
								</a>
							</td>
							<td><a href="memberpage.do?id=${id }&member_id=${postmember.member_id }" onclick="window.open(this.href,'','width=400px, height=500px'); return false;">${postmember.nickname }</a></td>
							<td>${postmember.hits }</td>
							<td>${postmember.recommend }</td>
						</tr>
						<c:set var="startNum" value="${startNum - 1 }" />
					</c:forEach>
				</c:if>
				<c:if test="${totCnt == 0 }">
					<tr>
						<td>데이터가 없습니다.</td>
					</tr>
				</c:if>
			</table>
				<table class="post_list">
				<tr><td>
				<c:if test="${startPage > blockSize }">
					<a href="matching.do?pageNum=${startPage - blockSize }">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="matching.do?pageNum=${i }">${i }</a>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="matching.do?pageNum=${startPage + blockSize }">[다음]</a>
				</c:if>
				</td></tr>
				</table>
				<a href="writeForm.do?board_no=1"><input type="button" value="글쓰기" id="write_but"></a>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>