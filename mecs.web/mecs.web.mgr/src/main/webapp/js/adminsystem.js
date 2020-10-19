$().ready(function() {
	getMenuAndAdmin();
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
function getMenuAndAdmin() {
	$.ajax({
		type : "POST", // 提交方式
		url : "../getMenuAndAdmin.action", // 路径
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
				showMenu(respData);
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
function showMenu(respData) {
	/*
	 * 显示个人信息
	 */
	 var admin = respData.admin;
	 $("#adminName").append(admin.adminName);

	/*
	 * 画左边表格
	 */
	/*
	 * 将体检项目信息加入到打印单的表格中
	 */
	var ul = $("#nav");
	ul.html("");
	var menuList = respData.menuList;
	for (var j = 0; j < menuList.length; j++) {
		var menu = menuList[j];
		var li = $("<li></li>");
		var a = $("<a href=\"javascript:;\"></a>");
		var i = $("<i class=\"iconfont left-nav-li layui-icon " + menu.menuIcon + "\" lay-tips=" + menu.menuName + "></i>");
		var cite = $("<cite>" + menu.menuName+ "</cite>");
		var i1 = $("<i class=\"iconfont nav_right\">&#xe697;</i>");
		
		a.append(i);
		a.append(cite);
		a.append(i1);
		
		li.append(a);
		
		var ul1 = $("<ul class=\"sub-menu\"></ul>");
		for(var k = 0; k < menu.menuSet.length; k++){
			var menu2 = menu.menuSet[k];
			var li1 = $("<li></li>");
			var a1 = $("<a onclick=\"xadmin.add_tab('"+ menu2.menuName +"','" + menu2.menuUrl + "')\"></a>");
			var i3 = $("<i class=\"layui-icon " + menu2.menuIcon + " \"></i>");
			var i2 = $("<i class=\"iconfont \">&#xe6a7;</i>");
			var cite1 = $("<cite>" + menu2.menuName + "</cite>");
			
			a1.append(i3);
			a1.append(i2);
			a1.append(cite1);
			
			li1.append(a1);
			
			ul1.append(li1);
			
			li.append(ul1);
			
			ul.append(li);
		}
	}
}

/*
 * 去修改密码
 */
function jumpChangePassword(){
	layer.open({
		type : 2,
		title : "修改密码",
		skin : 'layui-layer-rim', // 加上边框
		area : [ '550px', '400px' ], // 宽高
		offset: 'c',//底部坐标
		content : 'changePassword.html',
		/*end:function(){
			
			//跳到登录页面
			window.location.href="adminlogin.html";
			
		},*/
		
	});
}
