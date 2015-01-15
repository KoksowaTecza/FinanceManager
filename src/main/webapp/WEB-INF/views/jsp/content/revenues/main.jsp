<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div>
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<h2>
				<s:message code="revenues.title" text="default text" />
			</h2>
			<ul class="nav nav-tabs nav-justified">
				<li role="presentation" class="1"><a href="<s:url value="/app/profile/revenues/revenues"/> ">Przychody</a></li>
				<li role="presentation" class="2"><a href="<s:url value="/app/profile/revenues/categories"/> ">Kategorie</a></li>
			</ul>
			<tiles:insertAttribute name="tab" />
		</div>
	</div>
</div>