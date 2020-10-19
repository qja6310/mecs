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

					laydate.render({
						elem : '#date2'
					});
					laydate.render({
						elem : '#date3'
					});

				});
var currPage = 1;
var count = 0;
var limit = 0;
var stype = "";
var date1 = "";
var date2 = "";
var cardNum="";
var user = "";

//$().ready(function(){
//	alert('dd');
//})
//获取下拉框的动态的数据
$.ajax({
	url : "../getCheckInfo.action",
	type : "post",

	data : {
		type:'ACCOUNTTYPE'
	},
	dataType : "json",
	success : function(respData) {
	
		var list = respData.list;
		var select = $("#itemsState");
		//创建下拉框选项
		for (var i = 0; i < list.length; i++) {
			var option = $("<option ></option>");
		    option.attr('value',list[i].dictCode);
		    option.text(list[i].dictName);
		    select.append(option);
		}
	},
	error : function() {
	}
})
function cardReader() {
	cardNum = $("#cardNum").val();
	if (cardNum == "") {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('卡号不能为空！', {
				time : 2000, // 10s后自动关闭
				area : '200px',

			});
		});
		return;
	}
	firstAjax(1);
}
function  cleardata(){//清空搜索框
	$("#date1").val("");
	$("#date2").val("");
	$("#itemsState").val("");
}
function select() {//搜索
	date1 = $("#date1").val();
	date2 = $("#date2").val();
	currPage = 1;
	stype = $("#itemsState").val();
	if(date1>date2){
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('开始时间不能大于结束时间！', {
				time : 2000, // 10s后自动关闭
				area : '250px',
				icon : 2,

			});
		});
		return;
	}
	firstAjax(1);
}

function firstAjax(currPage1) {// 发送ajax, 获取数据库所有文件类型
	$.ajax({
		url : "../readCard.action",
		type : "post",
		data : {
			currPage : currPage1,
			beginTime : date1,
			cardNum:cardNum,
			overTime : date2,
			type : stype
		},
		dataType : "json",
		success : function(respData) {
			var userName = respData.userName;
			if (userName == null) {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('输入卡号无效！', {
						time : 2000, // 10s后自动关闭
						area : '200px',
						icon : 2

					});
//					$("#actionBar1").hide();
//					$("#actionBar2").hide();
//					$("#actionBar3").hide();
//					$("#actionBar4").hide();
//					$("#actionBar5").hide();
//					$("#myTable").hide();
//					$("#pageBtn").hide();
					cleardata();
					$("#balanceinput").val("");
					$("#user").val("");
					count  = 0;
				});
//				return;
			}else{
//			$("#actionBar1").show();
//			$("#actionBar2").show();
//			$("#actionBar3").show();
//			$("#actionBar4").show();
//			$("#actionBar5").show();
//			$("#pageBtn").show();
//			$("#myTable").show();
			
			var list = respData.list;
			count = respData.count;
			limit = respData.limit;
			$("#user").val(respData.userName);
		}
//			console.log(respData);
			layui.use('laypage', function() {
				var laypage = layui.laypage;
				laypage.render({
					elem : 'pageBtn',// 注意，这里的 test1 是 ID，不用加 # 号
					count : count,// 数据总数，从服务端得到
					limit : limit,
					curr : currPage,

					jump : function(obj) {
						secondAjax(obj.curr);
					}
				});
			});
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

function secondAjax(cur) {// 发送ajax, 获取数据库所有文件类型
	$.ajax({
		url : "../readCard.action",
		type : "post",
		data : {
			currPage : cur,
			beginTime : date1,
			cardNum:cardNum,
			overTime : date2,
			type : stype
		},
		dataType : "json",
		success : function(respData) {
			
			$("#balanceinput").val(respData.balance);
			var list = respData.list;
			count = respData.count;
			limit = respData.limit;
			share(list);

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
function share(list) {
	var myTable = $("#myTable");
	myTable.css({
		'text-align' : 'center'
	});
	myTable.html("<thead  ><tr  class='layui-bg-cyan' ><th width:\'25%\' style='text-align:center'  >发生时间</th><th width:\'35%\' style= 'text-align:center'>具体描述     </th><th width:\'20%\' style='text-align:center'>发生金额（元）</th><th style='text-align:center'>充值/扣费</th></tr></thead>");
	if (list.length == 0) {
		var tbody = $("<tbody></tbody>");
		var tr = $("<tr></tr>");
		var td = $("<td></td>");
		td.text("查无信息！");
		td.attr("colSpan", 4);
		tr.append(td);
		tbody.append(tr);
		myTable.append(tbody);
	}
	if(list.length>0){
		for (var i = 0; i < list.length; i++) {
			// 创建一个tr节点
			var tbody = $("<tbody></tbody>");
			var tr = $("<tr ></tr>");
			var td1 = $("<td></td>");
			var td2 = $("<td></td>");
			var td3 = $("<td></td>");
			var td4 = $("<td></td>");
			// 向td节点中添加文本
			td1.text(list[i].accCtime);
			td2.text(list[i].accDescribe);
			if(list[i].accMemo == 1){//扣费
			td3.text("-"+list[i].accMoney);
			}else{
			td3.text(list[i].accMoney);
			}
			td4.text(list[i].accType);
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);

			tbody.append(tr);
			myTable.append(tbody);

		}
	}
	
}