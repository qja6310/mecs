<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String id=request.getParameter("id"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>体检结果</title>
<link rel="stylesheet" href="../3rd/admin/css/font.css">
<link rel="stylesheet" href="../3rd/admin/css/xadmin.css">
<link rel="stylesheet" href="../3rd/layui/css/layui.css">
<script src="../3rd/admin/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="../3rd/admin/js/xadmin.js"></script>
</head>
<body >
					<div class="layui-inline" style="top: 4px;">
						<label class="layui-form-label" style="top: -2px;">体检结果：</label>
						 <br/>
						 <div class="layui-input-block" id='divn'>
						</div>
					</div>
</body>
<script src="../3rd/js/jquery.min.js"></script>
<script src="../js/peResult.js"></script>
</html>