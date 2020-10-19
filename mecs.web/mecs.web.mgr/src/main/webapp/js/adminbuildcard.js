

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
						elem : '#date1',//生日日期
						format: 'yyyy-MM-dd', //可任意组合
						trigger: 'click',
						max:maxDate()//调用下面的方法
					});

					// 日期
					laydate.render({
						elem : '#date2'
					});
					laydate.render({
						elem : '#date3'
					});

				});


//设置最大可选的日期
function maxDate(){
    var now = new Date();
    return now.getFullYear()+"-" + (now.getMonth()+1) + "-" + now.getDate();
 }

//预加载
(function ($) {
	quaryCardNumber();
	
})(jQuery);
var layer;
layui.use('layer', function() {
	layer = layui.layer;
	
});
/*//日期控件
layui.use([ 'form', 'layedit', 'laydate' ],
function() {
	var form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;

	// 日期
	laydate.render({
		elem : '#userBirthday',
		format: 'yyyy-MM-dd' //可任意组合
		//trigger: 'click',
		//max:maxDate()//调用下面的方法
	});
	
	
	laydate.render({
		elem : '#date1',
		format: 'yyyy-MM-dd', //可任意组合
		trigger: 'click',
		max:maxDate()//调用下面的方法
	});
	
});*/





//form表单  输入框问题
layui.use('form', function(){
  var form = layui.form;
  //各种基于事件的操作，下面会有进一步介绍
 
  form.on('select(province)', function(data){
		 /* console.log(data.elem); //得到select原始DOM对象
		  console.log(data.value); //得到被选中的值
		  console.log(data.othis); //得到美化后的DOM对象*/	
	   
	  	var pro = document.getElementById("province");
		var city = document.getElementById("city");
		
		//selectedIndex 属性可设置或返回下拉列表中被选选项的索引号，从上往下，从0开始
		var index = pro.selectedIndex;
		
		//把选择的选择的省的市，组成一个新的数组，
		var cityArr = arr[index].split(",");
		
		//清空下拉款的值
		city.length = 0;
		// 将城市数组中的值填充到城市下拉框中
		
		for (var i = 0; i < cityArr.length; i++) {
			//alert(cityArr[i]);
			//city[i] = new Option(cityArr[i], cityArr[i]);
			
			city.options.add(new Option(cityArr[i],cityArr[i]));
		}
		form.render(); 
  
  	}); 


});



// 之前不是layui 的时候，籍贯的开始----------------------------------------

var arr = new Array();
arr[0] = "东城,西城,崇文,宣武,朝阳,丰台,石景山,海淀,门头沟,房山,通州,顺义,昌平,大兴,平谷,怀柔,密云,延庆"
arr[1] = "黄浦,卢湾,徐汇,长宁,静安,普陀,闸北,虹口,杨浦,闵行,宝山,嘉定,浦东,金山,松江,青浦,南汇,奉贤,崇明"
arr[2] = "和平,东丽,河东,西青,河西,津南,南开,北辰,河北,武清,红挢,塘沽,汉沽,大港,宁河,静海,宝坻,蓟县"
arr[3] = "万州,涪陵,渝中,大渡口,江北,沙坪坝,九龙坡,南岸,北碚,万盛,双挢,渝北,巴南,黔江,长寿,綦江,潼南,铜梁,大足,荣昌,壁山,梁平,城口,丰都,垫江,武隆,忠县,开县,云阳,奉节,巫山,巫溪,石柱,秀山,酉阳,彭水,江津,合川,永川,南川"
arr[4] = "石家庄,邯郸,邢台,保定,张家口,承德,廊坊,唐山,秦皇岛,沧州,衡水"
arr[5] = "太原,大同,阳泉,长治,晋城,朔州,吕梁,忻州,晋中,临汾,运城"
arr[6] = "呼和浩特,包头,乌海,赤峰,呼伦贝尔盟,阿拉善盟,哲里木盟,兴安盟,乌兰察布盟,锡林郭勒盟,巴彦淖尔盟,伊克昭盟"
arr[7] = "沈阳,大连,鞍山,抚顺,本溪,丹东,锦州,营口,阜新,辽阳,盘锦,铁岭,朝阳,葫芦岛"
arr[8] = "长春,吉林,四平,辽源,通化,白山,松原,白城,延边"
arr[9] = "哈尔滨,齐齐哈尔,牡丹江,佳木斯,大庆,绥化,鹤岗,鸡西,黑河,双鸭山,伊春,七台河,大兴安岭"
arr[10] = "南京,镇江,苏州,南通,扬州,盐城,徐州,连云港,常州,无锡,宿迁,泰州,淮安"
arr[11] = "杭州,宁波,温州,嘉兴,湖州,绍兴,金华,衢州,舟山,台州,丽水"
arr[12] = "合肥,芜湖,蚌埠,马鞍山,淮北,铜陵,安庆,黄山,滁州,宿州,池州,淮南,巢湖,阜阳,六安,宣城,亳州"
arr[13] = "福州,厦门,莆田,三明,泉州,漳州,南平,龙岩,宁德"
arr[14] = "南昌市,景德镇,九江,鹰潭,萍乡,新馀,赣州,吉安,宜春,抚州,上饶"
arr[15] = "济南,青岛,淄博,枣庄,东营,烟台,潍坊,济宁,泰安,威海,日照,莱芜,临沂,德州,聊城,滨州,菏泽"
arr[16] = "郑州,开封,洛阳,平顶山,安阳,鹤壁,新乡,焦作,濮阳,许昌,漯河,三门峡,南阳,商丘,信阳,周口,驻马店,济源"
arr[17] = "武汉,宜昌,荆州,襄樊,黄石,荆门,黄冈,十堰,恩施,潜江,天门,仙桃,随州,咸宁,孝感,鄂州"
arr[18] = "长沙,常德,株洲,湘潭,衡阳,岳阳,邵阳,益阳,娄底,怀化,郴州,永州,湘西,张家界"
arr[19] = "广州,深圳,珠海,汕头,东莞,中山,佛山,韶关,江门,湛江,茂名,肇庆,惠州,梅州,汕尾,河源,阳江,清远,潮州,揭阳,云浮"
arr[20] = "南宁,柳州,桂林,梧州,北海,防城港,钦州,贵港,玉林,南宁地区,柳州地区,贺州,百色,河池"
arr[21] = "海口,三亚"
arr[22] = "成都,绵阳,德阳,自贡,攀枝花,广元,内江,乐山,南充,宜宾,广安,达川,雅安,眉山,甘孜,凉山,泸州"
arr[23] = "贵阳,六盘水,遵义,安顺,铜仁,黔西南,毕节,黔东南,黔南"
arr[24] = "昆明,大理,曲靖,玉溪,昭通,楚雄,红河,文山,思茅,西双版纳,保山,德宏,丽江,怒江,迪庆,临沧"
arr[25] = "拉萨,日喀则,山南,林芝,昌都,阿里,那曲"
arr[26] = "西安,宝鸡,咸阳,铜川,渭南,延安,榆林,汉中,安康,商洛"
arr[27] = "兰州,嘉峪关,金昌,白银,天水,酒泉,张掖,武威,定西,陇南,平凉,庆阳,临夏,甘南"
arr[28] = "银川,石嘴山,吴忠,固原"
arr[29] = "西宁,海东,海南,海北,黄南,玉树,果洛,海西"
arr[30] = "乌鲁木齐,石河子,克拉玛依,伊犁,巴音郭勒,昌吉,克孜勒苏柯尔克孜,博 尔塔拉,吐鲁番,哈密,喀什,和田,阿克苏"
arr[31] = "香港"
arr[32] = "澳门"
arr[33] = "台北,高雄,台中,台南,屏东,南投,云林,新竹,彰化,苗栗,嘉义,花莲,桃园,宜兰,基隆,台东,金门,马祖,澎湖"

init();

function init() {
	var city = document.getElementById("city");
	//获得北京下面的一级，组成新的数组
	var cityArr = arr[0].split(",");
	//把北京下面的所有的区加入到选择框的值
	for (var i = 0; i < cityArr.length; i++) {
		city[i] = new Option(cityArr[i], cityArr[i]);
	}
}

/*修改掉了
 * function getCity() {
	var pro = document.getElementById("province");
	var city = document.getElementById("city");
	
	//selectedIndex 属性可设置或返回下拉列表中被选选项的索引号，从上往下，从0开始
	var index = pro.selectedIndex;
	
	//把选择的选择的省的市，组成一个新的数组，
	var cityArr = arr[index].split(",");
	
	//清空下拉款的值
	city.length = 0;
	// 将城市数组中的值填充到城市下拉框中
	for (var i = 0; i < cityArr.length; i++) {
		city[i] = new Option(cityArr[i], cityArr[i]);
	}
	
	
	//动态添加：obj.options.add(new Option(“text”,“value”));
    //动态清空：obj.options.length = 0;
}
*/
//籍贯的结束----------------------------------------

//查数据库中有的一个卡号
function quaryCardNumber(){

	$.ajax({
		type:"post",
		url:"../backCardNumber",
		data:{
			
		},
		dataType:"json",
		success:function(respData){
			
			$("#cardIdShow").html(respData.backCardNumber);
			
			//登录成功，跳转到新的页面
			/*if(respData.loginback=="suc"){
				window.location.href="fileQuery.jsp";
			}
				*/	
			
		},
		
		error:function(respData){
			alert("AJAX错误");
		}
		
	});
}


//清空 input 输入框的值
function cleanbigDiv(){
	$('#bigDiv input').val("");
	//清空下拉框
	$('#userSex').val("");
	//血型
	$('#userBloodType').val("0");
	//清空文本域
	$('#textareaID').val("");
	
}

//计算出年龄
function getAge(BirthdayAge){
	//截取前4位，也就是截取出年份
	var briYear=BirthdayAge.substr(0,4);
	var myDate=new Date();
	var nowYear=myDate.getFullYear();
	var ageNumber=nowYear-briYear;
	//如果是当年出生的相减等于0，默认修改成1
	if(ageNumber==0){
		ageNumber=1;
	}
	
	return ageNumber;
}

//提交的按钮挂事件 
function buildCardCommit(){
	var cardNum=$("#cardNum").val();
	var cardMoney=$("#cardMoney").val();
	var userName=$("#userName").val();
	//var userAge=$("#userAge").val();
	var userSex=$("#userSex").val();
	var userBloodType=$("#userBloodType").val();
	var userBirthday=$("#date1").val();
	//alert("生日="+userBirthday);
	var userAge=getAge(userBirthday);
	//alert("nianl="+userAge);
	var userPhone=$("#userPhone").val();
	var userAddress=$("#userAddress").val();
	var userMemo=$("#textareaID").val();
	
	
	
	//获得籍贯：
	var province=$("#province").find("option:selected").text();
	var city=$("#city").val();
	var layer = layui.layer;
	//alert("66="+city);
	if(province==""){
		//alert("籍贯，城市不能为空"); 这个实际用不到
		
	  			
	  			layer.msg('籍贯，城市不能为空', {
	  				icon : 2
	  			});
		 
	}
	var userNativePlace=province+city;
	

	
	//输入款规范判断，判断所有输入款的是否为非空
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

  //02.金额
	var cardMoney2 = cardMoney.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
    if (cardMoney2 == '' || cardMoney2 == undefined || cardMoney2 == null) {
    	   
			layer.msg('充值金额不能为空', {
				icon : 2
			});
		
        return false;
    } 
    
    if(isNaN(cardMoney2)){
    	layer.msg('充值金额只能为数字', {
			icon : 2
		});
    }
    
   
    
  //03.姓名
	var userName2 = userName.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
    if (userName2 == '' || userName2 == undefined || userName2 == null) {
    	layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('姓名不能为空', {
				icon : 2
			});
		});
        return false;
    } 
    
    /**
     * 只包含中文和英文
     * @param cs
     * @returns {Boolean}
     */  
        var regu = "^[a-zA-Z\u4e00-\u9fa5]+$";
        var re = new RegExp(regu);
        if (userName2.search(re) != -1){
          /*return true;*/
        } else {
        	
        	layer.msg('姓名只能输入中文和英文', {
				icon : 2
			});
       
          return false;
        }
    
  //04.userAge 年龄判断   input 有<!--  限制只能输入3位数字 -->
	/*var userAge2 = userAge.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
    if (userAge2 == '' || userAge2 == undefined || userAge2 == null) {
    	layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('年龄不能为空', {
				icon : 2
			});
		});
        return false;
    } 
    */
  //05.性别  userSex 
	var userSex2 = userSex.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
    if (userSex2 == '' || userSex2 == undefined || userSex2 == null) {
    	layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('性别不能为空', {
				icon : 2
			});
		});
        return false;
    } 
    
    
  //06.血型 userBloodType，血型可以为空
    var userBloodType2 = userBloodType.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
   
   //07 出生日期 userBirthday
    
    var userBirthday2 = userBirthday.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
    if (userBirthday2 == '' || userBirthday2 == undefined || userBirthday2 == null) {
    	layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('出生日期不能为空', {
				icon : 2
			});
		});
        return false;
    }
    
	var dB = new Date(userBirthday2.replace(/-/g, "/"));//获取选择选择日期
	var d = new Date();
	var str = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();//获取当前实际日期
	//现在当天之后的日期不能选择了，这个用不到
	if (Date.parse(str) < Date.parse(dB)) {//时间戳对比
		layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('出生日期必须小于当前时间', {
				icon : 2
			});
		});
	    return false;
	} 
    
   
  //08.userPhone  电话  ，input 有 <!--  限制输入11位数字-->
	var userPhone2 = userPhone.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
    if (userPhone2 == '' || userPhone2 == undefined || userPhone2 == null) {
    	layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('手机号不能为空', {
				icon : 2
			});
		});
        return false;
    } 
    
    
    //手机号码，规则判断
    var telStr = /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;  
    if (!(telStr.test(userPhone2))) {
    	layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('手机号码，格式错误，必须为11位的纯数字', {
				icon : 2
			});
		});

     // flag = '手机号码输入不规范';
    }else{
    	
	  //msg = "手机号码规范";
	}
	
    
    
  //09.userAddress 地址
	var userAddress2 = userAddress.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
    if (userAddress2 == '' || userAddress2 == undefined || userAddress2 == null) {
    	layui.use('layer', function() {
			var layer = layui.layer;
			layer.msg('地址不能为空', {
				icon : 2
			});
		});
        return false;
    } 
   
    //10 籍贯userNativePlace 不会为空,不用去空格
  
    
    //010.userMemo 备注 备注可以为空
    var userMemo2 = userMemo.replace(/(^\s*)|(\s*$)/g, '');//去除空格;

	$.ajax({
		type:"post",
		url:"../buildcard",
		data:{
			cardNum:cardNum2,
			money:cardMoney2,
			userName:userName2,
			userName:userName2,
			userAge:userAge,
			userSex:userSex2,
			userBloodType:userBloodType2,
			userBirthday:userBirthday2,
			userPhone:userPhone2,
			userAddress:userAddress2,
			userNativePlace:userNativePlace,
			userMemo:userMemo2	
		},
		dataType:"json",
		success:function(respData){
			
			/*返回的所有可能的值 key 是buildcardkey
			 * 
			 *  * withoutCardID没有这个卡号 ，
			 * buildCardPericeErr未设置开卡单价,
			 * MoneyErr充值金额小于开卡的钱，无法开卡
			 * buildUserErr：开卡失败，无法创建用户时，序列号
			 * buildUserErr2：开卡失败，无法创建用户
			 * notLogIn：收费员未先登录
			 * buildCardErr：建卡错误
			 * AccountErr：请重试插入 个人对账表的时候，错误
			 * logErr：插入日志失败
			 * suc：建卡成功
			 * 
			 * Err：建卡失败：调用service层失败
			 *
			 *InputHaveNull:除了血型，备注的输入框不能为空。*/
			
			//alert("js得到的buildcardkey="+respData.buildcardkey);
			
			//layui的弹框 
			if(respData.buildcardkey=="withoutCardID"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("没有这个卡号")
					}); 
				
			}
			
			if(respData.buildcardkey=="buildCardPericeErr"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("未设置开卡的价格");
					}); 
				
			}

			if(respData.buildcardkey=="MoneyErr"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("充值金额小于开卡金额，请重新充值");
					}); 
				
			}			
			
			if(respData.buildcardkey=="buildUserErr"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("开卡失败，无法创建用户时，序列号，请重试");
					}); 
				
			}			

			if(respData.buildcardkey=="buildUserErr2"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("开卡失败，无法创建用户");
					}); 
				
			} 
			
			if(respData.buildcardkey=="AccountErr"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("请重试插入 个人对账表的时候，错误");
					}); 
				
			}
			
			if(respData.buildcardkey=="notLogIn"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("收费员没有登录");
					}); 
				
			}

			if(respData.buildcardkey=="logErr"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("开卡失败，插入日志失败，重试下");
					}); 
				
			}			
			
			if(respData.buildcardkey=="Err"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("建卡失败：调用service层失败");
					}); 
				
			}			
			
			if(respData.buildcardkey=="suc"){
				
				layui.use('layer', function(){
					  var layer = layui.layer;
					  layer.alert("开卡成功");
					}); 
				//清空输入框数据
				cleanbigDiv();
			}
			
			
			/*//登录成功，跳转到新的页面
			if(respData.loginback=="suc"){
				window.location.href="fileQuery.jsp";
			}*/
					
			
		},
		
		error:function(respData){
			alert("AJAX错误");
		}
		
	});
  
    
}


