<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="row">
<div class="col-md-10 col-md-offset-1">
	<h2><s:message code="signUp.name" text="default text" /></h2>
	<ol class="breadcrumb">
	  <li><a href="<s:url value="/app/home" />" ><s:message code="breadcrumb.home" text="default text" /></a></li>
	  <li class="active"><s:message code="breadcrumb.signUp.account" text="default text" /></li>
	</ol>
	<sf:form method="POST" modelAttribute="userAccount" class="form-horizontal" enctype="multipart/form-data" role="form">
		<div class="form">
		<div class="form-group">
		<label for="userFullName"><s:message code="signUp.fullname" text="default text" /></label>
		<sf:input path="userFullName" id="userFullName" size="15" class="form-control"/>
		<sf:errors path="userFullName" cssClass="error" />
		</div>
		<div class="form-group">
		<label for="username"><s:message code="signUp.username" text="default text" /></label>
		<sf:input path="username" id="username" size="15" maxlength="15" class="form-control"/>
		<sf:errors path="username" cssClass="error" />
		</div>
		<div class="form-group">
		<label for="password"><s:message code="signUp.password" text="default text" /></label>
		<sf:password path="password" id="password" size="30" showPassword="true" class="form-control"/>
		<sf:errors path="password" cssClass="error" />
		</div>
		<div class="form-group">
		<label for="email"><s:message code="signUp.email" text="default text" /></label>
		<sf:input path="email" id="email" size="30" class="form-control"/>
		<sf:errors path="email" cssClass="error" />
		</div>
		<div class="form-group">
		    <label for="image"><s:message code="signUp.profileImg" text="default text" /></label>
		    <input type="file" name="image">
		    <p class="help-block"><s:message code="signUp.img.help" text="default text" /></p>
		 </div>
		</div>
		<button type="submit"  class="btn btn-primary"><s:message code="signUp.submit" text="default text" /></button>
	</sf:form>
</div>
</div>