package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.commons.Action;
import com.itwillbs.commons.ActionForward;
import com.itwillbs.commons.JSForward;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

// 유저의 로그인에 관한 정보 처리
public class MemberLoginAction implements Action { // 상속받는 이유? request,response를 받아낼수 있기 때문!

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println(" M : MemberLoginAction.execute() 호출!");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달정보 저장 (id, pw) > MemberDTO
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		
		// MemberDAO 객체 생성
		MemberDAO dao = new MemberDAO();
		// 로그인 체크 메서드 호출
		int result = dao.memberLogin(dto);
		
		// 페이지 이동
		if(result == -1) {
			// 회원이 아닐때(아이디 오류거나 없음)
			JSForward.alertAndBack(response, "아이디 없음!");
			
			return null; // return > 정해진 값을 반환한다 + 함수를 종료한다.
			// >> 컨트롤러에서는 이동하지 않겠다 !
		}else if(result == 0) {
			// 비밀번호 오류
			JSForward.alertAndBack(response, "비밀번호 오류!");
			// 서블릿 + JS 동작으로 이동하는 코드를 JSForward로 옮겨서 모듈화 진행하였음.
			
			return null; // return > 정해진 값을 반환한다 + 함수를 종료한다.
			// >> 컨트롤러에서는 이동하지 않겠다 !
		}// i-e-e end
		
		// 로그인 성공! > 굳이 if문을 통하여 제어 할 필요가 없다!
		// >> 지금은 코드가 적어서 차이가 나지 않지만, 점점 성능 차이가 많이 날수 있다.
		// >> 효율적인 코드를 작성하는 습관을 들이자!!
		
		// 세션에 아이디정보 저장.
//		session.setAttribute(); >> 세션은 js꺼다 !!
		HttpSession session = request.getSession();
		session.setAttribute("id", dto.getId());
		// 페이지 이동 > 이동정보를 저장 (ActionForward)
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true); 
		// 주소 변경 + 화면변경 > true // 사실 왜 true 해야하는지는 잘 ..
		return forward;
	}// execute() method end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}// public class end
