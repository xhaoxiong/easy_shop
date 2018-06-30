<%@ page import="entity.Category" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Navigation" %>
<%@ page import="entity.Item" %><%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/4
  Time: 8:14
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
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
    <script src="static/js/jquery-1.8.3.min.js"></script>
    <script src="static/js/jquery.luara.0.0.1.min.js"></script>
</head>

<body>
<div class="width100" style="height:25ox;background:#f0f0f0;">
    <div class="width1200 center_yh font14 c_66" style="height:25px;line-height:25px;">
        <font class="left_yh">欢迎来到 长大服装店！</font>
        <div class="right_yh" id="fast_jump">


            <a href="/personal.jsp">
            </a>
            <%
                if (session.getAttribute("userinfo") != null) {
            %>

            <a href="/personal.jsp"><%=username%>
            </a>
            <a href="/user/logout">退出
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
        <!--列表导航-->
        <div class="left_yh Selected" style="width:230px;height:45px;" id="hiddenShow">
            <!--头部的图标-->
            <img src="static/images/cd.png" class="left_yh" style="margin-left:24px;">
            <span class="block_yh left_yh fff" style="height:45px;line-height:44px;margin-left:10px;">阶段分类</span>
            <!--下面的导航展开部分-->
            <div class="downSlide">
                <!--一块-->
                <%
                    List<Category> list = (List<Category>) request.getAttribute("categoryList");
                    if (list == null || list.size() == 0) {
                        request.getRequestDispatcher("/index/servlet").forward(request, response);
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
                            <a href="http://localhost:8080/list.jsp?cate_id=<%=cate2.getId()%>"><%=cate2.getName()%>
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
<!--banner-->
<div class="width1200 center_yh hidden_yh" style="position:relative;z-index:80;">
    <div class="example2" style="width:1200px;height:490px;overflow:hidden;margin-left:234px">
        <ul>
            <li><img src="static/images/1.jpg" alt="1"/></li>
            <li><img src="static/images/1.jpg" alt="1"/></li>
        </ul>
    </div>

</div>
<!--今日推荐-->
<%--<div class="width1200 center_yh groom">--%>
<%--<a href="#" class="right_yh block_yh" style="width:73px;height:45px;"></a>--%>
<%--</div>--%>
<%--<div class="width1200 center_yh" style="margin-top:20px;">--%>
<%--<div class="tjgoods">--%>
<%--<a href="#">--%>
<%--<img src="static/images/x.jpg">--%>
<%--<h4 class="yihang">毛绒玩具泰迪熊</h4>--%>
<%--<p class="red tcenter">￥299.9</p>--%>
<%--</a>--%>
<%--<a href="#">--%>
<%--<img src="static/images/x.jpg">--%>
<%--<h4 class="yihang">毛绒玩具泰迪熊</h4>--%>
<%--<p class="red tcenter">￥299.9</p>--%>
<%--</a>--%>
<%--<a href="#">--%>
<%--<img src="static/images/x.jpg">--%>
<%--<h4 class="yihang">毛绒玩具泰迪熊</h4>--%>
<%--<p class="red tcenter">￥299.9</p>--%>
<%--</a>--%>
<%--<a href="#">--%>
<%--<img src="static/images/x.jpg">--%>
<%--<h4 class="yihang">毛绒玩具泰迪熊</h4>--%>
<%--<p class="red tcenter">￥299.9</p>--%>
<%--</a>--%>
<%--<a href="#">--%>
<%--<img src="static/images/x.jpg">--%>
<%--<h4 class="yihang">毛绒玩具泰迪熊</h4>--%>
<%--<p class="red tcenter">￥299.9</p>--%>
<%--</a>--%>

<%--</div>--%>
<%--</div>--%>
<!--热门-->
<%--<div class="width1200 center_yh" style="height:300px;margin-top:40px;">--%>
<%--<div class="tone">--%>
<%--<a href="#"><img src="static/images/sp1.jpg"></a>--%>
<%--<a href="#"><img src="static/images/sp2.jpg"></a>--%>
<%--<a href="#"><img src="static/images/sp3.jpg"></a>--%>
<%--<a href="#"><img src="static/images/sp4.jpg"></a>--%>
<%--<a href="#"><img src="static/images/sp5.jpg"></a>--%>
<%--</div>--%>
<%--</div>--%>
<!--工艺礼品-->

<%
    List<Navigation> navsList = (List<Navigation>) request.getAttribute("navs");
    List<Item> items = (List<Item>) request.getAttribute("items");
%>
<% for (Navigation nav : navsList) {
    if (nav.getName().equals("首页")) {
        continue;
    }
%>
<div class="width1200 center_yh hidden_yh">
    <div class="width100" style="height:45px;line-height:45px;border-bottom:2px solid #dd4545;margin-top:20px;">
        <font class="left_yh font20"><%=nav.getName()%>
        </font>
        <a href="#" class="right_yh c_33 font16">更多»</a>
    </div>
    <div class="width100 hidden_yh" style="height:388px;">


        <a href="#" style="width:227px;height:388px;float:left;"><img src="<%=nav.getCover()%>"></a>

        <div class="normalPic">
            <%
                for (Item item : nav.getItems()) {
                    if (item.getNavigationId() != nav.getId()) {
                        continue;
                    } else {%>

            <a href="http://localhost:8080/detail.jsp?item_id=<%=item.getId()%>">
                <h3 class="yihang c_33 font14 font100" style="padding-left:10px;padding-right:10px;margin-top:10px;">
                    <%=item.getName()%>
                </h3>
                <p class="red font14" style="padding-left:10px;">￥<%=item.getNowPrice()%>
                </p>
                <img src="<%=item.getCover()%>" width="105" height="118" style="margin:0 auto">
            </a>
            <%}%>
            <%}%>
        </div>
    </div>
</div>
</a>
<%}%>


<!--品质保证-->
<div class="width1200 center_yh" style="height:130px;margin-top:60px;">
    <img src="static/images/db.png">
</div>
<!--页脚-->

<jsp:include page="footer.jsp" flush="true"/>
</body>


<!--Luara图片切换骨架end-->
<script>
    $(function () {
        $(".example2").luara({width: "966", height: "490", interval: 4500, selected: "seleted", deriction: "left"});
    });
</script>
</html>
