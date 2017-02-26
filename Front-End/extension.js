/* Title: E-moji Mail
 * Author: Jose Peña, Navon Francis, Tim Henry, Alexander Lascaibar
 * Hackathon@FIU: Mango Hacks  
 * 02/24/17 - 02/26/17
 * Google Extension (JavaScript + Java + IBM Watson API)
 */



// Extension is handling a Button event that when triggered, it will send a signal to the Back-end
$(document).on('click','#checkPage', function() {
	chrome.tabs.query({active: true, currentWindow: true}, function(tabs) {
	   chrome.tabs.sendMessage(tabs[0].id, {analyze: true}, function(response) {
	   });
	 });
});
