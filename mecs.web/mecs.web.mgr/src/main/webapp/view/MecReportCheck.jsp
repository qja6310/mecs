<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String mrNum = request.getParameter("mrNum");
%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="../3rd/layui/css/layui.css">
<script src="../3rd/layui/layui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>体检报告打印</title>
</head>

<body>
		<div class="layui-form-item" style="text-align: right;">
			<div class="layui-inline">

				<button
					class="layui-btn layui-btn-normal layuiadmin-btn-admin layui-btn-sm"
					onclick="exportWord()">
					<i class="layui-icon layui-icon-export layuiadmin-button-btn"></i>
					生成文件
				</button>
			</div>
		</div>
		<div id="export_word">
			<p>
				<div style="text-align: center;">
				<table style="width: 100%;">
				<tr>
					<td><label
							style="margin-left: 10%; font-weight: bold; font-size: 20px;">健
							康 体 检 中 心</label></td>
					<td style="text-align: right;"><label
							style="margin-left: 60%;">体检日期:</label><label id="time"></label>
					</td>
				</tr>
			</table>
		</div>
		<hr />
		<br />
		<br />
		<br />
		<br />
		<br />
		<div>
			<div style="text-align: center;">
				<table style="border: 2px; width: 60%;">
					<colgroup>
						<col width="200">
						<col />
					</colgroup>
					<tbody style="border: 0px;">
						<tr>
							<td><label>姓名:</label><label style="text-align: left"
									id="userName"></label></td>
							<td rowspan="4" style="text-align: center;"><img id="img"
									src="" /></td>
						</tr>
						<tr>
							<td><label>性别:</label><label style="text-align: left"
									id="userSex"></label></td>
						</tr>
						<tr>
							<td><label>年龄:</label><label style="text-align: left"
									id="userAge"></label></td>
						</tr>
						<tr>
							<td><label>手机:</label><label style="text-align: left"
									id="userPhone"></label></td>
						</tr>
						<tr>
							<td><label>日期:</label><label
									style="text-align: left; width: 300px;" id="printTime"></label></td>
							<td style="text-align: center;"><label>体检码:</label><label
									style="text-align: left; width: 300px;" id="mecCode"></label></td>
						</tr>
					</tbody>
				</table>
			</div>
			<br />
			<br />
			<br />
			<br />
			<br /> <br />
			<br />
			<br />
			<br />
			<br /> <br />
			<br />
			<br />
			<br />
			<br />
			<div style="text-align: center;">
				<font style="font-weight: bold; font-size: 50px;">健康体检中心</font> <br />
				<font
						style="font-weight: bold; font-size: 50px; letter-spacing: 80px;">体检报</font>
				<font style="font-weight: bold; font-size: 50px;">告</font>
			</div>
			<br />
			<br />
			<br />
			<br />
			<br /> <br />
			<br />
			<br />
			<br />
			<br /> <br />
			<br />
			<div style="margin-left: 10%; margin-right: 10%; margin-top: 40%;">
				<label>尊敬的:<u style="font-weight: bold;" id="userName2"></u>(先生/女士):
				</label> <br /> <label>&nbsp;&nbsp;感谢您来到康泰健康体检中心进行健康体检!</label> <br /> <label>&nbsp;&nbsp;
					为了增加你对健康体检的认识,我们在此特别向你说明,健康体检检查的目的在于及时的了解自身的健康状况,提高健康保健意识.如果此次
					检查在正常范围内,只表示您选择的体检项目所反映的身体健康状况,由于体检的手段及项目所限,并不能完全排除身体潜在疾病,若有疾病
					症状出现,请您及时到医院就医. </label> <br /> <label>&nbsp;&nbsp;
					"健康是人身最宝贵的".不良的生活习惯和行为会损坏您的健康,我们真诚希望您保持科学健康的生活方式,定期接收健康检查,在您最需要的时候请
					随时与我们联系,欢迎您再次光临康泰健康体检中心. </label> <br /> <label>&nbsp;&nbsp; 祝您,健康快乐! </label>
			
				</div>
			<br />
			<br />
			<br />
			<br />
			<br /> <br />
			<br />
			<br />
			<br />
			<br /> <br />
			<br />
			<br />
			<br />
			<br /> <br />
			<br />
			<br />
			<br />
			<br /> <br />
			<br />
			<br />
			<br />
			<br /> <br />
			<br />
			<br />
			<br />
			<br /> <br />
			<div
					style="text-align: left; margin-left: 10%; margin-top: 20%; margin-right: 10%;">
				<label><font style="font-weight: bold; font-size: 30px;">体检项目列表</font></label>
				<hr />
				<table style="width: 100%;">
					<thead>
						<tr style="background-color: #2F4056; font-weight: bold;">
							<td style="width: 10%;">序号</td>
							<td style="width: 30%">科室</td>
							<td style="width: 60%">项目</td>
						</tr>
					</thead>
					<tbody id="itemsTbody">

					</tbody>
				</table>
			</div>
			<br />
			<br />
			<br />
			<br />
			<br />
			<div
					style="text-align: left; margin-left: 10%; margin-top: 20%; margin-right: 10%;">
				<label><font style="font-weight: bold; font-size: 30px;">体检项目结果</font></label>
				<hr />
				<div style="width: 100%; text-align: left;" id="itemsRecordRes">

				</div>
			</div>
			<br />
			<br />
			<br />
			<br />
			<br />
			<div
					style="text-align: left; margin-left: 10%; margin-top: 20%; margin-right: 10%;">
				<label><font style="font-weight: bold; font-size: 30px;">体检总结和建议</font></label>
				<hr />
				<div style="width: 100%; text-align: left;" id="mecRecordReq">

				</div>
			</div>
			<div
					style="text-align: left; margin-left: 10%; margin-top: 20%; margin-right: 10%;">
				<label><font style="font-weight: bold; font-size: 30px;">生活保健指导</font></label>
				<hr />
				<div style="width: 100%; text-align: left;" id="mecRecordGuide">

				</div>
			</div>
			</p>
		</div>
</body>
<script src="../3rd/js/jquery.min.js"></script>
<script src="../3rd/js/FileSaver.js"></script>
<script src="../3rd/js/jquery.wordexport.js"></script>
<script src="../js/MecReportCheck.js"></script>
</html>