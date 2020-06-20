<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加资源</title>
	<link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
	<script>var ysdrzp = "${ysdrzp}";</script>
</head>
	<body class="childrenBody">

		<blockquote class="layui-elem-quote quoteBox">
			<form class="layui-form layui-form-pane" lay-filter="menu-add-form">
				<div class="layui-form-item">
					<input type="hidden" name="fatherId" value="${fatherId}" />
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">父级资源名称</label>
					<div class="layui-input-block">
						<input type="text" name="fatherMenuName" disabled = "disabled"  value="${fatherMenuName}" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">资源名称</label>
					<div class="layui-input-block">
						<input type="text" name="resourcesName" lay-verify="required" class="layui-input" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">url类型</label>
					<div class="layui-input-block">
						<select name="urlType" lay-verify="required">
							<option value=""></option>
							<option value="0">系统</option>
							<option value="1">静态资源</option>
							<option value="2">按钮</option>
						</select>
					</div>
				</div>
				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">资源描述</label>
					<div class="layui-input-block">
						<textarea name="miscDesc" class="layui-textarea"></textarea>
					</div>
				</div>

				<div class="layui-form-item">
					<button class="layui-btn layui-btn-sm layui-btn-normal layui-btn-fluid" lay-submit lay-filter="confirm">确认</button>
				</div>
			</form>
		</blockquote>

		<script>
			layui.use(['form','jquery','layer'], function(){
				var form = layui.form, $ = layui.jquery, layer = layui.layer;

				/**
				* 表单渲染
				*/
				form.render(null, 'menu-add-form');

				form.on('submit(confirm)', function(data){
					$.ajax({
						type:'post',
						url:'${ysdrzp}/menu/add',
						contentType:'application/json;charset=utf-8',
						data:JSON.stringify(data.field),
						dataType:'json',
						success:function(data){
							var msg = data.msg;
							layer.msg(msg, {time:2000}, function(){
								layer.close(layer.index);
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