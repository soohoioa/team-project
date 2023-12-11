
export function petList(responseJsonObject = {}) {
	let htmlTemplate =
	`
   <!--header area start-->
    
    <!--offcanvas menu area start-->
    
    <!--header area end-->

    <th:block layout:fragment="content">
    <!--breadcrumbs area start-->
   
    <!--breadcrumbs area end-->
    
    <!--shop  area start-->
    <div class="shop_area shop_fullwidth mt-70 mb-70">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!--shop wrapper start-->
                    <!--shop toolbar start-->
                    <div class="shop_toolbar_wrapper">
                        <div class="shop_toolbar_btn">
								
                            <button data-role="grid_3" type="button" class=" btn-grid-3" data-toggle="tooltip" title="3"></button>

                            <button data-role="grid_4" type="button"  class=" btn-grid-4" data-toggle="tooltip" title="4"></button>

                            <button data-role="grid_list" type="button"  class="active btn-list" data-toggle="tooltip" title="List"></button>
                        </div>
                        <div class=" niceselect_option">
                            <form class="select_option" action="#">
                                <select name="orderby" id="short">

                                    <option selected value="1">동물선택 </option>
                                    <option  value="강아지">강아지</option>
                                    <option value="고양이">고양이</option>
                                   <!--  <option value="4">Sort by price: low to high</option>
                                    <option value="5">Sort by price: high to low</option>
                                    <option value="6">Product Name: Z</option> -->
                                </select>
                            </form>
                            
                            <!-- 지역선택은 하나더추가 -->
                        </div>
                        <div class=" niceselect_option">
                            <form class="select_option" action="#">
                                <select name="orderby" id="short">

                                    <option selected value="1">지역선택 </option>
                                    <option  value="서울특별시">서울특별시</option>
                                    <option value="경기도">경기도</option>
                                    <option  value="강원도">강원도</option>
                                    <option value="충청남도">충청남도</option>
                                    <option  value="충청북도">충청북도</option>
                                    <option value="경상남도">경상남도</option>
                                    <option value="경상북도">경상북도</option>
                                    <option value="제주도">제주도</option>
                                   <!--  <option value="4">Sort by price: low to high</option>
                                    <option value="5">Sort by price: high to low</option>
                                    <option value="6">Product Name: Z</option> -->
                                </select>
                            </form>
                            
                            
                        </div>
                        <!-- <div class="page_amount">
                            <p>Showing 1–9 of 21 results</p>
                        </div> -->
                    </div>
                     <!--shop toolbar end-->
                     {{#each data}}
                     <!--펫 시작  -->
                     <div class="row shop_wrapper grid_list" th:each="pet:${petList}">
                        <div class="col-12 ">
                            <div class="single_product">
                                <div class="product_thumb">
                                        <!--  --><a class="primary_img" ><img src="image/pet/{{petImage}}"  alt=""></a>
                                      <!--    <a class="secondary_img" ><img src="image/pet/강아지2.jpeg" alt=""></a>-->
                                        
                                        
                                        <!-- <div class="label_product">
                                            <span class="label_sale">Sale</span>
                                            <span class="label_new">New</span>
                                        </div> -->
                                        
                                        
                                        <div class="action_links">
                                            <ul>
                                                <li class="add_to_cart"><a href="cart.html" title="Add to cart"><span class="lnr lnr-cart"></span></a></li>
                                                <li class="quick_button"><a href="#" data-toggle="modal" data-target="#modal_box"  title="quick view"> <span class="lnr lnr-magnifier"></span></a></li>
                                                 <li class="wishlist"><a href="wishlist.html" title="Add to Wishlist"><span class="lnr lnr-heart"></span></a></li>  
                                                <li class="compare"><a href="#" title="Add to Compare"><span class="lnr lnr-sync"></span></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    
                                <div class="product_content grid_content">
                                        <h4 class="product_name">
                                        <a href="product-details.html">Aliquam Consequat</a></h4>
                                        <p><a href="#">Fruits</a></p>
                                        <div class="price_box"> 
                                            <span class="current_price">$26.00</span>
                                            <span class="old_price">$362.00</span>
                                            
                                        </div>
                                    </div>
                                    
                                <div class="product_content list_content">
                                    <h4 class="pet_type"><a href="product-details.html" >Aliquam Consequat</a></h4>
                                    <p><a href="#" >Fruits</a></p>
                                    <div class="price_box"> 
                                    	
                                       <!--  <span class="current_price">$26.00</span>
                                        <span class="old_price">$362.00</span><br> -->
                                        <span class="Local" >지 역 : </span>
                                        <span class="petLocal" >{{petLocal}}</span><br>
                                           <span class="Gender">성 별 :</span>
                                        <span class="petGender">{{petGender}}</span><br>
                                           <span class="registerDate">등록날짜 :</span>
                                        <span class="petRegisterDate">{{petRegisterDate}}</span><br>
                                           <span class="findPlace">발견장소 :</span>
                                        <span class="petFindPlace">{{petRegisterDate}}</span><br>
                                           <span class="character" >설 명 :</span>
                                        <span class="petCharacter">{{petRegisterDate}}</span><br>
                                           <span class="center">센터이름 :</span>
                                        <span class="pet_center">{{center.getCenterName}}</span><br>
                                        
                                        
                                        <br>
                                          {{/each}}
                                    </div>
                                    <!-- <div class="product_desc">
                                        <p>Nunc facilisis sagittis ullamcorper. Proin lectus ipsum, gravida et mattis vulputate, tristique ut lectus. Sed et lorem nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean eleifend laoreet congue. Viva..</p>
                                    </div> -->
                                    <div class="action_links list_action_right">
                                        <ul>
                                            <li class="add_to_cart"><a href="visit" title="입양하기">입양</a></li>
                                            <li class="add_to_cart"><a href="visit" title="견학하기">견학</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <!-- 펫끝 -->
                        
                       
                       
                      
                       
                        
                       
                    </div>

                    <div class="shop_toolbar t_bottom">
                        <div class="pagination">
                            <ul>
                                <li class="current">1</li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li class="next"><a href="#">next</a></li>
                                <li><a href="#">>></a></li>
                            </ul>
                        </div>
                    </div>
                    <!--shop toolbar end-->
                    <!--shop wrapper end-->
                </div>
            </div>
        </div>
    </div>
    
    <!--shop  area end-->
    
  <!--footer area start-->
    
    <!--footer area end-->
    
    <!-- modal area start-->
   
    <!-- modal area end-->
</th:block>`;
	
	let bindTemplate = Handlebars.compile(htmlTemplate);
	let resultTemplate = bindTemplate(responseJsonObject);
	return resultTemplate;
}
