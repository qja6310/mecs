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
var comboNameVal = "";

// 初始化页面
$(window).ready(function() {
	startAjax(1);
});

// 点击查询按钮的监听方法
$("#search").on("click", function() {
	comboNameVal = $("#comboName").val();

	startAjax(1);
});

$("#clean").on("click", function() {
	$("#comboName").val("");
});

function startAjax(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../comboQuery.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			comboName : comboNameVal
		},
		dataType : "json",
		success : function(respData) {

			var itemList = respData.combo;

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
		url : "../comboQuery.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			comboName : comboNameVal
		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {
			var itemList = respData.combo;
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

	var $mytable = $("#comboTable");
	$mytable
			.html("<thead><tr class=\"layui-bg-cyan\"><th style=\"width: 25%\";>套餐名</th><th style=\"width: 8%\";>价格</th><th style=\"width: 8%\";>状态</th><th style=\"width: 20%\";>创建时间</th><th>操作</th></tr></thead>");

	if (list.length > 0) {

		for (var i = 0; i < list.length; i++) {
			var $tr = $("<tr></tr>");
			var $td1 = $("<td></td>");
			var $td2 = $("<td></td>");
			var $td3 = $("<td></td>");
			var $td4 = $("<td></td>");
			var $td5 = $("<td></td>");

			$td1.text(list[i].comboName);
			$td2.text(list[i].comboPrice);
			$td3.text(list[i].comboState == 1 ? '上架' : '下架');
			$td4.text(list[i].comboCtime);
			
			var look = $("<button class=\"layui-btn  layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-search layuiadmin-button-btn\"></i>查看</button>");
			look.attr("onclick", "lookCombo('" + list[i].comboId + "')");
			$td5.append(look);
			
			var alter = $("<button class=\"layui-btn  layui-btn-warm layui-btn-sm\"><i class=\"layui-icon layui-icon-edit layuiadmin-button-btn\"></i>修改</button>");
			alter.attr("onclick", "alterCombo('" + list[i].comboId + "')");
			$td5.append(alter);
			alter.attr("onclick", "jumpDetails('" + list[i].comboId + "')");	
			
			var del = $("<button class=\"layui-btn layui-btn-danger layui-btn-sm\"><i class=\"layui-icon layui-icon-delete layuiadmin-button-btn\"></i>删除</button>");
			del.attr("onclick", "DelCombo('" + list[i].comboId + "')");
			$td5.append(del);

			if (list[i].comboState == 1) {
				// 下架用红色
				var off = $("<button class=\"layui-btn layui-bg-gray layui-btn-sm\"><i class=\"layui-icon layui-icon-down layuiadmin-button-btn\"></i>下架</button>");
				off.attr("onclick", "ComboState('" + list[i].comboId + "','"
						+ 0 + "')");
				$td5.append(off);
			} else {
				// 上架用银灰色
				var on = $("<button class=\"layui-btn layui-bg-gray layui-btn-sm\"><i class=\"layui-icon layui-icon-up layuiadmin-button-btn\"></i>上架</button>");
				on.attr("onclick", "ComboState('" + list[i].comboId + "','" + 1
						+ "')");
				$td5.append(on);
			}

			$tr.append($td1);
			$tr.append($td2);
			$tr.append($td3);
			$tr.append($td4);
			$tr.append($td5);

			$mytable.append($tr);

		}

	} else {
		var $tr = $("<tr></tr>");
		var $td = $("<td></td>");
		$td.attr("colSpan", 5);
		$td.text("查无数据");
		$td.attr("align", "center");
		$tr.append($td);
		$mytable.append($tr);
	}
}
/*
 * 增加套餐界面
 */

$("#add").on("click", function() {
	layer.open({
		type : 2,
		title : '新增套餐',
		skin : 'layui-layer-rim',
		area : [ '560px', '530px' ],
		offset : 'b',// 底部坐标
		content : 'comboAdd.jsp',
		end : function() {
			// 刷新页面,
			startAjax(1);
		},
	});

});

function DelCombo(id) {

	layui.use('layer', function() {
		var layer = layui.layer;
		layer.confirm('删除前请确认相关信息！你确定删除吗？', {
			btn : [ '确定', '取消' ]
		// 按钮
		}, function(index) {
			$.ajax({
				type : "post",/* 数据的提交方式，GET POST */
				url : "../comboDel.action",/* 访问路径action */
				data : {
					/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
					ComboId : id
				},
				dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
				success : function(respData) {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg(respData.del);
					});

					nextAjax(1);
				},
				error : function() {
					// alert("请求失败");
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('请求失败，请联系超管');
					});
				}
			});
		});
	});

}

function ComboState(id, state) {

	$.ajax({
		type : "post",
		url : "../alterComboState.action",
		data : {
			comboId : id,
			comboState : state
		},
		dataType : "json",
		success : function(respData) {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg(respData.alt);
			});

			nextAjax(1);
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败，请联系超管');
			});
		}
	});

}

function jumpDetails(comboId){
	layer.open({
		type : 2,
		title : "套餐编辑",
		skin : 'layui-layer-rim', // 加上边框
		area : [ '560px', '530px' ], // 宽高
		offset: 'b',//底部坐标
		content : 'comboUpdate.jsp?comboId='+comboId,
		end : function() {
			// 刷新页面,
			startAjax(1);
		},
		
	});
}

function lookCombo(comboId){
	layer.open({
		type : 2,
		title : "套餐详情",
		skin : 'layui-layer-rim', // 加上边框
		area : [ '560px', '530px' ], // 宽高
		offset: 'b',//底部坐标
		content : 'comboLook.jsp?comboId='+comboId,
		end : function() {
			// 刷新页面,
			startAjax(1);
		},
		
	});
}


