<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			alert("변경되었습니다.");
			location.href = "loginForm.do";
		</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("새 비밀번호가 일치하지 않습니다.");
			history.back();
		</script>
	</c:if>
	<c:if test="${result == -1 }">
		<script type="text/javascript">
			alert("현재 비밀번호가 틀렸습니다.");
			history.back();
		</script>
	</c:if>
	<c:if test="${result == -2 }">
		<script type="text/javascript">
			alert("오류가 발생했습니다.");
			history.back();
		</script>
	</c:if>
</body>
</html>