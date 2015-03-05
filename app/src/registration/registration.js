
var api = require("../rest/rest.js");
var moment = require("moment");
var Chartist = require("chartist");
var stock = require("../stock/stock.js");

function deleteLogEntry(id) {
  api.httpDelete(localUrl + "/log" + "/"  + id)
    .success(function() {
      fetchFriskLog();
    });
}

function fetchTotalFriskCount() {
  api.httpGet(localUrl + "/log/count/" + 2)
    .success(function(data) {
      document.getElementById("friskCount").innerHTML = data[1];
    });
}

function fetchFriskCountPerUser() {
  api.httpGet(localUrl + "/log/user-frisk-count/" + 2)
    .success(function(data) {
      var labels = [];
      var series = [];
      data.forEach(function(obj) {
        labels.push(obj[0]);
        series.push(obj[1]);
      });

      new Chartist.Pie('.ct-chart', {
        labels: labels,
        series: series
      }, {
        donut: true
      });
    });
}

function fetchFriskLog() {
  var table = document.getElementById('friskTableBody');
  $("#friskTableBody > tr").remove();
  api.httpGet(localUrl + '/log/formatted')
    .success(function(data) {
      fillTableWithLogData(data);
    });

  function fillTableWithLogData(data) {
    var row;
    var button;
    for(var i = 0; i < data.length; i++) {
      row = table.insertRow(i);
      row.insertCell(0).innerHTML = data[i][0];
      row.insertCell(1).innerHTML = data[i][1];
      row.insertCell(2).innerHTML = data[i][2];
      row.insertCell(3).innerHTML = data[i][3];
      row.insertCell(4).innerHTML = data[i][4];
      row.insertCell(5).innerHTML = moment(data[i][5]).format('MMMM Do YYYY, HH:mm:ss');
      button = document.createElement("button");
      button.innerHTML = "Delete";
      button.onclick = function(num) {
        return function() {
          deleteLogEntry(data[num][0]);
          fetchTotalFriskCount();
        }
      }(i);
      row.insertCell(6).appendChild(button);
    }
  }
}

function populateSelect(element, list, prop) {
  var opt;
  for(var i = 0; i < list.length; i++) {
    opt = document.createElement("option");
    opt.value = list[i].id;
    opt.innerHTML = list[i][prop];

    element.appendChild(opt);
  }
}

function fillProjectSelect(element) {
  api.httpGet(localUrl + '/project').success(function(data) {
    populateSelect(element, data, "name");
    var cookieValue = $.cookie("projectId");
    if(cookieValue) {
      element.value = cookieValue;
    }
  });
}

module.exports = {
  deleteLogEntry: deleteLogEntry,
  fetchTotalFriskCount: fetchTotalFriskCount,
  fetchFriskCountPerUser: fetchFriskCountPerUser,
  fetchFriskLog: fetchFriskLog,
  populateSelect: populateSelect,
  fillProjectSelect: fillProjectSelect
};