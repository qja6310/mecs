$().ready(function() {
	GetRequest();
});
function GetRequest() {
	var url = location.search; // 获取url中"?"符后的字串
	if (url.indexOf("?") != -1) { // 判断是否有参数
		var str = url.substr(1); // 从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
		strs = str.split("="); // 用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔
								// 再用等号进行分隔）
		getComboId(strs[1]);
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
 * 
 * 
 * 获取套餐ID,将billingOrder.html和comboDetails.html两个界面信息共享
 */
function getComboId(comboId) {
	$.ajax({
		type : "POST", // 提交方式
		url : "../getDetailsByComboId.action", // 路径
		data : {
			comboId : comboId
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
				showComboDetails(respData.itemsDtoList);
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
function showComboDetails(itemsDtoList, comboName) {
	var tbody = $("#detailsTbody");
	tbody.html("");
	if (itemsDtoList.length > 0) {
		/*
		 * 套双层循环 每一个细项就是一个tr,但是前面的序号,项目,价格对应的可能就是多个细项
		 * 所以需要合并行,也就是说只有第一个tr是需要有序号,项目,价格,而后面的tr是不需要这三项的
		 */
		for (var i = 0; i < itemsDtoList.length; i++) {
			var items = itemsDtoList[i]; // 单个项目
			for (var j = 0; j < items.itemList.length; j++) {
				var tr = $("<tr></tr>");

				if (j == 0) {
					var seq = $("<td style=\"width: 10%;\"></td>"); // 序号
					seq.text(i + 1);
					seq.attr("rowspan", items.itemList.length);
					var itemsName = $("<td style=\"width: 35%;\"></td>"); // 套餐名
					itemsName.text(items.itemsName);
					itemsName.attr("rowspan", items.itemList.length);
					var price = $("<td style=\"width: 10%;\"></td>"); // 价格
					price.text(items.itemsPrice);
					price.attr("rowspan", items.itemList.length);
					tr.append(seq);
					tr.append(itemsName);
					tr.append(price);
				}
				var itemName = $("<td style=\"width: 41.5%;\"></td>"); // 细项
				itemName.text(items.itemList[j].itemName);
				tr.append(itemName);
				tbody.append(tr);
			}
		}
	} else {
		var tr = $("<tr></tr>");
		var nullCombo = $("<td></td>");// 空数据
		nullCombo.text("查无此套餐");
		nullCombo.attr("colspan", 5);
		tr.append(nullCombo);
		tbody.append(tr);
	}
}