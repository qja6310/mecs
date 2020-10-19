<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% String comboId=request.getParameter("comboId"); %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="../3rd/layui/css/layui.css">
		<script src="../3rd/layui/layui.js"></script>
	</head>

	<body class="layui-layout-body">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-body" style="left: 15px; top: 10px;right: 15px;">
				<!-- 内容主体区域 -->
				<div class="layui-inline" style="margin: 0px 0px 0px -40px;">
						<label class="layui-form-label">套餐名</label>
						<div class="layui-input-block">
							<input type="text" name="" id="comboName" placeholder="请输入套餐名"
								lay-verify="required" autocomplete="off" maxlength="50"
								class="layui-input" >
						</div>
					</div>
					<div class="layui-inline" style="margin: 0px 0px 0px -40px;">
					<label class="layui-form-label">价格</label>
					<div class="layui-input-block">
						<input type="text" name="" id="price" disabled="true"
								lay-verify="required" autocomplete="off" maxlength="50"
							class="layui-input">
					</div>
					
				</div>
				
				<div class="table-head">
					<table class="layui-table" lay-size="sm" style="margin-bottom: 0px;">
						<colgroup>
							<col width="200">
							<col />
						</colgroup>
						<thead>
							<tr class="layui-bg-cyan">
								<th style="width: 5%;">操作</th>
								<th style="width: 5%;">序号</th>
								<th style="width: 35%;">项目</th>
								<th style="width: 10%;">价格</th>
								<th style="width: 45%;">细项</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="table-body" style="height: 250px;overflow-y: scroll;">
					<table class="layui-table" lay-size="sm">
						<colgroup>
							<col width="200">
							<col />
						</colgroup>
						<tbody id="detailsTbody">
							
						</tbody>
					</table>
				</div>
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0px 0px -100px 120px;">
						<button type="button" id="sure"
							class="layui-btn layui-btn-normal layui-btn-sm"
							style="width: 100px">
							<i class="layui-icon layui-icon layuiadmin-button-btn"></i>确定
						</button>
					</div>

					<div class="layui-inline" style="margin: 0px 0px -100px 60px;">
						<button type="button" id="close"
							class="layui-btn layui-btn-danger layui-btn-sm"
							style="width: 100px">
							<i class="layui-icon layui-icon layuiadmin-button-btn"></i>取消
						</button>
					</div>

				</div>
			</div>
		</div>
	</body>

	<script src="../3rd/js/jquery.min.js"></script>
	<script src="../js/comboUpdate.js"></script>

</html>