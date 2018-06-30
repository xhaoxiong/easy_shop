<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="admin.dao.Imple.PermissionImple" %>
<%@ page import="entity.AdminUser" %>
<%@ page import="entity.StoreRecord" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/26
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<head>
    <title>库存单列表</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">

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


    <link rel="stylesheet" href="/static/bootstrap-select-1.12.4/dist/css/bootstrap-select.min.css">
    <script src="/static/js/jquery-1.11.1.min.js"></script>
    <script src="/static/js/bootstrap-datetimepicker.js"></script>

    <script src="/static/js/html5shiv.min.js"></script>
    <script src="/static/js/respond.min.js"></script>
</head>

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

                <!-- Default panel -->
                <div class="panel panel-default">
                    <%
                        List<StoreRecord> storeRecordList = (List<StoreRecord>) request.getAttribute("storeRecordList");
                        if (storeRecordList == null || storeRecordList.size() == 0) {
                            request.getRequestDispatcher("/admin/store/list/servlet").forward(request, response);
                            return;
                        }

                    %>

                    <select class="selectpicker" id="select_picker">
                        <option href="/admin/store/list/servlet">
                            出入库单筛选
                        </option>

                        <option href="/admin/store/list/servlet?type=1">
                            入库单
                        </option>
                        <option href="/admin/store/list/servlet?type=2">
                            出库单
                        </option>
                    </select>

                    <div style="float: right">
                        <input style="width: 200px;display: inline-block;" type="text" class="form-control"
                               placeholder="请输入用单号关键字" id="search">
                        <button style="display: inline-block;" type="button" class="btn btn-info"
                                onclick="searchRecord()">搜索单号
                        </button>
                    </div>


                    <div class="panel-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th class="hidden-xs">单号</th>
                                <th>类型</th>
                                <th class="hidden-xs">经手人</th>
                                <th>创建时间</th>
                                <th class="hidden-xs">最后更新时间</th>
                                <th class="hidden-xs">操作</th>
                            </tr>
                            </thead>

                            <tbody>

                            <%
                                for (StoreRecord storeRecord : storeRecordList) {
                            %>
                            <tr>
                                <td>
                                    <%=storeRecord.getId()%>
                                </td>
                                <td class="hidden-xs"><%=storeRecord.getRecordNumber()%>
                                </td>
                                <td>
                                    <%
                                        if (storeRecord.getType() == 1) {
                                    %>
                                    入库单
                                    <%} else if (storeRecord.getType() == 2) {%>
                                    出库单
                                    <%} else {%>
                                    未知
                                    <%}%>
                                </td>
                                <td><%=storeRecord.getAdminUser().getAdminUserName()%>
                                </td>
                                <td>
                                    <fmt:formatDate value="<%=storeRecord.getCreateTime()%>"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>

                                <td><fmt:formatDate value="<%=storeRecord.getUpdateTime()%>"
                                                    pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>

                                    <button style="width: 60px" class="btn btn-xs btn-gray"
                                            data-toggle="modal" onclick="GetDetail(<%=storeRecord.getId()%>)">详情
                                    </button>

                                    <div class="modal fade" id="DetailModal" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" id="DetailPannel">

                                        </div>
                                    </div>
                                    <%
                                        PermissionImple permissionImple = new PermissionImple();
                                        permissionImple.openDatabase();
                                    %>
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
                                                            onclick="DelRecord(<%=storeRecord.getId()%>)">
                                                        立即删除
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
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


                                <li>
                                    <% int pageNum = (int) request.getAttribute("pageNum");

                                    %>
                                    <% for (int i = 1; i < pageNum + 1; i++) { %>
                                    <a href="http://127.0.0.1:8080/admin/store.jsp?p=<%=i%>"><%=i%>
                                    </a>
                                    <%}%>
                                </li>
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
<script src="/static/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
<!-- JavaScripts initializations and stuff -->
<script src="/static/js/xenon-custom.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.0.0/js/jquery-weui.min.js"></script>
</body>
<script>

    function GetDetail(id) {
        $.ajax({
            url: 'http://127.0.0.1:8080/admin/store/get/one/servlet',
            type: 'post',
            data: {
                'id': id
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                console.log(data.storeRecordDetails);
                $('#DetailModal').modal('show');
                $("#DetailPannel").html("");

                str = "";
                for (i = 0; i < data.storeRecordDetails.length; i++) {
                    str += "                                                    <div>经手人:" + data.storeRecordDetails[i].adminUser.adminUserName + "</div>\n" +
                        "                                                    <div>单号:" + data.storeRecordDetails[i].recordNumber + "</div>\n" +
                        "                                                    <div>商品名称:" + data.storeRecordDetails[i].item.name + "</div>\n" +
                        "                                                    <div>货号:" + data.storeRecordDetails[i].item.number + "</div>\n" +
                        "                                                    <div>" + "--------------------------------------------" + "</div>\n"
                }

                $("#DetailPannel").append("<div class=\"modal-content\">\n" +
                    "                                                <div class=\"modal-header\">\n" +
                    "                                                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\"\n" +
                    "                                                            aria-hidden=\"true\">\n" +
                    "                                                        &times;\n" +
                    "                                                    </button>\n" +
                    "                                                    <h4 class=\"modal-title\" id=\"myModalLabel\">\n" +
                    "                                                        出入库单详情\n" +
                    "                                                    </h4>\n" +
                    "                                                </div>\n" +
                    "                                                <div class=\"modal-body\">\n" +
                    str +
                    "                                                </div>\n" +
                    "                                            </div>")
            },
            error: function () {
                $.hideLoading();
                $.toast("服务器错误", "cancel");
            }
        });
    }


    $(document).ready(function () {
        $('.selectpicker').selectpicker('setStyle', 'btn-primary');
    });


    $('#select_picker').on('changed.bs.select', function (e) {
        select_href = $(this).find("option:selected").attr("href");
        window.location.href = select_href;
        $(this)
    });

    function DelRecord(id) {
        $.ajax({
            url: 'http://127.0.0.1:8080/admin/store/delete/one/servlet',
            type: 'post',
            data: {
                'id': id
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                $.hideLoading();
                if (data.status == 10000) {
                    $.toast("删除成功");
                    location.reload();
                } else if (data.status == 10002) {
                    $.toast("参数错误", "cancel");
                } else if (data.status == 10003) {
                    $.toast("删除失败", "cancel")
                }
            },
            error: function () {
                $.hideLoading();
                $.toast("服务器错误", "cancel");
            }
        });
    }

    function searchRecord() {
        searchStr = $("#search").val();
        window.location.href = "http://127.0.0.1:8080/admin/store/list/servlet?str=" + searchStr;
    }

</script>
</html>
