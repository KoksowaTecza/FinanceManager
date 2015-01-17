<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script>
	$(document).ready(function() {
		var notification = "${success}"
			if(notification=="true"){
				$('#succes_message').show();
			}
			if(notification=="false"){
				$('#danger_message').show();
			}
		$('.nav.nav-tabs.nav-justified li.2').addClass("active");
	

		$("#category_table").on({
			click : function(e) {
				e.preventDefault();
				var urlGet = $(this).attr("href");
				var option = $(this).attr("class");
				if(option == "modify"){
					$.ajax({
						type : "GET",
						beforeSend : function(req) {
							req.setRequestHeader("Accept", "text/html;type=ajax");
						},
						url : urlGet,
						data : "edit",
						success : function(response) {
							$("#addCetegoryModal").html(response);
							$('#addCategory').modal('show');
						},
						error : function(e) {
							alert('Error : ' + e);
						}
						});
				}
				if(option == "delete"){
					$('.alert').hide();
					$.ajax({
						type : "GET",
						url : urlGet,
						data : "delete",
						success : function(response) {
							if(response.status == "SUCCESS"){
								$('#succes_message_delete').show();
								$("#category_table tbody tr td:contains('"+response.result+"')").parent().remove();
							}
						},
						error : function(e) {
							alert('Error : ' + e);
						}
						});
				}
				
			}
		}, 'a');
	});
	function addCategoryModal() {
		$('.alert').hide();
		$.ajax({
			type : "GET",
			beforeSend : function(req) {
				req.setRequestHeader("Accept", "text/html;type=ajax");
			},
			url : " <s:url value="/app/profile/expenses/categories"/> ",
			data : "new",
			success : function(response) {
				$("#addCetegoryModal").html(response);
				$('#addCategory').modal('show');
			},
			error : function(e) {
				alert('Error : ' + e);
			}
		});
	}
</script>
<div id="user_categories_revenue">
	<div class="alert alert-success" role="alert" id="succes_message"
		style="display: none;">
		<strong>Sukces!</strong> Dane zostaly zapisane poprawnie.
	</div>
	<div class="alert alert-danger" role="alert" id="dange_message"
		style="display: none;">
		<strong>Blad!</strong> Dane nie zostaly zapisane poprawnie, sprobuj
		pozniej.
	</div>
	<div class="alert alert-success" role="alert" id="succes_message_delete"
		style="display: none;">
		<strong>Sukces!</strong> Dane zostaly usuniete poprawnie.
	</div>
	
	<div class="buttons_grid">
		<button type="button" class="btn btn-success"
			onclick="addCategoryModal()">
			<i class="fa fa-plus-circle"></i> Dodaj nową kategorie
		</button>
	</div>
	<br />
	<div id="addCetegoryModal"></div>
	<table class="table table-bordered" id="category_table">
		<thead>
			<tr>
				<th width="10%">Ikona</th>
				<th width="30%">Nazwa kategorii</th>
				<th width="40%">Opis</th>
				<th width="20%">Modyfikacja</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${categoryList != null}">
			<c:forEach var="category" items="${categoryList}">
				<c:set var="url"><s:url value='/app/profile/expenses/categories/'/></c:set>
				<tr>
					<td class="icon" align="center"><i class="${category.icon_name} fa-2x fa-border" style="color:#428bca;"></i></td>
					<td class="cat_name">${category.category_name}</td>
					<td class="cat_desc">${category.category_desc}</td>
					<td class="cat_url"><a href="${url}${category.category_name}" class="modify">Modyfikuj</a> <a href="${url}${category.category_name}" class="delete">Usun</a></td>
				</tr>
			</c:forEach>
			</c:if>
		</tbody>
	</table>



</div>