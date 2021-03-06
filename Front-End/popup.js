/* Title: E-moji Mail
 * Author: Jose Peña, Navon Francis, Tim Henry, Alexander Lascaibar
 * Hackathon@FIU: Mango Hacks
 * 02/24/17 - 02/26/17
 * Google Extension (JavaScript + Java + IBM Watson API )
 */




// Creating a Json package to comunicate with the server is waiting for a trigger from the extension to Analyze the text.
chrome.runtime.onMessage.addListener( function(request, sender, sendResponse) {
    if (request.analyze == true) {
      //Here is where the server logic should be
      console.log("it worked!");
      let gmail = new Gmail();
    let body = gmail.dom.email_contents();
    var text = ($(body).text());
    $.ajax( {
            type: 'POST',
            url: 'https://c5aab4ae.ngrok.io/e-moji/webresources/service',
            data: text,

            success:function(respond) {
                alert(respond["body"]);
            },
            error:function (xhr, ajaxOptions, thrownError){
              alert(thrownError);
            }
        });
 
    }
 });
