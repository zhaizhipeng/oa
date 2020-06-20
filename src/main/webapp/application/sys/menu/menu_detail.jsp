<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>资源详情</title>
    <link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
    <script>var ysdrzp = "${ysdrzp}";</script>
</head>
    <body>
        <style type="text/css">
            .menuDetailData .layui-form-item{
                margin: 20px 100px 10px 45px;
            }
            .menuDetailData .layui-form-label{
                width: 85px;
            }
            .layui-input-block {
                margin-left: 120px;
            }
        </style>
    
        <form class="layui-form menuDetailData">
            <div class="layui-form-item">
                <input type="hidden" name="id" value="${menuDetail.id}" />
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">上级菜单</label>
                <div class="layui-input-block">
                    <input type="text" name="fatherResourcesName" disabled = "disabled"  value="${fatherResourcesName}" class="layui-input" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">资源Key</label>
                <div class="layui-input-block">
                    <input type="text" name="resourcesKey" disabled = "disabled" value="${menuDetail.resourcesKey}" class="layui-input" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">资源名称</label>
                <div class="layui-input-block">
                    <input type="text" name="resourcesName" lay-verify="required" value="${menuDetail.resourcesName}" class="layui-input" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">url类型</label>
                <div class="layui-input-block">
                    <input type="text" name="urlType" value="${menuDetail.urlType}" class="layui-input" />
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">资源描述</label>
                <div class="layui-input-block">
                    <textarea name="miscDesc" class="layui-textarea">${menuDetail.miscDesc}</textarea>
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
                        url:'${ysdrzp}/menu/update',
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