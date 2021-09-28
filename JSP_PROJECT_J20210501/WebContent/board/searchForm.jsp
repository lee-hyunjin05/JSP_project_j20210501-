<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../view_part/header.jsp"%>
<%@ include file="../view_part/aside.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/search.css" type="text/css">
</head>
<body>
	<div id="wrapper">
		<section id="section">
			<form action="searchPro.do" method="post">
				<div id="post_title">
					<h1><a href="searchForm.do">통합검색</a></h1>
				</div>
				<table class="search_table">
				<tr><td></td></tr>
					<tr>
						<th>장소</th>
						<td>바다<input type="checkbox" name="category" value="바다" checked="checked"></td>
						<td>산&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="category" value="산"></td>
						<td>도심<input type="checkbox" name="category" value="도심"></td>
					</tr>
					<tr>
						<th>목적</th>
						<td>힐링<input type="checkbox" name="category" value="힐링" checked="checked"></td>
						<td>관광&nbsp;&nbsp;&nbsp;<input type="checkbox" name="category" value="관광"></td>
						<td>맛집<input type="checkbox" name="category" value="맛집"></td>
					</tr>
					<tr>
						<th>교통</th>
						<td>도보<input type="checkbox" name="category" value="도보" checked="checked"></td>
						<td>자동차<input type="checkbox" name="category" value="자동차"></td>
						<td>기차<input type="checkbox" name="category" value="기차"></td>
					</tr>
					<tr>
						<th>인원모집</th>
						<td>카풀<input type="checkbox" name="category" value="카풀" checked="checked"></td>
						<td>가이드<input type="checkbox" name="category" value="가이드"></td>
						<td>친구<input type="checkbox" name="category" value="친구"></td>
					</tr>
					<tr>
						<th>게시판</th>
						<td colspan="2">
							<select name="kind" class="select">
								<option value="1" selected="selected">매칭</option>
								<option value="2">장터</option>
								<option value="3">정보</option>
							</select>
						</td>
					</tr>
			</table>
			<table class="search_text">
				<tr>
					<td>
							<input type="text" name="search_text" id="search_input_text">
							<input type="submit" name="" id="serch_but" value="검색">
						</td>
					</tr>
					<tr><th></th></tr>
				</table>
			</form>
		</section>
	</div>
</body>
<%@ include file="../view_part/footer.jsp"%>
</html>