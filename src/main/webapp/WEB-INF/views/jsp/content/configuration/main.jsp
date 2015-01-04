<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div>
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<h2>
				<s:message code="config.title" text="default text" />
			</h2>
			<ul class="nav nav-tabs nav-justified">
				<li role="presentation" class="finance"><a href="<s:url value="/app/profile/configuration/finance"/> ">Menadzer
						finansowy</a></li>
				<li role="presentation" class="profile"><a href="<s:url value="/app/profile/configuration/profile"/> ">Profil</a></li>
			</ul>
			<tiles:insertAttribute name="tab" />
		</div>
	</div>
</div>