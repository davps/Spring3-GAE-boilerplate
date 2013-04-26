<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    
    <div class="container" style="margin-top: -0px; padding-right: 0px; padding-top: 20px;" >
		<div class="content" style="margin-left: 0px; "  >
	        <div class="hero-unit">
	          <h1>Welcome!</h1>
	          <p>Vestibulum id ligula porta felis euismod semper. Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>
	          <p><a class="btn primary large">Sign in &raquo;</a>
	          
				<form name='f' action="<c:url value='j_spring_security_check' />"
					method='POST'>
			 
					<table>
						<tr>
							<td>User:</td>
							<td><input type='text' name='j_username' value=''>
							</td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input type='password' name='j_password' />
							</td>
						</tr>
						<tr>
							<td colspan='2'><input name="submit" type="submit"
								value="submit" />
							</td>
						</tr>
						<tr>
							<td colspan='2'><input name="reset" type="reset" />
							</td>
						</tr>
					</table>
			 
				</form>	          
	          
	        </div>
		</div><!-- /content -->
      <footer>
        <p>Vestibulum id ligula porta felis euismod semper.</p>
      </footer>
    </div> <!-- /container -->