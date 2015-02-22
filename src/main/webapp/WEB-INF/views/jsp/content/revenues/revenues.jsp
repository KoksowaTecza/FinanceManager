<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<script>
$(document).ready(function(){
	 $('.nav.nav-tabs.nav-justified li.1').addClass("active");
});



function addRevenueElement() {
	//$('.alert').hide();
	$.ajax({
		type : "GET",
		beforeSend : function(req) {
			req.setRequestHeader("Accept", "text/html;type=ajax");
		},
		url : " <s:url value="/app/profile/revenues/revenue"/> ",
		data : "new",
		success : function(response) {
			$("#addRevenueModal").html(response);
			$('#revenueItem').modal('show');
		},
		error : function(e) {
			alert('Error : ' + e);
		}
	});
}
</script>
<div>
<div class="buttons_grid">
		<button type="button" class="btn btn-success"
			onclick="addRevenueElement()">
			<i class="fa fa-plus-circle"></i> Dodaj przychod
		</button>
</div>
<br />
<div id="addRevenueModal"></div>		
</div>