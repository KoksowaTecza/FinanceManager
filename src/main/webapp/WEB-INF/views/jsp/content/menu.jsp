<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<div>
	<h4>Welcome ${sessionScope['scopedTarget.userEntity'].username}</h4>
	<div id="obraz">
		<img src="<s:url value="/image?id=${sessionScope['scopedTarget.userEntity'].username}" />" width="140px" height="140px" class="img-thumbnail" />
	</div>
	<div id="menu_button">
		<a href="<s:url value="/app/home" /> " class="btn btn-success btn-lg btn-block" role="button">Home</a>
		<a href="<s:url value="/app/spittle/username" /> " class="btn btn-success btn-lg btn-block" role="button">Your spittles</a> 
		<a href="#" class="btn btn-success btn-lg btn-block" role="button">Profile</a>
		<a href="<s:url value="/j_spring_security_logout" /> " class="btn btn-success btn-lg btn-block" role="button">Logout</a>
	</div>
</div>