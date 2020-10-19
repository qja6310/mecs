<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%
	String mrNum = request.getParameter("mrNum");
%> --%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<link rel="stylesheet" href="../3rd/layui/css/layui.css">
<script src="../3rd/layui/layui.js"></script>
<script language="javascript">
	function printdiv(printpage) {
		var headstr = "<html><head><title></title></head><body>";
		var footstr = "</body>";
		var newstr = document.all.item(printpage).innerHTML;
		var oldstr = document.body.innerHTML;
		document.body.innerHTML = headstr + newstr + footstr;
		window.print();
		document.body.innerHTML = oldstr;
		return false;
	}
</script>
</head>

<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-body" style="left: 20px; top: 5px;">
			<!-- 内容主体区域 -->
			<div class="layui-form-item" style="text-align: right;">
				<div class="layui-inline">
					<a href="">
						<button
							class="layui-btn layui-btn-normal layuiadmin-btn-admin layui-btn-sm"
							onclick="printdiv('div_print')">
							<i class="layui-icon layui-icon-export layuiadmin-button-btn"></i>
							打印
						</button>
					</a>
					<button
						class="layui-btn layui-btn-normal layuiadmin-btn-admin layui-btn-sm"
						onclick="javascript :history.go(-1);">
						<i class="layui-icon layui-icon-export layuiadmin-button-btn"></i>
						返回
					</button>
				</div>
			</div>
			<fieldset>
			<legend style="font-weight: bold;">导检单打印</legend>
				<div id='div_print'>
					<p>
					<div class="layui-card" style="right: 20px;">
						<div class="layui-card-header"
							style="font-weight: bold; font-size: 30px; text-align: center;">导检单</div>
						<div class="layui-card-body">
							<div class="layui-inline"
								style="margin-left: 5%; margin-right: 5%; text-align: center;">
								<div>
									<table class="layui-table" lay-size="sm"
										style="border: 2px; width: 60%; margin-left: 20%;">
										<colgroup>
											<col width="200">
											<col />
										</colgroup>
										<tbody style="border: 0px;">
											<tr>
												<td style="text-align: left;"><label
													class="layui-form-label">姓名:</label><label
													class="layui-form-label" style="text-align: left"
													id="userName">${user.userName}</label></td>
												<td rowspan="6" style="text-align: center;"><img
													id="img" src="getQRcode.action?mrNum=${mrNum}" /></td>
											</tr>
											<tr>
												<td style="text-align: left;"><label
													class="layui-form-label">性别:</label><label
													class="layui-form-label" style="text-align: left"
													id="userSex">${user.userSex}</label></td>
											</tr>
											<tr>
												<td style="text-align: left;"><label
													class="layui-form-label">年龄:</label><label
													class="layui-form-label" style="text-align: left"
													id="userAge">${user.userAge}</label></td>
											</tr>
											<tr>
												<td style="text-align: left;"><label
													class="layui-form-label">血型:</label><label
													class="layui-form-label" style="text-align: left"
													id="userBloodType">${user.userBloodType}</label></td>
											</tr>
											<tr>
												<td style="text-align: left;"><label
													class="layui-form-label">日期:</label><label
													class="layui-form-label"
													style="text-align: left; width: 300px;" id="time">${time}</label></td>
											</tr>
											<tr>
												<td style="text-align: left;"><label
													class="layui-form-label">体检码:</label><label
													class="layui-form-label"
													style="text-align: left; width: 300px;" id="mecCode">${mrNum}</label>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<br /> <br /> <br /> <br />
							<div class="layui-inline"
								style="margin-left: 5%; margin-right: 5%; text-align: center;">
								<div class="table-head">
									<table class="layui-table" lay-size="sm"
										style="border: 2px; width: 550px;">
										<colgroup>
											<col width="200">
											<col />
										</colgroup>
										<thead>
											<tr class="layui-bg-cyan">
												<th style="width: 10%;">序号</th>
												<th style="width: 30%;">体检项目</th>
												<th style="width: 20%;">项目费用</th>
												<th style="width: 10%;">科室</th>
												<th style="width: 10%;">医生</th>
												<th style="width: 20%;">日期</th>
											</tr>
										</thead>
										<tbody id="itemsTbody">
											<c:forEach items="${itemsList}" var="items" varStatus="xh">
												<tr>
													<td style="width: 10%; text-align: center;">${xh.count}</td>
													<td style="width: 30%; text-align: center;">
														${items.itemsName}</td>
													<td style="width: 10%; text-align: center;">
														${items.itemsPrice}</td>
													<td style="width: 15%; text-align: center;">
														${items.depId}</td>
													<td style="width: 15%; text-align: center;"></td>
													<td style="width: 20%; text-align: center;"></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<br /> <br /> <br /> <br />
						<div class="layui-inline"
							style="margin-left: 5%; margin-right: 5%; text-align: center;">
							<div>
								<table class="layui-table" lay-size="sm"
									style="border: 2px; width: 60%;">
									<colgroup>
										<col width="200">
										<col />
									</colgroup>
									<tbody style="border: 0px;">
										<tr>
											<td style="text-align: left;"><label
												class="layui-form-label"
												style="width: 600px; text-align: left">说明:</label></td>
										</tr>
										<tr>
											<td style="text-align: left;"><label
												class="layui-form-label"
												style="width: 600px; text-align: left">①:体检人凭体检单去指定科室进行体检</label>
											</td>
										</tr>
										<tr>
											<td style="text-align: left;"><label
												class="layui-form-label"
												style="width: 600px; text-align: left">②:体检医生对体检人体检结束后需签字和填写日期</label>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					</p>
				</div>
			</fieldset>
		</div>
	</div>
</body>

<!-- <script src="../3rd/js/jquery.min.js"></script>
<script src="../3rd/js/FileSaver.js"></script>
<script src="../3rd/js/jquery.wordexport.js"></script>
<script src="../js/billing.js"></script> -->

</html>