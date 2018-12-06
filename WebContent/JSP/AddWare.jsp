<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@page import="mode.Admin"%>
<!DOCTYPE html>
<html>

	<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
	<link href="${pageContext.request.contextPath}/css/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-accordion-menu.js" type="text/javascript"></script>
	<meta charset="UTF-8">
	<title></title>

		<style>
			.wareAdd{
				display: block;
				width: 950px;
				height: 550px;
				position: fixed;
				margin: 30px 300px;
			}
			#ware1{
			}
			#ware2{
				display: none;
			}
			#ware3{
				display: none;
			}
		</style>
	</head>

	<body>
		<div id="top">
		<img   style="float: left; margin: 34px; margin-left: 234px;" /> 
		<span id="b">欢迎登陆ACBY进销存系统！</span>
		<div id="Tright">
		<%Object object = session.getAttribute("admin");
		Admin admin = null;
		if(object != null){
			admin = (Admin)object;
		}
		else{
			request.getRequestDispatcher("Index.jsp").forward(request, response);
		}
		%>
		Hi! &ensp;&ensp; <span style="color: yellow;"><%=admin.getAdminName() %>&ensp; &ensp;</span>你好
		</div>
	</div>
		<div class="menu">
<div id="jquery-accordion-menu" class="jquery-accordion-menu red">
		 
		<ul id="demo-list">
		 
		   <li><a class="active" href="#">商品信息管理</a>
		   	<ul class="submenu">
					<li><a href="${pageContext.request.contextPath}/JSP/Add.jsp">添加商品信息</a></li>
					<li><a href="${pageContext.request.contextPath}/JSP/Display.jsp">浏览商品信息</a></li>
						</ul></li>
		   	 <li><a>商品类型</a>
				<ul class="submenu">
					<li><a href="${pageContext.request.contextPath}/JSP/Type.jsp">商品类型管理</a></li>
					<li><a href="AddType.jsp">商品类型添加</a></li>
				</ul>
			</li>
			<li><a>商品状态管理</a>
				<ul class="submenu">
					<li><a href="${pageContext.request.contextPath}/ShelfDownS?op=shelf">下架商品</a></li>
					<li><a href="${pageContext.request.contextPath}/ShelfDownS?op=down">上架商品</a></li>
				</ul>
			</li>
			  <li><a href="${pageContext.request.contextPath}/ProduceS?op=distype2&path=In">入库</a></li>
            <li><a href="${pageContext.request.contextPath}/ProduceS?op=distype2&path=Out">出库</a></li>
			<li><a>仓库管理</a>
				<ul class="submenu">
					 
					<li><a href="${pageContext.request.contextPath}/PositionS?op=disadmin">仓库添加</a></li>
					<li><a href="${pageContext.request.contextPath}/JSP/AddArea.jsp">仓库区域添加</a></li>
					<li><a href="${pageContext.request.contextPath}/JSP/AddDepot.jsp">仓位添加</a></li>
				</ul>
			</li>
			
			<li><a href="${pageContext.request.contextPath}/PositionS?op=position">仓库信息查询</a></li>
		</ul>

	</div>
</div>

	<div id="content">		
		<h1>仓库添加</h1>	
	<form action="${pageContext.request.contextPath}/PositionS?op=addwear" method="post">
		<span>仓库名称:</span>
		<input type="text" class="form-control" required="required" name="wear" placeholder="请输入仓库名称">
		

	
		<span>仓库地址:</span>
	<input type="text" class="form-control" required="required" name="address" placeholder="请输入仓库地址">
		

	
		<span>仓库描述:</span>
		
			<input type="text" class="form-control" required="required" name="size" placeholder="请输入仓库规格">

	
		<span>仓库管理员:</span>
		<select class="form-control" name="admin">
            
			<c:forEach items="${sessionScope.admins}" var="admin">
			<option value="${admin.getAdminId()}">${admin.getAdminName()}</option>
			</c:forEach>
			</select>
		

	<div class="form-group">
		<input type="submit" class="btn btn-lg btn-success" id="butadd1" value="添加"></input>
</div>


</form></div>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery("#jquery-accordion-menu").jqueryAccordionMenu();

		});

		$(function() {
			//顶部导航切换
			$("#demo-list li").click(function() {
				$("#demo-list li.active").removeClass("active")
				$(this).addClass("active");
			})
		})
	</script>
</body>

</html>