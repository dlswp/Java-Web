<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON 테스트</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function() {
	    $("#checkJson").click(function() {
	    var jsonStr  = '{"name": "박지성", "age": 25, "gender" : "남자", "nickname" : "날쌘돌이" }'; 
	    
	    var jsonObj = JSON.parse(jsonStr);
	    // 제이쿼리의 JSON 기능인 parse() 메서드를 이용해 JSON 자료형을 가져온다.
	    
	    var output ="회원 정보<br>";
	    output += "=======<br>";
	    
	    output += "이름: " + jsonObj.name + "<br>";
	    output += "나이: " + jsonObj.age + "<br>";
	    output += "성별: " + jsonObj.gender + "<br>";
	    output += "별명: " + jsonObj.nickname + "<br>";
	    // 문자열에서 JSON 객체의 속성을 가져온다.
	    
	    $("#output").html(output);
	    // 회원 이름을 출력한다.
	  });
	});
</script>
</head>
<body>
	<a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <div id="output"></div>
</body>
</html>