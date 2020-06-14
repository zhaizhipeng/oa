<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>机构列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
    <meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${ctx }/layui/css/layui.css" media="all" />
    <script>
        var ctx = "${ctx}";
    </script>
</head>
<body class="childrenBody">
    <blockquote class="layui-elem-quote news_search">
        <form class="layui-form">
            <div>
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" id="orgName" value="" placeholder="请输入机构名称" class="layui-input search_input">
                    </div>
                </div>
            </div>
        </form>
    </blockquote>

    <div class="layui-form">
        <table id="userList" lay-filter="userList"></table>
    </div>

    <script type="text/javascript" src="${ctx }/layui/layui.js"></script>

    <script>
        layui.use([ 'form', 'layer', 'jquery', 'table', 'laydate' ],
                function() {
                    var form = layui.form, table = layui.table, layer = parent.layer === undefined ? layui.layer
                        : parent.layer, laydate = layui.laydate
                    $ = layui.jquery,
                        nowTime = new Date().valueOf(),
                        max = null,
                        active = {
                            search: function () {
                                var nickname = $('#nickname'), sex = $('#sex option:selected'),
                                    status = $('#status option:selected');
                                createTimeStart = $("#createTimeStart"), createTimeEnd = $("#createTimeEnd");
                                //执行重载
                                table.reload(
                                    'userList',
                                    {
                                        page: {
                                            curr: 1
                                            //重新从第 1 页开始
                                        },
                                        where: {
                                            //key: {
                                            nickname: nickname
                                                .val(),
                                            sex: sex
                                                .val(),
                                            status: status
                                                .val(),
                                            createTimeStart: createTimeStart
                                                .val(),
                                            createTimeEnd: createTimeEnd
                                                .val()
                                            //}
                                        }
                                    });
                            }
                        };

                    //添加文章
                    $(".userAdd_btn").click(function () {
                        var index = layui.layer.open({
                            title: "添加机构",
                            type: 2,
                            content: ctx + "/user/addUser",
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