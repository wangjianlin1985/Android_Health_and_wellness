<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="style/PublicStyle.css" type="text/css"></link>
<script type="text/javascript" src="Script/jquery-1.4.4.js">
</script>
<script type="text/javascript">
function fndelete(id) {

	if (confirm("确定删除当前记录吗？")) {
		$.get("servlet/ServletService?Ran=" + Math.random(), {
			Action : "Del",
			ID : id,
			Table : "orders"
		}, function(data) {
			if (data == 1) {
				alert("恭喜您,删除成功！");
				location.href = location.href;
			} else {
				alert("对不起,删除失败,请稍后再试！");
			}

		});
	}
}
function changestatus(id,status) {
		$.get("servlet/ServletService?Ran=" + Math.random(), {
			Action : "ChangeStatus",
			ID : id,
			status : status
		}, function(data) {
			if (data == 1) {
				alert("恭喜您,操作成功！");
				location.href = location.href;
			} else {
				alert("对不起,操作失败,请稍后再试！");
			}

		});
	
}

function search100() {
	if ($("#txtkeyword").val() == "") {
		location.href = "/FoodAppService/servlet/GridServlet?Action=getuserlist&currentpage=0";
	} else {
         location.href = "/FoodAppService/servlet/GridServlet?Action=getuserlist&currentpage=0"+ "&msg="
				+ encodeURI($("#txtkeyword").val());	
	}
}
</script>

</head>
<body>
	<div class="backbody" style="background: #FFF;">

		

		<div style=" padding:10px;">

			<div style="padding: 15px 5px 10px 0;">
<table class="SearchTable" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td>
								姓名：
							</td>
							<td>
								<input type="text" class="textbox" id="txtkeyword" />
							</td>
							<td>
								<input type="button" class="btnClass_79px_A" value="搜索"
									onclick="javascript:search100();" />
							</td>
							<td>
								<input type="button" class="btnClass_79px_A" value="添加" style="display: none;"
									onclick="javascript:location.href='/FoodAppService/userinfo.jsp'" />

							</td>
						</tr>
					</table>
				<table width="940px;" class="GridTable" border="0" cellspacing="0"
					cellpadding="0" id="GridTableID">

					<tr>
						<th>姓名</th>
						<th>收藏名店</th>
						<th>收藏时间</th>
					
						
						<th class="right">删除</th>
					</tr>
					<c:forEach var="data" items="${datalist}">
						<tr>


							<td>${data.name}</td>
							<td>${data.shopname}</td>
							<td>${data.createtime}</td>
							
							
							<td class="right"><input id='${data.id}'
								onclick='javascript:fndelete(${data.id})' title="删除"
								type="button" value="" class="btnDel  btnGrid"
								style="background: url(images/delete.gif)" /></td>
						</tr>
					</c:forEach>
				</table>
				<div style="margin-top: 5px;">
					<a href="servlet/GridServlet?Action=getuserlist&currentpage=${1}">首页</a>
					<a
						href="servlet/GridServlet?Action=getuserlist&currentpage=${currentpage-1}">上一页</a>
					<a
						href="servlet/GridServlet?Action=getuserlist&currentpage=${currentpage+1}">下一页</a>
					<a
						href="servlet/GridServlet?Action=getuserlist&currentpage=${pagecount}">尾页</a>
					当前页是第${currentpage}/${pagecount}页,共有${total}条记录
				</div>
			</div>
		</div>
	</div>
</body>
</html>
