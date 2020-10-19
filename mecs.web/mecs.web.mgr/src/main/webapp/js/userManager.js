//日期控件
layui
		.use(
				[ 'form', 'layedit', 'laydate' ],
				function() {
					var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

					// 日期
					laydate.render({
						elem : '#date'
					});
					laydate.render({
						elem : '#date1'
					});

					laydate.render({
						elem : '#date2'
					});
					laydate.render({
						elem : '#date3'
					});

				});

// 定义每页的记录数
var limit = 0;
var count = "";
var currPage = 1;

// 定义全局变量
var beginTimeVal = "";
var endTimeVal = "";
var userNameVal = "";
var userTel = "";
var userNumber = "";

// 初始化页面
$(window).ready(function() {
	startAjax(1);
});

// 点击查询按钮的监听方法
$("#search").on("click", function() {
	beginTimeVal = $("#date1").val();
	endTimeVal = $("#date3").val();
	userNameVal = $("#userName").val();
	userTel = $("#tel").val();
	userNumber = $("#userNumber").val();
	
	var d1 = new Date(beginTimeVal.replace(/\-/g, "\/"));
	var d2 = new Date(endTimeVal.replace(/\-/g, "\/"));
	if (beginTimeVal != "" && endTimeVal != "" && d1 > d2) {
		layer.msg('开始时间不能大于结束时间', {icon: 2});
		return false;
	}
	
	startAjax(1);
});

$("#clean").on("click", function() {
	$("#date1").val("");
	$("#date3").val("");
	$("#userName").val("");
	$("#tel").val("");
	$("#userNumber").val("");
});

function startAjax(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../userQuery.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			startTime : beginTimeVal,
			endTime : endTimeVal,
			userName : userNameVal,
			userTel : userTel,
			number : userNumber
		},
		dataType : "json",
		success : function(respData) {

			var userList = respData.user;

			count = respData.count
			limit = respData.limit

			layui.use('laypage', function() {
				var laypage = layui.laypage;

				// 执行一个laypage实例
				laypage.render({
					elem : 'pageBtn',// 注意，这里的 test1 是 ID，不用加 # 号
					count : count,// 数据总数，从服务端得到
					limit : limit,
					curr : cur,
					groups : 3,
					jump : function(obj) {
						nextAjax(obj.curr);
					}

				});
			});
		},
		error : function() {
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败，请联系超管');
			});
		}
	});

}

function nextAjax(cur) {

	$.ajax({
		type : "post",/* 数据的提交方式，GET POST */
		url : "../userQuery.action",/* 访问路径action */
		data : {
			/* 需要提交到访问路径的参数，相当于表单中的表单元素以及表单元素的值 */
			nowPage : cur,
			startTime : beginTimeVal,
			endTime : endTimeVal,
			userName : userNameVal,
			userTel : userTel,
			number : userNumber
		},
		dataType : "json",/* 返回的数据类型，TEXT JSON(默认) */
		success : function(respData) {
			var userList = respData.user;
			count = respData.count;
			limit = respData.limit;
			initTale(userList);
		},
		error : function() {
			// alert("请求失败");
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败，请联系超管');
			});
		}
	});

}

function initTale(list) {

	var $mytable = $("#userTable");
	$mytable
			.html("<thead><tr class=\"layui-bg-cyan\"><th style=\"width: 15%\";>用户名</th><th style=\"width: 6%\";>性别</th><th style=\"width: 6%\";>血型</th><th style=\"width: 6%\";>年龄</th><th style=\"width: 10%\";>出生日期</th><th style=\"width: 10%\";>联系电话</th><th style=\"width: 15%\";>地址</th><th style=\"width: 15%\";>籍贯</th><th>注册时间</th></tr></thead>");

	if (list.length > 0) {

		for (var i = 0; i < list.length; i++) {
			var $tr = $("<tr></tr>");
			var $td1 = $("<td></td>");
			var $td2 = $("<td></td>");
			var $td3 = $("<td></td>");
			var $td4 = $("<td></td>");
			var $td5 = $("<td></td>");
			var $td6 = $("<td></td>");
			var $td7 = $("<td></td>");
			var $td8 = $("<td></td>");
			var $td9 = $("<td></td>");

			$td1.text(list[i].userName);
			$td2.text(list[i].userSex);
			$td3.text(list[i].userBloodType);
			$td4.text(list[i].userAge);
			var bir = list[i].userBirthday;
			var birthArray = bir.split(" "); 
			var birthDate = birthArray[0]; 
			$td5.text(birthDate);
			$td6.text(list[i].userPhone);
			$td7.text(list[i].userAddress);
			$td8.text(list[i].userNativePlace);
			$td9.text(list[i].userCtime);

			$tr.append($td1);
			$tr.append($td2);
			$tr.append($td3);
			$tr.append($td4);
			$tr.append($td5);
			$tr.append($td6);
			$tr.append($td7);
			$tr.append($td8);
			$tr.append($td9);

			$mytable.append($tr);

		}

	} else {
		var $tr = $("<tr></tr>");
		var $td = $("<td></td>");
		$td.attr("colSpan", 9);
		$td.text("查无数据");
		$td.attr("align", "center");
		$tr.append($td);
		$mytable.append($tr);
	}
}


	layui.config({// 配置并导入excel插件
		base: '../3rd/layui/layui_exts/'
	}).use([ 'excel', 'layer'], function() {
		
		var $ = layui.$;
		var layer = layui.layer;
		var excel = layui.excel;
		$('#export').on('click', function(){
		// 模拟从后端接口读取需要导出的数据
		$.ajax({
			url: '../userQuery.action'
			,dataType: 'json'
			,success(res) {
				
				var data = res.user;
				
				// 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
				data = excel.filterExportData(data, [
					'userName'
					 ,'userSex'
					 ,'userBloodType'
					 ,'userAge'
					 ,'userBirthday'
					 ,'userPhone'
					 ,'userAddress'
					 ,'userNativePlace'
					 ,'userCtime'
				]);
				// 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
				data.unshift({ userName: "用户名", userSex: "性别", userBloodType: '血型', userAge: '年龄', userBirthday:
					 '出生日期', userPhone: '电话', userAddress: '地址', userNativePlace: '籍贯', userCtime: '注册时间' });

				excel.exportExcel(data, '体检人信息' + '.xlsx', 'xlsx');
			}
			,error() {
				layer.alert('获取数据失败，请检查是否部署在本地服务器环境下');
			}
		});
	  }); 	
	});
	
