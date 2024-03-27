package com.sds.asynboard.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//수정 요청을 처리하는 서블릿
public class EditServlet2 extends HttpServlet {
	BoardDAO boardDAO = new BoardDAO(); 
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		//파라미터 4개 받기
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String board_idx = request.getParameter("board_idx");
		
		//낱개로 흩어져있는 파라미터들 DTO에 오므자
		Board board =new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		board.setBoard_idx(Integer.parseInt(board_idx));
		
		//DAO 에게 수정요청
		int result = boardDAO.update(board);
		
		if(result>0) {
			out.print("ok");
		} else {
			out.print("fail");
		}
	}
}















