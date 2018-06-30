<%@ page import="entity.*" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/12
  Time: 1:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
    <script src="static/js/jquery-1.8.3.min.js"></script>
    <script src="static/js/jquery.luara.0.0.1.min.js"></script>
</head>
<body>
<%
    String cartMapStr = request.getParameter("cart_map");
    System.out.println(cartMapStr);
    JSONObject jsonObject = JSONObject.fromObject(cartMapStr);
    Map<String, String> cartMaps = (Map) jsonObject;


    if (cartMapStr == null) {
        response.sendRedirect("/index.jsp");
        return;
    }
    //map中是购物车id与购买数量的键值对

    List<UserCart> userCarts = new ArrayList<>();

    List<Consignee> consignees = (List<Consignee>) request.getAttribute("consignees");
    List<UserCart> userCarts1 = (List<UserCart>) request.getAttribute("userCartList");


    if (consignees == null || userCarts1 == null) {
        String enCartMapStr = URLEncoder.encode(cartMapStr, "utf-8");

        request.getRequestDispatcher("/buy/servlet?cart_map=" + enCartMapStr).forward(request, response);
        return;
    }

    for (Map.Entry<String, String> cartMap : cartMaps.entrySet()) {
        UserCart userCart = new UserCart();
        userCart.setId(Integer.parseInt(cartMap.getKey()));
        userCart.setCounts(Integer.parseInt(cartMap.getValue()));
        userCarts.add(userCart);
    }


%><%
    User user = new User();
    if (session.getAttribute("userinfo") != null) {
        user = (User) session.getAttribute("userinfo");
    }
    String username = user.getUserName();
%>


<div class="width100" style="height:25ox;background:#f0f0f0;">
    <div class="width1200 center_yh font14 c_66" style="height:25px;line-height:25px;">
        <font class="left_yh">欢迎来到长大服装店！</font>
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

                    List<Category> list = (List<Category>) request.getAttribute("categoryList");
                    if (list == null || list.size() == 0) {
                        request.getRequestDispatcher("/buy/servlet").forward(request, response);
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
    <span>当前位置:</span><a href="#" class="c_66">首页></a><a href="#" class="c_66">购物车></a><a href="#" class="c_66">结算</a>
</div>
<!--第一步为on1,on2-->
<div class="width1200 hidden_yh center_yh on1" style="height:45px;">
    <img src="static/images/bz2.png" width="1200" height="90">
</div>


</body>
</html>
