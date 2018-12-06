<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
h1 {
	margin-top: 200px;
	display: block;
}

a {
	border: solid 1px plum;
	border-radius: 10px;
	padding: 8px 15px;
	font-size: 18px;
	font-weight: 800;
	margin: 50px auto;
	display: block;
	width: 60px
}

a:hover {
	cursor: pointer;
}

a:active {
	background: #204D74;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="text-align: center;">
	<!-- 判断是否成功 -->
	<%
		String msg = request.getAttribute("msg").toString();
		boolean msgv = (boolean) request.getAttribute("msgv");
	%>
	<c:if test="${msgv == 'true'}">
		<h1>操作成功！！</h1>
	</c:if>
	<c:if test="${msgv == 'false'}">
		<h1>操作失败！！</h1>
	</c:if>

	<!-- 点击返回后回到的页面 -->

	<!-- 点击返回后回到的添加类型 -->
	<c:if test="${msg == 'addtype'}">
		<a href="${pageContext.request.contextPath}/JSP/AddType.jsp">返回 </a>
	</c:if>
	<!-- 添加商品点击返回后回到的显示商品信息 -->
	<c:if test="${msg == 'addproduce'}">
		<a
			href="${pageContext.request.contextPath}/JSP/Add.jsp">返回
		</a>
	</c:if>
	<!-- 修改商品点击返回后回到的显示商品信息 -->
	<c:if test="${msg == 'modproduce'}">
		<a
			href="${pageContext.request.contextPath}/JSP/Display.jsp">返回
		</a>
	</c:if>
	<!-- 入库成功后 -->
	<c:if test="${msg == 'In'}">
		<h3>
			<%
				List<String> a = (List) request.getAttribute("val");
					for (String val : a) {
						out.print(val);
					}
			%>
		</h3>
		<br>
		<a
			href="${pageContext.request.contextPath}/ProduceS?op=distype2&path=In">返回
		</a>
	</c:if>

	<!-- 出库成功后 -->
	<c:if test="${msg == 'Out'}">
		<h3>
			<%
				List<String> a = (List) request.getAttribute("val");
					for (String val : a) {
						out.print(val);
					}
			%>
		</h3>
		<br>
		<a
			href="${pageContext.request.contextPath}/ProduceS?op=distype2&path=Out">返回
		</a>
	</c:if>
	<!-- 上架商品成功 -->
	<c:if test="${msg == 'shelf'}">
		<h3>
			<%
				List<String> a = (List) request.getAttribute("vals");
					for (String val : a) {
						out.print(val);%><br/><% 
					}
			%>
		</h3>
		<br>
		<a
			href="${pageContext.request.contextPath}/ShelfDownS?op=down">返回
		</a>
	</c:if>
	<!-- 下架商品成功 -->
	<c:if test="${msg == 'down'}">
		<h3>
			<%
				List<String> a = (List) request.getAttribute("vals");
					for (String val : a) {
						out.print(val);%><br/><% 
					}
			%>
		</h3>
		<br>
		<a
			href="${pageContext.request.contextPath}/ShelfDownS?op=shelf">返回
		</a>
	</c:if>
	<!--添加仓库 -->
	<c:if test="${msg == 'addwear'}">
		<a
			href="${pageContext.request.contextPath}/PositionS?op=disadmin">返回
		</a>
	</c:if>
	<!--添加区域 -->
	<c:if test="${msg == 'addarea'}">
	<a
			href="${pageContext.request.contextPath}/PositionS?op=disadmin">上一步
		</a>
		<a
			href="${pageContext.request.contextPath}/JSP/AddArea.jsp">返回继续添加
		</a>
		<a
			href="${pageContext.request.contextPath}/JSP/AddDepot.jsp">下一步
		</a>
	</c:if>
	<!--添加仓位 -->
	<c:if test="${msg == 'addposition'}">
	<a
			href="${pageContext.request.contextPath}/JSP/AddArea.jsp">上一步
		</a>
		<a
			href="${pageContext.request.contextPath}/JSP/AddDepot.jsp">返回继续添加
		</a>
		
	</c:if>
	
</body>
</html>