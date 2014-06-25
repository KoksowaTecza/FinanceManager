<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div>
	<h4>Welcome ${spitter.username}</h4>
	<div id="obraz">
		<img
			src="<s:url value="/static/avatars/${spitter.profile_image}.jpg" />"
			width="140px" height="140px" class="img-thumbnail" />
	</div>
	<div id="menu_button">
		<a href="#" class="btn btn-success btn-lg btn-block" role="button">Home</a>
		<a href="#" class="btn btn-success btn-lg btn-block" role="button">Your
			snippet</a> <a href="#" class="btn btn-success btn-lg btn-block"
			role="button">Profile</a>
	</div>
</div>