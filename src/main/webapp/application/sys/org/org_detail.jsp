<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>机构详情</title>
    <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
    <script>var ysdrzp = "${ysdrzp}";</script>
</head>
    <body>
        <style type="text/css">
            .orgDetailData .layui-form-item{
                margin: 20px 100px 10px 45px;
            }
            .orgDetailData .layui-form-label{
                width: 85px;
            }
            .layui-input-block {
                margin-left: 120px;
            }
        </style>
    
        <form class="layui-form orgDetailData">
            <div class="layui-form-item">
                <input type="hidden" name="id" value="${orgDetail.id}" />
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上级机构</label>
                <div class="layui-input-block">
                    <input type="text" name="parentOrgName" disabled = "disabled"  value="${parentOrgName}" class="layui-input" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构名称</label>
                <div class="layui-input-block">
                    <input type="text" name="orgName" lay-verify="required" value="${orgDetail.orgName}" class="layui-input" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">机构简称</label>
                <div class="layui-input-block">
                    <input type="text" name="subName" lay-verify="required" value="${orgDetail.subName}" class="layui-input" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"></label>
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="update-detail">
                        <i class="layui-icon layui-icon-edit"></i>修改
                    </button>
                </div>
            </div>
        </form>

        <script inline="javascript">
            layui.use(['form','jquery', 'layer'], function () {
                var form = layui.form, $ = layui.jquery, layer = layui.layer;
                form.on('submit(update-detail)', function (data) {
                    $.ajax({
                        type:'post',
                        url:'${ysdrzp}/org/update',
                        contentType:'application/json;charset=utf-8',
                        data:JSON.stringify(data.field),
                        dataType:'json',
                        success:function(data){
                            var msg = data.msg;
                            layer.msg(msg, {time:2000}, function(){
                                window.location.reload();
                            });
                        }
                    });
                    return false;
                });
            });
        </script>
    
    </body>
</html>