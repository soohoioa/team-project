export function ajaxRequest(method, url, sendJsonObject) {
	let returnResultJsonObject={};
	$.ajax({
		async:false,
		method: method,
		url: url,
		contentType: 'application/json;UTF-8',
		data: JSON.stringify(sendJsonObject),
		type: 'json',
		success: function(resultJsonObject) {
			returnResultJsonObject = resultJsonObject;
		}
	});
	return returnResultJsonObject;
}
export function ajaxRequestAsync(method, url,callbackFunction, sendJsonObject) {
	let returnResultJsonObject={};
	$.ajax({
		method: method,
		url: url,
		contentType: 'application/json;UTF-8',
		data: JSON.stringify(sendJsonObject),
		type: 'json',
		success: function(resultJsonObject) {
			callbackFunction(resultJsonObject);
		}
	});
	
	return returnResultJsonObject;
	
}
export function ajaxRequestPromise(method, url, sendJsonObject) {
	return new Promise(function(resolve, reject) {
		$.ajax({
			method: method,
			url: url,
			contentType: 'application/json;UTF-8',
			data: JSON.stringify(sendJsonObject),
			type: 'json',
			success: function(resultJsonObject) {
				resolve(resultJsonObject);
			},
			fail: function(resultJsonObject) {
				reject(resultJsonObject)
			}
		});
	});
	/*let xhr = new XMLHttpRequest();
	xhr.open(method, url, true);
	xhr.setRequestHeader('Content-Type', 'application/json;chraset=UTF-8');
	xhr.onload = function() {
		callbackFunction(JSON.parse(xhr.responseText));
	}
	xhr.send(JSON.stringify(sendJsonObject));*/
}
