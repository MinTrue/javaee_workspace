package com.sds.Dataroom.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sds.Dataroom.common.PoolManager;

//dataroom 테이블에 대한 CRUD 만을 수행하느 DAO정의
public class DataroomDAO {
	PoolManager pool = PoolManager.getInstance(); //싱글턴으로 선언된 객체의 인스턴스 얻기
	
	//모든 레코드 가져오기
	public  List SelectAll() {
		List list = new ArrayList();
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		con = pool.getConnection();
		
		String sql="select * from dataroom order by dataroom_idx desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//rs를 제거하기 전에 rs에 있는 내용들을 꺼내어, 레코드 1건마다 DTO와 매핑하자
			
		while(rs.next()) { //커서한칸 내리기
			Dataroom dto = new Dataroom(); // 비어있는 DTO생성
			dto.setDataroom_idx(rs.getInt("dataroom_idx"));
			dto.setTitle(rs.getString("title"));
			dto.setWriter(rs.getString("writer"));
			dto.setContent(rs.getString("content"));
			dto.setRegdate(rs.getString("regdate"));
			dto.setHit(rs.getInt("hit"));
			dto.setFilename(rs.getString("filename"));
			
			list.add(dto); //리스트에 추가
		  }	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.release(con, pstmt, rs);
		}
		return list;
	}
	
	//레코드 1건 가져오기
	
	//1건 등록
	public int insert(Dataroom dataroom) {
		Connection con =null;
		PreparedStatement pstmt =null;
		int result=0;
		
		con = pool.getConnection(); //대여
		
		String sql="insert into dataroom(dataroom_idx, title, writer, content, filename)";
		sql+=" values(seq_dataroom.nextval, ?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dataroom.getTitle());
			pstmt.setString(2, dataroom.getWriter());
			pstmt.setString(3, dataroom.getContent());
			pstmt.setString(4, dataroom.getFilename());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt); //자원 해제
		}
		return result;
	}
	
	//1건 수정
	
	//1건 삭제
	
	
}
