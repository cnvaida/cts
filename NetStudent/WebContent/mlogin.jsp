<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">  
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Net Student</title>  
    <link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/gray/easyui.css"%>>  
    <link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/icon.css"%>>  
    <script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.min.js"%>></script>  
    <script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.easyui.min.js"%>></script> 
    <style type="text/css">
    	
    </style>
</head>
<body>
    <div class="easyui-panel" title="Net Student" data-options="fit:true,border:false" style="font-family: arial;">
        <div style="text-align:center;margin:20px;overflow:hidden">
            <p style="font-family: arial;">Logare student</p>
        </div>
        <div style="padding:0 20px">
        	<form action=<%= application.getContextPath() + "/login.do" %> method="post">
        		<input type="hidden" name="mobil" value="mobil">
	            <div class="m-item" style="border-bottom:1px solid #eee; font-family: arial;">
	                <div class="m-label">Utilizator</div>
	                <div><input class="m-input" placeholder="Cod student" type="text" name="nume"></div>
	            </div>
	            <div class="m-item">
	                <div class="m-label" style="font-family: arial;">Parolă</div>
	                <div><input class="m-input" type="password" placeholder="Introduceți parola" type="text" name="parola"></div>
	            </div>
	            <div style="text-align:center;margin-top:30px;font-family: arial;">
	                <input type="submit" value="Login" style="width: 40%;">
	            </div>
            </form>
        </div>
        <% 
    		if (session.getAttribute("eroare_login") != null) {
    			out.print("<span style=\"color: red\">" + session.getAttribute("eroare_login") + "</span>");
    			session.removeAttribute("eroare_login");
    		}
    	%>
    </div>
    <style scoped>
        .panel-title{
            text-align:center;
            font-size:14px;
            font-weight:bold;
            text-shadow:0 -1px rgba(0,0,0,0.3);
        }
        .m-item{
            height:30px;
            line-height:30px;
            padding:10px;
            background:#fff;
            color:#000;
        }
        .m-label{
            float:left;
            width:100px;
            font-size:16px;
        }
        .m-input{
            height:30px;
            line-height:30px;
            font-size:16px;
            border:0;
            width:150px;
        }
    </style>
</body>        
</html>