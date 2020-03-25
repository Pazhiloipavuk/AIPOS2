<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pageTitle" required="true" %>

<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<main>
    <table>
        <thead>
        <tr>
            <c:url var="tasks" value="/tasks"/>
            <td><a href="${tasks}">Tasks</a></td>

            <c:url var="descriptions" value="/descriptions"/>
            <td><a href="${descriptions}">Descriptions</a></td>

            <c:url var="users" value="/users"/>
            <td><a href="${users}">Users</a></td>

            <c:url var="comments" value="/comments"/>
            <td><a href="${comments}">Comments</a></td>
        </tr>
        </thead>
    </table>
    <jsp:doBody/>
</main>
</body>
</html>
