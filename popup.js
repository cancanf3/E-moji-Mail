


//const GmailFactory = require("gmail-js");
//const gmail = new GmailFactory.Gmail($);

console.log('hello!');

$(document).ready(function(){
	let gmail = new Gmail();
	let body = gmail.dom.email_contents();
	console.log(body);

	let email = gmail.get.user_email();
	console.log(email);

	var body_text = $(body).text();
	console.log(body_text);

	$("#checkPage").on("click", function() {
		console.log(body_text);

	});
});
