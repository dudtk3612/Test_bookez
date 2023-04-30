package com.test.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.commons.Action;
import com.test.commons.ActionForward;
import com.test.member.action.TestJoinAction;

/**
 *  컨트롤러 : 서블릿 구현
 *    Model-View 연결동작 처리
 */
@WebServlet("*.ys")
public class TestFrontController extends HttpServlet {

	//http://localhost:8088/MVC7/member
	//http://localhost:8088/MVC7/member.me
	//http://localhost:8088/MVC7/Main.me
	//http://localhost:8088/Test_bookez/TestJoin.ys
	//http://localhost:8088/Test_bookez/TestUpdate.ys
	//http://localhost:8088/Test_bookez/TestLogin.ys
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 페이지 정보 전달방식에 상관없이 한번에 처리하는 메서드
		System.out.println("doProcess() 호출!");
		
		// URL : http://localhost:8088/MVC7/itwill.me
		// URI : /MVC7/itwill.me
		
		/**********************1. 가상주소 계산****************************/
		System.out.println(" 1. 가상주소 계산 - 시작 ");
		
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" ctxPath : "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" command : "+command);
		
		System.out.println(" 1. 가상주소 계산 - 끝 ");
		/**********************1. 가상주소 계산****************************/
		
		/**********************2. 가상주소 매핑****************************/
		System.out.println("\n");
		System.out.println(" 2. 가상주소 매핑 - 시작 ");
		Action action = null;
		ActionForward forward = null;
				// 회원가입-./TestJoin.ys
				if(command.equals("/TestJoin.ys")) {
					System.out.println(" C : /TestJoin.ys실행 ");
					System.out.println(" C : DB사용x, view페이지 이동(패턴1)");
					
					// 페이지 이동
					forward = new ActionForward();
					forward.setPath("./signup.jsp");
					forward.setRedirect(false);			
				}
				// 회원가입-./TestJoinAction.ys
				else if(command.equals("/TestJoinAction.ys")) {
					System.out.println(" C : /TestJoinAction.ys 호출 ");
					System.out.println(" C : DB사용 O, 페이지 이동O (패턴2)");
					
					// TestJoinAction 객체 생성
					//TestJoinAction joinAction = new TestJoinAction(); 
					action = new TestJoinAction(); 
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// 로그인
				else if(command.equals("/TestLogin.ys")) {
					System.out.println("C: /Login.ys 호출!");
					System.out.println("C: DB사용X, view페이지(정보입력 페이지) 이동 !(패턴 1)");
					
					forward = new ActionForward();
					forward.setPath("./login.jsp");
					forward.setRedirect(false);
				}
				// 로그인 액션
				else if(command.equals("/TestLoginAction.ys")) {
					System.out.println("C: /TestLoginAction.ys 호출!");
					System.out.println("C: DB사용 O, 페이지 이동 !(패턴 2)");
					
					// TestLoginAction() 객체 생성
					action = new TestLoginAction();
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				}
				// 회원정보수정 페이지
				else if(command.equals("/TestUpdate.ys")) {
					System.out.println(" C : /TestUpdate.ys 호출 ");
					System.out.println("C: DB사용O, view페이지 이동& !출력! !(패턴 3)");
					
					action = new TestUpdateAction();
					
					try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
		
		
		
		System.out.println(" 2. 가상주소 매핑 - 끝 ");
		System.out.println("\n");
		/**********************2. 가상주소 매핑****************************/
		
		/**********************3. 가상주소 이동****************************/
		System.out.println(" 3. 가상주소 이동 - 시작 ");
		System.out.println(" 3. 가상주소 이동 - 시작 ");
		if(forward != null) { //이동정보가 있을때
			if(forward.isRedirect()) {
				// 페이지 이동방식 - true
				System.out.println(" C : sendRedirect방식 - "+forward.getPath()+"이동");
				response.sendRedirect(forward.getPath());
			}else {							
				// 페이지 이동방식 - false
				System.out.println(" C : forward방식 - "+forward.getPath()+"이동");
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}			
		}		
		System.out.println(" 3. 가상주소 이동 - 끝 ");
		/**********************3. 가상주소 이동****************************/
		
		System.out.println(" doProcess - 끝(컨트롤러 종료) ");
	}// doProcess
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGET() 호출!");
		doProcess(request, response);		
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPOST() 호출!");
		doProcess(request, response);
	}

}
