function back(){//弹框返回
	 layui.use('layer', function(){
		 var layer = layui.layer;
  layer.confirm('确定取消吗?取消后当前编辑的内容将会丢失！', {icon: 3, title:'提示'}, function(index){
	layer.close(index); 
	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	parent.layer.close(index); 
	 });

	 });
}
var index;
var layedit ;
layui.use('layedit', function(){
	layedit= layui.layedit;
	  layedit.set({
	  uploadImage: {
	    url: "../image.action", //接口url
	    type: 'post'
	  }
	});
	index =layedit.build('result',{
		  height: 375 ,//设置编辑器高度
		  tool: [
		  'strong' //加粗
		  ,'italic' //斜体
		  ,'left' //左对齐
		  ,'center' //居中对齐
		  ,'right' //右对齐
		  ,'image' //插入图片
		  ,'|' //分割线
		  ,'del' //删除线
		  ,'underline' //下划线
	]
	
	}); //建立编辑器
	});
function submit(){//提交小结
	//获取富文本编辑器的内容
	
	 var ret =layedit.getContent(index);
	 if (ret.length == 7 ){//未录入
		 layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('录入结果不能为空！', {
					time : 2000, // 10s后自动关闭
					area : '200px',
					icon : 2,
				});
			});
		 return;
	 }
	 layui.use('layer', function(){
		 var layer = layui.layer;
  layer.confirm('确定提交吗?提交后将不可更改！', {icon: 3, title:'提示'}, function(index){
	layer.close(index); 
	
	 addResult(ret);
	 
	 });

	 });
  
	
}
function addResult(ret){
	$.ajax({
		url : "../smallResult.action",
		type : "post",
		data : {
			result:ret
			
		},
		dataType : "json",
		success : function(respData) {
			if (respData.type == 'suc') {
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('录入成功！', {
						time : 1200, // 10s后自动关闭
						area : '200px',
                        icon : 1,
					});
//					parent.window.location.href='itemsReceive.html';
					setTimeout(function(){
						//关闭弹出的html页面
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index); 
						},2000);
				});
			
			}else{
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('录入失败！', {
						time : 2000, // 10s后自动关闭
						area : '200px',
                        icon : 2,
					});
				});
			}
		},
		error : function(respData){
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg('请求失败！', {
					time : 2000, // 2s后自动关闭
					area : '200px',
	                icon : 2,
				});
			});
			}
	})
}