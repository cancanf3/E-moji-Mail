function processInboxToSheet() {
  //var threads = GmailApp.getInboxThreads();
  // Have to get data separate to avoid google app script limit!
  var start = 0;
  var threads = GmailApp.getInboxThreads(start, 100);
  var sheet = SpreadsheetApp.getActiveSheet();
  var result = [];
  
  for (var i = 0; i < threads.length; i++) {
    var messages = threads[i].getMessages();
    
    var content = messages[0].getPlainBody();

    // implement your own parsing rule inside
    if (content) {
      var tmp;
      tmp = content.match(/Name:\s*([A-Za-z0-9\s]+)(\r?\n)/);
      var username = (tmp && tmp[1]) ? tmp[1].trim() : 'No username';
      
      tmp = content.match(/Email:\s*([A-Za-z0-9@.]+)/);
      var email = (tmp && tmp[1]) ? tmp[1].trim() : 'No email';
      
      tmp = content.match(/Subject:\s*([A-Za-z0-9\s]+)(\r?\n)/);
      var subject = (tmp && tmp[1]) ? tmp[1].trim() : 'No subject';
      
      tmp = content.match(/Comments:\s*([\s\S]+)/);
      var comment = (tmp && tmp[1]) ? tmp[1] : 'No comment';
      
      sheet.appendRow([username, email, subject, comment]);
      
      Utilities.sleep(500);
    }
  }
};