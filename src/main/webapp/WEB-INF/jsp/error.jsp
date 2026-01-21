<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isErrorPage="true" %>

<html>
<head>
    <title>Error</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #ffdddd;
      }

      .error-box {
        width: 70%;
        margin: 100px auto;
        padding: 20px;
        border: 1px solid #cc0000;
        background-color: #fff0f0;
      }

      .error-title {
        color: #cc0000;
        font-size: 20px;
        margin-bottom: 10px;
      }

      .error-message {
        font-size: 14px;
      }
    </style>
</head>

<body>

<div class="error-box">
    <div class="error-title">
        Произошла ошибка
    </div>

    <div class="error-message">
        ${errorMessage}
    </div>

    <br/>

    <a href="/">Вернуться на главную</a>
</div>

</body>
</html>
