


//const GmailFactory = require("gmail-js");
//const gmail = new GmailFactory.Gmail($);

console.log('hello!');

setTimeout(function(){
	$(document).ready(function(){
		let gmail = new Gmail();
		console.log(gmail);
		let body = gmail.dom.email_contents();

		let email = gmail.get.user_email();

		console.log($(body).text());

		$("#checkPage").on("click", function() {
			console.log(body_text);

		});
	});
}, 2000);
