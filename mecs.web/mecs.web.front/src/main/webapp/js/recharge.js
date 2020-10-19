layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});

$().ready(function(){
	getCardNum();
});

function getCardNum(){
	$.ajax({
		type : "POST", // 提交方式
		url : "../getCardNumForRecharge.action", // 路径
		data : {
			
		},
		dataType : "json",
		success : function(respData) {
			$("#head").append("自助充值[" +respData.cardNum+"]");
			$("#cardNum").val(respData.cardNum);
			$("#balance").val(respData.balance);
			$("#WIDout_trade_no").val(respData.aoNum);
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
}


function sureRecharge(){
	var money = $("#WIDtotal_amount").val().trim();
	if(money == null || money == '' || money == 0){
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('请输入有效金额！', {
				time : 2000, // 2s后自动关闭
				area : '200px',
				icon : 2,
			});
		});
		return;
	}
	window.location.href='../recharge.action?rechrgeMoney='+money
}

function backMainPage(){
	window.location.href='../view/healthCheckup.html'
}
