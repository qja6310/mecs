layui.use('form', function() {
	var form = layui.form;

	// 各种基于事件的操作，下面会有进一步介绍
});

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});
$().ready(function() {
	showPanel();
})

function showPanel() {
	var layer = layui.layer;
	$
			.ajax({
				url : "../showItemsUpdate.action",
				type : "post",
				dataType : "json",
				success : function(respData) {
					$("#itemsName").val(respData.items.itemsName);
					$("#price").val(respData.items.itemsPrice);

					var list = respData.dep;
					var myDep = respData.items.depId;
					var dep = $("#addSelect");
					dep.html("<option value=''>请选择</option>");
					if (list.length > 0) {
						for (var i = 0; i < list.length; i++) {
							var op = $("<option></option>");
							op.append(list[i].depName);
							op.val(list[i].depId);
							dep.append(op);
						}
					}
					dep.val(myDep);// 回填科室
					var itemPanel = $("#item");
					var item = respData.itemAll;
					var myItem = respData.item;
					if (item.length > 0) {
						for (var i = 0; i < item.length; i++) {
//							var checkbox1 = $("<input type='checkbox' lay-skin='primary'/>");
//							checkbox1.attr("title", item[i].itemName);
//							checkbox1.attr("value", item[i].itemId);
//							itemPanel.append(checkbox1);
							var tr = $("<tr></tr>");
							var td1 = $("<td style=\"width: 5%;\"></td>");
							var td2 = $("<td style=\"width: 11.5%;\"></td>");
							var td3 = $("<td style=\"width: 66%;\"></td>");
							var td4 = $("<td style=\"width: 20%;\"></td>");
							
							var checkBox = $("<input type='checkbox' lay-skin='primary'/>");
							td1.append(checkBox);
							td2.text(i+1);
							td3.text(item[i].itemName);
							td4.text(item[i].itemUnit);
							tr.append(td1);
							tr.append(td2);
							tr.append(td3);
							tr.append(td4);
							checkBox.attr('value', item[i].itemId);

							$("#detailsTbody").append(tr);

						}
					}
					var cks = $("input[type='checkbox']");
					for (var j = 0; j < myItem.length; j++) {
						for (var i = 0; i < cks.length; i++) {
							if (cks[i].value == myItem[j]) {
								cks[i].checked = true; // 回填选择
							}
						}

					}

					layui.use('form', function() {
						var form = layui.form;
						form.render();
					});
				},
				error : function() {
					layer.msg("查询失败");
				}
			});

}

$("#sure").on("click", function() {

	var layer = layui.layer;

	var itemsName = $("#itemsName").val();
	var price = $("#price").val();
	var addSelect = $("#addSelect").val();
	
	var cksVal = new Array();
	var cks = $("input[type='checkbox']");
	for (var i = 0; i < cks.length; i++) {
		if (cks[i].checked) {
			cksVal.push(cks[i].value);
		}
	}
	if (itemsName == "") {
		layer.msg('项目名不能为空', {
			icon : 2
		});
		return;
	}
	if (price == "") {
		layer.msg('价格不能为空', {
			icon : 2
		});
		return;
	}
	if (price <= 0) {
		layer.msg('请输入的价格大于0', {
			icon : 2
		});
		return;
	}

	if (addSelect == "") {
		layer.msg('请选择科室', {
			icon : 2
		});
		return;
	}

	$.ajax({
		url : "../updateItems.action",
		data : {
			itemsName : itemsName,
			price : price,
			depId : addSelect,
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
