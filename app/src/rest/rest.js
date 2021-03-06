/**
 * Created by simena on 23.02.2015.
 */

module.exports = {

  httpGet: function (theUrl) {
    return $.ajax({
      type: "GET",
      dataType: "json",
      url: theUrl
    });
  },

  httpPost: function (theUrl, data) {
    return $.ajax({
      type: "POST",
      headers: {
        'Content-Type': 'application/json'
      },
      dataType: "json",
      url: theUrl,
      data: data
    });
  },
  
  httpDelete: function (theUrl) {
    return $.ajax({
      type: "DELETE",
      dataType: "json",
      url: theUrl
    });
  }
};

//MinModul = (function(){
//  var x = 0;
//
//  return {
//    x: 0,
//    f: function(){}
//
//  }
//})();