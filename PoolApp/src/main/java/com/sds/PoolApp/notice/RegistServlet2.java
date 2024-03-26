package com.sds.PoolApp.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 글쓰기 요청을 처리하는 서블릿
public class RegistServlet2 extends HttpServlet{
	
	//클라이언트가 post 방식으로 전송을 시도하기 떄문에 dopost() 로 재정의 한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트의 파라미터받기
		request.setCharacterEncoding("uft-8");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content =request.getParameter("content");
		
		System.out.println("제목은"+title);
		System.out.println("작성자는"+writer);
		System.out.println("내용은"+content);
		
		//데이터베이스 연동 직접 하지 않고 DAO에게시키자
		
		
		
	}
	



}