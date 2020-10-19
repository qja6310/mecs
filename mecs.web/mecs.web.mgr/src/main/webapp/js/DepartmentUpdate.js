	
$().ready(function() {
	getRequest();
});
// 日期控件
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

var depId ="";
function getRequest(){
	var url = location.search;//获取URL中“?”符号后的字符串
	if(url.indexOf("?") !=-1){//判断是否有参数
		var str = url.substr(1);//从 第一个字符开始  因为第0个是？号    获取出问号的所有字符串
		strs = str.split("=");//用等号进行分割  （应为知道只有一个参数  所以直接用等号进行分割，如果有多个参数要用&号进行分割，再用等号进行分割）
		depId=strs[1];
		initUpdatePanel(depId);
	}
}

// 初始化界面 消息回填
function initUpdatePanel(depId) {
	$.ajax({
		url : "../showDepartment.action",
		data : {
			depId : depId
		},
		type : "post",
		dataType : "json",
		success : function(respData) {
			//回填账号姓名信息 
			$("#depName").val(respData.newdepName);
			$("#depDescribe").val(respData.newdepDescribe);

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

}

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});

// 修改人员确定
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
			layer.msg('请先输入科室名！', {
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
			layer.msg('请先输入科室描述！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
                icon : 2
			});
		});

		return;
	}

	$.ajax({
		url : "../editDep.action",
		data : {
			depId:depId,
			depName : depName,
			depDescribe : depDescribe,

		},
		type : "post",
		dataType : "json",
		success : function(respData) {
//			console.log(respData)
			layui.use('layer', function() {
				if(respData.res=='suc'){
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('修改成功！', {
							time : 2000, // 10s后自动关闭
							area : '230px',
			                icon : 1
						});
					});
				}else if(respData.res=='rep'){
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('科室名重复！', {
							time : 2000, // 10s后自动关闭
							area : '230px',
			                icon : 2
						});
					});
				}else{
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('操作失败，请联系超管！', {
							time : 2000, // 10s后自动关闭
							area : '230px',
			                icon : 2
						});
					});
				}
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
