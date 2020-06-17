<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="application/include/taglib.jsp" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>OA 后台管理系统管理</title>
        <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css">
        <script>
            var ysdrzp = "${ysdrzp}";
        </script>
    </head>

    <body>
        <div class="layui-layout layui-layout-admin">
            <div class="layui-header">
                <div class="layui-logo">OA 后台管理系统</div>
                <ul class="layui-nav layui-layout-left">
                    <li class="layui-nav-item"><a href="javascript:void(0)">控制台</a></li>
                </ul>
                <ul class="layui-nav layui-layout-right">
                    <li class="layui-nav-item">
                        <a href=""><img src="" class="layui-nav-img">江湖最后一个大佬</a>
                        <dl class="layui-nav-child">
                            <dd><a href="">基本资料</a></dd>
                            <dd><a href="">修改密码</a></dd>
                            <dd><a href="">退出</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>

            <div class="layui-side layui-bg-black">
                <div class="layui-side-scroll">
                    <ul class="layui-nav layui-nav-tree" lay-filter="sideNav">
                        <li class="layui-nav-item">
                            <a href="javascript:;">机构管理</a>
                            <dl class="layui-nav-child">
                                <dd><a href="${ysdrzp}/application/org/org_list.jsp">组织机构</a></dd>
                                <dd><a href="">用户列表</a></dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;">系统设置</a>
                            <dl class="layui-nav-child">
                                <dd><a href="">角色管理</a></dd>
                                <dd><a href="">资源配置</a></dd>
                            </dl>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="layui-body">
                <div class="layui-tab-content">
                </div>
            </div>

            <div class="layui-footer">
                <center>@ysdrzp</center>
            </div>
        </div>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script>
            layui.use('element', function () {
                var element = layui.element;
                element.tabAdd('sideNav',{

                });
            });
        </script>

    </body>

</html>