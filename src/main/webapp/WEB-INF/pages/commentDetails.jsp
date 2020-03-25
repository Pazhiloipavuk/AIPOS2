<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:main pageTitle="${comment.id}">
    <table>
        <thead>
        <tr>
            <td>Content</td>
            <td>Description</td>
            <td>Author</td>
            <td>Save</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:url var="saveUrl" value="/comment/${comment.id}"/>
            <form:form method="PUT" modelAttribute="comment" action="${saveUrl}">
                <form:hidden path="id"/>
                <td>
                    <form:textarea path="content"/>
                    <form:errors path="content" cssStyle="color: red"/>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${comment.description.id}"/>
                    <a href="${descriptionUrl}">${comment.description.title}</a>
                    <form:hidden path="description"/>
                </td>
                <td>
                    <c:url var="authorUrl" value="/user/${comment.author.id}"/>
                    <a href="${authorUrl}">${comment.author.username}</a>
                    <form:hidden path="author"/>
                </td>
                <td><form:button type="submit">Update</form:button></td>
            </form:form>
        </tr>
        </tbody>
    </table>
</tags:main>