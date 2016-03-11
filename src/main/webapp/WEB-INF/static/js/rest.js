function RestServiceJs(newurl) {
	this.myurl = newurl;

	this.post = function(model, callback) {
		$.ajax({
			type : 'POST',
			url : this.myurl,
			data : JSON.stringify(model), // '{"name":"' + model.name + '"}',
			dataType : 'text',
			processData : false,
			contentType : 'application/json',
			success : callback,
			error : function(req, status, ex) {
			},
			timeout : 60000
		});
	};

	this.put = function(model, callback) {
		$.ajax({
			type : 'PUT',
			url : this.myurl,
			data : JSON.stringify(model), // '{"name":"' + model.name + '"}',
			dataType : 'text',
			processData : false,
			contentType : 'application/json',
			success : callback,
			error : function(req, status, ex) {
			},
			timeout : 60000
		});
	};

	this.find = function(callback) {
		$.ajax({
			type : 'GET',
			url : this.myurl,
			contentType : 'application/json',
			success : callback,
			error : function(req, status, ex) {
			},
			timeout : 60000
		});
	};

	this.remove = function(callback) {
		$.ajax({
			type : 'DELETE',
			url : this.myurl,
			contentType : 'application/json',
			success : callback,
			error : function(req, status, ex) {
			},
			timeout : 60000
		});
	}
}
