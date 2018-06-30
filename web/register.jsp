<%--
  Created by IntelliJ IDEA.
  User: haoxiongxiao
  Date: 2018/6/4
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
</head>
<body>
-<div class="width100">
    <div class="width1200 center_yh hidden_yh" style="height:181px">
        <a href="#" class="left_yh"><img src="static/images/logo.png"></a>
        <a href="/login.jsp" class="right_yh font16 c_33" style="line-height:181px;">已有帐号，<font
                class="red">马上去登录></font></a>
    </div>
</div>
<div class="width100 hidden_yh" style="border-top:1px solid #ddd">
    <div class="width1200 hidden_yh center_yh" style="margin-top:75px">
        <div class="width100 hidden_yh">

            <div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
                <span style="margin-right:40px;height:42px;line-height:42px;width:100px"
                      class="left_yh block_yh tright">用户名:</span>
                <input type="text" placeholder="请输入您的用户名"
                       style="border:1px solid #c9c9c9;width:292px;height:42px;font-size:16px;text-indent:22px;"
                       class="left_yh" id="username">
            </div>
            <div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
                <span style="margin-right:40px;height:42px;line-height:42px;width:100px"
                      class="left_yh block_yh tright">设置密码:</span>
                <input type="password" placeholder="建议至少使用两种字符组合"
                       style="border:1px solid #c9c9c9;width:292px;height:42px;font-size:16px;text-indent:22px;"
                       class="left_yh" id="password1">
            </div>
            <div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
                <span style="margin-right:40px;height:42px;line-height:42px;width:100px"
                      class="left_yh block_yh tright">确认密码:</span>
                <input type="password" placeholder="请再次输入密码"
                       style="border:1px solid #c9c9c9;width:292px;height:42px;font-size:16px;text-indent:22px;"
                       class="left_yh" id="password">
            </div>
            <div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
                <span style="margin-right:40px;height:42px;line-height:42px;width:100px"
                      class="left_yh block_yh tright">手机号:</span>
                <input type="text" placeholder="建议使用常用的手机"
                       style="border:1px solid #c9c9c9;width:292px;height:42px;font-size:16px;text-indent:22px;"
                       class="left_yh" id="mobile">
            </div>
            <div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
                <span style="margin-right:40px;height:42px;line-height:42px;width:100px"
                      class="left_yh block_yh tright">邮箱:</span>
                <input type="text" placeholder="建议使用常用的邮箱"
                       style="border:1px solid #c9c9c9;width:292px;height:42px;font-size:16px;text-indent:22px;"
                       class="left_yh" id="email">
            </div>
            <!--
            <div class="center_yh hidden_yh" style="width:475px;margin-bottom:40px;">
                <span style="margin-right:40px;height:42px;line-height:42px;width:100px"
                      class="left_yh block_yh tright">验证码:</span>
                <div style="width:293px;height:42px;border:1px solid #c9c9c9" class="left_yh">
                    <input type="text" placeholder="输入验证码"
                           style="border:0;width:184px;height:39px;font-size:16px;text-indent:22px;" class="left_yh">
                    <img src="static/images/s.jpg"
                         style="width:103px;height:40px;margin-top:1px;float:right;margin-right:1px;">
                </div>
            </div>

            <div class="center_yh hidden_yh" style="width:475px;">
                <span style="margin-right:40px;height:42px;line-height:42px;width:100px"
                      class="left_yh block_yh tright">手机验证码:</span>
                <div style="width:293px;height:42px;border:1px solid #c9c9c9" class="left_yh">
                    <input type="text" placeholder="请输入验证码"
                           style="border:0;width:184px;height:39px;font-size:16px;text-indent:22px;" class="left_yh">
                    <a href="javascript:void(0)"
                       style="width:103px;height:40px;margin-top:1px;float:right;margin-right:1px;display:block;color:#666;background:#ddd;text-align:center;line-height:40px;">获取验证码</a>
                </div>
            </div>
            -->
            <p class="font14 c_66" style="text-indent:503px;margin-top:30px;"><input type="checkbox">我已阅读并同意<a href="#"
                                                                                                               class="red">«会员注册协议»</a>和<a
                    href="#" class="red">«隐私保护政策»</a></p>
            <input type="submit" value="提交" onclick="register()" class="ipt_tj"
                   style="width:295px;height:44px;margin-left:505px;">
        </div>
    </div>
</div>

<jsp:include   page="footer.jsp" flush="true"/>
</body>
<script src="static/js/jquery-3.2.1.js"></script>
<script>
    function register() {
        username = $("#username").val();
        password = $("#password").val();
        password1 = $("#password1").val();
        mobile = $("#mobile").val();
        email = $("#email").val();
        console.log(username, password1, password, mobile, email);
        if (username == '' || password == '' || password1 == '' || mobile == '' || email == '') {
            alert("请检查是否填写完整");
            return;
        }
        if (password1 != password) {
            alert("两次输入密码不一致");
            return;
        }

        //正则处理手机号码
        var pattern = /[1][1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]/;
        if (!pattern.test(mobile)) {
            alert("请输入正确的手机号码");
            return;
        }

        //正则处理邮箱
        var parrern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!parrern.test(email)) {
            alert("请输入正确的邮箱");
            return;
        }

        console.log(username, password, mobile);
        $.ajax({
            url: "http://localhost:8080/user/register",
            type: "post",
            data: {
                "username": username,
                "password": password,
                "phone": mobile,
                "email": email
            },
            dataType: 'json',
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            cache: false,
            success: function (data) {
                console.log(data);
                if (data.status === 10000) {
                    alert("注册成功");
                    window.location.href = "http://localhost:8080/index.jsp"
                } else if (data.status === 10001) {
                    alert("该用户名已经注册")
                } else {
                    alert("注册失败");
                }
            },
            error: function () {
                alert("服务器错误")
            }
        })
    }


</script>

</html>
