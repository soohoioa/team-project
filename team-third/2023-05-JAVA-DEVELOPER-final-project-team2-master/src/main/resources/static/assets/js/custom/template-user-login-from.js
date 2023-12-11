
export function user_login_form(responseJsonObject = {}) {
	let htmlTemplate =
	`<th:block layout:fragment="content">
      <div id="content">
         <!--breadcrumbs area end-->

         <!-- customer login start -->

         <div class="customer_login">
            <div class="container">
               <div class="row" style="display: flex; flex-direction: column; justify-content: center; align-items: center;">
                  <!--login area start-->
                  <div class="col-lg-6 col-md-6">
                  
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
                            
                     <form name="f" id = "loginForm">
                     <h1 class="h3 mb-3 fw-normal">로그인</h1>
                     <div class="form-floating" style="margin-bottom:10px;">
                        <label>아이디</label>
                        <input type="email" class="form-control" id="loginUserId"
                           placeholder="name@example.com">
                     </div>
                     <div class="form-floating" style="margin-bottom:10px;">
                        <label>비밀번호</label>
                        <input type="password" class="form-control" id="loginPassword"
                           placeholder="Password">
                     </div>
                     <a></a>
                     <a></a>
                     <a></a>
                     <a></a>
	  				<a class="navi-link" href="#/findUserInfo" style="color:red;">아이디/비밀번호 찾기</a>
                     
                     <button class="btn btn-success w-100 py-1" type="button" data-navigate="/login" style="margin-top:20px;">로그인</button>
                     <!--<button type="button" data-navigate="/login">Sign in</button>-->
                  </div>
                  </form>
               </div>
            </div>
            <!--login area start-->

            <!--register area start-->

            <!--register area end-->
         </div>
      </div>
      </div>
      </div>
      <script>
		$('#loginForm').keypress(function(e){
			  if(e.keyCode==13){
				  window.location.hash = '#/login'
			  }
		  });
		</script>
   </th:block>`;
	
	let bindTemplate = Handlebars.compile(htmlTemplate);
	let resultTemplate = bindTemplate(responseJsonObject);
	return resultTemplate;
}
