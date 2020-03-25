<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:main pageTitle="${task.name}">
    <table>
        <thead>
        <tr>
            <td>Name</td>
            <td>Description</td>
            <td>Update</td>
        </tr>
        </thead>
        <tbody>
        <c:url var="saveUrl" value="/task/${task.id}"/>
        <form:form method="PUT" modelAttribute="task" action="${saveUrl}">
            <tr>
                <form:hidden path="id"/>
                <td>
                    <form:input path="name"/>
                    <form:errors path="name" cssStyle="color: red"/>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${task.description.id}"/>
                    <a href="${descriptionUrl}">${task.description.title}</a>
                </td>
                <td><form:button type="submit">Update</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>
</tags:main>

