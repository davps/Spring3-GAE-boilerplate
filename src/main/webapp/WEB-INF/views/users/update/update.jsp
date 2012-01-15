<%@ include file="/WEB-INF/views/include.jsp" %>

    <div class="container" style="margin-top: -0px; padding-right: 0px; padding-top: 20px;" >
		<div class="content" style="margin-left: 0px; "  >
			<div class="row">
				<div class="span8">
					<form:form method="post" action="updateUserDetails" commandName="userDetailsModel">
						<fieldset>
							<legend>Update user</legend>
							<div class="clearfix">
								<label for="username">Username</label>
								<div class="input">
									<form:input path="username" cssClass="xlarge" size="30" readonly="true"/>
									<form:errors path="username" cssClass="help-inline"/>					
								</div>
							</div>
							<div class="clearfix">
								<label for="firstName">First Name</label>
								<div class="input">
									<form:input path="firstName" cssClass="xlarge" size="30"/>
									<form:errors path="firstName" cssClass="help-inline"/>					
								</div>
							</div>
							<div class="clearfix">
								<label for="lastName">Last Name</label>
								<div class="input">
									<form:input path="lastName" cssClass="xlarge" size="30"/>
									<form:errors path="lastName" cssClass="help-inline"/>					
								</div>
							</div>
							<div class="clearfix">
								<label for="email">Email</label>
								<div class="input">
									<form:input path="email" cssClass="xlarge" size="30"/>
									<form:errors path="email" cssClass="help-inline"/>		
								</div>
							</div>
							<div class="clearfix">
								<label for="admin">Admin?</label>
								<div class="input" style="margin-top: 5px;">
									<form:checkbox path="admin"/>
									<form:errors path="admin" cssClass="help-inline"/>			
								</div>
							</div>
							<div class="actions">
								<input type="submit" class="btn primary" value="update">
							</div>
						</fieldset>
					</form:form>				
				</div>
			</div>
		</div><!-- /content -->
    </div> <!-- /container -->
