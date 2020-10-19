$().ready(function() {
	showAdd();
})
// 日期控件
layui
		.use(
				[ 'form', 'layedit', 'laydate' ],
				function() {
					var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

					// 日期
					laydate.render({
						elem : '#date'
					});
					laydate.render({
						elem : '#date1'
					});

					// 日期
					laydate.render({
						elem : '#date2'
					});
					laydate.render({
						elem : '#date3'
					});

				});

function showAdd() {
	$.ajax({
		url : "../showAllMenu.action",
		type : "post",
		dataType : "json",
		success : function(respData) {
			var list = respData.menu;
			var menu = $("#menu");
			if (list.length != 0) {
				for (var i = 0; i < list.length; i++) {
					if (list[i].menuPid == 0) {
						var checkbox1 = $("<input type='checkbox'  />");
						menu.append(checkbox1);
						menu.append(list[i].menuName);
						checkbox1.attr("name", "check" + (i + 1));
						checkbox1.attr("id", 1);
						checkbox1.attr("value", list[i].menuId);
						menu.append($("<br/>"));

						

						checkbox1.on("click", function() {
							chobx($(this));
						});

						for (var j = 0; j < list.length; j++) {
							if (list[j].menuPid == list[i].menuId) {
								var checkbox2 = $("<input type='checkbox' />");
								menu.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
								menu.append(checkbox2);
								menu.append(list[j].menuName);
								checkbox2.attr("name", "check" + (i + 1));
								checkbox2.attr("id", 2);
								checkbox2.attr("value", list[j].menuId);
								
								checkbox2.on("click", function() {
									chobx($(this));
								});
							}
						}
						menu.append($("<br/>"));

					}

				}

			}
			layui.use('form', function(){
		        var form = layui.form; 
		        form.render();
		 }); 
		},
		error : function() {
			layer.msg("用户查询失败")
		}
	});

}
function chobx(checkbox) {

	var name = checkbox.attr("name");
	var value = checkbox.attr("id");
	var check = document.getElementsByName(name);

	// 父级单选框
	if (value == 1) {
		// 如果父级被选择

		for (var i = 0; i < check.length; i++) {
			if (check[0].checked) {

				check[i].checked = true;
			} else {
				// 否者设为false
				check[i].checked = false;
			}

		}
	} else {
		// 如果是是下面的选择框被选择，则父类也被选择
		for (var i = 0; i < check.length; i++) {
			if (check[i].checked) {
				check[0].checked = true;
			}
		}
		for (var i = 0; i < check.length; i++) {
			// 下面的没被选择
			var num = 0;
			for (var i = 1; i < check.length; i++) {
				if (check[i].checked) {
					num++;
				}
			}
			// 如果都没被选择 0也不被选
			if (num == 0) {
				check[0].checked = false;
			}
		}

	}
}

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});

// 增加人员确定
$("#sure").on("click", function() {
	var layer = layui.layer;
	var role = $("#role").val();
	var cksVal = new Array();
	var cks = $("input[type='checkbox']");
	for (var i = 0; i < cks.length; i++) {
		if (cks[i].checked) {
			cksVal.push(cks[i].value);
		}
	}
	if (role == "") {
		layer.msg("角色名不能为空", {
			icon : 2
		});
		return;
	}

	/*if (cksVal.length == 0) {
		layer.msg("请选择菜单", {
			icon : 2
		});
		return;
	}
*/
	// $.ajaxSettings.traditional = true;// 能传集合
	$.ajax({
		url : "../addRoleMenu.action",
		data : {
			cksVal : cksVal,
			role : role

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
