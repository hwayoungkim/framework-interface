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

	$(document).ready(function() {	
		
		//controller준 apiId를 받아서 세팅한다.
		var openApiId = "${apiId}";
		$.ajax({
		    url: TARGET_URL_PREFIX+"/apis/"+openApiId,
		    type:"GET",
		    dataType: "json",
		    success: function(json) {
			    
		    	var ret = json.api;
				
				$("#apiId").html(ret.apiId);
				$("#apiVersion").html(ret.apiVersion);
				$("#serviceId").html(ret.serviceId);
				$("#adaptorId").html(ret.adaptorId);
				$("#apiUri").html(ret.apiUri);
				$("#apiMethod").html(ret.apiHttpMethod);
				$("#backendMethod").html(ret.backendApiHttpMethod);
				$("#contentType1").html(ret.requestContentTypeList);
				$("#contentType2").html(ret.responseContentTypeList);
				
				var queryTextParam ="<tr><th>No</th><th>파라미터</th><th>필수여부</th></tr>";
				var list = ret.apiQueryParameterList;
				
				for(var i=0; i<list.length; i++){
					num =+ i;
					
				 	queryTextParam +="<tr><td>"+num+"</td>"+
					"<td>"+list[i].name+"</td>";
					
					if(list[i].mandatory == true){
					queryTextParam += "<td>Y</td>";
					}else{
					queryTextParam += "<td>N</td>";
					}
				}
				$("#queryText").html(queryTextParam);
				
				
			    
		    },error: function(jqXHR, textStatus, errorThrown) {
		    	 alert("getDetailOpenApi error : " + textStatus);
			}
		}); 
		
		
		$("#modify").click(function(){
			location.href='http://localhost:8081/openApi/modifyOpenApi?apiId='+$("#apiId").text();
		});
		
		$("#delete").click(function(){
			if(confirm("정말 삭제 하시겠습니까?") == true){
				$.ajax({
				    url: TARGET_URL_PREFIX+"/apis/"+openApiId,
				    type:"DELETE",
				    success: function(json) {
					    alert("delete success");
					    location.href='http://localhost:8081/openApi/getOpenApiList';
				    },error: function(jqXHR, textStatus, errorThrown) {
				    	 alert("deleteOpenApi error : " + textStatus);
					}
				});  
			
			}else{
				return;
			}
		});
	});
	
</script>

<title>getOpenApiDatail</title>
<head> <style type="text/css">
table{
    border:1px solid gray;
    border-collapse: collapse;
    width:100%
}th{
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
<a href="http://localhost:8081/openApi/registerOpenApi"><i class="icon-chevron-right"></i> OpenAPI 등록</a></ul>
</div>
<div class="span10">
<br><p><i class="icon-list-alt"></i> OpenAPI 상세정보</p><hr>
<br><i class="icon-star"></i> 기본정보<hr>
<table class="table1">
<tr>
	<th>API ID</th>
	<td id="apiId"></td>
	<th>API 버전</th>
	<td id="apiVersion"></td>
</tr>
<tr>
	<th>서비스</th>	
	<td id="serviceId"></td>
	<th>Adaptor</th>
	<td colspan="2" id="adaptorId"></td>
</tr>
<tr>
	<th>API URL</th>
	<td colspan="3" id="apiUri"></td>
</tr>
<tr>
	<th>API Method</th>
	<td colspan="3" id="apiMethod"></td>
</tr>
<tr>
	<th>Backend Method</th>
	<td colspan="3" id="backendMethod"></td>
</tr>
</table>
    
<br><i class="icon-star"></i> Request 정보<hr>
<table>
<tr>
    <th>Content-type</th>
    <td colspan="2" id="contentType1"></td>
</tr>
</table>
<br><i class="icon-star"></i> Query String 정보<hr>
<table id="queryText">
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
<br><i class="icon-star"></i> Response 정보<hr>
<table>
<tr>
    <th>Content-type</th>
    <td colspan="2" id="contentType2"></td>
</tr>
</table>
<br><div><center>
<ul class="pager">
<li><a id="modify" >수정</a></li>
<li><a id="delete">삭제</a></li>
<li><a id="openApiList" href="http://localhost:8081/openApi/getOpenApiList">목록</a></li>
</ul>
</center></div><br>
</div></div></div>
</body>
</html>