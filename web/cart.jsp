<%@ page import="java.util.List" %>
<%@ page import="entity.*" %><%--
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
    <title>用户购物车</title>
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
                        request.getRequestDispatcher("/cart/list/servlet").forward(request, response);
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
<!--导航条-->

<!--当前位置-->
<div class="width1200 center_yh hidden_yh font14" style="height:40px;line-height:40px;">
    <span>当前位置:</span><a href="http://localhost:8080/index.jsp" class="c_66">首页></a><a href="#" class="c_66">我的账户></a><a
        href="http://localhost:8080/cart.jsp" class="c_66">购物车</a>
</div>
<!--全部商品-->
<%
    List<UserCart> userCarts = (List<UserCart>) request.getAttribute("userCarts");
%>
<div class="width1200 hidden_yh center_yh font20">全部商品<font class="red">(<%=userCarts.size()%>)</font></div>
<!--商品详情-->
<div class="width1198 hidden_yh center_yh" style="border:1px solid #ddd;margin-top:18px;min-height:300px;">
    <div class="width100 hidden_yh font14"
         style="height:32px;line-height:32px;background:#f0f0f0;text-indent:21px;color:#000;font-weight:600;border-bottom:1px solid #ddd;">
        商品详情
    </div>
    <!--列表头部-->
    <div class="width100 hidden_yh font14" style="height:42px;line-height:42px;border-bottom:1px solid #ddd;">
        <div class="left_yh tcenter font14" style="width:486px;">商品</div>
        <div class="left_yh tcenter font14" style="width:175.75px;">价格</div>
        <div class="left_yh tcenter font14" style="width:175.75px;">数量</div>
        <div class="left_yh tcenter font14" style="width:175.75px;">小计</div>
        <div class="left_yh tcenter font14" style="width:175.75px;">操作</div>
    </div>
    <!--具体的商品-->


    <%

        for (UserCart userCart : userCarts) {
    %>
    <div class="speCific">
        <div class="xzWxz" id="select_<%=userCart.getId()%>">
            <b><img src="static/images/xzwxz.png"></b>
        </div>

        <div class="xzSp">
            <img src="<%=userCart.getItem().getCover()%>">
            <div class="xzSpIn">
                <h3 class="font16 c_33 font100"><%=userCart.getItem().getName()%>
                </h3>
                <p class="font13 c_99">
                    <%for (ItemOption itemOption : userCart.getItemOption()) {%>
                    <%=itemOption.getOptionType().getName()%>:<%=itemOption.getText()%>
                    <%}%>
                </p>
            </div>
        </div>
        <div class="xzJg">￥<font><%=userCart.getPrice()%>
        </font></div>
        <div class="xzSl">
            <div class="xzSlIn">
                <b class="Amin">减</b>
                <input type="text" value="1" readonly class="cOnt">
                <b style="border-right:none;border-left:1px solid #ddd;" class="Aadd" onClick="zj(this)">加</b>
            </div>
        </div>
        <div class="xzXj">￥<font></font></div>
        <div class="xzCz">
            <div class="xzCzIn">
                <a href="javascript:void(0)" class="jRscj">移入收藏夹</a>
                <a onclick="Remove(<%=userCart.getId()%>)" id="usercart_<%=userCart.getId()%>" class="Dels">删除</a>
            </div>
        </div>
    </div>
    <%}%>
</div>
<!--合计-->
<div class="width1198 center_yh" style="height:60px;background:#f0f0f0;border:1px solid #ddd;margin-top:40px;">
    <div class="center_yh hidden_yh" style="width:1178px;height:60px;margin-left:20px;">
        <div class="ifAll"><b><img src="static/images/xzwxz2.png"></b><font>全选</font></div>
        <div class="ifDel">删除</div>
        <div class="ifRemove">移入收藏夹</div>
        <div class="sXd">
            <div class="sXd1">已选商品(<font style="color:#cb1c20">0</font>)件</div>
            <div class="sXd2">合计(不含运费):￥<font style="color:#cb1c20" id="zjJg">0</font></div>
            <a onclick="buy()" class="ifJs">结算</a>
        </div>
    </div>
</div>
<script>

    (function cx() {
        var e = $(".speCific").length
        for (var a = 0; a < e; a++) {
            var lt = $(".xzJg").eq(a).find("font").html()
            $(".xzXj").eq(a).find("font").html(lt)
        }
    })();

    var allPrice = 0;
    var es = $(".speCific").length;
    $(".xzWxz").click(function () {
        if ($(this).hasClass("on")) {
            $(this).removeClass("on")
            var ty = $(".xzWxz.on").length;
            $(".sXd1").find("font").html(ty);
            if (ty != es) {
                $(".ifAll").removeClass("on")
            }
            allPrice -= $(this).siblings(".xzXj").find("font").html() * 1
            console.log(allPrice);
            $("#zjJg").text(allPrice)
        } else {
            $(this).addClass("on");
            var hj = $(this).siblings(".xzXj").find("font").html() * 1;
            var ty = $(".xzWxz.on").length;
            $(".sXd1").find("font").html(ty);
            $("#zjJg").html()
            if (ty != es) {
                $(".ifAll").removeClass("on")
            }
            allPrice += $(this).siblings(".xzXj").find("font").html() * 1;
            $("#zjJg").text(allPrice)
        }
    })
    $(".jRscj").click(function () {
        if ($(this).hasClass("on")) {
            $(this).removeClass("on").html("移入收藏夹")
        } else {
            $(this).addClass("on").html("已收藏")
        }
    })

    $(".Aadd").click(function () {
        var t = $(this).siblings(".cOnt").val() * 1;
        var price = $(this).parent().parent().siblings(".xzJg").find("font").html() * 1;
        t++;
        $(this).siblings(".cOnt").val(t);
        $(this).parent().parent().siblings(".xzXj").find("font").html(sswr(price * t))
        console.log("aadd:", price)
        if ($(this).parent().parent().siblings(".xzWxz").hasClass("on")) {
            allPrice += price;
            $("#zjJg").text(allPrice)
        } else {
            $("#zjJg").text(allPrice)
        }

    })
    $(".Amin").click(function () {
        var t = $(this).siblings(".cOnt").val() * 1;
        var price = $(this).parent().parent().siblings(".xzJg").find("font").html() * 1;
        t--

        if ($(this).parent().parent().siblings(".xzWxz").hasClass("on")) {
            console.log($(this).parent().parent().siblings(".xzWxz").hasClass("on"))

            allPrice -= price;
            if (t < 1) {
                allPrice = $("#zjJg").text() * 1;
                $("#zjJg").text(allPrice);
            } else {
                $("#zjJg").text(allPrice)
            }
        } else {
            $("#zjJg").text(allPrice)
        }
        if (t < 1) {
            t = 1
        }

        $(this).siblings(".cOnt").val(t);
        $(this).parent().parent().siblings(".xzXj").find("font").html(sswr(price * t))
        console.log("admin:", price)

    })

    function sswr(num) {
        return (
            num.toFixed(2)
        )
    }

    $(".ifDel").click(function () {
        $(".xzWxz.on").parent().remove();
    })
    $(".ifAll").click(function () {
        if ($(".ifAll").hasClass("on")) {
            $(this).removeClass("on");
            $(".xzWxz").removeClass("on");

            $(".sXd1").find("font").html(0);
        } else {
            $(this).addClass("on");
            $(".xzWxz").addClass("on");

            $(".sXd1").find("font").html(es);
        }
    })


    function zj(obj) {
        var zj = $(obj).parent().parent().siblings(".xzXj").find("font").html() * 1;

    }


    function Remove(userCartId) {
        console.log(userCartId);
        $(".Dels").click(function () {
            $(this).parent().parent().parent().remove();
        })
        $.ajax({
            url: "http://localhost:8080/cart/remove/servlet",
            type: "post",
            data: {
                "user_cart_id": userCartId,
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                console.log(data);
                if (data.status == 10000) {

                    $("#usercart_" + userCartId).parent().parent().parent().remove();
                } else {
                    alert("移除失败");
                }
            },
            error: function () {
                alert("服务器错误")
            }
        })
    }

    function buy() {
        var cart_map = {};
        $(".speCific .xzWxz.on").each(function () {
            cart_id = $(this).attr("id").split("_");
            counts = $(this).siblings(".xzSl").find("input").val();
            cart_map[cart_id[1]] = counts
        })

        if (cart_map == null) {
            alert("请选择购买的商品")
            return;
        }
        window.location.href = "http://localhost:8080/buy.jsp?cart_map=" + encodeURI(JSON.stringify(cart_map));

//        $.ajax({
//                url: 'http://localhost:8080/buy/servlet',
//                type: 'get',
//                data: {
//                    'cart_map': JSON.stringify(cart_map),
//                },
//                dataType: 'json',
//                contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
//                cache: false,
//                success: function (response) {
//                    if (response.status == 10000) {
//                        alert(response.message);
//                    } else if (response.status == 10001) {
//                        alert(response.message);
//                        window.location.href = "http://localhost:8080/login.jsp"
//                    } else if (response.status == 10003) {
//                        alert(response.message);
//                        return;
//                    } else {
//                        console.log(response);
//                        alert("服务器错误");
//                    }
//                },
//                error: function () {
//                    alert("服务器错误");
//                }
//            }
//        )
    }

</script>
<script src="static/js/jquery-3.2.1.js"></script>

</body>
</html>
