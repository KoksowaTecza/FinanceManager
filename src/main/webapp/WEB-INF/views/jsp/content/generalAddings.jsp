<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<script>
	$(document)
			.ready(
					function() {
						var configurationAllert = <c:out value="${sessionScope['scopedTarget.userSessionObject'].configurationAllert}"/>
						if (configurationAllert == true) {
							$('#configAllert').modal('show');
						}
					});
</script>
<div class="modal fade" id="configAllert" data-backdrop="static"
		data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Brak konfiguracji</h4>
				</div>
				<div class="modal-body">
					<p>Do poprawnego dzialania apliakcji musisz najpierw
						skonfigurowac swoje konto.</p>
					<p>
						Przejdz do konfiguracji konta <a
							href="<s:url value="/app/profile/configuration/finance" />"
							class="btn btn-success" role="button"><s:message
								code="menu.config" text="default text" /></a>
					</p>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<%-- <div class="modal fade" id="monitorAllert" data-backdrop="static"
		data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">Rozpocznij okres
						monitorowania</h4>
				</div>
				<div class="modal-body">
					<p>Aby rozpoczac korzytsanie z aplikacji, musisz rzpoczac okres monitrowania</p>
				</div>
				<div class="modal-footer">
				<a href="<s:url value="/app/profile/configuration/finance" />" class="btn btn-success" role="button">Rozpocznij</a>
				<a href="<s:url value="/j_spring_security_logout" />" class="btn btn-default" role="button">Zamknij</a>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div> --%>