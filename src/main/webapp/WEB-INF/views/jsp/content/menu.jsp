<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<div>
	<h3><s:message code="menu.welcome" text="default text" /> ${sessionScope['scopedTarget.userSessionObject'].username}</h3>
	<div id="obraz">
		<img src="<s:url value="/image?id=${sessionScope['scopedTarget.userSessionObject'].profile_image_name}" />" width="140px" height="140px" class="img-thumbnail" />
	</div>
	<div id="menu_button">
		<a href="<s:url value="/app/profile/history"/> " class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.history" text="default text" /></a>
		<a href="<s:url value="/app/profile/revenues"/> " class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.revenu" text="default text" /></a>
		<a href="<s:url value="/app/profile/expenses"/> " class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.expenses" text="default text" /></a>
		<a href="<s:url value="/app/profile/investition"/> " class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.invest" text="default text" /></a>
		<a href="<s:url value="/app/profile/balance"/> " class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.balance" text="default text" /></a>
		<a href="<s:url value="/app/profile/reports"/> " class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.report" text="default text" /></a>
		<a href="<s:url value="/app/profile/configuration/finance" />" class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.config" text="default text" /></a>
		<a href="<s:url value="/j_spring_security_logout" /> " class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.logout" text="default text" /></a>
	</div>
</div>