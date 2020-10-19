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

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});

var depName = "";
var depDescribe = "";
// 增加科室确定
$("#sure").on("click", function() {
	var layer = layui.layer;
	depName = $("#depName").val();
	depDescribe = $("#depDescribe").val();
	if (depName == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('请输入科室名！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
				icon : 2
			});
		});

		return;
	}
	if (depDescribe == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('请输入科室描述！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
				icon : 2
			});
		});

		return;
	}

	$.ajax({
		url : "../addDep.action",
		data : {
			depName : depName,
			depDescribe : depDescribe,

		},
		type : "post",
		dataType : "json",
		success : function(respData) {
			layui.use('layer', function() {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg(respData.res, {
						time : 2000, // 10s后自动关闭
						area : '230px',
						icon : 1
					});
				});
				setTimeout(function() {
					// 关闭弹出的html页面
					var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
					parent.layer.close(index);
				}, 2000);// 2秒
			});

		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('操作失败！！', {
					time : 2000, // 10s后自动关闭
					area : '230px',
					icon : 2
				});
			});

		}
	});

});
