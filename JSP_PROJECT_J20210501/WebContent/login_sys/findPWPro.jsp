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
	<c:if test="${!pw.isEmpty() }">
		<script type="text/javascript">
			alert("비밀번호 : ${pw }");
			location.href = "loginForm.do";
		</script>
	</c:if>
	<c:if test="${pw.isEmpty() }">
		<script type="text/javascript">
			alert("해당 정보로 비밀번호를 찾을 수 없습니다.");
			history.back();
		</script>
	</c:if>
</body>
</html>