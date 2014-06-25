<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<h3>Please sign in</h3>
	<form role="form">
		<div class="form-group">
			<label for="usernameOrEmail">Username or email</label> <input
				type="text" class="form-control" id="usernameOrEmail">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password"
				class="form-control" id="password">
		</div>
		<div class="checkbox">
			<label> <input type="checkbox" value=""> Rember me
			</label>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
		<a href="">Forgot</a>
	</form>
	<div class="col-md-12">
		<div class="registration">
			<p>Want an account</p>
			<a href="<c:url value="/spitters?new" />">Join for free</a>
			<p>It's fast and easy</p>
		</div>
	</div>
</div>