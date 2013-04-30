<!DOCTYPE html>
<html lang="en" class="vocabbi_document" >
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/views/include.jsp" %>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Vestibulum id ligula porta felis euismod semper.</title>
	
	<spring:url value="../assets/css/bootstrap.css" var="style_css_url" htmlEscape="true"/>
	<spring:url value="../assets/css/bootstrap-responsive.css" var="styleResponsive_css_url" htmlEscape="true"/>
	
    <link href="${style_css_url}" rel="stylesheet">    
    <link href="${styleResponsive_css_url}" rel="stylesheet">	

	<tiles:insertAttribute name="head" />

</head>

<body>
    <tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body"/>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="../js/jquery.js"></script>
    <script src="../js/bootstrap-transition.js"></script>
    <script src="../js/bootstrap-alert.js"></script>
    <script src="../js/bootstrap-modal.js"></script>
    <script src="../js/bootstrap-dropdown.js"></script>
    <script src="../js/bootstrap-scrollspy.js"></script>
    <script src="../js/bootstrap-tab.js"></script>
    <script src="../js/bootstrap-tooltip.js"></script>
    <script src="../js/bootstrap-popover.js"></script>
    <script src="../js/bootstrap-button.js"></script>
    <script src="../js/bootstrap-collapse.js"></script>
    <script src="../js/bootstrap-carousel.js"></script>
    <script src="../js/bootstrap-typeahead.js"></script>					 
</body>

</html>
