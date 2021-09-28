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
<link rel="stylesheet" href="css/write_del_up.css" type="text/css">
</head>
<body>
	<div id="wrapper">
		<section id="section">
			<c:if test="${id == null }">
				<script type="text/javascript">
					alert("로그인이 필요합니다.");
					history.back();
				</script>
			</c:if>
			<c:if test="${id != null }">
				<form action="writePro.do?pageNum=${pageNum }" method="post" enctype="multipart/form-data">
					<div id="post_title">
						<h1>
							<c:choose>
								<c:when test="${board_no == 1 }">
									<a href="matching.do">매칭 게시판</a>
								</c:when>
								<c:when test="${board_no == 2 }">
									<a href="market.do">장터 게시판</a>
								</c:when>
								<c:when test="${board_no == 3 }">
									<a href="info_travel.do">여행지 정보 제공 게시판</a>
								</c:when>
							</c:choose>
						</h1>
					</div>
					<div class="content">
						<input type="hidden" name="id" value="${id }">
						<input type="hidden" name="board_no" value="${board_no}">
						<input type="hidden" name="post_no" value="${post_no}">
						<input type="hidden" name="nickname" value="${nickname }">
						<input type="hidden" name="ref" value="${ref }">
						<input type="hidden" name="re_level" value="${re_level}">
						<input type="hidden" name="re_step" value="${re_step}">
						<table class="select_table">
						<tr><td><br></td></tr>
							<tr>
								<th>장소</th>
								<td>바다<input type="checkbox" name="category" value="바다" checked="checked"></td>
								<td>산&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="category" value="산"></td>
								<td>도심<input type="checkbox" name="category" value="도심"></td>
							</tr>
							<tr>
								<th>목적</th>
								<td>힐링<input type="checkbox" name="category" value="힐링" checked="checked"></td>
								<td>관광&nbsp;&nbsp;&nbsp;<input type="checkbox" name="category" value="관광"></td>
								<td>맛집 <input type="checkbox" name="category" value="맛집"></td>
							</tr>
							<tr>
								<th>교통</th>
								<td>도보<input type="checkbox" name="category" value="도보" checked="checked"></td>
								<td>자동차<input type="checkbox" name="category" value="자동차"></td>
								<td>기차<input type="checkbox" name="category" value="기차"></td>
							</tr>
							<tr>
								<th>인원모집</th>
								<td>카풀<input type="checkbox" name="category" value="카풀" checked="checked"></td>
								<td>가이드 <input type="checkbox" name="category" value="가이드"></td>
								<td>친구 <input type="checkbox" name="category" value="친구"></td>
							</tr>
							<tr><td><br></td></tr>
							</table>
							<table class="write_table">
							<tr><td colspan="2"><br></td></tr>
							<tr>
								<td>
									&nbsp;&nbsp;제목&nbsp;&nbsp;&nbsp;<input type="text" name="post_title" required="required" size="40px">
								</td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;<input type="file" name="attach" accept="image/*" /></td>
							</tr>
							<tr>
								<td class="center"><textarea rows="30" cols="156" name="content" required="required"></textarea></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;암호&nbsp;&nbsp;&nbsp;<input type="password" name="passwd" required="required"></td>
							</tr>
							<tr>
								<td class="right">
									<input type="submit" value="확인">
									<input type="reset" value="다시작성">
								</td>
							</tr>
						</table>
					</div>
				</form>
			</c:if>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>