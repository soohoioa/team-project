import {ajaxRequest} from './request.js';
import {createInitializer} from "./initializer.js";
import {reviewBoard} from "./template-reviewBoard.js";

let hash = window.location.hash
let path = hash.substring(1);
let html = '';

/*
초기실행메쏘드
*/
const initialize=createInitializer();
initialize.addCustomFunctionHandlebars();

function init() {
	
	registEvent();
	navigate();
}
/*
이벤트등록
*/
function registEvent() {
	/*
	$(window).on('load', function(e) {
		alert('load  event:' + e);
	});
	*/
	$(window).on('hashchange', function(e) {
		//alert('hashchange event:' + e);
		hash = window.location.hash
		path = hash.substring(1);
		navigate();
	});
	
	$(document).on('click', function(e) {
		//if ($(e.target).attr('data-navigate')) {
			//alert('click event --> hash변경:' + $(e.target).attr('data-navigate'));
			// 해쉬 변경
			//window.location.hash = e.target.getAttribute('data-navigate');
		//}
		if (e.target.getAttribute('data-navigate') == '/user_write_action') {
		
			if (window.location.hash.substring(1) == e.target.getAttribute('data-navigate')) {
				// 현재 hash 값과 button data-navigate 속성값이 같은 경우(hashchange 이벤트 발생 안함)
				 navigate();
			} else {
				// 현재 hash 값과 button data-navigate 속성값이 다른 경우(hashchange 이벤트 발생)
				window.location.hash = e.target.getAttribute('data-navigate');
			}
		} else {
			if(e.target.getAttribute('data-navigate')!=null){
				window.location.hash = e.target.getAttribute('data-navigate');
			}
		}
		
	});
	
}

/*
	이벤트발생시 처리메쏘드
*/
function navigate() {
	if (path == '/reviewStarDesc') {
		let productNo = $('#productNo').val();
		let sendObject = {
			productNo : productNo
		}
		let responseObject = ajaxRequest('POST','reviewBoard/productDetail',sendObject);
		console.log(responseObject);
		html = reviewBoard(responseObject);
		$('#reviewContent').html(html);
	}
	
	if (path == '/reviewRatingDesc') {
		let productNo = $('#productNo').val();
		let sendObject = {
			productNo : productNo
		}
		let responseObject = ajaxRequest('POST','reviewBoard/productDetailDate',sendObject);
		console.log(responseObject);
		html = reviewBoard(responseObject);
		$('#reviewContent').html(html);
		
	}
	
	
}



init();

