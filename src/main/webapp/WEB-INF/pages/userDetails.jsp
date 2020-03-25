<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:main pageTitle="${description.title}">

    <table>
        <thead>
        <tr>
            <td>Title</td>
            <td>Content</td>
            <td>Task</td>
            <td>Author</td>
        </tr>
        </thead>
        <tbody>
        <c:url var="saveUrl" value="/description/${description.id}"/>
        <form:form method="PUT" modelAttribute="description" action="${saveUrl}">
            <tr>
                <form:hidden path="id"/>
                <td>
                    <form:input path="title"/>
                    <form:errors path="title" cssStyle="color: red"/>
                </td>
                <td>
                    <form:textarea path="content"/>
                    <form:errors path="content" cssStyle="color: red"/>
                </td>
                <td>
                    <form:select path="task.id" multiple="false">
                        <form:option value="${description.task.id}" label="${description.task.name}"/>
                        <form:options items="${tasks}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="task.id" cssStyle="color: red"/>
                </td>
                <td>
                    <form:select path="author.id" multiple="false">
                        <form:option value="${description.author.id}" label="${description.author.username}"/>
                        <form:options items="${users}" itemValue="id" itemLabel="username"/>
                    </form:select>
                    <form:errors path="author.id" cssStyle="color: red"/>
                </td>
                <td><form:button type="submit">Update</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>


    <h2>Comments of description</h2>
    <table>
        <thead>
        <tr>
            <td>Content</td>
            <td>Author</td>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not description.comments.isEmpty()}">
            <c:forEach var="comment" items="${description.comments}">
                <tr>
                    <td>
                            ${comment.content}
                    </td>
                    <td>
                        <c:url var="authorUrl" value="/user/${comment.author.id}"/>
                        <a href="${authorUrl}">${comment.author.username}</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

        <c:url var="addCommentUrl" value="/comments"/>
        <form:form method="PUT" modelAttribute="comment" action="${addCommentUrl}">
            <tr>
                <td>
                    <form:textarea path="content"/>
                    <form:errors path="content" cssStyle="color: red"/>
                </td>
                <form:hidden path="description.id" value="${description.id}"/>
                <td>
                    <form:select path="author.id" multiple="false">
                        <form:option value="-1" label="---Select author---"/>
                        <form:options items="${users}" itemValue="id" itemLabel="username"/>
                    </form:select>
                    <form:errors path="author.id" cssStyle="color: red"/>
                </td>
                <td><form:button type="submit">Add comment</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>

</tags:main>

