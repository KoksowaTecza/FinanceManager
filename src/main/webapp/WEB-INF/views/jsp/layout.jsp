<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value="/app/static/css/bootstrap.min.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/app/static/css/style.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/app/static/font-awesome/css/font-awesome.min.css" />" />
<script src="<c:url value="/app/static/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/app/static/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/app/static/js/script.js" />"></script>
<script src="https://maps.googleapis.com/maps/api/js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/highcharts-more.js"></script>
<script src="<c:url value="/app/static/js/draggable-points.js" />"></script>
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12 ">
				<div class="row">
					<div class="col-md-9 col-sm-12">
						<div id="content">
							<tiles:insertAttribute name="content" />
						</div>
					</div>
					<div class="col-md-3 md-hidden col-sm-12">
						<div id="menu">
							<tiles:insertAttribute name="menu" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<tiles:insertAttribute name="addings" />
</body>
</html>