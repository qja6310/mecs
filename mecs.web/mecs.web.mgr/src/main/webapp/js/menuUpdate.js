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
	showUpdatePanel();
})
// 初始化信息回填
function showUpdatePanel() {
	$.ajax({
		url : "../updateMenu.action",
		type : "post",
		dataType : "json",
		success : function(respData) {
			$("#URL").val(respData.menu.menuUrl);
			if(respData.menu.menuPid==0){
				$("#URL").attr('disabled',true);
			}else{
				$("#URL").attr('disabled',false);
			}
			$("#fid").val(respData.menu.menuId);
			$("#menu").val(respData.menu.menuName);
			
			
			$("#icon").val(respData.menu.menuIcon);

		},
		error : function() {
			layer.msg("用户查询失败")
		}
	});

}

var btns = $('button');
// 循环遍历所有的按钮，一个一个添加事件绑定
for (var i = 0; i < btns.length; ++i) {
	btns[i].onclick = function() {
		var val = this.getAttribute('value');
		var id = this.getAttribute('id');
		if (id == "sure" || id == "close") {
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

$("#sure").on("click", function() {
	var layer = layui.layer;
	var menu = $("#menu").val();
	var URL = $("#URL").val();
	var icon = $("#icon").val();
	if (menu == "") {
		layer.msg("菜单名不能为空", {
			icon : 2
		});
		return;
	}
	if (URL == "") {
		layer.msg("URL不能为空", {
			icon : 2
		});
		return;
	}
	if (icon == "") {
		layer.msg("图标不能为空", {
			icon : 2
		});
		return;
	}
	$.ajax({
		url : "../toUpdateMenu.action",
		data : {
			menu : menu,
			URL : URL,
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
