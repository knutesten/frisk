/**
 * Created by simena on 20.02.2015.
 */

var api = require("./api/rest.js");

$(document).ready(function() {
  var localUrl = 'http://localhost:8080/api';
  //var host = window.location.host;
  //var localUrl = 'http://' + host + '/api';

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
  });
  
  var flavourSelect = logForm.elements['friskFlavour'];
  api.httpGet(localUrl + '/flavour').success(function(data) {
    populateSelect(flavourSelect, data, "flavour");
  });
  
  var typeSelect = logForm.elements['friskType'];
  api.httpGet(localUrl + '/consume-type').success(function(data) {
    populateSelect(typeSelect, data, "name");
  });

  var projectSelect = logForm.elements['friskProject'];
  api.httpGet(localUrl + '/project').success(function(data) {
    populateSelect(projectSelect, data, "name");
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
    
    api.httpPost(localUrl + '/log', JSON.stringify(log))
      .success(function(data, code) {
        console.log(code);
        console.log(data);
    }).error(function(data, code) {
        console.log(code);
        console.log(data);
    });
    e.preventDefault();
  });
});