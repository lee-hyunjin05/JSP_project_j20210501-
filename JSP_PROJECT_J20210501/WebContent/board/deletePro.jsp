<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${result > 0 }">
		<script type="text/javascript">
			alert("삭제 완료!");
			
			if (${board_no == 1 }){				
				if (${re_step == 0 })
					location.href = "matching.do?pageNum=${pageNum }";
				else
					location.href = "content.do?board_no=${board_no }&post_no=${ref }&pageNum=${pageNum }";
			}
			if (${board_no == 2 }) {
				if (${re_step == 0 })
					location.href = "market.do?pageNum=${pageNum }";
				else
					location.href = "content.do?board_no=${board_no }&post_no=${ref }&pageNum=${pageNum }";
			}
			if (${board_no == 3 }) {
				if (${re_step == 0 })
					location.href = "info_travel.do?pageNum=${pageNum }";
				else
					location.href = "content.do?board_no=${board_no }&post_no=${ref }&pageNum=${pageNum }";
			}
		</script>
	</c:if>
	<c:if test="${result == 0 }">
		<script type="text/javascript">
			alert("비밀번호가 틀렸습니다.");
			location.href = "deleteForm.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }";
		</script>
	</c:if>
	<c:if test="${result < 0 }">
		<script type="text/javascript">
			alert("수정 오류");
			location.href = "deleteForm.do?board_no=${board_no }&post_no=${post_no }&pageNum=${pageNum }";
		</script>
	</c:if>
</body>
</html>