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
	queryData(1);
})

function queryData(currPage) {
	var roleHd = $("#roleHd").val();
	var date1Hd = $("#date1Hd").val();
	var date2Hd = $("#date2Hd").val();

	$.ajax({
		url : "../queryRole.action",
		type : "post",
		data : {
			currPage : currPage,
			roleName : roleHd,
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
					groups:3 ,

					jump : function(obj) {
						nextAjax(obj.curr);
					}

				});

			});

		},
		error : function() {
			layer.msg("用户查询失败")
		}
	});
}

function nextAjax(cur) {
	var roleHd = $("#roleHd").val();
	var date1Hd = $("#date1Hd").val();
	var date2Hd = $("#date2Hd").val();

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../queryRole.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			currPage : cur,
			roleName : roleHd,
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
			.html("<thead><tr class='layui-bg-cyan'><th>角色名称</th><th>创建时间</th><th >修改时间</th><th >操作事项</th></tr></thead>");
	// 获取返回的集合
	// 判断查询是否有数据
	if (list.length > 0) {
		for (var i = 0; i < list.length; i++) {
			var tr = $("<tr></tr>");
			var td1 = $("<td></td>");
			var td2 = $("<td></td>");
			var td3 = $("<td></td>");
			var td4 = $("<td></td>");

			// 给td节点中添加文本
			td1.text(list[i].roleName);
			td2.text(list[i].roleCtime);
			td3.text(list[i].roleUtime);

			// 修改编辑按钮
			var update = $("<button class=\"layui-btn layui-bg-orange layui-btn-sm\"><i class=\"layui-icon layui-icon-set-sm layuiadmin-button-btn\"></i>编辑 </button>");
			update.attr("id", list[i].roleId);
			update.on("click", function() {
				showUpPanel($(this).attr("id"));

			});

			var del = $("<button class=\"layui-btn layui-btn-danger layui-btn-sm\"><i class=\"layui-icon layui-icon-delete layuiadmin-button-btn\"></i>删除</button>");
			del.attr("id", list[i].roleId);
			del.on("click", function() {
				delRole($(this).attr("id"));

			});
			
			td4.attr("align", "center");
			td4.append(update);
			td4.append(del);
			// 把节点串起来
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);
			mytable.append(tr);

		}
	} else {// 没有数据
		var tr = $("<tr></tr>");
		var td = $("<td></td>");
		td.attr("colSpan", 4);
		td.text("查询不到数据");
		td.attr("align", "center");
		tr.append(td);
		mytable.append(tr);
	}

}
// 搜索
$("#search").on("click", function() {
	var role = $("#role").val();
	var date1 = $("#date1").val();
	var date2 = $("#date2").val();
	if (date1 > date2) {
		var layer = layui.layer;
		layer.msg("开始时间不能大于结束时间");
		return;
	}

	$("#roleHd").val(role);
	$("#date1Hd").val(date1);
	$("#date2Hd").val(date2);
	queryData(1);
});
// 新增界面
$("#add").on("click", function() {
	layer.open({
		type : 2,
		title : '新增角色',
		skin : 'layui-layer-rim', // 加上边框
		area : [ '570px', '540px' ], // 宽高
		content : 'roleAdd.html',
		end : function() {
			queryData(1);
		},

	});
});
// 删除人员
function delRole(id) {
	layer.confirm('删除前请确认相关信息！你确定删除吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		$.ajax({
			type : "post",/* 数据的提交方式，GET POST */
			url : "../delRole.action",/* 访问路径action */
			data : {
				/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
				roleId : id
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
	$("#role").val("");
	$("#date1").val("");
	$("#date2").val("");
});

// 初始化编辑界面

function showUpPanel(id) {
	

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../toSetRoleId.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			roleId : id,

		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {

			layer.open({
				type : 2,
				title : '修改角色',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '570px', '540px' ], // 宽高
				content : 'roleUpdate.html',
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
