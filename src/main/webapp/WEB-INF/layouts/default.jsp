<!DOCTYPE html>
<html lang="en" class="vocabbi_document" >
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/views/include.jsp" %>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Vestibulum id ligula porta felis euismod semper.</title>

	<%-- JQuery --%>
	<spring:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" 
			var="jquery_js_url"/>
	<script type="text/javascript" src="${jquery_js_url}" ></script>
			
	<%-- Twitter bootstrap --%>
	<spring:url value="http://twitter.github.com/bootstrap/1.4.0/bootstrap-dropdown.js" 
			var="bootstrap_js_dropdown"/>
	<spring:url value="http://twitter.github.com/bootstrap/1.4.0/bootstrap-alerts.js" 
			var="bootstrap_js_alerts"/>
	<spring:url value="http://twitter.github.com/bootstrap/1.4.0/bootstrap-modal.js" 
			var="bootstrap_js_modal"/>
	<spring:url value="/js/jquery/jquery.tablesorter.min.js" 
			var="jquery_js_tablesorter"/>
	<script type="text/javascript" src="${bootstrap_js_dropdown}" ></script>
	<script type="text/javascript" src="${bootstrap_js_alerts}" ></script>
	<script type="text/javascript" src="${bootstrap_js_modal}" ></script>
	<script type="text/javascript" src="${jquery_js_tablesorter}" ></script>

	<script type="text/javascript">
		$(document).ready(function() {

			//bootstrap js initializations
			$('#topbar').dropdown();
			
			$(".alert-message").alert();
			$(".alert-message").alert('close');
			
			$('#my-modal').modal({
				keyboard: true,
			});
			
			//adapting spring error messages css styles according to twitter bootstrap css style 
			$('.help-inline').parent().parent().addClass('error');
			
		});
	</script>
	<script type="text/javascript">
		$(function(){
			$("table#sortTable").tablesorter({ sortList: [[1,0]] });
		});
	</script>

	<spring:url value="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css" var="style_css_url" htmlEscape="true"/>
	<link href="${style_css_url}" rel="stylesheet" type="text/css" />

	<style type="text/css">
		body {
			padding-top: 100px;
		}
	</style>

	<tiles:insertAttribute name="head" />

</head>

<body>
    <tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body"/>				 
</body>

</html>
