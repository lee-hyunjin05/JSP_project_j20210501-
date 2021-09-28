<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mb_pf.css" type="text/css" />
</head>
<body>
	<div class="pfwrap">
		<div class="pf_image">
			<c:if test="${other_member.profile_img == null }">
				<c:if test="${other_member.gender == '남자' }">
					<img src="images/profile_img_m.png" width="200px" height="200px" />
				</c:if>
				<c:if test="${other_member.gender == '여자' }">
					<img src="images/profile_img_f.png" width="200px" height="200px" />
				</c:if>
			</c:if>
			<c:if test="${other_member.profile_img != null }">
				<img id="user" src="${upLoadFilename }" width="200px" height="200px" />					
			</c:if>
		</div>
		<div class="pf_content">
			<form action="">
				<table>
					<tr>
						<th width="100px">닉네임</th>
						<th width="150px">${other_member.nickname }</th>
					</tr>
					<tr>
						<th>총 추천수</th>
						<th>${other_member.rec }</th>
					</tr>
					<tr>
						<th width="100px">칭호</th>
						<th width="150px">${user_title }</th>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<th colspan="2">
							<a href="dmwriteForm.do?other_id=${other_member.member_id }" onclick="window.open(this.href,'','width=600px, height=480px'); return false;">
							<input type="button" value="쪽지보내기"></a>
						</th>
					</tr>
					<tr>
						<th></th>
					</tr>
					<tr>
						<th colspan="2">
							<input type="button" value="닫기" onclick="window.close()">
						</th>
					</tr>

				</table>
			</form>
		</div>
	</div>
</body>
</html>