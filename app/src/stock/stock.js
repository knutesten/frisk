/**
 * Created by simena on 25.02.2015.
 */

module.exports = {
  getStock: function() {
    $.get("http://finance.yahoo.com/d/quotes.csv?s=FUNCOM.OL&f=sb1")
      .success(function(data) {
        var m = data.match(/"(.*?)"/);
        var v = data.match(/((?:\d*\.)?\d+)/);
        document.getElementById("stockName").innerHTML = m[1];
        document.getElementById("stockValue").innerHTML = v[1];
      });
  }
};