<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<script>
var chart;
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
		url : " <s:url value="/app/profile/revenues/summary"/> ",
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
	for(var i = 0; i<response.length; i++){
		category[i] = response[i].cat_name;
		amount[i] = response[i].sum;
	}
	console.log("category "+category);
	chart.xAxis[0].update({categories:category});
	chart.series[0].update({data:amount});
}



function drawChart(){
	 chart = new Highcharts.Chart({

		    chart: {
		        renderTo: 'chartRevenue',
		        animation: false
		    },
		    
		    title: {
		        text: 'Twoje przychody'
		    },

		    xAxis: {
		
		    },

		    tooltip: {
		        yDecimals: 2
		    },

		    series: [{
		        draggableY: false,
		        dragMinY: 0,
		        type: 'column',
		        minPointLength: 2
		    }]

		});
}



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
		<div>
		<button type="button" class="btn btn-success"
			onclick="addRevenueElement()">
			<i class="fa fa-plus-circle"></i> Dodaj przychod
		</button>
		</div>
		<br/>
		<div id="chartRevenue" style="height: 400px"></div>
		<div id="drag"></div>
		<div id="drop"></div>
</div>
<br />
<div id="addRevenueModal"></div>		
</div>