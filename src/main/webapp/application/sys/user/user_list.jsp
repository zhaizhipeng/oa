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

        <blockquote class="layui-elem-quote quoteBox">
            <form class="layui-form layui-form-pane">
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
                        <input type="text" class="layui-input" id = "orgName" name="orgName"/>
                    </div>
                </div>

                <button type="button" id = "reload" class="layui-btn layui-inline">查询</button>
                <button type="button" id = "new-user" class="layui-btn layui-btn-normal layui-inline"><i class="layui-icon layui-icon-add-1"></i>添加用户</button>
            </form>
        </blockquote>

        <table id="userTable" class="layui-table-main" lay-filter="user-list"></table>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script type="text/html" id="singleOperBar">
            {{# if(d.disabled == 0 ){ }}
            <a class="layui-btn layui-btn-xs" lay-event="enable">启用</a>
            {{# } else { }}
            <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="disable">禁用</a>
            {{# } }}
            <a class="layui-btn layui-btn-warm layui-btn-xs " lay-event="pwd-reset">密码重置</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>

        <script type="text/html" id="disabledTpl">
            {{# if(d.disabled == 0 ){ }}
            已禁用
            {{# } else { }}
            已启用
            {{# } }}
        </script>

        <script type="text/html" id="genderTpl">
            {{# if(d.gender == 0 ){ }}
            保密
            {{# } else if(d.gender == 1){ }}
            男
            {{# } else if(d.gender == 2){ }}
            女
            {{# } }}
        </script>

        <script>
            layui.use(['table','jquery', 'layer'], function(){
                var table = layui.table, $ = layui.jquery, layer = layui.layer;

                var tableIns = table.render({
                    elem: '#userTable',
                    height: '600',
                    url: ysdrzp + '/user/list',
                    method: 'post',
                    contentType: 'application/json',
                    page: true,
                    cols: [[
                        {field: 'mobilePhone', title: '手机号', width:180},
                        {field: 'userName', title: '用户名', width:180},
                        {field: 'orgName', title: '机构所属', width:180},
                        {field: 'gender', title: '性别', width:100, templet:'#genderTpl'},
                        {field: 'pwdValidDate', title: '密码有效日期', width: 180, templet:'<div>{{ layui.util.toDateString(d.pwdValidDate, "yyyy-MM-dd") }}</div>'},
                        {field: 'lastLoginDate', title: '上次登录时间', width: 180, templet:'<div>{{ layui.util.toDateString(d.lastLoginDate, "yyyy-MM-dd HH:mm:ss") }}</div>'},
                        {field: 'miscDesc', title: '备注', width:250},
                        {field: 'disabled', title: '状态', width:180, templet:'#disabledTpl', fixed: 'right'},
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
                 * 重新加载用户列表
                 */
                $('#reload').click(function () {
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
                });

                /**
                 * 新增用户
                 */
                $("#new-user").click(function(){
                   var index = layer.open({
                        type: 2,
                        title: '添加用户',
                        shadeClose: true,
                        area: ['625px', '465px'],
                        content: ysdrzp + '/application/sys/user/user_add.jsp',
                        cancel: function(index, layero){
                            if(confirm('确认不要添加用户么')){
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
                table.on('tool(user-list)', function(obj){
                    /**获得当前行数据*/
                    var data = obj.data;
                    /**获得 lay-event 对应的值*/
                    var layEvent = obj.event;

                    /**启用*/
                    if(layEvent === 'enable'){
                        layer.confirm('确认启用么',{icon : 1, skin:'layui-layer-molv'}, function(index){
                            $.ajax({
                                type:'get',
                                url:'${ysdrzp}/user/enable?' + 'id=' + obj.data.id,
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
                                url:'${ysdrzp}/user/disable?' + 'id=' + obj.data.id,
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
                    if(layEvent === 'del'){
                        layer.confirm('确认删除么', {icon : 2, skin: 'layui-layer-molv'}, function(index){
                            /**
                             * 删除对应行（tr）的DOM结构，并更新缓存
                             */
                            obj.del();

                            $.ajax({
                                type:'get',
                                url:'${ysdrzp}/user/delete?' + 'id=' + obj.data.id,
                                success:function(data){
                                    var msg = data.msg;
                                    layer.msg(msg, {time : 2000}, function(){
                                        window.location.reload();
                                    });
                                }
                            });
                        });
                    }

                    /**密码重置*/
                    if(layEvent === 'pwd-reset'){

                        layer.confirm('确认重置密码么', {icon : 0, skin: 'layui-layer-molv'}, function(index){
                            $.ajax({
                                type:'get',
                                url:'${ysdrzp}/user/pwdReset?' + 'id=' + obj.data.id,
                                success:function(data){
                                    var msg = data.msg;
                                    layer.msg(msg, {time:2000}, function(){
                                        window.location.reload();
                                    });
                                }
                            });
                        });
                    }
                });
            });
        </script>

    </body>
</html>