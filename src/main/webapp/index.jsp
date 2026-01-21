<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Card loader</title>
</head>
<body>
<html:form method="GET" action="/cards">
    value: <html:text property="cardSubstr"/>
    <html:submit value="find"/>
</html:form>
</body>
</html>