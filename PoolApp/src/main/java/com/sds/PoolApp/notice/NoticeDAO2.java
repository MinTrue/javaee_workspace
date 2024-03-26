package com.sds.PoolApp.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//이 객체는 오직 notice 테이블에 대한 CRUD만을 담당하는 객체이며, 이외의 로직은 담지 않는다..
//결국 DB 작업만 전담한다고 하여 DAO(Data Access Object) 라 한다 
public class NoticeDAO2 {
	PoolManager poolManager = new PoolManager();
	
	//커넥션풀로부터 Connection을 대여해와서 글쓰기
	public int insert(Notice2 notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;
		
		con = poolManager.getConnection();//1개대여
		
		String sql="insert into notice(notice_idx, title, wrider, content)";
		sql+=" values(seq_notice.nextval, ?,?,?)";
		
		try {
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			poolManager.release(con, pstmt);
		}
		return result;
	}
	
	//목록 가져오기
	public List selectAll() {
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		
		con = poolManager.getConnection();
		
		String sql="select * from notice order by desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs= pstmt.executeQuery(); //쿼리 실행
			
			//rs를 대신할 객체가 필요하다
			while(rs.next()) {
				Notice notice = new Notice(); //DTO 인스턴스 생성
				notice.setNotice_idx(rs.getInt("notice_idx"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("content"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
				list.add(notice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			poolManager.release(con, pstmt, rs);
		}		
		return list;
	}
	
	
	//글 한건 가져오기(상세보기)
	public Notice select(int notice_idx) {
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		Notice notice = null;
		
		con=poolManager.getConnection();
		String sql="select * from notice where notice_idx=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_idx);
			rs = pstmt.executeQuery();
			
			//rs가 곧 죽을 예정이므로, rs가 가진 레코드 1건을, DTO 한개에 담아놓자
			while(rs.next()) {
				notice = new Notice();
				
				notice.setNotice_idx(rs.getInt("notice_idx"));
				notice.setTitle(rs.getString("title"));
				notice.setWriter(rs.getString("writer"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			poolManager.release(con, pstmt, rs);
		}
		return notice;
		
	}
	
	
	
	
	
	/*
	public int insert(Notice notice) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0; //쿼리실행 결과를 담게 될 변수
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//접속 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "seshop", "1234");
			if(con ==null) {
				System.out.println("접속 실패");
			}else {
				String sql="insert into notice(notice_idx, title, writer, content)";
				sql+=" values(seq_notice.nextval, ?,?,?)";
				
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1, notice.getTitle()); //제목을 dto에서 꺼내서 대입
				pstmt.setString(2, notice.getWriter()); //작성자를 dto에서 꺼내서 대입
				pstmt.setString(3, notice.getContent()); //내용을 dto에서 꺼내서 대입 
				
				result=pstmt.executeUpdate();//쿼리실행 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

	 */
}