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
				<h1><a href="searchForm.do">통합검색</a></h1>
			</div>
			<table class="post_list">
				<tr>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>추천수</th>
				</tr>
				<c:if test="${searchtotCnt > 0 }">
					<c:forEach var="postmember" items="${searchlist }">
						<tr>
							<td class="left">
								<c:choose>
									<c:when test="${postmember.board_no == 1 }">
										<a href="content.do?board_no=${postmember.board_no }&post_no=${postmember.post_no }&pageNum=${currentPage }">[매칭] ${postmember.post_title }
											<c:if test="${postmember.attach != null}">
												<img alt="" src="images/picture.png" width="20px">
											</c:if>
										</a>
									</c:when>
									<c:when test="${postmember.board_no == 2 }">
										<a href="content.do?board_no=${postmember.board_no }&post_no=${postmember.post_no }&pageNum=${currentPage }">[장터] ${postmember.post_title }
											<c:if test="${postmember.attach != null}">
												<img alt="" src="images/picture.png" width="20px">
											</c:if>
										</a>
									</c:when>
									<c:when test="${postmember.board_no == 3 }">
										<a href="content.do?board_no=${postmember.board_no }&post_no=${postmember.post_no }&pageNum=${currentPage }">[정보] ${postmember.post_title }
											<c:if test="${postmember.attach != null}">
												<img alt="" src="images/picture.png" width="20px">
											</c:if>
										</a>
									</c:when>
								</c:choose></td>
							<td>${postmember.nickname }</td>
							<td>${postmember.hits }</td>
							<td>${postmember.recommend }</td>
						</tr>
						<c:set var="startNum" value="${startNum - 1 }" />
					</c:forEach>
				</c:if>
				<c:if test="${searchtotCnt == 0 }">
					<tr>
						<td colspan="4">데이터가 없습니다.</td>
					</tr>
				</c:if>
			</table>
			<table class="post_list">
				<tr><td>
				<c:if test="${startPage > blockSize }">
					<a href="searchPro.do?category=${search_category}&kind=${board_no}&search_text=${search_text}&pageNum=${startPage - blockSize }">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="searchPro.do?category=${search_category}&kind=${board_no}&search_text=${search_text}&pageNum=${i }">${i }</a>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="searchPro.do?category=${search_category}&kind=${board_no}&search_text=${search_text}&pageNum=${startPage + blockSize }">[다음]</a>
				</c:if>
			</td></tr>
			</table>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>