<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:main pageTitle="Comments">
    <table>
        <thead>
        <tr>
            <td>Content</td>
            <td>Description</td>
            <td>Author</td>
            <td>Action</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td>
                    <c:url var="commentUrl" value="/comment/${comment.id}"/>
                    <a href="${commentUrl}">${comment.content}</a>
                </td>
                <td>
                    <c:url var="descriptionUrl" value="/description/${comment.description.id}"/>
                    <a href="${descriptionUrl}">${comment.description.title}</a>
                </td>
                <td>
                    <c:url var="authorUrl" value="/user/${comment.author.id}"/>
                    <a href="${authorUrl}">${comment.author.username}</a>
                </td>
                <td>
                    <c:url var="deleteUrl" value="/comment/${comment.id}"/>
                    <form:form method="DELETE" action="${deleteUrl}" modelAttribute="comment">
                        <form:button type="submit">Delete</form:button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</tags:main>