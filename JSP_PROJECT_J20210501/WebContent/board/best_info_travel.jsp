<%@page import="dao.PostMember"%>
<%@page import="java.util.List"%>
<%@page import="dao.PostMember_dao"%>
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
				<h1>
					<a href="best_info_travel.do">베스트 여행지 게시판</a>
				</h1>
			</div>
			<div></div>
			<table class="post_list">
				<tr>
					<th>&nbsp;게시판</th>
					<th class="title_width">제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>추천수</th>
				</tr>
				<c:if test="${besttotCnt > 0 }">
					<c:forEach var="postmember" items="${bestlist }">
						<tr>
							<td>
								<c:choose>
									<c:when test="${postmember.board_no == 1 }">&nbsp;[매칭]</c:when>										
									<c:when test="${postmember.board_no == 2 }">&nbsp;[장터]</c:when>										
									<c:when test="${postmember.board_no == 3 }">&nbsp;[정보]</c:when>										
								</c:choose>
							</td>
							<td class="left">
								<c:if test="${postmember.recommend >= 20 }">
									<img src="images/hot.gif">
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
				<c:if test="${besttotCnt == 0 }">
					<tr>
						<td>데이터가 없습니다.</td>
					</tr>
				</c:if>
			</table>
			<div style="text-align: center;">
				<c:if test="${startPage > blockSize }">
					<a href="best_info_travel.do?pageNum=${startPage - blockSize }">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="best_info_travel.do?pageNum=${i }">${i } </a>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="best_info_travel.do?pageNum=${startPage + blockSize }">[다음]</a>
				</c:if>
			</div>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>