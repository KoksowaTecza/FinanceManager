<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<script>
$(document).ready(function(){
	 $('.nav.nav-tabs.nav-justified li.finance').addClass("active");
});
</script>
<div>
				<sf:form method="POST" modelAttribute="configurationData" class="form-horizontal" role="form">
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Waluta</label>
						<div class="col-md-9">
						<label class="radio-inline"> 
							<sf:radiobutton path="waluta" value="zloty" />Zloty
						</label> 
						<label class="radio-inline"> 
							<sf:radiobutton path="waluta" value="dolar" />Dolar
						</label>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Kwota poczatkowa</label>
						<div class="col-md-5">
							<sf:input path="startAmount" id="startAmount" class="form-control" placeholder="Kwota"/>
							<sf:errors path="startAmount" cssClass="error" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-success">Zapisz</button>
						</div>
					</div>
				</sf:form>

			</div>