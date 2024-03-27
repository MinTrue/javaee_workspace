package com.sds.asynboard.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//목록 삭체 요청을 처리하는 서블릿
public class DeleteServlet2 extends HttpServlet{ 
	BoardDAO boardDAO = new BoardDAO();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String board_idx = request.getParameter("board_idx");
		
		int result = boardDAO.delete(Integer.parseInt(board_idx));
		
		if(result >0) {
			out.print("ok");
		} else {
			out.print("nob");
		}
	}
	
	
}
