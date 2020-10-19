//1-20行，清明加
var publickey1;//rsa加密公钥
var publickey2;
$().ready(function (){
	//发送ajax获得rsa公钥
	$.ajax({
		type:"post",
		url:"../getPublicKey.action",
		data:{
		},
		dataType:"json",
		success:function(respData){
			publickey1 = respData.exponent;
			publickey2 = respData.modulus;
		},
		
		error:function(respData){
			
		}
		
	});
})



  //点击验证码刷新二维码 
		
	function chageImage(obj){
		obj.src="../getKeyCode.action?time="+Math.random();
	}


//登录按钮挂事件
function doLogin(){
	var adminName= $("#adminName").val();
	var passWord= $("#passWord").val();
	var code= $("#code").val();
	
	if(adminName==null || adminName==""){
	
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.alert('账号不能为空');
		});
		
		//刷新验证码，必须用dom获得img对象，jq会失效
		var img = document.getElementById('codeImg');
		chageImage(img);
		
		return;
	}
	if(passWord==null || passWord==""){
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.alert('密码不能为空');
		});
		
		//刷新验证码
		var img = document.getElementById('codeImg');
		chageImage(img);
		
		return;
	}
	if(code==null || code==""){
		
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.alert('验证码不能为空');
		});
		
		//刷新验证码
		var img = document.getElementById('codeImg');
		chageImage(img);
		
		return;
	}
	//对密码进行rsa加密， 清明加
	var pwdKey = new RSAUtils.getKeyPair(publickey1,"",publickey2);
	var reversedPwd = passWord.split("").reverse().join("");
	passWord = RSAUtils.encryptedString(pwdKey,reversedPwd);
	
	$.ajax({
		type:"post",
		url:"../do-login.action",
		data:{
			adminAcc:adminName,
			adminPswd:passWord,
			keyCode:code	
		},
		dataType:"json",
		success:function(respData){
			
			//alert("js得到的loginback="+respData.loginback);
	
			
			//登录成功，跳转到新的页面
			if(respData.loginback=="suc"){
				window.location.href="../view/adminsystem.html";
			}
			//账号被禁用
			if(respData.loginback=="forb"){
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.alert('账号被禁用了');
				});
			
				//刷新验证码
				var img = document.getElementById('codeImg');
				chageImage(img);
			}
			//账号或者密码错误					
			if(respData.loginback=="err"){
				
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.alert('账号或者密码错误');
				});
			
				//刷新二维码
				var img = document.getElementById('codeImg');
				chageImage(img);
			}
			//验证码错误
			if(respData.loginback=="codeErr"){
				
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.alert('验证码错误,请重新输入');
				});
				
				//刷新二维码
				var img = document.getElementById('codeImg');
				chageImage(img);
			}
			//输入框有空的时候。
			if(respData.loginback=="haveNull"){
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.alert('输入框不能为空,请重新输入');
				});
			
				//刷新二维码
				var img = document.getElementById('codeImg');
				chageImage(img);
			}
								
			/*//判断Struts2传回来的值
			//"账号登录成功. JSONObject 存list的值，[0]
			if(respData.loginSucJSKey!=null&&respData.loginSucJSKey[0]!=null&&"loginSuc"==respData.loginSucJSKey[0]){
				alert("账号登录成功+正确为loginSuc="+respData.loginSucJSKey[0]);
				window.location.href="fileQuery.jsp";
			}
			//如果是账号或者密码错误accountErrOrPsdErr
			//var s1=respData.accountErrOrPsdErrMapkeyJSONKEy.accountErrOrPsdErrMapkey;
			//alert("if之前的：账号或者密码错误+正确为accountErrOrPsdErr s1="+s1);
			if(respData.accountErrOrPsdErrMapkeyJSONKEy!=null&&respData.accountErrOrPsdErrMapkeyJSONKEy.accountErrOrPsdErrMapkey!=null&&"accountErrOrPsdErr"==respData.accountErrOrPsdErrMapkeyJSONKEy.accountErrOrPsdErrMapkey){
				alert("账号或者密码错误+正确为accountErrOrPsdErr s1="+respData.accountErrOrPsdErrMapkeyJSONKEy.accountErrOrPsdErrMapkey);
			
			}
			//如果是账号被禁用了accountBan ,存的是list
			if(respData.accountBanJSkey!=null&&respData.accountBanJSkey[0]!=null&&"accountBan"==respData.accountBanJSkey[0]){
				alert("如果是账号被禁用了+正确为accountBan s2="+respData.accountBanJSkey[0]);
			}
			
			//验证码错误 codeErr，存的是map
			
			if(respData.JsonCodeErrMapKey!=null&&respData.JsonCodeErrMapKey.codeErrMapKey!=null&&"codeErr"==respData.JsonCodeErrMapKey.codeErrMapKey){
				alert("验证码错误 正确为codeErr="+respData.JsonCodeErrMapKey.codeErrMapKey);
			}
			//如果有一个空haveNull
			//var s5=respData.haveNullJsonKey;
			if(respData.haveNullJsonKey!=null&&"haveNull"==respData.haveNullJsonKey){
				alert("所有的输入框都不能为空haveNull="+respData.haveNullJsonKey);
			}*/
		},
		
		error:function(respData){
			alert("登录的时候AJAX系统错误");
		}
		
	});
	

}