<%@page import="delphi.netstudent.model.Materii"%>
<%@page import="delphi.netstudent.business.MateriiPersistenceUtil"%>
<%@page import="delphi.netstudent.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editare materie</title>
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
	
	int materieID = 0;
	
	if (logat == true) {
		Admin currentStudent = (Admin) session.getAttribute("currentAdmin");
		if (currentStudent != null && request.getParameter("id") != null) {
			materieID = Integer.parseInt(request.getParameter("id"));
		} else{
			response.sendRedirect(application.getContextPath() + "/adminlogin.jsp");
		}
	
	Materii materie = MateriiPersistenceUtil.getMaterie(materieID);
		%>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="editareMaterie">
				<input type="hidden" name="idMaterie" value=<%= materie.getId() %>>
				<p>Denumire</p>
				<input type="text" class="easyui-validatebox" required="true" name="nume" value=<%= materie.getDenumire() %>>
				<input type="submit" value="Salvare" onclick="inchide();">
			</form>
		<%
	} else {
		response.sendRedirect(application.getContextPath() + "/adminlogin.jsp");		
	}
%>
</body>
</html>