package com.test.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TestDAO {
	
	// 공통변수 선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// DB 연결 메서드
	private Connection getCon() throws Exception {
		
		Context initCTX = new InitialContext();
		
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/Test_bookez");
		
		con = ds.getConnection();
		System.out.println("DAO : 디비연결 성공" + con);
		
		return con;
	}
	
	// 자원 해제
	public void closeDB() {
		try {
			if(rs != null)  rs.close();
			if(pstmt != null)  pstmt.close();
			if(con != null)  con.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 로그인
	public int login(TestDTO dto) {
		int result = -1;

		try {
			con = getCon();
			
			sql = "select pw from signup where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 회원
					result = 1;
				} else {
					// 회원 o 비밀번호 x
					result = 0;
				} 
			} else {
				// 비회원
				result = -1;
			}
			System.out.println("로그인 처리 결과 : " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}
	
	
	// 회원가입 
	public void memberJoin(TestDTO dto) {
		try {
			// 1.2. DB 연결
			con = getCon();
			// 3. SQL 작성 & pstmt 객체
//			sql = "insert into itwill_member(user_no, user_id, pw, name, nickname, tel, email, regdate, status, photo, address, code_num) "
//					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			sql = "insert into signup(user_id, pw, pwre, name, nickname, tel, email, regdate, address) "
					+ "values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, dto.getUser_no());
			pstmt.setString(1, dto.getUser_id());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getPwre());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getNickname());
			pstmt.setString(6, dto.getTel());
			pstmt.setString(7, dto.getEmail());
			pstmt.setDate(8, dto.getRegdate());
//			pstmt.setInt(8, dto.getStatus());
//			pstmt.setString(8, dto.getPhoto());
			pstmt.setString(9, dto.getAddress());
//			pstmt.setInt(11, dto.getCode_num());
//			pstmt.setInt(10, 0); //dto로 가져오지 않고 그냥 부여했음// 원래는 이러면 안됨.
			
			
			// 4. SQL 실행
			pstmt.executeUpdate();
			System.out.println("DAO : 회원가입 성공!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}
	
	// 회원정보수정
	public TestDTO memberUpdate(String id) {
		TestDTO dto = null;
		
		try {
			con = getCon();
			
			sql = "select * from signup where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new TestDTO();
				dto.setUser_id(rs.getString("user_id"));
				dto.setName(rs.getString("name"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setAddress(rs.getString("address"));
				dto.setTel(rs.getString("tel"));
				
				System.out.println(" DAO : 회원정보 저장완료");
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return dto;
	}
}
