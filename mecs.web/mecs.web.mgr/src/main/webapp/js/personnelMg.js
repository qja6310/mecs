//日期控件
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

$().ready(function() {

	initSelect();
	queryData(1);
})

function initSelect() {

	$.ajax({
		url : "../initAdmSelect.action",
		type : "post",
		dataType : "json",
		success : function(respData) {
			var list = respData.res;
			var dep = $("#dep");
			dep.html("<option value=''>请选择</option>");
			if (list.length > 0) {
				for (var i = 0; i < list.length; i++) {
					var op = $("<option></option>");
					op.append(list[i].depName);
					op.val(list[i].depId);
					dep.append(op);

				}
			}
			layui.use('form', function() {
				var form = layui.form;
				form.render();
			});
		},
		error : function() {
		}
	});

}

function queryData(currPage) {
	var adminAccHd = $("#adminAccHd").val();
	var adminNameHd = $("#adminNameHd").val();
	var stateHd = $("#stateHd").val();
	var depHd = $("#depHd").val();
	var date1Hd = $("#date1Hd").val();
	var date2Hd = $("#date2Hd").val();

	$.ajax({
		url : "../queryAdmin.action",
		type : "post",
		data : {
			currPage : currPage,
			adName : adminNameHd,
			adAcc : adminAccHd,
			adDepId : depHd,
			state : stateHd,
			beginDate : date1Hd,
			endDate : date2Hd

		},
		dataType : "json",
		success : function(respData) {
			var count = respData.count;
			var limit = respData.limit;

			layui.use('laypage', function() {
				var laypage = layui.laypage;

				// 执行一个laypage实例
				laypage.render({
					elem : 'pageBtn',// 注意，这里的 test1 是 ID，不用加 # 号
					count : count,// 数据总数，从服务端得到
					limit : limit,
					curr : currPage,
					groups : 3,
					jump : function(obj) {
						nextAjax(obj.curr);
					}

				});

			});

		},
		error : function() {
			alert("用户查询失败")
		}
	});
}

function nextAjax(cur) {
	var adminAccHd = $("#adminAccHd").val();
	var adminNameHd = $("#adminNameHd").val();
	var stateHd = $("#stateHd").val();
	var depHd = $("#depHd").val();
	var date1Hd = $("#date1Hd").val();
	var date2Hd = $("#date2Hd").val();

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../queryAdmin.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			currPage : cur,
			adName : adminNameHd,
			adAcc : adminAccHd,
			adDepId : depHd,
			state : stateHd,
			beginDate : date1Hd,
			endDate : date2Hd
		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {
			var logList = respData.logList;
			count = respData.count
			limit = respData.limit
			showTable(respData.table);
		},
		error : function() {
			layer.msg("请求失败");
		}
	});

}
// 显示表格
function showTable(list) {
	var mytable = $("#table");
	mytable
			.html("<thead><tr class='layui-bg-cyan'><th>账号</th><th >姓名</th><th >科室</th><th >状态</th><th >创建时间</th><th >修改时间</th><th >操作事项</th></tr></thead>");
	// 获取返回的集合
	// 判断查询是否有数据
	if (list.length > 0) {
		for (var i = 0; i < list.length; i++) {
			var tr = $("<tr></tr>");
			var td1 = $("<td></td>");
			var td2 = $("<td></td>");
			var td3 = $("<td></td>");
			var td4 = $("<td></td>");
			var td5 = $("<td></td>");
			var td6 = $("<td></td>");
			var td7 = $("<td></td>");

			// 给td节点中添加文本
			td1.text(list[i].adminAcc);
			td2.text(list[i].adminName);
			td3.text(list[i].depId);
			td4.text(list[i].adminState);
			td5.text(list[i].adminCtime);
			td6.text(list[i].adminUtime);

			// 修改编辑按钮
			var update = $("<button class=\"layui-btn layui-bg-orange layui-btn-sm\"><i class=\"layui-icon layui-icon-set-sm layuiadmin-button-btn\"></i>编辑 </button>");
			update.attr("id", list[i].adminId);
			update.on("click", function() {
				showUpPanel($(this).attr("id"));

			});

			var del = $("<button class=\"layui-btn layui-btn-danger layui-btn-sm\"><i class=\"layui-icon layui-icon-delete layuiadmin-button-btn\"></i>删除</button>");
			del.attr("id", list[i].adminId);
			del.on("click", function() {
				delAdmin($(this).attr("id"));

			});
			// 重置密码按钮
			var repswd = $("<button class=\"layui-btn layui-bg-green layui-btn-sm\"><i class=\"layui-icon layui-icon-refresh-1 layuiadmin-button-btn\"></i>重置密码</button>");
			repswd.attr("id", list[i].adminId);

			repswd.on("click", function() {
				rePswd($(this).attr("id"));

			});

			// 禁用启用按钮
			var state = $("<button class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-password layuiadmin-button-btn\"></i></button>");


			if (list[i].adminState == "禁用") {
				state = $("<button class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-password layuiadmin-button-btn\"></i>启用</button>");
				state.attr("id", list[i].adminId);
				state.attr("state", list[i].adminState);
				state.on("click", function() {
					upState($(this).attr("id"), $(this).attr("state"));

				});

			} else {
				state = $("<button class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-password layuiadmin-button-btn\"></i>禁用</button>");
				state.attr("id", list[i].adminId);
				state.attr("state", list[i].adminState);
				state.on("click", function() {
					upState($(this).attr("id"), $(this).attr("state"));

				});
			}
			td7.attr("align", "center");
			td7.append(update);
			td7.append(repswd);
			td7.append(state);
			td7.append(del);
			// 把节点串起来
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);
			tr.append(td5);
			tr.append(td6);
			tr.append(td7);
			mytable.append(tr);

		}
	} else {// 没有数据
		var tr = $("<tr></tr>");
		var td = $("<td></td>");
		td.attr("colSpan", 7);
		td.text("查询不到数据");
		td.attr("align", "center");
		tr.append(td);
		mytable.append(tr);
	}

}
// 搜索
$("#search").on("click", function() {
	var adminAcc = $("#adminAcc").val();
	var adminName = $("#adminName").val();
	var state = $("#state").val();
	var dep = $("#dep").val();
	var date1 = $("#date1").val();
	var date2 = $("#date2").val();
	if (date1 > date2) {
		var layer = layui.layer;
		layer.msg("开始时间不能大于结束时间");
		return;
	}

	$("#adminAccHd").val(adminAcc);
	$("#adminNameHd").val(adminName);
	$("#stateHd").val(state);
	$("#depHd").val(dep);
	$("#date1Hd").val(date1);
	$("#date2Hd").val(date2);
	queryData(1);
});
// 新增界面
$("#add").on("click", function() {
	layer.open({
		type : 2,
		title : '新增人员',
		skin : 'layui-layer-rim', // 加上边框
		area : [ '570px', '540px' ], // 宽高
		content : 'personelAdd.html',
		end : function() {
			queryData(1);
		},

	});
});
// 删除人员
function delAdmin(id) {
	layer.confirm('删除前请确认相关信息！你确定删除吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		$.ajax({
			type : "post",/* 数据的提交方式，GET POST */
			url : "../delAdmin.action",/* 访问路径action */
			data : {
				/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
				adId : id
			},
			dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
			success : function(respData) {

				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg(respData.res);
				});
				queryData(1);
			},
			error : function() {
				layer.msg("请求失败");
			}
		});

	});

}
// 禁用启用人员
function upState(id, state) {
	if (state == "禁用") {
		state = "启用"
	} else {
		state = "禁用"
	}

	layer.confirm('你确定' + state + '吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		$.ajax({
			type : "post",/* 数据的提交方式，GET POST */
			url : "../toAdminState.action",/* 访问路径action */
			data : {
				/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
				adId : id,
				state : state
			},
			dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
			success : function(respData) {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg(respData.res);
				});
				queryData(1);
			},
			error : function() {
				alert("请求失败");
			}
		});

	});

}
// 重置密码
function rePswd(id) {

	layer.confirm('你确定重置密码吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		$.ajax({
			type : "post",/* 数据的提交方式，GET POST */
			url : "../rePswd.action",/* 访问路径action */
			data : {
				/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
				adId : id,

			},
			dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
			success : function(respData) {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg(respData.res);
				});
				queryData(1);
			},
			error : function() {
				layer.msg("请求失败");
			}
		});

	});

}

// 清空
$("#clean").on("click", function() {
	$("#adminAcc").val("");
	$("#adminName").val("");
	$("#state").val("");
	$("#dep").val("");
	$("#date1").val("");
	$("#date2").val("");
});

// 初始化编辑界面

function showUpPanel(id) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../toSetAdminId.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			adId : id
		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {

			layer.open({
				type : 2,
				title : '编辑人员',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '570px', '540px' ], // 宽高
				content : 'psrsonnelUpdate.html',
				end : function() {
					queryData(1);
				},

			});

		},
		error : function() {
			layer.msg("请求失败");
		}
	});

}
