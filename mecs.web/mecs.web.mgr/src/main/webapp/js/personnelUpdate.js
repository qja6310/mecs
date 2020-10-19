$().ready(function() {
	showUpdatePanel();
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

// 初始化界面
function showUpdatePanel() {
	$.ajax({
		url : "../showAdminUpdate.action",
		type : "post",

		dataType : "json",
		success : function(respData) {
			//人员ID存到隐藏域
			$("#fid").val(respData.admin.adminId);
			//回填账号姓名信息 
			$("#adminName").val(respData.admin.adminName);
			$("#adminAcc").val(respData.admin.adminAcc);
			// 初始化科室下拉框
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
			// 设置回填值
			dep.val(respData.admin.depId)
			// 初始化角色
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
			var cks = $("input[type='checkbox']");
			for (var j = 0; j < respData.myRole.length; j++) {
				for (var i = 0; i < cks.length; i++) {
					if (cks[i].value == respData.myRole[j]) {
						cks[i].checked = true; // 回填选择
					}
				}

			}
			layui.use('form', function(){
		        var form = layui.form; 
		        form.render();
		 }); 
		},
		error : function() {
			alert("用户查询失败")
		}
	});

}

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});

// 修改人员确定
$("#sure").on("click", function() {
	var layer = layui.layer;
	var adminName = $("#adminName").val();
	var adminAcc = $("#adminAcc").val();
	var addSelect = $("#addSelect").val();
	var id = $("#fid").val();
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
		});;
		return;
	}
	if (adminAcc == "") {
		layer.msg("账号不能为空", {
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
		url : "../toUpdateAdmin.action",
		data : {
			adId:id,
			cksVal : cksVal,
			adName : adminName,
			adAcc : adminAcc,
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
			layer.msg("用户查询失败")
		}
	});

});
