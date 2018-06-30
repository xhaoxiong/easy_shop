<%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/4
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%
    if (session.getAttribute("userinfo") != null) {
        response.sendRedirect("/index.jsp");
    }

%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
</head>
<body>
<div class="width100">
    <div class="width1200 center_yh hidden_yh" style="height:181px">
        <a href="#" class="left_yh"><img src="static/images/logo.png"></a>
        <a href="/register.jsp" class="right_yh font16 c_33" style="line-height:181px;">还没有账号，<font
                class="red">马上去注册></font></a>
    </div>
</div>
<div class="width100 hidden_yh" style="height:573px;background:url(static/images/bj.jpg) no-repeat center">
    <div class="width1200 center_yh hidden_yh" style="margin-top:70px">
        <div class="right_yh bj_fff" style="width:408px;height:438px;">
            <h3 class="tcenter font30 c_33" style="font-weight:100;margin-top:36px;margin-bottom:36px;">账户登录</h3>
            <div class="center_yh hidden_yh" style="width:336px;">
                <div class="width100 box-sizing hidden_yh"
                     style="height:44px;border:1px solid #c9c9c9;margin-bottom:34px;">
                    <img src="static/images/rw.jpg" class="left_yh" width="42" height="42">
                    <input type="text" placeholder="请输入用户名或手机号" name="username"
                           style="border:0;width:292px;height:42px;font-size:16px;text-indent:22px;" id="user_name">
                </div>
                <div class="width100 box-sizing hidden_yh"
                     style="height:44px;border:1px solid #c9c9c9;margin-bottom:14px;">
                    <img src="static/images/pass.jpg" class="left_yh" width="42" height="42">
                    <input type="password" placeholder="请输入密码" name="password"
                           style="border:0;width:292px;height:42px;font-size:16px;text-indent:22px;" id="password">
                </div>
                <p class="width100 tright font16" style="margin-bottom:26px;"><a href="#"
                                                                                 style="color:#4585dd;">忘记密码?</a>
                </p>
                <input type="submit" value="登录" onclick="Login()" class="center_yh"
                       style="width:100%;height:43px;font-size:16px;background:#dd4545;outline:none;border:0;color:#fff;cursor:pointer;">
            </div>
        </div>
    </div>
</div>

<jsp:include   page="footer.jsp" flush="true"/>
</body>
<script src="static/js/jquery-3.2.1.js"></script>
<script>
    function Login() {
        username = $("#user_name").val();
        password = $("#password").val();
        console.log(username, password);
        $.ajax({
            url: "http://localhost:8080/user/login/validate",
            type: "post",
            data: {
                "username": username,
                "password": password
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                console.log(data);
                if (data.status == 10000) {
                    alert("登陆成功");
                    window.location.href = "http://localhost:8080/index.jsp"
                } else if (data.stackTrace == 10003) {
                    alert("该用户名已经注册")
                } else {

                    alert("登录失败");
                }
            },
            error: function () {
                alert("服务器错误")
            }
        })
    }

</script>
</html>
