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
<meta http-equiv="Cache-Control" content="No-Cache">
<!-- Le styles -->
<link href="/resources/css/bootstrap.css" rel="stylesheet">
<link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">

<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="<%=request.getContextPath()%>/resources/js/jquery.mockjax.js"></script>

<script type="text/javascript">
var TARGET_URL_PREFIX = "http://localhost:8081/framework/config";

	function registerOpenApi(payload) {
		$.ajax({
		    url: TARGET_URL_PREFIX+"/apis",
		    type:"POST",
		    contentType : 'application/json',
		    data:payload,
		    success: function(json) {
		    	 alert("register success");
		    	 location.href='http://localhost:8081/openApi/getOpenApiList';
		    },error: function(jqXHR, textStatus, errorThrown) {
		    	alert("registerOpenApi error : " + textStatus);
			}
		});  
	}

	function getData() {
	
		var service = $('#service').val();
		var adaptor = $('#adaptor').val();
		var apiId = $('#apiId').val();
		var backendUrl = $('#backendUrl').val();
		var backendMethod = $('input:radio[name=optionsRadios2]:checked').val();
		var requestContentTypes = [];		
		var responseContentTypes = [];		
		var apiMethod = $('input:radio[name=optionsRadios1]:checked').val();
		var apiProtocol = $('#apiProtocol').val();
		var apiUrl1 = $('#apiUrl1').val();
		var apiUrl2 = $('#apiUrl2').val();
		var apiUrl = apiProtocol+"://"+apiUrl1+"/"+apiUrl2;
		var keys = $(".key");
		var mandatorys = $(".mandatory");
		var queryParamArray = [];
		// input example: version={version}&key1={value1}
		var jsonData ={};
		
		$("input:checkbox[name=checkbox1]:checked").each(function(){
			requestContentTypes.push($(this).val());
		});
		
		$("input:checkbox[name=checkbox1]:checked").each(function(){
			responseContentTypes.push($(this).val());
		});
		
		if (keys) {
			if (keys.length == mandatorys.length) {
				var mandatorysValue =true;
				for (var i =0; i<keys.length; i++) {
					if (mandatorys[i].value != "Y"){
						mandatorysValue =false ;
					}
					var queryParam = {name:keys[i].value, mandatory:mandatorysValue};
					queryParamArray.push(queryParam);
				}
			}				
		}
		
		jsonData = {
			"apiEnable" : true,
			"serviceId" : service,
			"adaptorId" : adaptor,
			"apiId" : apiId,
			"apiVersion" : 1,
			"apiUri" :apiUrl,
			"apiStatus" :"1",
			"apiHttpMethod" : apiMethod,
			"backendApiHttpMethod" : backendMethod,
			"backendApiVersion" : 1,
			"backendApiUri" : backendUrl,
			"backendApiStatus" : "1",
			"requestContentTypeList" :requestContentTypes,
			"responseContentTypeList" :responseContentTypes,
			"apiPathParameterList":[],
			"apiQueryParameterList":queryParamArray
		};
		var data = JSON.stringify(jsonData);
		return data;	
	};
		
	$(document).ready(function() {	
		
		$("#apiMethod").click(function(){
			var apiMethod = $('input:radio[name=optionsRadios1]:checked').val();
			$("#methodId").html(apiMethod);
		});
		
		$("#apiUrlTotal").click(function(){
			var apiProtocol = $('#apiProtocol').val();
			var apiUrl1 = $('#apiUrl1').val();
			var apiUrl2 = $('#apiUrl2').val();
			var apiUrl = apiProtocol+"://"+apiUrl1+"/"+apiUrl2;
			$("#apiUrlText").html(apiUrl);
		});
		
		
		$("#createField").click(function(){
		
			var queryString = $('#queryString').val();
			var num = 0;
			var param ="<tr><th>No</th><th>파라미터</th><th>필수여부</th></tr>";
			// input example: version={version}&key1={value1}
			if(queryString != null && queryString != ""){
				
				// ["version={version}","key1={value1}"]
				var splitQueryString = queryString.split("&");
			
				for(var i=0; i<splitQueryString.length; i++){
					
					num =+ i;
					//i=0 splitQueryString[i] "version={version}" keyAndValue ["version","{version}"]	
					var keyAndValue = splitQueryString[i].split("=");
					var key = keyAndValue[0];
				
					param +="<tr><td>"+num+"</td>"+
							"<td><input type='text' readonly='readonly' value='"+key+"' class='key search-query'/>"+
							"</td><td><select class='mandatory'><option>Y</option><option>N</option></select></td></tr>";
					
					$("#queryText").html(param);
				}
			}else{
				alert("queryString를 입력해 주세요!");
			}
		});

		$("#save").click(function(){

			var service = $('#service').val();
			var adaptor = $('#adaptor').val();
			var apiId = $('#apiId').val();
			var backendUrl = $('#backendUrl').val();
			var requestContentTypes = [];		
			var responseContentTypes = [];		
			var apiUrl2 = $('#apiUrl2').val();
			var keys = $(".key");
			var mandatorys = $(".mandatory");
			var queryParamArray = [];
			// input example: version={version}&key1={value1}
			var queryString = $('#queryString').val();
			
			$("input:checkbox[name=checkbox1]:checked").each(function(){
				requestContentTypes.push($(this).val());
			});
			
			$("input:checkbox[name=checkbox2]:checked").each(function(){
				responseContentTypes.push($(this).val());
			});
			
			if (keys) {
				if (keys.length == mandatorys.length) {
					var mandatorysValue =true;
					for (var i =0; i<keys.length; i++) {
						if (mandatorys[i].value != "Y"){
							mandatorysValue =false ;
						}
						var queryParam = {name:keys[i].value, mandatory:mandatorysValue};
						queryParamArray.push(queryParam);
					}
				}				
			}
			
			if(service == "선택해 주세요"){
				alert("서비스를 선택해 주세요!");
			} else if(adaptor == "선택해 주세요") {
				alert("adaptor를 선택해 주세요!");
			} else if(apiId == null || apiId == ''){
				alert("apiId를 입력해 주세요!");
			} else if(apiUrl2 == null|| apiUrl2 == ''){
				alert("apiUrl을 입력해 주세요!");
			} else if(backendUrl == null|| backendUrl == ''){
				alert("backendUrl를 입력해 주세요!");
			} else if(queryString == null|| queryString == ''){
				alert("queryString를 입력해 주세요!");
			} else{
				
			var payload = getData();
			registerOpenApi(payload);
			}
		});
	});
</script>

<title>registerOpenApi</title>
<head> <style type="text/css">
table{
    border:1px solid gray;
    border-collapse: collapse;
    width:100%
}
th{
   background-color: #d0d0d0; 
   font-weight:bold;
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
<a href="http://localhost:8081/openApi/getOpenApiList"><i class="icon-chevron-right"></i> OpenAPI 목록</a></ul>
<ul class="nav nav-tabs nav-stacked">
<a href="http://localhost:8081/openApi/registerOpenApi"><i class="icon-chevron-right"></i><i class="icon-chevron-right"></i> OpenAPI 등록</a></ul>
</div>
<div class="span10">
<br><p><i class="icon-pencil"></i> OpenAPI 등록</p><hr>
<br><p><i class="icon-star"></i> 기본정보 (<abbr title="attribute"><i class="icon-ok"></i> 필수입력 사항입니다.</abbr>)</p><hr>
<table class="table1">
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> 서비스</abbr></th>
	<td>
	<div class="control-group error">
	<label class="control-label" for="inputError"></label>
	<div class="controls">
    	<select class="span3" id="service">
            <option>선택해 주세요</option>
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
        </div></div>
     </td>
</tr>
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> Adaptor</abbr></th>
	<td>
	<div class="control-group error">
	<label class="control-label" for="inputError"></label>
	<div class="controls">
    
    	<select class="span2" id="adaptor">
            <option>선택해 주세요</option>
            <option>mockAssetAdaptor</option>
            <option>commonAdaptor</option>
            <option>appldAdaptor</option></select> 
        </div></div>
     </td>
</tr>    
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> API ID</abbr></th>
    <td>
    <div class="control-group error">
	<label class="control-label" for="inputError"></label>
	<div class="controls">
    
    <input class="input-xxlarge search-query" type="text" id="apiId">
    <span class="help-inline"></span> </div></div>
    </td>
</tr>
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> API URL</abbr></th>
	<td>
	<div class="control-group error">
	<label class="control-label" for="inputError"></label>
	<div class="controls" id="apiUrlTotal">
    
    	<select class="span2" id="apiProtocol">
        <option>http</option>
        <option>https</option></select> 
        ://
        <input class="input-medium search-query" type="text" readonly="readonly" id="apiUrl1" value="apis.skplanetx.com"> /
        <span class="help-inline"></span>
        <input class="input-xlarge search-query" type="text" id="apiUrl2">
        <span class="help-inline"></span>
        </div></div>
     </td>
</tr>
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> Backend URL</abbr></th>
	<td>
	<div class="control-group error">
	<label class="control-label" for="inputError"></label>
	<div class="controls">
        <input class="input-xxlarge search-query" type="text" id="backendUrl">
        <span class="help-inline"></span>
        </div></div>
     </td>
</tr>
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> API Method</abbr></th>
    <td>
        <label class="radio1" id="apiMethod">
        <input type="radio" name="optionsRadios1" value="GET" id="optionsRadios1" checked > GET
        <input type="radio" name="optionsRadios1" value="POST" id="optionsRadios2"> POST
        <input type="radio" name="optionsRadios1" value="PUT" id="optionsRadios3"> PUT
        <input type="radio" name="optionsRadios1" value="DELETE" id="optionsRadios4"> DELETE</label> 
    </td>
</tr>
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> Backend Method</abbr></th>
    <td>
        <label class="radio2" id="backendMethod">
        <input type="radio" name="optionsRadios2" value="GET" id="optionsRadios5" checked> GET
        <input type="radio" name="optionsRadios2" value="POST" id="optionsRadios6" > POST
        <input type="radio" name="optionsRadios2" value="PUT" id="optionsRadios7" > PUT
        <input type="radio" name="optionsRadios2" value="DELETE" id="optionsRadios8" > DELETE</label> 
    </td>
</tr>
</table>

<br><p><i class="icon-star"></i> Request 정보 (<abbr title="attribute"><i class="icon-ok"></i>  필수입력 사항입니다.</abbr>)</p>
<hr>
<table>
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> Method</abbr></th>
    <td id="methodId"></td>
</tr>
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> API URL</abbr></th>
    <td id="apiUrlText"></td>
</tr>
<tr>
    <th><abbr title="attribute"><i class="icon-ok"></i> Query String</abbr></th>
	<td>
	<div class="control-group error">
	<label class="control-label" for="inputError"></label>
	<div class="controls">
        <textarea class="input-xxlarge" rows="3" id="queryString"></textarea>
        <ul class="pager">
        <li id="createField"><a>필드생성</a></li></ul>
        <span class="help-inline"></span>
        </div></div>
     </td>
</tr>
<tr>
    <th>Content-type</th>
    <td>
        <label class="checkbox1" id="contentType1">
        <input type="checkbox" value="None" name="checkbox1" id="checkbox1" checked> None<br>
        <input type="checkbox" value="application/x-www-form-urlencoded" name="checkbox1" id="checkbox2"> application/x-www-form-urlencoded<br>
        <input type="checkbox" value="application/json" name="checkbox1" id="checkbox3"> application/json<br>
        <input type="checkbox" value="application/xml" name="checkbox1" id="checkbox4"> application/xml<br>
        <input type="checkbox" value="application/javascript" name="checkbox1" id="checkbox5"> application/javascript 
        </label> 
    </td>
</tr>

</table>
<br><i class="icon-star"></i> Query String 정보
<table id="queryText" class="table2">
    <tr>
        <th>No</th>
        <th>파라미터</th>
        <th>필수여부</th>
    </tr>
    <tr>
    	<td></td>
    	<td></td>
    	<td></td>
    </tr>
</table>
<br><p><i class="icon-star"></i> Response 정보</p><hr>
<table>
<tr>
    <th>Content-type</th>
    <td>
        <label class="checkbox2" id="contentType2">
        <input type="checkbox" value="None" name="checkbox2" id="checkbox6" checked> None<br>
        <input type="checkbox" value="application/x-www-form-urlencoded" name="checkbox2" id="checkbox7"> application/x-www-form-urlencoded<br>
        <input type="checkbox" value="application/json" name="checkbox2" id="checkbox8"> application/json<br>
        <input type="checkbox" value="application/xml" name="checkbox2" id="checkbox9"> application/xml<br>
        <input type="checkbox" value="application/javascript" name="checkbox2" id="checkbox10"> application/javascript 
        </label>
</td>
</tr>
</table>
<center><br>
<ul class="pager">
<li><a href="http://localhost:8081/openApi/getOpenApiList" id="previous">뒤로</a></li>
<li><a id="save">등록</a></li>
</ul>
</center>
  </div></div></div>
</body>
</html>