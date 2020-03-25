<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:main pageTitle="Tasks">
    <table>
        <thead>
        <tr>
            <td>Name</td>
            <td>Description</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>
                    <c:url var="taskUrl" value="/task/${task.id}"/>
                    <a href="${taskUrl}">${task.name}</a>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${task.description.id}"/>
                    <a href="${descriptionUrl}">${task.description.title}</a>
                </td>
                <td>
                    <c:url var="deleteUrl" value="/task/${task.id}"/>
                    <form:form method="DELETE" action="${deleteUrl}" modelAttribute="task">
                        <form:button type="submit">Delete</form:button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>

        <c:url var="saveUrl" value="/tasks"/>
        <form:form method="PUT" modelAttribute="task" action="${saveUrl}">
            <tr>
                <td>
                    <form:input path="name"/>
                    <form:errors path="name" cssStyle="color: red"/>
                </td>
                <td><form:button type="submit">Save</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>
</tags:main>

