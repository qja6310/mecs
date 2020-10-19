	
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

var dictId ="";
function getRequest(){
	var url = location.search;//获取URL中“?”符号后的字符串
	if(url.indexOf("?") !=-1){//判断是否有参数
		var str = url.substr(1);//从 第一个字符开始  因为第0个是？号    获取出问号的所有字符串
		strs = str.split("=");//用等号进行分割  （应为知道只有一个参数  所以直接用等号进行分割，如果有多个参数要用&号进行分割，再用等号进行分割）
		dictId=strs[1];
		initUpdatePanel(dictId);
	}
}

// 初始化界面 消息回填
function initUpdatePanel(dictId) {
	$.ajax({
		url : "../showDictMessage.action",
		data : {
			dictId : dictId
		},
		type : "post",
		dataType : "json",
		success : function(respData) {
			
			//回填参数信息
			$("#dictname").val(respData.dictName);
			$("#dictDescribe").val(respData.dictDescribe);

		},
		error : function() {
			alert("操作失败")
		}
	});

}

// 取消
$("#close").on("click", function() {
	var index = parent.layer.getFrameIndex(window.name)
	parent.layer.close(index)
});

// 修改人员确定
var dictName = "";
var dictDescribe = "";

// 修改参数
$("#sure").on("click", function() {
	var layer = layui.layer;
	dictName = $("#dictname").val();
	dictDescribe = $("#dictDescribe").val();
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
		url : "../editDict.action",
		data : {
			dictId:dictId,
			dictName : dictName,
			dictDescribe : dictDescribe,

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
				layer.msg('操作失败，联系超管！', {
					time : 2000, // 10s后自动关闭
					area : '230px',
	                icon : 2
				});
			});
		}
	});

});
