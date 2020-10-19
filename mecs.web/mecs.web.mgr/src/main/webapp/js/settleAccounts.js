//清空
function clearCardNum(){
	$("#cardNum").val("");
}


layui.use('form', function() {
	var form = layui.form;

	// 监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});

//创建一个layer出来
var layer;
layui.use('layer', function() {
	layer = layui.layer;
	
});


//日期控件 因为只用到了layer只要用上面的就可以了
/*layui.use([ 'form', 'layedit', 'laydate' ],
function() {
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

});*/

//读卡
function readCard(){
	//输入款规范判断，判断所有输入款的是否为非空
	var cardNum=$("#cardNum").val();
	//01.卡号，非空 
	var cardNum2 = cardNum.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
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
	
	
	//var cardBalance=$("#cardBalance").val();
	
	if(cardNum2==null || cardNum2=="" || cardNum2==undefined){
		layui.use('layer', function(){
			  var layer = layui.layer;
			  layer.alert("卡号不能为空");
		});
	}
	


	$.ajax({
		type:"post",
		url:"../settleAccounts",
		data:{
			cardNum:cardNum2
		},
		dataType:"json",
		success:function(respData){
			var dto=respData.SettleAccountsKey;
			
			
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
					  layer.alert("没有这个卡号");
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
				
				
				/*
				for(var i=0;i<list.length;i++){
					alert(list[i].mrId);
					
					//第一列的序号
					var first=i+1;
					//创建按钮
					var update = $("<button class=\"layui-btn layui-bg-orange layui-btn-sm\"><i class=\"layui-icon layui-icon-set-sm layuiadmin-button-btn\"></i>编辑 </button>");

					//创建一个tr标签
					if (list[i].mrState == "未结算") {
					var row=
						"<tr>"+
							"<th>"+first+"</th>"+//序号
							//"<th>"++"</th>"+//卡号
							"<th>"+list[i].comboName+"</th>"+//套餐名,可能会是一个集合,所以以","分割
							"<th>"+list[i].mrPrice+"</th>"+//体检价格
							"<th>"+list[i].saAdmin+"</th>"+//结算人
							"<th>"+list[i].mrState+"</th>"+//状态
							
							"<th>"+
								"<button onclick=\"SettleAccountsCommit()\" class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-rmb layuiadmin-button-btn\"></i>结算</button>"+
							"</th>"+//操作
							
							"<th>"+list[i].bilTime+"</th>"+//开单时间
						"</tr>";
					}else{
						
						"<tr>"+
						"<th>"+first+"</th>"+//序号
						//"<th>"++"</th>"+//卡号
						"<th>"+list[i].comboName+"</th>"+//套餐名,可能会是一个集合,所以以","分割
						"<th>"+list[i].mrPrice+"</th>"+//体检价格
						"<th>"+list[i].saAdmin+"</th>"+//结算人
						"<th>"+list[i].mrState+"</th>"+//状态
						
						"<th>"+
							"<button onclick=\"SettleAccountsCommit()\" class=\"layui-btn layui-bg-blue layui-btn-sm\"><i class=\"layui-icon layui-icon-rmb layuiadmin-button-btn\"></i>已结算</button>"+
						"</th>"+//操作
						
						"<th>"+list[i].bilTime+"</th>"+//开单时间
					"</tr>";
					
					}
					table.append(row);
				}	
				
				*/
				
			}
			
			/*//登录成功，跳转到新的页面
			if(respData.loginback=="suc"){
				window.location.href="fileQuery.jsp";
			}*/
		
		},
		
		error:function(respData){
			layer.alert("读卡AJAX错误,没有返回值");
		}
		
	});
}
	//提交结算的挂事件计算之后，要再刷新界面
	function SettleAccountsCommit(mrId,mrState){  
		
		
		//获得输入框的卡号
		var cardNum=$("#cardNum").val();
		//01.卡号，
		var cardNum2 = cardNum.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
		
		//如果得到 未结算，给界面发送 已结算 ，让dao方法先去通过字典表去改成响应的字母想，
		//如果是已结算，那就不用去管它，因为已经没办法改变状态了
		//如果是启用，禁用的话，怎么两种情况都要去操作
		if(mrState=="已结算"){
			/*mrState="未结算";*/
			return;
		}
		if(mrState=="未结算"){
			/*var haveSettle="已结算";
			var NoSettle="未结算";*/
			
			//layui 确定取消按钮
			layer.confirm('你确定' + "结算" + '吗？', {
				btn : [ '确定', '取消' ]
			// 按钮
			
			
			}, function(index) {
				
				$.ajax({
					type:"post",
					url:"../SettleAccountsCommit",
					data:{
						/*haveSettle:haveSettle,
						NoSettle:NoSettle,*/
						mrId:mrId
			
					},
					dataType:"json",
					success:function(respData){
						/** 可能返回的值如下
						 * haveSettle:已经被结算了
						 * balanceInsuf:余额不足
						 * updateBalanceErr:修改卡的余额失败
						 * Err1:：请重试，修改体检记录表的状态失败
						 * Err2：请重试，修改项目记录表的状态失败
						 * Err5:请重试，插入日志，失败
						 * notLogIn:账号未登录
						 * Err3:：请重试。插入日志失败
						 * cardNumIsNull：插入对账表的时候，查询卡号失败
						 * Err4：请重试。个人对账表插入数据失败
						 * 
						 * suc:结算成功*/
						var v=respData.SettleAccountsCommitKey;
						if(v=="haveSettle"){
							layer.alert("已经被结算了,请刷新界面");
						}
						if(v=="balanceInsuf"){
							layer.alert("余额不足,请先充值");
						}
						if(v=="updateBalanceErr"){
							layer.alert("请重试，修改卡的余额失败");
						}						
						if(v=="Err1"){
							layer.alert("请重试，修改体检记录表的状态失败");
						}
						if(v=="Err2"){
							layer.alert(" 请重试，修改项目记录表的状态失败");
						}							
						if(v=="Err5"){
							layer.alert("请重试，插入日志，失败");
						}
						if(v=="notLogIn"){
							layer.alert("账号未登录");
						}							
						if(v=="cardNumIsNull"){
							layer.alert("插入对账表的时候，查询卡号失败");
						}
						if(v=="Err4"){
							layer.alert("请重试。个人对账表插入数据失败");
						}	
						if(v=="suc"){
							layer.alert("结算成功");
							//要调用显示的AJAX 
							readCard();
						}							
												
					},
					
					error:function(respData){
						layer.alert("读卡AJAX错误,没有返回值");
					}
					
				});
				
				layer.close(index);//解决点了确定之后弹框不消失的问题
			}, function(){
		    });
			return false;//防止不点 确定直接提交的问题
		   
		}

	}

//卡充值
function AdminCardTopUp(){
	//获得输入框的卡号
	var cardNum=$("#cardNum").val();
	var cardNum2 = cardNum.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
	
	//把这个界面的值传到充值界面。然后必须有用配套的方法接收数据
//	window.location.href="adminTopUp.html?valus="+cardNum2;  
  
	if(cardNum2==null || cardNum2=="" || cardNum2==undefined){
		
		layer.alert("卡号不能为空");
		
	}else{
		
		//显示充值界面
		layer.open({
			type : 2,
			title : "充值",
			skin : 'layui-layer-rim', // 加上边框
			area : [ '550px', '400px' ], // 宽高
			offset: '30px',//底部坐标
			content : 'adminTopUp.html?valus='+cardNum2,
			
			//关闭HTML弹框的之后操作的
			end:function(){
				readCard();
			},
		});
	}
}

