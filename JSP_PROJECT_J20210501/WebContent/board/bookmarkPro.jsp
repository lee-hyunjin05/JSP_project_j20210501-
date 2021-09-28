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
				alert("${id }님 즐겨찾기 취소");
				history.back();
			</script>
		</c:when>
		<c:when test="${check == 1 }">
			<script type="text/javascript">
				alert("${id }님 즐겨찾기 완료");
				history.back();
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