package com.sds.asynboard.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sds.asynboard.common.PoolManager;

//이 클래스는, 오직  board 테이블에 대한 CRUD를 수행하기 위한 객체이다 
public class BoardDAO2 {
	PoolManager pool=new PoolManager(); //수영장 관리 객체 생성
	
	
	//모든 글 가져오기
	public List selectAll() {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		List list = new ArrayList();
		
		con = pool.getConnection();
		
		String sql="selelct * from board order by board_idx desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setBoard_idx(rs.getInt("board_idx"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt, rs);
		}
		return list;
	}
	
	//글 한건 가져오기
	public Board select(int board_idx) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Board board =null;
		
		con = pool.getConnection();
		
		String sql ="select * from board where board_idx=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			rs =pstmt.executeQuery();
			if(rs.next()) {
				board = new Board(); //비어있는 상태
				board.setBoard_idx(rs.getInt("board_idx"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt, rs);
		}
		return board;
	}
	
	//글 1건 등록하기
 	public int insert(Board board) {
		Connection con =null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		con = pool.getConnection();
		
		String sql ="insert into board(board_idx, title, writer, content)";
		sql+=" values(seq_board.nextval, ?, ? ,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle() );
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt);
		}
		return result;
	}
	
	//글 수정하기
 	public int update(Board board) {
 		Connection con =null;
 		PreparedStatement pstmt =null;
 		int result =0;
 		
 		con = pool.getConnection();
 		
 		String sql="update board set title=?, writer=? ,content=? where boadr_idx=? ";
 		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getBoard_idx());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt);
		}
 		return result;
 	}

 	//글 삭제하기
 	public int delete(int board_idx) {
 		Connection con = null;
 		PreparedStatement pstmt = null;
 		int result =0;
 		
 		con = pool.getConnection();
 		
 		String sql ="delete board where board_idx=?";
 		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, board_idx);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
 		return result;
 	}

}