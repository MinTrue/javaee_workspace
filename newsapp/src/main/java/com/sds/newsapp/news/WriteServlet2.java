package com.sds.newsapp.news;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 글쓰기 요청을 받아, 오라클에 넣되, 직접 넣지 않고 DAO한테 시키자!!
//DAO는 서버에서 실행되거나, 클라이언트의 파라미터를 받을 능력이 없다.
//오직 데이터베이스와의 CRUD만을 수행하는 중립적 객체 (웹용 아니라 중립적 객체다)
public class WriteServlet2 extends HttpServlet{
	
	//클라이언트의 요청이 post 방식이므로, doPost() 재정의
	protected void doPost(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//파라미터 받기
		requset.setCharacterEncoding("utf-8");
		String title= requset.getParameter("title");
		String writer= requset.getParameter("writer");
		String content = requset.getParameter("content");
		
		//DAO에게 일 시키기
		
	}
	
}