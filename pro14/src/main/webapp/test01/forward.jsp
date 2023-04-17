<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	request.setAttribute("address", "서울시 강남구");
	// 회원 가입창의 request에 대해 다시 주소 정보를 바인딩합니다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward</title>
</head>
<body>
	<jsp:forward page="member2.jsp"></jsp:forward>
	<%--member2.jsp로 포워딩합니다. --%>
</body>
</html>