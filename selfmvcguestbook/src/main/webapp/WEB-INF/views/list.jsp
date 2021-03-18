<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- scope에서 guestBooks리스트를 el을 이용해 받아오고 jstl을 이용해 출력해주었습니다. 
	guestBooks리스트가 비어있지 않으면 scope에서 start 변수를 가져와 begin end를 설정해주어서
	한 페이지에 5개씩 나오게 출력해줍니다. 비어 있으면 등록된 방명록이 없다고 나옵니다. 
--%>
<c:choose>
	<c:when test="${!empty guestBooks}">
		<c:forEach items="${guestBooks}" var="guestbook" begin="${start}" end="${start + 4}">
			${guestbook.id } <br>		
			${guestbook.name} <br>
			${guestbook.content} <br>
			${guestbook.regdate } <br>
		</c:forEach>
	</c:when>
	<c:otherwise>
		등록된 방명록이 없습니다.
	</c:otherwise>
</c:choose>

<p>

<%-- 5개를 기준으로 페이지를 생성합니다. ex) 방명록이 6개면 페이지 2개 
	페이지를 누르면 start가 바뀌면서 방명록 리스트가 바뀌게 됩니다.
--%>
<c:forEach var="page" begin="0" end="${(count-1)/5}">
<a href="?start=${page * 5 }"> ${page + 1} </a>
</c:forEach>

<p>

<form method="post" action="write" accept-charset="UTF-8">  
이름 : <input type="text" name="name"><br>
내용 : <textarea name="content" cols = "50" rows="5"
		 placeholder="내용을 입력해주세요."></textarea><br>
<input type="submit" value="등록">
</form>

<p>

<form method="post" action="delete" accept-charset="UTF-8">  
ID : <input type="text" name="id"><br>
<input type="submit" value="삭제">    
</form>


</body>
</html>