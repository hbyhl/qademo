<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" cellpadding="10">
<tr><td>status</td><td>question</td><td>answer</td></tr>
<c:forEach var="item" items="${array}" varStatus="status"> 
<tr>
   <td>${item.status}</td><td>${item.question}</td><td>${item.answer}</td>
   
 </tr>
</c:forEach>
 </table>

</body>
</html>