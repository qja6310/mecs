//日期控件
layui.use([ 'form', 'layedit', 'laydate' ],function() {
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
// 定义全局变量
var beginTimeVal = "";
var endTimeVal = "";
var userNameVal = "";

startAjax(currPage);

// 点击查询按钮的监听方法
$("#search").on("click", function() {

	beginTimeVal = $("#date1").val();
	endTimeVal = $("#date3").val();
	userNameVal = $("#userName").val();
	if(beginTimeVal>endTimeVal){
		 layer.msg("开始时间不能大于结束时间");
			return;
	 }
	startAjax(currPage);
});

$("#clean").on("click", function() {
	$("#date1").val("") ;
	$("#date3").val("");
	$("#userName").val("");
});

// 发送ajax获取用户集合 及其他数据
function startAjax(cur) {

	$.ajax({
				type : "post",/* 数据的提交方式，GET POST */
				url : '../showMgrLog.action',/* 访问路径action */
				data : {
					/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */

					nowPage : cur,
					beginTime : beginTimeVal,
					endTime : endTimeVal,
					userName : userNameVal
				},
				dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
				success : function(respData) {

					var mgrtList = respData.mgrtList;
					count = respData.count
					limit = respData.limit
					
					layui.use('table',function() {
										var table = layui.table;
										table.render({
											elem : '#mgrLogTable',
											data : mgrtList,
											count:count,
											id : 'idTest',
											toolbar:'#toolbardemo',
											title : '用户数据表',
											page: true //是否显示分页
									        ,limits: [10,20,30]
									         ,limit:10 //每页默认显示的数量
											,cols : [ [ {
												type : 'checkbox',
												fixed : 'left'
											}, {
												field : 'logUserId',
												title : '操作人',
												fixed : 'left',
												width : 100
											}, {
												field : 'logType',
												title : '类型',
												fixed : 'left',
												width : 150
											}, {
												field : 'logRemark',
												title : '信息',
												fixed : 'left',
												width : 300
											}, {
												field : 'logCtime',
												title : '开始时间',
												fixed : 'left',
												width : 300
											}, {
												field : 'logCtime',
												title : '结束时间',
												fixed : 'left',
												width : 300
											}] ]
										});
										
										// 头工具栏事件
										table.on('toolbar(mgrLogTable)',function(obj) {
												
											
											var checkStatus = table.checkStatus(obj.config.id);
											switch (obj.event) {
											case 'getCheckData':
												var data = checkStatus.data;
												//写一个弹框提示
												
												if(data.length==0){
													layui.use('layer', function() {
														var layer = layui.layer;
														layer.msg('请选择要删除的数据！', {
															time : 2000, // 10s后自动关闭
															area : '230px',
											                icon : 2
														});
													});
													return;
												}else{
													
													 layui.use('layer', function(){
														 var layer = layui.layer;
												  layer.confirm('真的要删除吗?', {icon: 3, title:'提示'}, function(index){
													layer.close(index); 
													//进行删除操作
													var logArr = new Array();
													for (var i = 0; i < data.length; i++) {
														logArr.push(data[i].logId);
													}
													var logIdJson =JSON.stringify(logArr);
													
													$.ajax({
														type : "post",/* 数据的提交方式，GET POST */
														url : "../delMgrLog.action",/* 访问路径action */
														data : {
															/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
															logIdJson : logIdJson
														},
														dataType : "json",
														success : function(respData) {
															
															if(respData.res=="suc"){
																layui.use('layer', function() {
																	var layer = layui.layer;
																	layer.msg('删除成功！', {
																		time : 2000, // 10s后自动关闭
																		area : '230px',
														                icon : 1
																	});
																});
																startAjax(currPage);
															}else{
																layui.use('layer', function() {
																	var layer = layui.layer;
																	layer.msg('删除失败！', {
																		time : 2000, // 10s后自动关闭
																		area : '230px',
														                icon : 2
																	});
																});
																startAjax(currPage);
															}
															
														},
														error : function() {
															layui.use('layer', function() {
																var layer = layui.layer;
																layui.use('layer', function() {
																	var layer = layui.layer;
																	layer.msg('请求失败，请联系超管！', {
																		time : 2000, // 10s后自动关闭
																		area : '230px',
														                icon : 2
																	});
																});
															});
														}
													});
													
													 });
												
													 });

													
												}
												
												
												break;
											};
														
								});
					});

				},
				error : function() {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('请求失败！', {
							time : 2000, // 10s后自动关闭
							area : '230px',
			                icon : 1
						});
					});
				}
			});

}

