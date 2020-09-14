
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Why are you here?</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="alert alert-danger" >
   <div style="margin: 0 auto;width:100%;text-align: center">
       <strong>Danger! </strong><%=request.getParameter("msg")%>
   </div>
</div>
</body>
</html>
