<%@ page isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:main pageTitle="Descriptions">
    <table>
        <thead>
        <tr>
            <td>Title</td>
            <td>Content</td>
            <td>Task</td>
            <td>Author</td>
            <td>Action</td>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="description" items="${descriptions}">
            <tr>
                <td>
                    <c:url var="descriptionUrl" value="/description/${description.id}"/>
                    <a href="${descriptionUrl}">${description.title}</a>
                </td>
                <td>
                        ${description.content}
                </td>
                <td>
                    <c:url var="taskUrl" value="/task/${description.task.id}"/>
                    <a href="${taskUrl}">${description.task.name}</a>
                </td>
                <td>
                    <c:url var="authorUrl" value="/user/${description.author.id}"/>
                    <a href="${authorUrl}">${description.author.username}</a>
                </td>
                <td>
                    <c:url var="deleteUrl" value="/description/${description.id}"/>
                    <form:form method="DELETE" action="${deleteUrl}" modelAttribute="description">
                        <form:button type="submit">Delete</form:button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>

        <c:url var="saveUrl" value="/descriptions"/>
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
                        <form:option value="-1" label="--- Select ---"/>
                        <form:options items="${tasks}" itemValue="id" itemLabel="name"/>
                    </form:select>
                    <form:errors path="task.id" cssStyle="color: red"/>
                </td>
                <td>
                    <form:select path="author.id" multiple="false">
                        <form:option value="-1" label="--- Select ---"/>
                        <form:options items="${users}" itemValue="id" itemLabel="username"/>
                    </form:select>
                    <form:errors path="author.id" cssStyle="color: red"/>
                </td>
                <td><form:button type="submit">Save</form:button></td>
            </tr>
        </form:form>
        </tbody>
    </table>
</tags:main>
