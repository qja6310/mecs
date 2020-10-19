var index1;
var index2;
var layedit ;
//创建编辑器
createedit();
//mecNum -- 体检号码
//cardBalance- 卡余额 userName--用户名 userSex -- 用户性别 userAge--用户年龄 userBloodType--血型
//userBirthday -- 生日 userPhone--电话  userAddress--住址 userNativeplace--籍贯
//表格的 身体 tbody  summary1 总结建议  summary2 指导
//submit() 提交总结
//清空体检号码框

var mecNumLast = "";// 有效的体检号码保存

function submit() {
	if (mecNumLast == '') {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('请先输入体检号码！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
				icon : 2,
			});
		});
		return;
	}
	 var ret1 =layedit.getContent(index1);
	 var ret2 =layedit.getContent(index2);
	 var len1 = ret1.replace(/\s+/g,"").length;
	 var len2 = ret2.replace(/\s+/g,"").length;
	if (len1 < 10) {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('体检总结及建议不得少于10个字！', {
				time : 2000, // 10s后自动关闭
				area : '300px',
				icon : 2,
			});
		});
		return;
	}
	if (len1 > 1000) {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('体检总结及建议最多1000个字！', {
				time : 2000, // 10s后自动关闭
				area : '300px',
				icon : 2,
			});
		});
		return;
	}
	if (len2 < 10) {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('生活保健指导不得少于10个字！', {
				time : 2000, // 10s后自动关闭
				area : '300px',
				icon : 2,
			});
		});
		return;
	}

	if (len2 > 1000) {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('生活保健指导对多1000个字！', {
				time : 2000, // 10s后自动关闭
				area : '300px',
				icon : 2,
			});
		});
		return;
	}
	// 提交体检总结和建议 生活保健指导 通过体检记录的号码
	// 参数，总结，指导，体检号吗
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.confirm('确定提交吗?', {
			icon : 3,
			title : '提示'
		}, function(index) {
			layer.close(index);
//			var requ = $("#summary1").val();
//			var guidev = $("#summary2").val();
			$.ajax({
				type : "POST", // 提交方式
				url : "../summaryLast.action", // 路径
				data : {
					mecNum : mecNumLast,
					req : ret1,
					guide : ret2
				},
				dataType : "json",
				success : function(respData) {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg('提交成功！', {
							time : 2000, // 10s后自动关闭
							area : '300px',
							icon : 1,
						});
					});
					mecNumLast = "";
					createedit();
					//重新创建编辑器
					layui.use('layedit', function(){
						layedit= layui.layedit;
						  layedit.set({
						  uploadImage: {
						    url: "../image.action", //接口url 
						    type: 'post'
						  }
						});
						index1 =layedit.build('summary1',{
							  height: 375 ,//设置编辑器高度
							  tool: [
							  'strong' //加粗
							  ,'italic' //斜体
							  ,'left' //左对齐
							  ,'center' //居中对齐
							  ,'right' //右对齐
							  ,'image' //插入图片
							  ,'|' //分割线
							  ,'del' //删除线
							  ,'underline' //下划线
						]
						
						}); //建立编辑器
						
						index2 =layedit.build('summary2',{
							  height: 375 ,//设置编辑器高度
							  tool: [
							  'strong' //加粗
							  ,'italic' //斜体
							  ,'left' //左对齐
							  ,'center' //居中对齐
							  ,'right' //右对齐
							  ,'image' //插入图片
							  ,'|' //分割线
							  ,'del' //删除线
							  ,'underline' //下划线
						]
						
						}); //建立编辑器
						});
					
				

					//清楚信息
					$("#cardBalance").val("");
					$("#userName").val("");
					$("#userSex").val("");
					$("#userAge").val("");
					$("#userBloodType").val("");
					$("#userBirthday").val("");
					$("#userPhone").val("");
					$("#userAddress").val("");
					$("#userNativeplace").val("");
					$("#mecNum").val("");
					//清楚富文本信息
					createedit();
					//刷新表格
					$("#tbody").html("");
					
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
				},
			});
		});

	});

}
// 获取 用户，项目记录的信息
function getInfoAjax(mecNum) {// 发送ajax, 获取数据库所有文件类型
	$("#comboPanel1").show();
	$("#comboPanel2").show();
	$.ajax({
				url : "../getInfo.action",
				type : "post",
				data : {
					mecNum : mecNum
				},
				dataType : "json",
				success : function(respData) {
					if (respData.user == undefined) {// 卡号错误无效
						layui.use('layer', function() {
							var layer = layui.layer;
							layer.msg('体检号码有误！', {
								time : 2000, // 10s后自动关闭
								area : '200px',
								icon : 2,
							});
						});
						//清楚富文本里的内容（重新创建编辑器）
						createedit();
						mecNumLast = '';
						$("#cardBalance").val("");
						$("#userName").val("");
						$("#userSex").val("");
						$("#userAge").val("");
						$("#userBloodType").val("");
						$("#userBirthday").val("");
						$("#userPhone").val("");
						$("#userAddress").val("");
						$("#userNativeplace").val("");
						var tr = $("<tr></tr>");
						var td = $("<td></td>");
						td.text("请先输入体检号码");
						td.attr("colSpan", 6);
						tr.append(td);
						$("#tbody").html("");
						$("#tbody").append(tr);
						$("#summary1").val("");
						$("#summary2").val("");
						return;
					}

					// 给文本框赋值
					$("#summary1").val("");
					$("#summary2").val("");
					$("#cardBalance").val(respData.user.card.balance);
					$("#userName").val(respData.user.userName);
					$("#userSex").val(respData.user.userSex);
					$("#userAge").val(respData.user.userAge);
					$("#userBloodType").val(respData.user.userBloodType);
					$("#userBirthday").val(respData.user.userBirthday);
					$("#userPhone").val(respData.user.userPhone);
					$("#userAddress").val(respData.user.userAddress);
					$("#userNativeplace").val(respData.user.userNativePlace);
					// 卡号返回 赋值给全局变量
					mecNumLast = respData.mec.mrNum;
					var stateMec = respData.mec.mrState;
					if (stateMec == 4) { // 表是体检记录已经完结，不可以再次编辑体检结果，总结，建议等等。
						$("#comboPanel1").hide();
						$("#comboPanel2").hide();
						layui.use('layer', function() {
							var layer = layui.layer;
							layer.msg('该体检记录已经生成报告,不可再次编辑！', {
								time : 2000, // 10s后自动关闭
								area : '350px',
								icon : 2,
							});
						});
					}
//					else if(stateMec != 3){
//						$("#comboPanel1").hide();
//						$("#comboPanel2").hide();
//						layui.use('layer', function() {
//							var layer = layui.layer;
//							layer.msg('该体检记录状态是未体检，不能录入总结！', {
//								time : 2000, // 10s后自动关闭
//								area : '350px',
//								icon : 2,
//							});
//						});
//					}
//					;
					// 画数据到表格上
					var end = respData.mec.irList.length ;
					// console.log(respData.mec.irList);
					// alert(respData.mec.irList[0].itemsName);
					// var list = respData.mec.irList;
					$("#tbody").html("");
					if (end == 0) {
						var tbody = $("#tbody");
						var tr = $("<tr></tr>");
						var td = $("<td></td>");
						td.text("请先输入体检号码");
						td.attr("colSpan", 6);
						tr.append(td);
						tbody.append(tr);
					}
					for (var i = 0; i < end; i++) {

						// 创建一个tr节 点
						var tbody = $("#tbody");
						var tr = $("<tr ></tr>");
						var td0 = $("<td></td>");
						var td1 = $("<td></td>");
						var td2 = $("<td></td>");
						var td3 = $("<td></td>");
						var td4 = $("<td></td>");
						var td5 = $("<td></td>");
						var td6 = $("<td></td>");
						// 向td节点中添加文本
						td0.append(i + 1);
						td2.append(respData.mec.irList[i].irTime);
						td3.append(respData.mec.irList[i].itemsAdmin);
						td4.append(respData.mec.irList[i].irState);
						td5.append(respData.mec.irList[i].depId);
						td1.append(respData.mec.irList[i].itemsName);
						// 还有三个参数
						// list[i].irId--项目id
						// list[i].itemsRes 项目体检结果
						// list[i].irMemo 状态code

						if (respData.mec.irList[i].irMemo == 3) {// 表示已经体检
							button = $("<button title='点击查看体检小结'style='margin:-7px' class=\"layui-btn layui-btn-sm  layui-inline \" ><i class=\"layui-icon layui-icon-search layui-inline \" ></i> 体检结果 </button>");
							button.attr("irId", respData.mec.irList[i].irId);
							button.attr("itemsName",
									respData.mec.irList[i].itemsName);
							// mecNumLast
							button.on("click", function() {
								var id = $(this).attr("irId");
								var name = $(this).attr("itemsName");
								// 通过项目记录id 查询出项目小结
								$.ajax({
									url : "../getResult.action",
									type : "post",
									data : {
										irId : id
									},
									dataType : "json",
									success : function(respData) {
										// console.log(respData.result);
										var ret = respData.result;
										// console.log(ret);
										layui.use("layer", function() {
											var layer = layui.layer;
											layer.open({
												type : 2,
												title : name + '体检结果',
												skin : 'layui-layer-rim',
												area : [ '900px', '500px' ],
												// content :
												// ['peResult.jsp','no'],
												content : 'peResult.jsp?id='
														+ id
											// end : function () {
											// firstAjax(currPage,cardNum,itemsName,itemsState);
											// }
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
								});
							});
							td6.append(button);
						}

						tr.append(td0);
						tr.append(td1);
						tr.append(td2);
						tr.append(td3);
						tr.append(td4);
						tr.append(td5);
						tr.append(td6);
						tbody.append(tr);
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
}
function clearMecNum() {
	$("#mecNum").val("");
}
// 读体检号码
function readMecNum() {
	var mecNum = $("#mecNum").val();
	if (mecNum == '') {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('体检号码不能为空！', {
				time : 2000, // 10s后自动关闭
				area : '230px',
				icon : 2,
			});
		});
		//清楚富文本里的内容（重新创建编辑器）
		createedit();
		//清楚基本信息
		$("#cardBalance").val("");
		$("#userName").val("");
		$("#userSex").val("");
		$("#userAge").val("");
		$("#userBloodType").val("");
		$("#userBirthday").val("");
		$("#userPhone").val("");
		$("#userAddress").val("");
		$("#userNativeplace").val("");
		return;
	}
	// 发送ajax 获取信息
	getInfoAjax(mecNum);
}

//扫描二维码


function ajaxFileUploaddeqr() {
	$("#deqr_result").hide(), $(".deqr_prompt").hide(), $(".deqr_loading").show(), seajs.use(["lib/plug/ajaxfileupload", "uploadfile/uploaddeqr"], function(e, t) {
		t.ajaxFileUpload()
	})
}

function checkURL(e) {
	var t = e,
		n = /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/,
		a = new RegExp(n);
	return 1 == a.test(t) ? !0 : !1
}
var oldIE;
function closeee(){
	$(".scanner").hide(), 
	$("#htmlScanModal").hide();
}
$(function() {
	function e() {
		n.onScan = function(e, t) {
			var a = "";
			if (!e)
				if ("onlineScan" === t.type && 1 === t.data.status) {
					var a = t.data.info.data[0];
					n.stopScan(!1), $(".scanner").hide(), 
					$("#scan-content").show().text(a),
					$("#continueToScan").show(), 
					$("#copyResultBtn").show(),
					$("#htmlScanModal .modal-footer").show()
				} else if ("webScan" === t.type && t.data) {
				var a = t.data.data;
				n.stopScan(!1),
				
				$(".scanner").hide(), 
				$("#htmlScanModal").hide();
				//把扫描到的体检号回填到输入框汇中（）
				$("#mecNum").val(a);;
				
				// 发送ajax 获取信息
				getInfoAjax(a);
			}
		}, n.scan(document.querySelector("#scanVideo")), $(".scanner").show(), $("#continueToScan").hide(), $("#scan-content").hide(), $("#copyResultBtn").hide(), $("#htmlScanModal").modal("show"), $("#htmlScanModal .modal-footer").hide()
	}
	$("html").is(".lt-ie9") && (oldIE = !0), oldIE && $('input[type="text"], textarea').placeholder();
	var t = document.documentElement.clientHeight - 70 - 360;
	$("#warper").css("min-height", t + "px"), $(document).click(function(e) {
		$(".deqr_url").is(e.target) || $(".deqr_url").has(e.target).length > 0 || "" != $("#deqr_urls").val() ? ($("#deqr_url_show").removeClass("none"), $("#qr-url").hide()) : ($("#deqr_url_show").addClass("none"), $("#qr-url").show())
	}), $("#deqr_url_btn").click(function() {
		var e = $("#deqr_urls").val();
		e.indexOf("http://") < 0 && e.indexOf("https://") < 0 && $("#deqr_urls").val("http://" + e), $("#deqr_result").hide(), $(".deqr_prompt").hide(), $("#modal-alert-deqr-result").modal("show"), $(".deqr_loading").show();
		var e = $("#deqr_urls").val();
		$.post("/Api/Browser/deqr", {
			data: e
		}, function(e) {
			1 == e.status ? ($(".deqr_loading").hide(), $("p#deqrresult").text(e.data.RawData), $("#deqr_result").show()) : ($(".deqr_loading").hide(), alert(e.data.info), $(".deqr_prompt").show())
		}, "json")
	});
	var n = null;
	$("#toggleToFlash").click(function() {
		$("#htmlScanModal").modal("hide"), n.stopScan(!1), seajs.use("m/deqrCamera", function(e) {
			e.openCamera()
		})
	}), $("#stopScan").click(function() {
		$("#htmlScanModal").modal("hide")
	}), $("#copyResultBtn").click(function() {
		if (!window.getSelection || !document.createRange) return void alert("����������֧�ָ��ƹ���");
		var e = window.getSelection();
		e.removeAllRanges();
		var t = document.createRange();
		t.selectNodeContents($("#scan-content")[0]), e.addRange(t), document.execCommand("copy"), alert("���Ƴɹ�")
	}), $("#continueToScan").click(function() {
		e()
	}), $("#htmlScanModal").on("hide.bs.modal", function() {
		setTimeout(function() {
			n.stopScan(!1)
		}, 300)
	}), $("#deqrcamera").click(function() {
		$("#htmlScanModal").show();
		try {
			if (n = new $QRCodeScanner, n.canIUse()) return n.openWebScan = !0, n.scanFrequency = 1e3, void e();
			throw Error("Can not use cam to scan.")
		} catch (t) {
			seajs.use("m/deqrCamera", function(e) {
				e.openCamera()
			})
		}
	}), $("input#filedatacode").click(function() {
		var e = $(this).attr("uptype");
		seajs.use(["//static.clewm.net/public/upload.js?v=20170503", "m/upload"], function(t, n) {
			n.upload(e)
		})
	})
});

function createedit(){
	
	//重新创建编辑器
	layui.use('layedit', function(){
		layedit= layui.layedit;
		  layedit.set({
		  uploadImage: {
		    url: "../image.action", //接口url 
		    type: 'post'
		  }
		});
		index1 =layedit.build('summary1',{
			  height: 375 ,//设置编辑器高度
			  tool: [
			  'strong' //加粗
			  ,'italic' //斜体
			  ,'left' //左对齐
			  ,'center' //居中对齐
			  ,'right' //右对齐
			  ,'image' //插入图片
			  ,'|' //分割线
			  ,'del' //删除线
			  ,'underline' //下划线
		]
		
		}); //建立编辑器
		
		index2 =layedit.build('summary2',{
			  height: 375 ,//设置编辑器高度
			  tool: [
			  'strong' //加粗
			  ,'italic' //斜体
			  ,'left' //左对齐
			  ,'center' //居中对齐
			  ,'right' //右对齐
			  ,'image' //插入图片
			  ,'|' //分割线
			  ,'del' //删除线
			  ,'underline' //下划线
		]
		
		}); //建立编辑器
		});
	
}
