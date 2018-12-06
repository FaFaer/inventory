<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>登陆</title>
   <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
   <style>
   	#he{
   		display: inline-block;
   		position: absolute;
   		left: 95px;
   		top: 66px;
   		
   	}
   	#he h1{
   		color: #ADADAD;
   	}
   </style>
</head>

<body>
  <main> 
   	<div id="he"><h1>欢迎进入进销存管理系统!</h1></div>
   	<div style="display: inline-block; position: absolute; top: 66px; right: 95px;"> <a href="regist.jsp" style="color:#ADADAD;">注册</a></div>
        <form class="form" action="${pageContext.request.contextPath}/ManageS?op=login" method="post">
           <div class="form__cover"></div>
            <div class="form__content">
                <h1>登陆</h1>
                <c:if test="${sessionScope.va == '0'}">
                <h3>用户名或密码不正确，请重新登录！</h3>
                </c:if>
                <div class="styled-input">
                    <input type="text" class="styled-input__input" name="Username">
                    <div class="styled-input__placeholder">
                        <span class="styled-input__placeholder-text">Username</span>
                    </div>
                    <div class="styled-input__circle"></div>
                </div><div class="styled-input">
                     <input type="text" class="styled-input__input" name="Password">
                    <div class="styled-input__placeholder">
                        <span class="styled-input__placeholder-text">Password</span>
                    </div>
                    <div class="styled-input__circle"></div>
                </div>
                <button type="submit" class="styled-button" id="login">
                    <span class="styled-button__real-text-holder">
                        <span class="styled-button__real-text">登陆</span>
                        <span class="styled-button__moving-block face">
                            <span class="styled-button__text-holder">
                                <span class="styled-button__text">登陆</span>
                            </span>
                        </span><span class="styled-button__moving-block back">
                            <span class="styled-button__text-holder">
                                <span class="styled-button__text">登陆</span>
                            </span>
                        </span>
                    </span>
                </button>
            </div>

        </form>
    </main>
  
    <script src="${pageContext.request.contextPath}/js/index.js"></script>

</body>
</html>
