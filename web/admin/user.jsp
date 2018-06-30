<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="entity.AdminUser" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="admin.dao.Imple.PermissionImple" %>
<%@ page import="entity.Role" %><%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/20
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    AdminUser user = new AdminUser();
    if (session.getAttribute("admin_user") != null) {
        user = (AdminUser) session.getAttribute("admin_user");
    } else {
        response.sendRedirect("/admin/login.jsp");
        return;
    }
    String username = user.getAdminUserName();
%>
<html>
<head>
    <Title>管理员列表</Title>
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

        <div class="row">
            <div class="col-md-12">
                <%
                    List<AdminUser> adminUsers = (List<AdminUser>) request.getAttribute("adminUsers");
                    if (adminUsers == null || adminUsers.size() == 0) {
                        System.out.println("null");
                        request.getRequestDispatcher("/admin/user/list/servlet").forward(request, response);
                        return;
                    }
                %>
                <!-- Default panel -->
                <div class="panel panel-default">
                    <%
                        PermissionImple permissionImple = new PermissionImple();
                        permissionImple.openDatabase();
                    %>

                    <%if (permissionImple.IdentityPermission(user)) {%>
                    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#add-user">添加用户
                    </button>
                    <%}%>
                    <div class="modal fade" id="add-user" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="add-user-titile">
                                        添加用户
                                    </h4>
                                </div>

                                <div class="form-group">
                                    <label for="InputUserName">用户名</label>
                                    <input type="text" class="form-control" id="InputUserName"
                                           placeholder="用户名">
                                </div>

                                <div class="form-group">
                                    <label for="InputPassword">密码</label>
                                    <input type="password" class="form-control" id="InputPassword"
                                           placeholder="请输入密码">
                                </div>
                                <div class="form-group">
                                    <label for="InputEmail">邮箱</label>
                                    <input type="email" class="form-control" id="InputEmail"
                                           placeholder="请输入email">
                                </div>
                                <%--角色列表--%>
                                <div class="checkbox">
                                    <% List<Role> roles = (List<Role>) request.getAttribute("roles");
                                    %>
                                    <%for (Role role : roles) {%>
                                    <label>
                                        <input type="checkbox" name="role"
                                               value="<%=role.getId()%>"> <%=role.getRoleName()%>
                                    </label>
                                    <%}%>
                                </div>

                                <button type="submit" class="btn btn-success" onclick="AddAdminUser()">提交
                                </button>

                            </div>
                        </div>

                    </div>

                    <%
                        if (permissionImple.IdentityPermission(user)) {
                    %>
                    <div style="float: right">
                        <input style="width: 200px;display: inline-block;" type="text" class="form-control"
                               placeholder="请输入用户名关键字" id="search">
                        <button style="display: inline-block;" type="button" class="btn btn-info"
                                onclick="searchUser()">搜索用户
                        </button>
                    </div>
                    <%}%>

                    <div class="panel-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th class="hidden-xs">用户名</th>
                                <th>邮箱</th>

                                <th class="hidden-xs">登录时间</th>
                                <th>最后登陆ip</th>
                                <th class="hidden-xs">操作</th>
                            </tr>
                            </thead>

                            <tbody>


                            <%
                                for (AdminUser adminUser : adminUsers) {

                            %>
                            <tr>
                                <td><%=adminUser.getId()%>
                                </td>
                                <td class="hidden-xs"><%=adminUser.getAdminUserName()%>
                                </td>
                                <td><%=adminUser.getEmail()%>
                                </td>
                                <td class="hidden-xs"><fmt:formatDate value="<%=adminUser.getLastLoginTime()%>"
                                                                      pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td><%=adminUser.getLastLoginIp()%>
                                </td>
                                <td>
                                    <button style="width: 60px" class="btn btn-xs btn-green" data-toggle="modal"
                                            onclick="editUser(<%=adminUser.getId()%>)">编辑
                                    </button>
                                    <div class="modal fade" id="editUser" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabel"
                                         aria-hidden="true">

                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">
                                                        &times;
                                                    </button>
                                                    <h4 class="modal-title" id="editUserTitle">
                                                        编辑用户
                                                    </h4>
                                                </div>

                                                <div class="modal-body" id="editPannel">


                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%if (permissionImple.IdentityPermission(user)) {%>
                                    <button style="width: 60px" class="btn btn-xs btn-red"
                                            data-toggle="modal" data-target="#DelModal">删除
                                    </button>
                                    <%}%>
                                    <div class="modal fade" id="DelModal" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">
                                                        &times;
                                                    </button>
                                                    <h4 class="modal-title" id="myModalLabel">
                                                        提示
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    确定要删除吗？
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        关闭
                                                    </button>
                                                    <button type="button" class="btn btn-xs btn-red"
                                                            onclick="DelUser(<%=adminUser.getId()%>)">
                                                        立即删除
                                                    </button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal -->
                                    </div>


                                </td>
                            </tr>
                            <%}%>
                            </tbody>
                        </table>
                        <div class="text-right">
                            <ul class="pagination pagination-sm">

                                <%--<li><a href="{{.paginator.PageLinkFirst}}">首页</a></li>--%>
                                <%--<li><a href="{{.paginator.PageLinkPrev}}">&lt;</a></li>--%>

                                <li class="disabled"><a>首页</a></li>
                                <li class="disabled"><a>&lt;</a></li>


                                <%--<li class="active">--%>
                                <%--<a href="#">1</a>--%>
                                <%--<a href="#">2</a>--%>
                                <%--<a href="#">3</a>--%>
                                <%--<a href="#">4</a>--%>
                                <%--</li>--%>
                                <li>
                                    <% int pageNum = (int) request.getAttribute("pageNum");

                                    %>
                                    <% for (int i = 1; i < pageNum + 1; i++) { %>
                                    <a href="http://127.0.0.1:8080/admin/user.jsp?p=<%=i%>"><%=i%>
                                    </a>
                                    <%}%>
                                </li>
                                <%--<li><a href="{{.paginator.PageLinkNext}}">&gt;</a></li>--%>
                                <%--<li><a href="{{.paginator.PageLinkLast}}">尾页</a></li>--%>
                                <li class="disabled"><a>&gt;</a></li>
                                <li class="disabled"><a>尾页</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>

        </div>

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
<script>
    function AddAdminUser() {
        var roleIdArray = [];//定义一个数组
        $('input[name="role"]:checked').each(function () {//遍历每一个名字为interest的复选框，其中选中的执行函数
            roleIdArray.push($(this).val());//将选中的值添加到数组chk_value中
        });

        InputUserName = $("#InputUserName").val();
        InputPassword = $("#InputPassword").val();
        InputEmail = $("#InputEmail").val();
        if (!InputUserName || !InputPassword || !InputEmail) {
            $.toast("内容填写不完整", "cancel");
            return
        }
        $.ajax({
            url: 'http://127.0.0.1:8080/admin/user/add/servlet',
            type: 'post',
            data: {
                'roles': roleIdArray,
                'username': InputUserName,
                'password': InputPassword,
                'email': InputEmail
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                $.hideLoading();
                if (data.status == 10000) {
                    $.toast("添加成功");
                    location.reload();
                } else if (data.status == 10002) {
                    $.toast("用户名或密码错误", "cancel");
                } else if (data.status == 10003) {
                    $.toast("您没有该权限", "cancel")
                }
            },
            error: function () {
                $.hideLoading();
                $.toast("服务器错误", "cancel");
            }
        });

    }


    function editUser(id) {
        console.log(id);

        $.ajax({
            url: 'http://127.0.0.1:8080/admin/user/one/servlet',
            type: 'post',
            data: {
                'id': id,
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                $.hideLoading();
                if (data.status == 10000) {
                    console.log(data.adminUser)
                    $('#editUser').modal('show');
                    $("#editPannel").html("");
                    $("#editPannel").append("<div class=\"form-group\">\n" +
                        "                                                        <label for=\"editInputUserName" + id + "\">用户名</label>\n" +
                        "                                                        <input type=\"text\" class=\"form-control\"\n" +
                        "                                                               id=\"editInputUserName" + id + "\"\n" +
                        "                                                               readonly=\"readonly\"\n" +
                        "                                                               value=\"" + data.adminUser.adminUserName + "\">\n" +
                        "                                                    </div>\n" +
                        "\n" +
                        "                                                    <div class=\"form-group\">\n" +
                        "                                                        <label for=\"editInputPassword" + id + "\">密码</label>\n" +
                        "                                                        <input type=\"password\" class=\"form-control\"\n" +
                        "                                                               id=\"editInputPassword" + id + "\"\n" +
                        "                                                               placeholder=\"Password\">\n" +
                        "                                                    </div>\n" +
                        "                                                    <div class=\"form-group\">\n" +
                        "                                                        <label for=\"editInputEmail" + id + "\">邮箱</label>\n" +
                        "                                                        <input type=\"email\" class=\"form-control\"\n" +
                        "                                                               id=\"editInputEmail" + id + "\"\n" +
                        "                                                               placeholder=\"Email\" value=\"" + data.adminUser.email + "\">\n" +
                        "                                                    </div>\n" +
                        "                                                    \n" +
                        "                                                    <button type=\"submit\" class=\"btn btn-success\"\n" +
                        "                                                            onclick=\"EditAdminUser(" + id + ")\">提交\n" +
                        "                                                    </button>\n")
                } else if (data.status == 10003) {
                    $.toast("您没有权限操作", "cancel");
                }
            },
            error: function () {
                $.hideLoading();
                $.toast("服务器错误", "cancel");
            }
        });
    }


    function EditAdminUser(id) {
        var chk_value = [];//定义一个数组
        $('input[name="role"]:checked').each(function () {//遍历每一个名字为interest的复选框，其中选中的执行函数
            chk_value.push($(this).val());//将选中的值添加到数组chk_value中
            console.log($(this).val());
        });


        password = $("#editInputPassword" + id).val();
        email = $("#editInputEmail" + id).val();

        $.ajax({
            url: 'http://127.0.0.1:8080/admin/user/edit/servlet',
            type: 'post',
            data: {
                'id': id,
                'password': password,
                'email': email
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                $.hideLoading();
                if (data.status == 10000) {
                    $.toast("修改成功", "true");
                    location.reload();
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

    function DelUser(id) {
        $.ajax({
            url: 'http://127.0.0.1:8080/admin/user/delete/servlet',
            type: 'post',
            data: {
                'id': id,
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                $.hideLoading();
                if (data.status == 10000) {
                    location.reload();
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

    function searchUser() {
        searchStr = $("#search").val();
        window.location.href = "http://127.0.0.1:8080/admin/user/list/servlet?str=" + searchStr;
    }

</script>

</html>
