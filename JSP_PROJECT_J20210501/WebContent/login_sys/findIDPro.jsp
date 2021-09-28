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
	<c:if test="${!id.isEmpty() }">
		<script type="text/javascript">
			alert("아이디 : ${id }");
			location.href = "loginForm.do";
		</script>
	</c:if>
	<c:if test="${id.isEmpty() }">
		<script type="text/javascript">
			alert("해당 정보로 가입된 아이디가 없습니다.");
			history.back();
		</script>
	</c:if>
</body>
</html>