<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

<title>维护白词库</title>


<link rel="stylesheet" type="text/css"
	href="../css/semantic.css">
<link rel="stylesheet" type="text/css" href="../css/homepage.css">

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.js"></script>
<script src="../js/semantic.js"></script>
<script src="../js/homepage.js"></script>

</head>
<body id="home">
	<h4 class="ui inverted blue block header" >白词库功能展示<a href="/qademo/home" style='float:right'>首页</a></h4>
	<!--  <div class="ui form segment" method="Post" action="/whiteword/add">-->
	<form action="/whiteword/add" method="Post">
	<table class="ui table segment">
		
			<thead>
				<tr>
					<th>待增加的白词，请选择</th>
				</tr>
			</thead>
			<tbody>			
				<tr>
					<td>					 
							<div class="field">
								法律政策：&nbsp 
								<c:forEach var="data" items="${policy}">
									<c:if test="${data!=null}">
                                	<input type="checkbox" name="policy" value="${data}">${data}
                            		</c:if>								
								</c:forEach>
							</div>
					</td>
				</tr>	
				<tr>
					<td>
							<div class="field">
								位置交通：&nbsp 
								<c:forEach var="data" items="${location}">
								<c:if test="${data!=null}">
								<input type="checkbox" name="location" value="${data}">${data}
								</c:if>	
								</c:forEach>
							</div>	
					</td>
				</tr>	
				<tr>
					<td>		
							<div class="field">
								风&nbsp &nbsp &nbsp &nbsp水：&nbsp 
								<c:forEach var="data" items="${fengshui}">
								<c:if test="${data!=null}">
								<input type="checkbox" name="fengshui" value="${data}">${data}
								</c:if>	
								</c:forEach>
							</div>	
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								房&nbsp &nbsp &nbsp &nbsp价：&nbsp 
								<c:forEach var="data" items="${price}">
								<c:if test="${data!=null}">
								<input type="checkbox" name="price" value="${data}">${data}
								</c:if>	
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								楼&nbsp &nbsp &nbsp &nbsp层：&nbsp 
								<c:forEach var="data" items="${floor}">
								<c:if test="${data!=null}">
								<input type="checkbox" name="floor" value="${data}">${data}
								</c:if>	
								</c:forEach>
							</div>
					</td>
				</tr>	
				<tr>
					<td>		
							<div class="field">
								户&nbsp &nbsp &nbsp &nbsp型：&nbsp 
								<c:forEach var="data" items="${housetype}">
								<c:if test="${data!=null}">
								<input type="checkbox" name="housetype" value="${data}">${data}
								</c:if>	
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								小区配套：&nbsp 
								<c:forEach var="data" items="${facilities}">								
								<c:if test="${data!=null}">
								<input type="checkbox" name="facilities" value="${data}">${data}
								</c:if>	
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								贷&nbsp &nbsp &nbsp &nbsp款：&nbsp 
								<c:forEach var="data" items="${loan}">
								<c:if test="${data!=null}">
								<input type="checkbox" name="loan" value="${data}">${data}
								</c:if>	
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								买房时机：&nbsp 
								<c:forEach var="data" items="${occasion}">
								<c:if test="${data!=null}">
								<input type="checkbox" name="occasion" value="${data}">${data}
								</c:if>	
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								开&nbsp 发&nbsp 商：&nbsp 
								<c:forEach var="data" items="${developers}">
								<c:if test="${data!=null}">
								<input type="checkbox" name="developers" value="${data}">${data}
								</c:if>	
								</c:forEach>
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
	<!--  </div>-->
	<div class="ui section divider"></div>
		<table class="ui table segment">
			<thead>
				<tr>
					<th>新增加的白词</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>					 
							<div class="field">
								法律政策：&nbsp 
								<c:forEach var="data" items="${policy1}">
									${data} &nbsp 
								</c:forEach>
							</div>
					</td>
				</tr>	
				<tr>
					<td>
							<div class="field">
								位置交通：&nbsp 
								<c:forEach var="data" items="${location1}">
								${data} &nbsp 
								</c:forEach>
							</div>	
					</td>
				</tr>	
				<tr>
					<td>		
							<div class="field">
								风&nbsp &nbsp &nbsp &nbsp水：&nbsp 
								<c:forEach var="data" items="${fengshui1}">
								${data} &nbsp 
								</c:forEach>
							</div>	
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								房&nbsp &nbsp &nbsp &nbsp价：&nbsp 
								<c:forEach var="data" items="${price1}">
								${data} &nbsp 
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								楼&nbsp &nbsp &nbsp &nbsp层：&nbsp 
								<c:forEach var="data" items="${floor1}">
								${data} &nbsp 
								</c:forEach>
							</div>
					</td>
				</tr>	
				<tr>
					<td>		
							<div class="field">
								户&nbsp &nbsp &nbsp &nbsp型：&nbsp 
								<c:forEach var="data" items="${housetype1}">
								${data} &nbsp 
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								小区配套：&nbsp 
								<c:forEach var="data" items="${facilities1}">
								${data} &nbsp 
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								贷&nbsp &nbsp &nbsp &nbsp款：&nbsp 
								<c:forEach var="data" items="${loan1}">
								${data} &nbsp 
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								买房时机：&nbsp 
								<c:forEach var="data" items="${occasion1}">
								${data} &nbsp 
								</c:forEach>
							</div>
					</td>
				</tr>
				<tr>
					<td>
							<div class="field">
								开&nbsp 发&nbsp 商：&nbsp 
								<c:forEach var="data" items="${developers1}">
								${data} &nbsp 
								</c:forEach>
							</div>												
					</td>
				</tr>					
			</tbody>
		</table>
</body>
</html>
