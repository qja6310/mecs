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
var beginTimeVal = "";
var endTimeVal = "";
var userAccVal = "";
var cardState = "";
var cardNumber = "";

// 初始化页面
$(window).ready(function() {
	startAjax(1);
	getPrefix();
});

// 点击查询按钮的监听方法
$("#search").on("click", function() {
	beginTimeVal = $("#date1").val();
	endTimeVal = $("#date3").val();
	userAccVal = $("#userName").val();
	cardState = $("[name = 'state']").val();
	cardNumber = $("#cardNumber").val();

	var d1 = new Date(beginTimeVal.replace(/\-/g, "\/"));
	var d2 = new Date(endTimeVal.replace(/\-/g, "\/"));
	if (beginTimeVal != "" && endTimeVal != "" && d1 > d2) {
		layer.msg('开始时间不能大于结束时间', {
			icon : 2
		});
		return false;
	}

	startAjax(1);
});

$("#clean").on("click", function() {
	$("#date1").val("");
	$("#date3").val("");
	$("#userName").val("");
	$("[name = 'state']").val("");
	$("#cardNumber").val("");
});

function startAjax(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../queryCard.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			startTime : beginTimeVal,
			endTime : endTimeVal,
			userName : userAccVal,
			cardState : cardState,
			cardNumber : cardNumber
		},
		dataType : "json",
		success : function(respData) {

			var cardList = respData.card;

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
		url : "../queryCard.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			startTime : beginTimeVal,
			endTime : endTimeVal,
			userName : userAccVal,
			cardState : cardState,
			cardNumber : cardNumber
		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {
			var cardList = respData.card;
			count = respData.allCardCount;
			limit = respData.limit;
			initTale(cardList);
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

	var $mytable = $("#cardTable");
	$mytable
			.html("<thead><tr class=\"layui-bg-cyan\"><th style=\"width: 10%\";>卡号</th><th style=\"width: 10%\";>持有人</th><th style=\"width: 25%\";>入库时间</th><th style=\"width: 25%\";>销售时间</th><th style=\"width: 15%\";>状态</th><th >操作</th></tr></thead>");

	if (list.length > 0) {

		for (var i = 0; i < list.length; i++) {
			var $tr = $("<tr></tr>");
			var $td1 = $("<td></td>");
			var $td2 = $("<td></td>");
			var $td3 = $("<td></td>");
			var $td4 = $("<td></td>");
			var $td5 = $("<td></td>");
			var $td6 = $("<td></td>");

			$td1.text(list[i].cardNum);
			$td2.text(list[i].user.userName);
			$td3.text(list[i].cardCtime);
			$td4.text(list[i].cardUtime);
			$td5.text(list[i].dict.dictName);

			var del = $("<button class=\"layui-btn layui-btn-danger layui-btn-sm\"><i class=\"layui-icon layui-icon-delete layuiadmin-button-btn\"></i>删除</button>");
			del.attr("onclick", "DelCard('" + list[i].cardId + "','"
					+ list[i].cardNum + "')");
			$td6.append(del);

			$tr.append($td1);
			$tr.append($td2);
			$tr.append($td3);
			$tr.append($td4);
			$tr.append($td5);
			$tr.append($td6);

			$mytable.append($tr);

		}

	} else {
		var $tr = $("<tr></tr>");
		var $td = $("<td></td>");
		$td.attr("colSpan", 6);
		$td.text("查无数据");
		$td.attr("align", "center");
		$tr.append($td);
		$mytable.append($tr);
	}
}

function DelCard(id, cardNum) {
	layer.confirm('删除前请确认相关信息！你确定删除吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		$.ajax({
			type : "post",
			url : "../delCard.action",
			data : {
				cardId : id,
				cardNum : cardNum
			},
			dataType : "json",
			success : function(respData) {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg(respData.del);
				});
				startAjax(1);
			},
			error : function() {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('删除失败，请联系超管');
				});
			}
		});
	});

}

function getPrefix() {
	$.ajax({
		type : "post",
		url : "../dictPrefix.action",
		data : {},
		dataType : "json",
		success : function(respData) {
			$("#prefix").val(respData.prefix)
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('获取失败，请联系超管');
			});
		}
	});
}

$("#addCard").on("click", function() {

	layer.open({
		type : 2,
		title : '卡入库',
		skin : 'layui-layer-rim',
		area : [ '450px', '300px' ],
		content : 'cardStore.html',
		end : function() {
			// 刷新页面,
			startAjax(1);
		},
	});

});

$("#add")
		.on(
				"click",
				function() {

					var startNum = $("#startNumber").val();
					var endNum = $("#endNumber").val();
					var cardPrefix = $("#prefix").val();

					if (startNum == "undefined" || startNum.trim() == null
							|| startNum == "") {
						layer.msg('开始卡号不能为空', {
							icon : 2
						});
						return false;
					}
					if (endNum == "undefined" || endNum.trim() == null
							|| endNum == "") {
						layer.msg('结束卡号不能为空', {
							icon : 2
						});
						return false;
					}
					if (startNum <= 0 || startNum <= 0) {
						layer.msg('开始、和截止卡号请填写大于0的数', {
							icon : 2
						});
						return false;
					}
					if (parseInt(endNum) < parseInt(startNum)) {
						layer.msg('请填写截止卡号大于开始卡号', {
							icon : 2
						});
						return false;
					}
					$
							.ajax({
								type : "post",
								url : "../cardStore.action",
								data : {
									startNum : startNum,
									endNum : endNum,
									cardPrefix : cardPrefix
								},
								dataType : "json",
								success : function(respData) {

									if (respData.error == 0) {
										layui.use('layer', function() {
											var layer = layui.layer;
											layer.msg('导入成功');
										});

										/**
										 * 测试
										 */
										setTimeout(
												function() {
													// 关闭弹出的html页面
													var index = parent.layer
															.getFrameIndex(window.name); // 获取窗口索引
													parent.layer.close(index);
												}, 2000);// 2秒
									} else {
										// layer.tab({
										// area : [ '450px', '260px' ],
										// tab : [ {
										// title : '所有卡号',
										// content : respData.list
										// }, {
										// title : '导入成功',
										// content : respData.right
										// }, {
										// title : '导入失败',
										// content : respData.error,
										// } ]
										// });
										layer
												.prompt(
														{
															formType : 2,
															maxlength : 9999999999999999999999999999999999999999,
															value : '所有卡号：'
																	+ respData.list
																	+ '\n成功导入的卡号为：'
																	+ respData.right
																	+ '\n导入失败的卡号为：'
																	+ respData.error,
															title : '导入结果',
															area : [ '400px',
																	'350px' ]
														// 自定义文本域宽高
														},
														function(value, index,
																elem) {
															// alert(value); //
															// 得到value
															layer.close(index);

															/**
															 * 测试
															 */
															setTimeout(
																	function() {
																		// 关闭弹出的html页面
																		var index = parent.layer
																				.getFrameIndex(window.name); // 获取窗口索引
																		parent.layer
																				.close(index);
																	}, 0);// 2秒

														});

									}

									// window.parent.layer.closeAll();

								},
								error : function() {
									layui.use('layer', function() {
										var layer = layui.layer;
										layer.msg('导入失败，请联系超管');
									});
								}
							});

				});

$("#close").on("click", function() {

	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);

});