<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<!-- Le styles -->
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.mockjax.js"></script>

<script type="text/javascript">
var TARGET_URL_PREFIX = "http://localhost:8081/framework/config";

$(document).ready(function() {	
	getOpenApiList();
	
	function getOpenApiList() {	
		
		var searchSelect = $('#searchSelect').val();
		var searchText = $('#searchText').val();
		var serviceSelect = $('#serviceSelect').val();
		var method = $('input:radio[name=optionsRadios]:checked').val();
		var protocol = $('input:radio[name=optionsRadios1]:checked').val();
		
		var queryString = "searchGubun="+searchSelect+
						  "&searchText="+searchText+
						  "&service="+serviceSelect+
						  "&method="+method+
						  "&protocol="+protocol;
		
		$.ajax({	    
			url: TARGET_URL_PREFIX+"/apis?"+queryString,	    
			type:"GET",	    
			dataType: "json",	    
			success: function(json) {	

				var ret = json.list;
				var data="<tr><th>API ID</th><th>서비스</th><th>API URI</th></tr>";
				var count="";
				
				for(var i=0; i<ret.length; i++){
					data += "<tr><td><a href='http://localhost:8081/openApi/getOpenApiDetail?apiId="+ret[i].apiId.trim()+"'>"
							+ret[i].apiId+"</a></td>";
					data += "<td>"+ret[i].serviceId+"</td>";
					data += "<td>"+ret[i].apiUri+"</td></tr>";
					count =+ i;
				}
				
				count +=1;
				$("#apiList").html(data);
				$("#count").val(count);

			}	
		}); 
	}
});
	
</script>


<title>getOpenApiList</title>
<head> <style type="text/css">

table{
    border:1px solid gray;
    border-collapse: collapse;
    width:100%
}
th{
   background-color: #d0d0d0; 
}

td,th{
    font-weight:bold;
    border:1px solid gray;
    padding:5px;
}
</style> </head>

<body>
<div class="container-fluid">
<div class="row-fluid">
<div class="span2">
<br><br><br>
<ul class="nav nav-tabs nav-stacked">
<a href="http://localhost:8081/openApi/getOpenApiList"><i class="icon-chevron-right"></i><i class="icon-chevron-right"></i> OpenAPI 목록</a></ul>
<ul class="nav nav-tabs nav-stacked">
<a href="http://localhost:8081/openApi/registerOpenApi"><i class="icon-chevron-right"></i> OpenAPI 등록</a></ul>
</div>
<div class="span10">
<br><p><i class="icon-search"></i> OpenAPI 목록</p><hr>
<table class="table1">
<tr>
    <th>검색구분</th>
	<td colspan="3">
	<div class="control-group error">
	<label class="control-label" for="inputError"></label>
	<div class="controls">
    
    	<select class="span2" id="searchSelect">
            <option>전체</option>
            <option>API ID</option>
            <option>API URL</option>
            </select> 
            
            <input class="input-xlarge search-query" type="text" id="searchText">
            <span class="help-inline"></span> </div></div>
     </td>
</tr>
                  
<tr>
    <th>서비스</th>
    <td colspan="3">
    <div class="control-group error">
	<label class="control-label" for="inputError"></label>
	<div class="controls">
	
   		<select class="span3" id="serviceSelect">
            <option>전체</option>
            <option>OpenPlatform</option>
            <option>Cyworld</option>
            <option>NateOn</option>
            <option>Nate</option>
            <option>11st</option>
            <option>MelOn</option>
            <option>Smart Touch</option>
            <option>Social Component</option>
            <option>Communication Component</option>
            <option>Activity Component</option>
            <option>user</option>
            <option>Tcloud</option>
            <option>tmap</option>
            <option>Smart Touch Tag</option>
            <option>hoppin</option>
        	<option>Comment</option></select>
        
        	<span class="help-inline"></span> </div></div>
    </td>
 </tr>
<tr>
    <th>메소드 구분</th>
    <td>
        <label class="radio2" id="method">
            <input type="radio" name="optionsRadios" value="전체" id="optionsRadios1" checked/> 전체
            <input type="radio" name="optionsRadios " value="GET" id="optionsRadios2" /> GET 
            <input type="radio" name="optionsRadios" value="POST" id="optionsRadios3" /> POST  
            <input type="radio" name="optionsRadios " value="PUT" id="optionsRadios4" /> PUT 
            <input type="radio" name="optionsRadios" value="DELETE" id="optionsRadios5" /> DELETE </label> 
    </td>   
    <th>프로토콜</th>
    <td>
        <label class="radio4" id="protocol">
        <input type="radio" name="optionsRadios1" value="전체" id="optionsRadios1" checked> 전체
        <input type="radio" name="optionsRadios1" value="http" id="optionsRadios2"> http
        <input type="radio" name="optionsRadios1" value="https" id="optionsRadios3"> https </label> 
    </td>
</tr>         
</table><br>
<center><ul class="pager">
<li><a href="#">조회</a></li>
</ul></center>
<br><i class="icon-star-empty"></i> 총 <input class="span1 search-query" type="text" id="count">건   
<table id="apiList" class="table2">
<tr>
    <th>API ID</th>
    <th>서비스</th>
    <th>API URI</th>
</tr>
<tr>
    <td></td>
    <td></td>
    <td></td>
</tr>
</table>
<br>
<ul class="pager">
<li><a href="http://localhost:8081/openApi/registerOpenApi">등록</a></li>
</ul>
</div></div></div>
</body>
</html>