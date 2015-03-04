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
        //console.log(JSON.parse(data));
        document.getElementById("stockName").innerHTML = JSON.parse(data).query.results.quote.Name;
        document.getElementById("stockValue").innerHTML = JSON.parse(data).query.results.quote.Bid;
        document.getElementById("stockChange").innerHTML = JSON.parse(data).query.results.quote.Change;
        document.getElementById("stockPercentChange").innerHTML = JSON.parse(data).query.results.quote.PercentChange;
      });
  }
};