/*
 * 卡号
 * 体检号
 */
var cardNumVal = '';
var mecCodeVal = 0;

layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});

$().ready(function(){
	readCard();
});

function readCard() {
	cardNumVal = $("#cardNum").val();
	mecCodeVal = $("#mecCode").val();
	if (cardNum == null || cardNum == '' || cardNum == undefined || mecCode == null || mecCode == '' || mecCode == undefined) {
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('请输入体检卡号,或者体检号码', {
				icon : 2
			});
		});
		/*
		 * 清除表中数据
		 */
		clearTable();
		return;
	}
	$.ajax({
		type: "POST", // 提交方式
		url: "../getMecRecord.action", // 路径
		data: {
			cardNum: cardNumVal,
			mecCode: mecCodeVal
		},
		dataType: "json",
		success: function(respData) {
			if (respData.res == "isNull") {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('信息丢失', {
						icon : 2
					});
				});
			} else if(respData.res == "cardNumErr"){
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('登录卡号与输入卡号不一致', {
						icon : 2
					});
				});
			}else {
				showTableInfo(respData.mecRecordList);
			}
		},
		error: function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败', {
					icon : 2
				});
			});
		},
	});
}

/*
 * 清除表中数据方法
 */
function clearTable() {
	var tbody = $("#mecRecord");
	tbody.html("");
	var tr = $("<tr></tr>");
	var td = $("<td colspan=\"6\" style=\"text-align: center;font-weight: bold;\">请先输入卡号或者体检码</td>");
	tr.append(td);
	tbody.append(tr);
}

/*
 * 显示表中数据
 */
function showTableInfo(list) {
	var tbody = $("#mecRecord");
	tbody.html("");
	if (list.length > 0) {
		for (var i = 0; i < list.length; i++) {
			var mr = list[i];

			var tr = $("<tr></tr>");

			var seq = $("<td style=\"width: 10%;\"></td>");
			var mecCode = $("<td style=\"width: 15%;\"></td>");
			var comboName = $("<td style=\"width: 30%;\"></td>");
			var price = $("<td  style=\"width: 10%;\"></td>");
			var time = $("<td style=\"width: 20%;\"></td>");
			var print = $("<td style=\"width: 13.5%;\"></td>");

			seq.append(i + 1);
			mecCode.append(mr.mrNum);
			comboName.append(mr.comboName);
			price.append(mr.mrPrice);
			time.append(mr.bilTime);

			if(mr.mrState == "已打印" || mr.mrState == "已体检"){
				var printBtn = $("<button class=\"layui-btn layuiadmin-btn-admin layui-btn-sm\">打印体检报告</button>");
				printBtn.attr("mrNum",mr.mrNum);
				printBtn.on("click",function(){
					var mrNum = $(this).attr("mrNum");
					jumpPrintMecReport(mrNum);
				});
				print.append(printBtn);
			}else{
				print.append("未体检结束不可打印");

			}

			tr.append(seq);
			tr.append(mecCode);
			tr.append(comboName);
			tr.append(price);
			tr.append(time);
			tr.append(print);

			tbody.append(tr);
		}
	} else {
		var tr = $("<tr></tr>");
		var td = $("<td colspan=\"6\" style=\"text-align: center;font-weight: bold;\">请确认体检卡号或体检号码</td>");
		tr.append(td);
		tbody.append(tr);
	}

}

/*
 * 清空数据
 */
function clearInfo(){
	$("#cardNum").val("");
	$("#mecCode").val("");
	clearTable();
}

/*
 *进入生成体检报告
 */
function jumpPrintMecReport(mrNum) {
	window.location.href="../printMecReport.action?mrNum=" + mrNum;
}

function backMainPage(){
	window.location.href='../view/healthCheckup.html'
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
				$("#mecCode").val(a);;
				
				// 发送ajax 获取信息
				ScanCard(a);
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
function ScanCard(mecNum) {
	if (mecNum == null || mecNum == '' || mecNum == undefined) {
		alert("请输入体检卡号,或者体检号码");
		/*
		 * 清除表中数据
		 */
		clearTable();
		return;
	}
	$.ajax({
		type: "POST", // 提交方式
		url: "../getMecRecord.action", // 路径
		data: {
			cardNum: cardNumVal,
			mecCode: mecNum
		},
		dataType: "json",
		success: function(respData) {
			if (respData.res == "isNull") {
				alert("信息丢失");
			} else {
				showTableInfo(respData.mecRecordList);
			}
		},
		error: function() {
			alert("请求失败");
		},
	});
}