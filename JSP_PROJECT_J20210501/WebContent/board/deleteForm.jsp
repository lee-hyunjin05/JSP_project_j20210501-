<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
<link rel="stylesheet" href="css/write_del_up.css" type="text/css">
</head>
<body>
	<c:if test="${check == 1 }">
		<script type="text/javascript">
			alert("삭제된 글입니다.");
			history.back();
		</script>
	</c:if>
	<div id="wrapper">
		<section id="section">
			<c:choose>
				<c:when test="${id eq postmember.member_id }">
					<form action="deletePro.do" method="post">
						<div id="post_title">
							<h2>게시물을 삭제하시려면 암호를 입력하세요</h2>
						</div>
						<input type="hidden" name="id" value="${id }">
						<input type="hidden" name="board_no" value="${board_no }">
						<input type="hidden" name="post_no" value="${postmember.post_no }">
						<input type="hidden" name="pageNum" value="${pageNum }">
						<input type="hidden" name="re_step" value="${re_step }">
						<input type="hidden" name="ref" value="${ref }">
						<table class="del_table">
							<tr>
								<td>
									<div class="center">
									&nbsp;암호&nbsp;<input type="password" name="passwd" autofocus="autofocus">
									<input type="submit" value="확인">
									<input type="button" value="취소" onclick="javascript:history.back();" />
									</div>
								</td>
							</tr>
						</table>
					</form>
				</c:when>
				<c:when test="${sessionScope.auth eq 1 }">
					<form action="deletePro.do" method="post">
						<div id="post_title">
							<h2>게시물을 삭제하시려면 암호를 입력하세요</h2>
						</div>
						<input type="hidden" name="id" value="${id }">
						<input type="hidden" name="board_no" value="${board_no }">
						<input type="hidden" name="post_no" value="${postmember.post_no }">
						<input type="hidden" name="pageNum" value="${pageNum }">
						<input type="hidden" name="re_step" value="${re_step }">
						<input type="hidden" name="ref" value="${ref }">
						<table class="del_table">
							<tr>
								<td>
								<div class="center">
									&nbsp;암호&nbsp;<input type="text" name="passwd" autofocus="autofocus">
									<input type="submit" value="확인">
									<input type="button" value="취소" onclick="javascript:history.back();" />
								</div>
								</td>
							</tr>
						</table>
					</form>
				</c:when>
				<c:otherwise>
					<script type="text/javascript">
						alert("삭제할 권한이 없습니다.");
						history.back();
					</script>
				</c:otherwise>
			</c:choose>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>