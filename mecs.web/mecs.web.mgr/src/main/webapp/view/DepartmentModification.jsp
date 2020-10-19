<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<% String depId=request.getParameter("depId"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../3rd/layui2/css/layui.css">
<script src="../3rd/layui2/layui.js"></script>
        <title>科室编辑</title>
    </head>
    <body class="layui-layout-body">
	<form class="layui-form">
			<div  style="width:100%"  >
				<!-- 内容主体区域 -->
				<div class="layui-form-item" style="margin-left: 10%; margin-top: 50px;" >
					<div class="layui-inline" >
						<label class="layui-form-label" >科室</label>
						<div class="layui-input-block">
							<input type="text" name="" id="depName" placeholder="请输入科室名称"
								lay-verify="required" autocomplete="off" maxlength="15"
								class="layui-input" style="width: 200px; height: 30px;">
						</div>
					</div>
				</div>

				<div class="layui-form-item" style="margin-left: 10%;">
				<div class="layui-inline" >
					<label class="layui-form-label">科室描述</label>
					<div class="layui-input-block" style=" width: 300px">
							<textarea placeholder="请输入内容" class="layui-textarea" maxlength="110" id="depDescribe"></textarea>
					</div>
					</div>
				</div>
					<div class="layui-form-item" style="margin-left: 40%; margin-top: 30px;">
					<div class="layui-inline" >
						<div class="layui-btn-container">
							<button type="button" class="layui-btn layui-btn-sm" id="sure">确定</button>
							<button type="button" class="layui-btn layui-btn-sm" id="close">取消</button>
						</div>
					</div>

				</div>
	
			</div>
	</form>
	<script src="../3rd/js/jquery.min.js"></script>
	<script src="../js/DepartmentUpdate.js"></script>
	<script type="text/javascript"></script>
</body>
</html>