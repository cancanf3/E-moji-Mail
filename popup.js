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
		console.log($(body).text());
    }
  });    
