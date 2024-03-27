package com.sds.asynboard.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

//글 한건에 대한 요청을 처리하는 서블릿
public class DetailServlet2 extends HttpServlet{
	BoardDAO boardDAO = new BoardDAO();
		
	protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//파라미터 받기
		String board_idx = requset.getParameter("board_idx");
		System.out.println("board_idx is"+board_idx);
		
		//DAO를 이용하여 한건 반환 받기
		Board board = boardDAO.select(Integer.parseInt(board_idx));
		
		//GSON 이용해 DTO를 JSON 문자열로 반환
		Gson gson = new Gson();
		String json = gson.toJson(board);
		
		out.print(json);
		
			
	}
		
	
}