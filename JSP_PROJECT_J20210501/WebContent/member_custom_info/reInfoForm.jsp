<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<link rel="stylesheet" href="css/post_style1.css" type="text/css" />
<script type="text/javascript">
	function winop_nick() {
		if (!frm.nickname.value) {
			alert("닉네임을 입력하세요.");
			frm.nickname.focus();
			
			return false;
		}
		
		window.open("confirmNickPro.do?nickname=" + frm.nickname.value, "", "width=500 height=200");
	}
	function setThumbnail(event) {
			var reader = new FileReader();
			reader.onload = function(event) {
								var img = document.createElement("img");
// 								var img1 = document.createElement("width");
								img.setAttribute("src", event.target.result);
// 								img1.setAttribute("width", 200px);
								document.querySelector("div#image_container").appendChild(img);
							};
			
			reader.readAsDataURL(event.target.files[0]); 
	 }
</script>
</head>
<body>
	<div id="wrapper">
		<section id="section">
			<div>
				<form action="reInfoPro.do?id=${member.member_id }" method="post" name="frm" enctype="multipart/form-data">
					<h1>회원정보수정</h1>
					<table class="post_list">
						<tr>
							<th>아이디</th>
							<td class="reinfo">${member.member_id }</td>
						</tr>
						<tr>
							<th>이름</th>
							<td class="reinfo"><input type="text" name="name" value="${member.member_name }" /></td>
						</tr>
						<tr>
							<th>프로필 사진</th>
							<td class="reinfo">
								<input type="file" id="image" accept="image/*" onchange="setThumbnail(event);" name="profile_img" value="${member.profile_img }"/> 
								<div id="image_container">
								</div>
							</td>
						</tr>
						<tr>
							<th>닉네임</th>
							<td class="reinfo">
								<input type="text" name="nickname" value="${member.nickname }" />
								<input type="button" value="중복체크" onclick="winop_nick() " class="button2"/>
							</td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td class="reinfo">
								<input type="tel" name="tel1" pattern="\d{2, 3}" maxlength="3" size="1" value="${tel1 }" />
								-
								<input type="tel" name="tel2" pattern="\d{3, 4}" maxlength="4" size="2" value="${tel2 }" />
								-
								<input type="tel" name="tel3" pattern="\d{4}" maxlength="4" size="2" value="${tel3 }" />
							</td>
						</tr>
						<tr>
							<th>자기소개</th>
							<td class="reinfo">
								<textarea name="intro" cols="30" rows="10">${member.intro }</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="수정" class="button"/>
								<input type="reset" value="취소" onclick="javascript:history.back();" class="button"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>