<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="application/include/taglib.jsp" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>云上的日子</title>
        <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css">
        <script>
            var ysdrzp = "${ysdrzp}";
        </script>
    </head>

    <body>
        <div class="layui-layout layui-layout-admin">
            <div class="layui-header">
                <div class="layui-logo">云上的日子</div>
                <ul class="layui-nav layui-layout-left">
                    <li class="layui-nav-item"><a href="javascript:void(0)"><i class="layui-icon layui-icon-console"></i>&nbsp;控制台</a></li>
                </ul>
                <ul class="layui-nav layui-layout-right">
                    <li class="layui-nav-item">
                        <a href=""><img src="image/head.jpg" class="layui-nav-img">向往的生活</a>
                        <dl class="layui-nav-child">
                            <dd><a href="javascript:void(0)">消息未读&nbsp;<i class="layui-icon layui-icon-notice"></i></a></dd>
                            <dd><a href="javascript:void(0)"><i class="layui-icon layui-icon-logout"></i>&nbsp;退出</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>

            <div class="layui-side layui-bg-black">
                <div class="layui-side-scroll">
                    <ul class="layui-nav layui-nav-tree" lay-filter="">
                        <li class="layui-nav-item">
                            <a href="javascript:;"><i class="layui-icon layui-icon-group"></i>&nbsp;&nbsp;基础资料</a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a data-url="${ysdrzp}/application/sys/org/org_list.jsp" data-id="1111" data-title="机构管理" href="#" class="site-nav-active" data-type="tabAdd"><i class="layui-icon layui-icon-group"></i>&nbsp;&nbsp;&nbsp;机构管理</a>
                                </dd>
                                <dd>
                                    <a data-url="${ysdrzp}/application/sys/user/user_list.jsp" data-id="2222" data-title="用户管理" href="#" class="site-nav-active" data-type="tabAdd"><i class="layui-icon layui-icon-user"></i>&nbsp;&nbsp;&nbsp;用户管理</a>
                                </dd>
                            </dl>
                        </li>
                        <li class="layui-nav-item">
                            <a href="javascript:;"><i class="layui-icon layui-icon-set-sm"></i>&nbsp;&nbsp;系统设置</a>
                            <dl class="layui-nav-child">
                                <dd>
                                    <a data-url="${ysdrzp}/application/sys/org/org_list.jsp" data-id="3333" data-title="角色管理" href="#" class="site-nav-active" data-type="tabAdd"><i class="layui-icon layui-icon-group"></i>&nbsp;&nbsp;角色管理</a>
                                </dd>
                                <dd>
                                    <a data-url="${ysdrzp}/application/sys/org/org_list.jsp" data-id="4444" data-title="菜单管理" href="#" class="site-nav-active" data-type="tabAdd"><i class="layui-icon layui-icon-group"></i>&nbsp;&nbsp;菜单管理</a>
                                </dd>
                                <dd>
                                    <a data-url="${ysdrzp}/application/sys/org/org_list.jsp" data-id="5555" data-title="授权管理" href="#" class="site-nav-active" data-type="tabAdd"><i class="layui-icon layui-icon-group"></i>&nbsp;&nbsp;授权管理</a>
                                </dd>
                            </dl>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="layui-body">
                <div class="layui-tab" lay-filter="content-tab" lay-allowclose="true">
                    <ul class="layui-tab-title"></ul>
                    <div class="layui-tab-content"></div>
                </div>
            </div>

            <div class="layui-footer">
                <center>@ysdrzp</center>
            </div>
        </div>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script>
            layui.use(['element','jquery'], function() {
                var $ = layui.jquery, element = layui.element;

                /**
                 * 触发事件
                 * @type {{tabDelete: tabDelete, tabDeleteAll: tabDeleteAll, tabChange: tabChange, tabAdd: tabAdd}}
                 */
                var active = {
                    /**
                     * 在这里给active绑定几项事件，后面可通过active调用这些事件
                     * @param url
                     * @param id
                     * @param name
                     */
                    tabAdd: function(url, id, name) {
                        /**
                         * 新增一个Tab项，传入三个参数，分别对应tab页面的地址，一个规定的id【标签中data-id的属性值】，还有标题。
                         */
                        element.tabAdd('content-tab', {
                            title: name,
                            content: '<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%; height:50%;"></iframe>',
                            id: id
                        });

                        computeFrameWH();
                    },

                    /**
                     * Tab页切换
                     * @param id
                     */
                    tabChange: function(id) {
                        element.tabChange('content-tab', id);
                    }
                };

                /**
                 * 当点击有site-nav-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
                 */
                $('.site-nav-active').on('click', function() {

                    var dataid = $(this);

                    /**
                     * 判断右侧 .layui-tab-title 属性下的有 lay-id 属性的li的数目，也就是已经打开的tab项数目
                     */
                    openTabCount = $(".layui-tab-title li[lay-id]").length;

                    if (openTabCount <= 0) {
                        active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                    }

                    if (openTabCount > 0) {
                        var isOpened = false;
                        $.each($(".layui-tab-title li[lay-id]"), function () {
                            if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                                isOpened = true;
                            }
                        });

                        if (isOpened == false) {
                            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                        };
                    };

                    /**
                     * 切换到要打开的选项卡
                     */
                    active.tabChange(dataid.attr("data-id"));
                });

                function computeFrameWH() {
                    var h = $(window).height() - 200;
                    $("iframe").css("height", h +"px");
                };

                $(window).resize(function () {
                    computeFrameWH();
                });

            });
        </script>

    </body>

</html>