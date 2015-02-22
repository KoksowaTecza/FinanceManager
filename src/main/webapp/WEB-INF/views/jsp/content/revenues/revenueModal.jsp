<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	var map;
	var marker
	function initialize() {
		var mapCanvas = document.getElementById('map-canvas');
		var mapOptions = {
			zoom : 10,
		};
		var initialLocation;
		marker = new google.maps.Marker({
			draggable : true,
			animation : google.maps.Animation.DROP,
			title : "miejsce"
		});

		//inicjalizacja
		map = new google.maps.Map(mapCanvas, mapOptions);
		marker.setMap(map);
		console.log("1:");
		// Try W3C Geolocation (Preferred)
		if (navigator.geolocation) {
			browserSupportFlag = true;
			navigator.geolocation.getCurrentPosition(function(position) {
				initialLocation = new google.maps.LatLng(
						position.coords.latitude, position.coords.longitude);
				$("#coordLat").val(initialLocation.lat().toFixed(3));
				$("#coordLng").val(initialLocation.lng().toFixed(3));
				map.setCenter(initialLocation);
				marker.setPosition(initialLocation);
			}, function() {
				handleNoGeolocation(browserSupportFlag);
			});
		}
		// Browser doesn't support Geolocation
		else {
			browserSupportFlag = false;
			handleNoGeolocation(browserSupportFlag);
		}

		function handleNoGeolocation(errorFlag) {
			if (errorFlag == true) {
				alert("Geolocation service failed.");
				initialLocation = newyork;
			} else {
				alert("Your browser doesn't support geolocation. We've placed you in Siberia.");
				initialLocation = siberia;
			}
			map.setCenter(initialLocation);
			marker.setPosition(initialLocation);
			$("#coordLat").val(initialLocation.lat().toFixed(3));
			$("#coordLng").val(initialLocation.lng().toFixed(3));
		}

		google.maps.event.addListener(marker, 'click', toggleBounce);
		google.maps.event.addListener(marker, 'dragend', function(evt) {
			$("#coordLat").val(evt.latLng.lat().toFixed(3));
			$("#coordLng").val(evt.latLng.lng().toFixed(3));
			//alert("wsp: lat"+ evt.latLng.lat().toFixed(3) +" lng "+evt.latLng.lng().toFixed(3));
		});

		console.log("3: " + initialLocation);
	}

	function toggleBounce() {

		if (marker.getAnimation() != null) {
			marker.setAnimation(null);
		} else {
			marker.setAnimation(google.maps.Animation.BOUNCE);
		}
	}

	$('#revenueItem').on('shown.bs.modal', function() {
		initialize();
		google.maps.event.trigger(map, "resize");
	});

	function doAjaxPost() {
		// get the form values
		$("input").prop('disabled', true);
		var urlGet = "<s:url value='/app/profile/revenues/revenue'/>";

		if ($("#edit").val() == 'edit') {
			urlGet = urlGet + "?edit";
		}

		var amount = $('#amount').val();
		var place = $('#place').val();
		var description = $('#description').val();
		var id = $('#id').val();
		var category = $('#category_name').val();
		var transaction_time_string = $("#transaction_time_string").val();
		var coordLat = $('#coordLat').val();
		var coordLng = $('#coordLng').val();
		$.ajax({
			type : "POST",
			url : urlGet,
			data : "id=" + id + "&amount=" + amount + "&place=" + place
					+ "&description=" + description + "&category_name="
					+ category + "&transaction_time_string="
					+ transaction_time_string+"&coordLat="+coordLat+"&coordLng="+coordLng,
			success : function(response) {
				// we have the response
				if (response.status == "SUCCESS") {
					$('#revenueItem').modal('hide');
					var onlyUrl = window.location.href.replace(
							window.location.search, '');
					window.location.replace(onlyUrl + "?success=true");
				} else {
					for (i = 0; i < response.result.length; i++) {
						var errorMessage = response.result[i].defaultMessage;
						var field = response.result[i].field;
						$("#" + field).next(".error").remove();
						$("#" + field).after(
								" <span class='error'>" + errorMessage
										+ "</span>");
					}
				}
				$("input").prop('disabled', false);
			},
			error : function(e) {
				$('#dange_message').show();
				$("input").prop('disabled', false);
			}
		});
	};
</script>
<style>
#map-canvas {
	height: 300px;
	background-color: #CCC;
}
</style>
<div class="modal fade" id="revenueItem" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true"
	data-backdrop="static" onload="resizeMap()">
	<div class="modal-dialog">
		<div class="modal-content">

			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Zamknij</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Dodaj przychod</h4>
			</div>

			<div class="modal-body">
				<sf:form method="POST" modelAttribute="revenueEntity"
					class="form-horizontal" role="form" id="formAddRevenueItem">
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Kwota:</label>
						<div class="col-md-5">
							<sf:input path="amount" id="amount" class="form-control"
								placeholder="Kwota" />
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Kategoria:</label>
						<div class="col-md-5">
							<sf:select path="category_name" items="${categoryMap}"
								class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Data:</label>
						<div class="col-md-5">
							<sf:input type="date" path="transaction_time_string"
								class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Miejsce:</label>
						<div class="col-md-8">
							<div id="map-canvas"></div>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-md-3 control-label">Opis:</label>
						<div class="col-md-5">
							<sf:textarea path="description" id="description"
								class="form-control" placeholder="Opis" />
						</div>
					</div>
					<input type="hidden" name="edit" value="${edit}" id="edit" />
					<input type="hidden" name="coordLat" value="${coordLat}"
						id="coordLat" />
					<input type="hidden" name="coordLng" value="${coordLng}"
						id="coordLng" />
					<sf:hidden path="id" id="id" />
				</sf:form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="doAjaxPost()">Zapisz</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Zmaknij</button>
			</div>
		</div>
	</div>
</div>