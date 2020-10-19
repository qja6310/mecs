$().ready(function() {
	GetRequest();
});
function GetRequest() {
   var url = location.search; //获取url中"?"符后的字串
   if (url.indexOf("?") != -1) {    //判断是否有参数
      var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
      strs = str.split("=");   //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
      toPrint(strs[1]);
   }
}

layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});

/*
* 获取套餐ID,将billingOrder.html和comboDetails.html两个界面信息共享
 */
function toPrint(mrNum) {
	$.ajax({
		type : "POST", // 提交方式
		url : "../getPrintInfo.action", // 路径
		data : {
			mrNum : mrNum
		},
		dataType : "json",
		success : function(respData) {
			if (respData.res == "isNull") {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('信息丢失', {
						icon : 2
					});
				});
			} else {
				showPrintInfo(respData,mrNum);
			}
		},
		error : function() {
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
 * 将详情信息显示出来
 */
function showPrintInfo(respData,mrNum) {
	/*
	 * 显示个人信息
	 */
	var user = respData.user;
	$("#userName").append(user.userName);
	$("#userSex").append(user.userSex);
	$("#userAge").append(user.userAge);
	$("#userBloodType").append(user.userBloodType);
	$("#time").append(respData.time);
	$("#mecCode").append(mrNum);
	
	/*
	 * 显示二维码图片
	 */
	var img = $("#img");
	img.attr("src","../getQRcode.action?mrNum="+mrNum);
	
	var tbody = $("#itemsTbody");
	tbody.html("");
	var itemsList = respData.itemsList;
	if (itemsList.length > 0) {
		/*
		 * 将体检项目信息加入到打印单的表格中
		*/ 
		for (var i = 0; i < itemsList.length; i++) {
			var items = itemsList[i]; // 单个项目
				var tr = $("<tr></tr>");
				var seq = $("<td style=\"width: 10%;text-align:center;\"></td>"); // 序号
				seq.text(i + 1);
				var itemsName = $("<td style=\"width: 30%;text-align:center;\"></td>"); // 套餐名
				itemsName.text(items.itemsName);
				var price = $("<td style=\"width: 10%;text-align:center;\"></td>"); // 价格
				price.text(items.itemsPrice);
				var dep = $("<td style=\"width: 15%;text-align:center;\"></td>"); // 科室
				dep.text(items.depId);
				var doctor = $("<td style=\"width: 15%;text-align:center;\"></td>"); // 医生
				var time = $("<td style=\"width: 20%;text-align:center;\"></td>"); // 日期
				
				tr.append(seq);
				tr.append(itemsName);
				tr.append(price);
				tr.append(dep);
				tr.append(doctor);
				tr.append(time);
				
				tbody.append(tr);
		}
	} else {
		var tr = $("<tr></tr>");
		var nullCombo = $("<td></td>");// 空数据
		nullCombo.text("没有项目");
		nullCombo.attr("colspan", 6);
		tr.append(nullCombo);
		tbody.append(tr);
	}
}

function exportWord(){
    $("#export_word").wordExport();
}