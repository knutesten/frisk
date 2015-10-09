/**
 * Created by simena on 20.02.2015.
 */

var api = require("./rest/rest.js");
var moment = require("moment");
var Chartist = require("chartist");
var stock = require("./stock/stock.js");

require("../style.less");
require("jquery");
require("jquery-cookie");
require("bootstrap");
require("sammy");
var reg = require("./registration/registration.js");

//window.localUrl = 'http://localhost:8080/api';
window.host = window.location.host;
window.localUrl = 'http://' + host + '/api';


$(document).ready(function() {
  stock.getStock(localUrl + "/stock", "FUNCOM.OL", "stock1");
  stock.getStock(localUrl + "/stock", "GIG.OL", "stock2");
  reg.fetchFriskLog();
  //
  reg.fetchTotalFriskCount();

  var friskCountSelect = document.getElementById('projectFriskCountSelect');
  reg.fillProjectSelect(friskCountSelect);

  var logForm = document.forms['logForm'];
  var userSelect = logForm.elements['friskUser'];
  api.httpGet(localUrl + '/user')
    .success(function(data) {
      reg.populateSelect(userSelect, data, "username");
      var cookieValue = $.cookie("userId");
      if(cookieValue) {
        userSelect.value = cookieValue;
      }
    });

  var flavourSelect = logForm.elements['friskFlavour'];
  api.httpGet(localUrl + '/flavour').success(function(data) {
    reg.populateSelect(flavourSelect, data, "flavour");
    var cookieValue = $.cookie("flavourId");
    if(cookieValue) {
      flavourSelect.value = cookieValue;
    }
  });

  var typeSelect = logForm.elements['friskType'];
  api.httpGet(localUrl + '/consume-type').success(function(data) {
    reg.populateSelect(typeSelect, data, "name");
    var cookieValue = $.cookie("consumeTypeId");
    if(cookieValue) {
      typeSelect.value = cookieValue;
    }
  });

  var projectSelect = logForm.elements['friskProject'];
  reg.fillProjectSelect(projectSelect);

  $("#submitLog").click(function() {
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

    console.log(log.projectId);
    api.httpPost(localUrl + '/log', JSON.stringify(log))
      .success(function() {
        reg.fetchFriskLog();
        document.getElementById('msg').innerHTML="Success";
        logForm.reset();
    }).error(function() {
        document.getElementById('msg').innerHTML="Error";
    });
  });

  reg.fetchFriskCountPerUser();
  
});