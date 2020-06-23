<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>角色管理</title>
        <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
        <script>var ysdrzp = "${ysdrzp}";</script>
    </head>

    <body class="childrenBody">

        <blockquote class="layui-elem-quote quoteBox">
            <form class="layui-form layui-form-pane">
                <div class="layui-inline">
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id = "roleName" name="roleName"/>
                    </div>
                </div>
                <button type="button" id = "reload" class="layui-btn layui-inline">查询</button>
                <button type="button" id = "new-role" class="layui-btn layui-btn-normal layui-inline"><i class="layui-icon layui-icon-add-1"></i>添加角色</button>
            </form>
        </blockquote>

        <table id="roleTable" class="layui-table-main" lay-filter="role-list"></table>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script type="text/html" id="singleOperBar">
            {{# if(d.disabled == 0 ){ }}
            <a class="layui-btn layui-btn-xs" lay-event="enable">启用</a>
            {{# } else { }}
            <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="disable">禁用</a>
            {{# } }}
            <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit">编辑</a>
        </script>

        <script type="text/html" id="disabledTpl">
            {{# if(d.disabled == 0 ){ }}
            已禁用
            {{# } else { }}
            已启用
            {{# } }}
        </script>

        <script>
            layui.use(['table','jquery', 'layer'], function(){
                var table = layui.table, $ = layui.jquery, layer = layui.layer;

                var tableIns = table.render({
                    elem: '#roleTable',
                    height: '600',
                    url: ysdrzp + '/role/list',
                    method: 'post',
                    contentType: 'application/json',
                    page: true,
                    cols: [[
                        {field: 'id', title: 'ID', width:100},
                        {field: 'roleCnName', title: '角色中文名', width:180},
                        {field: 'roleEnName', title: '角色英文名', width:180},
                        {field: 'miscDesc', title: '备注', width:300},
                        {field: 'disabled', title: '角色是否启用', width:150, templet:'#disabledTpl', fixed: 'right'},
                        {title: '操作', align: 'center', toolbar: '#singleOperBar', fixed: 'right'}
                    ]],
                    limit: 10,
                    limits:[5,10,20,50],
                    even: true,
                    text: '暂无数据',
                    toolbar: true,
                    done : function(res, curr, count){
                        if (res.count == 0) {
                            $(".layui-table-main").html('<div class="layui-none">暂无数据哦</div>');
                        }
                    }
                });

                /**
                 * 重新加载角色列表
                 */
                $('#reload').click(function () {
                    var roleName = $('#roleName').val();
                    var mobilePhone = $('#mobilePhone').val();
                    var orgId = $('#orgId').val();
                    tableIns.reload({
                        url: ysdrzp + '/role/list',
                        methods:"post",
                        limit: 10,
                        where: {
                            roleName : roleName,
                            mobilePhone: mobilePhone,
                            orgId: orgId
                        },
                        page: {
                            curr: 1
                        }
                    });
                });

                /**
                 * 新增角色
                 */
                $("#new-role").click(function(){
                   var index = layer.open({
                        type: 2,
                        title: '添加角色',
                        shadeClose: true,
                        area: ['625px', '440px'],
                        content: ysdrzp + '/application/sys/role/role_add.jsp',
                        cancel: function(index, layero){
                            if(confirm('确认不要添加角色么')){
                                layer.close(index)
                            }
                            return false;
                        }
                   });
                });

                /**
                 * 监听选中行
                 * 注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                 */
                table.on('tool(role-list)', function(obj){
                    /**获得当前行数据*/
                    var data = obj.data;
                    /**获得 lay-event 对应的值*/
                    var layEvent = obj.event;

                    /**启用*/
                    if(layEvent === 'enable'){
                        layer.confirm('确认启用么',{icon : 1, skin:'layui-layer-molv'}, function(index){
                            $.ajax({
                                type:'get',
                                url:'${ysdrzp}/role/enable?' + 'id=' + obj.data.id,
                                success:function(data){
                                    var msg = data.msg;
                                    layer.msg(msg, {time : 2000}, function(){
                                        window.location.reload();
                                    });
                                }
                            });
                        });
                    }

                    /**禁用*/
                    if(layEvent === 'disable'){
                        layer.confirm('确认禁用么', {icon : 0, skin: 'layui-layer-molv'}, function(index){
                            $.ajax({
                                type:'get',
                                url:'${ysdrzp}/role/disable?' + 'id=' + obj.data.id,
                                success:function(data){
                                    var msg = data.msg;
                                    layer.msg(msg, {time : 2000}, function(){
                                        window.location.reload();
                                    });
                                }
                            });
                        });
                    }

                    /**删除*/
                    /*if(layEvent === 'del'){
                        layer.confirm('确认删除么', {icon : 2, skin: 'layui-layer-molv'}, function(index){
                            /!**
                             * 删除对应行（tr）的DOM结构，并更新缓存
                             *!/
                            obj.del();

                            $.ajax({
                                type:'get',
                                url:'${ysdrzp}/role/delete?' + 'id=' + obj.data.id,
                                success:function(data){
                                    var msg = data.msg;
                                    layer.msg(msg, {time : 2000}, function(){
                                        window.location.reload();
                                    });
                                }
                            });
                        });
                    }*/

                    if (layEvent === 'edit'){
                        var index = layer.open({
                            type: 2,
                            title: '编辑角色',
                            shadeClose: true,
                            area: ['1025px', '625px'],
                            content: ysdrzp + '/role/openRoleResourcesEdit?roleId=' + obj.data.id + "&roleName=" + obj.data.roleCnName,
                            cancel: function(index, layero){
                                if(confirm('确认不要编辑角色么')){
                                    layer.close(index)
                                }
                                return false;
                            }
                        });
                    }

                });
            });
        </script>

    </body>
</html>