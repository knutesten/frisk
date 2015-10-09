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
        document.getElementById(id).getElementByClassName("stockName").innerHTML = JSON.parse(data).query.results.quote.Name;
        document.getElementById(id).getElementByClassName("stockValue").innerHTML = JSON.parse(data).query.results.quote.Bid;
        document.getElementById(id).getElementByClassName("stockChange").innerHTML = JSON.parse(data).query.results.quote.Change;
        document.getElementById(id).getElementByClassName("stockPercentChange").innerHTML = JSON.parse(data).query.results.quote.PercentChange;
      });
  }
};