export function my_account_order(responseJsonObject = {}) {
	alert('asd');
	let htmltemplate =
	`<div class="modal fade" id="orderDetailsModal" tabindex="-1"
									aria-labelledby="orderDetailsModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content" style="width: 800px; height: 500px;" th:id="modalContent">
											<div class="modal-header">
												<h5 class="modal-title" id="orderDetailsModalLabel">주문 상세</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close" style="border: none; padding: 7px;">X
													<!--<span aria-hidden="true">&times;</span>-->
												</button>
											</div>
											<div class="modal-body">
												<!-- 주문 상세 내용이 여기에 로드됩니다 -->
								
												<div class="table-responsive">
													<table class="table">
														<thead>
															<tr>
																<th>번호</th>
																<th>상품가격</th>
																<th>상품명</th>
																<!--<th>총가격</th>-->
																<th>주문상태</th>
																<th>리뷰</th>
															</tr>
														</thead>
														{{#each data}}
														<tbody>
															<tr>
																<td>{{oiNo}}</td>
																<td>{{product.productPrice}}</td>
																<td ><span class="success">{{product.productName}}</span></td>
																<!-- <td>$25.00 for 1 item </td>-->
																<td> <select class="form-select form-select-sm"
																		aria-label="Large select example">
																		<option selected>주문 상태</option>
																		<option value="1">주문확인</option>
																		<option value="2">배송준비</option>
																		<option value="3">배송중</option>
																		<option value="4">배송완료</option>
																	</select>
																</td>
																<td><a href="cart.html" class="view">리뷰쓰기</a></td>
															</tr>
														</tbody>
														{{/each}}
													</table>
												</div>

											</div>
											
											
											<div class="modal-footer">
												<!--   <button type="button" class="btn btn-primary">리뷰 저장</button>-->
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">닫기</button>
													 <button type="button" class="btn btn-primary">저장</button>

											</div>
										</div>
									</div>
								</div>`;
	
	let bindTemplate = Handlebars.compile(htmltemplate);
	let resultTemplate = bindTemplate(responseJsonObject);
	
	return resultTemplate;
}