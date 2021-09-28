<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${check == 0 }">
			<script type="text/javascript">
				alert("${id }님 추천 취소");
				location.href = "content.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }";
			</script>
		</c:when>
		<c:when test="${check == 1 }">
			<script type="text/javascript">
				alert("${id }님 추천 완료");
				location.href = "content.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }";s
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				alert("오류 발생!");
				history.back();
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>