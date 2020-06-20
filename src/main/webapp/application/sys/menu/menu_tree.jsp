<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>菜单管理</title>
        <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
        <script>var ysdrzp = "${ysdrzp}";</script>
    </head>

    <body class="childrenBody">
        <style type="text/css">
            #menu_main, #menu_detail{
                width: 48.5%;
                display: inline-block;
                vertical-align: top;
                padding: 20px;
                background: white;
                box-sizing: border-box;
            }
            #menu_tree{
                margin-top: 20px;
            }
        </style>
    
        <div id="menu_main" style="margin-right: 2%;">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>所有菜单</legend>
            </fieldset>

            <button class="layui-btn layui-btn-normal layui-btn-radius" lay-event="addMenu" style="width: 120px; color: #23262E"><i class="layui-icon layui-icon-add-1"></i>添加菜单</button>

            <div id="menu_tree"></div>
        </div>
    
        <div id="menu_detail">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>菜单详情</legend>
            </fieldset>
            <div id="menu_home">
                <div class="layui-tree-emptyText">无数据</div>
            </div>
        </div>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script>
            layui.use(['tree', 'jquery', 'util', 'layer'], function() {
                var tree = layui.tree,
                    $ = layui.jquery,
                    util = layui.util,
                    layer = layui.layer;

                var checkId = null;
                tree.render({
                    elem: '#menu_tree',
                    data: getData(),
                    id: 'treeId',
                    onlyIconControl: true,
                    edit: true,
                    click: function (obj) {
                        checkId = obj.data.id;
                        /** 获取菜单详情 */
                        $("#menu_home").load("${ysdrzp}/menu/detail?id="+checkId);
                    },
                    operate: function(obj){

                        /** 得到当前节点的数据 */
                        var data = obj.data;

                        /** 得到操作类型 */
                        var type = obj.type;

                        /**
                         * 修改节点
                         */
                        if(type === 'update'){
                            $.ajax({
                                type:'post',
                                url:'${ysdrzp}/menu/update',
                                contentType:'application/json;charset=utf-8',
                                data:JSON.stringify({id: data.id, menuName: data.title}),
                                dataType:'json',
                                success:function(data){
                                    var msg = data.msg;
                                    layer.msg(msg, {time:2000}, function(){
                                        tree.reload('treeId', {data: getData()});
                                    });
                                }
                            });
                        }

                        /** 删除节点 */
                        if(type === 'del'){
                            $.ajax({
                                type:'post',
                                url:'${ysdrzp}/menu/delete',
                                contentType:'application/json;charset=utf-8',
                                data:JSON.stringify({id: data.id}),
                                dataType:'json',
                                success:function(data){
                                    var msg = data.msg;
                                    layer.msg(msg, {time:2000}, function(){
                                        tree.reload('treeId', {data: getData()});
                                    });
                                }
                            });
                        }
                    }
                });

                util.event('lay-event', {
                    addMenu: function(){
                        if (checkId == null){
                            layer.msg("请选中一个父菜单");
                        }

                        if (checkId != null){
                            $.get('${ysdrzp}/menu/getAdd?id=' + checkId, function(data) {
                                layer.open({
                                    type: 1,
                                    title: '添加菜单',
                                    area: ['520px'],
                                    content: data
                                });
                                return false;
                            });
                        }
                    }
                });

                function getData(){
                    var data = [];
                    $.ajax({
                        url: "${ysdrzp}/menu/menuTree",
                        type: "post",
                        async: false,
                        success: function(result){
                            data = result;
                        }
                    });
                    return data;
                }
            });
        </script>

    </body>
</html>