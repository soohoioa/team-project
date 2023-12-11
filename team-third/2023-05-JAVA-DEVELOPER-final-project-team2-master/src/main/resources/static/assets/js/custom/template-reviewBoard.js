export function reviewBoard(responseJsonObject = {}) {
  Handlebars.registerHelper('formatDate', function (date) {
    var options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
    return new Date(date).toLocaleDateString('ko-KR', options);
  });
	let htmlTemplate =
		`<div class="reviews_wrapper">
        {{#	each this}}
        <h2>{{formatDate boardDate}}</h2>
        <div class="reviews_comment_box">
            <div class="comment_thmb" style="width:50px">
              <p><strong>{{userName}}</strong></p>

              


            </div>
            <div class="comment_text">
                <div class="reviews_meta">
                   <div class="star_rating">
															<ul>
																<li>
																	<a href="#">
																		<img src={{starNumber boardStar 1}} alt="Filled Star">
																	</a>
																</li>
																<li>
																	<a href="#">
																		<img src={{starNumber boardStar 2}} alt="Filled Star">
																	</a>
																</li>
																<li>
																	<a href="#">
																		<img src={{starNumber boardStar 3}} alt="Filled Star">
																	</a>
																</li>
																<li>
																	<a href="#">
																		<img src={{starNumber boardStar 4}} alt="Filled Star">
																	</a>
																</li>
																<li>
																	<a href="#">
																		<img src={{starNumber boardStar 5}} alt="Filled Star">
																	</a>
																</li>
															</ul>

														</div>
               
                    <h2>
                        <p>{{boardTitle}}</p>
                    </h2>
                    <p>{{boardContent}}</p>
                </div>
            </div>
        </div>
        {{/each}}
    </div>
</div>`;
	console.log(responseJsonObject);
let bindTemplate = Handlebars.compile(htmlTemplate);
let resultTemplate = bindTemplate(responseJsonObject);
return resultTemplate;
}