$().ready(function() {
	welcome();
});

layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});

/*
 * 获取套餐ID,将billingOrder.html和comboDetails.html两个界面信息共享
 */
function welcome() {
	$.ajax({
		type : "POST", // 提交方式
		url : "../getAdminAndData.action", // 路径
		data : {},
		dataType : "json",
		success : function(respData) {
			if (respData.res == "isNull") {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('请先登录', {
						icon : 2
					});
				});
			} else {
				show(respData);
			}
		},
		error : function() {
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
 * 将详情信息显示出来
 */
function show(respData) {
	/*
	 * 显示个人信息
	 */
	 var admin = respData.admin;
	 var welcome = "欢迎管理员:"
	 var p = $("<p class=\"x-red\"> "+admin.adminName+" </p>");
	 var time = "登录时间:" + CurentTime();
//	 var welcome = wel + p + time;
	 $("#adminName").append(welcome);
	 $("#adminName").append(p);
	 $("#adminName").append(time);
	
	 /*
	  * 售卡数
	  */
	 $("#sellCardNum").append(respData.sellCardNum);
	 /*
	  * 用户数
	  */
	 $("#userNum").append(respData.userNum);
	 /*
	  * 营业额
	  */
	 $("#turnover").append(respData.turnover);
	 
	 /*
	  * 可用卡数
	  */
	 $("#usableCardNum").append(respData.usableCardNum);
	 
	 /*
	  * 当月售卡数
	  */
	 $("#sell").append(respData.sell);
	 
	 /*
	  * 当月用户数
	  */
	 $("#user").append(respData.userM);
	 
	 /*
	  * 当月营业额
	  */
	 $("#yingyee").append(respData.turnoverM);
	 
	
}

function CurentTime()
{ 
    var now = new Date();
    
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒
    
    var clock = year + "-";
    
    if(month < 10)
        clock += "0";
    
    clock += month + "-";
    
    if(day < 10)
        clock += "0";
        
    clock += day + " ";
    
    if(hh < 10)
        clock += "0";
        
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm + ":"; 
     
    if (ss < 10) clock += '0'; 
    clock += ss; 
    return(clock); 
}