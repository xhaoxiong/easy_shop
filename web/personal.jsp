<%@ page import="entity.Category" %>
<%@ page import="entity.Navigation" %>
<%@ page import="entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/5
  Time: 11:20
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
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
    <script src="static/js/jquery-1.8.3.min.js"></script>
    <script src="static/js/jquery.luara.0.0.1.min.js"></script>
    <script src="static/js/163css.js"></script>
    <script src="static/js/lib.js"></script>
</head>
<body>
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

                    List<Category> list = (List<Category>) request.getAttribute("categoryList");
                    if (list == null || list.size() == 0) {
                        request.getRequestDispatcher("/personal/index/servlet").forward(request, response);
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
    <span>当前位置:</span><a href="#" class="c_66">首页></a><a href="#" class="c_66">我的账户></a>
</div>
<div class="width100 hidden_yh" style="background:#f0f0f0;padding-top:34px;padding-bottom:34px;">
    <div class="width1200 hidden_yh center_yh">
        <div id="vipNav">
            <a href="#" class="on">账户总览</a>
            <a href="#">个人信息</a>
            <a href="#">我的订单</a>
            <a href="#">商品收藏</a>
            <a href="#">我的购物车</a>
            <a href="#">修改密码</a>
            <a href="#">售后管理</a>
            <a href="#">收货地址</a>
        </div>
        <div id="vipRight">
            <div class="hidden_yh bj_fff" style="width:938px;border:1px solid #ddd;">
                <div class="width100 hidden_yh" style="height:122px;background:#f5f8fa;">
                    <div class="left_yh hidden_yh" style="margin-top:32px;margin-left:50px;">
                        <img src="static/images/n,.jpg" width="60" height="60" class="left_yh"
                             style="border-radius:50%;margin-right:34px;">
                        <div class="left_yh">
                            <h3 class="font24 c_33 font100"><%=username%>
                            </h3>
                            <p class="font20 c_66" style="margin-top:4px;"><%=user.getPhone()%>
                            </p>
                        </div>
                    </div>
                    <div class="left_yh hidden_yh font20" style="margin-left:150px;line-height:122px;">
                        <a href="#" style="color:#ff5802;" class="onHover">我的收获地址</a>
                    </div>
                    <div class="left_yh hidden_yh font20" style="margin-left:150px;line-height:122px;">
                        <a href="#" style="color:#ff5802;" class="onHover">我的收藏</a>
                    </div>
                </div>
                <ul class="width100 hidden_yh" style="height:78px;border-top:1px solid #ddd;">
                    <li class="left_yh tcenter font20"
                        style="width:233.5px;border-right:1px solid #ddd;height:14px;margin-top:32px;line-height:10px;">
                        待付款<font>(0)</font></li>
                    <li class="left_yh tcenter font20"
                        style="width:233.5px;border-right:1px solid #ddd;height:14px;margin-top:32px;line-height:10px;">
                        待发货<font>(0)</font></li>
                    <li class="left_yh tcenter font20"
                        style="width:233.5px;border-right:1px solid #ddd;height:14px;margin-top:32px;line-height:10px;">
                        待收货<font>(0)</font></li>
                    <li class="left_yh tcenter font20" style="width:233.5px;margin-top:32px;line-height:10px;">待评价<font>(0)</font>
                    </li>
                </ul>
            </div>
            <div class="hidden_yh bj_fff" style="width:938px;border:1px solid #ddd;margin-top:26px;">
                <div class="width100 font24"
                     style="height:60px;line-height:60px;text-indent:50px;background:#f5f8fa;border-bottom:1px solid #ddd;">
                    最近订单
                </div>
                <div class="width100" style="height:60px;line-height:60px;border-bottom:1px solid #ddd">
                    <div class="left_yh tcenter font20" style="width:16.4%">订单号</div>
                    <div class="left_yh tcenter font20" style="width:16.4%">金额</div>
                    <div class="left_yh tcenter font20" style="width:18%">下单时间</div>
                    <div class="left_yh tcenter font20" style="width:16.4%">订单状态</div>
                    <div class="left_yh tcenter font20" style="width:16.4%">支付方式</div>
                    <div class="left_yh tcenter font20" style="width:16.4%">操作</div>
                </div>
                <!--一条-->
                <div class="width100 hidden_yh" style="height:45px;line-height:45px;">
                    <div class="left_yh tcenter font20 c_66" style="width:16.4%">600123456001</div>
                    <div class="left_yh tcenter font20 red" style="width:16.4%">￥268.30</div>
                    <div class="left_yh tcenter font20 c_66" style="width:18%">2017.09.28 14:30</div>
                    <div class="left_yh tcenter font20 c_66" style="width:16.4%">待收货</div>
                    <div class="left_yh tcenter font20 c_66" style="width:16.4%">在线支付</div>
                    <div class="left_yh tcenter font20 c_66" style="width:16.4%">确认收货</div>
                </div>
                <div class="width100 hidden_yh" style="height:45px;line-height:45px;">
                    <div class="left_yh tcenter font20 c_66" style="width:16.4%">600123456001</div>
                    <div class="left_yh tcenter font20 red" style="width:16.4%">￥268.30</div>
                    <div class="left_yh tcenter font20 c_66" style="width:18%">2017.09.28 14:30</div>
                    <div class="left_yh tcenter font20 c_66" style="width:16.4%">待收货</div>
                    <div class="left_yh tcenter font20 c_66" style="width:16.4%">在线支付</div>
                    <div class="left_yh tcenter font20 c_66" style="width:16.4%">去评价</div>
                </div>
            </div>
            <div class="hidden_yh bj_fff" style="width:938px;border:1px solid #ddd;margin-top:26px;">
                <div class="width100 font24"
                     style="height:60px;line-height:60px;text-indent:50px;background:#f5f8fa;border-bottom:1px solid #ddd;">
                    最近收藏
                </div>
                <div class="hidden_yh" style="width:916px;padding:20px;padding-bottom:0;">
                    <a href="javascript:void(0)" class="navSc">
                        <img src="static/images/xc.png">
                        <h3>2017玉石摆件客厅装饰品玄关柜招财工艺品摆设</h3>
                        <p class="font16 red" style="margin-top:10px;">￥268.00</p>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
