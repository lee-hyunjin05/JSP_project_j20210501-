<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<%
String context = request.getContextPath();
%>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/content.css" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
// function scrap(check) {
// 	location.href = "m_bookmark.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }";

//     /* 	이미 존재한 것을 삭제한 하러 감  */
// 	if (check == 1) {
// 		alert("${id }님 즐겨찾기 완료");
// 	} else {
// 		alert("${id }님 즐겨찾기 취소");
// 	}
// }

// function recommend(result) {
// 	location.href='m_recommend.do?board_no=${board_no }&post_no=${postmember.post_no }&id=${sessionScope.id }';

//     /* 	이미 존재한 것을 삭제한 하러 감  */
// 	if (result == 0) {
// 		alert("${sessionScope.id }님은 이 글을 더이상 추천 할 수 없습니다.");
// 	} else {
// 		alert("${sessionScope.id }님이 추천하였습니다.");
// 	}
// }
function recomment_btn() {
	if (${id == null}) {
		alert("로그인이 필요합니다");
		
		return false;
	}
	
	var board_no_text = ${postmember.board_no }
	var post_no_text = ${postmember.post_no }
	var post_title_text = '[댓글]' + $('#re_title').val();	// 문자열
	var ref_text = ${postmember.ref } 						// 숫자형만 가능
	var re_step_text = ${postmember.re_step }
	var re_level_text = ${postmember.re_level }
	var member_id_text = "${member.member_id }"
	var passwd_text = $("#recomment_pw").val();
	var recomment_text = $("#recomment").val();
	
// 	alert('recomment_btn board_no_text : ' + board_no_text);
// 	alert('recomment_btn post_no_text : ' + post_no_text);
// 	alert('recomment_btn post_title_text : ' + post_title_text);
// 	alert('recomment_btn ref_text : ' + ref_text);
// 	alert('recomment_btn re_step_text : ' + re_step_text);
// 	alert('recomment_btn re_level_text : ' + re_level_text);
// 	alert('recomment_btn member_id_text : ' + member_id_text);
// 	alert('recomment_btn passwd_text_text : ' + passwd_text);
// 	alert('recomment_btn recomment_text : ' + recomment_text);

	$.ajax({
		url: "<%=context%>/ajax_m_recomment.do",
		data: {
			board_no : board_no_text,
			post_no : post_no_text,
			post_title : post_title_text,
			ref : ref_text,
			re_step : re_step_text,
			re_level : re_level_text,
			member_id : member_id_text,
			passwd : passwd_text,
			content : recomment_text
		},
		dataType:'text',
		success:function(data){
// 			console.log("댓글작성 성공");
// 			alert("댓글작성 ajax Data"+data);
// 			$('#deptName').val(data);
// 			$('#deptName').html(data);
			location.reload();
		}
	});
}

function re_recomment_btn(reply_count) {
	if (${id == null}) {
		alert("로그인이 필요합니다");
		
		return false;
	}
	
	var board_no_text = ${postmember.board_no }
	var post_no_text = ${postmember.post_no }
	var post_title_text = '[대댓글]' + $('#re_re_title' + reply_count).val();	// 문자열
	var ref_text = ${postmember.ref } 										// 숫자형만 가능
	var re_step_text = $('#re_re_step' + reply_count).val();
	var re_level_text = 1
	var member_id_text = "${member.member_id }"
	var passwd_text = $("#re_recomment_pw" + reply_count).val();
	var recomment_text = $("#re_recomment" + reply_count).val();
	
// 	alert('re_recomment_btn board_no_text : ' + board_no_text);
// 	alert('re_recomment_btn post_no_text : ' + post_no_text);
// 	alert('re_recomment_btn post_title_text : ' + post_title_text);
// 	alert('re_recomment_btn ref_text : ' + ref_text);
// 	alert('re_recomment_btn re_step_text : ' + re_step_text);
// 	alert('re_recomment_btn re_level_text : ' + re_level_text);
// 	alert('re_recomment_btn member_id_text : ' + member_id_text);
// 	alert('re_recomment_btn passwd_text_text : ' + passwd_text);
// 	alert('re_recomment_btn recomment_text : ' + recomment_text);

	$.ajax({
		url: "<%=context%>/ajax_m_re_recomment.do",
		data: {
			board_no : board_no_text,
			post_no : post_no_text,
			post_title : post_title_text,
			ref : ref_text,
			re_step : re_step_text,
			re_level : re_level_text,
			member_id : member_id_text,
			passwd : passwd_text,
			content : recomment_text
		},
		dataType:'text',
		success:function(data){
			location.reload();
		}
	});
}

function hide_show_btn(reply_count) {	
	status = $(".hide"+reply_count).css("display"); 

	if (status == "none")
		$(".hide"+reply_count).css("display", ""); 
	else
		$(".hide"+reply_count).css("display", "none"); 
}
</script>
</head>
<body>
	<div id="wrapper">
		<section id="section">
			<form action="content.do" method="post">
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
								<a href="info_travel.do">여행지 정보 제공</a>
							</c:when>
						</c:choose>
					</h1>
				</div>
				<div class="content">
					<table class="content_table">
						<tr>
							<td colspan="4">
								<c:forTokens items="${postmember.category }" delims=" " var="ca" >
									#${ca }&nbsp;&nbsp;&nbsp;
								</c:forTokens>
							</td>
						</tr>
						<tr>
							<td class="left">
							<c:if test="${postmember.recommend > 20 }">
									<img src="images/hot.gif">
							</c:if>
								${postmember.post_title }</td>
							<td>닉네임 : ${member.nickname }</td>
							<td>조회수 : ${postmember.hits }</td>
							<td>추천수 : ${postmember.recommend }</td>
						</tr>
						<tr>
							<td colspan="4">
								<c:if test="${postmember.attach == null }">
									<img src="images/header_img.jpg" width="1120px">
								</c:if>
								<c:if test="${postmember.attach != null }">
									<img src="<%=context %>/${upLoadFilename}" width="1120px">
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="left" id="content_pading">${postmember.content }</td>
						</tr>
						<tr>
							<td colspan="4" class="center">
								<input type="hidden" name="board_no" value="${board_no }">
								<input type="hidden" name="post_no" value="${post_no }">
								<input type="hidden" name="id" value="${sessionScope.id }">
								<input type="hidden" name="pageNum" value="${pageNum }">
<%-- 								<input type="button" value="추천" onclick="javascript:recommend(${result });"> --%>
								<input type="button" value="추천" onclick="location.href='recommendPro.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }'">
<%-- 								<input type="button" value="스크랩" onclick="javascript:scrap(${check });"> --%>
								<input type="button" value="스크랩" onclick="location.href='bookmarkPro.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }'">
								<input type="button" value="수정" onclick="location.href='updateForm.do?board_no=${board_no }&post_no=${postmember.post_no }&pageNum=${pageNum }&re_step=${postmember.re_step }&ref=${postmember.ref }'">
								<input type="button" value="삭제" onclick="location.href='deleteForm.do?board_no=${board_no }&post_no=${postmember.post_no }&pageNum=${pageNum }&re_step=${postmember.re_step }&ref=${postmember.ref }'">
								<c:choose>
									<c:when test="${board_no == 1 }">
										<input type="button" value="목록" onclick="location.href='matching.do?pageNum=${pageNum }'">
									</c:when>
									<c:when test="${board_no == 2 }">
										<input type="button" value="목록" onclick="location.href='market.do?pageNum=${pageNum }'">
									</c:when>
									<c:when test="${board_no == 3 }">
										<input type="button" value="목록" onclick="location.href='info_travel.do?pageNum=${pageNum }'">
									</c:when>
								</c:choose>
							</td>
						</tr>
					</table>
					<!-- 댓글 작성 -->
					<table class="comment_write">
						<tr>
							<td colspan="2"><h3>댓글( ${replytotCnt } )</h3></td>
						</tr>
						<tr>
							<td>
								<input type="hidden" id="re_title" value="${postmember.post_title }" >
								내용&nbsp;<textarea rows="3px" cols="80px" name="content" id="recomment"></textarea>
								암호&nbsp;<input type="password" id="recomment_pw"><br>
							</td>
							<td>
								
								<input type="submit" value="댓글 등록" onclick="recomment_btn()">
							</td>
						</tr>
					</table>
					<!-- 댓글 작성자만 수정, 삭제 가능하게 나중에 수정 -->
					<table class="content_rewrite">
						<tr>
							<td>작성자</td>
							<td>댓글 내용</td>
							<td></td>
						</tr>
						<c:if test="${replytotCnt > 0 }">
							<c:forEach var="postmember" items="${list }" varStatus="status">
								<tr>
									<td>
										<c:if test="${postmember.re_level > 1 }">
											<img src="images/대댓글.png" width="20px">
										</c:if> ${postmember.nickname }
									</td>
									<td>${postmember.content }</td>
									<td>
										<div class="re_btn">
										<a href="updateForm.do?board_no=${board_no }&post_no=${postmember.post_no }&pageNum=${pageNum }&re_step=${postmember.re_step }&ref=${postmember.ref }"><input type="button" value="수정"></a>
										<a href="deleteForm.do?board_no=${board_no }&post_no=${postmember.post_no }&pageNum=${pageNum }&re_step=${postmember.re_step }&ref=${postmember.ref }"><input type="button" value="삭제"></a>
										<c:if test="${postmember.re_level == 1 }">
											<input type="button" value="답글" name="seek_${status.count }" onclick="hide_show_btn('${status.count }')">
										</c:if>
										</div>
									</td>
								</tr>
								<tr class="hide${status.count }" style="display: none"> 
									<td>
										내용<br><br>
										암호
									</td>
									<td>
										<input type="hidden" id="re_re_step${status.count }" value="${postmember.re_step }">
										<input type="hidden" id="re_re_title${status.count }" value="${postmember.post_title }">
										<textarea rows="3" cols="40px" name="content" id="re_recomment${status.count }"></textarea><br>
										<input type="password" id="re_recomment_pw${status.count }">
									</td>
									<td class="center">
										<input type="submit" value="대댓글 등록" onclick="re_recomment_btn(${status.count })" class="recomment_btn">
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</form>
		</section>
	</div>
</body>
</html>
<%@ include file="../view_part/footer.jsp"%>