<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<div>
	<h4><s:message code="menu.welcome" text="default text" /> ${sessionScope['scopedTarget.userSessionObject'].username}</h4>
	<div id="obraz">
		<img src="<s:url value="/image?id=${sessionScope['scopedTarget.userSessionObject'].profile_image_name}" />" width="140px" height="140px" class="img-thumbnail" />
	</div>
	<div id="menu_button">
		<a href="/app/history" class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.history" text="default text" /></a>
		<a href="/app/revenues" class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.revenu" text="default text" /></a>
		<a href="/app/expenses" class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.expenses" text="default text" /></a>
		<a href="/app/investition" class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.invest" text="default text" /></a>
		<a href="/app/balance" class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.balance" text="default text" /></a>
		<a href="/app/reports" class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.report" text="default text" /></a>
		<a href="/app/configuration" class="btn btn-success btn-lg btn-block" role="button"><s:message code="menu.config" text="default text" /></a>
	</div>
</div>