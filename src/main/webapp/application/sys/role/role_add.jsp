<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加角色</title>
	<link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
	<script>var ysdrzp = "${ysdrzp}";</script>
</head>
<body class="childrenBody">

	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label class="layui-form-label">角色中文名</label>
				<div class="layui-input-inline">
					<input type="text" name="roleCnName" required lay-verify="required" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">请输入角色中文名</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">角色英文名</label>
				<div class="layui-input-inline">
					<input type="text" name="roleEnName" required lay-verify="required" class="layui-input">
				</div>
				<div class="layui-form-mid layui-word-aux">请输入角色英文名</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">备注内容</label>
				<div class="layui-input-block">
					<textarea name="miscDesc" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<button class="layui-btn layui-btn-sm layui-btn-normal layui-btn-fluid" lay-submit lay-filter="confirm">确认</button>
			</div>
		</form>
	</blockquote>

	<script type="text/javascript" src= "${ysdrzp}/layui/layui.js"></script>

	<script>
		layui.use(['form','jquery','layer'], function(){
			var form = layui.form, $ = layui.jquery, layer = layui.layer;
			form.on('submit(confirm)', function(data){
				$.ajax({
					type:'post',
					url:'${ysdrzp}/role/addRole',
					contentType:'application/json;charset=utf-8',
					data:JSON.stringify(data.field),
					dataType:'json',
					success:function(data){
						var msg = data.msg;
						layer.msg(msg, {time:2000}, function(){
							layer.close(layer.index);
							window.parent.location.reload();
						});
					}
				});
				return false;
			});
		});
	</script>

</body>
</html>