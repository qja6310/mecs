var totalPrice = 0;// 显示总价格
var cardBalance = 0;// 卡余额
var sureCardNum = '';// 卡号
layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});

var comboNameVal = ""; // 套餐名,做搜索用
var comboStateVal = "";// 套餐状态

/**
 * 初始获取数据方法
 */
$().ready(function() {
	getAllComboInfo();
});

/*
 * 进入页面就获取所有的套餐信息,并且可根据套餐名查询
 */
function getAllComboInfo() {
	$.ajax({
		type : "POST", // 提交方式
		url : "../getAllComboInfo.action", // 路径
		data : {
			comboName : comboNameVal,
			comboState : comboStateVal
		},
		dataType : "json",
		success : function(respData) {
			showAllCombo(respData.comboList);
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败！', {
					time : 2000, // 2s后自动关闭
					area : '200px',
					icon : 2,
				});
			});
		},
	});
}

function getAllComboInfoByComboName() {
	comboNameVal = $("#comboName").val();
	comboStateVal = $("#comboState").val();
	$("#totalPrice").val("0");
	totalPrice = 0;

	getAllComboInfo();
}

function clearComboName() {
	$("#comboName").val("");
}

function clearCardNum() {
	$("#cardNum").val("");
	cardNum = '';
}

function showAllCombo(comboList) {
	var comboTbody = $("#comboTbody");
	comboTbody.html("");
	if (comboList.length > 0) {
		for (var j = 0; j < comboList.length; j++) {
			var combo = comboList[j]; // 单个套餐
			var tr = $("<tr></tr>");
			var choose = $("<td style=\"width: 5%;\"></td>");
			choose.attr('align', 'center');
			var seq = $("<td style=\"width: 5%;\"></td>"); // 序号
			var name = $("<td style=\"width: 30%;\"></td>"); // 套餐名
			var price = $("<td style=\"width: 10%;\"></td>"); // 价格
			var memo = $("<td style=\"width: 30%;\"></td>"); // 备注
			var operation = $("<td style=\"width: 18%;\"></td>"); // 操作
			/**
			 * 每列中添加相应的数据
			 */
			if (combo.comboState == '上架') {
				var checkBox = $("<input type='checkbox' />");
				checkBox.attr('comboId', combo.comboId);
				checkBox.attr('comboPrice', combo.comboPrice);
				checkBox.attr('value', combo.comboId);
				checkBox.on('click', function() {
					calculatePrice(this);
				});
				choose.append(checkBox);
			}
			seq.append(j + 1);
			name.append(combo.comboName);
			price.append(combo.comboPrice);
			memo.append(combo.comboMemo);

			var details = $("<button class=\"layui-btn layuiadmin-btn-admin layui-btn-sm\"></button>");
			var i = $("<i class=\"layui-icon layui-icon-list layuiadmin-button-btn\"></i>");
			details.append(i);
			details.append("详情");
			details.attr("comboId", combo.comboId);
			details.attr("comboName", combo.comboName);
			details.on("click", function() {
				var comboId = $(this).attr("comboId");
				var comboName = $(this).attr("comboName");
				jumpDetails(comboId, comboName);
			});
			operation.append(details);
			/*
			 * 信息显示
			 */
			tr.append(choose);
			tr.append(seq);
			tr.append(name);
			tr.append(price);
			tr.append(memo);
			tr.append(operation);
			comboTbody.append(tr);
		}
	} else {
		var tr = $("<tr></tr>");
		var nullCombo = $("<td></td>"); // 空数据
		nullCombo.text("查无此套餐");
		nullCombo.attr("colspan", 5);
		tr.append(nullCombo);
		comboTbody.append(tr);
	}
	readCard();
}

/*
 * 获取套餐详情
 */
function jumpDetails(comboId, comboName) {
	layer.open({
		type : 2,
		title : comboName,
		skin : 'layui-layer-rim', // 加上边框
		area : [ '800px', '530px' ], // 宽高
		offset : '80px',// 底部坐标
		content : 'comboDetails.jsp?comboId=' + comboId
	});
}

/*
 * 读卡获取数据
 */
function readCard() {
	$.ajax({
		type : "POST", // 提交方式
		url : "../getUserInfoByCardNum.action", // 路径
		data : {},
		dataType : "json",
		success : function(respData) {
			if (respData.res == "isNull") {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('信息丢失', {
						icon : 2
					});
				});
				clearUserInfo();
			} else if (respData.res == "cardNumErr") {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('卡号错误', {
						icon : 2
					});
				});
				clearUserInfo();
			} else if (respData.res == "cardNumSuc") {
				showUserInfo(respData);
			}
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败！', {
					time : 2000, // 2s后自动关闭
					area : '200px',
					icon : 2,
				});
			});
		},
	});
}

/*
 * 显示读卡后的数据
 */
function showUserInfo(respData) {
	$("#cardNum").val(respData.cardNum);
	$("#cardBalance").val(respData.balance);
	cardBalance = parseInt(respData.balance);
	$("#userName").val(respData.user.userName);
	$("#userSex").val(respData.user.userSex);
	$("#userAge").val(respData.user.userAge);
	$("#userBloodType").val(respData.user.userBloodType);
	$("#userBirthday").val(respData.user.userBirthday);
	$("#userPhone").val(respData.user.userPhone);
	$("#userAddress").val(respData.user.userAddress);
	$("#userNativeplace").val(respData.user.userNativePlace);

	/*
	 * 显示体检记录表格
	 */
	var tbody = $("#mecRecord");
	tbody.html("");
	var mecRecordList = respData.mecRecordList;
	if (mecRecordList.length > 0) {
		for (var i = 0; i < mecRecordList.length; i++) {
			var mr = mecRecordList[i];
			var tr = $("<tr></tr>");
			var seq = $("<td style='width: 5%;'></td>");
			var cardNum = $("<td style='width: 10%;'></td>");
			var combo = $("<td style='width: 20%;'></td>");
			var price = $("<td style='width: 5%;'></td>");
			var doctor = $("<td style='width: 10%;'></td>");
			var req = $("<td style='width: 30%;'></td>");
			var state = $("<td style='width: 10%;'></td>");
			var time = $("<td style='width: 8.5%;'></td>");

			seq.append(i + 1);
			cardNum.append(mr.cardId);
			combo.append(mr.comboName);
			price.append(mr.mrPrice);
			doctor.append(mr.docAdmin);
			req.append(mr.mrReq);
			state.append(mr.mrState);
			time.append(mr.bilTime);

			tr.append(seq);
			tr.append(cardNum);
			tr.append(combo);
			tr.append(price);
			tr.append(doctor);
			tr.append(req);
			tr.append(state);
			tr.append(time);

			tbody.append(tr);
		}
	} else {
		var tr = $("<tr></tr>");
		var nullCombo = $("<td></td>"); // 空数据
		nullCombo.text("还未参加体检");
		nullCombo.attr("colspan", 8);
		tr.append(nullCombo);
		tbody.append(tr);
	}
}

/*
 * 清楚读卡错误后的数据
 */
function clearUserInfo(respData) {
	$("#cardBalance").val("");
	$("#userName").val("");
	$("#userSex").val("");
	$("#userAge").val("");
	$("#userBloodType").val("");
	$("#userBirthday").val("");
	$("#userPhone").val("");
	$("#userAddress").val("");
	$("#userNativeplace").val("");
	var tbody = $("#mecRecord");
	tbody.html("");
	var tr = $("<tr></tr>");
	var nullCombo = $("<td></td>"); // 空数据
	nullCombo.text("请先读卡");
	nullCombo.attr("colspan", 8);
	tr.append(nullCombo);
	tbody.append(tr);
}

/*
 * 计算总价格
 */
function calculatePrice(checkbox) {
	if (checkbox.checked) {
		totalPrice = parseInt($(checkbox).attr("comboPrice")) + totalPrice;
		if (totalPrice > cardBalance) {
			$("#totalPrice").css('color', 'red');
			$("#totalPrice").val(totalPrice);
		} else {
			$("#totalPrice").css('color', 'green');
			$("#totalPrice").val(totalPrice);
		}
	} else {
		totalPrice = totalPrice - parseInt($(checkbox).attr("comboPrice"));
		if (totalPrice > cardBalance) {
			$("#totalPrice").css('color', 'red');
			$("#totalPrice").val(totalPrice);
		} else {
			$("#totalPrice").css('color', 'green');
			$("#totalPrice").val(totalPrice);
		}
	}
}

/*
 * 获取所选择的套餐,加入数据库
 */
function sureBilling() {
	var comboIdList = new Array();// 创建一个数组,将套餐id存入该数组
	/**
	 * 获取指定div块中的全部复选框
	 */
	var checkBoxList = $(".comboCheckBox").find("input[type='checkbox']");
	for (var i = 0; i < checkBoxList.length; i++) {
		if (checkBoxList[i].checked) {
			comboIdList.push(checkBoxList[i].value);
		}
	}
	if (comboIdList.length == 0) {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('请选择', {
				icon : 2
			});
		});
		return;
	}
	/*
	 * 将该集合封装JSON对象
	 */
	var json_str = JSON.stringify(comboIdList);
	/*
	 * 获取总金额
	 */
	var totalPrice = $("#totalPrice").val();
	/*
	 * 提示是否确定开单
	 */
	// layui 确定取消按钮
	layer.confirm('是否确定开单并结算?', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		/*
		 * 数据传入后端
		 */
		$.ajax({
			type : "POST", // 提交方式
			url : "../chooseItems.action", // 路径
			data : {
				comboJSON : json_str,
				mrPrice : totalPrice
			},
			dataType : "json",
			success : function(respData) {
				if (respData.res == "toLogin") {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('请重新登录', {
							icon : 2
						});
					});
					window.location.href = "../adminlogin.html";
				} else if (respData.res == "isNull") {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('信息丢失', {
							icon : 2
						});
					});
				} else if (respData.res == "notEnough"){
					layer.confirm('卡上余额不足,是否现在去充值?', {
						btn : [ '去充值', '暂不充值' ]
					// 按钮
					}, function(index) {
						window.location.href = 'recharge.jsp';
					});
					layer.close(index);// 解决点了确定之后弹框不消失的问题
				    return false;// 防止不点 确定直接提交的问题
				}else if (respData.res == "err") {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('开单失败', {
							icon : 2
						});
					});
				} else if (respData.res == "suc") {
					jumpBilling(respData.mrNum);
				}
			},
			error : function() {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('请求失败！', {
						time : 2000, // 2s后自动关闭
						area : '200px',
						icon : 2,
					});
				});
			},
		});
		layer.close(index);// 解决点了确定之后弹框不消失的问题
	});
	return false;// 防止不点 确定直接提交的问题
}

/*
 * 进入开单页面
 */
function jumpBilling(mrNum) {
	window.location.href = "../billing.action?mrNum=" + mrNum;
}

/*
 * 返回首页
 */
function backMainPage() {
	window.location.href = "../view/healthCheckup.html";
}