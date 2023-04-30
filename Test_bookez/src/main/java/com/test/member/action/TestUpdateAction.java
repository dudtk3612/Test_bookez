package com.test.member.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.commons.Action;
import com.test.commons.ActionForward;
import com.test.member.db.TestDAO;
import com.test.member.db.TestDTO;

// 회원가입 처리(인코딩,정보저장,디비연결,페이지이동)
public class TestUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println(" M : TestUpdateAction_execute() 실행");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		System.out.println("-------------아이디 " + id + "-------------");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./TestLogin.ys");
			forward.setRedirect(true);
			return forward;
		}
		
		TestDAO dao = new TestDAO();
		TestDTO dto = dao.memberUpdate(id);
		
		request.setAttribute("dto", dto);
		
		forward.setPath("./myinfoupdate.jsp");
		forward.setRedirect(false);
		
		System.out.println("M : 정보수정 저장, 처리");
		
		return forward;
	}
	
	
}
