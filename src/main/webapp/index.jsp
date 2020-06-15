<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>OA 后台管理系统管理</title>
        <link rel="stylesheet" href="layui/css/layui.css">
        <script src="layui/layui.js"></script>

        <script>
            var ctx = "${ctx}";
        </script>
    </head>

    <body>
        <div class="layui-layout-admin">
            <div class="layui-header">
                <div class="layui-logo">OA 后台管理系统</div>
                <ul class="layui-nav layui-layout-left">
                    <li class="layui-nav-item"><a href="javascript:void(0)">控制台</a></li>
                </ul>
                <ul class="layui-nav layui-layout-right">
                    <li class="layui-nav-item">
                        <a href=""><img src="" class="layui-nav-img">翟</a>
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
                                <dd><a href="${ctx}/orgInfo/list">组织机构</a></dd>
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
                    <table class="layui-table">
                        <thead>
                            <tr>
                                <th>昵称</th>
                                <th>加入时间</th>
                                <th>签名</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>贤心</td>
                                <td>2016-11-29</td>
                                <td>人生就像是一场修行</td>
                            </tr>
                            <tr>
                                <td>许闲心</td>
                                <td>2016-11-28</td>
                                <td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="layui-footer">
                <center>@ysdrzp</center>
            </div>
        </div>

        <script>
            layui.use('element', function(){
                var element = layui.element;
                var openTitle = '机构管理';
                var content =  null;
                element.tabAdd("sideNav",{
                    title : openTitle,
                    content : content,
                    url : {ctx} + "/orgInfo/list",
                    id : new Date().getTime()
                });
            });
        </script>

    </body>

</html>