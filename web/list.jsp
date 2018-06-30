<%@ page import="entity.Category" %>
<%@ page import="entity.Navigation" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Item" %>
<%@ page import="com.sun.org.apache.regexp.internal.RE" %><%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/4
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<%
    User user = new User();
    if (session.getAttribute("userinfo") != null) {
        user = (User) session.getAttribute("userinfo");
    }
    String username = user.getUserName();
%>
<head>
    <title>商品列表</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
    <script src="static/js/jquery-1.8.3.min.js"></script>
    <script src="static/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>
<div class="width100" style="height:25ox;background:#f0f0f0;">
    <div class="width1200 center_yh font14 c_66" style="height:25px;line-height:25px;">
        <font class="left_yh">欢迎来到永安庄园•礼品世家！</font>
        <div class="right_yh" id="fast_jump">


            <a href="/personal.jsp">
            </a>
            <%
                if (session.getAttribute("userinfo") != null) {
            %>

            <a href="/personal.jsp"><%=username%>
            </a>
            <%} else {%>
            <a href="/login.jsp">登陆
                <%}%>
            </a>
            <b></b>
            <a href="/register.jsp">注册</a>
            <b></b>
            <a href="/personal.jsp">个人中心</a>
            <b></b>
            <a href="/cart.jsp">购物车</a>
        </div>
    </div>
</div>
<div class="width100 hidden_yh">
    <div class="width1200 center_yh hidden_yh">
        <a href="#" class="block_yh left_yh" style="margin-top:40px;"><img src="static/images/logo2.png"></a>
        <div class="right_yh" style="height:28px;width:316px;border:2px solid #dd4545;margin-top:48px;">

            <form>
                <input type="text" id="searchId" placeholder="关键词查询" class="searCh">
                <input type="button" class="btnSearh" onclick="Search()" value="搜索">
            </form>
            <script>
                function Search() {
                    console.log(searchStr = $("#searchId").val());
                    window.location.href = "http://localhost:8080/list.jsp?cate_id=9&search=" + searchStr;
                }
            </script>
        </div>
    </div>
</div>
<!--导航条-->
<div class="width100" style="height:45px;background:#dd4545;margin-top:40px;position:relative;z-index:100;">
    <!--中间的部分-->
    <div class="width1200 center_yh relative_yh" style="height:45px;">
        <!--列表导航(downSlide就显示,否则就不显示)-->
        <div class="left_yh" style="width:230px;height:45px;" id="hiddenShow">
            <!--头部的图标-->
            <img src="static/images/cd.png" class="left_yh" style="margin-left:24px;">
            <span class="block_yh left_yh fff" style="height:45px;line-height:44px;margin-left:10px;">阶段分类</span>
            <!--下面的导航展开部分-->
            <div class="downSlide">
                <!--一块-->
                <%
                    int cateId = Integer.parseInt(request.getParameter("cate_id"));

                    List<Category> list = (List<Category>) request.getAttribute("categoryList");
                    if (list == null || list.size() == 0) {
                        request.getRequestDispatcher("/item/list/servlet?" + cateId).forward(request, response);
                        return;
                    }
                    for (Category cate : list) {

                %>
                <div class="n1Nav">
                    <font><%=cate.getName()%>
                    </font>

                    <img src="static/images/jt.png">
                    <div class="n2Nav">
                        <%
                            for (Category cate1 : cate.getCategory()) {


                        %>
                        <div class="n3Nav">
                            <h3><%=cate1.getName()%>
                            </h3>
                            <% for (Category cate2 : cate1.getCategory()) {%>
                            <a href="http://localhost:8080/list.jsp?cate_id=<%=cate2.getId()%>""><%=cate2.getName()%>
                            </a>
                            <%}%>
                        </div>
                        <%}%>
                    </div>
                </div>
                <%}%>

            </div>
        </div>
        <!--普通导航-->
        <!--普通导航-->
        <div class="left_yh font16" id="pageNav">
            <%
                List<Navigation> navs = (List<Navigation>) request.getAttribute("navs");
            %>
            <% for (Navigation nav : navs) {%>
            <%if (nav.getName().equals("首页")) {%>
            <a href="http://localhost:8080/index.jsp"><%=nav.getName()%>
            </a>
            <%} else {%>
            <a href="http://localhost:8080/list.jsp?cate_id=<%=nav.getCateId()%>"><%=nav.getName()%>
            </a>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
<!--当前位置-->
<div class="width1200 center_yh hidden_yh font14" style="height:40px;line-height:40px;">
    <% Category category = (Category) request.getAttribute("category"); %>
    <span>当前位置:</span>
    <a href="#" class="c_66">首页></a>
    <a href="#" class="c_66"><%=category.getName()%>
    </a>
</div>
<!--各种筛选-->
<div id="filter">
    <%--<div class="oList">--%>
    <%--<span>品牌：</span>--%>
    <%--<a href="#" class="on">不限</a>--%>
    <%--<a href="#">Nike</a>--%>
    <%--</div>--%>
    <%--<script>--%>
    <%--$(".oList a").click(function () {--%>
    <%--$(this).addClass("on").siblings().removeClass("on")--%>
    <%--})--%>
    <%--</script>--%>
</div>
<% String pStr = request.getParameter("p");
    int p = 1;
    if (pStr != null) {
        p = Integer.parseInt(pStr);
    }

%>
<div class="width1198 center_yh" style="height:35px;background:#f0f0f0;border:1px solid #ddd;margin-top:25px;">
    <a href="http://localhost:8080/list.jsp?cate_id=<%=cateId%>&p=<%=p%>" id="default" class="mR ">默认</a>
    <a href="http://localhost:8080/list.jsp?cate_id=<%=cateId%>&is_price=1&p=<%=p%>" id="isPrice" class="mR">价格<img
            src="static/images/gg.png"></a>
    <a href="http://localhost:8080/list.jsp?cate_id=<%=cateId%>&is_volume=1&p=<%=p%>" id="isVolume" class="mR">销量<img
            src="static/images/gg.png"></a>

    <% String isPrice = request.getParameter("is_price");
        String isVolume = request.getParameter("is_volume");
        if (isPrice == null && isVolume == null) {
    %>
    <script>
        $("#default").addClass("on")
    </script>
    <%}%>
    <%
        if (isPrice != null) {
            if (isPrice.equals("1")) {%>

    <script>
        $("#isPrice").addClass("on")
    </script>

    <%
            }
        }
        if (isVolume != null) {
            if (isVolume.equals("1")) {

    %>
    <script>
        $("#isVolume").addClass("on")
    </script>
    <%} else {%>
    <script>
        $("#default").addClass("on")
    </script>
    <%
            }
        }
    %>
    <%--<script>--%>
    <%--$(".mR").click(function () {--%>
    <%--$(this).addClass("on").find("img").attr("src", "static/images/gg2.png").parent().siblings().removeClass("on")--%>
    <%--$(this).siblings().removeClass("on")--%>
    <%--})--%>
    <%--</script>--%>
</div>
<!--商品列表-->
<div class="width1200 center_yh hidden_yh" style="margin-top:25px;">
    <ul class="listSs">

        <% List<Item> items = (List<Item>) request.getAttribute("items"); %>

        <%for (Item item : items) {%>
        <li>
            <a href="http://localhost:8080/detail.jsp?item_id=<%=item.getId()%>" class="bjK">
                <img src="<%=item.getCover()%>">
            </a>
            <h3 class="spName"><%=item.getName()%>
            </h3>
            <p class="center_yh block_yh hidden_yh" style="width:202px;">
                <font class="left_yh red font16">￥<%=item.getNowPrice()%>
                </font>
                <%--<font class="right_yh c_66 font14">126人已买</font>--%>
            </p>
            <%--<div class="wCa">--%>
            <%--<div class="wCa1">--%>
            <%--<b><img src="static/images/star.png"></b>--%>
            <%--<font>收藏</font>--%>
            <%--</div>--%>
            <%--<div class="wCa2">--%>
            <%--<b><img src="static/images/sar.png"></b>--%>
            <%--<font>加入购物车</font>--%>
            <%--</div>--%>
            <%--</div>--%>
        </li>
        <%}%>
    </ul>
</div>
<div id="navs">
    <div id="nav_in">
        <% int pageNum = (Integer) request.getAttribute("pageNum");%>
        <%if (pageNum > 1) {%>
        <a href="javascript:void(0)">«</a>
        <% for (int i = 1; i < pageNum + 1; i++) { %>
        <a href="http://localhost:8080/list.jsp?p=<%=i%>&cate_id=<%=cateId%>"><%=i%>
        </a>
        <%}%>
        <a href="javascript:void(0)">»</a>
        <%}%>
    </div>
</div>
<script>
    $(".wCa div").click(function () {
        $(this).addClass("on")
    })
</script>
<!--品质保证-->
<div class="width1200 center_yh" style="height:130px;margin-top:60px;">
    <img src="static/images/db.png">
</div>


<jsp:include   page="footer.jsp" flush="true"/>
<!--页脚-->
</body>
</html>
