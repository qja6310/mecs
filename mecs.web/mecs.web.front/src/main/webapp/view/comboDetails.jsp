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
				<div class="table-head">
					<table class="layui-table" lay-size="sm">
						<colgroup>
							<col width="200">
							<col />
						</colgroup>
						<thead>
							<tr class="layui-bg-cyan">
								<th style="width: 10%;">序号</th>
								<th style="width: 35%;">项目</th>
								<th style="width: 10%;">价格</th>
								<th style="width: 45%;">细项</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="table-body" style="height: 380px;overflow-y: scroll;">
					<table class="layui-table" lay-size="sm">
						<colgroup>
							<col width="200">
							<col />
						</colgroup>
						<tbody id="detailsTbody">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</body>

	<script src="../3rd/js/jquery.min.js"></script>
	<script src="../js/comboDetails.js"></script>

</html>