package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.commons.Action;
import com.itwillbs.commons.ActionForward;

/**
 * 
 * 컨트롤러: 서블릿 구현
 * Model-View 연결
 *
 */
public class MemberFrontController extends HttpServlet {

	// http://localhost:8088/MVC7/anyWords.me 
	
//	@Override // doProcess() 메소드는 상속받은 클래스에 존재하지 않는 메소드이기 때문에, 오버라이딩을 하면 에러가 발생한다.
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 페이지정보 전달방식에 상관없이 한번에 처리하는 메서드.
		System.out.println("doProcess() 호출!");
		
		// URL: http://localhost:8088/MVC7/anyWords.me 
		// URI: /MVC7/anyWords.me // > 프로토콜, 포트번호 없음
		
		// address
		// INDEX: http://localhost:8088/MVC7/index.jsp
		// 회원가입: http://localhost:8088/MVC7/MemberJoin.me
		// 로그인: http://localhost:8088/MVC7/MemberLogin.me
		// 메인: http://localhost:8088/MVC7/Main.me
		
		// 3가지 파트의 동작을 구현할 메서드 !!
		/**********************************1. 가상주소 계산 ***************************************/
		// 가상주소 가져오기
		System.out.println("1. 가상주소 계산 - 시작!");
		// 1. URI 가져오기
		String requestURI = request.getRequestURI();
		System.out.println("requestURI: "+requestURI);
		// 2. 프로젝트명 가져오기
		String ctxPath = request.getContextPath();
		System.out.println("ctxPath: "+ctxPath);
		// 3. 가상주소 = URI-프로젝트명 
		String command = requestURI.substring(ctxPath.length());
		System.out.println("command: "+command); // 가상주소명 !!
		
		System.out.println("1. 가상주소 계산 - 끝!");
		
		/**********************************1. 가상주소 계산 ***************************************/
		
		/**********************************2. 가상주소 매핑 ***************************************/
		System.out.println("\n\n");
		System.out.println("2. 가상주소 매핑 - 시작!");
		
		Action action = null;
		ActionForward forward = null;
		//회원가입 - ./MemberJoin.me
		if(command.equals("/MemberJoin.me")) {
			System.out.println("C: /MemberJoin.me 실행!");
			System.out.println("C: DB사용 X, view페이지로 이동 O.(패턴 1)");
			
			// 페이지 이동 
			forward = new ActionForward();
			forward.setPath("./member/insertForm.jsp"); // 목적지
			forward.setRedirect(false); // 주소가 ~.jsp로 끝나면 잘못된것이다.
			// 티켓 생성 - 정보저장을 완료한 것으로, 해당 정보를 사용하지 않으면 아직 의미가 없는것!!
		}// if(./MemberJoin.me) end
		
		//회원가입 - ./MemberJoinAction.me
		else if(command.equals("/MemberJoinAction.me")) { // 콘솔로그에 출력되는 command와 비교해서 입력하면 된다.
			System.out.println("C: /MemberJoinAction.me실행!");
			System.out.println("C: DB사용 O, 페이지로 이동 O.(패턴 2)");
			// 모델을 사용하여 분리할것 !!
//			MemberJoinAction joinAction = new MemberJoinAction();
			action = new MemberJoinAction(); // UPCASTING // 주소와 같은 액션객체
//			joinAction.execute(request, response);
			//Err! Unhandled exception type Exception > 예외처리!
			try {
				forward = action.execute(request, response); // interface를 통한 객체 호출! > 다형성 !
				// 왜 업캐스팅까지 해가면서 이렇게 ?
				// >> (나중에 배울개념) 강한결합 !! (책임과 관련됨.)
				// >> 업캐스팅 > 약한결합 !! > 책임 줄어듬. > 이해가 딱 되지는 않는데, 이렇게 해야한다고 ..
				// ex) 휴대폰 전원버튼이 고장났다 ! > 전원버튼만 바꾸면 됨.   > 약한결합 > 지향
				//									> 메인보드를 교체해야한다.> 강한결합
				
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
			
		}// else if(./MemberJoinAction.me) end
		
		// 로그인 - ./MemberLogin.me
		else if(command.equals("/MemberLogin.me")) {
			System.out.println("C: /MemberLogin.me 호출!");
			System.out.println("C: DB사용X, view페이지(정보입력 페이지) 이동 !(패턴 1)");
			
			// 패턴 1
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false); // 주소가 jsp로 끝나면 안되니까 포워딩 방식으로 이동해야 한다.
										// 가상주소를 변경하지않고 페이지를 이동하기 위해서 사용.
		}// else if(./MemberLogin.me) end
		
		// 로그인 - ./MemberLoginAction.me
		else if(command.equals("/MemberLoginAction.me")) {
			System.out.println("C: /MemberLoginAction.me 호출!");
			System.out.println("C: DB사용 O, 페이지 이동 !(패턴 2)");
			
			// MemberLoginAction() 객체 생성
			action = new MemberLoginAction(); // UPCASTING
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
			
		}// else if(./MemberLoginAction.me) end
		
		// 메인페이지 -./Main.me
		else if(command.equals("/Main.me")) {
			System.out.println("C: /Main.me 호출");
			System.out.println("C: DB사용X, view페이지(정보입력 페이지) 이동 !(패턴 1)");
			
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
		}// else if(./Main.me) end
		
		// 로그아웃 - ./MemberLogoutAction.me
		else if(command.equals("/MemberLogoutAction.me")) {
			System.out.println("C: /MemberLogoutAction.me 호출");
			System.out.println("C: DB사용O, view페이지(정보입력 페이지) 이동 !(패턴 2)");
			//					// > 편의상 DB 사용이라고 했지만, 실제로는 처리를 진행하는(Model,JAVAfile)
			//						 모든 동작은 패턴 2번을 사용해야 한다 !!
			
			// MemberLogoutAction() 객체
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
		}// else if(./MemberLogoutAction.me) end
		
		// 회원정보조회 - ./MemberInfo.me
		else if(command.equals("/MemberInfo.me")) {
			System.out.println("C: /MemberInfo.me 호출");
			System.out.println("C: DB사용O, view페이지 이동& !출력! !(패턴 3)");
			
			//MemberInfoAction() 객체 생성 // 패턴 2번과 동일!, 객체 내부구조가 다르다!
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
			
		}// else if(./MemberInfo.me) end
		
		// 회원정보 수정 페이지 - ./MemberUpdate.me
		else if(command.equals("/MemberUpdate.me")) {
			System.out.println("C: /MemberUpdate.me 호출");
			System.out.println("C: DB사용O, view페이지 이동& !출력! !(패턴 3)");
			
			// MemberUpdateAction() 객체
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
			
		}// else if(./MemberUpdate.me) end
		
		// 회원정보 수정 동작 - ./MemberUpdateProAction.me
		else if(command.equals("/MemberUpdateProAction.me")) {
			System.out.println("C: /MemberUpdateProAction.me 호출");
			System.out.println("C: DB사용O, 페이지 이동(패턴 2)");
			
			// MemberUpdateProAction() 객체
			action = new MemberUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
			
		}// else if(./MemberUpdateProAction.me) end
		
		// 회원정보 삭제 , 비밀번호 확인 페이지로 이동.
		else if(command.equals("/MemberDelete.me")) {
			System.out.println("C: /MemberDelete.me 호출");
			System.out.println("C: DB사용X, view페이지(정보입력 페이지) 이동 !(패턴 1)");
			
			forward = new ActionForward();
			forward.setPath("./member/memberDelete.jsp");
			forward.setRedirect(false);
		}// else if(./MemberDelete.me) end
		// 회원정보 삭제 동작 - ./MemberDeleteAction.me
		else if(command.equals("/MemberDeleteAction.me")) {
			System.out.println("C: /MemberDeleteAction.me 호출");
			System.out.println("C: DB사용O, 페이지 이동(패턴 2)");
			
			// MemberDeleteAction() 객체
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
		}// else if(./MemberDeleteAction.me) end
		
		// 회원정보목록(관리자) - ./MemberList.me
		else if(command.equals("/MemberList.me")) {
			System.out.println("C: /MemberList.me 호출");
			System.out.println("C: DB사용O, view페이지 이동& !출력! !(패턴 3)");
			
			//MemberListAction() 객체 생성 // 패턴 2번과 동일!, 객체 내부구조가 다르다!
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}// t-c end
		}// else if(./MemberList.me) end
		
		
		////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("2. 가상주소 매핑 - 끝!");
		System.out.println("\n\n");
		/**********************************2. 가상주소 매핑 ***************************************/
		
		/**********************************3. 가상주소 이동 ***************************************/
		System.out.println("3. 가상주소 이동 - 시작!");
		if(forward != null) { // 티켓이 정상적으로 생성 되었을때.
			// 페이지 이동
			if(forward.isRedirect()) { // isRedirect가 true 일때
				System.out.println("C: sendRedirect방식 - "+forward.getPath()+" 로 이동.");
				response.sendRedirect(forward.getPath());
			}else {				       // isRedirect가 false 일때
				System.out.println("C: forward방식 - "+forward.getPath()+" 로 이동.");
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}// if end
		
		System.out.println("3. 가상주소 이동 - 끝!");
		/**********************************3. 가상주소 이동 ***************************************/
		System.out.println("doProcess - end (컨트롤러 종료)");

	}// doProcess() method end
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doGET() 호출!");
		doProcess(request,response);

	}// doGet() method end

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doPost() 호출!");
		doProcess(request,response);

	}// doPost() method end
	

}// public class end










































