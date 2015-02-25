/**
 * Created by simena on 20.02.2015.
 */

var api = require("./api/rest.js");

$(document).ready(function() {
  //var localUrl = 'http://localhost:8080/api';
  var host = window.location.host;
  var localUrl = 'http://' + host + '/api';
  
  var fetchFriskLog = function() {
    var table = document.getElementById('friskTableBody');
    $("#friskTableBody > tr").remove();
    api.httpGet(localUrl + '/log')
      .success(function(data) {
        fillTableWithLogData(data);
      });

    function fillTableWithLogData(data) {
      var row;
      for(var i = 0; i < data.length; i++) {
        row = table.insertRow(i);
        row.insertCell(0).innerHTML = data[i][0];
        row.insertCell(1).innerHTML = data[i][1];
        row.insertCell(2).innerHTML = data[i][2];
        row.insertCell(3).innerHTML = data[i][3];
        row.insertCell(4).innerHTML = data[i][4];
        row.insertCell(5).innerHTML = Date(data[i][5]);
      }
    }
  };

  fetchFriskLog();
  
  var populateSelect = function(element, list, prop) {
    var opt;
    for(var i = 0; i < list.length; i++) {
      opt = document.createElement("option");
      opt.value = list[i].id;
      opt.innerHTML = list[i][prop];

      element.appendChild(opt);
    }
  };
  
  var logForm = document.forms['logForm'];
  var userSelect = logForm.elements['friskUser'];
  api.httpGet(localUrl + '/user').success(function(data) {
    populateSelect(userSelect, data, "username");
    var cookieValue = $.cookie("userId");
    if(cookieValue) {
      userSelect.value = cookieValue;
    }
  });
  
  var flavourSelect = logForm.elements['friskFlavour'];
  api.httpGet(localUrl + '/flavour').success(function(data) {
    populateSelect(flavourSelect, data, "flavour");
    var cookieValue = $.cookie("flavourId");
    if(cookieValue) {
      flavourSelect.value = cookieValue;
    }
  });
  
  var typeSelect = logForm.elements['friskType'];
  api.httpGet(localUrl + '/consume-type').success(function(data) {
    populateSelect(typeSelect, data, "name");
    var cookieValue = $.cookie("consumeTypeId");
    if(cookieValue) {
      typeSelect.value = cookieValue;
    }
  });

  var projectSelect = logForm.elements['friskProject'];
  api.httpGet(localUrl + '/project').success(function(data) {
    populateSelect(projectSelect, data, "name");
    var cookieValue = $.cookie("projectId");
    if(cookieValue) {
      projectSelect.value = cookieValue;
    }
  });
  
  $("#logForm").submit(function(e) {
    var log = {
      id: null,
      date: new Date().getTime(),
      userId: parseInt(logForm.elements['friskUser'].value),
      flavourId: parseInt(logForm.elements['friskFlavour'].value),
      consumeTypeId: parseInt(logForm.elements['friskType'].value),
      projectId: parseInt(logForm.elements['friskProject'].value)
    };

    $.cookie("userId", log.userId);
    $.cookie("flavourId", log.flavourId);
    $.cookie("consumeTypeId", log.consumeTypeId);
    $.cookie("projectId", log.projectId);


    api.httpPost(localUrl + '/log', JSON.stringify(log))
      .success(function(data, code) {
        fetchFriskLog();
        document.getElementById('msg').innerHTML+="Success";
        logForm.reset();
    }).error(function(data, code) {
        document.getElementById('msg').innerHTML+="Error";
    });
    e.preventDefault();
  });
  
  
  
  
});