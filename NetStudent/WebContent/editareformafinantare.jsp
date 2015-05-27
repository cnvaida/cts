<%@page import="delphi.netstudent.business.FormaFinantarePersistenceUtil"%>
<%@page import="delphi.netstudent.model.FormaFinantare"%>
<%@page import="delphi.netstudent.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editare forma finantare</title>
</head>
<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/icon.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/demo.css" %>>
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.min.js" %>></script>
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.easyui.min.js" %>></script>
	<script>
		function inchide() {
			window.close();
			window.opener.location.reload();
		}
	</script>
<body>
<%
	boolean logat = false;
	Cookie prajituri[] = request.getCookies(); 
	if (prajituri != null) {
		for (Cookie cook : prajituri) {
			if (cook.getName().equalsIgnoreCase("loggedAdmin")) {
				logat = true;
			}	
		}
	}
	
	int formaFinantareID = 0;
	
	if (logat == true) {
		Admin currentStudent = (Admin) session.getAttribute("currentAdmin");
		if (currentStudent != null && request.getParameter("id") != null) {
			formaFinantareID = Integer.parseInt(request.getParameter("id"));
		} else{
			response.sendRedirect(application.getContextPath() + "/adminlogin.jsp");
		}
	FormaFinantare formaFinantare = FormaFinantarePersistenceUtil.getFormaFinantare(formaFinantareID);
		%>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="editareFormaFinantare">
				<input type="hidden" name="idFormaFinantare" value=<%= formaFinantare.getId() %>>
				<p>Denumire</p>
				<input type="text" class="easyui-validatebox" required="true" name="nume" value=<%= formaFinantare.getDenumire() %>>
				<input type="submit" value="Salvare" onclick="inchide();">
			</form>
		<%
	} else {
		response.sendRedirect(application.getContextPath() + "/adminlogin.jsp");		
	}
%>
</body>
</html>