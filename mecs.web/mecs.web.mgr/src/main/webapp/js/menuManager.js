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
	var menuNameHd = $("#menuNameHd").val();
	var urlHd = $("#urlHd").val();
	// var levelHd = $("#levelHd").val();

	$.ajax({
		url : "../queryMenu.action",
		type : "post",
		data : {
			currPage : currPage,
			menuName : menuNameHd,
			url : urlHd
		// level : levelHd

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
			layer.msg("用户查询失败")
		}
	});
}

function nextAjax(cur) {
	var menuNameHd = $("#menuNameHd").val();
	var urlHd = $("#urlHd").val();
	// var levelHd = $("#levelHd").val();
	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../queryMenu.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			currPage : cur,
			menuName : menuNameHd,
			url : urlHd
		// level : levelHd
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
			.html("<thead><tr class='layui-bg-cyan'><th style='width: 10%;'>菜单名</th><th style='width: 15%;'>URL</th><th style='width: 10%;' >图标</th><th style='width: 10%;'>父级菜单</th><th style='width: 15%;' >创建时间</th><th style='width: 25%;'>操作事项</th></tr></thead>");
	// 获取返回的集合
	// 判断查询是否有数据
	if (list.length > 0) {
		for (var i = 0; i < list.length; i++) {
			var tr = $("<tr></tr>");
			var td1 = $("<td  style='width: 10%;'></td>");
			var td2 = $("<td style='width: 15%;'></td>");
			var td3 = $("<td style='width: 10%;'></td>");
			var td4 = $("<td style='width: 10%;'></td>");
			var td5 = $("<td style='width: 15%;'></td>");
//			var td6 = $("<td style='width: 15%;'></td>");
			var td7 = $("<td style='width: 25%;'></td>");
			// 修改编辑按钮
			var update = $("<button class=\"layui-btn layui-bg-orange layui-btn-sm\"><i class=\"layui-icon layui-icon-set-sm layuiadmin-button-btn\"></i>编辑 </button>");
			update.attr("id", list[i].menuId);
			update.on("click", function() {
				showUpPanel($(this).attr("id"));

			});

			var del = $("<button class=\"layui-btn layui-btn-danger layui-btn-sm\"><i class=\"layui-icon layui-icon-delete layuiadmin-button-btn\"></i>删除</button>");
			del.attr("id", list[i].menuId);
			del.on("click", function() {
				delMenu($(this).attr("id"));

			});

			td7.append(update);
			td7.append(del);
			// 给td节点中添加文本
			td1.text(list[i].menuName);
			td2.text(list[i].menuUrl);
			td3.text(list[i].menuIcon);

			if (list[i].menuPid == null) {
				td4.text("一级菜单");
				var add = $("<button class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon  layuiadmin-button-btn\"></i>添加子菜单 </button>");
				add.attr("id", list[i].menuId);
				add.on("click", function() {
					addSubMenu($(this).attr("id"));

				});
				td7.append(add);
			} else {
				td4.text(list[i].menuPid)

			}

			td5.text(list[i].menuCtime);
//			td6.text(list[i].menuUtime);

			td7.attr("align", "left");

			// 把节点串起来
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);
			tr.append(td5);
//			tr.append(td6);
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
	var menuName = $("#menuName").val();
	var url = $("#url").val();
	var level = $("#level").val();

	$("#menuNameHd").val(menuName);
	$("#urlHd").val(url);
	$("#levelHd").val(level);
	queryData(1);
});
// 新增界面
$("#add").on("click", function() {
	layer.open({
		type : 2,
		title : '新增一级菜单',
		skin : 'layui-layer-rim', // 加上边框
		area : [ '590px', '540px' ], // 宽高
		content : 'menuAdd.html',
		end : function() {
			queryData(1);
		},

	});
});
// 删除菜单
function delMenu(id) {
	layer.confirm('删除会同时删除所有子菜单导航且不可恢复 ， 你确定删除吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		$.ajax({
			type : "post",/* 数据的提交方式，GET POST */
			url : "../delMenu.action",/* 访问路径action */
			data : {
				/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
				menuId : id
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
	$("#menuName").val("");
	$("#url").val("");
	// $("#level").val("");
});

// 初始化编辑界面

function showUpPanel(id) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../toSetMenuId.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			menuId : id

		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {

			layer.open({
				type : 2,
				title : '修改菜单',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '590px', '540px' ], // 宽高
				content : 'menuUpdate.html',
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

function addSubMenu(id) {
	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../toSetMenuId.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			menuId : id

		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {

			layer.open({
				type : 2,
				title : '新增子菜单',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '590px', '540px' ], // 宽高
				content : 'menuAddSub.html',
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
