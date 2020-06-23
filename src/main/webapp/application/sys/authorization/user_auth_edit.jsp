<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>编辑用户角色</title>
	<link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
	<script>var ysdrzp = "${ysdrzp}";</script>
</head>
<body class="childrenBody">

	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form layui-form-pane">

			<div class="layui-inline">
				<label class="layui-form-label">用户名称</label>
				<div class="layui-input-inline">
					<input type="hidden" class="layui-input" id = "userId" name="userId" value="${userId}"/>
					<input type="text" class="layui-input" id = "userName" name="userName" value="${userName}" disabled="disabled"/>
				</div>
			</div>

			<div class="layui-inline">
				<label class="layui-form-label">手机号</label>
				<div class="layui-input-inline">
					<input type="text" class="layui-input" id = "mobilePhone" name="mobilePhone" value="${mobilePhone}" disabled="disabled"/>
				</div>
			</div>

			<div style="height: 15px"></div>

			<div class="layui-form-item layui-form-text">
				<span style="font-size: medium; color: #0C0C0C">选择角色</span>
				<div style="height: 15px"></div>
				<div class="layui-input-block" style="border: 1px solid #c0c4cc; width: auto; height: 200px; background:#f2f2f2;">
					<div class="layui-input-block" id="roleCheck"></div>
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

				/**
				 * 获取checkbox数据
				 * @type {any[]}
				 */
				let roleIdsArray = new Array();
				$("input:checkbox[name='roleName']:checked").each(function(){
					roleIdsArray.push($(this).val());
				});

				data.field.roleIds = roleIdsArray;

				$.ajax({
					type:'post',
					url:'${ysdrzp}/user/editRole',
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

			$(function () {
				$.post("${ysdrzp}/role/allRole", function (data) {

					var allRole = data.data;
					for (var k in allRole) {
						let checked = '';

						/**
						 * 用户目前拥有的角色
						 */
						let  currentRoleIds = "${currentRoleIds}"
						if (currentRoleIds != ''){

							for (var r in currentRoleIds){
								if (currentRoleIds[r] == allRole[k].id){
									checked = 'checked';
								}
							}
						}

						$("#roleCheck").append("<input type='checkbox' name = 'roleName' title='" + allRole[k].roleCnName + "' value='" + allRole[k].id + "' " + checked + "></input>");
					}
					form.render();
				});
			});
		});
	</script>

</body>
</html>