/**
 * Created by simena on 25.02.2015.
 */

var api = require("../rest/rest.js");

module.exports = {
  getStock: function(theUrl, data) {
    $.ajax({
      url: theUrl,
      data: { ticker: data }
    }).success(function(data) {
        var m = data.match(/"(.*?)"/);
        var v = data.match(/((?:\d*\.)?\d+)/);
        document.getElementById("stockName").innerHTML = m[1];
        document.getElementById("stockValue").innerHTML = v[1];
      });
  }
};