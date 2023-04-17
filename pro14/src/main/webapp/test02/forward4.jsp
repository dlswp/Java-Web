<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%
 	request.setCharacterEncoding("utf-8");
	//회원 정보를 표시하기 전에 한글 인코딩을 설정한다.

	request.setAttribute("id", "hong");
	request.setAttribute("pwd", "1234");
	session.setAttribute("name", "홍길동");
	application.setAttribute("email", "hong@test.com");
	request.setAttribute("address", "서울시 강남구");
	// request에 address 속성 이름으로 바인딩합니다.
%>    

<html>
<head>
<meta  charset=”UTF-8">
<title>forward4</title>
</head>
<body>
   <jsp:forward page="member4.jsp" />
   <%-- member3.jsp로 포워딩한다. --%>
</html>
