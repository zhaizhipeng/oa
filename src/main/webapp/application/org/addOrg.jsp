<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ include file="/WEB-INF/page/include/taglib.jsp"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>添加管理员</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${ctx }/layui/css/layui.css" media="all" />
	<script>
        var ctx = "${ctx}";  
    </script> 
	<style type="text/css">
	.layui-form-item .layui-inline {
		width: 33.333%;
		float: left;
		margin-right: 0;
	}

	@media ( max-width :1240px) {
		.layui-form-item .layui-inline {
			width: 100%;
			float: none;
		}
	}
</style>
</head>
<body class="childrenBody">

	<<form class="layui-form" action="">
		<div class="layui-form-item">
			<label class="layui-form-label">输入框</label>
			<div class="layui-input-block">
				<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码框</label>
			<div class="layui-input-inline">
				<input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux">辅助文字</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">选择框</label>
			<div class="layui-input-block">
				<select name="city" lay-verify="required">
					<option value=""></option>
					<option value="0">北京</option>
					<option value="1">上海</option>
					<option value="2">广州</option>
					<option value="3">深圳</option>
					<option value="4">杭州</option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">复选框</label>
			<div class="layui-input-block">
				<input type="checkbox" name="like[write]" title="写作">
				<input type="checkbox" name="like[read]" title="阅读" checked>
				<input type="checkbox" name="like[dai]" title="发呆">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">开关</label>
			<div class="layui-input-block">
				<input type="checkbox" name="switch" lay-skin="switch">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">单选框</label>
			<div class="layui-input-block">
				<input type="radio" name="sex" value="男" title="男">
				<input type="radio" name="sex" value="女" title="女" checked>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">文本域</label>
			<div class="layui-input-block">
				<textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>
	</form>

	<script type="text/javascript" src="${ctx }/layui/layui.js"></script>
	<script type="text/javascript" src="${ctx }/application/org/addOrg.js"></script>
</body>
</html>