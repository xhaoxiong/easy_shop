<%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/20
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Hi长大 - 登录</title>

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arimo:400,700,400italic">
    <link rel="stylesheet" href="/static/css/fonts/linecons/css/linecons.css">
    <link rel="stylesheet" href="/static/css/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <link rel="stylesheet" href="/static/css/xenon-core.css">
    <link rel="stylesheet" href="/static/css/xenon-forms.css">
    <link rel="stylesheet" href="/static/css/xenon-components.css">
    <link rel="stylesheet" href="/static/css/xenon-skins.css">
    <link rel="stylesheet" href="/static/css/custom.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.0/style/weui.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.0.0/css/jquery-weui.min.css">

    <script src="/static/js/jquery-1.11.1.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/static/js/html5shiv.min.js"></script>
    <script src="/static/js/respond.min.js"></script>
    <![endif]-->


</head>
<body class="page-body login-page">


<div class="login-container">

    <div class="row">

        <div class="col-sm-6">

            <script type="text/javascript">
                jQuery(document).ready(function ($) {
                    // Reveal Login form
                    setTimeout(function () {
                        $(".fade-in-effect").addClass('in');
                    }, 1);

                });
            </script>

            <!-- Errors container -->
            <div class="errors-container">


            </div>

            <!-- Add class "fade-in-effect" for login form effect -->
            <form method="post" role="form" id="login" class="login-form fade-in-effect">

                <div class="login-header">
                    <img src="/static/img/logo.png" alt="" width="200"/>
                    <p>长沙学院数学与计算机科学系-软件工程实训</p>
                </div>


                <div class="form-group">
                    <input type="text" class="form-control input-dark" placeholder="用户名" name="username" id="username"/>
                </div>

                <div class="form-group">
                    <input type="password" class="form-control input-dark" placeholder="密码" name="password"
                           id="password"/>
                </div>

                <div class="form-group">
                    <button type="button" class="btn btn-dark  btn-block text-left" onclick="login()">
                        <i class="fa-lock"></i>
                        登录
                    </button>
                </div>


            </form>


        </div>

    </div>

</div>

<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/TweenMax.min.js"></script>
<script src="/static/js/resizeable.js"></script>
<script src="/static/js/joinable.js"></script>
<script src="/static/js/xenon-api.js"></script>
<script src="/static/js/xenon-toggles.js"></script>
<script src="/static/js/jquery-validate/jquery.validate.min.js"></script>
<script src="/static/js/toastr/toastr.min.js"></script>

<script src="/static/js/xenon-custom.js"></script>


<script src="https://cdn.bootcss.com/jquery-weui/1.0.0/js/jquery-weui.min.js"></script>
<script>
    function login() {
        username = $('#username').val();
        password = $('#password').val();
        if (!password || !username) {
            $.toast("内容填写不完整", "cancel");
            return
        }
        $.showLoading("登录中...");
        $.ajax({
            url: 'http://127.0.0.1:8080/admin/login/validate/servlet',
            type: 'post',
            data: {
                'username': username,
                'password': password

            },   
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                $.hideLoading();
                if (data.status == 10000) {
                    location.href = 'http://127.0.0.1:8080/admin/main.jsp';
                } else if (data.status == 10002) {
                    $.toast("用户名或密码错误", "cancel");
                }
            },
            error: function () {
                $.hideLoading();
                $.toast("服务器错误", "cancel");
            }
        });
    }
</script>
</body>


</html>
