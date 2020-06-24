<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="application/include/taglib.jsp" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>登录页面</title>
        <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css">
        <script src="${ysdrzp}/layui/layui.js"></script>
        <script>
            var ysdrzp = "${ysdrzp}";
        </script>
    </head>

    <body>

        <style type="text/css">
            body{overflow:hidden;}
            .video_mask{ width:100%; height:100%; position:absolute; left:0; top:0; z-index:90; background-image: url("${ysdrzp}/image/03_bg.jpg");}
            .login{ height:260px;width:260px;padding: 20px;background-color:rgba(0,0,0,0.5);border-radius: 4px;position:absolute;left: 50%;top: 50%; margin:-150px 0 0 -150px;z-index:99;}
            .login h1{ text-align:center; color:#fff; font-size:24px; margin-bottom:20px; }
            .login_btn{ width:100%; }
        </style>

        <div class="video_mask"></div>
        <div class="login">
            <h1>欢迎你</h1>
            <form class="layui-form" id="form">
                <div class="layui-form-item">
                    <input class="layui-input" name="mobilePhone" placeholder="手机号" value="18616260963" lay-verify="required" type="text" autocomplete="off">
                </div>
                <div class="layui-form-item">
                    <input class="layui-input" name="password" placeholder="密码" value="123456"  lay-verify="required" type="password" autocomplete="off">
                </div>
                <button class="layui-btn login_btn" lay-submit lay-filter="login" id="btn">登录</button>
            </form>
        </div>

        <script type="">
            layui.use(['form','jquery','layer'], function(){
                var form = layui.form, $ = layui.jquery, layer = layui.layer;

                form.on('submit(login)', function(data){
                    let mobilePhone = data.field.mobilePhone;
                    $.ajax({
                        type:'post',
                        url:'${ysdrzp}/user/login',
                        contentType:'application/json;charset=utf-8',
                        data:JSON.stringify(data.field),
                        dataType:'json',
                        success:function(result){
                            var msg = result.msg;
                            layer.msg(msg, {time:1000}, function(){
                                layer.close(layer.index);
                                if (result.code == 0){
                                    let token = result.data;
                                    console.log(token);
                                    window.location.href = '${ysdrzp}/index.jsp';
                                    /*$.ajax({
                                        type:'get',
                                        sync: true,
                                        url:'${ysdrzp}/user/openIndex?token=' + token + "&mobilePhone=" + mobilePhone,
                                        success: function(open){

                                        }
                                    });*/
                                }
                            });
                        }
                    });
                    return false;
                });
            })

        </script>
    </body>

</html>