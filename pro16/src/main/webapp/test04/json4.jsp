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
	    var jsonStr  = '{"members":[{"name" : "박지성", "age": "25", "gender" : "남자", "nickname" : "날쌘돌이"}'
	    	+ ', {"name" : "손흥민", "age": "30", "gender" : "남자", "nickname" : "탱크"}] }'; 
	    // members배열에 회원정보를 객체의 name/value 쌍으로 저장한다.
	    	
	    var jsonInfo = JSON.parse(jsonStr);
	    // 제이쿼리의 JSON 기능인 parse() 메서드를 이용해 JSON 자료형을 가져온다.
	    
	    var output ="회원 정보<br>";
	    output += "=======<br>";
	    
	    for (var i in jsonInfo.members){
	    	output += "이름: " + jsonInfo.members[i].name + "<br>";
		    output += "나이: " + jsonInfo.members[i].age + "<br>";
		    output += "성별: " + jsonInfo.members[i].gender + "<br>";
		    output += "별명: " + jsonInfo.members[i].nickname + "<br><br><br>";
		    // 각 배열 요소에 접근하여 객체의 name으로 value를 출력한다.
	    }
	    
	    
	    
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