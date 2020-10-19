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

// 定义每页的记录数
var limit = 0;
var count = "";
var currPage = 1;

// 定义全局变量
var itemsNameVal = "";
var depIdVal = "";

// 初始化页面
$(window).ready(function() {
	initSelect();
	startAjax(1);
});

// 点击查询按钮的监听方法
$("#search").on("click", function() {
	itemsNameVal = $("#itemsName").val();
	depIdVal = $("#dep").val();

	startAjax(1);
});

$("#clean").on("click", function() {
	$("#itemsName").val("");
	$("#dep").val("");
});

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

function startAjax(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../itemsQuery.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			itemsName : itemsNameVal,
			depId : depIdVal
		},
		dataType : "json",
		success : function(respData) {

			var itemList = respData.item;

			count = respData.count
			limit = respData.limit

			layui.use('laypage', function() {
				var laypage = layui.laypage;

				// 执行一个laypage实例
				laypage.render({
					elem : 'pageBtn',// 注意，这里的 test1 是 ID，不用加 # 号
					count : count,// 数据总数，从服务端得到
					limit : limit,
					curr : cur,
					groups : 3,
					jump : function(obj) {
						nextAjax(obj.curr);
					}

				});
			});
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败，请联系超管');
			});
		}
	});

}

function nextAjax(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../itemsQuery.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			itemsName : itemsNameVal,
			depId : depIdVal
		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {
			var itemList = respData.items;
			count = respData.count;
			limit = respData.limit;
			initTale(itemList);
		},
		error : function() {
			// alert("请求失败");
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败，请联系超管');
			});
		}
	});

}

function initTale(list) {

	var $mytable = $("#itemsTable");
	$mytable
			.html("<thead><tr class=\"layui-bg-cyan\"><th style=\"width: 25%\";>项目名</th><th style=\"width: 15%\";>价格</th><th style=\"width: 20%\";>科室</th><th>操作</th></tr></thead>");

	if (list.length > 0) {

		for (var i = 0; i < list.length; i++) {
			var $tr = $("<tr></tr>");
			var $td1 = $("<td></td>");
			var $td2 = $("<td></td>");
			var $td3 = $("<td></td>");
			var $td4 = $("<td></td>");

			$td1.text(list[i].itemsName);
			$td2.text(list[i].itemsPrice);
			$td3.text(list[i].depId);
			var look = $("<button class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-search layuiadmin-button-btn\"></i>查看</button>");
			look.attr("onclick", "lookAll('" + list[i].itemsId + "')");
			$td4.append(look);
			
			var alter = $("<button class=\"layui-btn  layui-btn-warm layui-btn-sm\"><i class=\"layui-icon layui-icon-edit layuiadmin-button-btn\"></i>修改</button>");
			alter.attr("onclick", "toSetItemsId('" + list[i].itemsId + "')");
			
			$td4.append(alter);

			var del = $("<button class=\"layui-btn layui-btn-danger layui-btn-sm\"><i class=\"layui-icon layui-icon-delete layuiadmin-button-btn\"></i>删除</button>");
			del.attr("onclick", "DelItems('" + list[i].itemsId + "')");
		
			
			
		
			$td4.append(del);
			$tr.append($td1);
			$tr.append($td2);
			$tr.append($td3);
			$tr.append($td4);

			$mytable.append($tr);

		}

	} else {
		var $tr = $("<tr></tr>");
		var $td = $("<td></td>");
		$td.attr("colSpan", 4);
		$td.text("查无数据");
		$td.attr("align", "center");
		$tr.append($td);
		$mytable.append($tr);
	}
}

// 新增界面
$("#add").on("click", function() {
	layer.open({
		type : 2,
		title : '新增项目',
		skin : 'layui-layer-rim', // 加上边框
		area : [ '570px', '540px' ], // 宽高
		content : 'itemsAdd.html',
		end : function() {
			startAjax(1);
		},

	});
});

function DelItems(id) {

	layui.use('layer', function() {
		var layer = layui.layer;
		layer.confirm('删除前请确认相关信息！你确定删除吗？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function(index) {
			$.ajax({
				type : "post",
				url : "../itemsDelete.action",
				data : {
					itemsId : id,
				},
				dataType : "json",
				success : function(respData) {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg(respData.del);
					});

					setTimeout(function() {
						// 关闭弹出的html页面
						var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
						parent.layer.close(index);
					}, 2000);// 2秒

					startAjax(1);
				},
				error : function() {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('请求失败，请联系超管');
					});
				}
			});
		});
	});

}
// 初始化项目编辑界面 id存到后端
function toSetItemsId(id) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../toSetItemsId.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			itemsId : id

		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {

			layer.open({
				type : 2,
				title : '修改项目',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '570px', '540px' ], // 宽高
				content : 'itemsUpdate.html',
				end : function() {
					startAjax(1);
				},

			});

		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败');
			});
		}
	});

}
function lookAll(id){
	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../toSetItemsId.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			itemsId : id

		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {

			layer.open({
				type : 2,
				title : '项目详情',
				skin : 'layui-layer-rim', // 加上边框
				area : [ '570px', '540px' ], // 宽高
				content : 'itemsLook.html',
				end : function() {
					startAjax(1);
				},

			});

		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败');
			});
		}
	});
	
}


// function alterName(id) {
//
// var newName = $("#newName").val();
//
// if (newName == "undefined" || newName.trim() == null || newName == "") {
// layui.use('layer', function() {
// var layer = layui.layer;
// layer.msg('新名称不能为空', {
// icon : 2
// });
// });
// return false;
// }
//
// layer.confirm('修改前请确认相关信息！你确定修改吗？', {
// btn : [ '确定', '取消' ]
// // 按钮
// }, function(index) {
// $.ajax({
// type : "post",
// url : "../itemAlter.action",
// data : {
// itemId : id,
// itemsName : newName
// },
// dataType : "json",
// success : function(respData) {
// layui.use('layer', function() {
// var layer = layui.layer;
// layer.msg(respData.alter);
// });
// startAjax(1);
// },
// error : function() {
// layui.use('layer', function() {
// var layer = layui.layer;
// layer.msg('修改失败，请联系超管');
// });
// }
// });
// });
//
// }
