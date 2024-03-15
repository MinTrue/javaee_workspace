<%@ page contentType="text/html;charset=utf-8"%>
<%
//javaSE 에서 URLConnection 에 의한 요청을 처리하기 위함
//하지만, 웹 서버측에서는 클라이언트가 javaSE건 웹브라우저건 관심 x
//즉 고양이는 http 프로토콜로 말을 걸어오는 자에게 무조건 응답을할 뿐이지
//그 대상이 누군지는 관심 없다!!
System.out.println("클라이언트 접속 감지!!"); //톰캣 서버 콘솔에 찍힘

out.print("ok"); //미래의 응답(response) 정보 객체에, 클라이언트에게 보내질
					    //문자열을 채우는 과정일 뿐, 이 자체가 클라이언트에게 전송되어지는 시점 아님
%>