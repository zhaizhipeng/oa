<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8">
        <title>用户授权</title>
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
                    <label class="layui-form-label">角色名称</label>
                    <div class="layui-input-inline">
                        <select lay-verify="required" name="roleId" id = "roleId" class="SelectRoleName">
                            <option value="">请选择角色</option>
                        </select>
                    </div>
                </div>

                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" id="orgTreeSelect" name="orgName" lay-filter="tree" lay-verify="required" value="" class="layui-input">
                        <input type="hidden" id="orgId" name="orgId" value="" class="layui-input">
                    </div>
                </div>

                <button type="button" id = "reload" class="layui-btn layui-inline">查询</button>
            </form>
        </blockquote>

        <table id="userAuthTable" class="layui-table-main" lay-filter="user-auth-list"></table>

        <script src="${ysdrzp}/layui/layui.js"></script>

        <script type="text/html" id="singleOperBar">
            <a class="layui-btn layui-btn-normal" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
        </script>

        <script>

            layui.config({
                base: '${ysdrzp}/layui/lay/modules/'
            }).extend({treeSelect: 'treeSelect/treeSelect'});

            layui.use(['table','jquery', 'layer', 'form', 'treeSelect'], function(){
                var table = layui.table, $ = layui.jquery, layer = layui.layer, form = layui.form, treeSelect = layui.treeSelect;

                $(function () {
                    $.post("${ysdrzp}/role/selectList", function (data) {
                        var roleSelect = data.data;
                        for (var k in roleSelect) {
                            $(".SelectRoleName").append("<option value='" + roleSelect[k].id + "'>" + roleSelect[k].roleCnName + "</option>");
                        }
                        form.render();
                    });
                });

                treeSelect.render({
                    elem: '#orgTreeSelect',
                    data: '${ysdrzp}/org/orgTreeSelect',
                    type: 'post',
                    placeholder: '选择机构所属',
                    search: true,

                    click: function(d){
                        $("#orgTreeSelect").val(d.current.name);
                        $("#orgId").val(d.current.id);
                    },

                    /**
                     * 加载完成后的回调函数
                     * @param d
                     */
                    success: function (d) {
                    }
                });

                var tableIns = table.render({
                    elem: '#userAuthTable',
                    height: '600',
                    url: ysdrzp + '/authorization/userAuthList',
                    method: 'post',
                    contentType: 'application/json',
                    page: true,
                    cols: [[
                        {field: 'userId', title: 'ID', width:180},
                        {field: 'mobilePhone', title: '手机号', width:250},
                        {field: 'userName', title: '用户名', width:250},
                        {field: 'orgName', title: '机构所属', width:250},
                        {field: 'roleCnNames', title: '关联角色', width:450, fixed: 'right'},
                        {title: '操作', align: 'center', toolbar: '#singleOperBar', fixed: 'right'}
                    ]],
                    limit: 10,
                    limits:[5,10,20,50],
                    even: true,
                    text: '暂无数据',
                    toolbar: true,
                    done : function(res, curr, count){
                        if (res.count == 0) {
                            $(".layui-table-main").html('<div class="layui-none">暂无数据</div>');
                        }
                    }
                });
                
                $('#reload').click(function () {
                    var userName = $('#userName').val();
                    var mobilePhone = $('#mobilePhone').val();
                    var orgId = $('#orgId').val();
                    var roleId = $('#roleId').val()
                    tableIns.reload({
                        url: ysdrzp + '/authorization/userAuthList',
                        methods:"post",
                        limit: 10,
                        where: {
                            userName : userName,
                            mobilePhone: mobilePhone,
                            orgId: orgId,
                            roleId: roleId
                        },
                        page: {
                            curr: 1
                        }
                    });
                });

                table.on('tool(user-auth-list)', function(obj){
                    var data = obj.data;
                    var layEvent = obj.event;

                    if(layEvent === 'edit'){
                        var index = layer.open({
                            type: 2,
                            title: '编辑角色',
                            shadeClose: true,
                            area: ['750px', '335px'],
                            content: ysdrzp + '/authorization/getUserAuthEdit?mobilePhone=' + data.mobilePhone + "&userName=" + data.userName + "&userId=" + data.userId,
                            cancel: function(index, layero){
                                if(confirm('确认不要编辑用户角色么')){
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