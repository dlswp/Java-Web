<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String name=(String)session.getAttribute("name");
String address = (String)session.getAttribute("address");
// session객체에 바인딩된 name값(SessionTest.java참조) 이순신과 address값(session1.jsp참조) 서울시 강남구를 가져옵니다.

%>    
 
<!DOCTYPE html>     
<html>
<head>
<meta charset="UTF-8">
<title>session 내장 객체 테스트2</title>
</head>
<body>
이름은 <%=name %>입니다.<br>
주소는 <%=address %>입니다. 
</body>
</html>
