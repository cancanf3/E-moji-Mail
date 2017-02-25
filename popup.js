let body = gmail.dom.email_contents();

var body_text = $(body).text();

$("#checkPage").on("click","#checkPage", function() {
	console.log(body_text);

});