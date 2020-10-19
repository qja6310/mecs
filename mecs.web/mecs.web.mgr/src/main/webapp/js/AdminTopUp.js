
var result;

//创建一个layer出来
var layer;
layui.use('layer', function() {
	layer = layui.layer;
	
});


//取消充值
function out(){
	
	setTimeout(function() {
		// 关闭弹出的html页面
		var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
		parent.layer.close(index);
	}, 1000);// 1秒
	
}

$().ready(function(){
	//用配套的方法接收从充值界面传过来的值
	
	var url=window.location.search; //获取url中"?"符后的字串  
	if(url.indexOf("?")!=-1){
	result = url.substr(url.indexOf("=")+1);
	$("#cardNum").val(result);
	}
});

//确认充值
function topUp(){
	
	//输入款规范判断，判断所有输入款的是否为非空
	//var cardNum=$("#cardNum").val();
	
	//01.卡号，非空 
	var cardNum2 = result.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
	
    if (cardNum2 == '' || cardNum2 == undefined || cardNum2 == null) {
			
			layer.msg('卡号不能为空', {
				icon : 2
			});
		
        return false;
    } 
    
    //只能输入字母和字母
    var reg = /^[0-9a-zA-Z]+$/;
    if(!reg.test(cardNum2)){
    	 layui.use('layer', function() {
  			var layer = layui.layer;
  			layer.msg('卡号只能输入字母和数字', {
  				icon : 2
  			});
  		});

        return false;
    }
	
    
    //这个充值从充值的html来的
    //01.充值金额
    var money=$("#money").val();
	var money2 = money.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
    if (money2 == '' || money2 == undefined || money2 == null) {
			
			layer.msg('充值金额不能为空', {
				icon : 2
			});
		
        return false;
    } 
    
  
  
    $.ajax({
    	type:"post",
    	url:"../AdminTopUp",
    	data:{
    		cardNum:cardNum2,
    		money:money2	
    	},
    	dataType:"json",
    	success:function(respData){
    		
    		
    		if(respData.AdminTopUpKey=="suc"){
    			layer.alert("充值成功");
    			//调用读卡的ajax刷新结算的充值页面
    			//showCardMsg(cardNum2);
    			//隐藏充值界面
    			
    			setTimeout(function() {
    				// 关闭弹出的html页面
    				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
    				parent.layer.close(index);
    			}, 1000);// 2秒   是写2000
    			
    			
    		}
    		
    		if(respData.AdminTopUpKey=="Err"){
    			layer.alert("充值失败");
    		}
    		
    		
    		if(respData.AdminTopUpKey=="notLogin"){
    			layer.alert("账号未登录");
    		}
    	},
    	
    	error:function(respData){
    		layer.alert("AJAX错误");
    		
    	}
    	
    	});
	
}

//刷新 读卡的信息
function showCardMsg(cardNum2){
	$.ajax({
		type:"post",
		url:"../settleAccounts",
		data:{
			cardNum:cardNum2
		},
		dataType:"json",
		success:function(respData){
			
			var dto=respData.SettleAccountsKey;
			console.log("刷新返回时："+dto);
			if(dto=="inputHaveNull"){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("输入框为空，结算失败");
				});
				return;
			}
			if(dto=="cardNumErr"){
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("查无次卡号");
				});
				return;
			}
			//先取出余额,画到界面
			$("#cardBalance").val(dto.card.balance);
			
			//再取出user的信息，画到界面
			$("#userName").val(dto.userName);// 姓名
			$("#userSex").val(dto.userSex);// 性别
			$("#userAge").val(dto.userAge);// 年龄
			$("#userBloodType").val(dto.userBloodType);// 血型
			$("#userBirthday").val(dto.userBirthday);// 出生日期
			$("#userPhone").val(dto.userPhone);// 电话
			$("#userAddress").val(dto.userAddress);// 住址
			$("#userNativeplace").val(dto.userNativePlace);// 籍贯
			$("#textareaID").val(dto.userMemo);// 备注
			 
			//再画体检记录表
			var list=dto.mecRecordlist;
			//获得表格对象
			var table=$("#tableId");
			//清空除了表头的数据
			$("#tableId tr:not(:first)").html("");  
			//或者
			//$("#tableId tr:not(:first)").empty("");
			//或者：内部的双引号 用单 \ 转义
			//table.html("<tr class=\"layui-bg-cyan\"><th style=\"width: 5%;\">序号</th><th style=\"width: 20%;\">套餐</th><th style=\"width: 5%;\" >金额</th><th style=\"width: 10%;\">状态</th><th style=\"width: 10%;\">时间</th></tr>");
			if(list.length>0){
				for(var i=0;i<list.length;i++){
					//一行的标签先写好，再接着来
					var tr=$("<tr></tr>");
					var th1=$("<th></th>");
					var th2=$("<th></th>");
					var th3=$("<th></th>");
					var th4=$("<th></th>");
					var th5=$("<th></th>");
					var th6=$("<th></th>");
					var th7=$("<th></th>");
					
					//给所有列赋值，或者设置按钮
					//第一列的序号
					var first=i+1;
					th1.text(first);
					th2.text(list[i].comboName);
					th3.text(list[i].mrPrice);
					th4.text(list[i].saAdmin);
					th5.text(list[i].mrState);
					
					//操作区
					//先生成按钮，再加到按钮上面
					var btn=$("<button class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-rmb layuiadmin-button-btn\"></i></button>");
					if(list[i].mrState == "未结算"){
						btn=$("<button class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-rmb layuiadmin-button-btn\"></i>结算</button>");
						//把值存到按钮里面
						btn.attr("mrId",list[i].mrId);
						btn.attr("mrState",list[i].mrState);
						//按钮绑定监听，调用方法把存的值传出去，把这一行的体检表ID和中文的状态传回去了。
						btn.on("click",function(){
							SettleAccountsCommit($(this).attr("mrId"),$(this).attr("mrState"));
						});
						
						
					}
					if(list[i].mrState == "已结算"){
						btn=$("<button class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-rmb layuiadmin-button-btn\"></i>已结算</button>");
						//把值存到按钮里面
						btn.attr("mrId",list[i].mrId);
						btn.attr("mrState",list[i].mrState);
						//按钮绑定监听.调用方法把存的值传出去。，把这一行的体检表ID和中文的状态传回去了。
						btn.on("click",function(){
							SettleAccountsCommit($(this).attr("mrId"),$(this).attr("mrState"));
						});
						
					}
					//按钮加到列上
					th6.append(btn);
					
					//最后一列
					th7.text(list[i].bilTime);
					
					//把列加到tr行上
					tr.append(th1);
					tr.append(th2);
					tr.append(th3);
					tr.append(th4);
					tr.append(th5);
					tr.append(th6);
					tr.append(th7);
					//把行加到表上
					table.append(tr);
				}
				
			}
		},
				
		error:function(respData){
			alert("读卡AJAX错误,没有返回值");
		}
				
		});
}


