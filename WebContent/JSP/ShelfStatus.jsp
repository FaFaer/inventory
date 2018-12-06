<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mode.Produce"%><%@page import="mode.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link href="${pageContext.request.contextPath}/css/jquery-accordion-menu.css" rel="stylesheet"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery-accordion-menu.js" type="text/javascript"></script>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
tr td {
	width: 273px;
}
span{
width: 277px;
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
						<li><a
							href="${pageContext.request.contextPath}/JSP/Add.jsp">添加商品信息</a></li>
						<li><a
							href="${pageContext.request.contextPath}/JSP/Display.jsp">浏览商品信息</a></li>
					</ul></li>
				<li><a>商品类型</a>
					<ul class="submenu">
						<li><a
							href="${pageContext.request.contextPath}/JSP/Type.jsp">商品类型管理</a></li>
						<li><a href="AddType.jsp">商品类型添加</a></li>
					</ul></li>
				<li><a>商品状态管理</a>
					<ul class="submenu">
						<li><a
							href="${pageContext.request.contextPath}/ShelfDownS?op=shelf">下架商品</a></li>
						<li><a
							href="${pageContext.request.contextPath}/ShelfDownS?op=down">上架商品</a></li>
					</ul></li>
				<li><a
					href="${pageContext.request.contextPath}/ProduceS?op=distype2&path=In">入库</a></li>
				<li><a
					href="${pageContext.request.contextPath}/ProduceS?op=distype2&path=Out">出库</a></li>
				<li><a>仓库管理</a>
					<ul class="submenu">

						<li><a href="${pageContext.request.contextPath}/PositionS?op=disadmin">仓库添加</a></li>
						<li><a href="${pageContext.request.contextPath}/JSP/AddArea.jsp">仓库区域添加</a></li>
						<li><a href="${pageContext.request.contextPath}/JSP/AddDepot.jsp">仓位添加</a></li>
					</ul></li>

				<li><a href="${pageContext.request.contextPath}/PositionS?op=position">仓库信息查询</a></li>
			</ul>

		</div>
	</div>

	<div id="content">
		<!--内容-->

		<h1>商品下架操作</h1>
		<!--这里加自己的页面-->
		<div id="search">
			<input type="search" id="searchin" class="form-control"
				placeholder="请输入需要查询的商品条形码"
				style="width: 400px; display: inline-block;" /> <input
				class="btn btn-default btn-lg" id="searchbut"
				style="margin-left: 20px;" type="button" value="查询" />

		</div>
<form action="${pageContext.request.contextPath}/ShelfDownS?op=producedown" method="post">
		<div id="select" style="width: 850px;">
			<div id="topname">
				<span>商品条形码</span><span>商品名称</span><span>下架</span>
			</div>
			<table class="table-hover" style="width: 830px; margin: 0px;">
				<%
				 List<Produce> produces = new ArrayList<Produce>();
				List<Object[]> names = new ArrayList<Object[]>();
				produces = (List<Produce>)request.getAttribute("produces");
				for(int i = 0;i < produces.size() ; i++){
					Produce produce = produces.get(i);
				%>
					<tr>
						<td style="display: none;"><input type="text"
							value="<%=produce.getProduceId()%>" name="produceId<%=i%>"></td>
						<td><%=produce.getProduceCode()%></td>
						<td><%=produce.getProduceName()%></td>
						<td><input type="checkbox" style="width: 30px; height: 30px;"
							name="produceCheck<%=i%>" /></td>
					</tr>
				<%
				Object[] name = {"produceId" + i,"produceCheck" + i ,"produceCode" + i};
				names.add(name);
				} 
				request.getSession().setAttribute("names", names);
				%>
			</table>
		</div>

		<input type="submit" class=" btn btn-lg btn-success" value="下架" />
		</form>
	</div>


	<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery("#jquery-accordion-menu").jqueryAccordionMenu();

		});

		$(function() {
			$('#searchbut').on(
					'click',
					function() {
						$('table tbody tr').hide().filter(
								":contains('" + ($('#searchin').val()) + "')")
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

