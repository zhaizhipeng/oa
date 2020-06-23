<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/application/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>编辑角色</title>
	<link rel="stylesheet" href="${ysdrzp}/layui/css/layui.css" media="all" />
	<script>var ysdrzp = "${ysdrzp}";</script>
</head>
	<body class="childrenBody">
		<style type="text/css">
			#menu_main{
				width: 90%;
				display: inline-block;
				vertical-align: top;
				padding: 10px;
				background: white;
				box-sizing: border-box;
			}
			#menu_tree{
				margin-top: 20px;
			}
		</style>

		<div id="menu_main" style="margin-right: 2%;">

			<blockquote class="layui-elem-quote quoteBox">
				<form class="layui-form layui-form-pane">
					<div class="layui-inline">
						<label class="layui-form-label">角色名称</label>
						<div class="layui-input-inline">
							<input type="hidden" name="roleId" required lay-verify="required" class="layui-input" value="${roleId}">
							<input type="text" name="roleName" class="layui-input" disabled value="${roleName}">
						</div>
					</div>
					<div class="layui-inline" style="left: 480px">
						<button class="layui-btn layui-btn-sm" lay-submit lay-filter="confirm" style="width: 60px; color: #23262E"><i class="layui-icon"></i>提交</button>
					</div>
				</form>
			</blockquote>

			<div id="menu_tree"></div>

		</div>

		<script src="${ysdrzp}/layui/layui.js"></script>

		<script>
			layui.use(['tree', 'jquery', 'layer', 'form'], function() {
				var tree = layui.tree, $ = layui.jquery, layer = layui.layer, form = layui.form;

				tree.render({
					elem: '#menu_tree',
					data: getData(),
					id: 'treeId',
					onlyIconControl: true,
					edit: false,
					showCheckbox: true,
					showLine: false
				});

				function getData(){
					var data = [];
					$.ajax({
						url: "${ysdrzp}/menu/roleMenuTree?roleId=" + ${roleId},
						type: "get",
						async: false,
						success: function(result){
							data = result;
							console.log(data);
						}
					});
					return data;
				}

				form.on('submit(confirm)', function(data){

					/**
					 * 获得选中的节点
					 */
					var checkData = tree.getChecked('treeId');

					var permList = getCheckedId(checkData);

					function getCheckedId(jsonObj) {
						var id = "";
						$.each(jsonObj, function (index, item) {
							if (id != "") {
								id = id + "," + item.id;
							}
							else {
								id = item.id;
							}
							var i = getCheckedId(item.children);
							if (i != "") {
								id = id + "," + i;
							}
						});
						return id;
					}

					data.field.resourcesIds = permList;

					$.ajax({
						type:'post',
						url:'${ysdrzp}/role/editRoleResources',
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