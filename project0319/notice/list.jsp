<%@ page contentType="text/html;charset=utf-8"%>
<%!
	//선언부? 멤버변수, 멤버메서드 작성 영역
	int price=56;
%>
<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>게시판 목록</title>
 </head>
 <body>

	<table width="100%" border="1px">
		<tr>
			<td>No</td>
			<td>제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		<%for(int i =1;i<=10;i++){%>
		<tr>
			<td><%=i%></td>
			<td>제목 테스트</td>
			<td>홍길동</td>
			<td>2024-05-25</td>
			<td>35</td>
		</tr>
		<%}%>
	</table>
 </body>
</html>
