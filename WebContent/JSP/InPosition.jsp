<%@page import="mode.Admin"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mode.Position"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			body{
				font-size: 18px;
			}
			#form{
				width: 870px;
				margin: 0px;
			}
			.col-sm-10{
				margin-left: 30px;
			}
		</style>
	</head>
<script>
			window.onload = function(){
				var butin = document.getElementById("butin");
				var innumber = document.getElementsByClassName("innumber");
				var sumnuber = 0;
				butin.onclick = function(){
					for(var i = 0;i<innumber.length;i++){
						if(innumber[i].value == ""){
						innumber[i].value = 0;
					}
						
						sumnuber = sumnuber + parseInt(innumber[i].value);
					}
					alert("入库总数量为："+sumnuber);
				}
		
			}
		</script>
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

<div id="content" style="text-align: center;">
			<h1>入库位置</h1>
       <div style="margin: 20px auto ;width: 800px;">
       	<form action="${pageContext.request.contextPath}/InOutS?op=in2" method="post">
			<input type="submit" id="butin" value="入库" class="btn btn-default btn-lg" style="margin-top: 10px; margin-left: 20px;" />
		
			<%List<Object[]> names = new ArrayList<Object[]>();
			int j = Integer.parseInt(request.getSession().getAttribute("inumber").toString());
			if(j > 10){
				j = 10;
			}
			
	for (int i = 0 ;i < j; i++){ %>
	    <div style="margin-top: 20px; width: 900px;">
	    仓库:
		<select class="form-control"  style="width: 120px; display: inline-block;" name="wear<%=i%>">
		
		<c:forEach items="${sessionScope.wears}" var="wear">
		<option value="${wear.getWearId()}">${wear.getWearName()}</option>
		</c:forEach>
		</select>
		仓库区域:
		<select class="form-control"  style="width: 120px; display: inline-block;"  name="area<%=i%>">
		<c:forEach items="${sessionScope.wearareas}" var="weararea">
		<option>${weararea.getWearAreaId()}</option>
		</c:forEach>
		</select>
		货位:
		<select class="form-control"  style="width: 120px; display: inline-block;"  name="posi<%=i%>">
			<c:forEach items="${sessionScope.wearpositions}" var="wearposition">
		<option>${wearposition.getWearPositionId()}</option>
		
		</c:forEach>
		</select>
		数量:
		<input type="number" class="innumber form-control" style="width: 120px; display: inline-block;"  name="number<%=i%>"/>
		<input type="checkbox" name="check<%=i%>">仓库已满
	
	</div>
	<%
	
	Object[] objects = {"wear"+i,"area"+i,"posi"+i,"number"+i,"check"+i};
	names.add(objects);
	} 
request.getSession().setAttribute("names", names);
	%>
	</form>
	</div>	
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