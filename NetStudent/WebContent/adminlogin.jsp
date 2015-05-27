<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Log in</title>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/stil.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/gray/easyui.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/icon.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/demo.css" %>>
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.min.js" %>></script>
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.easyui.min.js" %>></script>
	<style type="text/css">
		a:ACTIVE {
			color: black;
			text-decoration: none;
			
		}
		
		a:HOVER {
			color: black;
			text-decoration: none;
			
		}
		
		a:VISITED {
			color: black;
			text-decoration: none;
			
		}	
		
		a:LINK {
			color: black;
			text-decoration: none;
			
		}
	</style>
</head>
<%
	
%>
<body>

<div id="win" class="easyui-window" title="Conectare administrator" collapsible="false" minimizable="false" closable="false" maximizable="false" resizable="false" style="width:230px;height:215px;">
	<form action=<%= application.getContextPath() + "/admin.do" %> method="post" style="padding:3px 45px 10px 40px;">
		<input type="hidden" name="action" value="adminLogin">
    	<% 
    		if (session.getAttribute("eroare_login") != null) {
    			out.print("<span style=\"color: red\">Utilizator/parolă greșite!</span>");
    			session.removeAttribute("eroare_login");
    		}
    	%>
    	<p>Nume:<br /><input class="easyui-validatebox" type="text" name="nume" required="true"></p>
        	
        <p>Parolă:<br /><input type="password" class="easyui-validatebox" name="parola" required="true"></p>
        	
        <div style="padding:5px;text-align:center;">
            <input type="submit" value="Conectare">
        </div>
    </form>
    <a href=<%= application.getContextPath() + "/index.jsp" %>>&#8592; Înapoi</a>
</div>

</body>
</html>