<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script>
	$(document).ready(function() {
		$('.nav.nav-tabs.nav-justified li.2').addClass("active");
		$("#iconsPage div a").click(function() {
			var element = $(this).children("i").attr("class").split(" ", 2);
			var icon = element[0] + " " + element[1];
			$("#icon_name").val(icon);
			$("#iconsPage").modal("hide");
			$("#addCategory div a i").attr("class", icon + " fa-3x fa-border");
		});

		$("#category_table").on({
			click : function(e) {
				e.preventDefault();
				var urlGet = $(this).attr("href");
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
		}, 'a');
	});
	function addCategoryModal() {
		$('#succes_message').hide();
		$('#dange_message').hide();
		$.ajax({
			type : "GET",
			beforeSend : function(req) {
				req.setRequestHeader("Accept", "text/html;type=ajax");
			},
			url : " <s:url value="/app/profile/revenues/categories"/> ",
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
	<div class="buttons_grid">
		<button type="button" class="btn btn-success"
			onclick="addCategoryModal()">
			<i class="fa fa-plus-circle"></i> Dodaj kategorie
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
			<c:forEach var="category" items="${categoryList}">
		
				<c:set var="url"><s:url value='/app/profile/revenues/categories/'/></c:set>
				<tr>
					<td><i class="${category.icon_name} fa-border"></i></td>
					<td>${category.category_name}</td>
					<td>${category.category_desc}</td>
					<td><a href="${url}${category.category_name}">Modyfikuj</a> <a href=''>Usun</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="modal fade" id="iconsPage" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Wybierz ikone</h4>
				</div>
				<div class="modal-body">
					<a href="#"><i class="fa fa-glass fa-4x fa-border"></i></a> <a
						href="#"><i class="fa fa-coffee fa-4x fa-border"></i></a> <a
						href="#"><i class="fa fa-graduation-cap fa-4x fa-border"></i></a>
					<a href="#"><i class="fa fa-heart fa-4x fa-border"></i></a> <a
						href="#"><i class="fa fa-home fa-4x fa-border"></i></a> <a
						href="#"><i class="fa fa-briefcase fa-4x fa-border"></i></a>
				</div>
			</div>
		</div>
	</div>

</div>