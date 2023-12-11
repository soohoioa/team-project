export function product_list_form(responseJsonObject = {}) {
	
	let htmltemplate = 
	`<div class="row shop_wrapper" id="ListContent">
	{{#each productsDto}}
                        <div class="col-lg-4 col-md-4 col-sm-6 col-12" >
                            <div class="single_product">
                                <div class="product_thumb">
                                        <a class="primary_img" href="product-details?productNo={{productNo}}"><img src="/image/product/{{productImage}}" alt=""></a>

                                       <!-- <a class="secondary_img" href="product-details.html"><img src="assets/img/product/productbig2.jpg" alt=""></a>-->
                                        <div class="action_links">
                                            <ul>
                                                <li class="add_to_cart"><a href="cart.html" title="Add to cart"><span class="lnr lnr-cart"></span></a></li>
                                                <!--<li class="quick_button"><a href="#" data-toggle="modal" data-target="#modal_box"  title="quick view"> <span class="lnr lnr-magnifier"></span></a></li>-->
                                                 <li class="wishlist"><a href="wishlist.html" title="Add to Wishlist"><span class="lnr lnr-heart"></span></a></li>  
                                                <!--<li class="compare"><a href="#" title="Add to Compare"><span class="lnr lnr-sync"></span></a></li>-->
                                            </ul>
                                        </div>
                                    </div>
                                    
                                    
                                <div class="product_content grid_content">
                                        <h4 class="product_name"><a href="product-details.html">{{productName}}</a></h4>
                                        <p><a href="#">{{productPetCategory}}</a></p>
                                        <div class="price_box"> 
                                            <span class="current_price">{{productPrice}}</span>
                                           <!-- <span class="old_price">$362.00</span>-->
                                        </div>
                                    </div>
                                    
                                    
                                    
                                    
                                    
                                    
                                <div class="product_content list_content">
                                    <h4 class="product_name"><a href="product-details.html">{{productName}}</a></h4>	
                                    <p><a href="#">Fruits</a></p>
                                    <div class="price_box"> 
                                        <span class="current_price">{{productPrice}}</span>
                                        <!--<span class="old_price">$362.00</span>-->
                                    </div>
                                    <div class="product_desc">
                                        <p>Nunc facilisis sagittis ullamcorper. Proin lectus ipsum, gravida et mattis vulputate, tristique ut lectus. Sed et lorem nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean eleifend laoreet congue. Viva..</p>
                                    </div>
                                    <div class="action_links list_action_right">
                                        <ul>
                                            <li class="add_to_cart"><a href="cart.html" title="Add to cart">Add to Cart</a></li>
                                           <!--  <li class="quick_button"><a href="#" data-toggle="modal" data-target="#modal_box"  title="quick view"> <span class="lnr lnr-magnifier"></span></a></li> -->
                                             <li class="wishlist"><a href="wishlist.html" title="Add to Wishlist"><span class="lnr lnr-heart"></span></a></li>  
                                            <!-- <li class="compare"><a href="#" title="Add to Compare"><span class="lnr lnr-sync"></span></a></li> -->
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>`;
	
	let bindTemplate = Handlebars.compile(htmltemplate);
	let resultTemplate = bindTemplate(responseJsonObject);
	
	return resultTemplate;
}