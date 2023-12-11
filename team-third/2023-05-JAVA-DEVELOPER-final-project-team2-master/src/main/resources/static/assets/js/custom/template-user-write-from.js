
export function user_write_form(responseJsonObject = {}) {
	let htmlTemplate =
		`<!--header area start-->
    
    <!--offcanvas menu area start-->
    
    <!--header area end-->

    
    <!-- customer login start -->
    <th:block layout:fragment="content">
    <div id="content">
    <div class="customer_login">
        <div class="container">
            <div class="row">
               <!--login area start-->
               
                <!--login area start-->

                <!--register area start-->
                <div class="col-lg-6 col-md-6" style="margin-left: 300px;">
                    <div class="account_form register" style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
                        
                        <style>
                                @font-face {
                                    font-family: 'GmarketSansMedium';
                                    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
                                    font-weight: normal;
                                    font-style: normal;
                                }
    
                                .container {
                                    font-family: 'GmarketSansMedium';
                                }
                            </style>
                        
                        <h2 th:text="Register">회원가입</h2>
                        <form name="f" id="user_write_form" action="#" method="post" >
                            
                            <p>   
                                <label>아이디(이메일 형식으로 작성하세요) <span>*</span></label>
                                <input type="text" name="userId" id="userId" value="{{userId}}">
                                <span id="userIdError" style="color:red;"></span>
                             </p>
                
                             <p>
                                <label>비밀번호 <span>*</span></label>
                                <input type="password" name="password" id="password" value="{{userPassword}}">
                                <span id="passwordError" style="color:red;"></span>
                            </p>
                            <p>
                                <label>비밀번호 확인 <span>*</span></label>
                                <input type="password" name="confirmPassword" id="confirmPassword" value="{{userPassword}}">
                                <span id="confirmPasswordError" style="color:red;"></span>
                            </p>
                            <p>   
                                <label>이름  <span>*</span></label>
                                <input type="text" name="name" id="name" value="{{userName}}">
                                <span id="nameError" style="color:red;"></span>
                             </p>
                			
                			<p>   
                                <label>주민등록번호(ex:######-#######) <span>*</span></label>
                                <input type="text" name="residentNumber" id="residentNumber" value="{{userResidentNumber}}">
                                <span id="residentNumberError" style="color:red;"></span>
                            </p>
                			
                             <p>
                                <label>성별  <span>*</span></label>
                                <select id="gender" name="gender">
                                    <option value="">성별 선택</option>
                                    <option value="남">남</option>
                                    <option value="여">여</option>
                                </select>
                                <span id="genderError" style="color:red;"></span>
                             </p>
                
                             <p>   
                                <label>핸드폰번호(ex:010-####-####)  <span>*</span></label>
                                <input type="text" name="phone" id="phone" value="{{userPhoneNumber}}">
                                <span id="phoneError" style="color:red;"></span>
                             </p>

                        <!-- <p>   
                                <label>주소  <span>*</span></label>
                                <input type="text" name="address" id="address" value="{{userAddress}}">
                                <span id="addressError" style="color:red;"></span>
                            </p>-->
                            
                            
                            <div class="col-lg-6 mb-20">
								<label>우편주소<span></span></label>
								<button type="button" class="btn btn-secondary btn-sm"
									onclick="execution_daum_address()">주소찾기</button>
								<input placeholder="우편번호" type="text" id="postcode" name="postcode" input readonly="readonly">

							</div>
							<div class="col-12 mb-20">
										<input placeholder="기본주소" input readonly="readonly"
											id="ordersAddress1" name="ordersAddress1"> <input placeholder="기본주소" input
											readonly="readonly" id="ordersAddress2" name="ordersAddress2"> <input
											placeholder="상세주소" type="text" id="ordersAddress3" name="ordersAddress3">
									</div>
                			
                            <div class="login_submit">
                                <button type="button" data-navigate="/user_write_action">확인</button>
                            </div>
						</form>
                        </div>
                    
                    </div>    
            
             
                 <!--login area start-->
            </div>
        </div>   
            </div> 
    </div>
    
    
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   	<script>
   		/* 다음 주소 연동 */
function execution_daum_address() {
	new daum.Postcode(
			{
				oncomplete : function(data) {
					// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

					// 각 주소의 노출 규칙에 따라 주소를 조합한다.
					// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
					var addr = ''; // 주소 변수
					var extraAddr = ''; // 참고항목 변수

					//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
					if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
						addr = data.roadAddress;
					} else { // 사용자가 지번 주소를 선택했을 경우(J)
						addr = data.jibunAddress;
					}

					// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
					if (data.userSelectedType === 'R') {
						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== ''
								&& /[동|로|가]$/g.test(data.bname)) {
							extraAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== ''
								&& data.apartment === 'Y') {
							extraAddr += (extraAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraAddr !== '') {
							extraAddr = ' (' + extraAddr + ')';
						}
						// 조합된 참고항목을 해당 필드에 넣는다.
						document.getElementById("ordersAddress2").value = extraAddr;

					} else {
						document.getElementById("ordersAddress2").value = '';
					}

					// 우편번호와 주소 정보를 해당 필드에 넣는다.
					document.getElementById('postcode').value = data.zonecode;
					document.getElementById("ordersAddress1").value = addr;
					
				
					// 커서를 상세주소 필드로 이동한다.
					
					document.getElementById("postcode").focus();
					
					
					
				}
			

			}).open();
			
			
			}
			

   	</script>
    
    </th:block>
    <!-- customer login end -->

    <!--footer area start-->
    
    <!--footer area end-->`;

	let bindTemplate = Handlebars.compile(htmlTemplate);
	let resultTemplate = bindTemplate(responseJsonObject);
	return resultTemplate;
}
