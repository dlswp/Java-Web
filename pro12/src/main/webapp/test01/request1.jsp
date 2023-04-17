<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="javax.servlet.RequestDispatcher"
    pageEncoding="UTF-8"
    %>
<%
  request.setAttribute("name","이순신");
  request.setAttribute("address","서울시 강남구"); 
  // request 객체에 setAttribute()를 이용해 name과 address를 바인딩한다.
%>       

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>첫 번째 JSP</title>
</head>
<body>

<%
  RequestDispatcher dispatch = request.getRequestDispatcher("request2.jsp");  
  dispatch.forward(request, response);
  // request객체를 다른 JSP로 포워딩한다.
%>
</body>
</html>
