<%@ page import="entity.AdminUser" %><%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/20
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    AdminUser user = new AdminUser();
    if (session.getAttribute("admin_user") != null) {
        user = (AdminUser) session.getAttribute("admin_user");
    }
    String username = user.getAdminUserName();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>


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
    <script src="/static/js/bootstrap-datetimepicker.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/static/js/html5shiv.min.js"></script>
    <script src="/static/js/respond.min.js"></script>
    <![endif]-->
<body class="page-body">

<div class="page-container">
    <div class="sidebar-menu toggle-others fixed">

        <div class="sidebar-menu-inner">

            <header class="logo-env">

                <!-- logo -->
                <div class="logo">
                    <a href='/admin/main.jsp' class="logo-expanded">
                        <img src="/static/img/logo.png" width="100" alt=""/>
                    </a>

                    <a href='/admin/main.jsp' class="logo-collapsed">
                        <img src="/static/img/logo.png" width="40" alt=""/>
                    </a>
                </div>

                <!-- This will toggle the mobile menu and will be visible only on mobile devices -->
                <div class="mobile-menu-toggle visible-xs">
                    <a href="#" data-toggle="mobile-menu">
                        <i class="fa-bars"></i>
                    </a>
                </div>

            </header>


            <ul id="main-menu" class="main-menu">
                <!-- add class "multiple-expanded" to allow multiple submenus to open -->
                <!-- class "auto-inherit-active-class" will automatically add "active" class for parent elements who are marked already with class "active" -->
                <li>
                    <a href="/admin/main.jsp">
                        <i class="linecons-cog"></i>
                        <span class="title">控制台</span>
                    </a>
                </li>
                <li class="has-sub">
                    <a>
                        <i class="linecons-user"></i>
                        <span class="title">权限管理</span>
                    </a>

                    <ul>
                        <li>
                            <a href='/admin/user.jsp'>
                                <span class="title">管理员列表</span>
                            </a>
                        </li>
                        <li>
                            <a href='/admin/role.jsp'>
                                <span class="title">角色列表</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="has-sub">
                    <a>
                        <i class="linecons-note"></i>
                        <span class="title">库存管理</span>
                    </a>
                    <ul>
                        <li>
                            <a href='/admin/store.jsp'>
                                <span class="title">库存单列表</span>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="had-sub">
                    <a>
                        <i class="linecons-location"></i>
                        <span class="title">商品管理</span>
                    </a>
                    <ul>
                        <li>
                            <a href='/admin/item.jsp'>
                                <span class="activity">商品列表</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>

        </div>

    </div>

    <div class="main-content">

        <!-- User Info, Notifications and Menu Bar -->
        <nav class="navbar user-info-navbar" role="navigation">

            <!-- Left links for user info navbar -->
            <ul class="user-info-menu left-links list-inline list-unstyled">

                <li class="hidden-sm hidden-xs">
                    <a href="#" data-toggle="sidebar">
                        <i class="fa-bars"></i>
                    </a>
                </li>

            </ul>


            <!-- Right links for user info navbar -->
            <ul class="user-info-menu right-links list-inline list-unstyled">


                <li class="dropdown user-profile">
                    <a href="#" data-toggle="dropdown">
                       <span>
								<%=username%>
								<i class="fa-angle-down"></i>
							</span>
                    </a>

                    <ul class="dropdown-menu user-profile-menu list-unstyled">
                        <li class="last">
                            <a href="/admin/user/logout/servlet">
                                <i class="fa-lock"></i>
                                注销
                            </a>
                        </li>
                    </ul>
                </li>

            </ul>

        </nav>


        <jsp:include page="footer.jsp" flush="true"/>


    </div>
</div>


<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/TweenMax.min.js"></script>
<script src="/static/js/resizeable.js"></script>
<script src="/static/js/joinable.js"></script>
<script src="/static/js/xenon-api.js"></script>
<script src="/static/js/xenon-toggles.js"></script>


<!-- JavaScripts initializations and stuff -->
<script src="/static/js/xenon-custom.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.0.0/js/jquery-weui.min.js"></script>
</body>
</html>
