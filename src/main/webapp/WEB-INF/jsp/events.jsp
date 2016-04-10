<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
<h2>Spring MVC and List Example</h2>

<c:if test="${not empty events}">

    <ul>
        <c:forEach var="event" items="${events}">
            <li>${event}</li>
        </c:forEach>
    </ul>

</c:if>
</body>
</html>