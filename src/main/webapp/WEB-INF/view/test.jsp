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

var TARGET_URL_PREFIX = "http://localhost:8080/framework/config";

$(document).ready(function() {
/*
	$.mockjax({
		  url:/^http:\/\/framework\-interface\/apis\/([A-Za-z0-9]+)$/i,
		  urlParams: ['openApiId'],
		  status: 200,
		  responseTime: 750,
		  response: function (settings) {
			console.log(settings);
		    var openApiId = settings.urlParams.openApiId;
		    if (settings.type === "GET") {
			    this.responseText = 
           	        { 
        	        	apiEnabled : true,
        	        	serviceId : "SV00001",
        	        	adaptorId : "AD00001",
        	        	apiId : openApiId,
        	        	apiVersion : 1,
        	        	apiUri :"/api/test",
        	        	apiStatus :"1",
        	        	apiProtocol : "http",
        	        	apiUriRegExp : "/api/test",
        	        	requestMethod : "get",
        	        	transferMethod : "get",
        	        	backendApiVersion : 1,
        	        	backendUri : "/backend/test",
        	        	backendStatus : "1"
        	        };
		    } else if (settings.type === "PUT") {
		    	alert(settings.data);
			    this.responseText = { openApiId: openApiId };
		    } else if (settings.type === "DELETE") {
			    this.responseText = { openApiId: openApiId };
		    } else {
		    	this.status = 405;
		    	this.responseText = { error:"error"};

		    }

		  }
		});
	
	$.mockjax({
		url: "http://framework-interface/apis",
	    responseTime: 750,
	    contentType: 'text/json',
	    response: function (settings) {
    		if (settings.type === "GET") {
		    	this.responseText = [
		    	{ 
		        	apiEnabled : true,
		        	serviceId : "SV00001",
		        	adaptorId : "AD00001",
		        	apiId : "ID00001",
		        	apiVersion : 1,
		        	apiUri :"/api/test",
		        	apiStatus :"1",
		        	apiProtocol : "http",
		        	apiUriRegExp : "/api/test",
		        	requestMethod : "get",
		        	transferMethod : "get",
		        	backendApiVersion : 1,
		        	backendUri : "/backend/test",
		        	backendStatus : "1"
		        },
		        { 
		        	apiEnabled : true,
		        	serviceId : "SV00002",
		        	adaptorId : "AD00002",
		        	apiId : "ID00002",
		        	apiVersion : 1,
		        	apiUri :"/api/test2",
		        	apiStatus :"2",
		        	apiProtocol : "http",
		        	apiUriRegExp : "/api/test2",
		        	requestMethod : "get",
		        	transferMethod : "get",
		        	backendApiVersion : 1,
		        	backendUri : "/backend/test2",
		        	backendStatus : "2"
		        }
		    	];
			} else if (settings.type === "POST") {
				this.status = 200;
				this.responseText = { openApiId: "AAA" };
			} else {
		    	this.status = 405; //not allowed method
		    	this.responseText = { error:"error"};
			}
	    }
	});

	*/
	$("#getOpenApiDetail").click(
			function() {
				getDetailOpenApi($("#openApiId").val());
			}
		);
	$("#deleteOpenApi").click(function() {
		deleteOpenApi($("#openApiId").val());
		});
	$("#updateOpenApi").click(function() {
		updateOpenApi($("#openApiId").val(), $("#inputPayload").val());
		});
	
	$("#registerOpenApi").click(function() {
		registerOpenApi( $("#inputPayload").val());
	});

	$("#getOpenApiList").click(function() {
		getOpenApiList();
	});


});

function getDetailOpenApi(openApiId) {
	
	$.ajax({
	    url: TARGET_URL_PREFIX+"/apis/"+openApiId,
	    type:"GET",
	    dataType: "json",
	    success: function(json) {
	    	//Json Object to String
		    var string = JSON.stringify(json);
	    	//String to Json Object
	    	// JSON.parse()
		    $("#outputPayload").val(string);
	    },error: function(jqXHR, textStatus, errorThrown) {
	    	 $("#outputPayload").val("getDetailOpenApi error : " + textStatus);
		}
	});  
}

function deleteOpenApi(openApiId) {
	
	$.ajax({
	    url: TARGET_URL_PREFIX+"/apis/"+openApiId,
	    type:"DELETE",
	    success: function(json) {
		    $("#outputPayload").val("delete success");
	    },error: function(jqXHR, textStatus, errorThrown) {
	    	 $("#outputPayload").val("deleteOpenApi error : " + textStatus);
		}
	});  
}


function updateOpenApi(openApiId, payload) {
	
	$.ajax({
	    url: TARGET_URL_PREFIX+"/apis/"+openApiId,
	    type:"PUT",
	    contentType : 'application/json',
	    data:payload,
	    success: function(json) {
		    $("#outputPayload").val("update success");
	    },error: function(jqXHR, textStatus, errorThrown) {
	    	 $("#outputPayload").val("updateOpenApi error : " + textStatus);
		}
	});  
}

function registerOpenApi(payload) {
	$.ajax({
	    url: TARGET_URL_PREFIX+"/apis",
	    type:"POST",
	    contentType : 'application/json',
	    data:payload,
	    success: function(json) {
	    	 $("#outputPayload").val("register success");
	    },error: function(jqXHR, textStatus, errorThrown) {
	    	 $("#outputPayload").val("registerOpenApi error : " + textStatus);
		}
	});  
}

function getOpenApiList() {
	
	$.ajax({
	    url: TARGET_URL_PREFIX+"/apis",
	    type:"GET",
	    dataType: "json",
	    success: function(json) {
		    var string = JSON.stringify(json);
		    $("#outputPayload").val(string);
	    },error: function(jqXHR, textStatus, errorThrown) {
	    	 $("#outputPayload").val("getOpenApiList error : " + textStatus);
		}
	});  
}


</script>


<title>Test</title>
</head>
<body>


<table>
	<tr>
		<td>
			http://framework-interface/apis/<input type="text" id="openApiId" maxlength="15" style="width:100px">
		</td>
		<td>
			<button id="getOpenApiDetail">getOpenApiDetail</button>
			<button id="updateOpenApi">updateOpenApi</button>
			<button id="deleteOpenApi">deleteOpenApi</button>
		</td>
	<tr>
		<td>
			http://framework-interface/apis
		</td>
		<td>
			<button id="getOpenApiList">getOpenApiList</button><button id="registerOpenApi">registerOpenApi</button>
		</td>
	</tr>
	<tr>
		<td>
		<textarea id="inputPayload" cols="50" rows="10" style="width:400px"  >
{
	"apiEnable" : true,
	"serviceId" : "SV0ddda00011",
	"adaptorId" : "AD000101",
	"apiId" : "ID00001",
	"apiVersion" : 1,
	"apiUri" :"http://localhost:8081/api/test",
	"apiStatus" :"1",
	"apiHttpMethod" : "get",
	"backendApiHttpMethod" : "get",
	"backendApiVersion" : 1,
	"backendApiUri" : "/backend/test/{a}/{b}",
	"backendApiStatus" : "1",
	"requestContentTypeList" :[],
	"responseContentTypeList" :[],
	"apiPathParameterList":[
		{
			"name":"a"
		},
		{
			"name":"b"
		}
	],
	"apiQueryParameterList":[
		{
			"name":"a",
			mandatory:true,
		},
		{
			"name":"b",
			mandatory:true,
		}
	]
}</textarea>
		</td>
		<td>
		<textarea id="outputPayload" readonly="readonly" cols="50" rows="10" style="width:400px">OutputPayload: </textarea>
		</td>
	</tr>
</table>
</body>
</html>



