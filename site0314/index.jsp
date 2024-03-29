<!-- 
■ jsp를 작성할 수 있는 코드 영역 총 4영역
1) 지시영역 [%@ %]
    현재 jsp 에 대한 페이지 마임타임(종류:이미지,문서,음원 등)인코등 설정, 임포트

2) 선언부 [%! %]
     -현재 페이지의 멤버 변수와 멤버 메스드를 정의하는 영역

3) 스크립틀릿 영역[% %]
    -service() 메서드 안쪽의 영역으로써, java 코드, 로직을 작성하는 영역

4) 표현식 영역 [%= %]
    -out.print(); 줄여쓴 영역으로써, 문자, 숫자, 논리값인 데이터를 클라이언트 브라이저 출력함

■ 내장객체(built-in-Object)
   1) 의미 : 웹어플리케이션 개발시 필수적이고 중요한 기능들을 지원하는 이미 서버에 인스턴스가 올라와 있는
               내장되어진 객체들, 그리고 각 인스턴스에 부여된 이름 조차 정해져 있다.

   2) 종류
       ★ requset  : 클라이언트의 요청을 받는 객체
	   ★ response : 클라리언트에게 응답정보를 구성하는 객체
	   ★ out : 클라이언트에게 전송할 문자열을 담는 스트림
	   		      더 정확하게는 response 객체가 보유한 출력스트림에 문자열을 모은다.
	   - page: 현재 jsp 페이지의 인스턴스를 가리키는 객체
	   ★ session : 클라이언트의 세션을 담는 객체
	   ★ application : 현재 어플리케이션 정보를 담는 객체
	   - pageConstext : 서블릿 수업 시 진행
	   - config : 서블릿 수업 시 진행
	   - exception : jsp 에서의 예외 정보 객체
-->
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>
 </head>
 <body>
	<!--폼태그는 html의 입력양식 태그들(input, checkbox 등)을
		  전송해주는 역할을 수행한다. 즉 문서내의 특정 영역을 양식으로
		  감싸고, 그 영역을 전송할 수 있는 능력이 있다.
	-->
	<form action="/receive.jsp">
		<!--
			그 동안은 id, name은 하나의 문서내에서 요소들을 구분하기 위한 용도로만 사용했지만
			, 서버연동 프로그램에서 name은 전송 시 변수 역할을 수행함 이때 이 전송용 변수를가리켜
			전송 파라미터(parameter)라 한다.
		-->
		<input type="text" placeholder="아이디 입력" name="id">
		<input type="text" placeholder="비밀번호 입력" name="pass">
		<!--아래의 버튼은 고전적 버튼이 아닌, html5 버튼이므로 디폴트 속성 submit
			속성으로 되어 있기 때문에, 폼태그 안에 위치시킬 경우 현재 폼의 입력양식을 무조건 전송해버린다.
			하지만, 우리가 전송주소를 명시하지 않았기 때문에 새로고침(깜빡)만 일어난다
			해결책1) 전송 주소를 명시
			해결책2) 자동 전송(submit)을 막는다
			해결책3) 버튼을 form 태그 밖으로 빼놓는다.
		-->
		<button>서버에 전송</button>
	</form>
 </body>
</html>
