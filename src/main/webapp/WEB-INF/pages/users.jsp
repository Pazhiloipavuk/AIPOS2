<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:main pageTitle="Users">
    <table>
        <thead>
        <tr>
            <td>Username</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                    <c:url var="userUrl" value="/user/${user.id}"/>
                    <a href="${userUrl}">${user.username}</a>
                </td>
                <td>
                    <c:url var="deleteUrl" value="/user/${user.id}"/>
                    <form:form method="DELETE" action="${deleteUrl}" modelAttribute="user">
                        <form:button type="submit">Delete</form:button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        <c:url var="saveUrl" value="/users"/>
        <form:form method="PUT" modelAttribute="user" action="${saveUrl}">
            <tr>
                <td>
                    <form:input path="username"/>
                    <form:errors path="username" cssStyle="color: red"/>
                </td>
                <td><form:button type="submit">Save</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>
</tags:main>