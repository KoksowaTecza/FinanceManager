<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
function doAjaxPost() {
	    // get the form values
	    $("input").prop('disabled', true);
	    var urlGet = "<s:url value='/app/profile/revenues/categories'/>";
	    
	    if($("#edit").val()=='edit'){
	    	urlGet = urlGet+"?edit";
	    }
	    
	    
	    var category_name = $('#category_name').val();
	    var icon_name = $('#icon_name').val();
	    var category_desc = $('#category_desc').val();

	    $.ajax({
	        type: "POST",
	        url: urlGet,
	        data: "category_name=" + category_name + "&icon_name=" + icon_name + "&category_desc="+category_desc,
	        success: function(response){
	            // we have the response
	            if(response.status == "SUCCESS"){
	            	 $('#addCategory').modal('hide');   
	            	 $('#succes_message').show();
	            	 if($("#edit").val()!='edit'){
	            	 	var url = "<s:url value='/app/profile/revenues/categories/'/>"+response.result.category_name;
	            	 	$("#category_table tbody").append("<tr><td><i class='"+response.result.icon_name +" fa-border'></i></td><td>"+response.result.category_name+"</td><td>"+response.result.category_desc+"</td><td><a href='"+url+"'>Modyfikuj</a> <a href=''>Usun</a></td></tr>");
	            	 }
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
				<sf:form method="POST" modelAttribute="categoryRevenueEntity"
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
							<a href="#" onclick="$('#iconsPage').modal('show')"><i class="fa fa-glass fa-3x fa-border"></i></a>
							<sf:hidden path="icon_name" id="icon_name" value="fa fa-glass"/>
						</div>
					</div>
					<input type="hidden" name="edit" value="${edit}" id="edit" />
				</sf:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="doAjaxPost()">Zapisz</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Zmaknij</button>
			</div>
		</div>
	</div>
</div>