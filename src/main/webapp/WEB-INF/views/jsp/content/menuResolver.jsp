<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasRole('ROLE_SPITTER')">
<jsp:include page="menu.jsp" />
</sec:authorize>
<sec:authorize access="isAnonymous()">
<jsp:include page="signUpForm.jsp" />
</sec:authorize>


