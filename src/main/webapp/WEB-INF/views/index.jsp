<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    <div class="container" style="margin-top: -0px; padding-right: 0px; padding-top: 20px;" >
		<div class="content" style="margin-left: 0px; "  >
	        <div class="hero-unit">
	          <h1>Welcome!</h1>
	          <p>Vestibulum id ligula porta felis euismod semper. Integer posuere erat a ante venenatis dapibus posuere velit aliquet. Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit.</p>

	          <c:if test="${pageContext['request'].userPrincipal == null}">
	             <p>
	               <a href="/login" class="btn btn-primary btn-large">Sign in &raquo;</a>
	               <a href="/createDefaultUsers" class="btn btn-large btn-success"> Create System Users </a>
	             </p>
	             
	          </c:if>	                    

	        </div>
		</div><!-- /content -->
      <footer>
        <p>Vestibulum id ligula porta felis euismod semper.</p>
      </footer>
    </div> <!-- /container -->
