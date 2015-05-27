<%@page import="delphi.netstudent.business.StudentPersistenceUtil"%>
<%@page import="java.util.List"%>
<%@page import="delphi.netstudent.business.MateriiPersistenceUtil"%>
<%@page import="delphi.netstudent.model.Student"%>
<%@page import="delphi.netstudent.model.Materii"%>
<%@page import="java.util.Iterator"%>
<%@page import="delphi.netstudent.model.Note"%>
<%@page import="delphi.netstudent.business.NotePersistenceUtil"%>
<%@page import="delphi.netstudent.model.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
	
	long notaID = 0;
	
	if (logat == true) {
		Admin currentStudent = (Admin) session.getAttribute("currentAdmin");
		if (currentStudent != null && request.getParameter("id") != null) {
			notaID = Long.valueOf(request.getParameter("id"));
		} else{
			response.sendRedirect(application.getContextPath() + "/adminlogin.jsp");
		}
		List materii = MateriiPersistenceUtil.getMaterii();
		List studenti = StudentPersistenceUtil.getStudents();
		Note nota = NotePersistenceUtil.getNota(notaID);
		%>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="editareNota">
				<input type="hidden" name="idNota" value=<%= nota.getIdNota() %>>
				<p>Notă:</p>
				<input type="text" class="easyui-validatebox" required="true" name="nota" value=<%= nota.getNota() %>>
				<p>Materie:</p>
				<select name="materie">
						<option value="0" selected="selected"></option>
						<%
							for (Iterator it = materii.iterator(); it.hasNext(); ) {
								Materii materie = (Materii) it.next();
								%>
									<option value=<%= materie.getId() %> <% if (nota.getMaterie().getDenumire().equals(materie.getDenumire())) out.print("selected"); %>><%= materie.getDenumire() %></option>
								<%
							}
						%>
					</select>
					<p>Student:</p>
					<select name="student">
						<option value="0" selected="selected"></option>
						<%
							for (Iterator it = studenti.iterator(); it.hasNext();) {
								Student stud = (Student) it.next();
								%>
									<option value=<%= stud.getId() %> <% if (nota.getStudent().getCod_student().equals(stud.getCod_student())) out.print("selected"); %>><%= stud.getNume() + " " + stud.getPrenume() %></option>
								<%
							}
						%>
					</select>
					<p>Tip:</p>
					<select name="calificativ">
						<option value="Examen">Examen</option>
						<option value="Restanță">Restanță</option>
					</select>
					<p></p>
				<input type="submit" value="Salvare" onclick="inchide();">
			</form>
			<p></p>
			<a href=<%= application.getContextPath() + "/administrarenote.jsp"%>>&#8592; Înapoi</a>
		<%
	} else {
		response.sendRedirect(application.getContextPath() + "/adminlogin.jsp");		
	}
%>
</body>
</html>