<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'menu.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=path %>/css/base.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/css/menu.css" type="text/css" />
	<style type="text/css">
	    div {
			padding:0px;
			margin:0px;
		}
		
		body {
		 scrollbar-base-color:#226CC5;
		 scrollbar-arrow-color:#FFFFFF;
		 scrollbar-shadow-color:#c1ea8b;
		 padding:0px;
		 margin:auto;
		 text-align:center;
		 background-color:#226cc5;
		}
		
		dl.bitem {
			width:148px;
			margin:0px 0px 5px 4px;
		}
		
		dl.bitem dt {
		  background:url(<%=path %>/images/menubg1.gif);
		  height:26px;
		  line-height:26px;
		  text-align:center;
		  cursor:pointer;
		}
		
		dl.bitem dd {
		  padding:3px 3px 3px 3px;
		  background-color:#fff;
		}
		
		.fllct
		{
			float:left;
			
			width:90px;
		}
		
		.flrct
		{
			padding-top:3px;
			float:left;
		}
		
		div.items
		{
			line-height:22px;
			background:url(<%=path %>/images/arr4.gif) no-repeat 10px 9px;
		}
		
		span.items
		{
			padding:10px 0px 10px 22px;
			background:url(<%=path %>/images/arr4.gif) no-repeat 10px 12px;
		}
		
		ul {
		  padding-top:3px;
		}
		
		li {
		  height:22px;
		}
		
		.sitemu li {
			padding:0px 0px 0px 22px;
			line-height:24px;
			background:url(<%=path %>/images/arr4.gif) no-repeat 10px 9px;
		}
	</style>
	<script language='javascript'>var curopenItem = '1';</script>
	<script language="javascript" type="text/javascript" src="<%=path %>/js/menu.js"></script>
	<base target="main" />
  </head>
  
  <body target="main">
	<table width='99%' height="100%" border='0' cellspacing='0' cellpadding='0'>
	  <tr>
	    <td style='padding-left:3px;padding-top:8px' valign="top">
	      <!-- 1 -->
	      <dl class='bitem'>
	        <dt onClick='showHide("items1_1")'><b>系统管理</b></dt>
	        <dd style='display:block' class='sitem' id='items1_1'>
	          <ul class='sitemu'>
	              <li><a href='<%=path %>/index/sysPro.jsp' target='main'>系统属性</a> </li>
	              <!-- <li><a href='<%=path %>/admin/userinfo/userPw.jsp' target='main'>密码修改</a> </li> -->
	         
  <li> <a href="#" onClick="javascript:parent.parent.location='<%=path %>/login.jsp';" >安全退出</a></li>
	          </ul>
	        </dd>
	      </dl>
	      <!-- 1 -->
	      <!-- 1 -->
	      <dl class='bitem'>
	        <dt onClick='showHide("items1_1")'><b>养生信息管理</b></dt>
	        <dd style='display:block' class='sitem' id='items1_1'>
	          <ul class='sitemu'>
	            <li><a href="/FoodAppService/servlet/GridServlet?Action=getlist&currentpage=0" target="main" >养生资料管理</a> </li>
	             
	             
	              
	          </ul>
	        </dd>
	      </dl>
	      <!-- 1 -->
	     
	       <!-- 1 -->
	      <dl class='bitem'>
	        <dt onClick='showHide("items1_2")'><b>用户管理</b></dt>
	        <dd style='display:block' class='sitem' id='items1_1'>
	          <ul class='sitemu'>
	         
	              <li><a href="/FoodAppService/servlet/GridServlet?Action=getuserlist&currentpage=0" target="main" >用户信息管理</a> </li>
	             
	          </ul>
	        </dd>
	      </dl>
	      <!-- 1 -->
	      
	      <!-- 1 -->
	      <dl class='bitem'>
	        <dt onClick='showHide("items1_4")'><b>养生馆管理</b></dt>
	        <dd style='display:block' class='sitem' id='items1_1'>
	          <ul class='sitemu'>
	           
	              <li><a href="/FoodAppService/servlet/GridServlet?Action=getshoplist&currentpage=0" target="main" >养生馆管理</a></li>
	             
	              
	          </ul>
	        </dd>
	      </dl>
	      <!-- 1 -->
	       <!-- 1 -->
	      <dl class='bitem'>
	        <dt onClick='showHide("items1_4")'><b>收藏管理</b></dt>
	        <dd style='display:block' class='sitem' id='items1_1'>
	          <ul class='sitemu'>
	            
	              <li><a href="/FoodAppService/servlet/GridServlet?Action=getcollectlist&currentpage=0" >收藏管理</a></li>
	             
	              
	          </ul>
	        </dd>
	      </dl>
	      <!-- 1 -->
	       <!-- 1 -->
	      <dl class='bitem'>
	        <dt onClick='showHide("items1_4")'><b>类型管理</b></dt>
	        <dd style='display:block' class='sitem' id='items1_1'>
	          <ul class='sitemu'>
	            
	              <li><a href="/FoodAppService/servlet/GridServlet?Action=gettypelist&currentpage=0" target="main" >类型管理</a></li>
	             
	              
	          </ul>
	        </dd>
	      </dl>
	      <!-- 1 -->
	        <!-- 1 -->
	      <dl class='bitem'>
	        <dt onClick='showHide("items1_4")'><b>症状管理</b></dt>
	        <dd style='display:block' class='sitem' id='items1_1'>
	          <ul class='sitemu'>
	             <li><a href="<%=path %>/zhenz/zhenzAdd.jsp" target="main" >症状添加</a></li>
	              <li><a href="<%=path %>/zhenz?type=zhenzMana" target="main" >症状管理</a></li>
	             
	              
	          </ul>
	        </dd>
	      </dl>
	      <!-- 1 -->
	      
	      
        

         
	      
		  </td>
	  </tr>
	</table>
  </body>
</html>
