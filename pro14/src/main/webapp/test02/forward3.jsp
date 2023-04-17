<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, sec01.ex01.*"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%
 	request.setCharacterEncoding("utf-8");
	//회원 정보를 표시하기 전에 한글 인코딩을 설정한다.

	List membersList = new ArrayList();
	// ArrayList 객체를 생성합니다.
	
	MemberBean m1 = new MemberBean("lee","1234","이순신","lee@test.com");
	MemberBean m2 = new MemberBean("son","1234","손흥민","son@test.com");
	// MemberBean 객체를 생성한 후 두명의 회원 정보를 저장한다.
	
	membersList.add(m1);
	membersList.add(m2);
	// 두 개의 MemberBean 객체를 ArrayList에 저장한다.
	
	request.setAttribute("membersList", membersList);
	// request 내장 객체에 ArrayList를 속성 이름 membersList로 바인딩합니다.
%>    

<html>
<head>
<meta  charset=”UTF-8">
<title>forward3</title>
</head>
<body>
   <jsp:forward page="member3.jsp" />
   <%-- member3.jsp로 포워딩한다. --%>
</html>
