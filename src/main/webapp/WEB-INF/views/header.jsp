<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<%--
	**********************************************************
							Top bar
	**********************************************************
--%>
<spring:message code="menu_account_settings" var="account_settings_label" htmlEscape="false" />
<spring:url value="/account" var="account_settings_link"/>

<%-- Menu Users --%>
<spring:message code="menu_users" var="users_label" htmlEscape="false" />
<spring:message code="menu_users_list_enabled_users" var="users_list_enabled_users_label" htmlEscape="false" />
<spring:message code="menu_users_list_disabled_users" var="users_list_disabled_users_label" htmlEscape="false" />
<spring:message code="menu_users_create" var="users_create_label" htmlEscape="false" />
<spring:url value="#" var="users_link"/>
<spring:url value="/enabledUsersList" var="users_list_enabled_users_link"/>
<spring:url value="/disabledUsersList" var="users_list_disabled_users_link"/>
<spring:url value="/newUser" var="users_create_link"/>


<spring:url var="home" value="/" htmlEscape="true"/>
<spring:message code="button_home" var="home_label" htmlEscape="false" />


<div class="topbar" id="topbar">
    <div class="topbar-inner">
	    <div class="container-fluid">
	          <ul class="nav" >
				  <li><a href="${home}">${fn:escapeXml(home_label)}</a></li>
				  <sec:authorize ifAllGranted="ROLE_ADMIN">
			          <li class="dropdown" data-dropdown="dropdown">
			          	<a href="${users_link}" class="dropdown-toggle">${fn:escapeXml(users_label)}</a>
			          	<ul class="dropdown-menu">
							<li><a href="${users_list_enabled_users_link}">${fn:escapeXml(users_list_enabled_users_label)}</a></li>
							<li><a href="${users_list_disabled_users_link}">${fn:escapeXml(users_list_disabled_users_label)}</a></li>
		   					<li class="divider"></li>
							<li><a href="${users_create_link}">${fn:escapeXml(users_create_label)}</a></li>
			          	</ul>
			          </li>
				  </sec:authorize>
	          </ul>
	          
	          <ul class="nav pull-right" style="float: right;">
					<c:if test="${pageContext['request'].userPrincipal != null}">
						<li>
							<spring:message code="header_account_owner" var="account_owner_label" htmlEscape="false" />
							<%--
							<p>Frank's account - 334343 &nbsp;</p> 
							--%>
							<p><c:out value="${pageContext['request'].userPrincipal.name }"></c:out>${fn:escapeXml(account_owner_label)} &nbsp;</p>
						</li>
						<li>
							<%--
							<a href="#">Account Settings</a>
							 --%>
							<spring:url value="/account" var="account"/>
							<a href="${account}">
							  <spring:message code="header_account_label"/>
							</a>
						</li>
						<li>
							<%--
							<a href="#">Logout</a>
							 --%>
							<spring:url value="/resources/j_spring_security_logout" var="logout"/>
							<a href="${logout}">
								<spring:message code="security_logout"/>
							</a>
						</li>
					 </c:if>
					
					 <c:if test="${pageContext['request'].userPrincipal == null}">
						<li>
						<spring:url value="/login" var="login"/>
						<a href="${login}">
						  <spring:message code="security_login_title"/>
						</a>
						</li>
					 </c:if>
	          </ul>
	    </div>
    </div>
</div>

    