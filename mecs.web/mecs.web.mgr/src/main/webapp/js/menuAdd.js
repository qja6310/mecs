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


var btns = $('button');
// 循环遍历所有的按钮，一个一个添加事件绑定
for (var i = 0; i < btns.length; ++i) {
	
	btns[i].onclick = function() {
		var val = this.getAttribute('value');
		var id = this.getAttribute('id');
		if(id=="sure"||id=="close"){
			return;
		}
		$("#icon").val(val);
	}
}

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});

// 增加一级菜单确定
$("#sure").on("click", function() {
	
	var layer = layui.layer;
	
	var menu = $("#menu").val();
	
	var icon = $("#icon").val();
	if (menu == "") {
		layer.msg('菜单名不能为空', {
			icon : 2
		});
		return;
	}
	
	if (icon == "") {
		layer.msg('图标不能为空', {
			icon : 2
		});
		return;
	}
	
	// $.ajaxSettings.traditional = true;// 能传集合
	$.ajax({
		url : "../addParentMenu.action",
		data : {
			menu : menu,
			icon : icon

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
			layer.msg("查询失败");
		}
	});

});