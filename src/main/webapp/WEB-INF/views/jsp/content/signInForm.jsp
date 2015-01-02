<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<h3><s:message code="signIn.name" text="default text" /></h3>
	<s:url var="authUrl" value="/app/account/authenticate" />
	<form method="post" class="signin" action="${authUrl}" role="form">
		<div class="form-group">
			<label for="usernameOrEmail"><s:message code="signIn.username" text="default text" /></label> 
				<input
				type="text" class="form-control" id="usernameOrEmail"
				name="username" />
		</div>
		<div class="form-group">
			<label for="password"><s:message code="signIn.password" text="default text" /></label> 
			<input type="password"
				class="form-control" id="password" name="password" />
		</div>
		<div class="checkbox">
			<label>
			 <input type="checkbox" id="remeber_me"
				name="_spring_security_remember_me" /> <s:message code="signIn.remeber" text="default text" />
			</label>
		</div>
		<button name="commit" type="submit" class="btn btn-primary"><s:message code="signIn.signIn" text="default text" /></button> <a href=""><s:message code="signIn.forget" text="default text" /></a>
	</form>
	<div class="col-md-12">
		<div class="registration">
			<p><s:message code="signUp.text1" text="default text" /></p>
			<a href="<c:url value="/app/account?new" />"><s:message code="signUp.text2" text="default text" /></a>
			<p><s:message code="signUp.text3" text="default text" /></p>
		</div>
	</div>
</div>