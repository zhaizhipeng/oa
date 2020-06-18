<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>机构管理</title>
        <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
        <script>var ysdrzp = "${ysdrzp}";</script>
    </head>

    <body class="childrenBody">

        <form class="layui-form layui-form-pane">
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <label class="layui-form-label">机构名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id = "orgName" name="orgName" placeholder="请输入机构名称" />
                        </div>
                        <button type="button" id = "reload-btn" class="layui-btn layui-inline"  data-type="reload">查询</button>
                    </div>
                </form>
            </blockquote>

            <table id="orgTable" lay-filter=""></table>

        </form>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

            <!-- 这里同样支持 laytpl 语法，如： -->
            {{#  if(d.auth > 2){ }}
            <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
            {{#  } }}
        </script>

        <script>
            layui.use(['table','jquery'], function(){
                var table = layui.table, $ = layui.jquery;

                var tableIns = table.render({
                    elem: '#orgTable',
                    height: 400,
                    url: ysdrzp + '/orgInfo/list',
                    method: 'post',
                    contentType: 'application/json',
                    page: true,
                    cols: [[
                        {field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'},
                        {field: 'orgId', title: '机构ID', width:120},
                        {field: 'orgName', title: '机构名称', width:200},
                        {field: 'updateTime', title: '操作时间', width: 200, templet:'#updatedTimeTpl'},
                        {field: 'updateUserName',title: '操作人', width: 150},
                        {fixed: 'right', align:'center', toolbar: '#barDemo'}
                    ]],
                    limit: 10,
                    limits:[5,10,20,50],
                    even: true,
                    text: '暂无数据',
                    toolbar: true
                });

                $('.layui-btn').click(function () {
                    var orgName = $('#orgName').val()
                    tableIns.reload({
                        url: ysdrzp + '/orgInfo/list',
                        methods:"post",
                        limit: 10,
                        where: {
                            orgName : orgName
                        },
                        page: {
                            curr: 1
                        }
                    });
                })
            });
        </script>

    </body>
</html>