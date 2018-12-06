<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@page import="mode.Admin"%>
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
	</head>

	<body><div id="top">
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
			<!--内容-->	
			
			<h1>修改商品信息</h1>
			<form action="${pageContext.request.contextPath}/ProduceS?op=modproduce" method="post">
			<div class="form-horizontal" id="form">
					<div class="form-group">
						<label for="Name" class="col-sm-2 control-label">商品图片:</label>
						<a></a>
						<div class="col-sm-10">
							<img src="/pc/${requestScope.produce.getProducePic()}" style="width: 150px; height:150px;" />
						</div>
					</div>
					<div class="form-group">
						<label for="Name" class="col-sm-2 control-label">商品名称:</label>
						<a></a>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="Name" placeholder="${requestScope.produce.getProduceName()}">
						</div>
					</div>

					<div class="form-group">
						<label for="Code" class="col-sm-2 control-label">商品编号:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="Code" placeholder="${requestScope.produce.getProduceCode()}">
						</div>
					</div>

					<div class="form-group">
						<label for="Price" class="col-sm-2 control-label">商品价格:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name="Price" placeholder="${requestScope.produce.getProducePrice()}">
						</div>
					</div>
					
<div class="form-group">
						<input type="submit" class="btn btn-default" id="btnY" value="确认修改"/>
						&emsp;&emsp;&emsp;&emsp;
						<button class="btn btn-default" id="btnN">取消</button>
					</div>
				</div>

		</form>
				
			</div>
 
		
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