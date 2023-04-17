<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 	request.setCharacterEncoding("utf-8");
	//회원 정보를 표시하기 전에 한글 인코딩을 설정한다.

	request.setAttribute("id","hong");
  	request.setAttribute("pwd", "1234");
 	// request 내장 객체에 바인딩합니다.
  	
  	session.setAttribute("name", "홍길동");
  	// session 내장 객체에 바인딩합니다.
  	
  	application.setAttribute("email", "hong@test.com");
	// application 내장 객체에 바인딩합니다.
%>    

<html>
<head>
<meta  charset=”UTF-8">
<title>forward1</title>
</head>
<body>
   <jsp:forward page="member1.jsp" />
   <%-- member1.jsp로 포워딩한다. --%>
</html>
