<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%!
	String name = "이순신";
	public String getName(){ return name;}
%>
  
<% String age = request.getParameter("age"); %>
<!-- 스크립트릿을 이용해 자바 코드를 작성한다. -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트릿 연습</title>
</head>
<body>

	<h1>안녕하세요 이순신님!!</h1>
	<h1>나이는 <%=age %>입니다.!!</h1>
	<!-- 표현식을 이용해 전송된 나이를 출력한다. -->
</body>
</html>