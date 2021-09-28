<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<link rel="stylesheet" href="css/post_style.css" type="text/css" />
<script type="text/javascript">
function winop(member_id) {
	window.open("admin_withdrawalForm.do?id=" + member_id, "", "width=500 height=500");
}
</script>
</head>
<body>
	<div id="wrapper">
		<section id="section">
					<h1>회원관리</h1>
			<table class="post_list">
				<tr><td colspan="10"></td>
				</tr>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>성별</th>
					<th>닉네임</th>
					<th>생일</th>
					<th>전화번호</th>
					<th>가입일</th>
					<th>추천수</th>
					<th>탈퇴여부</th>
				</tr>
				<c:if test="${totCnt > 0 }">
					<c:forEach var="member" items="${list }">
						<tr>
							<td>${startNum }</td>
							<td>${member.member_id }</td>
							<td>${member.member_name }</td>
							<td>${member.gender }</td>
							<td>${member.nickname }</td>
							<td>${member.birth }</td>
							<td>${member.tel }</td>
							<td>${member.reg_date }</td>
							<td>${member.rec }</td>
							<td><a href="#" onclick="winop('${member.member_id }')">${member.withdrawal }</a></td>
						</tr>
						<c:set var="startNum" value="${startNum - 1 }"></c:set>
					</c:forEach>
				</c:if>
				<c:if test="${totCnt == 0 }">
					<tr>
						<td colspan="10">회원이 없습니다.</td>
					</tr>
				</c:if>
			</table>
			<div style="text-align: center;">
				<c:if test="${startPage > blockSize }">
					<a href="manageForm.do?pageNum=${startPage - blockize }">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="manageForm.do?pageNum=${i }">[${i }]</a>
				</c:forEach>
				<c:if test="${endPage < pageCnt }">
					<a href="manageForm.do?pageNum=${startPage + blockSize }">[다음]</a>
				</c:if>
			</div>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>