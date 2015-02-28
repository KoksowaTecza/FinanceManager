<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<script>
$(document).ready(function(){
	 $('.nav.nav-tabs.nav-justified li.1').addClass("active");
	 drawChart();
	 if(chart != null){
		 getRevenueSummaryData();
	 }
});

function getRevenueSummaryData(){
	$.ajax({
		type : "GET",
		url : " <s:url value="/app/profile/expenses/summary"/> ",
		success : function(response) {
			console.log(response);
			setChartData(response);
		},
		error : function(e) {
			alert('Error : ' + e);
		}
	});
}

function setChartData(response){
	var category = [];
	var amount = [];
	var projection = [];
	for(var i = 0; i<response.length; i++){
		category[i] = response[i].cat_name;
		amount[i] = response[i].sum;
		projection[i] = response
	}
	console.log("category "+category);
	chart.xAxis[0].update({categories:category});
	chart.series[1].update({data:amount});
}



function drawChart(){
	 chart = new Highcharts.Chart({

		 chart: {
		        renderTo: 'chartExpenses',
		        animation: false
		    },
		    
		    title: {
		        text: 'Twoje wydatki'
		    },

		    xAxis: {
		    },

		    plotOptions: {
		        series: {
		            cursor: 'ns-resize',
		            point: {
		                events: {

		                    drag: function (e) {
		                        // Returning false stops the drag and drops. Example:
		                        /*
		                        if (e.newY > 300) {
		                            this.y = 300;
		                            return false;
		                        }
		                        */
		                        $('#drag').html(
		                            'Dragging <b>' + this.series.name + '</b>, <b>' + this.category + '</b> to <b>' + Highcharts.numberFormat(e.newY, 2) + '</b>');
		                    },
		                    drop: function () {
		                    	
		                        $('#drop').html(
		                            'In <b>' + this.series.name + '</b>, <b>' + this.category + '</b> was set to <b>' + Highcharts.numberFormat(this.y, 0) + '</b>');
		                    }
		                }
		            },
		            stickyTracking: false
		        },
		    },

		    tooltip: {
		        yDecimals: 2
		    },

		    series: [{
		    	data: [10].reverse(),
		        //draggableX: true,
		        draggableY: true,
		        dragMinY: 0,
		        type: 'column',
		        minPointLength: 2
		    }, {
		        draggableY: false,
		        dragMinY: 0,
		        type: 'column',
		        minPointLength: 2
		    }]
	 })


		
}

function addProjectionToCategory(category, amount){
	var urlGet = "<s:url value='/app/profile/expenses/expense'/>";
	$.ajax({
		type : "POST",
		url : urlGet,
		data : "category_name=" + category + "&amount=" + amount,
		success : function(response) {
			// we have the response
			if (response.status == "SUCCESS") {
				$('#expenseItem').modal('hide');
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
}


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
		<div>
		<button type="button" class="btn btn-success"
			onclick="addExpensesModal()">
			<i class="fa fa-plus-circle"></i> Dodaj wydatek
		</button>
		</div>
		<br/>
		<div id="chartExpenses" style="height: 400px"></div>
		<div id="drag"></div>
		<div id="drop"></div>
</div>
<br />
<div id="addExpensesModal"></div>		
</div>