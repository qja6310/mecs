layui.use('form', function(){
  var form = layui.form;
  
  //各种基于事件的操作，下面会有进一步介绍
});

$().ready(function() {
	getAllComboInfo();
});

function getAllComboInfo() {
	$.ajax({
		type : "POST", // 提交方式
		url : "../getAllCombo.action", // 路径
		dataType : "json",
		success : function(respData) {
			showComboDetails(respData.itemsDtoList);

		},
		error : function() {
			alert("请求失败");
		},
	});

}

function showComboDetails(itemsDtoList) {
	var tbody = $("#detailsTbody");
	tbody.html("");
	if (itemsDtoList.length > 0) {
		/*
		 * 套双层循环 每一个细项就是一个tr,但是前面的序号,项目,价格对应的可能就是多个细项
		 * 所以需要合并行,也就是说只有第一个tr是需要有序号,项目,价格,而后面的tr是不需要这三项的
		 */
		for (var i = 0; i < itemsDtoList.length; i++) {
			var items = itemsDtoList[i]; // 单个项目
			for (var j = 0; j < items.itemList.length; j++) { // 细项集合的循环
				var tr = $("<tr></tr>");
				if (j == 0) {
					var choose = $("<td style=\"width: 3.5%;\"></td>");
					choose.attr('align', 'center');
					tr.append(choose);
					choose.attr("rowspan", items.itemList.length);
					var seq = $("<td style=\"width: 5.5%;\"></td>"); // 序号
					seq.text(i + 1);
					seq.attr("rowspan", items.itemList.length);
					var itemsName = $("<td style=\"width: 36%;\"></td>"); // 项目名
					itemsName.text(items.itemsName);
					itemsName.attr("rowspan", items.itemList.length);
					var price = $("<td style=\"width: 10%;\"></td>"); // 价格
					price.text(items.itemsPrice);
					price.attr("rowspan", items.itemList.length);
					tr.append(seq);
					tr.append(itemsName);
					tr.append(price);
					var checkBox = $("<input type='checkbox' />");

					checkBox.attr('id', items.itemsId);

					checkBox.attr('itemsPrice', items.itemsPrice);
					// checkBox.attr('value', items.itemId);
//					checkBox.attr('value', items.itemId);
					checkBox.attr('value', items.itemsId);
					checkBox.on('click', function() {
						calculatePrice(this);
					});
					choose.append(checkBox);

				}
				var itemName = $("<td ></td>"); // 细项
				itemName.text(items.itemList[j].itemName);
				tr.append(itemName);
				tbody.append(tr);
			}
		}
	} else {
		var tr = $("<tr></tr>");
		var nullCombo = $("<td></td>");// 空数据
		nullCombo.text("查无此套餐");
		nullCombo.attr("colspan", 6);
		tr.append(nullCombo);
		tbody.append(tr);
		tr.attr('align', 'center');
	}
}

/*
 * 计算总价格
 */
var totalPrice = 0;// 显示总价格
$("#price").val(totalPrice);
function calculatePrice(checkbox) {
	var price = $("#price").val();
	if (checkbox.checked) {
		totalPrice = parseInt($(checkbox).attr("itemsPrice")) + parseInt(totalPrice);
		$("#price").val(totalPrice);
	} else {
		totalPrice = parseInt(totalPrice) - parseInt($(checkbox).attr("itemsPrice"));
		$("#price").val(totalPrice);
	}
}

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});

// 增加一级菜单确定
$("#sure").on("click", function() {

	var layer = layui.layer;

	var comboName = $("#comboName").val();
	var price = $("#price").val();

	if (comboName == "") {
		layer.msg('套餐名不能为空', {
			icon : 2
		});
		return;
	}

	var cksVal = new Array();
	var cks = $("input[type='checkbox']");
	for (var i = 0; i < cks.length; i++) {
		if (cks[i].checked) {
			cksVal.push(cks[i].value);
		}
	}

	// $.ajaxSettings.traditional = true;// 能传集合
	$.ajax({
		url : "../addCombo.action",
		data : {
			comboName : comboName,
			price : price,
			cksVal : cksVal

		},
		type : "post",
		dataType : "json",
		success : function(respData) {
			layui.use('layer', function() {
				layer.msg(respData.res);
				setTimeout(function() {
					// 关闭弹出的html页面
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
				}, 2000);// 2秒
			});

		},
		error : function() {
			layer.msg("查询失败");
		}
	});

});

