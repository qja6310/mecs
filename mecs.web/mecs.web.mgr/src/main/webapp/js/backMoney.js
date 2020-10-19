var currPage = 1;
var count = 0;
var limit = 0;
var itemsState = "";
var cardNum;
//发送ajax 获取下拉框的数据
$.ajax({
	url : "../getCheckInfo.action",
	type : "post",

	data : {
		type:'ITEMSSTATE'
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
	firstAjax(currPage, 'SELECT', cardNum,0,0);
	
}
function  cleardata(){
	$("#cardNum").val("");
	$("#itemsState").val("");
}
function select() {

	itemsState = $("#itemsState").val();
	currPage = 1;
	firstAjax(currPage, 'SELECT', cardNum,0,0);
}
function firstAjax(curr, sta, cardNum,itemslId,money) {// 发送ajax, 获取数据库所有文件类型
	$.ajax({
		url : "../backMoney.action",
		type : "post",
		data : {
			type : sta,
			currPage : curr,
			itemsState : itemsState,
			cardNum : cardNum,
			cardItemsId:itemslId,
			money:money
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
                        icon : 2,
					});
//					$("#state").hide();
//					$("#name").hide();
//					$("#balance").hide();
//					$("#pageBtn").hide();
//					$("#unit").hide();
//					$("#myTable").hide();
					count = 0;
					$("#itemsState").val("");
					$("#username").val("");
					$("#balanceinput").val("");
					itemsState = "";
				});
//				return;
			}else{
//			$("#state").show();
//			$("#name").show();
//			$("#balance").show();
//			$("#pageBtn").show();
//			$("#myTable").show();
//			$("#unit").show();
			$("#username").val(userName);
			$("#balanceinput").val(respData.balance);
			var list = respData.list;
			count = respData.count;
			limit = respData.limit;
			}
			layui.use('laypage', function() {
				var laypage = layui.laypage;
				laypage.render({
					elem : 'pageBtn',
					count : count,
					limit : limit,// 数据总数,
					curr : currPage,
					jump : function(obj) {
						if(obj.curr != currPage){
							sta = 'SELECT';
						}
						currPage = obj.curr;
						secondAjax(obj.curr, sta, cardNum,itemslId,money)
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
function secondAjax(curr, sta, cardNum,itemslId,money) {// 发送ajax, 获取数据库所有文件类型
	$.ajax({
		url : "../backMoney.action",
		type : "post",

		data : {
			type : sta,
			currPage : curr,
			itemsState : itemsState,
			cardNum : cardNum,
			cardItemsId:itemslId,
			money:money
		},
		dataType : "json",
		success : function(respData) {
		   if(respData.ret == '退费成功' || respData.ret == '退费失败' ){
			   $("#balanceinput").val(respData.balance);
			   layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg(respData.ret, {
						time : 2000, // 10s后自动关闭
						area : '200px',
                        icon : 1,
					});
				});
			}
			var list = respData.list;
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
function share(respData) {
	var myTable = $("#myTable");
	myTable.css({
		'text-align' : 'center'
	});
	myTable.html("<thead  ><tr  class='layui-bg-cyan' ><th  style='text-align:center' width:'20%' >体检项目</th><th style='text-align:center'width:'20%'>价格（元）</th><th style='text-align:center'width:'20%'>项目状态</th><th style='text-align:center'width:'20%'>开单时间</th><th style='text-align:center'width:'20%'>操作</th></tr></thead>");
	var end = respData.length;
	if (end == 0) {
		var tbody = $("<tbody></tbody>");
		var tr = $("<tr></tr>");
		var td = $("<td></td>");
		td.text("查无信息！");
		td.attr("colSpan", 5);
		tr.append(td);
		tbody.append(tr);
		myTable.append(tbody);
	}
	for (var i = 0; i < end; i++) {
		// 创建一个tr节点
		var tbody = $("<tbody></tbody>");
		var tr = $("<tr ></tr>");
		var td1 = $("<td></td>");
		var td2 = $("<td></td>");
		var td3 = $("<td></td>");
		var td4 = $("<td></td>");
		var td5 = $("<td></td>");
		// 向td节点中添加文本
		td1.text(respData[i].itemsName);
		td2.text(respData[i].itemsPrice);
		td3.text(respData[i].dict.dictName);
		td4.text(respData[i].mecr.bilTime);
		var button;
		if (respData[i].irState == 2) {
			button = $("<button style='margin:-7px' class=\"layui-btn layui-btn-sm layui-btn-danger layui-inline \" ><i class=\"layui-icon layui-icon-return layui-inline \" ></i> 退费 </button>");
			button.attr("cardItemsId", respData[i].irId);
			button.attr("money", respData[i].itemsPrice);
			button.on("click", function() {
				  var id = $(this).attr("cardItemsId");
					var money = $(this).attr("money");
					 layui.use('layer', function(){
						 var layer = layui.layer;
				  layer.confirm('本次将退回'+money+'元 确定退费吗?', {icon: 3, title:'提示'}, function(index){
					layer.close(index); 
					secondAjax(currPage, 'BACKMONEY', cardNum,id,money);
					 });
				
					 });
					
			});
			td5.append(button);
		} else if (respData[i].irState == 1) {
			button = $("<button title='未结算项目不能退费' style='margin:-7px' class=\"layui-btn layui-btn-disabled layui-inline\" ><i class=\"layui-icon layui-icon-return layui-inline\" ></i> 退费 </button>");
			td5.append(button);
		} else if (respData[i].irState == 3) {
			button = $("<button title='已体检项目不能退费' style='margin:-7px' class=\"layui-btn layui-btn-disabled layui-inline\" ><i class=\"layui-icon layui-icon-return layui-inline\" ></i> 退费 </button>");
			td5.append(button);
		} else if (respData[i].irState == 4) {
			button = $("<button title='已取消项目不能退费' style='margin:-7px'class=\"layui-btn layui-btn-disabled layui-inline\" ><i class=\"layui-icon layui-icon-return layui-inline\" ></i>退费  </button>");
			td5.append(button);
		} else if (respData[i].irState == 5) {
			button = $("<button title='已接收项目不能退费' style='margin:-7px'class=\"layui-btn layui-btn-disabled layui-inline\" ><i class=\"layui-icon layui-icon-return layui-inline\" ></i>退费  </button>");
			td5.append(button);
		}

		tr.append(td1);
		tr.append(td2);
		tr.append(td3);
		tr.append(td4);
		tr.append(td5);

		tbody.append(tr);
		myTable.append(tbody);

	}
}