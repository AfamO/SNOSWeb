/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
        $(function(){
            alert("Text: "+$("#divText").text("A dynamically set text").toString());
            alert("Html: "+$("#divHtml").html("<b>A <i>dynamically set</i> html String </b>").toString());
            alert("Value: "+$("#txtTest").val("A dynamically set value").toString());

            alert("Text: " + $("#txtTest").text());
            alert("HTML: " + $("#txtTest").html());
            alert("Value: " + $("#txtTest").val())
        });
        */
       
        $(function()
{
        $("p").text(function(index, oldText) {
                return "Existing text: " + oldText + ". New text: A dynamically set text (#" + index + ")";
        });
});
$(function()
{
    alert("The url is "+$("#aGoogle1").attr("href"));
    $("#aGoogle2").attr({
        "href": "http://www.google.co.uk",
        "name":"Google.co.uk",
        "title" :"Click to go to UK Google version"
    });
    $("a.google").attr("href", function(index, oldValue)
        {
                return oldValue + "imghp?tab=wi";
        });
});
function ToggleClass(sender)
{
    if($(sender).hasClass("bold"))
        $(sender).removeClass("bold");
    else
        $(sender).addClass("bold");
}
$(function()
{
    $("#divTestArea1 span,#divTestArea1 i").addClass("blue");
    $("#divTestArea1 div").addClass("bold blue");
});
function AppendItemsToList()
{
        var item1 = $("<li></li>").text("Item 1");
        var item2 = "<li>Item 2</li>";
        var item3 = document.createElement("li");
        item3.innerHTML = "Item 3";
        var item4=document.createElement("li");
        item4.innerHTML='ITEM 4';
        var item5=$("<li></li>").text("ItEm 5");

        $("#olTestList2").append(item1, item2, item3,item4,item5);
}
function PrependItemsToList()
{
        $("#olTestList3").prepend($("<li></li>").text("prepend() item"));
        $("<li></li>").text("prependTo() item").prependTo("#olTestList3");
}
function InsertElements()
{
        var element1 = $("<b></b>").text("Hello ");
        var element2 = "<i>there </i>";
        var element3 = document.createElement("u");
        element3.innerHTML = "jQuery!";

        $("#spnTest2").before(element1, element2, element3);
}
/*
$(function()
{
        //$("a, span").bind("mouseover", function() {
                //alert('Hello, world!');

        });
});
*/
$(function()
{
        $("a").bind("mouseout", function() {
                alert($(this).text());
        });
});
$("#divArea").bind("mousemove", function(event)
{
        $(this).text("The X-axis is:"+event.pageX + ",While the Y-axis is:" + event.pageY);
});



