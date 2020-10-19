layui.use('form', function() {
	var form = layui.form;

	// 各种基于事件的操作，下面会有进一步介绍
});
$().ready(function() {
	showPanel();
})
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});
function showPanel() {
	var layer = layui.layer;
	$
			.ajax({
				url : "../lookItems.action",
				type : "post",
				dataType : "json",
				success : function(respData) {
					$("#itemsName").val(respData.items.itemsName);
					$("#price").val(respData.items.itemsPrice);
					$("#dep").val(respData.items.depId);
		
					var myDep = respData.items.depId;
					var dep = $("#addSelect");

					var itemPanel = $("#item");
					var item = respData.itemAll;
					if (item.length > 0) {
						for (var i = 0; i < item.length; i++) {
							// var checkbox1 = $("<input type='checkbox'
							// lay-skin='primary'/>");
							// checkbox1.attr("title", item[i].itemName);
							// checkbox1.attr("value", item[i].itemId);
							// itemPanel.append(checkbox1);
							var tr = $("<tr></tr>");
							var td2 = $("<td style=\"width: 9.5%;\"></td>");
							var td3 = $("<td style=\"width: 61%;\"></td>");
							var td4 = $("<td style=\"width: 20%;\"></td>");

							td2.text(i + 1);
							td3.text(item[i].itemName);
							td4.text(item[i].itemUnit);
							tr.append(td2);
							tr.append(td3);
							tr.append(td4);

							$("#detailsTbody").append(tr);

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