<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<h3>Please sign in</h3>
	<s:url var="authUrl" value="/app/spitters/authenticate" />
	<form method="post" class="signin" action="${authUrl}" role="form">
		<div class="form-group">
			<label for="usernameOrEmail">Username or email</label> 
				<input
				type="text" class="form-control" id="usernameOrEmail"
				name="username" />
		</div>
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password"
				class="form-control" id="password" name="password" />
		</div>
		<div class="checkbox">
			<label>
			 <input type="checkbox" id="remeber_me"
				name="_spring_security_remember_me" /> Rember me
			</label>
		</div>
		<button name="commit" type="submit" class="btn btn-primary"/>Sign in</button> <a href="">Forgot</a>
	</form>
	<div class="col-md-12">
		<div class="registration">
			<p>Want an account</p>
			<a href="<c:url value="/app/spitters?new" />">Join for free</a>
			<p>It's fast and easy</p>
		</div>
	</div>
</div>