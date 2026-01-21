<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Cards</title>
    <style>
      body {
        font-family: Arial, sans-serif;
      }
      .card {
        border: 1px solid #ccc;
        padding: 12px;
        margin-bottom: 10px;
        border-radius: 4px;
      }
      .topic {
        font-weight: bold;
        color: #555;
      }
      .question {
        margin-top: 6px;
        font-weight: bold;
      }
      .answer {
        margin-top: 4px;
      }
    </style>
</head>
<body>
<h1>Cards</h1>

<c:choose>
    <c:when test="${empty cards}">
        <p>No cards are available.</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="card" items="${cards}">
            <div class="card">
                <div class="topic">
                    Topic: ${card.topicDto}
                </div>
                <div class="question">
                    Q: ${card.question}
                </div>
                <div class="answer">
                    A: ${card.answer}
                </div>
            </div>
        </c:forEach>
    </c:otherwise>
</c:choose>

</body>
</html>

