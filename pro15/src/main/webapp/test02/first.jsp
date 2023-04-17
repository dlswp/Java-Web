<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="result.jsp">
		<input type="hidden" name="param1" value="duke.png" /> <br>
		<input type="hidden" name="param2" value="duke2.png" /> <br>
		<%-- 다운로드할 파일 이름을 매개변수로 전달한다. duke.png나 duke2.png가 아닌 다른 파일을 업로드 했다면 해당 파일이름으로 수정하세요 --%>
		<input type="submit" value="이미지 다운로드">
	</form>
</body>
</html>