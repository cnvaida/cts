<%@page import="delphi.netstudent.business.SpecializariPersistenceUtil"%>
<%@page import="delphi.netstudent.model.Specializari"%>
<%@page import="delphi.netstudent.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editare specializare</title>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/default/easyui.css" %>>
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
</head>
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
	
	int specializareID = 0;
	
	if (logat == true) {
		Admin currentStudent = (Admin) session.getAttribute("currentAdmin");
		if (currentStudent != null && request.getParameter("id") != null) {
			try {
				specializareID = Integer.parseInt(request.getParameter("id"));
			} catch (NumberFormatException e) {
				
			}
		} else {
			response.sendRedirect(application.getContextPath() + "/cpanel.jsp");
		}
		
		Specializari specializare = null;
		if (specializareID != 0) {
			specializare = SpecializariPersistenceUtil.getSpecializare(specializareID);
		} else {
			response.sendRedirect(application.getContextPath() + "/cpanel.jsp");
		}
		%>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="editareSpecializare">
				<input type="hidden" name="idSpecializare" value=<%= specializare.getId() %>>
				<p>Nume:</p>
				<input type="text" name="nume" class="easyui-validatebox" required="true" value=<%= specializare.getDenumire() %>>
				<input type="submit" value="Salvare" onclick="inchide();">
			</form>
		<% 
	} else {
		response.sendRedirect(application.getContextPath() + "/cpanel.jsp");
	}
%>
</body>
</html>