/**
 * Created by simena on 25.02.2015.
 */

var api = require("../rest/rest.js");

module.exports = {
  getStock: function(theUrl, data, id) {
    $.ajax({
      url: theUrl,
      data: { ticker: data }
    }).success(function(data) {
        //console.log(JSON.parse(data));
        var results = JSON.parse(data).query.results;
        document.getElementById(id).getElementsByClassName("stockName")[0].innerHTML = results.quote.symbol;
        document.getElementById(id).getElementsByClassName("stockValue")[0].innerHTML = results.quote.Bid;
        document.getElementById(id).getElementsByClassName("stockChange")[0].innerHTML = results.quote.Change;
        document.getElementById(id).getElementsByClassName("stockPercentChange")[0].innerHTML = results.quote.PercentChange;
      });
  }
};