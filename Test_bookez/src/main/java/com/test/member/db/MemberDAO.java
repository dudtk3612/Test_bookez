package com.itwillbs.member.db;
// DB와 연결되는 모든 동작은 db패키지에 저장한다 !!

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


// DB와 연결해서 처리하는 모든 동작 수행
// >> DB를 활용해야하는 모든 동작 > MemberDAO 객체를 생성해야한다!
public class MemberDAO {
	// 공통변수 선언
	private Connection con = null; 			// DB연결정보 저장(관리)
	private PreparedStatement pstmt = null; // SQL쿼리 작성, 실행
	private ResultSet rs = null;			// select결과 데이터를 저장.
	private String sql = "";				// SQL쿼리 구문 저장.
	
	// DB 연결 메서드
	private Connection getCon() throws Exception{
		// Context 객체 생성 > 프로젝트(Context)에대한 정보
		Context initCTX = new InitialContext(); // 초기화된 프로젝트 정보를 initCTX 안에 저장.
		// DB연결정보 불러와서 사용!
		DataSource ds // xml파일에서 타입을 DataSource 로 미리 설정해뒀음!(8번 라인)
			=(DataSource)initCTX.lookup("java:comp/env/jdbc/mvc7");
									// 여기까지는 고정/(여기가 달라진다 !!)
		// DB연결
		con = ds.getConnection(); // DB연결정보를 Connection타입 변수 con에 저장.
		
		System.out.println("DAO: DB연결 성공!"+con);
		return con;
	}// getCon() method end
	
	// 자원 해제
	public void closeDB() {
			try {
				if(rs != null) 	  rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)   con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}// t-c end
	}// closeDB() method end
	
	// 회원가입 - memberJoin()
	public void memberJoin(MemberDTO dto) {
		//1,2. DB연결
		try {
			con = getCon();
			sql = "insert into itwill_member (id,pw,name,gender,age,email,regdate)"
					+ " values (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			// 3. SQL 쿼리작성 & pstmt객체
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setInt(5, dto.getAge());
			pstmt.setString(6, dto.getEmail());
			pstmt.setDate(7, dto.getRegdate());
			// 4. SQL쿼리 실행
			pstmt.executeUpdate();
			System.out.println("DAO: memberJoin() 메서드 정상작동");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}// t-c-f end
		
	}// memberJoin() method end
	
	// 로그인 - memberLogin()
	public int memberLogin(MemberDTO dto) {
		int result = -1;
		try {
			// 1,2 DB 연결
			con = getCon();
			// 3 sql쿼리 작성
			sql = "select pw from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId( ));
			// 4 sql쿼리 실행
			rs = pstmt.executeQuery();
			// 5 데이터 처리
			if(rs.next()) {
				if(dto.getPw().equals(rs.getString("pw"))) {
					// 비밀번호 체크 > 1 회원
					result = 1;
				}else {
					// 비밀번호 오류 > 0 pw 에러
					result = 0;
				} // pwcheck i-e end
			}else {
				// 아이디 없음 > -1 비회원
				result = -1;
			}// rs check i-e end
			System.out.println("로그인처리 결과: "+ result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}// t-c-f end
		return result;
	}// memberLogin() method end
	
	// 회원정보조회 - getMember()
	public MemberDTO getMember(String id) {
		MemberDTO dto = null;
		try {
			// 1,2 DB연결
			con = getCon();
			// 3. sql쿼리 & pstmt 객체
			sql = "select * from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4. 쿼리 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				// id가 있을때
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getInt("age"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getDate("regdate"));
				
				System.out.println("DAO: 회원정보 저장 완료!"+dto);
			}else {
				// id가 없을때 (else는 없어도 되긴 함~)
			}// i-e end
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		} // t-c-f end
		return dto;
	}// getMember() method end
	
	//회원정보수정 폼페이지 출력을 위한 정보 저장 메서드 - updateMember()
	public MemberDTO updateMember(String id) {
		MemberDTO dto = null;
		try {
			// 1,2 DB연결
			con = getCon();
			// 3. sql쿼리 & pstmt 객체
			sql = "select * from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// 4. 쿼리 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				// id가 있을때
				dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getInt("age"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getDate("regdate"));
				
				System.out.println("DAO: 회원정보 저장 완료!"+dto);
			}else {
				// id가 없을때 (else는 없어도 되긴 함~)
			}// i-e end
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		} // t-c-f end
		return dto;
	}// updateMember() method end
	
	//회원정보수정 동작을 위한 정보 저장 메서드 - memberUpdate()
	public int memberUpdate(MemberDTO dto) {
		int result = -1;
		try {
			con = getCon();
			sql="select pw from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId()); // PK
			rs = pstmt.executeQuery();
			// id(PK)를 통하여 pw 추출
			if(rs.next()) {
				if(dto.getPw().equals(rs.getString("pw"))) { // pw 체크
				sql ="update itwill_member set name=?, age=?, gender=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, dto.getName());
					pstmt.setInt(2, dto.getAge());
					pstmt.setString(3, dto.getGender());
					pstmt.setString(4, dto.getId());
					
					result = pstmt.executeUpdate(); 
					// 영향을 받은 행의 수를 리턴하기때문에 1을 리턴해야 정상적인 것!!
					
					
				}else{ // 비밀번호가 틀린 경우
					System.out.println("비밀번호 오류!");
					result = 0;
				}// i - e end
			}else {
				// 아이디 오류
			}// rs.next() if end
			System.out.println("DAO: 회원정보 수정 동작 완료!("+result+")");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		} // tcf end
		return result;
	}// memberUpdate() method end
	
	//회원정보삭제 동작을 위한 정보 저장 메서드 - deleteMember()
	public int deleteMember(String id, String pw) {
		int result = -1;
		try {
			con = getCon();
			sql="select pw from itwill_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // PK
			rs = pstmt.executeQuery();
			// id(PK)를 통하여 pw 추출
			if(rs.next()) {
				if(pw.equals(rs.getString("pw"))) { // pw 체크
					sql ="delete from itwill_member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					
					result = pstmt.executeUpdate(); 
					// 영향을 받은 행의 수를 리턴하기때문에 1을 리턴해야 정상적인 것!!

				}else{ // 비밀번호가 틀린 경우
					System.out.println("비밀번호 오류!");
					result = 0;
				}// i - e end
			}else {
				// 아이디 오류
				result = -1;
			}// rs.next() if end
			System.out.println("DAO: 회원정보 삭제 동작 완료!("+result+")");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		} // tcf end
		return result;
	}// deleteMember() method end
	
	public List<MemberDTO> getMemberList() {
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		try {
			con = getCon();
			sql = "select * from itwill_member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setAge(rs.getInt("age"));
				dto.setEmail(rs.getString("email"));
				dto.setRegdate(rs.getDate("regdate"));
				
				memberList.add(dto);
			}// while end
			System.out.println(" DAO: 회원목록 조회 성공!");
			System.out.println(memberList.size()); // memberList를 그냥 갖다가 조회하면 시간이 많이걸리니까 이렇게 확인하는거!
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}//tcf end
		return memberList;
	}//getMemberList() method end
	

	
}// public class end




























