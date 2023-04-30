package com.test.member.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.commons.Action;
import com.test.commons.ActionForward;
import com.test.commons.JSFoward;
import com.test.member.db.TestDAO;
import com.test.member.db.TestDTO;

// 회원가입 처리(인코딩,정보저장,디비연결,페이지이동)
public class TestLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(" M : TestLoginAction_execute() 실행");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달 정보 저장
		TestDTO dto = new TestDTO();
		dto.setUser_id(request.getParameter("user_id"));
		dto.setPw(request.getParameter("pw"));
		
		// DAO 객체 생성
		TestDAO dao = new TestDAO();
		int result = dao.login(dto);
		
		// 페이지 이동
		if(result == -1) { 
			JSFoward.alertAndBack(response, "아이디 없음");
			
			return null;
		} else if(result == 0) {
			JSFoward.alertAndBack(response, "비밀번호 틀림");
			return null;
		}

		HttpSession session = request.getSession();
		session.setAttribute("id", dto.getUser_id());
		
		ActionForward forward = new ActionForward();
		forward.setPath("./TestUpdate.ys");
		forward.setRedirect(true);
		
		return forward;
	}
	
	
}
