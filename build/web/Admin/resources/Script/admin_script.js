// JavaScript Document


function ValidateAdminSnosRegistration()
{
    var snostype=document.forms["SnosForm"]["snosType"];
    var fone=document.forms['SnosForm']['number'];
    var snosFormat= /^[SNOS]+[0-9]{1,}$/;
    var FoneNumberFormat = /^[234]+[7-8]{1}([0-9]{9})$/;

if(snostype.value == "")
{
window.alert("SNOS Type is Required, all fields with * is compulory");
document.forms['SnosForm']['snosType'].focus();
return false;
}else if(!snostype.value.match(snosFormat)){
  window.alert('Snos Type must have alphabet and numeric characters  only e.g SNOS25');
  document.forms['SnosForm']['snosType'].focus();
  return false;
}else if(fone.value == ""){
window.alert("Phone number is required, all fields with * is compulory");
document.forms['SnosForm']['number'].focus();
return false;
}else if(!fone.value.match(FoneNumberFormat)){
  window.alert('Phone number must have numeric characters only e.g 2348067707555');
  document.forms['SnosForm']['number'].focus();
  return false;
}else{
  return true;
}

}

function ValidateSensorRegistration()
{
var snos_type=document.forms['sensorForm']['snos_type'];
var sensor=document.forms['sensorForm']['sensor'];
//var sensorFormat= /^[a-zA-Z]+[a-zA-Z0-9.-]{6,100}$/;
var sensorFormat= /^([a-zA-Z0-9]{2,30})\)?[ ]?([a-zA-Z0-9.-]{2,50})[. ]?[a-zA-Z0-9.-]{2,50}$/;
if(snos_type.value==0)
{
	window.alert("SNOS Type is not Selected,all fields with* is compulory");
	//document.forms['sensorForm']['snos_t ype'].focus();
	return false;
}else if(sensor.value == ""){
window.alert("Sensor is required, all fields with * is compulory");
document.forms['sensorForm']['sensor'].focus();
return false;
}else if(!sensor.value.match(sensorFormat)){
  window.alert('Sensor name can have alphbets and numeric characters only e.g DOOR Open');
  document.forms['sensorForm']['sensor'].focus();
  return false;
}

	return true;
}



function ValidateAdministrator()
{
var Name2=document.forms["adminForm"]["adminname"];
var userid2 = document.forms["adminForm"]["userid"];
var pass2=document.forms['adminForm']['pass'];
var vpass2=document.forms['adminForm']['vpass'];
var status2=document.forms['adminForm']['status'];

var useridFormat= /^[a-zA-Z]{5,15}$/;
var passwordFormat = /^[a-zA-Z]+[0-9]{2,}$/;
var NameFormat= /^([a-zA-Z]{2,30})\)?[, ]?([a-zA-Z]{2,50})[., ]?[a-zA-Z0-9]{2,50}$/;

if(Name2.value == "")
{
	window.alert("Name is Required all fields with * is compulory");
	document.forms['adminForm']['adminname'].focus();
	return false;
}else if(!Name2.value.match(NameFormat)){
  window.alert('Name must be alphabet characters of 5 surname and firstname only e.g Snos teledom');
  document.forms['adminForm']['adminname'].focus();
  return false;
}else if(userid2.value == ""){
window.alert("user id is required all fields with * is compulory");
document.forms['adminForm']['userid'].focus();
return false;
}else if(!userid2.value.match(useridFormat)){
 window.alert('Userid must have alphabet characters of 5 to 15 only e.g teledom');
 document.forms['adminForm']['userid'].focus();
return false;
}else if(pass2.value == ""){
window.alert("Password is required all fields with * is compulory");
document.forms['adminForm']['pass'].focus();
return false;
}else if(!pass2.value.match(passwordFormat)){
 window.alert('Password should be Alphanumeric characters only e.g teledom04');
 document.forms['adminForm']['pass'].focus();
return false;
}else if(vpass2.value == ""){
window.alert("Verify Password is required all fields with * is compulory");
document.forms['adminForm']['vpass'].focus();
return false;
}else if(!vpass2.value.match(passwordFormat)){
 window.alert(' verify Password should be Alphanumeric characters only and must match password e.g teledom04');
 document.forms['adminForm']['vpass'].focus();
return false;
}else if(status2.value==0){
	window.alert("Status is not Selected,all fields with* is compulory");
	//document.forms['sensorForm']['snos_t ype'].focus();
	return false;
}
return true
}




function ValidateAdminReport()
{
var search=document.forms["searchForm"]["search"];
var snosFormat2= /^([SNOS])+[0-9]{1,}$/i;
//var SearchDateFormat = /^(\d{2})([.\/-])(\d{2})\2(\d{4})$/;
//var SearchDateFormat = /^([a-zA-Z]{3})([])(\d{1})\,(\d{4})$/;


if(search.value == "")
{
	window.alert("Search Information is empty");
	document.forms['searchForm']['search'].focus();
	return false;
}else if(!search.value.match(snosFormat2)){
  window.alert('Snos Type must have alphabet and numeric characters  only e.g SNOS25');
  document.forms['searchForm']['search'].focus();
  return false;
}


return true;
}


function ValidateAdminDateSearch()
{
var search=document.forms["searchDateForm"]["search_date"];
//var SearchDateFormat = /^(\d{2})([.\/-])(\d{2})\2(\d{4})$/;
var SearchDateFormat = /^([a-zA-Z]{3})[, ]?([0-9]{1,2})\,[, ]?[0-9]{4}$/;


if(search.value == "")
{
	window.alert("Search Information is empty");
	document.forms['searchDateForm']['search_date'].focus();
	return false;
}else if(!search.value.match(SearchDateFormat)){
  window.alert('Date must be in date format only e.g mar 15,2013');
  document.forms['searchDateForm']['search_date'].focus();
  return false;
}
return true;
}

