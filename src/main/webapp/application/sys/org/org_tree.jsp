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
        <style type="text/css">
            #org_main, #org_detail{
                width: 48.5%;
                display: inline-block;
                vertical-align: top;
                padding: 20px;
                background: white;
                box-sizing: border-box;
            }
            #org_tree{
                margin-top: 20px;
            }
        </style>
    
        <div id="org_main" style="margin-right: 2%;">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>所有机构</legend>
            </fieldset>
            <button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" lay-demo="addDept"><i class="layui-icon">&#xe654;</i>添加机构</button>
            <button class="layui-btn layui-btn-sm layui-btn-radius layui-btn-normal" lay-demo="gain">获取选中节点</button>
    
            <div id="org_tree"></div>
        </div>
    
        <div id="org_detail">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>机构详情</legend>
            </fieldset>
            <div id="org_home">
                <div class="layui-tree-emptyText">无数据</div>
            </div>
        </div>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script>
            layui.use(['tree', 'jquery'], function() {
                var tree = layui.tree, $ = layui.jquery;
                tree.render({
                    elem: '#org_tree',
                    data: getData(),
                    id: 'treeId',
                    onlyIconControl: true,
                    edit: true,
                    click: function (obj) {
                        var id = obj.data.id;
                        /** 获取机构详情 */
                        $("#org_home").load("${ysdrzp}/org/detail?id="+id);
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
                                url:'${ysdrzp}/org/update',
                                contentType:'application/json;charset=utf-8',
                                data:JSON.stringify({id: data.id, orgName: data.title}),
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
                                url:'${ysdrzp}/org/delete',
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

                function getData(){
                    var data = [];
                    $.ajax({
                        url: "${ysdrzp}/org/orgTree",
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