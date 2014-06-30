<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="row">
<div class="col-md-10 col-md-offset-1">
	<h2>Create a free spitter account</h2>
	<ol class="breadcrumb">
	  <li><a href="<s:url value="/app/home" />" >Home</a></li>
	  <li class="active">Add Spitter account</li>
	</ol>
	<sf:form method="POST" modelAttribute="spitter" class="form-horizontal" enctype="multipart/form-data" role="form">
		<div class="form">
		<div class="form-group">
		<label for="userFullName">Full Name</label>
		<sf:input path="userFullName" id="userFullName" size="15" class="form-control"/>
		<sf:errors path="userFullName" cssClass="error" />
		</div>
		<div class="form-group">
		<label for="username">Username</label>
		<sf:input path="username" id="username" size="15" maxlength="15" class="form-control"/>
		<sf:errors path="username" cssClass="error" />
		</div>
		<div class="form-group">
		<label for="password">Password</label>
		<sf:password path="password" id="password" size="30" showPassword="true" class="form-control"/>
		<sf:errors path="password" cssClass="error" />
		</div>
		<div class="form-group">
		<label for="email">Email</label>
		<sf:input path="email" id="email" size="30" class="form-control"/>
		<sf:errors path="email" cssClass="error" />
		</div>
		<div class="form-group">
		    <label for="image">Profile image</label>
		    <input type="file" name="image">
		    <p class="help-block">Upload only .jpg file.</p>
		 </div>
		</div>
		<button type="submit"  class="btn btn-primary">Submit</button>
	</sf:form>
</div>
</div>