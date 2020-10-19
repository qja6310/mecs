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

var dictCode = "";
var dictName = "";
var dictType = "";
var dictDescribe = "";
// 增加科室确定
$("#sure").on("click", function() {
	var layer = layui.layer;
	dictCode = $("#dictCode").val();
	dictName = $("#dictName").val();
	dictType = $("#dictType").val();
	dictDescribe = $("#dictDescribe").val();
	if (dictCode == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('参数对应码不能为空！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
                icon : 2
			});
		});
		return;
	}
	if (dictName == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('参数名不能为空！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
                icon : 2
			});
		});
		return;
	}
	if (dictType == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('参数类型不能为空！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
                icon : 2
			});
		});
		return;
	}
	if (dictDescribe == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('参数描述不能为空！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
                icon : 2
			});
		});
		return;
	}

	$.ajax({
		url : "../addDict.action",
		data : {

			 dictCode : dictCode,
			 dictName : dictName,
			 dictType : dictType,
			 dictDescribe : dictDescribe
		},
		type : "post",
		dataType : "json",
		success : function(respData) {
			layui.use('layer', function() {
				layer.msg(respData.res);
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
				layer.msg('操作失败！', {
					time : 2000, // 10s后自动关闭
					area : '230px',
	                icon : 2
				});
			});
		}
	});

});





