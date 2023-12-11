
export function user_finduserinfo_form(responseJsonObject = {}) {
	let htmlTemplate =
	`<th:block layout:fragment="content">
	<div id="content">
    <div class="customer_login">
        <div class="container">
            <div class="row">
               <!--login area start-->
                <div class="col-lg-6 col-md-6">
                 <div class="account_form register">
                 
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
                 
                        <h2>아이디 찾기</h2>
                        <form name="userFindIdForm" id="userFindIdForm" action="#">
                            <p>   
                                <label>이름 <span>*</span></label>
                                <input type="text" id="name" name="name">
                                <span id="nameError" style="color:red;"></span>
                             </p>
                             <p>   
                                <label>전화번호 <span>*</span></label>
                                <input type="text" id="phone" name="phone">
                                <span id="phoneError" style="color:red;"></span>
                             </p>
                             <label id="findIdText" style="color: green"></label>
                            <div class="login_submit">
                                <button type="button" data-navigate="/findUserId">확인</button>
                            </div>
                        </form>
                    </div>  
                </div>
                <!--login area start-->
				<!--register area start-->
                <div class="col-lg-6 col-md-6">
					          <div class="account_form">
                        <h2>비밀번호 찾기</h2>
                        <form name="userFindPasswordForm" id="userFindPasswordForm" action="#">
                            <p>   
                                <label>아이디<span>*</span></label>
                                <input class ="finduserId" type="text" id="userId" name="userId">
                             </p>
                             <p>   
                                <label>전화번호<span>*</span></label>
                                <input type="text" id="phone" name="phone">
                             </p>   
                            <div class="login_submit">
                                <button type="button" data-navigate="/findPassword">확인</button>
                            </div>

                        </form>
                     </div>  
                 
                </div>
                <!--register area end-->
            </div>
        </div>    
    </div>
    </div>`;
	
	let bindTemplate = Handlebars.compile(htmlTemplate);
	let resultTemplate = bindTemplate(responseJsonObject);
	return resultTemplate;
}
