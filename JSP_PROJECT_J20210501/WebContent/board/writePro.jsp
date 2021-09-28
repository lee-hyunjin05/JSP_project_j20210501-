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
	<form action="">
		<c:if test="${result > 0 }">
			<script type="text/javascript">
				alert("입력 완료");

				if (${board_no } == 1)
					location.href = "matching.do?pageNum=${pageNum }";
				if (${board_no } == 2)
					location.href = "market.do?pageNum=${pageNum }";
				if (${board_no } == 3)
					location.href = "info_travel.do?pageNum=${pageNum }";
			</script>
		</c:if>
		<c:if test="${result == 0 }">
			<script type="text/javascript">
				alert("입력 오류");
				location.href = "writeForm.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }";
			</script>
		</c:if>
	</form>
</body>
</html>