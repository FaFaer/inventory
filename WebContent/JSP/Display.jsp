<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <%@page import="mode.Admin"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html><head>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
	<link href="${pageContext.request.contextPath}/css/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-accordion-menu.js" type="text/javascript"></script>
	<meta charset="UTF-8">
	<title></title>
	</head>
	<style>
	table tr td{
	width:166px;
	}
	</style>
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
					<li><a href="${pageContext.request.contextPath}/JSP/Diaplay.jsp">浏览商品信息</a></li>
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

			<!--内容-->	
			<div id="content">
			<h1>浏览商品信息</h1>
				<div id="search">
					<form action="${pageContext.request.contextPath}/JSP/Add.jsp"  method="post">
					<input type="search" class="form-control" id="searchin"
						placeholder="请输入需要查询的商品编号、商品名称"
						style="width: 400px; display: inline-block;" /> 
						<input id="searchbut"
						class="btn btn-default btn-lg" style="margin-left: 20px;"
						type="button" value="查询" />
						<input class="btn btn-default btn-lg" id="add"
						type="submit" value="添加"  />
</form>
				</div>

		<div id="select" style="width: 850px;">
			<div id="topname">
				<span>商品条形码</span><span>商品名称</span><span>商品价格</span>
			</div>
			<table class="table-hover"
				style="width: 833px; margin: 0px; font-size: 16px;" id="sel">
			</table>
		</div>

	</div>
	
		

	 
	<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery("#jquery-accordion-menu").jqueryAccordionMenu();

		});

		$(function() {
			var xmlHttpReq = null;
			if (window.XMLHttpRequest) {
				xmlHttpReq = new XMLHttpRequest();
			} else {
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xmlHttpReq.open("POST","${pageContext.request.contextPath}/ProduceS?op=disproduce",true);
			xmlHttpReq.onreadystatechange = function() {
				if (xmlHttpReq.readyState == 4) {
					var d1Obj = document.getElementById("sel");
					d1Obj.innerHTML = xmlHttpReq.responseText;
						}
						}
					xmlHttpReq.setRequestHeader("Content-type","application/x-www-form-urlencoded");
					xmlHttpReq.send();
			
			
			
			
			
			
			$('#searchbut').on('click', function() {
         $('table tbody tr').hide()
             .filter(":contains('" + ($('#searchin').val()) + "')")
             .show();
     })
     
			//顶部导航切换
			$("#demo-list li").click(function() {
				$("#demo-list li.active").removeClass("active")
				$(this).addClass("active");
			})
			
		})
	</script>

</body>

</html>