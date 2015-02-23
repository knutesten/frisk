/**
 * Created by simena on 20.02.2015.
 */
$(document).ready(function() {

  var localUrl = 'http://localhost:8080/api';
  
  console.log(window.location.host);

 var get = function httpGet(theUrl) {
    var jsonHTTP = new XMLHttpRequest();
    jsonHTTP.open( "GET", theUrl, false );
    jsonHTTP.send( null );
    return JSON.parse(jsonHTTP.responseText);
  }

  var populateSelect = function(element, list, prop) {
    var opt;
    for(var i = 0; i < list.length; i++) {
      opt = document.createElement("option");
      opt.value = list[i].id;
      opt.innerHTML = list[i][prop];

      element.appendChild(opt);
    }
  }

  var userSelect = document.getElementById('friskUser');
  var users = get(localUrl + '/user');
  populateSelect(userSelect, users, "username");

  //Populates the select-box for "Type"
  var flavourSelect = document.getElementById('friskFlavour');
  var flavours = get(localUrl + '/flavour');
  populateSelect(flavourSelect, flavours, "flavour");

  var typeSelect = document.getElementById('friskType');
  var types = get(localUrl + '/consume-type');
  populateSelect(typeSelect, types, "name");

  var projectSelect = document.getElementById('friskProject');
  var projects = get(localUrl + '/project');
  populateSelect(projectSelect, projects, "name");

});