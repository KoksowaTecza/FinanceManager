<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<h2>Your spittles</h2>
			<ol class="breadcrumb">
				<li><a href="/app/home">Home</a></li>
				<li class="active">Spittle</li>
			</ol>
		   <button type="button" class="btn btn-success" data-toggle="modal"
		        data-target="#addSpittle">
		        <span class="glyphicon glyphicon-plus"></span> Add new spittle
		      </button>
			<div></div>
		</div>
	</div>
	
	<!-- Modal -->
    <div class="modal fade" id="addSpittle" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
    
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">
              <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
            </button>
            <h4 class="modal-title" id="myModalLabel">Add Spittle</h4>
          </div>
      
          <div class="modal-body">
          	<s:url var="form_addSpitlleUrl" value="/app/spittle/addSpitlle" />
            <form role="form" id="addSpittleForm" action="${form_addSpitlleUrl}" method="post">
              <div class="form-group">
                <textarea class="form-control" rows="3" name="message" placeholder="Your message"></textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" id="publish">Publish</button>
          </div>
        </div>
      </div>
    </div>
	


</div>