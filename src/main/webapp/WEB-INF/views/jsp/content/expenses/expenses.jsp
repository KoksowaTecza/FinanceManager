<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<script>
$(document).ready(function(){
	 $('.nav.nav-tabs.nav-justified li.1').addClass("active");
});

function addExpensesModal() {
	//$('.alert').hide();
	$.ajax({
		type : "GET",
		beforeSend : function(req) {
			req.setRequestHeader("Accept", "text/html;type=ajax");
		},
		url : " <s:url value="/app/profile/expenses/expense"/> ",
		data : "new",
		success : function(response) {
			$("#addExpensesModal").html(response);
			$('#expenseItem').modal('show');
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
			onclick="addExpensesModal()">
			<i class="fa fa-plus-circle"></i> Dodaj wydatek
		</button>
</div>
<br />
<div id="addExpensesModal"></div>		
</div>