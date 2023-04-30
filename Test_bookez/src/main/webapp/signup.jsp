<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>북이지 - 회원가입</title>
<link href="signup.css" rel="stylesheet">
</head>
<body>
	
	<div class="wrap">
		<header>
			<div class="logo">
				<a href="index.html">북이지</a>
			</div>
		</header>
		<section>
			<form action="TestJoinAction.ys" method="post" name="fr">
				<div class="signup">
					<label>
						아이디 <input type="text" class="id" name="user_id" required>
					</label>
					
					<label>
						비밀번호 <input type="password" class="pw" name="pw" required>
					</label>
					
					<label>
						비밀번호 확인 <input type="password" class="pw-re" name="pwre" required>
					</label>
					
					<label>
						이름 <input type="text" class="name" name="name" required>
					</label>
					
					<label>
						닉네임 <input type="text" class="nickname" name="nickname" required>
					</label>
					
					<label>
						이메일 <input type="email" class="email" name="email" required>
					</label>
					
					<label class="addr">
						주소
						<div class="addr-wrap">
							<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
							<input type="text" id="sample4_postcode" placeholder="우편번호" required onclick="sample4_execDaumPostcode();" name="address">
							<input type="text" id="sample4_roadAddress" placeholder="도로명주소" readonly name="address">
							<input type="text" id="sample4_jibunAddress" placeholder="지번주소" readonly name="address">
							<span id="guide" style="color:#999;display:none"></span>
							<input type="text" id="sample4_detailAddress" placeholder="상세주소" required name="address">
							<input type="text" id="sample4_extraAddress" placeholder="참고항목" name="address">
						</div>
					</label>
					
					<label>
						연락처 <input type="text" class="phone" name="tel" required>
					</label>
					
					<div class="btn-wrap">
						<button class="success" onclick="signupSuccess();">회원가입</button>
						<button class="cancel" onclick="location.href='login.html';">취소</button>
					</div>
				</div>
			</form>
			
		</section>
	</div>
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript">
		function signupSuccess() {
			var fr = document.fr;
			 
			if((fr.user_id.value && fr.pw.value && fr.pwre.value && fr.name.value && fr.nickname.value && fr.email.value && fr.tel.value) != "") {
				
				if(fr.pw.value == fr.pwre.value) {
					alert("회원가입이 완료됐습니다.");					
				} else {
					alert("비밀번호가 일치하지 않습니다.")
				}
				
			}
		}
		
		function sample4_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수

	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample4_postcode').value = data.zonecode;
	                document.getElementById("sample4_roadAddress").value = roadAddr;
	                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if(roadAddr !== ''){
	                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("sample4_extraAddress").value = '';
	                }

	                var guideTextBox = document.getElementById("guide");
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';

	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
	            }
	        }).open();
	    }
	</script>
</body>
</html>