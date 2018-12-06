<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@page import="mode.Admin"%>
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

</head>
<style>
table tr td {
	display: inline-block;
	width: 290px;
	height: 50px;
	font-size: 20px;
	border: solid 0px !important;
	line-height: 50px;
}

.form-control {
	display: inline-block;
	width: 150px;
}

select {
	margin: 0px 74px;
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

						<li><a
							href="${pageContext.request.contextPath}/PositionS?op=disadmin">仓库添加</a></li>
						<li><a
							href="${pageContext.request.contextPath}/JSP/AddArea.jsp">仓库区域添加</a></li>
						<li><a
							href="${pageContext.request.contextPath}//JSP/AddDepot.jsp">仓位添加</a></li>
					</ul></li>

				<li><a
					href="${pageContext.request.contextPath}/PositionS?op=position">仓库信息查询</a></li>
			</ul>

		</div>
	</div>
	<div id="content">
		<h1>仓库管理</h1>
		<select class="form-control" id="sel1" name="sel1"
			onchange="getArea()">
			<c:forEach items="${sessionScope.wears}" var="wear">
				<option value="${wear.getWearId()}">${wear.getWearId()}号${wear.getWearName()}</option>
			</c:forEach>
		</select> <select class="form-control" id="sel2">
			<option></option>

		</select> <select class="form-control" id="sel3">
			<option></option>
		</select>

		<div
			style="overflow: scroll; overflow-x: hidden; width: 911px; margin: 0px auto; height: 520px;">
			<table class="table-hover" id="sel4">
				
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
			$("#sel1")
			.change(
					function() {
						
						var sel1v = sel1.value;
						xmlHttpReq
								.open(
										"POST",
										"${pageContext.request.contextPath}/PositionS?op=disposition&wera=false&area=true",
										true);
						xmlHttpReq.onreadystatechange = function() {
							if (xmlHttpReq.readyState == 4) {
								var d1Obj = document
										.getElementById("sel2");
								d1Obj.innerHTML = xmlHttpReq.responseText;
							}
						}
						xmlHttpReq.setRequestHeader("Content-type",
								"application/x-www-form-urlencoded");
						xmlHttpReq.send("sel1=" + encodeURI(sel1v));

					})	
			$("#sel2")
					.change(
							function() {
								
								var sel2v = sel2.value;
								xmlHttpReq
										.open(
												"POST",
												"${pageContext.request.contextPath}/PositionS?op=position1",
												true);
								xmlHttpReq.onreadystatechange = function() {
									if (xmlHttpReq.readyState == 4) {
										var d1Obj = document
												.getElementById("sel3");
										d1Obj.innerHTML = xmlHttpReq.responseText;
									}
								}
								xmlHttpReq.setRequestHeader("Content-type",
										"application/x-www-form-urlencoded");
								xmlHttpReq.send("sel2=" + encodeURI(sel2v));

							})
							$("#sel3")
					.change(
							function() {
								
								var sel3v = sel3.value;
								xmlHttpReq
										.open(
												"POST",
												"${pageContext.request.contextPath}/PositionS?op=position2",
												true);
								xmlHttpReq.onreadystatechange = function() {
									if (xmlHttpReq.readyState == 4) {
										var d1Obj = document
												.getElementById("sel4");
										d1Obj.innerHTML = xmlHttpReq.responseText;
									}
								}
								xmlHttpReq.setRequestHeader("Content-type",
										"application/x-www-form-urlencoded");
								xmlHttpReq.send("sel3=" + encodeURI(sel3v));

							})
			//顶部导航切换
			$("#demo-list li").click(function() {
				$("#demo-list li.active").removeClass("active")
				$(this).addClass("active");
			});

			var sel1 = document.getElementById("sel1");
			var sel2 = document.getElementById("sel2");
			var sel3 = document.getElementById("sel3");
			sel1.onchange = function() {
				$('table tbody tr').hide();

				$('table tbody tr td:nth-child(1)').filter(
						":contains(" + ($('#sel1').val()) + ")").parent('.a1')
						.show();
			}
			sel2.onchange = function() {
				$('table tbody tr').hide();

				$('table tbody tr td:nth-child(2)').filter(
						":contains(" + ($('#sel2').val()) + ")").parent('.a1')
						.show();
			}
			sel3.onchange = function() {
				$('table tbody tr').hide();

				$('table tbody tr td:nth-child(3)').filter(
						":contains(" + ($('#sel3').val()) + ")").parent('.a1')
						.show();
			}
		})
	</script>
</body>

</html>