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
	    var jsonStr  = '{"age": [22, 33, 44] }'; 
	    // 정수형 데이터를 가지는 이름인 age인 배열을 선언한다.
	    
	    var jsonInfo = JSON.parse(jsonStr);
	    // 제이쿼리의 JSON 기능인 parse() 메서드를 이용해 JSON 자료형을 가져온다.
	    
	    var output ="회원 나이<br>";
	    output += "=======<br>";
	    
	    for(var i in jsonInfo.age) {
	        output += jsonInfo.age[i]+"<br>";
	    }
	    // 배열 요소(나이)를 차례대로 출력한다.
	    
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