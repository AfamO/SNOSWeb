// JavaScript Document
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

function TextRoll2(){
	 
// Scrollers width here (in pixels)
var scrollerwidth="176px"
 
// Scrollers height here
var scrollerheight="120px"
 
// Scrollers speed here (larger is faster 1-10)
var scrollerspeed=1
 
 
 
// Scrollers content goes here! Keep all of the message on the same line!
 
 
var scroller_Content1='<font face="Arial" color="blue" size="1"><P>The auto attendant SNOS <br> Is to be installed at the customers Premises<br><br>rttttttttttttyrrrrrr<br><br>sdddddddddddsddddddd<br><br>The auto attendant SNOS to be installed at the customers Premises<br><br>wwwwwwwwwwwwwwwwwww<br><br>fgggdhdhhdjdjjdjdj<br><br>dsfsgsggshgshshshshsh<br><br> dfdgdgdgdgdgdgdgdgd.<br>';
 
var pauseit=1
 
 
// Change nothing below!
 
scrollerspeed=(document.all)? scrollerspeed : Math.max(1, scrollerspeed-1) //slow speed down by 1 for NS
var copyspeed=scrollerspeed
var iedom=document.all||document.getElementById
var actualheight=''
var cross_scroller, ns_scroller
var pausespeed=(pauseit==0)? copyspeed: 0
 
function populate(){
if (iedom){
cross_scroller=document.getElementById? document.getElementById("iescroller") : document.all.iescroller
cross_scroller.style.top=parseInt(scrollerheight)+8+"px"
cross_scroller.innerHTML=scroller_Content1
actualheight=cross_scroller.offsetHeight
}
else if (document.layers){
ns_scroller=document.ns_scroller.document.ns_scroller2
ns_scroller.top=parseInt(scrollerheight)+8
ns_scroller.document.write(scroller_Content1)
ns_scroller.document.close()
actualheight=ns_scroller.document.height
}
lefttime=setInterval("scrollscroller()",20)
}
window.onload=populate
 
function scrollscroller(){
 
if (iedom){
if (parseInt(cross_scroller.style.top)>(actualheight*(-1)+8))
cross_scroller.style.top=parseInt(cross_scroller.style.top)-copyspeed+"px"
else
cross_scroller.style.top=parseInt(scrollerheight)+8+"px"
}
else if (document.layers){
if (ns_scroller.top>(actualheight*(-1)+8))
ns_scroller.top-=copyspeed
else
ns_scroller.top=parseInt(scrollerheight)+8
}
}
 
if (iedom||document.layers){
with (document){
if (iedom){
write('<div style="position:relative;width:'+scrollerwidth+';height:'+scrollerheight+';overflow:hidden" onMouseover="copyspeed=pausespeed" onMouseout="copyspeed=scrollerspeed">')
write('<div id="iescroller" style="position:absolute;left:5px;top:0px;width:100%;">')
write('</div></div>')
}
else if (document.layers){
write('<ilayer width='+scrollerwidth+' height='+scrollerheight+' name="ns_scroller">')
write('<layer name="ns_scroller2" width='+scrollerwidth+' height='+scrollerheight+' left=0 top=0 onMouseover="copyspeed=pausespeed" onMouseout="copyspeed=scrollerspeed"></layer>')
write('</ilayer>')
}
}
}
 
 

} //end of textrool function