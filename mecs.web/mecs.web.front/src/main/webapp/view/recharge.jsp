<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String comboId = request.getParameter("comboId");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<link rel="stylesheet" href="../3rd/layui/css/layui.css">
<script src="../3rd/layui/layui.js"></script>
<link rel="stylesheet" href="../css/new_file.css" />
<link rel="stylesheet" href="../3rd/res/static/css/index.css">
</head>

<body >
	<div >
		<div class="nav index">
			<div class="layui-container">
				<!-- 公司logo -->
				<div class="nav-logo" style="margin-left: -4%;">
					<a href="healthCheckup.html"> <span
						style="background: transparent; font-weight: bold; color: #669999; font-size: 40px; font-family: '华文行楷';">自贡康桥健康体检中心</span>
					</a>
				</div>
				<div class="nav-list">
					<button>
						<span></span><span></span><span></span>
					</button>
					<ul class="layui-nav" lay-filter=""
						style="font-weight: bold; font-size: 40px;">
						<li class="layui-nav-item"><a href="healthCheckup.html">返回首页</a></li>
					</ul>

				</div>
			</div>
		</div>
		<div class="layui-body"
			style="width: 100%; height: 100%; left: 0%; top: 0%; right: 15px; text-align: center; ">
			<!-- 内容主体区域 -->
			<div>
				<img src="../img/hu01.png" style ="margin-top: 5%;margin-left: -60%;" />
			</div>
			<fieldset class="layui-elem-field site-demo-button"
				style="margin-top: -40%; width: 70%; margin-left: 18%">
				<legend style="font-weight: bold;" id="head"></legend>
				<br /> <br /> <br /> <br />
				<div style="text-align: center; margin-top: 10px;">
					<div class="layui-inline">
						<label class="layui-form-label">卡内余额</label>
						<div class="layui-input-block">
							<input type="text" name="balance" id="balance" autocomplete="off"
								class="layui-input" readonly="readonly">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">订单编号</label>
						<div class="layui-input-block">
							<input type="text" name="WIDout_trade_no" id="WIDout_trade_no"
								autocomplete="off" class="layui-input" readonly="readonly">
						</div>
					</div>
					<br /> <br /> <br /> <br />
					<div class="layui-inline">
						<label class="layui-form-label">订单名称</label>
						<div class="layui-input-block">
							<input type="text" name="WIDsubject" id="WIDsubject"
								autocomplete="off" class="layui-input" value="自助充值"
								readonly="readonly">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">充值金额</label>
						<div class="layui-input-block">
							<input type="text" name="WIDtotal_amount" placeholder="请输入金额"
								id="WIDtotal_amount" autocomplete="off" class="layui-input"
								maxlength="6"
								onkeyup="value=value.replace(/[^\d]/g, '').replace(/^0{1,}/g,'')">
						</div>
					</div>
					<br /> <br /> <br /> <br />
					<div class="layui-inline" style="margin-bottom: 10px;">
						<button class="layui-btn layuiadmin-btn-admin layui-btn-sm"
							type="submit" onclick="sureRecharge()" style="width: 200px;">
							<i class="layui-icon layui-icon-rmb layuiadmin-button-btn"></i>
							确认充值
						</button>
						<!-- <button
								class="layui-btn layui-btn-warm layuiadmin-btn-admin layui-btn-sm"
								onclick="backMainPage()">
								<i class="layui-icon layui-icon-return layuiadmin-button-btn"></i>
								返回首页
							</button> -->
					</div>
				</div>
			</fieldset>
			<div>
					<img src="../img/yiyuan.jpg" style="margin-top: -3%;margin-left: 64%;width: 560PX;height: 300px;"  />
					</div>
		</div>
	</div>
</body>

<script src="../3rd/js/jquery.min.js"></script>
<script src="../js/recharge.js"></script>

</html>