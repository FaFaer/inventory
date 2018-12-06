<%@page import="java.util.ArrayList"%>
<%@page import="mode.Position"%><%@page import="mode.Admin"%>
<%@page import="java.util.List"%>
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
<style>
body {
	font-size: 18px;
}

.form-group {
	float: left;
	margin-left: 10px;
}

.form-group select {
	width: 80px;
	display: inline-block;
}
</style>
</head>
<script>
	window.onload = function() {
		var btnout = document.getElementById("btnout");
		var innumber = document.getElementsByClassName("innumber");
		var sumnuber = 0;
		btnout.onclick = function() {
			for (var i = 0; i < innumber.length; i++) {
				if (innumber[i].value == "") {
					innumber[i].value = 0;
				}
				sumnuber = sumnuber + parseInt(innumber[i].value);

			}
			alert("出库总数量为：" + sumnuber);
		}
	}
</script>


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
						<li><a href="${pageContext.request.contextPath}/ShelfDownS?op=shelf">下架商品</a></li>
						<li><a href="${pageContext.request.contextPath}/ShelfDownS?op=down">上架商品</a></li>
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
	<h1>出库仓位选择</h1>
	<form action="${pageContext.request.contextPath}/InOutS?op=out3"
		method="post">
		<div
			style="height: 570px; width: 920px; border: solid 1px #1E90FF; border-radius: 5px; margin: 10px auto; overflow: scroll;">
			<%
				List<Object[]> names = new ArrayList<Object[]>();
				List<Position> positions = new ArrayList<Position>();
				positions = (List<Position>)request.getAttribute("positions");
				for(int i = 0;i<positions.size();i++){
				Position position = positions.get(i);
			%>
				<div class="form-group">
					仓库: <select class="form-control" name="wearId<%=i%>">
						<option><%=position.getWearId() %></option>
					</select> 
					仓库区域: <select class="form-control"
						name="areaId<%=i%>"><option><%=position.getAreaId() %></option></select>
						货位: <select  class="form-control" name="positionId<%=i%>" > <option><%=position.getPositionId()%></option></select>
						商品出库数量:<input
						type="number" name="positioninNumber<%=i%>"
						max="<%=position.getPositioninNumber()%>"
						placeholder="总库存：<%=position.getPositioninNumber()%>"
						class="innumber form-control"
						style="display: inline-block; width: 200px;" />
						<input type="checkbox" style="margin-left: 10px;" name="check<%=i%>">出库


					<%
		Object[] objects = {"wearId"+i,"areaId"+i, "positionId"+i, "positioninNumber"+i, "check"+i};
 		names.add(objects);}
 %>
				</div>
			<%
				request.getSession().setAttribute("names", names);
			%>
		</div>

		<input type="submit" class="btn btn-success btn-lg" value="出库"
			id="btnout"/>
	</form>



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