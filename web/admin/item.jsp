<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page import="entity.AdminUser" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="admin.dao.Imple.PermissionImple" %>
<%@ page import="entity.Role" %>
<%@ page import="entity.Item" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.Shelf" %><%--
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
    <Title></Title>
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
                <!-- Default panel -->
                <div class="panel panel-default">
                    <%
                        PermissionImple permissionImple = new PermissionImple();
                        permissionImple.openDatabase();
                    %>
                    <%

                        List<Item> items = (List<Item>) request.getAttribute("items");

                        if ((items == null || items.size() == 0)) {
                            System.out.println("null");
                            request.getRequestDispatcher("/admin/item/list/servlet?p=1").forward(request, response);
                            return;
                        }
                        List<Shelf> shelves = (List<Shelf>) request.getAttribute("shelves");

                    %>
                    <button type="button" class="btn btn-success" data-toggle="modal" onclick="showAddItemPannel()">添加商品
                    </button>


                    <button type="button" class="btn btn-primary">批量入库</button>
                    <button type="button" class="btn btn-primary">批量出库</button>

                    <select class="selectpicker" id="select_picker">
                        <option href="/admin/item/list/servlet">
                            货架筛选
                        </option>
                        <%for (Shelf shelf : shelves) {%>

                        <option href="/admin/item/list/servlet?shelf_id=<%=shelf.getId()%>"
                                id="select_<%=shelf.getId()%>"><%=shelf.getName()%>
                        </option>
                        <%}%>
                    </select>

                    <div class="modal fade" id="add-item" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        &times;
                                    </button>
                                    <h4 class="modal-title" id="add-item-titile">
                                        添加商品
                                    </h4>
                                </div>

                                <div class="form-group">
                                    <label for="itemName">商品名称</label>
                                    <input type="text" class="form-control" id="itemName" placeholder="请输入商品名称">
                                </div>
                                <div class="form-group">
                                    <label for="itemNumber">商品编号</label>
                                    <input type="text" class="form-control" id="itemNumber"
                                           placeholder="长度不应超过20个字符, 留空则系统会按约定规则自动生成货号">
                                </div>

                                <select class="selectpicker" id="shelves">

                                    <%for (Shelf shelf : shelves) {%>

                                    <option id="option_<%=shelf.getId()%>"
                                    ><%=shelf.getName()%>
                                    </option>
                                    <%}%>
                                </select>
                                <br/>
                                <div class="form-group">
                                    <label for="itemInCount">入库数量</label>
                                    <input type="text" class="form-control" id="itemInCount"
                                           placeholder="请输入入库数量,不输入则默认为0">
                                </div>

                                <button type="submit" class="btn btn-success" onclick="AddItem()">提交
                                </button>

                            </div>
                        </div>

                    </div>


                    <div style="float: right">

                        <input style="width: 200px;display: inline-block;" type="text" class="form-control"
                               placeholder="请输入商品关键字" id="search">
                        <button style="display: inline-block;" type="button" class="btn btn-info" onclick="Search()"
                        >搜索商品
                        </button>
                    </div>

                    <div class="panel-body">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th></th>
                                <th>#</th>
                                <th class="hidden-xs">商品名</th>

                                <th>货架</th>
                                <th>货号</th>
                                <th>库存</th>
                                <th>出入库数量</th>
                                <th class="hidden-xs">操作</th>
                            </tr>
                            </thead>

                            <tbody>
                            <%for (Item item : items) {%>
                            <tr>
                                <td>

                                    <input type="checkbox" value="<%=item.getId()%>">

                                </td>
                                <td><%=item.getId()%>
                                </td>
                                <td class="hidden-xs"><%=item.getName()%>
                                </td>
                                <td><%=item.getShelf().getName()%>
                                </td>
                                <td><%=item.getNumber()%>
                                </td>
                                <td id="currentQty_<%=item.getId()%>"><%=item.getStockQty()%>
                                </td>
                                <td>
                                    <input style="width: 100px;display: inline-block;" type="text" class="form-control"
                                           placeholder="请输入数量" id="itemInput<%=item.getId()%>">
                                </td>
                                <td>
                                    <button style="width: 60px" class="btn btn-xs btn-green" data-toggle="modal"
                                            data-target="#inModal">入库
                                    </button>

                                    <div class="modal fade" id="inModal" tabindex="-1" role="dialog"
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
                                                    确定要入库吗？
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        关闭
                                                    </button>
                                                    <button type="button" class="btn btn-xs btn-red"
                                                            onclick="inStock(<%=item.getId()%>)">
                                                        立即入库
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                    <button style="width: 60px" class="btn btn-xs btn-red"
                                            data-toggle="modal"
                                            data-target="#outModal">出库
                                    </button>


                                    <div class="modal fade" id="outModal" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-hidden="true">
                                                        &times;
                                                    </button>
                                                    <h4 class="modal-title">
                                                        提示
                                                    </h4>
                                                </div>
                                                <div class="modal-body">
                                                    确定要出库吗？
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        关闭
                                                    </button>
                                                    <button type="button" class="btn btn-xs btn-red"
                                                            onclick="outStock(<%=item.getId()%>)">
                                                        立即出库
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
                                <% int pageNum = (int) request.getAttribute("pageNum"); %>
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
                                    <% for (int i = 1; i < pageNum + 1; i++) { %>
                                    <a href="http://127.0.0.1:8080/admin/item.jsp?p=<%=i%>"><%=i%>
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
<script src="/static/bootstrap-select-1.12.4/dist/js/bootstrap-select.min.js"></script>
<!-- JavaScripts initializations and stuff -->
<script src="/static/js/xenon-custom.js"></script>
<script src="https://cdn.bootcss.com/jquery-weui/1.0.0/js/jquery-weui.min.js"></script>
</body>

<script>

    $(document).ready(function () {
        $('.selectpicker').selectpicker('setStyle', 'btn-primary');
    });


    $('#select_picker').on('changed.bs.select', function (e) {
        select_href = $(this).find("option:selected").attr("href");
        window.location.href = select_href;
        $(this)
    });


    function Search() {
        searchStr = $("#search").val();

        window.location.href = "http://127.0.0.1:8080/admin/item/list/servlet?str=" + searchStr;
    }


    function showAddItemPannel() {

        $('#add-item').modal('show');
    }

    function inStock(id) {
        inputCount = $(" #itemInput" + id).val();
        if (inputCount <= 0) {
            $.toast("请输入合适的库存数量", "cancel");
            return;
        }
        $.ajax({
            url: 'http://127.0.0.1:8080/admin/item/qty/add/servlet',
            type: 'post',
            data: {
                'item_id': id,
                'input_count': inputCount
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                $.hideLoading();
                if (data.status == 10000) {
                    $.toast("入库成功");
                    location.reload();
                } else if (data.status == 10002) {
                    $.toast("参数不全", "cancel");
                } else {
                    $.toast("入库失败", "cancel");
                }
            },
            error: function () {
                $.hideLoading();
                $.toast("服务器错误", "cancel");
            }
        });
    }

    function outStock(id) {

        inputCount = $(" #itemInput" + id).val();
        currentQty = $("#currentQty_" + id).text();
        console.log(inputCount, currentQty);
        if (currentQty < inputCount) {
            $.toast("库存不足，请出库小于库存数量", "cancel");
            return;
        }
        if (inputCount <= 0) {
            $.toast("请输入合适的库存数量", "cancel");
            return;
        }


        $.ajax({
            url: 'http://127.0.0.1:8080/admin/item/qty/cut/servlet',
            type: 'post',
            data: {
                'item_id': id,
                'input_count': inputCount
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                $.hideLoading();
                if (data.status == 10000) {
                    $.toast("出库成功");
                    location.reload();
                } else if (data.status == 10002) {
                    $.toast("参数不全", "cancel");
                } else {
                    $.toast("出库失败", "cancel");
                }
            },
            error: function () {
                $.hideLoading();
                $.toast("服务器错误", "cancel");
            }
        });
    }

    function AddItem() {
        optionId = $('#shelves').find("option:selected").attr('id').split("_");

        shelf_id = optionId[1];

        itemName = $("#itemName").val();
        itemNumber = $("#itemNumber").val();
        inCount = $("#itemInCount").val();

        if (!itemName) {
            $.toast("商品名不能为空", "cancel");
            return
        }

        if (inCount < 0) {
            $.toast("请输入大于等于0的数量", "cancel");
            return
        }

        if (!inCount) {
            inCount = 0;
        }

        $.ajax({
            url: 'http://127.0.0.1:8080/admin/item/add/servlet',
            type: 'post',
            data: {
                'shelf_id': shelf_id,
                'item_name': itemName,
                'item_number': itemNumber,
                'in_count': inCount

            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                $.hideLoading();
                if (data.status == 10000) {
                    location.reload();
                } else if (data.status == 10002) {
                    $.toast("参数不全", "cancel");
                } else {
                    $.toast("添加失败", "cancel");
                }
            },
            error: function () {
                $.hideLoading();
                $.toast("服务器错误", "cancel");
            }
        });
    }
</script>
</html>
