<%@page import="delphi.netstudent.model.Specializari"%>
<%@page import="delphi.netstudent.model.AnStudiu"%>
<%@page import="java.util.Iterator"%>
<%@page import="delphi.netstudent.business.SpecializariPersistenceUtil"%>
<%@page import="delphi.netstudent.business.AnStudiuPersistenceUtil"%>
<%@page import="java.util.List"%>
<%@page import="delphi.netstudent.model.Serii"%>
<%@page import="delphi.netstudent.business.SeriiPersistenceUtil"%>
<%@page import="delphi.netstudent.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editare serie</title>
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
	
	int serieID = 0;
	
	if (logat == true) {
		Admin currentStudent = (Admin) session.getAttribute("currentAdmin");
		if (currentStudent != null && request.getParameter("id") != null) {
			try {
				serieID = Integer.parseInt(request.getParameter("id"));
			} catch (NumberFormatException e) {
				
			}
		} else {
			response.sendRedirect(application.getContextPath() + "/cpanel.jsp");
		}
		
		Serii serie = null;
		if (serieID != 0) {
			serie = SeriiPersistenceUtil.getSerie(serieID);
		} else {
			response.sendRedirect(application.getContextPath() + "/cpanel.jsp");
		}
		List aniStudiu = AnStudiuPersistenceUtil.getAniStudiu();
		List specializari = SpecializariPersistenceUtil.getSpecializari(); 
		%>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="editareSerie">
				<input type="hidden" name="idSerie" value=<%= serie.getId() %>>
				<p>Nume:</p>
				<input type="text" name="nume" class="easyui-validatebox" required="true" value=<%= serie.getDenumire() %>>
				<p>Specializare:</p>
	        	<select name="specializare">
	        		<%
						for (Iterator it = specializari.iterator(); it.hasNext(); ) {
							Specializari specializare = (Specializari) it.next();
						%>
							<option value=<%= specializare.getId() %> <% if (specializare.getId() == serie.getSpecializare().getId()) out.print("selected"); %>><%= specializare.getDenumire() %></option>	
						<%
						}
					%>
	        	</select>
	        	<p>An studiu:</p>
	        	<select name="an_studiu">
	        		<%
						for (Iterator it = aniStudiu.iterator(); it.hasNext(); ) {
							AnStudiu anStudiu = (AnStudiu) it.next();
						%>
							<option value=<%= anStudiu.getId() %> <% if (anStudiu.getId() == serie.getAnStudiu().getId()) out.print("selected"); %>><%= anStudiu.getNume() %></option>	
						<%
						}
					%>
	        	</select>
				<input type="submit" value="Salvare" onclick="inchide();">
			</form>
		<% 
	} else {
		response.sendRedirect(application.getContextPath() + "/cpanel.jsp");
	}
%>
</body>
</html>