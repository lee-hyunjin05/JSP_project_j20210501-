<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
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
					<form action="updatePro.do" method="post">
						<input type="hidden" name="pageNum" value="${pageNum }">
						<div id="post_title">
							<c:if test="${re_step == 0 }">
								<h1>게시글 수정</h1>
							</c:if>
							<c:if test="${re_step != 0 }">
								<h1>댓글 수정</h1>
							</c:if>
						</div>
						<div class="content">
							<input type="hidden" name="board_no" value="${postmember.board_no }">
							<input type="hidden" name="post_no" value="${postmember.post_no }">
							<input type="hidden" name="re_step" value="${re_step }">
							<input type="hidden" name="ref" value="${ref }">
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
							<table class="update_table">
								<tr>
									<td>
										<input type="hidden" name="member_id" value="${postmember.member_id }"><br>
									</td>
								</tr>
<!-- 								<tr> -->
<%-- 									<td><input type="file" name="attach" accept="image/*" value="${postmember.attach }"></td> --%>
<!-- 								</tr> -->
								<tr>
									<td>&nbsp;제목 :&nbsp;<input type="hidden" size="40px" name="post_title" required="required" value="${postmember.post_title }" class="left">${postmember.post_title }</td>
								</tr>
								<tr>
									<td class="center">
										<textarea rows="30" cols="156" name="content" required="required" class="left">${postmember.content }</textarea>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;암호&nbsp;<input type="password" name="passwd" required="required" value="${postmember.passwd }">
										<input type="submit" value="수정완료" style="right: 0px;">
										<input type="button" value="취소" onclick="javascript:history.back();" />
									</td>
								</tr>
							</table>
						</div>
					</form>
				</c:when>
				<c:when test="${sessionScope.auth eq 1 }">
					<form action="updatePro.do" method="post">
						<input type="hidden" name="pageNum" value="${pageNum }">
						<div id="post_title">
							<c:if test="${re_step == 0 }">
								<h1>게시글 수정</h1>
							</c:if>
							<c:if test="${re_step != 0 }">
								<h1>댓글 수정</h1>
							</c:if>
						</div>
						<div class="content">
							<input type="hidden" name="board_no" value="${postmember.board_no }">
							<input type="hidden" name="post_no" value="${postmember.post_no }">
							<input type="hidden" name="re_step" value="${re_step }">
							<input type="hidden" name="ref" value="${ref }">
							<table class="update_table">
								<tr>
									<td>
										<input type="hidden" name="member_id" value="${postmember.member_id }"><br>
									</td>
								</tr>
								<tr>
									<td><input type="file" name="attach" accept="image/*" value="${postmember.attach }"></td>
								</tr>
								<tr>
									<td>&nbsp;제목&nbsp;<input type="text" size="40px" name="post_title" required="required" value="${postmember.post_title }" class="left"></td>
								</tr>
								<tr>
									<td class="center">
										<textarea rows="30" cols="156" name="content" required="required" class="left">${postmember.content }관리자에 의해 수정되었습니다.</textarea>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;암호&nbsp;<input type="password" name="passwd" required="required" value="${postmember.passwd }">
										<input type="submit" value="수정완료" style="right: 0px;">
										<input type="button" value="취소" onclick="javascript:history.back();" />
									</td>
								</tr>
							</table>
						</div>
					</form>
				</c:when>
				<c:otherwise>
					<script type="text/javascript">
						alert("수정 권한이 없습니다.");
						history.back();
					</script>
				</c:otherwise>
			</c:choose>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>