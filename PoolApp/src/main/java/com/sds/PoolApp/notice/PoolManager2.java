package com.sds.PoolApp.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//이 클래스의 정의는 필수는 아니지만, 이러한 객체 존재가 없다면 DAO에서
//XML의 커넥션풀을 검색하여, DataSource를 얻어오는 코드를 일일이 메서드 마다 보유해야 한다.
//개발의 효율성이 떨어진다. 따라서 아래의 PoolManger가 검색을 대신 해주고, DataSource로부터
//필요한 Connection 이 있을때, DAO에게 빌려주거나 반납도 받는 대행자 역할을 수행한다.
public class PoolManager2 {
		InitialContext context;
		DataSource ds;
		
		public PoolManager2() {
			try {
				context = new InitialContext();
				ds =(DataSource)context.lookup("jdbc:comp/env/jndi/oracle");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		//DAO 등이 커넥션풀로부터 Connection 한개를 얻어갈 수 있도록 메서드를 제공하자
		public Connection getConnection() {
			Connection con = null;
			
			try {
				con = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		//DAO등이 다 사용하고나서, 반납을 요청받는 메서드 정의 오버로딩 기법
		public void release(Connection con) {
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public void release(Connection con, PreparedStatement pstmt) {
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs !=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
			
}

