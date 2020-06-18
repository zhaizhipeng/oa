<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>用户管理</title>
        <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
        <script>var ysdrzp = "${ysdrzp}";</script>
    </head>

    <body class="childrenBody">

        <form class="layui-form layui-form-pane">
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <label class="layui-form-label">用户名称</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id = "userName" name="userName"/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">手机号</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id = "mobilePhone" name="mobilePhone"/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">机构所属</label>
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input" id = "orgId" name="orgId"/>
                        </div>
                    </div>

                    <button type="button" id = "reload-btn" class="layui-btn layui-inline" data-type="reload">查询</button>
                </form>
            </blockquote>

            <table id="userTable" lay-filter=""></table>

        </form>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script type="text/html" id="singleOperBar">
            {{# if(d.disabled == 0 ){ }}
            <a class="layui-btn layui-btn-xs" lay-event="check">启用</a>
            {{# } else { }}
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="check">禁用</a>
            {{# } }}
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>

        <script type="text/html" id="disabledTpl">
            {{# if(d.disabled == 0 ){ }}
            已禁用
            {{# } else { }}
            已启用
            {{# } }}
        </script>

        <script>
            layui.use(['table','jquery'], function(){
                var table = layui.table, $ = layui.jquery;

                var tableIns = table.render({
                    elem: '#userTable',
                    height: 400,
                    url: ysdrzp + '/user/list',
                    method: 'post',
                    contentType: 'application/json',
                    page: true,
                    cols: [[
                        {field: 'id', title: 'ID', width:100, sort: true, fixed: 'left'},
                        {field: 'mobilePhone', title: '手机号', width:180},
                        {field: 'userName', title: '用户名称', width:180},
                        {field: 'orgName', title: '机构所属', width:180},
                        {field: 'lastLoginDate', title: '上次登录时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.lastLoginDate, "yyyy-MM-dd HH:mm:ss") }}</div>'},
                        {field: 'miscDesc', title: '备注', width:180},
                        {field: 'disabled', title: '状态', width:120, templet:'#disabledTpl'},
                        {fixed: 'right', align:'center', toolbar: '#singleOperBar'}
                    ]],
                    limit: 10,
                    limits:[5,10,20,50],
                    even: true,
                    text: '暂无数据'
                    ,toolbar: true
                });

                $('.layui-btn').click(function () {
                    var userName = $('#userName').val();
                    var mobilePhone = $('#mobilePhone').val();
                    var orgId = $('#orgId').val();
                    tableIns.reload({
                        url: ysdrzp + '/user/list',
                        methods:"post",
                        limit: 10,
                        where: {
                            userName : userName,
                            mobilePhone: mobilePhone,
                            orgId: orgId
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