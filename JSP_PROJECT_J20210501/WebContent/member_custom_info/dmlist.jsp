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
				<h1><a href="dmlist.do?id=${id }&pageNum=1">${member.nickname } 님에게 온 DM 목록</a></h1>
			</div>
			<table class="post_list">
				<tr>
					<th>보낸사람</th>
					<th class="title_width4">내용</th>
					<th>받은시간</th>
				</tr>
				<c:if test="${totCnt > 0 }">
					<c:forEach var="message" items="${list }">
						<tr>
							<td><a href="memberpage.do?id=${id }&member_id=${message.sender_id }" onclick="window.open(this.href,'','width=400px, height=500px'); return false;">${message.nickname }</a></td>
							<td class="left">${message.content }</td>
							<td>${message.send_time }</td>
						</tr>
						<c:set var="startNum" value="${startNum - 1 }" />
					</c:forEach>
				</c:if>
				<c:if test="${totCnt == 0 }">
					<tr>
						<td colspan="3">데이터가 없습니다.</td>
					</tr>
				</c:if>
			</table>
			<div style="text-align: center;">
				<c:if test="${startPage > blockSize }">
					<a href="dmlist.do?id=${id }&pageNum=${startPage - blockSize }">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="dmlist.do?id=${id }&pageNum=${i }">${i }</a>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="dmlist.do?id=${id }&pageNum=${startPage + blockSize }">[다음]</a>
				</c:if>
			</div>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>