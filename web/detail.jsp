<%@ page import="java.util.List" %>
<%@ page import="entity.*" %><%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/4
  Time: 11:32
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
    <title>商品详情</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
    <script src="static/js/jquery-1.8.3.min.js"></script>
    <script src="static/js/jquery.luara.0.0.1.min.js"></script>
    <script src="static/js/163css.js"></script>
    <script src="static/js/lib.js"></script>
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
                        request.getRequestDispatcher("/item/detail/servlet").forward(request, response);
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
                            <a href="#"><%=cate2.getName()%>
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
    <%--面包屑--%>

    <%
        List<Category> breadCateList = (List<Category>) request.getAttribute("breadCateList");
        Item item = (Item) request.getAttribute("item");
        List<ItemAlbum> itemAlbums = (List<ItemAlbum>) request.getAttribute("itemAlbums");
    %>
    <a href="http://localhost:8080/index.jsp" class="c_66">首页></a>
    <% for (Category cate : breadCateList) {%>

    <a href="http://localhost:8080/list.jsp?cate_id=<%=cate.getId()%>" class="c_66"><%=cate.getName()%> >

        <%
            }
        %>
    </a><a href="#" class="c_66"><%=item.getName()%>
</a>
</div>
<!--商品详情-->
<div class="width1200 center_yh hidden_yh" style="height:420px;">
    <!--相册-->
    <div class="left_yh" style="width:350px;height:420px;">
        <div id="preview">

            <div class=jqzoom id="spec-n1" onClick="window.open('/')">
                <IMG height="350" src="<%=item.getCover()%>"
                     jqimg="<%=item.getCover()%>" width="350">
            </div>
            <div id="spec-n5">
                <div class=control id="spec-left">
                    <img src="static/images/left.gif">
                </div>
                <div id="spec-list">
                    <ul class="list-h">

                        <%
                            for (ItemAlbum itemAlbum : itemAlbums) {
                        %>
                        <li><img src="<%=itemAlbum.getImage()%>"></li>
                        <%}%>
                    </ul>
                </div>
                <div class=control id="spec-right">
                    <img src="static/images/right.gif">
                </div>
            </div>
        </div>
    </div>


    <!--商品详情-->
    <div class="left_yh" style="width:486px;height:420px;margin-left:42px;">
        <h3 class="font20 font100"><%=item.getName()%>
        </h3>
        <p class="font16 c_66" style="margin-top:25px;">
            <font style="margin-right:20px;">价格:</font>
            <font class="red font20">
                <em class="font14" style="font-style:normal">￥</em>
                <span id="item_price"><%=item.getNowPrice()%></span>
            </font>
            <font class="font14 c_66">(运费10元)</font>
        </p>
        <%--<p class="font16 c_66" style="margin-top:25px;">--%>
        <%--<font style="margin-right:20px;">已卖:</font>--%>
        <%--<font class="">126件</font>--%>
        <%--</p>--%>

        <%--类型--%>
        <%
            List<OptionType> optionTypes = (List<OptionType>) request.getAttribute("optionTypes");

        %>
        <%for (OptionType opt : optionTypes) {%>
        <p class="font16 c_66 likeColor" style="margin-top:25px;">
            <font style="margin-right:20px;" id="opt_<%=opt.getId()%>%>"><%=opt.getName()%>:</font>
            <% for (ItemOption itemOption : opt.getItemOptions()) {%>

            <span id="itemoption_<%=itemOption.getId()%>_<%=itemOption.getPrice()%>"><%=itemOption.getText()%></span>

            <%}%>
        </p>
        <%}%>
        <p class="font16 c_66 likeColor" style="margin-top:25px;">
            <font style="margin-right:20px;">数量:</font>
            <a href="javascript:void(0)" id="min_s">-</a>
            <input type="text" value="1" readonly id="t_a">
            <a href="javascript:void(0)" id="add_s">+</a>
            <font class="font14 c_99" style="margin-left:20px;">库存<%=item.getStockQty()%>件</font>
        </p>
        <div class="buyFor">
            <a href="javascript:void(0)" class="mstBuy">立即购买</a>
            <a onclick="AddCart()" class="addCar">加入购物车</a>
        </div>
        <div class="jOy">
            <a href="javascript:void(0)">收藏</a>
            <a href="javascript:void(0)">分享</a>
        </div>
    </div>
    <div class="right_yh"
         style="width:198px;height:418px;margin-left:42px;padding-left:40px;padding-right:40px;border:1px solid #ddd">
        <h3 class="tcenter font16 font100" style="margin-top:10px;">------&nbsp;今日推荐&nbsp;------</h3>
        <a href="#" class="lftSp">
            <img src="static/images/T1CtvCXaFjXXcVIIsU_014122.jpg">
            <span class="yihang">男衬衫</span>
            <p class="tcenter c_66">￥128.0</p>
        </a>

    </div>
</div>
<!--左边和右边-->
<div class="width1200 hidden_yh center_yh" style="margin-top:40px;">
    <div class="left_yh hidden_yh" style="width:270px;border:1px solid #ddd;padding-bottom:36px;">
        <h3 class="font16 c_33 font100 tcenter" style="padding-top:10px;padding-bottom:10px;">购买过该商品的用户还购买了</h3>
        <a href="#" class="center_yh hidden_yh block_yh" style="width:250px;margin-top:25px;">
            <img src="static/images/T1CtvCXaFjXXcVIIsU_014122.jpg" class="left_yh" width="105" height="115">
            <div class="left_yh" style="width:120px;margin-left:25px;">
                <h4 class="font14 c_33 font100" style="margin-top:6px;">男衬衫</h4>
                <p class="red font14" style="margin-top:6px;">￥128.0</p>
            </div>
        </a>

    </div>
    <div class="right_yh" style="width:888px;">
        <div class="hidden_yh" id="spXqpj">
            <a href="javascript:void(0)" class="on">商品详情</a>
            <a href="javascript:void(0)">商品评价</a>
        </div>
        <div class="width100 hidden_yh">
            <!--商品详情-->
            <div id="spDetil">
                <div class="spGg">
                    <h3 class="font16 c_33 font100">产品参数</h3>
                    <ul class="gGlb">
                        <% List<ItemAttr> itemAttrs = (List<ItemAttr>) request.getAttribute("itemAttrs"); %>
                        <%for (ItemAttr itemAttr : itemAttrs) {%>
                        <li><%=itemAttr.getCateAttr().getName()%>:<%=itemAttr.getValue()%>
                        </li>
                        <%}%>
                    </ul>
                </div>
                <%--这里是商品介绍--%>
                <div>这是一件非常好的商品
                    </br>
                    应该放商品介绍图片
                </div>

                <%--<img src="static/images/n,.jpg" class="spPic">--%>
            </div>
            <!--商品评价-->
            <div id="spPj">
                <div class="pjYxz">
                    <a href="javascript:void(0)"><input type="radio" name="as" checked id="nn0"
                                                        style="margin-right:8px;"><label for="nn0">全部</label></a>
                    <a href="javascript:void(0)"><input type="radio" name="as" id="nn1" style="margin-right:8px;"><label
                            for="nn1">好评</label></a>
                    <a href="javascript:void(0)"><input type="radio" name="as" id="nn2" style="margin-right:8px;"><label
                            for="nn2">差评</label></a>
                </div>
                <div class="pjBox">
                    <!--全部-->
                    <div class="pjBoxA">
                        <!--一条-->
                        <div class="width100 hidden_yh"
                             style="padding-bottom:20px;border-bottom:1px dashed #ddd;margin-bottom:10px;">
                            <img src="static/images/x.jpg"
                                 style="width:40px;height:40px;border-radius:50%;float:left;border:1px solid #ddd;">
                            <div style="width:790px;float:right;overflow:hidden;">
                                <h3 class="font16 c_33 font100">小***微</h3>
                                <p class="font14 c_99" style="margin-top:6px;">2017-7-7 墨绿色,15cm</p>
                                <p class="font16 c_33" style="margin-top:6px;">(所有)非常漂亮，挂在家里朋友们看了都很喜欢。</p>
                            </div>
                        </div>
                        <div id="navs">
                            <div id="nav_in">
                                <a href="javascript:void(0)">«</a>
                                <a href="javascript:void(0)">1</a>
                                <a href="javascript:void(0)">2</a>
                                <a href="javascript:void(0)">3</a>
                                <a href="javascript:void(0)">4</a>
                                <a href="javascript:void(0)">5</a>
                                <a href="javascript:void(0)">»</a>
                            </div>
                        </div>
                    </div>
                    <!--好评-->
                    <div class="pjBoxG">
                        <div class="width100 hidden_yh"
                             style="padding-bottom:20px;border-bottom:1px dashed #ddd;margin-bottom:10px;">
                            <img src="static/images/x.jpg"
                                 style="width:40px;height:40px;border-radius:50%;float:left;border:1px solid #ddd;">
                            <div style="width:790px;float:right;overflow:hidden;">
                                <h3 class="font16 c_33 font100">小***微</h3>
                                <p class="font14 c_99" style="margin-top:6px;">2017-7-7 墨绿色,15cm</p>
                                <p class="font16 c_33" style="margin-top:6px;">(好)非常漂亮，挂在家里朋友们看了都很喜欢。</p>
                            </div>
                        </div>
                        <div id="navs">
                            <div id="nav_in">
                                <a href="javascript:void(0)">«</a>
                                <a href="javascript:void(0)">1</a>
                                <a href="javascript:void(0)">2</a>
                                <a href="javascript:void(0)">3</a>
                                <a href="javascript:void(0)">4</a>
                                <a href="javascript:void(0)">5</a>
                                <a href="javascript:void(0)">»</a>
                            </div>
                        </div>
                    </div>
                    <!--差评-->
                    <div class="pjBoxL">
                        <div class="width100 hidden_yh"
                             style="padding-bottom:20px;border-bottom:1px dashed #ddd;margin-bottom:10px;">
                            <img src="static/images/x.jpg"
                                 style="width:40px;height:40px;border-radius:50%;float:left;border:1px solid #ddd;">
                            <div style="width:790px;float:right;overflow:hidden;">
                                <h3 class="font16 c_33 font100">小***微</h3>
                                <p class="font14 c_99" style="margin-top:6px;">2017-7-7 墨绿色,15cm</p>
                                <p class="font16 c_33" style="margin-top:6px;">(差)非常漂亮，挂在家里朋友们看了都很喜欢。</p>
                            </div>
                        </div>
                        <div id="navs">
                            <div id="nav_in">
                                <a href="javascript:void(0)">«</a>
                                <a href="javascript:void(0)">1</a>
                                <a href="javascript:void(0)">2</a>
                                <a href="javascript:void(0)">3</a>
                                <a href="javascript:void(0)">4</a>
                                <a href="javascript:void(0)">5</a>
                                <a href="javascript:void(0)">»</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--品质保证-->
<div class="width1200 center_yh" style="height:130px;margin-top:60px;">
    <img src="static/images/db.png">
</div>
<!--页脚-->

<jsp:include page="footer.jsp" flush="true"/>
</body>
<script type=text/javascript>
    $(function () {
        $(".jqzoom").jqueryzoom({
            xzoom: 400,
            yzoom: 400,
            offset: 10,
            position: "right",
            preload: 1,
            lens: 1
        });
        $("#spec-list").jdMarquee({
            deriction: "left",
            width: 350,
            height: 56,
            step: 2,
            speed: 4,
            delay: 10,
            control: true,
            _front: "#spec-right",
            _back: "#spec-left"
        });
        $("#spec-list img").bind("mouseover", function () {
            var src = $(this).attr("src");
            $("#spec-n1 img").eq(0).attr({
                src: src.replace("\/n5\/", "\/n1\/"),
                jqimg: src.replace("\/n5\/", "\/n0\/")
            });
            $(this).css({
                "border": "2px solid #ff6600",
                "padding": "1px"
            });
        }).bind("mouseout", function () {
            $(this).css({
                "border": "",
                "padding": "0"
            });
        });
    })
</script>
<script>
    $("#spXqpj a").click(function () {
        $(this).addClass("on").siblings().removeClass("on")

    });
    $("#spXqpj a").eq(0).click(function () {
        $("#spDetil").css({display: "block"}).siblings().css({display: "none"});
    })
    $("#spXqpj a").eq(1).click(function () {
        $("#spPj").css({display: "block"}).siblings().css({display: "none"});
    })
    $(".pjYxz a").eq(0).click(function () {
        $(".pjBoxA").css({display: "block"}).siblings().css({display: "none"});
    })
    $(".pjYxz a").eq(1).click(function () {
        $(".pjBoxG").css({display: "block"}).siblings().css({display: "none"});
    })
    $(".pjYxz a").eq(2).click(function () {
        $(".pjBoxL").css({display: "block"}).siblings().css({display: "none"});
    })
</script>
<!--控制数量加减-->
<script>
    var price = 0 * 1;


    $(".likeColor span").click(function () {

        $(this).addClass("on").siblings().removeClass("on");
        id = $(this).attr("id");
        option_price = id.split('_');
        price += option_price[2] * 1;
        if (price != 0) {
            $("#item_price").replaceWith("<span id='item_price'>" + price + "</span>")
        }
        price = 0 * 1;
    })


    var tr = $("#t_a").val();
    $("#add_s").click(function () {
        tr++;
        $("#t_a").val(tr)
    })
    $("#min_s").click(function () {
        tr--;
        if (tr < 1) {
            $("#t_a").val(1);
            tr = 1
        } else {
            $("#t_a").val(tr);
        }
        $("#ano").html(tr);
    })
</script>
<script>

    function AddCart() {
        var optionArray = [];
        $(".likeColor .on").each(function (index, el) {

            id = $(this).attr("id");
            option_id = id.split('_')[1];
            console.log(option_id);
            optionArray.push(option_id);
        });
        count = $("#t_a").val();


        console.log(optionArray)
        item_id = <%=item.getId()%>;
        $.ajax({
                url: 'http://localhost:8080/cart/add/servlet',
                type: 'post',
                data: {
                    'option_map': optionArray,
                    'count': count,
                    'item_id': item_id,
                },
                dataType: 'json',
                contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
                cache: false,
                success: function (response) {
                    if (response.status == 10000) {
                        alert(response.message);
                    } else if (response.status == 10001) {
                        alert(response.message);
                        window.location.href = "http://localhost:8080/login.jsp"
                    } else if (response.status == 10003) {
                        alert(response.message);
                        return;
                    } else {
                        console.log(response);
                        alert("服务器错误");
                    }
                },
                error: function () {
                    alert("服务器错误");
                }
            }
        )
    }


</script>
</html>
