<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:main pageTitle="${user.username}">
    <table>
        <thead>
        <tr>
            <td>Username</td>
            <td>Save</td>
        </tr>
        </thead>
        <tbody>
        <c:url var="saveUrl" value="/user/${user.id}"/>
        <form:form method="PUT" modelAttribute="user" action="${saveUrl}">
            <tr>
                <form:hidden path="id"/>
                <td>
                    <form:input path="username"/>
                    <form:errors path="username" cssStyle="color: red"/>
                </td>
                <td><form:button type="submit">Update</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>

    <h2>Descriptions of user</h2>
    <table>
        <thead>
        <tr>
            <td>Title</td>
            <td>Content</td>
            <td>Task</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="description" items="${user.descriptions}">
            <tr>
                <td>
                    <c:url var="descriptionUrl" value="/description/${description.id}"/>
                    <a href="${descriptionUrl}">${description.title}</a>
                </td>
                <td>${description.content}</td>
                <td>
                    <c:url var="taskUrl" value="/task/${description.task.id}"/>
                    <a href="${taskUrl}">${description.task.name}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h2>Comments of user</h2>
    <table>
        <thead>
        <tr>
            <td>Content</td>
            <td>Description</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="comment" items="${user.comments}">
            <tr>
                <td>
                    <c:url var="commentUrl" value="/comment/${comment.id}"/>
                    <a href="${commentUrl}">${comment.content}</a>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${comment.description.id}"/>
                    <a href="${descriptionUrl}">${comment.description.title}</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tags:main>
