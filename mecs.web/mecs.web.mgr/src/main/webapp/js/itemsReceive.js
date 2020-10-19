var currPage = 1;
var count = 0;
var limit = 0;
var itemsName = "";
var itemsState= "";
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
	 firstAjax(currPage,cardNum,itemsName,itemsState);
	
}
function  cleardata(){
	$("#cardNum").val("");
	$("#itemsState").val("");
	$("#itemsNames").val("");
	itemsName = "";
    itemsState= "";
}
function select() {

	itemsState = $("#itemsState").val();
	itemsName = $("#itemsNames").val();
	currPage = 1;
	firstAjax(currPage,cardNum,itemsName,itemsState);
}
function firstAjax(currPage1, cardNum,itemsName,itemsState) {// 发送ajax, 获取数据库所有文件类型
	$.ajax({
		url : "../itemsReceice.action",
		type : "post",
		data : {
			currPage : currPage1,
			itemsState : itemsState,
			cardNum : cardNum,
			itemsName:itemsName
		},
		dataType : "json",
		success : function(respData) {
			var userName = respData.name;
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
//					$("#state").val("");
//					$("#name").val("");
					count = 0;
					cleardata();
					$("#username").val("");
					$("#balanceinput").val("");
//					$("#pageBtn").hide();
//					$("#unit").hide();
//					$("#myTable").hide();
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
					curr : currPage1,
					jump : function(obj) {
						
						currPage = obj.curr;
						secondAjax(obj.curr, cardNum,itemsName,itemsState)
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
function secondAjax(currPage, cardNum,itemsName,itemsState) {// 发送ajax, 获取数据库所有文件类型
	$.ajax({
		url : "../itemsReceice.action",
		type : "post",

		data : {
			currPage : currPage,
			itemsState : itemsState,
			cardNum : cardNum,
			itemsName:itemsName
		},
		dataType : "json",
		success : function(respData) {
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
	myTable.html("<thead  ><tr  class='layui-bg-cyan' ><th  style='text-align:center' width:'20%' >体检项目</th><th style='text-align:center'width:'20%'>价格（元）</th><th style='text-align:center'width:'20%'>项目状态</th><th style='text-align:center'width:'20%'>科室</th><th style='text-align:center'width:'20%'>操作</th></tr></thead>");
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
		td4.text(respData[i].dep.depName);
		var button;
		if (respData[i].dict.dictCode == 2) {
			button = $("<button style='margin:-7px' class=\"layui-btn layui-btn-sm layui-inline \" ><i class=\"layui-icon layui-icon-right layui-inline \" ></i> 接收项目 </button>");
			button.attr("cardItemsId", respData[i].irId);
			button.attr("itemsName", respData[i].itemsName);
			button.on("click", function() {//通过项目记录id去接收项目，
				  var id = $(this).attr("cardItemsId");
				  var itemsName1=  $(this).attr("itemsName");
					 layui.use('layer', function(){
						 var layer = layui.layer;
				  layer.confirm('确定接收该项目吗?', {icon: 3, title:'提示'}, function(index){
					layer.close(index); 
					//调用接收项目的方法
					itemsReceive(id,itemsName1);
					 });
				
					 });
					
			});
			td5.append(button);
		} else if (respData[i].dict.dictCode == 5) {//已接收，录入小结
			button = $("<button style='margin:-7px' class=\"layui-btn layui-btn-sm layui-btn-danger layui-inline \" ><i class=\"layui-icon layui-icon-add-circle-fine layui-inline \" ></i> 录入小结 </button>");
			button.attr("cardItemsId", respData[i].irId);
			button.attr("itemsName", respData[i].itemsName);
			button.on("click", function() {
				  var irId = $(this).attr("cardItemsId");
					var itemsName1 = $(this).attr("itemsName");
					layui.use("layer",function(){
						var layer = layui.layer; 
					 layer.open({
							type : 2,
							title : '录入小结',
							skin : 'layui-layer-rim',
							area : ['900px', '500px'],
							content : ['smallResult.html','no'],
							end : function () {
								firstAjax(currPage,cardNum,itemsName,itemsState);
							}
						});
					})
//					//发送ajax过去controller保存 irId以及卡号，项目名
					sendParam(irId,itemsName1,cardNum);
			});
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
//接收项目的ajax
function itemsReceive(irId,itemsNamee){
	$.ajax({
		url : "../changState.action",
		type : "post",
		data : {
			itemsRecordId:irId,
			cardNum : cardNum,
			itemsName : itemsNamee
		},
		dataType : "json",
		success : function(respData) {
			if (respData.type == 'suc') {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('接收成功', {
						time : 2000, // 10s后自动关闭
						area : '200px',
                        icon : 1,
					});
				});
				firstAjax(currPage,cardNum,itemsName,itemsState)
			}else{
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('接收失败！', {
						time : 2000, // 10s后自动关闭
						area : '200px',
						icon : 2,
					});
				});
			}
		},
		error : function(respData){
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
function sendParam(irId,itemsName,cardNum){
	$.ajax({
		url : "../sendParam.action",
		type : "post",
		data : {
			itemsRecordId:irId,
			cardNum : cardNum,
			itemsName : itemsName
		},
		dataType : "json",
		success : function(respData) {
		
		},
		error : function(respData){
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