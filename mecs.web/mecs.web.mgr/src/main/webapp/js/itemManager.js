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
var itemNameVal = "";

// 初始化页面
$(window).ready(function() {
	startAjax(1);
});

// 点击查询按钮的监听方法
$("#search").on("click", function() {
	itemNameVal = $("#itemName").val();

	startAjax(1);
});

$("#clean").on("click", function() {
	$("#itemName").val("");
});

function startAjax(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../itemQuery.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			itemName : itemNameVal
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
		url : "../itemQuery.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			itemName : itemNameVal
		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {
			var itemList = respData.item;
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

	var $mytable = $("#itemTable");
	$mytable
			.html("<thead><tr class=\"layui-bg-cyan\"><th style=\"width: 15%\";>细项名</th><th style=\"width: 15%\";>单位</th><th style=\"width: 20%\";>最近修改时间</th><th>操作</th></tr></thead>");

	if (list.length > 0) {

		for (var i = 0; i < list.length; i++) {
			var $tr = $("<tr></tr>");
			var $td1 = $("<td></td>");
			var $td2 = $("<td></td>");
			var $td3 = $("<td></td>");
			var $td4 = $("<td></td>");

			$td1.text(list[i].itemName);
			$td2.text(list[i].itemUnit);
			$td3.text(list[i].itemUtime);
			var newName = $("<input  type=\"text\" id='newName"
					+ list[i].itemId
					+ "'  placeholder=\"请输入细项名\"  class=\"layui-input\" style=\"width: 200px; height: 30px; float:left;\">");
			$td4.append(newName);

			var alter = $("<button style=\"margin-left: 15px;\"  lay-submit lay-filter=\"LAY-user-back-search\" class=\"layui-btn layui-btn-warm layui-btn-sm\"><i class=\"layui-icon layui-icon-edit layuiadmin-button-btn\"></i>修改</button>");
			alter.attr("onclick", "alterName(" + list[i].itemId + ")");
			$td4.append(alter);

			var del = $("<button class=\"layui-btn layui-btn-danger layui-btn-sm\"><i class=\"layui-icon layui-icon-delete layuiadmin-button-btn\"></i>删除</button>");
			del.attr("onclick", "DelItem('" + list[i].itemId + "')");
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

function alterName(id) {

	var aaa = "newName" + id;
	var newName = $("#" + aaa).val().trim();

	if (newName == "undefined" || newName == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('新名称不能为空', {
				icon : 2
			});
		});
		return false;
	}

	layui.use('layer', function() {
		var layer = layui.layer;
		layer.confirm('修改前请确认相关信息！你确定修改吗？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function(index) {
			$.ajax({
				type : "post",
				url : "../itemAlter.action",
				data : {
					itemId : id,
					itemName : newName
				},
				dataType : "json",
				success : function(respData) {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg(respData.alter);
					});
					startAjax(1);
				},
				error : function() {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('修改失败，请联系超管');
					});
				}
			});
		});
	});

}

$("#add").on("click", function() {

	layer.open({
		type : 2,
		title : '新增细项',
		skin : 'layui-layer-rim',
		area : [ '450px', '280px' ],
		content : 'addItem.html',
		end : function() {
			// 刷新页面,
			startAjax(1);
		},
	});

});

$("#confirm").on("click", function() {
	// layer.alert("想干嘛");
	var itemName = $("#itemName").val();
	var itemUnit = $("#itemUnit").val();

	if (itemName == "undefined" || itemName.trim() == null || itemName == "") {
		layer.msg('细项名称不能为空', {
			icon : 2
		});
		return false;
	}
	if (itemUnit == "undefined" || itemUnit.trim() == null || itemUnit == "") {
		layer.msg('细项单位不能为空', {
			icon : 2
		});
		return false;
	}

	$.ajax({
		type : "post",
		url : "../itemAdd.action",
		data : {
			itemName : itemName,
			itemUnit : itemUnit,
		},
		dataType : "json",
		success : function(respData) {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg(respData.add);
			});

			setTimeout(function() {
				// 关闭弹出的html页面
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
			}, 2000);// 2秒
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败，请联系超管');
			});
		}
	});

});

$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
});

function DelItem(id) {

	layer.confirm('删除前请确认相关信息！你确定删除吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		$.ajax({
			type : "post",
			url : "../itemDel.action",
			data : {
				itemId : id,
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

}