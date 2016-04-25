<%-- 
    Document   : CSSExamples
    Created on : Aug 18, 2014, 2:43:08 PM
    Author     : fupre1
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSS Examples Page</title>
    </head>
    <body>
       <p style="background-image:url(resources/slideimage/snoc2.png);height:20%; width:20%;background-attachment:repeat fixed;-moz-opacity:1;opacity:1;filter:alpha(opacity=100);">This parapgraph has fixed repeated background image.</p>

    <style type="text/css"> a:hover {color: red}
</style>
<a href="#pageLogins">Bring Mouse Here</a><br>
    <style type="text/css">
        a:active {color: #FF00CC}
</style> <a rel="external" href="Html5.html">Click This Link</a><br>
        <style type="text/css">
table.one
{
    border-collapse:collapse;
    width:400px;
    border-spacing:10px;
 }
table.two
{
    border-collapse:separate;
    width:400px; 
    border-spacing:10px 50px;
}
td.a
{ 
    border-style:dotted;
    border-width:3px;
    border-color:#000000;
    padding: 10px;
 }
td.b
{
    border-style:solid;
    border-width:3px;
    border-color:#333333;
    padding:10px;
}





</style>
<table class="one" border="1">
<caption>Collapse Border Example with border-spacing</caption>
<tr>
<td class="a"> Cell A Collapse Example</td>
</tr>
<tr>
<td class="b"> Cell B Collapse Example</td>
</tr>
</table>
<br />
<table class="two" border="1">
<caption><caption>Separate Border Example with border-spacing</caption></caption>
<tr>
<td class="a"> Cell A Separate Example</td>
</tr>
<tr>
<td class="b"> Cell B Separate Example</td>
</tr>
</table>


<style type="text/css">
caption.top
{
caption-side:top;
} 
caption.bottom
{
 caption-side:bottom
}
caption.lefta
{
caption-side:left
}
caption.righta
{
caption-side:right
}
</style>
<table style="width:400px; border:1px solid black;">
<caption class="top"> This caption will appear at the top </caption>
<tr>
<td > Cell A</td>
</tr>
<tr>
 <td > Cell B</td>
</tr> </table> <br />
<table style="width:400px; border:1px solid black;">
<caption class="bottom"> This caption will appear at the bottom </caption>
<tr>
    <td > Cell A</td>
</tr>
<tr><td > Cell B</td></tr>
</table> <br />
<table style="width:400px; border:1px solid black;">
<caption class="lefta"> This caption will appear at the left </caption>
<tr>
    <td > Cell A</td>
    </tr>
    <tr><td > Cell B</td></tr>
    </table> <br />
    <table style="width:400px; border:1px solid black;">
    <caption class="righta"> This caption will appear at the right </caption>
    <tr><td > Cell A</td></tr>
    <tr><td > Cell B</td></tr>
    </table>

    <style type="text/css">
table.empty
{
    width:350px;
    border-collapse:separate;
    empty-cells:hide;
    }
td.empty
{
    padding:5px;
    border-style:solid;
    border-width:1px;
    border-color:#999999;
}
</style>
    <table class="empty"> 
    <tr> 
    <th></th><th>Title one</th> <th>Title two</th>
    </tr>
    <tr> 
    <th>Row Title</th> <td class="empty">value</td> <td class="empty">value</td>
    </tr>
    <tr> <th>Row Title</th> <td class="empty">value</td> <td class="empty"></td>
    </tr>
    </table>
    <style type="text/css">
table.auto
{
    table-layout: auto
    }
table.fixed
{ table-layout: auto
}
</style>
<table class="auto" border="1" width="100%">
<tr>
<td width="20%">1000000000000000000000000000</td> <td width="40%">10000000</td> <td width="40%">100</td>
</tr>
</table> <br />
<table class="fixed" border="1" width="100%">
<tr> <td width="20%">1000000000000000000000000000</td> <td width="40%">10000000</td> <td width="40%">100</td>
</tr>
</table>

<style type="text/css">
p.example1
{ 
    border:1px solid;
    border-bottom-color:#009900; /* Green */
    border-top-color:#FF0000; /* Red */
    border-left-color:#330000; /* Black */
    border-right-color:#0000CC; /* Blue */
}
p.example2
{ 
    border:1px solid ;
    border-color:#009900; /* Green */
}
p.shorthandborder
{ 
    border:1px dashed #0000CC;/* shorthand in blue color */
    /* Green */
}
</style>
<p class="example1"> This example is showing all borders in different colors. </p>
<p class="example2"> This example is showing all borders in green color only. </p>
<p class="shorthandborder"> This example is showing shorthandborder in blue color only. </p>

<p style="border-width:4px; border-style:none;"> This is a border with none width. </p>
<p style="border-width:4px; border-style:solid;"> This is a solid border. </p>
<p style="border-width:4px; border-style:dashed;"> This is a dahsed border. </p>

<p style="border-width:4px; border-style:double;"> This is a double border. </p>
<p style="border-width:4px; border-style:groove;"> This is a groove border. </p>
<p style="border-width:4px; border-style:ridge"> This is a ridge border. </p>
<p style="border-width:4px; border-style:inset;"> This is a inset border. </p>
<p style="border-width:4px; border-style:outset;"> This is a outset border. </p>
<p style="border-width:4px; border-style:hidden;"> This is a hidden border. </p>
<p style="border-width:4px; border-top-style:solid; border-bottom-style:dashed; border-left-style:groove; border-right-style:double;"> This is a a border with four different styles. </p>

<p style="border-width:4px; border-style:solid;"> This is a solid border whose width is 4px. </p>
<p style="border-width:4pt; border-style:solid;"> This is a solid border whose width is 4pt. </p>
<p style="border-width:thin; border-style:solid;"> This is a solid border whose width is thin. </p>
<p style="border-width:medium; border-style:solid;"> This is a solid border whose width is medium; </p>
<p style="border-width:thick; border-style:solid;"> This is a solid border whose width is thick. </p>
<p style="border-bottom-width:4px; border-top-width:10px; border-left-width: 2px; border-right-width:15px; border-style:solid;"> This is a a border with four different width. </p>


<div data-role="table" style="background-image:url(resources/slideimage/snoc2.png);width:40%;background-position:100px 200px;"> <tr><td> This table has background image positioned 100 pixels away from the left and 200 pixels from the top... </td></tr> </div>
           <p style="font-family:georgia,garamond,serif;"> This text is rendered in either georgia, garamond, or the default serif font depending on which font you have at your system. </p>
           <p style="font-style:italic;"> This text will be rendered in italic style </p>
           
           <p style="font-variant:small-caps;"> This text will be rendered as small caps </p>
           <p style="font-weight:bold;"> This font is bold. </p> <p style="font-weight:bolder;"> This font is bolder. </p> <p style="font-weight:900;"> This font is 900 weight. </p>
           <p style="font-stretch:ultra-expanded;"> If this doesn't appear to work, it is likely that your computer doesn't have a condensed or expanded version of the font being used. </p>
           <p style="font:italic small-caps bold 15px georgia;"> Applying all the properties on the text at once. </p>
    </body>
</html>
