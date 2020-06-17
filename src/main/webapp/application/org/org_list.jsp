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

        <form class="layui-form">
            <blockquote class="layui-elem-quote quoteBox">
                <form class="layui-form">
                    <div class="layui-inline">
                        <div class="layui-input-inline">
                            <input type="text" class="layui-input searchVal" name="orgName" placeholder="请输入机构名称" />
                        </div>
                        <a class="layui-btn search_btn" data-type="search">搜索</a>
                    </div>
                </form>
            </blockquote>

            <table id="orgTable" lay-filter=""></table>

        </form>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script>
            layui.use(['table','jquery'], function(){
                var table = layui.table, $ = layui.jquery;

                var tableIns = table.render({
                    elem: '#orgTable'
                    ,height: 500
                    ,url: ysdrzp + '/orgInfo/list'
                    ,method: 'post'
                    ,contentType: 'application/json'
                    ,page: true
                    ,cols: [[
                        {field: 'id', title: 'ID', width:200, sort: true, fixed: 'left'}
                        ,{field: 'orgId', title: '机构ID', width:200}
                        ,{field: 'orgName', title: '机构名称', width:200}
                        ,{field: 'fatherId', title: '父机构ID', width: 200}
                        ,{field: 'createUserName',title: '创建人', width: 200}
                        ,{field: 'updateUserName',title: '更新人'}
                    ]]
                    ,limit: 10
                    ,limits:[5,10,20,50,100]
                    ,even: true
                    ,text: '暂无数据'
                });

                active = {
                    search: function(){
                        var orgName = $("#orgName");
                        tableIns.reload('orgTable',{
                            page: {
                                curr: 1
                            },
                            where: {
                                orgName: orgName.val()
                            }
                        })
                    }
                };

            });
        </script>

    </body>
</html>