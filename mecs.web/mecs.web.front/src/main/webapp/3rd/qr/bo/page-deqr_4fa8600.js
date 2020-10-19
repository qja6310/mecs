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
				alert("扫描成功，结果为："+a);
				
//				$("#scan-content").show().text(a), 
//				$("#continueToScan").show(),
//				$("#copyResultBtn").show(),
//				$("#htmlScanModal .modal-footer").show()
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