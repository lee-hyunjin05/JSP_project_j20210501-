<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보확인</title>
</head>
<body>
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			location.href = "mypageForm.do";
		</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("비밀번호가 다릅니다.");
			history.go(-1);
		</script>
	</c:if>
</body>
</html>