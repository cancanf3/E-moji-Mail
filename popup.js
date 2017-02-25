/* Title: E-moji Mail
 * Author: Jose Peña, Navon Francis, Tim Henry, Alexander Lascaibar
 * Hackathon@FIU: Mango Hacks
 * 02/24/17 - 02/26/17
 * Google Extension (JavaScript + IBM Watson API)
 */




// Back-end is waiting for a trigger from the extension to Analyze the text.
chrome.runtime.onMessage.addListener( function(request, sender, sendResponse) {
    if (request.analyze == true) {
    	//Here is where the server logic should be
    	console.log("it worked");
    	let gmail = new Gmail();
			let body = gmail.dom.email_contents();
			var text = ($(body).text());
			console.log(text);

			var ToneAnalyzerV3 = require('watson-developer-cloud/tone-analyzer/v3');
			var tone_analyzer = new ToneAnalyzerV3({
			  username: '<0c9aa4c7-3806-4b4b-9110-f97eb32eea93>',
			  password: '<lA1fuWgH8ROI>',
			  version_date: '2016-05-19'
			});

			tone_analyzer.tone({ text: 'Greetings from Watson Developer Cloud!' },
			  function(err, tone) {
			    if (err)
			      console.log(err);
			    else
			      console.log(JSON.stringify(tone, null, 2));
			});
    	}
  });
