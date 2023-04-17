<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%
	request.setCharacterEncoding("UTF-8");
	// 회원 정보를 표시하기 전에 한글 인코딩을 설정한다.

	String id = (String)request.getAttribute("id");
	String pwd = (String)request.getAttribute("pwd");
	String name = (String)session.getAttribute("name");
	String email = (String)application.getAttribute("email");
	// 각 내장객체에 바인딩된 속성 값들을 getAttribute()메서드를 이용해 가져온다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 출력창</title>
</head>
<body>
	<table border="1"  align="center" >
	    <tr align="center" bgcolor="#99ccff">
	      <td width="20%"><b>아이디</b></td>
	      <td width="20%"><b>비밀번호</b></td>
	      <td width="20%" ><b>이름</b></td>
	      <td width="20%"><b>이메일</b></td>
	   </tr>
	   <tr align=center>
	      <td><%=id %> </td>
	      <td><%=pwd%> </td>
	      <td><%=name %> </td>
	      <td><%=email %> </td>
	      <%-- 표현식으로 회원 정보를 출력한다. --%>
	   </tr>   
	   <tr align=center>
			<td>${id} </td>
			<td>${pwd} </td>
			<td>${name} </td>
			<td>${email}</td>
	      <%-- 자바 코드 없이 바로 바인딩된 속성 이름으로 회원 정보를 출력한다. --%>
	   </tr>
	</table>

</body>
</html>