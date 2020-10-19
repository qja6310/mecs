var sureCardNum = ''; //卡号
layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});
function clearCardNum() {
	$("#cardNum").val("");
	sureCardNum = '';
}

/*
 * 读卡获取数据
 */
function readCard() {
	var cardNumVal = $("#cardNum").val().trim();
	if(cardNumVal == null || cardNumVal == "" || cardNumVal == undefined){
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('请输入卡号', {
				icon : 2
			});
		});
		return;
	}
	$.ajax({
		type: "POST", // 提交方式
		url: "../getMecRecordByCardNum.action", // 路径
		data: {
			cardNum: cardNumVal
		},
		dataType: "json",
		success: function(respData) {
			if(respData.res == "toLogin"){
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('请重新登录', {
						icon : 2
					});
				});
				window.location.href="../adminlogin.html";
			}else if (respData.res == "isNull") {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('信息丢失', {
						icon : 2
					});
				});
				/*
				 * 清除表中用户的信息和体检记录中的信息
				 */
				clearUserInfo();
			} else if (respData.res == "cardNumErr") {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('卡号错误', {
						icon : 2
					});
				});
				/*
				 * 清除表中用户的信息和体检记录中的信息
				 */
				clearUserInfo();
			} else if (respData.res == "cardNumSuc") {
				showUserInfo(respData, cardNumVal);
			}
		},
		error: function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败', {
					icon : 2
				});
			});
		},
	});
}

/*
 * 显示读卡后的数据
 */
function showUserInfo(respData, cardNumVal) {
	sureCardNum = cardNumVal;
	$("#userName").text("姓名:" + respData.user.userName);
	$("#userSex").text("性别:" + respData.user.userSex);
	$("#userAge").text("年龄:" + respData.user.userAge);
	if(respData.user.userBloodType == undefined || respData.user.userBloodType == null){
		respData.user.userBloodType = '';
	}
	$("#userBloodType").text("血型:" + respData.user.userBloodType);
	$("#userBirthday").text("生日:" + respData.user.userBirthday);
	$("#userPhone").text("电话:" + respData.user.userPhone);
	$("#userNativeplace").text("籍贯:" + respData.user.userNativePlace);
	
	/*
	 * 显示体检记录表格
	 */
	var tbody = $("#mecRecord");
	tbody.html("");
	var mecRecordList = respData.mecRecordList;
	if (mecRecordList.length > 0) {
		for (var i = 0; i < mecRecordList.length; i++) {
			var mr = mecRecordList[i];
			for (var j = 0; j < mr.irList.length; j++) {
				var items = mr.irList[j];
				var tr = $("<tr></tr>");
				if (j == 0) {
					var seq = $("<td style='width: 5%;'></td>"); //序号
					seq.attr('rowspan', mr.irList.length);
					var mecNum = $("<td style='width: 15%;'></td>"); //体检号码
					mecNum.attr('rowspan', mr.irList.length);
					var state = $("<td style='width: 10%;'></td>"); //状态
					state.attr('rowspan', mr.irList.length);
					var print = $("<td style='width: 18%;'></td>"); //打印
					print.attr('rowspan', mr.irList.length);

					seq.append(i + 1);
					mecNum.append(mr.mrNum);
					state.append(mr.mrState);
					if (mr.mrState != '已完结') {
						var printBtn = $("<button class=\"layui-btn layuiadmin-btn-admin layui-btn-sm\">打印导检单</button>");
						printBtn.attr("mrNum", mr.mrNum);
						printBtn.on('click', function() {
							var mrNum = $(this).attr('mrNum');
							jumpBilling(mrNum);
						});
						print.append(printBtn);
					}
				}
				var itemsName = $("<td style='width: 20%;'></td>"); //项目名
				var price = $("<td style='width: 10%;'></td>"); //项目价格
				var dep = $("<td style='width: 20%;'></td>"); //科室

				itemsName.append(items.itemsName);
				price.append(items.itemsPrice);
				dep.append(items.depId);

				if (j == 0) {
					tr.append(seq);
					tr.append(mecNum);
					tr.append(itemsName);
					tr.append(price);
					tr.append(dep);
					tr.append(state);
					tr.append(print);
				} else {
					tr.append(itemsName);
					tr.append(price);
					tr.append(dep);
				}
				tbody.append(tr);
			}
		}
	} else {
		var tr = $("<tr></tr>");
		var nullMr = $("<td style=\"text-align: center; font-weight: bold;\"></td>"); // 空数据
		nullMr.text("还未参加体检");
		nullMr.attr("colspan", 7);
		tr.append(nullMr);
		tbody.append(tr);
	}
}

/*
 * 清楚读卡错误后的数据
 */
function clearUserInfo() {
	$("#userName").text("姓名:");
	$("#userSex").text("性别:");
	$("#userAge").text("年龄:");
	$("#userBloodType").text("血型:");
	$("#userBirthday").text("生日:");
	$("#userPhone").text("电话:");
	$("#userNativeplace").text("籍贯:");
	var tbody = $("#mecRecord");
	tbody.html("");
	var tr = $("<tr></tr>");
	var nullMr = $("<td style=\"text-align: center; font-weight: bold;\"></td>"); // 空数据
	nullMr.text("还未参加体检");
	nullMr.attr("colspan", 7);
	tr.append(nullMr);
	tbody.append(tr);
}

/*
 * 清除体检表记录
 */
function clearMecRecord() {
	var tbody = $("#mecRecord");
	tbody.html("");
	var tr = $("<tr></tr>");
	var nullCombo = $("<td></td>"); // 空数据
	nullCombo.text("请输入正确卡号");
	nullCombo.attr("colspan", 7);
	tr.append(nullCombo);
	tbody.append(tr);
}

/*
 *进入开单页面 
 */
function jumpBilling(mrNum) {
	window.location.href="../billing.action?mrNum=" + mrNum;
}