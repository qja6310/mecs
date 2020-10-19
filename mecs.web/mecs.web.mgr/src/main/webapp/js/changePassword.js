function clearInput(){
	$("#oldPswd").val("");
	$("#newPswd").val("");
	$("#surePswd").val("");
}

//创建一个layer出来
var layer;
layui.use('layer', function() {
	layer = layui.layer;
	
});

//确认修改密码的方法
function changePsd(){
	
	var oldPswd=$("#oldPswd").val();
	var newPswd=$("#newPswd").val();
	var surePswd=$("#surePswd").val();
	
	var oldPswd2 = oldPswd.replace(/(^\s*)|(\s*$)/g, '');// 去除空格;
    if (oldPswd2 == '' || oldPswd2 == undefined || oldPswd2 == null) {
    	 layer.alert("旧密码不能为空");
        return false;
    } 
    
    var newPswd2 = newPswd.replace(/(^\s*)|(\s*$)/g, '');// 去除空格;
    
    if(newPswd2.length<6){
    	layer.alert("新密码的值不能小于6位数");
    	return false;
    }
    
    
    if (newPswd2 == '' || newPswd2 == undefined || newPswd2 == null) {
			
    	layer.alert("请输入新密码");
			
        return false;
    } 
    
   
    
    var surePswd2 = surePswd.replace(/(^\s*)|(\s*$)/g, '');// 去除空格;
    
    if(surePswd2.length<6){
    	layer.alert("新密码的值不能小于6位数");
        return false;
    }
    
    
    if (surePswd2 == '' || surePswd2 == undefined || surePswd2 == null) {
    	layer.alert("请再次输入新密码");
		
        return false;
    } 
	
	if(newPswd2!=surePswd2){
		layer.alert("两次新密码请输入相同的");
		
		
		return false;
	}
	
	if(oldPswd2==surePswd2){
		layer.alert("新旧密码不可相同的");
		return false;
	}
		
		
	
	
	$.ajax({
	type:"post",
	url:"../changePsd.action",
	data:{
		oldPsd:oldPswd2,
		newPsd:surePswd2 	
	},
	dataType:"json",
	success:function(respData){
		 /*返回suc:表示修改成功
		 * oldErr:旧密码不存在
		 * err：插入日志失败*/
		console.log(respData.changPsdKey);
		if(respData.changPsdKey=="suc"){
			
			//这个实际没来得及弹出
			layer.alert('修改成功', {icon: 6,time:2000});
			
			setTimeout(function() {
				// 关闭弹出的html页面
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
			}, 2000);// 2秒
			
			/*//原来的窗体 跳到登录页面
			window.location.href="adminlogin.html";*/
			//新的窗体跳转 登录页面
			window.open('adminlogin.html');
			
			return false;
			
		}
		if(respData.changPsdKey=="oldErr"){
			layer.alert("修改失败，旧密码错误");
			
			setTimeout(function() {
				// 关闭弹出的html页面
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
			}, 2000);// 2秒
		
			return false;
			
		}

		if(respData.changPsdKey=="err"){
			layer.alert("修改失败，插入日志失败");
			
			setTimeout(function() {
				// 关闭弹出的html页面
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);
			}, 2000);// 2秒
			
			
			
			return false;
	
		}
		
		//登录成功，跳转到新的页面
		/*if(respData.loginback=="suc"){
			window.location.href="fileQuery.jsp";
		}
			*/	
		
	},
	
	error:function(respData){
		layer.alert("AJAX错误");
		
	}
	
	});
	
	
	
	
}

