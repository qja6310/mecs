var currPage = 1;
var count = 0;
var limit = 0;
var beginTime;
var overTime;
var type;
var cardNum;

function cardReader() {
	cardNum = $("#cardNum").val();
	if (cardNum == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('卡号不能为空！', {
				time : 2000, // 10s后自动关闭
				area : '200px',
				icon : 2,

			});
		});
		return;
	}
	firstAjax(currPage, type, cardNum, beginTime, overTime);

}
function firstAjax(curr, type, cardNum, beginTime, overTime) {// 发送ajax,
																// 获取数据库所有文件类型
	$.ajax({
		url : "../frontlogin.action",
		type : "post",
		data : {
			cardNum : cardNum
		},
		dataType : "json",
		success : function(respData) {
			if (respData.sign == 'err') {// 卡号不存在或者无效
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('卡号不存在或者无效！', {
						time : 2000, // 10s后自动关闭
						area : '300px',
						icon : 2,
					});
				});
				return;
			} else {// 卡号是有效的
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('读卡成功！欢迎您： ' + respData.user.userName, {
						time : 2000, // 10s后自动关闭
						area : '320px',
						icon : 1,
					});

				});

				setTimeout(function() {
					// 关闭弹出的html页面
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
					parent.window.location.href = 'healthCheckup.html';
				}, 2000);

				// alert($("#islogin").val());

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
	})
}