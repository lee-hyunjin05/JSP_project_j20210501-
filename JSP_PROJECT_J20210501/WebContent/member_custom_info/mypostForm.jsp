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
<title>내가 쓴 글</title>
<link rel="stylesheet" href="css/post_style.css" type="text/css">
</head>
<body>
	<div id="wrapper">
		<section id="section">
			<div id="post_title">
				<h1><a href="mypostForm.do">${member.nickname }님이 쓴 글</a></h1>
			</div>
			<table class="post_list">
				<tr>
					<th>게시판</th>
					<th class="title_width">제목</th>
					<th>작성자</th>
					<th>조회수</th>
				</tr>
				<c:if test="${posttotCnt > 0 }">
					<c:forEach var="postlist" items="${postlist }">
						<tr>
							<td>
								<c:choose>
									<c:when test="${postlist.board_no == 1 }">[매칭]</c:when>										
									<c:when test="${postlist.board_no == 2 }">[장터]</c:when>										
									<c:when test="${postlist.board_no == 3 }">[정보]</c:when>										
								</c:choose>
							</td>
							<td class="left">
								<c:if test="${postlist.recommend >= 20 }">
									<img src="images/hot.gif">
								</c:if>
								<a href="content.do?board_no=${postlist.board_no }&post_no=${postlist.post_no }&pageNum=${currentPage }">${postlist.post_title }
									<c:if test="${postlist.attach != null}">
										<img alt="" src="images/picture.png" width="20px">
									</c:if>
								</a>
							</td>
							<td>${postlist.nickname }</td>
							<td>${postlist.hits }</td>
						</tr>
						<c:set var="startNum" value="${startNum - 1 }" />
					</c:forEach>
				</c:if>
				<c:if test="${posttotCnt == 0 }">
					<tr>
						<td colspan="3">내가 쓴 글이 없습니다.</td>
					</tr>
				</c:if>
			</table>
			<div style="text-align: center;">
				<c:if test="${startPage > blockSize }">
					<a href="mypostForm.do?pageNum=${startPage - blockSize }">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="mypostForm.do?pageNum=${i }">${i }</a>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="mypostForm.do?pageNum=${startPage + blockSize }">[다음]</a>
				</c:if>
			</div>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>