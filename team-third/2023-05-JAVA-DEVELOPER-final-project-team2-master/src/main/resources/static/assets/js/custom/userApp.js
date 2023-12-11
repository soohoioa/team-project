import {user_write_form} from './template-user-write-from.js';
import {user_login_form} from './template-user-login-from.js';
//import {user_login_PopUp} from './template-user-login-popUp.js';
import {user_finduserinfo_form} from './template-user-finduserinfo-form.js';
import {ajaxRequest} from './request.js';
import {createInitializer} from "./initializer.js";


let hash = window.location.hash
let path = hash.substring(1);
let html = '';

/*
초기실행메쏘드
*/
const initialize=createInitializer();
initialize.addCustomFunctionHandlebars();

const initialize2=createInitializer();

jQuery.validator.addMethod("phone", function(phone_number, element) {
    phone_number = phone_number.replace(/\s+/g, "");
    return this.optional(element) || phone_number.length > 9 && 
    phone_number.match(/010-\d{4}-\d{4}$/);
}, "핸드폰 번호 형식으로 써주세요");

jQuery.validator.addMethod("resident", function(resident_number, element) {
    resident_number = resident_number.replace(/\s+/g, "");
    return this.optional(element) || resident_number.length > 13 && 
    resident_number.match(/^\d{6}-\d{7}$/);
}, "주민등록번호 형식으로 써주세요");





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
		if (e.target.getAttribute('data-navigate') == '/user_write_action' || e.target.getAttribute('data-navigate') == '/login'
			|| e.target.getAttribute('data-navigate') == '/findUserId' || e.target.getAttribute('data-navigate') == '/findPassword') {
		
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
	if (path == '/user_write_form') {
		html = user_write_form();
		$('#content').html(html);
		initialize.validatorUserWriteFormSetDefault();
		let validator = $('#user_write_form').validate();
		initialize.setValidator(validator);
	}
	if (path == '/user_write_action') {
		/**************** /user_write_action******************/
		if (initialize.getValidator().form()) {
			let address = document.f.ordersAddress1.value+document.f.ordersAddress2.value + document.f.ordersAddress3.value;
			let sendJsonObject = {
					userId: document.f.userId.value,
					userPassword: document.f.password.value,
					userName: document.f.name.value,
					userGender: document.f.gender.value,
					userPhoneNumber: document.f.phone.value,
					userAddress: address,
					/*userAddress: document.f.postcode.value,*/
					userResidentNumber : document.f.residentNumber.value
			}
			const responseJsonObject = ajaxRequest('POST','user',sendJsonObject);
			
			
			
			window.location.hash = '/login_form';
		}
	}
	if (path == '/login_form') {
		html = user_login_form();
		$('#content').html(html);
	}
	if (path == '/login') {
		let sendJsonObject = {
				userId: document.f.loginUserId.value,
				userPassword: document.f.loginPassword.value,
		}
		let responseJsonObject = ajaxRequest('POST','user/login',sendJsonObject);
		
		if(responseJsonObject.status == 1000){
			if(responseJsonObject.userId=="admin@gmail.com"){
				window.location.href='adminUserList';
			}else{
				window.location.href='index';	
			}
			
		}
		
		if((responseJsonObject.status == 1001) || (responseJsonObject.status == 1002)) {
			html = user_login_form();
			$('#content').html(html);
			window.location.hash = '/login_form';
			alert('아이디 혹은 비밀번호를 잘못 입력 하셨습니다. ');
		}
	}
	if (path == '/loginPopUp') {
		let sendJsonObject = {
				userId: document.f.loginUserId.value,
				userPassword: document.f.loginPassword.value,
		}
		let responseJsonObject = ajaxRequest('POST','user/login',sendJsonObject);
		
		if(responseJsonObject.status == 1000){
			window.location.href='index';
		}
		
		if((responseJsonObject.status == 1001) || (responseJsonObject.status == 1002)) {
			html = user_login_form();
			$('#content').html(html);
			window.location.hash = '/login_form';
			alert('아이디 혹은 비밀번호를 잘못 입력 하셨습니다. ');
		}
	}
	if (path == '/findUserInfo') {
		html = user_finduserinfo_form();
		$('#content').html(html);
		
		initialize.validatorUserFindFormSetDefault();
		let validator = $('#userFindIdForm').validate();
		initialize.setValidator(validator);
		
		initialize2.validatorUserPasswordFormSetDefault();
		validator = $('#userFindPasswordForm').validate();
		initialize2.setValidator(validator);
	}
	if (path == '/findUserId') {
		if (initialize.getValidator().form()) {
			let sendJsonObject = {
					userName: document.userFindIdForm.name.value,
					userPhoneNumber: document.userFindIdForm.phone.value
			}
			const responseJsonObject = ajaxRequest('POST','user/findIdUserInfo',sendJsonObject);
			alert('찾으신 아이디:'+responseJsonObject.userId);
			$('.finduserId').val(responseJsonObject.userId);
		}
	}
	if (path == '/findPassword') {
		if (initialize2.getValidator().form()) {
			let sendJsonObject = {
					userId: document.userFindPasswordForm.userId.value,
					userPhoneNumber: document.userFindPasswordForm.phone.value
			}
			const responseJsonObject = ajaxRequest('POST','user/findPasswordUserInfo',sendJsonObject);
			alert('찾으신 비밀번호:'+responseJsonObject.userPassword);
			window.location.hash = "#/login_form";
		}
	}
}



init();

