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
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			alert("수정 완료! ");
			
			if(${re_step == 0 })
				location.href = "content.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }";
			else
				location.href = "content.do?board_no=${board_no }&post_no=${ref }&pageNum=${pageNum }";
		</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("수정 오류");
			location.href = "updateForm.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }";
		</script>
	</c:if>
</body>
</html>