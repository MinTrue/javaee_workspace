
package com.sds.project0319.notice;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

//서블릿이란? 오직 서버에서만 실행될 수 있는 클래스
public class ListServlet extends HttpServlet{

	//생명주기 메서드 중, 초기화 메서드를 이용하여, web.xml에서 넘긴 파라미터정보를
	//보유해보자
	String user;

	public void init(ServletConfig config){
		user = config.getInitParameter("user");
		//tomcat의 콘솔(터미널)에 출력하자, log파일 출력에도 출력된다.
		System.out.print("초기 파라미터 값은 "+user);
	}

	//목록 요청을 처리하는 서블릿이므로, get방식의 요청을 처리
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//게시판 목록 요청이므로, 오라클을 연동하자
		
		/*
		JSP나 서블릿 둘다 사용목적은 동일하다. 즉 서버측에서 실행될 수 있는 기술이다.
		하지만, 서블릿은 순수 클래스로 코드를 작성하다 보니, 디자인을 표현할때 모든 것들을 문자열 처리를 해야
		하므로, 효울성이 매우 떨어진다
		해결책) jsp와 서블릿 중 둘중 누구를 써야할지에 대한 판단 기준은 디자인(view)이 들어가는 코드라면
		   		 jsp 우세하고, 그게 아니라면 서블릿으로 처리한다.
		*/

		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<meta charset=\"UTF-8\">");
		out.print("</head>");
		out.print("<body>");
		out.print("<table width=\"100%\" border=\"1px\">");
		out.print("<tr>");
		out.print("<td>No</td>");
		out.print("<td>title</td>");
		out.print("<td>writer</td>");
		out.print("<td>content</td>");
		out.print("</tr>");
		out.print("</table>");
		out.print("초기화 파라미터 값은"+user);
		out.print("</body>");
		out.print("</html>");

	}
}
