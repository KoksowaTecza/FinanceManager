<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
$(document).ready(function() {
	$("#iconsPage div a").click(function(e) {
		e.preventDefault();
		var element = $(this).children("i").attr("class").split(" ", 2);
		var icon = element[0] + " " + element[1];
		$("#icon_name").val(icon);
		$("#iconsPage").modal("hide");
		$("#addCategory div a i").attr("class", icon + " fa-3x fa-border");
	});
});


function doAjaxPost() {
	    // get the form values
	    $("input").prop('disabled', true);
	    var urlGet = "<s:url value='/app/profile/expenses/categories'/>";
	    
	    if($("#edit").val()=='edit'){
	    	urlGet = urlGet+"?edit";
	    }
	    
	    
	    var category_name = $('#category_name').val();
	    var category_name_old = $('#category_name_old').val();
	    var icon_name = $('#icon_name').val();
	    var category_desc = $('#category_desc').val();
	    var id = $('#id').val();

	    $.ajax({
	        type: "POST",
	        url: urlGet,
	        data: "id="+ id +"&category_name=" + category_name + "&icon_name=" + icon_name + "&category_desc="+category_desc,
	        success: function(response){
	            // we have the response
	            if(response.status == "SUCCESS"){
	            	 $('#addCategory').modal('hide');   
	            	 var onlyUrl = window.location.href.replace(window.location.search,'');
	            	 window.location.replace(onlyUrl+"?success=true");
	            	 //$('#succes_message').show();
	            	 /* var url = "<s:url value='/app/profile/revenues/categories/'/>"+response.result.category_name;
	            	 if(response.addings != "edit"){
	        
	            	 	$("#category_table tbody").append("<tr><td align='center'><i class='"+response.result.icon_name +" fa-2x fa-border' style='color:#428bca;'></i></td><td>"+response.result.category_name+"</td><td>"+response.result.category_desc+"</td><td><a href='"+url+"' class='modify'>Modyfikuj</a> <a href='"+url+"' class='delete'>Usun</a></td></tr>");
	            	 }else {
	            		//$("#category_table tbody tr[td:contains('"+category_name+"')]");
	            		var tr = $("#category_table tbody tr td:contains('"+category_name_old+"')").parent();
	            		tr.children(".cat_name").text(""+response.result.category_name+"");
	            		tr.children(".icon").children("i").attr("class", ""+ response.result.icon_name +" fa-2x fa-border");
	            		tr.children(".cat_desc").text(""+response.result.category_desc+"");
	            		tr.children(".cat_url").children("a").attr("href", ""+ url +"");
	            		//alert(category_name_old+"  "+$("#category_table tbody tr td:contains('"+category_name_old+"')").text());
	            	 } */
	            	}else{
	                 for(i =0 ; i < response.result.length ; i++){
	                     var errorMessage = response.result[i].defaultMessage;
	                     var field = response.result[i].field;
	                     $("#"+field).next(".error" ).remove();
	                     $("#"+field).after(" <span class='error'>"+ errorMessage +"</span>" );
	                 }  
	             }
	            $("input").prop('disabled', false);
	         },
	         error: function(e){
	        	 $('#dange_message').show();  
	             $("input").prop('disabled', false);
	         }
	    });
	};
</script>
<div class="modal fade" id="addCategory" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Zamknij</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Dodaj kategorie</h4>
			</div>

			<div class="modal-body">
				<sf:form method="POST" modelAttribute="categoryExpensesEntity"
					class="form-horizontal" role="form" id="formAddCategory">
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Nazwa
							kategorii:</label>
						<div class="col-md-5">
							<sf:input path="category_name" id="category_name"
								class="form-control" placeholder="Nazwa" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Opis:</label>
						<div class="col-md-5">
							<sf:textarea path="category_desc" id="category_desc" class="form-control" placeholder="Opis"/>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Ikona:</label>
						<div class="col-md-5">
							<a href="#" onclick="event.preventDefault();$('#iconsPage').modal('show');"><i class="${categoryRevenueEntity.icon_name != null ? categoryRevenueEntity.icon_name : 'fa fa-glass' } fa-3x fa-border"></i></a>
							<sf:hidden path="icon_name" id="icon_name" value="fa fa-glass"/>
						</div>
					</div>
					<input type="hidden" name="edit" value="${edit}" id="edit" />
					<sf:hidden path="id" id="id" />
					<sf:hidden path="category_name" id="category_name_old" />
				</sf:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="doAjaxPost()">Zapisz</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Zmaknij</button>
			</div>
		</div>
	</div>
</div>

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
					<a href=""><i class="fa fa-glass fa-4x fa-border"></i></a> <a
						href=""><i class="fa fa-coffee fa-4x fa-border"></i></a> <a
						href=""><i class="fa fa-graduation-cap fa-4x fa-border"></i></a>
					<a href=""><i class="fa fa-heart fa-4x fa-border"></i></a> <a
						href=""><i class="fa fa-home fa-4x fa-border"></i></a> <a
						href=""><i class="fa fa-briefcase fa-4x fa-border"></i></a>
				</div>
			</div>
		</div>
	</div>