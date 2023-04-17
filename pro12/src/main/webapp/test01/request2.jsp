<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   String name=(String)request.getAttribute("name");
   String address=(String )request.getAttribute("address");
   // 첫 번째 JSP 페이지에서 포워딩된 request객체에서 getAttribute()를 이용해 정보를 가져온다.
%> 

<!DOCTYPE html>      
<html>
<head>
<meta charset="UTF-8">
<title>두 번째 JSP</title>
</head>
<body>
   <h1>이름은 <%=name %>입니다.</h1>
   <h1>주소는 <%=address %>입니다.</h1>
   <!-- 이전 JSP에서 전송된 정보를 출력한다. -->
</body>
</html>
