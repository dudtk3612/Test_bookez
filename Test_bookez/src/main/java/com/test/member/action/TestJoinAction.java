package com.test.member.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.commons.Action;
import com.test.commons.ActionForward;
import com.test.member.db.TestDAO;
import com.test.member.db.TestDTO;

// 회원가입 처리(인코딩,정보저장,디비연결,페이지이동)
public class TestJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(" M : TestJoinAction_execute() 실행");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// TestDTO 객체 생성
		TestDTO dto = new TestDTO();
		// 전달된 정보 저장
		dto.setUser_id(request.getParameter("user_id"));
		dto.setPw(request.getParameter("pw"));
		dto.setPwre(request.getParameter("pwre"));
		dto.setName(request.getParameter("name"));
		dto.setNickname(request.getParameter("nickname"));
		dto.setTel(request.getParameter("tel"));
		dto.setEmail(request.getParameter("email"));
		dto.setRegdate(new Date(System.currentTimeMillis()));
//		dto.setPhoto(request.getParameter("photo"));
		dto.setAddress(request.getParameter("address"));
		
		System.out.println(" M : "+dto);
		
		// MemberDAO 객체 생성
		TestDAO dao = new TestDAO();
		// 회원가입 메서드
		dao.memberJoin(dto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);
		
		System.out.println(" M : 데이터 처리완료! 티켓 가지고 이동");
		
		return forward;
	}
	
	
}
