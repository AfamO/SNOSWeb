// JavaScript Document
/*
 * This displays the current time at the home page
 */
function TeleTime(){
	
var currentTime = new Date()

var hours = currentTime.getHours()
var minutes = currentTime.getMinutes()
	
if(minutes<10){
minutes = "0"+ minutes
}
if(hours<10){
hours = "0" + hours
}
if(hours>12){
var n = "PM"
}else{
var n = "AM"
}

document.write("  Time:"+ hours + ":" + minutes + " " + n);
	
}



$(document).ready(function()
{
$(".account").click(function()
{
var X=$(this).attr('id');

if(X==1)
{
$(".submenu").hide();
$(this).attr('id', '0');
}
else
{

$(".submenu").show();
$(this).attr('id', '1');
}

});

//Mouseup textarea false
$(".submenu").mouseup(function()
{
return false
});
$(".account").mouseup(function()
{
return false
});


//Textarea without editing.
$(document).mouseup(function()
{
$(".submenu").hide();
$(".account").attr('id', '');
});

});

