<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			alert("수정되었습니다.");
			location.href = "mypageForm.do?id=${id }";
		</script>
	</c:if>
	<c:if test="${result < 1 }">
		<script type="text/javascript">
			alert("오류가 발생했습니다.");
			history.back();
		</script>
	</c:if>
</body>
</html>