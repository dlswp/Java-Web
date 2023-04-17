<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="sec01.ex01.*"
    pageEncoding="UTF-8"%>
<%
 	request.setCharacterEncoding("utf-8");
	//회원 정보를 표시하기 전에 한글 인코딩을 설정한다.

	MemberBean member = new MemberBean("lee","1234","이순신","lee@test.com");
	request.setAttribute("member", member);
%>    

<html>
<head>
<meta  charset=”UTF-8">
<title>forward2</title>
</head>
<body>
   <jsp:forward page="member2.jsp" />
   <%-- member2.jsp로 포워딩한다. --%>
</html>
