<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div>
	<h2>Create a free spitter account</h2>
	<sf:form method="POST" modelAttribute="spitter" class="form-horizontal" enctype="multipart/form-data" role="form">
		<div class="form-group">
		<label for="userFullName">Full Name</label>
		<sf:input path="userFullName" id="userFullName" size="15" class="form-control"/>
		</div>
		<div class="form-group">
		<label for="username">Username</label>
		<sf:input path="username" id="username" size="15" maxlength="15" class="form-control"/>
		</div>
		<div class="form-group">
		<label for="password">Password</label>
		<sf:password path="password" id="password" size="30" showPassword="true" class="form-control"/>
		</div>
		<div class="form-group">
		<label for="email">Email</label>
		<sf:input path="email" id="email" size="30" class="form-control"/>
		</div>
		<div class="form-group">
		    <label for="image">Profile image</label>
		    <input type="file" name="image">
		    <p class="help-block">Upload only .jpg file.</p>
		 </div>
		<input type="submit" />
	</sf:form>
</div>