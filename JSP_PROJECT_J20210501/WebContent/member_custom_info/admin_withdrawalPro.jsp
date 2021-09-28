<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴 시키기</title>
<script type="text/javascript">
	function wincl() {
		opener.parent.location.reload();
		window.close();
	}
</script>
</head>
<body>
	<c:if test="${wd == 'N' }">
		<c:if test="${result > 0 }">
			<p>탈퇴되었습니다.</p>
			<input type="button" value="닫기" onclick="wincl()" />
		</c:if>
		<c:if test="${result < 0 }">
			<p>오류가 발생했습니다.</p>
			<input type="button" value="닫기" onclick="wincl()" />
		</c:if>
	</c:if>
	<c:if test="${wd == 'Y' }">
		<c:if test="${result > 0 }">
			<p>복구되었습니다.</p>
			<input type="button" value="닫기" onclick="wincl()" />
		</c:if>
		<c:if test="${result < 0 }">
			<p>오류가 발생했습니다.</p>
			<input type="button" value="닫기" onclick="wincl()" />
		</c:if>
	</c:if>
</body>
</html>