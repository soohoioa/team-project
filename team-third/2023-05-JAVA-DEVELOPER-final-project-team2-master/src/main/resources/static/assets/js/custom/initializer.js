function createInitializer() {
	let validator = {};
	
	const initializer = {
		
		setValidator: function(v) {
			console.log(v);
			validator = v;
		}
		,
		getValidator: function() {
			return validator;
		},
		
		addCustomFunctionHandlebars: function() {
			/*****Handlebars 함수등록 */
			 window.Handlebars.registerHelper('select', function( value, options ){
				var $el = $('<select />').html( options.fn(this) );
		        $el.find('[value="' + value + '"]').attr({'selected':'selected'});
		        return $el.html();
		    });
			Handlebars.registerHelper('substring', function(str, start, end) {
				return str.substring(start, end);
			});
			Handlebars.registerHelper('intToString', function(i) {
				return i+'';
			});
			Handlebars.registerHelper('toUpper', function(str) {
				return str.toUpperCase();
			});
			Handlebars.registerHelper('starNumber',function(boardStar,star){
				//assets/img/icon/star-icon2.png
				//"assets/img/icon/star-icon.png"
				if(boardStar >= star){
					return "assets/img/icon/star-icon2.png";
				}else{
					return "assets/img/icon/star-icon.png"
				}
			});
			Handlebars.registerHelper('ifCond', function(v1, operator, v2, options) {
				switch (operator) {
					case '==':
						return (v1 == v2) ? options.fn(this) : options.inverse(this);
					case '===':
						return (v1 === v2) ? options.fn(this) : options.inverse(this);
					case '!=':
						return (v1 != v2) ? options.fn(this) : options.inverse(this);
					case '!==':
						return (v1 !== v2) ? options.fn(this) : options.inverse(this);
					case '<':
						return (v1 < v2) ? options.fn(this) : options.inverse(this);
					case '<=':
						return (v1 <= v2) ? options.fn(this) : options.inverse(this);
					case '>':
						return (v1 > v2) ? options.fn(this) : options.inverse(this);
					case '>=':
						return (v1 >= v2) ? options.fn(this) : options.inverse(this);
					case '&&':
						return (v1 && v2) ? options.fn(this) : options.inverse(this);
					case '||':
						return (v1 || v2) ? options.fn(this) : options.inverse(this);
					default:
						return options.inverse(this);
				}
			});

		
		Handlebars.registerHelper('ifEqual', function(arg1, arg2, options) {
  				return (arg1 === arg2) ? options.fn(this) : options.inverse(this);
			});


		},
		getMessageScript: function() {
			$.getScript(`js/localization/messages_${navigator.language}.js`);
		},
		validatorSetDefault: function() {
			$.validator.setDefaults({});
		},
		validatorUserWriteFormSetDefault: function() {
			$.validator.setDefaults({
		rules: {
			userId: {
				required: true,
				email : true,
				remote: {
					url: 'user/idcheck',
					type: 'GET',
					data: {
						userId: function() {
							return $('#userId').val();
						}
					}
				}
			},
			password: {
				required: true
			},
			confirmPassword: {
				required: true,
				equalTo: '#password'
			},
			name: {
				required: true
			},
			phone :{
				required: true,
				phone : true
			},
			residentNumber :{
				required: true,
				resident : true
			},
			gender : {
				required : true
			}/*,
			postcode :{
				required:true
			},
			ordersAddress1:{
				required:true
			}*/,
			ordersAddress3:{
				required:true
			}
			
		},
		messages: {
			userId: {
				required: '아이디를 입력하세요.',
				email : '이메일로 입력해주세요.',
				remote: '{0}는 중복된 아이디입니다.'
			},
			password: {
				required: '패스워드를 입력하세요.'
			},
			confirmPassword: {
				required: '패스워드 확인을 입력하세요.',
				equalTo: '패스워드가 일치하지 않습니다.'
			},
			name: {
				required: '이름을 입력하세요.'
			},
			phone :{
				required: '휴대폰 번호를 입력하세요.',
			},
			residentNumber :{
				required: '주민등록번호를 입력하세요.',
			},
			gender :{
				required: '성별을 선택하세요.',
			}/*,
			postcode :{
				required:'우편번호를 입력하세요.'
			},
			ordersAddress1:{
				required:'주소를 입력하세요.'
			}*/,
			ordersAddress3:{
				required:'주소를 입력하세요.'
			}
		},
		errorClass: 'error',
		validClass: 'valid'
	});
		},
		validatorUserFindFormSetDefault: function() {
			$.validator.setDefaults({
		rules: {
			name:{
				required : true
			},
			phone:{
				required : true,
				phone : true
			}
		},
		messages: {
			name:{
				required : '이름을 입력하세요'
			},
			phone: {
				required: '전화번호를 입력하세요',
				phone : '휴대폰 번호를 입력하세요.'
			}
		},
		errorClass: 'error',
		validClass: 'valid'
	});
		},
		validatorUserPasswordFormSetDefault: function() {
			$.validator.setDefaults({
		rules: {
			userId:{
				required : true,
				email : true
			},
			phone:{
				required : true,
				phone : true
			}
		},
		messages: {
			userId:{
				required : '아이디를 입력하세요',
				email : '이메일 형식으로 입력하세요'
			},
			phone: {
				required: '전화번호를 입력하세요',
				phone : '휴대폰 번호를 입력하세요.'
			}
		},
		errorClass: 'error',
		validClass: 'valid'
	});
		}

	}

	return initializer;
}
export { createInitializer };