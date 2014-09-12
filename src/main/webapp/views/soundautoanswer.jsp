<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta http-equiv="refresh" content="2">
<title>问题过滤及自动回答</title>


<link rel="stylesheet" type="text/css"
	href="/css/semantic.css">
<link rel="stylesheet" type="text/css" href="/css/homepage.css">

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.js"></script>
<script src="/js/semantic.js"></script>
<script src="/js/homepage.js"></script>

</head>
<body id="home">

	<h4 class="ui inverted red block header" >语音自动回答功能展示<a href="/qademo/home" style='float:right'>首页</a></h4>
	<form action="/qademo/list" method="get">
	
	<table class="ui table segment">
		
			<thead>
				<tr>
					<th>请输入问题</th>
					<th>过滤分类结果</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<div class="field">							
							<textarea rows="10" cols="80" name="question" >收到的语音为题为： ${question} </textarea>
						</div>							
					</td>
					<td>
						<div class="field">							
							<textarea rows="10" cols="80" name="result">${result}</textarea>						
						</div>							
					</td>
				</tr>		
				<tr>
					<td>
						<div class="field">
						<input type="submit" value="提交">
						</div>
					</td>
				</tr>
			<tbody>
		</table>
		</form>

	<div class="ui section divider"></div>
		<table class="ui table segment" border="1">
			<thead>
				<tr>
					<th colspan="3">匹配结果</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>状态</td>
					<td>问题</td>
					<td>答案</td>
				</tr>
				
				<c:forEach var="item" items="${array}" varStatus="status"> 
				<tr>
				 <td>${item.status}</td><td>${item.question}</td><td>${item.answer}</td>
				 </tr>
				 </c:forEach>

			</tbody>
		</table>
</body>
</html>