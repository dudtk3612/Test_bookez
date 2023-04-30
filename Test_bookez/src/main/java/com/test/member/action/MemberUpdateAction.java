package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.commons.Action;
import com.itwillbs.commons.ActionForward;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

// 회원정보 수정 페이지이동 수행
public class MemberUpdateAction implements Action{
	//
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.println(" M : MemberUpdateAction.execute()");
		
		// 세션정보 가져오기
		HttpSession session = request.getSession();		 // UPCASTING
		String id = (String) session.getAttribute("id"); // DOWNCASTING
		
		ActionForward forward = new ActionForward();
		if(id == null) {
//			response.sendRedirect("./Main.me"); (X)
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}// idcheck if end
		
		// MemberDAO객체 생성 - 회원정보 수정 메서드 updateMember()
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.updateMember(id);
		
		// 회원정보 저장 (request영역)
		request.setAttribute("dto", dto);

		// 페이지 forward이동(jsp) + 정보 출력
		forward.setPath("./member/UpdateForm.jsp");
		forward.setRedirect(false); // request정보를 가져가기 위해서 필요함.
		
		System.out.println(" M : 정보수정 저장, 처리 끝!!");

		return forward;
	}// execute() method end

}// public class end
