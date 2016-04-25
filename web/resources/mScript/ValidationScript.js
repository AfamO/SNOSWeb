/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function ClientFormValidation(){

    var checksurname=document.forms["registerform1"]["Sname"];
    var checkOther=document.forms["registerform1"]["Oname"];
    var checkgsm=document.forms["registerform1"]["gsm"];
    var checkemail=document.forms["registerform1"]["email1"];
    var checkaddress=document.forms["registerform1"]["address"];
    var checkarea=document.forms["registerform1"]["Area"];
    var checksnos=document.forms["registerform1"]["snos"];
    var checkstate=document.forms["registerform1"]["state"];
    var checkcontact=document.forms["registerform1"]["Contact_num"];
    var checklga=document.forms["registerform1"]["lga"];
    var checkpass=document.forms["registerform1"]["pass"];
    var checkvpass=document.forms["registerform1"]["vpass"];

    var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    var foneFormat  =  /^[234]+[7-8]{1}([0-9]{9})$/;
    var NameFormat= /^[a-zA-Z]{5,35}$/;
    var AdressFormat = /^([a-zA-Z0-9]{5,200})\)?[, ]?([a-zA-Z]{2,200})[., ]?[a-zA-Z]{2,200}$/;
        //^[0-9a-zA-Z]+[.a-zA-Z]{5,200}$/;
   // var passwordFormat = /^[a-zA-Z]+[0-9]{2,}$/;
    var OtherNameFormat= /^([a-zA-Z]{2,30})\)?[, ]?([a-zA-Z]{2,50})[., ]?[a-zA-Z]{2,50}$/;
    //var snosFormat= /^[SNOS]+[0-9]{1,}$/;
    var StateFormat= /^([a-zA-Z]{4,30})?[. ]?[a-zA-Z]{4,25}$/;

    if(checksurname.value==""){
        window.alert("Surname is Required, all fields with * is compulsory");
        checksurname.focus();
        return false;
    }
else if(!checksurname.value.match(NameFormat)){
  window.alert('Surname must be alphabet of 5-35 characters  only e.g  teledom');
    checksurname.focus();
  return false;
    }else if(checkOther.value==""){
       window.alert("Othername is Required, all fields with * is compulsory");
        checkOther.focus();
        return false;
  }else if(!checkOther.value.match(OtherNameFormat)){
  window.alert('Other must be alphabet of at least 5 characters  firstname and middlename only e.g emeka teledom');
     checkOther.focus();
  return false;
}else if(checkgsm.value==""){
       window.alert("Phone Number is Required, all fields with * is compulsory");
        checkgsm.focus();
        return false;
}else if(!checkgsm.value.match(foneFormat)){
  window.alert("You have entered an invalid phone number!");
     checkgsm.focus();
  return false;
}else if(checkemail.value==""){
    window.alert("Email address is Required, all fields with * is compulsory");
        checkemail.focus();
        return false;
}else if(!checkemail.value.match(mailformat)){
  window.alert("You have entered an invalid email address!");
    checkemail.focus();
  return false;
}else if(trimtextarea(checkaddress.value)=='' ){
     window.alert("Address is Required, all fields with * is compulsory");
        checkaddress.focus();
        return false;
}else if(!checkaddress.value.match(AdressFormat)){
  window.alert("You have entered an invalid Address!");
     checkaddress.focus();
  return false;
}else if(trimtextarea(checkarea.value)=="" ){
    window.alert("Area is Required, all fields with * is compulsory");
    checkarea.focus();
    return false;
}else if(!checkarea.value.match(AdressFormat)){
  window.alert("You have entered an invalid Area!");
     checkarea.focus();
  return false;
}else if(checkstate.value==""){
    window.alert("State is Required, all fields with * is compulsory");
   checkstate.focus();
   return false;
    }else if(!checkstate.value.match(StateFormat)){
   window.alert('State must be alphabet of 4-30 characters  only e.g  kano');
     checkstate.focus();
  return false;
   }else if(checkcontact.value==""){
    window.alert("Contact Person is Required, all fields with * is compulsory");
   checkcontact.focus();
   return false;
    }else if(checklga.value==""){
    window.alert("LGA is Required, all fields with * is compulsory");
   checklga.focus();
   return false;
    }else if(!checklga.value.match(StateFormat)){
   window.alert('LGA must be alphabet of 4-30 characters  only e.g  kano North');
     checklga.focus();
  return false;
   }else if(checkpass.value==""){
    window.alert("Password is Required, all fields with * is compulsory");
    checkpass.focus();
     return false;
}else if(checkvpass.value==""){

    window.alert("Verify Password is Required, all fields with * is compulsory");
    checkvpass.focus();
     return false;
 }else if(checksnos.value==0){
   window.alert("snos id not selected");
   document.forms["registerform1"]["snos"].focus();
   return false;
}
return true;
}

function trimtextarea( str ) {
    return str.replace( /^\s+|\s+$/g, '' );
}