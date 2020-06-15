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
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
        <meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="${ctx}/layui/css/layui.css" media="all" />
        <script src="${ctx}/layui/layui.js"/>
        <script>
            var ctx = "${ctx}";
        </script>
    </head>

    <body class="childrenBody">
        <form class="layui-form">
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input searchVal" name="orgName" placeholder="请输入搜索的内容" />
                        </div>
                        <a class="layui-btn search_btn" data-type="reload">搜索</a>
                    </div>
                </form>
            </blockquote>

            <table id="orgList" lay-filter="orgList"></table>

        </form>

        <script>
            layui.use('table', function(){
                var table = layui.table;

                //加载页面数据
                table.render({
                    id : 'orgList',
                    elem : '#orgList',
                    url : ctx + '/orgInfo/list',
                    limit : 10,
                    limits : [ 10, 20, 30, 40 ],
                    cols : [[
                        {
                            type : 'checkbox'
                        },
                        {
                            field : 'orgId',
                            title : '邮箱'
                        },
                        {
                            field : 'OrgName',
                            title : '昵称'
                        }]],
                    page : true
                });

            });
        </script>

    </body>
</html>