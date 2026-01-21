<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Topics</title>
</head>
<body>
<h1>Topics</h1>
<c:forEach items="${topics}" var="topic">
    ${topic}
</c:forEach>
</body>
</html>
