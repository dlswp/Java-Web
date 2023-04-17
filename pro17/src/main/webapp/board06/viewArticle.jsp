<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
<meta charset="UTF-8">
<title>글보기</title>
<script type="text/javascript">
	function backToList(obj){
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
	
	<%-- 텍스트 박스의 id로 접근해 disabled 속성을 false로 설정하고 수정하기 클릭 시 텍스트 박스를 활성화 시킨다. --%>
	function fn_enable(obj){
		 document.getElementById("i_title").disabled=false;
		 document.getElementById("i_content").disabled=false;
		 document.getElementById("i_imageFileName").disabled=false;
		 document.getElementById("tr_btn_modify").style.display="block";
		 document.getElementById("tr_btn").style.display="none";
	 }
	
	<%-- 수정반영하기 클릭 시 컨트롤러에 수정데이터를 전송한다. --%>
	function fn_modify_article(obj){
		 obj.action="${contextPath}/board/modArticle.do";
		 obj.submit();
	 }
	
	<%-- 삭제하기 버튼 클릭 시 삭제 --%>
	function fn_remove_article(url,articleNO){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		// 자바 스크립트를 이용해 동적으로 <form>태그를 생성한다.
		
		var articleNOInput = document.createElement("input");
		articleNOInput.setAttribute("type", "hidden");
		articleNOInput.setAttribute("name", "articleNO");
		articleNOInput.setAttribute("value", articleNO);
		// 자바스크립트를 이용해 동적으로 <input> 태그를 생성한 후 name과 value를 articleNO와 컨트롤러로 글 번호로 설정한다.
		
		form.appendChild(articleNOInput);
		// 동적으로 생성된 <input> 태그를 동적으로 생성한 <form> 태그에 append한다.
		
		document.body.appendChild(form);
		form.submit();
		// <form> 태그를 <body>태그에 추가 (append)한 후 서버에 요청한다.
	}
	
	function fn_reply_form(url, parentNO){
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		// 전달된 요청명을 <form> 태그의 action 속성 값에 설정한다.
		
		var parentNOInput = document.createElement("input");
		parentNOInput.setAttribute("type", "hidden");
		parentNOInput.setAttribute("name", "parentNO");
		parentNOInput.setAttribute("value", parentNO);
		// 함수 호출 시 전달된 articleNO 값을 <input> 태그를 이용해 컨트롤러에 전달한다.
		
		form.appendChild(parentNOInput);
		// 동적으로 생성된 <input> 태그를 동적으로 생성한 <form> 태그에 append한다.
		
		document.body.appendChild(form);
		form.submit();
		// <form> 태그를 <body>태그에 추가 (append)한 후 서버에 요청한다.
	}
   </script>
</head>
<body>
	<form name="frmArticle" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">글번호</td>
				<td><input type="text" value="${article.articleNO }" disabled />
					<input type="hidden" name="articleNO" value="${article.articleNO}" />
					<%-- 글 수정 시 글 번호를 컨트롤러로 전송하기 위해 미리 <hidden> 태그를 이용해 글 번호를 저장한다. --%>
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">작성자 아이디</td>
				<td><input type="text" value="${article.id }" name="id"
					disabled /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">제목</td>
				<td><input type="text" value="${article.title }" name="title"
					id="i_title" disabled /></td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">내용</td>
				<td><textarea rows="20" cols="60" name="content" id="i_content"
						disabled />${article.content }</textarea></td>
			</tr>

			<c:if
				test="${not empty article.imageFileName && article.imageFileName!='null' }">
				<tr>
					<td width="20%" align="center" bgcolor="#FF9933" rowspan="2">
						이미지</td>
					<td>
						<input type="hidden" name="originalFileName" value="${article.imageFileName }" /> 
						<%-- 이미지 수정에 대비해 미리 원래 이미지 파일 이름을 <hidden> 태그에 저장한다. --%>
						
						<img src="${contextPath}/download.do?imageFileName=${article.imageFileName}&articleNO=${article.articleNO }"
						id="preview" /><br>
					</td>
				</tr>
				<tr>
					<td>
						<input type="file" name="imageFileName " id="i_imageFileName" disabled onchange="readURL(this);" />
						<%-- 수정된 이미지 파일 이름을 전송한다. --%>
					</td>
				</tr>
			</c:if>
			<tr>
				<td width="20%" align="center" bgcolor="#FF9933">등록일자</td>
				<td>
					<input type=text value="<fmt:formatDate value="${article.writeDate}" />" disabled />
				</td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center">
					<input type=button value="수정하기" onClick="fn_modify_article(frmArticle)"> 
					<input type=button value="취소" onClick="backToList(frmArticle)">
				</td>
			</tr>

			<tr id="tr_btn">
				<td colspan=2 align="center">
					<input type=button value="수정하기" onClick="fn_enable(this.form)"> 
					<input type=button value="삭제하기" 
					onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
					<%-- 삭제하기 클릭 시 fn_remove_article() 자바스크립트함수를 호출하면서 articleNO를 전달한다. --%>
					<input type=button value="리스트로 돌아가기" onClick="backToList(this.form)"> 
					<input type=button value="답글쓰기" 
					onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})">
					<%-- 답글쓰기 클릭 시 fn_reply_form()함수를 호출하면서 요청명과 글 번호를 전달한다. --%>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>