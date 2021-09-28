<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="view_part/header.jsp"%>
<%@ include file="view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
</head>
<body>
	<div id="wrapper">
		<section id="section">
			<div class="main">
				
				<div class="card">
					<div class="board">
						<h1>
							<a href="best_info_travel.do">베스트 게시글</a>
						</h1>
						<c:forEach var="postmember4" items="${bestlist }">
							<div class="best_img">
								<c:choose>
									<c:when test="${postmember4.board_no == 1 }">
										<a  href="content.do?board_no=${postmember4.board_no }&post_no=${postmember4.post_no }&pageNum=${currentPage}">
											<img src="file_upload/${postmember4.attach }" width="220px" height="150px"> <br>
											${postmember4.post_title } [추천수 : ${postmember4.recommend }]
										</a>
									</c:when>
									<c:when test="${postmember4.board_no == 2 }">
										<a href="content.do?board_no=${postmember4.board_no }&post_no=${postmember4.post_no }&pageNum=${currentPage}">
											<img src="file_upload/${postmember4.attach }" width="220px" height="150px"> <br>
											${postmember4.post_title } [추천수 : ${postmember4.recommend }]
										</a>
									</c:when>
									<c:when test="${postmember4.board_no == 3 }">
										<a href="content.do?board_no=${postmember4.board_no }&post_no=${postmember4.post_no }&pageNum=${currentPage}">
											<img src="file_upload/${postmember4.attach }" width="220px" height="150px"> <br>
											${postmember4.post_title } [추천수 : ${postmember4.recommend }]
										</a>
	<%-- 									<a href="content.do?board_no=${postmember4.board_no }&post_no=${postmember4.post_no }&pageNum=${currentPage}">${postmember4.post_title }</a> --%>
									</c:when>
								</c:choose>
							</div>
							<c:set var="startNum3" value="${besttotCnt - 1 }" />
						</c:forEach>
					</div>
				</div>
				<div class="card">
					<div class="board">
						<h1>
							<a href="info_travel.do">여행지 정보 공유</a>
						</h1>
						<c:forEach var="postmember3" items="${list3 }">
							<hr />
							<a href="content.do?board_no=3&post_no=${postmember3.post_no }&pageNum=1">${postmember3.post_title }
								<c:if test="${postmember3.attach != null}">
									<img alt="" src="images/picture.png" width="20px">
								</c:if>
							</a>
							<c:set var="startNum3" value="${totCnt3 - 1 }" />
						</c:forEach>
					</div>
				</div>
				<div class="card">
					<div class="board">
						<h1>
							<a href="market.do">장터</a>
						</h1>
						<c:forEach var="postmember2" items="${list2 }">
							<hr />
							<a href="content.do?board_no=2&post_no=${postmember2.post_no }&pageNum=1">${postmember2.post_title }
								<c:if test="${postmember2.attach != null}">
									<img alt="" src="images/picture.png" width="20px">
								</c:if>
							</a>
							<c:set var="startNum2" value="${totCnt2 - 1 }" />
						</c:forEach>
					</div>
				</div>
				<div class="card">
					<div class="board">
						<h1>
							<a href="matching.do">매칭</a>
						</h1>
						<c:forEach var="postmember1" items="${list1 }">
							<hr />
							<a href="content.do?board_no=1&post_no=${postmember1.post_no }&pageNum=1">${postmember1.post_title }
								<c:if test="${postmember1.attach != null}">
									<img alt="" src="images/picture.png" width="20px">
								</c:if>
							</a>
							<c:set var="startNum1" value="${totCnt1 - 1 }" />
						</c:forEach>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
</html>
<%@ include file="view_part/footer.jsp"%>