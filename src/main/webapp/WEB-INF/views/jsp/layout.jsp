<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/app/static/css/style.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/app/static/css/bootstrap.min.css" />" />
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
<div class="row">
  <div class="col-md-10 col-md-offset-1">
    <div class="row">
      <div class="col-md-9">
        <div id="content">
          <tiles:insertAttribute name="content" />
        </div>
      </div>
       <div class="col-md-3">
       	<div id="menu">
			<tiles:insertAttribute name="menu" />
    	</div>
      </div>
    </div>
  </div>
</div>
</body>
</html>