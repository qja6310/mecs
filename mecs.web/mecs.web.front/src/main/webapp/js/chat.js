var doc = document;
var dialogueInput = doc.getElementById('dialogue_input'), dialogueContain = doc
		.getElementById('dialogue_contain'), dialogueHint = doc
		.getElementById('dialogue_hint'), btnOpen = doc
		.getElementById('btn_open'), btnClose = doc.getElementById('btn_close'), timer, timerId, shiftKeyOn = false; // 辅助判断shift键是否按住

$("#send").on(
		"click",
		function() {
			if (dialogueInput.value == '') {

				// 多次触发只执行最后一次渐隐
				setTimeout(function() {
					fadeIn(dialogueHint);
					clearTimeout(timerId)
					timer = setTimeout(function() {
						fadeOut(dialogueHint)
					}, 2000);
				}, 10);
				timerId = timer;
			} else {

				var nodeP = doc.createElement('p'), nodeSpan = doc
						.createElement('span');
				nodeP.classList.add('dialogue-customer-contain');
				nodeSpan.classList.add('dialogue-text',
						'dialogue-customer-text');
				nodeSpan.innerHTML = dialogueInput.value;
				nodeP.appendChild(nodeSpan);
				dialogueContain.appendChild(nodeP);
				dialogueContain.scrollTop = dialogueContain.scrollHeight;
				/*
				 * send(dialogueInput.value);// 发送消息给后端
				 */
				guide(dialogueInput.value);
				$('#dialogue_input').val("");

			}

		});

btnOpen
		.addEventListener(
				'click',
				function(e) {
					$('.dialogue-support-btn').css({
						'display' : 'none'
					});
					$('.dialogue-main').css({
						'display' : 'inline-block',
						'height' : '0'
					});
					$('.dialogue-main').animate({
						'height' : '520px',
					})
					getServiceText("您好，欢迎来到自贡康桥健康体检中心，请输入以下菜单序号进行导航查询：1.体检流程质询。2.体检报告咨询。3.自助充值咨询。4.对账记录咨询。");
				})

btnClose.addEventListener('click', function(e) {
	$('.dialogue-main').animate({
		'height' : '0'
	}, function() {
		$('.dialogue-main').css({
			'display' : 'none'
		});
		$('.dialogue-support-btn').css({
			'display' : 'inline-block'
		});
	});
	$("#dialogue_contain").html("");
})

dialogueInput.addEventListener('keydown', function(e) {
	var e = e || window.event;
	if (e.keyCode == 16) {
		shiftKeyOn = true;
	}
	if (shiftKeyOn) {
		return true;
	} else if (e.keyCode == 13 && dialogueInput.value == '') {
		// console.log('发送内容不能为空');
		// 多次触发只执行最后一次渐隐
		setTimeout(function() {
			fadeIn(dialogueHint);
			clearTimeout(timerId)
			timer = setTimeout(function() {
				fadeOut(dialogueHint)
			}, 2000);
		}, 10);
		timerId = timer;
		return true;
	} else if (e.keyCode == 13) {
		var nodeP = doc.createElement('p'), nodeSpan = doc
				.createElement('span');
		nodeP.classList.add('dialogue-customer-contain');
		nodeSpan.classList.add('dialogue-text', 'dialogue-customer-text');
		nodeSpan.innerHTML = dialogueInput.value;
		nodeP.appendChild(nodeSpan);
		dialogueContain.appendChild(nodeP);
		dialogueContain.scrollTop = dialogueContain.scrollHeight;
		/*
		 * send(dialogueInput.value);// 发送消息给后端
		 */
		guide(dialogueInput.value);
	}
});

dialogueInput.addEventListener('keyup', function(e) {
	var e = e || window.event;
	if (e.keyCode == 16) {
		shiftKeyOn = false;
		return true;
	}
	if (!shiftKeyOn && e.keyCode == 13) {
		dialogueInput.value = null;
	}
});
// 绕过调用这个直接发送给后端
function submitCustomerText(text) {
	send(text)
}
/*
 * // code here 向后端发送text内容 // 传给后端 var websocket = new
 * WebSocket("ws://localhost:8080/mecs.web.front/websocket"); //
 * 判断当前浏览器是否支持WebSocket if ('WebSocket' in window) { } else { alert('当前浏览器 Not
 * support websocket') } // 连接发生错误的回调方法 websocket.onerror = function() {
 * setMessageInnerHTML("WebSocket连接发生错误"); }; // 连接成功建立的回调方法 websocket.onopen =
 * function() { setMessageInnerHTML("WebSocket连接成功"); } // 接收到消息的回调方法
 * websocket.onmessage = function(event) { setMessageInnerHTML(event.data);
 * getServiceText(event.data) } // 连接关闭的回调方法 websocket.onclose = function() {
 * setMessageInnerHTML("WebSocket连接关闭"); } //
 * 监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
 * window.onbeforeunload = function() { closeWebSocket(); } // 关闭WebSocket连接
 * function closeWebSocket() { websocket.close(); } // 发送消息 function
 * send(message) { websocket.send(message); }
 */
// 模拟对话数据
function guide(msg) {
	var resp = "";
	if (msg == 1) {
		resp = "用户可以在收费口开卡成功后， 在本页面用卡号进行开单、获取体检报告、充值、对账的功能。"
				+ "用户在开单选项中查看套餐详情：检查的项目与细项，以及价格，方便用户自助选择体检项目、自助充值、体检、结算"
				+ "以及二维码扫码获取体检报告，查看对账记录等。"
	} else if (msg == 2) {
		resp = "用户在'体检报告'选项中通过体检卡号、体检号码或者二维码进行获取体检报告并打印体检报告。"
	} else if (msg == 3) {
		resp = "用户点击'用户充值'按钮，用户可以自行自助充值，填写充值金额后使用'支付宝'扫码充值。"
	} else if (msg == 4) {
		resp = "用户在'个人对账'选项中查看自己的消费对账记录，快速便利查询自己的消费对账动向。"
	} else {
		resp = "请选择正确的问题序号";

	}
	getServiceText(resp);

}

// 模拟后端回复
function getServiceText(data) { /* 客户回复消息 */
	var nodeP = doc.createElement('p'), nodeSpan = doc.createElement('span');
	nodeP.classList.add('dialogue-service-contain');
	nodeSpan.classList.add('dialogue-text', 'dialogue-service-text');
	nodeSpan.innerHTML = data; // 添加客服回复的消息
	nodeP.appendChild(nodeSpan);
	dialogueContain.appendChild(nodeP);
	dialogueContain.scrollTop = dialogueContain.scrollHeight;
}

// 渐隐
function fadeOut(obj) {
	var n = 100;
	var time = setInterval(function() {
		if (n > 0) {
			n -= 10;
			obj.style.opacity = '0.' + n;
		} else if (n <= 30) {
			obj.style.opacity = '0';
			clearInterval(time);
		}
	}, 10);
	return true;
}

// 渐显
function fadeIn(obj) {
	var n = 30;
	var time = setInterval(function() {
		if (n < 90) {
			n += 10;
			obj.style.opacity = '0.' + n;
		} else if (n >= 80) {

			obj.style.opacity = '1';
			clearInterval(time);
		}
	}, 100);
	return true;
}