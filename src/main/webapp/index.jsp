<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>主页</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <script>
        var ctx = "${ctx}";
    </script>
</head>

<body>
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <div class="layui-logo">OA管理系统</div>
            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;">
                        <img src="http://t.cn/RCzsdCq" class="layui-nav-img">贤心
                    </a>
                    <dl class="layui-nav-child">
                        <dd><a href="">个人资料</a></dd>
                        <dd><a href="">安全设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">退出</a></li>
            </ul>
        </div>

        <div class="layui-side layui-bg-black">
            <div class="layui-side-scroll">
                <ul class="layui-nav layui-nav-tree" lay-filter="test">
                    <li class="layui-nav-item layui-nav-itemed">
                        <a class="" href="javascript:;">管理员管理</a>
                        <a class="layui-btn layui-btn-normal userAdd_btn">添加用户</a>
                        <dl class="layui-nav-child">
                            <dd><a href="application/org/org_list.jsp">组织机构</a></dd>
                            <dd><a href="javascript:;">用户管理</a></dd>
                            <dd><a href="javascript:;">角色管理</a></dd>
                            <dd><a href="javascript:;">资源管理</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
        </div>

        <div class="layui-body layui-form">
            <div class="layui-tab marg0" lay-filter="bodyTab" id="top_tabs_box">
                <ul class="layui-tab-title top_tab" id="top_tabs">
                    <li class="layui-this" lay-id=""><i class="iconfont icon-computer"></i> <cite>后台首页</cite></li>
                </ul>
                <!-- 当前页面操作 -->
                <ul class="layui-nav closeBox">
                    <li class="layui-nav-item" pc>
                        <a href="javascript:;"><i class="iconfont icon-caozuo"></i> 页面操作</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#x1002;</i> 刷新当前</a></dd>
                            <dd><a href="javascript:;" class="closePageOther"><i class="iconfont icon-prohibit"></i> 关闭其他</a></dd>
                            <dd><a href="javascript:;" class="closePageAll"><i class="iconfont icon-guanbi"></i> 关闭全部</a></dd>
                        </dl>
                    </li>
                </ul>
                <div class="layui-tab-content clildFrame">
                    <div class="layui-tab-item layui-show">
                    </div>
                </div>
            </div>
        </div>

        <div class="layui-footer">
            © ysdrzp
        </div>
    </div>

    <script type="text/javascript" src="${ctx }/layui/layui.js"></script>

    <script>
        layui.config({
            base : "js/"
        })
        .use([ 'form', 'layer', 'jquery', 'table', 'laydate' ],
            function() {
                var $ = layui.jquery

                $(".userAdd_btn").click(function () {
                    var index = layui.layer.open({
                        title: "添加机构",
                        type: 2,
                        content: ctx + "/orgInfo/addOrg",
                        success: function (layero, index) {

                        }
                    })

                    //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
                    $(window).resize(function () {
                        layui.layer.full(index);
                    })
                    layui.layer.full(index);
                })
            });
    </script>

</body>

</html>