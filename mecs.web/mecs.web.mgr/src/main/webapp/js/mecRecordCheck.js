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
						elem : '#sTime'
					});

					laydate.render({
						elem : '#eTime'
					});
				});
layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});
// 定义每页的记录数
var limit = 0;
var count = "";//总记录数
var currPage = 1;//当前页数

// 定义全局变量
var sTimeVal = "";//开始时间
var eTimeVal = "";//结束时间
var userNameVal = "";//用户名
var userTelVal = "";//电话
var mecCodeVal = "";//体检码

// 初始化页面
$().ready(function() {
	getMecRecord(1);
}); 


// 点击查询按钮的监听方法
$("#search").on("click", function() {
	sTimeVal = $("#sTime").val();
	eTimeVal = $("#eTime").val();
	userNameVal = $("#userName").val();
	userTelVal = $("#tel").val();
	mecCodeVal = $("#mecCode").val();
	
	var d1 = new Date(sTimeVal.replace(/\-/g, "\/"));
	var d2 = new Date(eTimeVal.replace(/\-/g, "\/"));
	if (sTimeVal != "" && eTimeVal != "" && d1 > d2) {
		layer.msg('开始时间不能大于结束时间', {icon: 2});
		return false;
	}
	getMecRecord(1);
});

$("#clean").on("click", function() {
	$("#sTime").val("");
	$("#eTime").val("");
	$("#userName").val("");
	$("#tel").val("");
	$("#mecCode").val("");
});

function getMecRecord(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../getMecSynthesize.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			sTime : sTimeVal,
			eTime : eTimeVal,
			userName : userNameVal,
			userTel : userTelVal,
			mecCode : mecCodeVal
		},
		dataType : "json",
		success : function(respData) {
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
						nextGetMecRecord(obj.curr);
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

function nextGetMecRecord(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../getMecSynthesize.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			sTime : sTimeVal,
			eTime : eTimeVal,
			userName : userNameVal,
			userTel : userTelVal,
			mecCode : mecCodeVal
		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {
			var mecSynthesizeList = respData.mecSynthesizeList;
			count = respData.count;
			limit = respData.limit;
			showMecRecord(mecSynthesizeList);
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

function showMecRecord(list) {
	var mecRecord = $("#mecRecord");
	mecRecord.html("");
	if (list.length > 0) {

		for (var i = 0; i < list.length; i++) {
			var tr = $("<tr></tr>");
			var userName = $("<td></td>");
			var userPhone = $("<td></td>");
			var mecCode = $("<td></td>");
			var itemsName = $("<td></td>");
			var mrDoc = $("<td></td>");
			var irPeTime = $("<td></td>");
			var check = $("<td></td>");

			userName.text(list[i].userName);
			userPhone.text(list[i].userPhone);
			mecCode.text(list[i].mecCode);
			itemsName.text(list[i].itemsName);
			mrDoc.text(list[i].mrDoc);
			irPeTime.text(list[i].irPeTime);
			var checkBtn = $("<button class=\"layui-btn layuiadmin-btn-admin layui-btn-sm\">预览详情</button>");
			checkBtn.attr("mrNum",list[i].mecCode);
			checkBtn.on("click",function(){
				var mrNum = $(this).attr("mrNum");
				jumpMecReportCheck(mrNum);
			});
			check.append(checkBtn);

			tr.append(userName);
			tr.append(userPhone);
			tr.append(mecCode);
			tr.append(itemsName);
			tr.append(mrDoc);
			tr.append(irPeTime);
			tr.append(check);

			mecRecord.append(tr);

		}

	} else {
		var $tr = $("<tr></tr>");
		var $td = $("<td></td>");
		$td.attr("colSpan", 7);
		$td.text("查无数据");
		$td.attr("align", "center");
		$tr.append($td);
		mecRecord.append($tr);
	}
}


function jumpMecReportCheck(mrNum){
	layer.open({
		type : 2,
		title : "预览详情",
		skin : 'layui-layer-rim', // 加上边框
		area : [ '800px', '580px' ], // 宽高
		offset: 'b',//底部坐标
		content : 'MecReportCheck.jsp?mrNum='+mrNum
	});
}
