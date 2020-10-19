//日期控件
layui.use([ 'form', 'layedit', 'laydate' ],
				function() {
					
					var form = layui.form,
					layer = layui.layer, 
					layedit = layui.layedit,
					laydate = layui.laydate;
			
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

$().ready(function() {
	showAddPanel();

})


// 初始化界面
function showAddPanel() {
	$.ajax({
		url : "../initAddAdmSelect.action",
		type : "post",
		dataType : "json",
		success : function(respData) {
			
			var list = respData.dep;
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
			var roPanel = $("#role");
			var role = respData.role;
			if (role.length > 0) {
				for (var i = 0; i < role.length; i++) {
					var checkbox1 = $("<input type='checkbox' />");
					checkbox1.attr("title", role[i].roleName);
					checkbox1.attr("value", role[i].roleId);
					roPanel.append(checkbox1);
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

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});

// 增加人员确定
$("#sure").on("click", function() {
	var layer = layui.layer;
	var adminName = $("#adminName").val();
	var adminAcc = $("#adminAcc").val();
	var adminPswd = $("#adminPswd").val();
	var rePswd = $("#rePswd").val();
	var addSelect = $("#addSelect").val();
	var cksVal = new Array();
	var cks = $("input[type='checkbox']");
	for (var i = 0; i < cks.length; i++) {
		if (cks[i].checked) {
			cksVal.push(cks[i].value);
		}
	}
	if (adminName == "") {
		layer.msg("姓名不能为空", {
			icon : 2
		});
		return;
	}
	if (adminAcc == "") {
		layer.msg("账号不能为空", {
			icon : 2
		});
		return;
	}
	if (adminPswd == "") {
		layer.msg("密码不能为空", {
			icon : 2
		});
		return;
	}
	if (adminPswd .length<6) {
		layer.msg("密码不能少于6位", {
			icon : 2
		});
		return;
	}
	if (rePswd == "") {
		layer.msg("再次密码不能为空", {
			icon : 2
		});
		return;
	}
	if (rePswd .length<6) {
		layer.msg("再次密码不能少于6位", {
			icon : 2
		});
		return;
	}
	if (rePswd != adminPswd) {
		layer.msg("请确认两次密码是否一致", {
			icon : 2
		});
		return;
	}
	if (addSelect == "") {
		layer.msg("请选择科室", {
			icon : 2
		});
		return;
	}
/*	if (cksVal.length == 0) {
		layer.msg("请选择角色", {
			icon : 2
		});
		return;
	}*/

	// $.ajaxSettings.traditional = true;// 能传集合
	$.ajax({
		url : "../addAdmin.action",
		data : {
			cksVal : cksVal,
			adName : adminName,
			adAcc : adminAcc,
			adPswd : adminPswd,
			adDepId : addSelect

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

