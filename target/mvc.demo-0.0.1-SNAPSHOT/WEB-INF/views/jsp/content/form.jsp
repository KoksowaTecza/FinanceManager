<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="row">
<div class="col-md-6 col-md-offset-3">
<br/>
<h2>Wpisz swoje dane</h2>
<br/>
<sf:form method="POST" modelAttribute="userEntity" role="form">
	<div class="form-group">
		<label for="user_full_name">Imie: </label>
		<sf:input class="form-control" path="userFullName" id="user_full_name"/>
		<sf:errors path="userFullName" />
	</div>
	<div class="form-group">
		<label for="user_name">Username: </label>
		<sf:input class="form-control" path="username" id="user_name" />
		<sf:errors path="username" />
	</div>
	<div class="form-group">
		<label for="password">Haslo: </label>
		<sf:password class="form-control" path="password" id="password" />
		<sf:errors path="password" />
	</div>
	<div class="form-group">
		<label for="email">Email: </label>
		<sf:input class="form-control" path="email" id="email" />
	</div>
	<button type="submit" class="btn btn-default">Submit</button>
</sf:form>
</div>
</div>