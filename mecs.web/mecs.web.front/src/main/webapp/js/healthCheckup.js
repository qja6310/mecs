$("#loginfirst").show();
layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});
$().ready(function() {
	var url = window.location.search; // 获取url中"?"符后的字串
	if (url.indexOf("?") != -1) { // 判断是否有参数
		var str = url.substr(1); // 从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
		strs = str.split("="); // 用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔
								// 再用等号进行分隔）
		if(strs[1] != null && strs[1] != ''){
			if(strs[1] == "suc"){
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('充值成功！', {
						time : 2000, // 2s后自动关闭
						area : '200px',
						icon : 1,
					});
				});
			}
		}
	}
	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../checkLogin.action",/* 访问路径action */
		data : {

		},
		dataType : "json",
		success : function(respData) {

			if (respData.sign == 'no') {// 表示还未登录 如果登录了就不走这一步
				$("#loginfirst").show();
			} else {// 有值，则表示已经登录了，直接发送用户的请求
				var name = respData.name;
				$("#loginName").text("欢迎您：" + name);
				$("#sign").show();// 欢迎的字样
				$("#logont").show();// 注销按钮
				$("#loginfirst").hide();// 登录按钮
			}
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败！', {
					time : 2000, // 2s后自动关闭
					area : '200px',
					icon : 2,
				});
			});
		}
	});
});
// 注销
function logout() {
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.confirm('确定注销吗？', {
			icon : 3,
			title : '提示'
		}, function(index) {
			layer.close(index);

			$.ajax({
				type : "post",/* 数据的提交方式，GET POST */
				url : "../logout.action",/* 访问路径action */
				data : {

				},
				dataType : "json",
				success : function(respData) {

					if (respData.sign == 'no') {// 注销成功
						layui.use('layer', function() {
							var layer = layui.layer;
							layer.msg('注销成功！ ', {
								time : 2000, // 10s后自动关闭
								area : '250px',
								icon : 1,
							});

						});
						// 登录按钮显示
						$("#loginfirst").show();
						setTimeout(function() {
							// 关闭弹出的html页面
							parent.window.location.href = 'healthCheckup.html';
						}, 1000);

					} else {// 注销失败
						layui.use('layer', function() {
							var layer = layui.layer;
							layer.msg('注销失败！ ', {
								time : 2000, // 10s后自动关闭
								area : '250px',
								icon : 2,
							});

						});
					}
				},
				error : function() {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('请求失败！', {
							time : 2000, // 2s后自动关闭
							area : '200px',
							icon : 2,
						});
					});
				}
			});

		});

	});

}

$("#billing").on("click", function() {// 开单请求
	// 发送ajax查看用户是否有登录
	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../checkLogin.action",/* 访问路径action */
		data : {

		},
		dataType : "json",
		success : function(respData) {
			if (respData.sign == 'no') {// 表示还未登录 如果登录了就不走这一步
				layui.use("layer", function() {
					var layer = layui.layer;
					layer.open({
						type : 2,
						title : '读卡',
						skin : 'layui-layer-rim',
						area : [ '500px', '180px' ],
						content : [ 'login.html', 'no' ]
					});
				})
			} else {// 有值，则表示已经登录了，直接跳转到对账表页面
				window.location.href = 'billingOrder.html';
			}
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败！', {
					time : 2000, // 2s后自动关闭
					area : '200px',
					icon : 2,
				});
			});
		}
	});
})

$("#report").on("click", function() {// 体检报告请求
	// 发送ajax查看用户是否有登录
	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../checkLogin.action",/* 访问路径action */
		data : {

		},
		dataType : "json",
		success : function(respData) {
			if (respData.sign == 'no') {// 表示还未登录 如果登录了就不走这一步
				layui.use("layer", function() {
					var layer = layui.layer;
					layer.open({
						type : 2,
						title : '读卡',
						skin : 'layui-layer-rim',
						area : [ '500px', '180px' ],
						content : [ 'login.html', 'no' ]
					});
				})
			} else {// 有值，则表示已经登录了，直接跳转到对账表页面

				window.location.href = 'mecReport.html';
			}
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败！', {
					time : 2000, // 2s后自动关闭
					area : '200px',
					icon : 2,
				});
			});
		}
	});
})

$("#recharge").on("click", function() {// 用户充值
	// 发送ajax查看用户是否有登录
	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../checkLogin.action",/* 访问路径action */
		data : {

		},
		dataType : "json",
		success : function(respData) {
			if (respData.sign == 'no') {// 表示还未登录 如果登录了就不走这一步
				layui.use("layer", function() {
					var layer = layui.layer;
					layer.open({
						type : 2,
						title : '读卡',
						skin : 'layui-layer-rim',
						area : [ '500px', '180px' ],
						content : [ 'login.html', 'no' ]
					});
				})
			} else {// 有值，则表示已经登录了，直接跳转到对账表页面
			// layer.open({
			// type : 2,
			// title : "自助充值",
			// skin : 'layui-layer-rim', // 加上边框
			// area : [ '500px', '400px' ], // 宽高
			// offset: '',//底部坐标
			// content : 'recharge.jsp'
			// });
				window.location.href = 'recharge.jsp';
			}
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败！', {
					time : 2000, // 2s后自动关闭
					area : '200px',
					icon : 2,
				});
			});
		}
	});
})

$("#account").on("click", function() {// 卡对账请求
	// 发送ajax查看用户是否有登录
	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../checkLogin.action",/* 访问路径action */
		data : {

		},
		dataType : "json",
		success : function(respData) {
			if (respData.sign == 'no') {// 表示还未登录 如果登录了就不走这一步
				layui.use("layer", function() {
					var layer = layui.layer;
					layer.open({
						type : 2,
						title : '读卡',
						skin : 'layui-layer-rim',
						area : [ '500px', '180px' ],
						content : [ 'login.html', 'no' ]
					});
				})
			} else {// 有值，则表示已经登录了，直接跳转到对账表页面

				window.location.href = 'account.html';
			}
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败！', {
					time : 2000, // 2s后自动关闭
					area : '200px',
					icon : 2,
				});
			});
		}
	});
})

function login11() {
	layui.use("layer", function() {
		var layer = layui.layer; // layer初始化
		layer.open({
			type : 2,
			title : '读卡',
			area : [ '500px', '180px' ],
			content : [ 'login.html', 'no' ],
		});
	})
}

