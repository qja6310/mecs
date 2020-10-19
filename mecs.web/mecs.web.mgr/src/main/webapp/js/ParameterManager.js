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


// 定义每页的记录数
var limit = 0;
var count = "";
var currPage = 1;

var dictname = "";
var beginTime = "";
var endTime = "";

startAjax(1);
//清空
$("#clean").on("click", function() {
	$("#date1").val("") ;
	$("#date2").val("");
	$("#dictname").val(""); 
});
//搜索
$("#search").on("click", function() {

	dictname = $("#dictname").val();
	 beginTime = $("#date1").val();
	 endTime = $("#date2").val();
	
	if(beginTime>endTime){
		layer.msg("开始时间不能大于结束时间");
		return;
	}
	 startAjax(1);
});

function startAjax(cur) {
	var dictname = $("#dictname").val();
	var beginTime = $("#date1").val();
	var endTime = $("#date2").val();
	
	$.ajax({
		url : "../showDict.action",
		type : "post",
		data : {
			nowPage : currPage,
			name : dictname,
			beginTime : beginTime,
			endTime : endTime

		},
		dataType : "json",
		success : function(respData) {
			var count = respData.count;
			var limit = respData.limit;

			layui.use('laypage', function() {
				var laypage = layui.laypage;

				// 执行一个laypage实例
				laypage.render({
					elem : 'pageBtn',// 注意，这里的 test1 是 ID，不用加 # 号
					count : count,// 数据总数，从服务端得到
					limit : limit,
					curr : currPage,

					jump : function(obj) {
						nextAjax(obj.curr);
					}
				});
			});
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败，请联系超管！', {
					time : 2000, // 10s后自动关闭
					area : '230px',
	                icon : 2
				});
			});
		}
	});
}

function nextAjax(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../showDict.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			name : dictname,
			beginTime : beginTime,
			endTime : endTime
		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {
			var dictList = respData.dictList;
			count = respData.count
			limit = respData.limit
			showTable(dictList);
		},
		error : function() {
			alert("请求失败");
		}
	});

}
// 显示表格
function showTable(list) {
	var mytable = $("#table");
	mytable
			.html("<thead><tr class='layui-bg-cyan'><th style=\"width: 10%\";>参数对应码</th><th style=\"width: 10%\";>参数名称</th><th style=\"width: 15%\";>参数对应类型</th><th style=\"width: 20%\";>参数描述</th><th style=\"width: 15%\";>创建时间</th><th style=\"width: 15%\";>修改时间</th><th >操作事项</th></tr></thead>");
	// 获取返回的集合
	// 判断查询是否有数据
	if (list.length > 0) {
		for (var i = 0; i < list.length; i++) {
			var tr = $("<tr></tr>");
			var td1 = $("<td></td>");
			var td2 = $("<td></td>");
			var td3 = $("<td></td>");
			var td4 = $("<td></td>");
			var td5 = $("<td></td>");
			var td6 = $("<td></td>");
			var td7 = $("<td></td>");
			// 给td节点中添加文本
			td1.text(list[i].dictCode);
			td2.text(list[i].dictName);
			td3.text(list[i].dictType);
			td4.text(list[i].dictMemo);
			td5.text(list[i].dictCtime);
			td6.text(list[i].dictUtime);
			// 修改编辑按钮
			var update = $("<button class=\"layui-btn layui-bg-orange layui-btn-sm\"><i class=\"layui-icon layui-icon-set-sm layuiadmin-button-btn\"></i>编辑 </button>");
			update.attr("id", list[i].dictId);
			update.on("click", function() {//根据科室ID修改科室信息
				showUpPanel($(this).attr("id"));
			});

			var del = $("<button class=\"layui-btn layui-btn-danger layui-btn-sm\"><i class=\"layui-icon layui-icon-delete layuiadmin-button-btn\"></i>删除</button>");
			del.attr("id", list[i].dictId);
			del.on("click", function() {//根据科室ID 删除科室
				delDict($(this).attr("id"));
			});
			
			td7.attr("align", "center");
			td7.append(update);
			td7.append(del);
			// 把节点串起来
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);
			tr.append(td5);
			tr.append(td6);
			tr.append(td7);
			mytable.append(tr);

		}
	} else {// 没有数据
		var tr = $("<tr></tr>");
		var td = $("<td></td>");
		td.attr("colSpan", 7);
		td.text("查询不到数据");
		td.attr("align", "center");
		tr.append(td);
		mytable.append(tr);
	}

}

// 新增界面
$("#add").on("click", function() {
	layer.open({
		type : 2,
		title : '新增参数',
		skin : 'layui-layer-rim', // 加上边框
		area : [ '570px', '540px' ], // 宽高
		content : 'ParameterAdd.html',
		end : function() {
	    startAjax(1);
		},

	});
});
// 根据ID删除参数信息
function delDict(id) {
	layer.confirm('确定删除参数吗？', {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function(index) {
		$.ajax({
			type : "post",/* 数据的提交方式，GET POST */
			url : "../delDict.action",/* 访问路径action */
			data : {
				/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
				dictId : id
			},
			dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
			success : function(respData) {
				
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg(respData.res);
				});
				startAjax(1);
			},
			error : function() {
				alert("请求失败");
			}
		});

	});

}

/**
 * 跳转编辑界面jsp
 * @param id
 * @returns
 */

function showUpPanel(id ) {
	
	layer.open({
		type : 2,
		title : '参数修改',
		skin : 'layui-layer-rim', // 加上边框
		area : [ '570px', '440px' ], // 宽高
		content : 'ParameterModification.jsp?dictId='+id,
		end : function() {
			startAjax(1);
		},

	});

}
