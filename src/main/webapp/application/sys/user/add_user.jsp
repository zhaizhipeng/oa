<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加用户</title>
	<link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
	<script>var ysdrzp = "${ysdrzp}";</script>
</head>
<body class="childrenBody">

	<form class="layui-form">
		<div class="layui-form-item">
			<label class="layui-form-label">手机号</label>
			<div class="layui-input-block">
				<input type="text" name="mobilePhone" required  lay-verify="phone" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-inline">
				<input type="text" name="userName" required lay-verify="required" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">请输入真实姓名</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">备注内容</label>
			<div class="layui-input-block">
				<textarea name="miscDesc" placeholder="请输入内容" class="layui-textarea"></textarea>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">所属机构</label>
			<div class="layui-input-inline">
				<input type="text" name="orgName" value="地表最强" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="confirm">添加</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

	<script type="text/javascript" src= "${ysdrzp}/layui/layui.js"></script>

	<script>
		layui.use(['form','jquery','layer'], function(){
			var form = layui.form, $ = layui.jquery, layer = layui.layer;
			form.on('submit(confirm)', function(data){
				$.ajax({
					type:'post',
					url:'${ysdrzp}/user/addUser',
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