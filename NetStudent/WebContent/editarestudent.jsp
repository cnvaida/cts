<%@page import="delphi.netstudent.business.FormaFinantarePersistenceUtil"%>
<%@page import="delphi.netstudent.business.SeriiPersistenceUtil"%>
<%@page import="delphi.netstudent.business.GrupePersistenceUtil"%>
<%@page import="delphi.netstudent.business.SpecializariPersistenceUtil"%>
<%@page import="delphi.netstudent.business.AnStudiuPersistenceUtil"%>
<%@page import="java.util.List"%>
<%@page import="delphi.netstudent.model.Specializari"%>
<%@page import="delphi.netstudent.model.Grupe"%>
<%@page import="delphi.netstudent.model.Serii"%>
<%@page import="delphi.netstudent.model.FormaFinantare"%>
<%@page import="delphi.netstudent.model.AnStudiu"%>
<%@page import="delphi.netstudent.business.StudentPersistenceUtil"%>
<%@page import="delphi.netstudent.model.Student"%>
<%@page import="java.util.Iterator"%>
<%@page import="delphi.netstudent.model.Admin"%>
<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editare student</title>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/default/easyui.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/icon.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/demo.css" %>>
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
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.min.js" %>></script>
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.easyui.min.js" %>></script>
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
	
	int studID = 0;
	
	if (logat == true) {
		Admin currentStudent = (Admin) session.getAttribute("currentAdmin");
		if (currentStudent != null && request.getParameter("id") != null) {
			try {
				studID = Integer.parseInt(request.getParameter("id"));
			} catch (NumberFormatException e) {
				out.println("Ciudat id de student!");
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			
		}
		
		Student stud = null;
		if (studID != 0) {
			stud = StudentPersistenceUtil.getStudent(studID);
		} else {
			out.println("student inexistent!<br/>");
			response.sendRedirect(application.getContextPath() + "/cpanel.jsp");
		}
		
		List studenti = StudentPersistenceUtil.getStudents();
		List aniStudiu = AnStudiuPersistenceUtil.getAniStudiu();
		List specializari = SpecializariPersistenceUtil.getSpecializari(); 
		List grupe = GrupePersistenceUtil.getGrupe();
		List serii = SeriiPersistenceUtil.getSerii();
		List formeFinantare = FormaFinantarePersistenceUtil.getFormeFinantare();
		%>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
		        <input type="hidden" name="action" value="editareStudenti">
		        <input type="hidden" name="idStudent" value=<%= stud.getId() %>>
		       	Nume:
		        <input type="text" class="easyui-validatebox" name="nume" required="true" value=<%= stud.getNume() %>>
		        Prenume:
		        <input type="text" class="easyui-validatebox" name="prenume" required="true" value=<%= stud.getPrenume() %>>
		        Cod student:
		        <input type="text" class="easyui-validatebox" name="cod_student" required="true" value=<%= stud.getCod_student() %>><br><br>
		        E-mail:
	        	<input type="text" class="easyui-validatebox" name="email" data-options="validType:'email'" required="true" value=<%= stud.getEmail() %>>
		        Specializare:
		        <select name="specializare">
		        	<%
						for (Iterator it = specializari.iterator(); it.hasNext(); ) {
							Specializari specializare = (Specializari) it.next();
						%>
							<option value=<%= specializare.getId() %> <% if (specializare.getId() == stud.getSpecializare().getId()) out.print("selected"); %>><%= specializare.getDenumire() %></option>	
						<%
						}
					%>
		        </select>
		        Grupă:
	        	<select name="grupa">
	        	<%
					for (Iterator it = grupe.iterator(); it.hasNext(); ) {
						Grupe grupa = (Grupe) it.next();
					%>
						<option value=<%= grupa.getId() %> <% if (grupa.getId() == stud.getGrupa().getId()) out.print("selected"); %>><%= grupa.getNume() %></option>	
					<%
					}
				%>
	        	</select>
	        	Seria:
	        	<select name="serie">
	        	<%
					for (Iterator it = serii.iterator(); it.hasNext(); ) {
						Serii serie = (Serii) it.next();
					%>
						<option value=<%= serie.getId() %> <% if (serie.getId() == stud.getSeria().getId()) out.print("selected"); %>><%= serie.getDenumire() + " " + serie.getSpecializare().getDenumire() + " " + serie.getAnStudiu().getNume() %></option>	
					<%
					}
				%>
	        	</select>
	        	Formă finanțare:
	        	<select name="forma_finantare">
	        	<%
					for (Iterator it = formeFinantare.iterator(); it.hasNext(); ) {
						FormaFinantare formaFinantare = (FormaFinantare) it.next();
					%>
						<option value=<%= formaFinantare.getId() %> <% if (formaFinantare.getId() == stud.getForma_finantare().getId()) out.print("selected"); %>><%= formaFinantare.getDenumire() %></option>	
					<%
					}
				%>
	        	</select>
	        	An studiu:
	        	<select name="an_studiu">
	        	<%
					for (Iterator it = aniStudiu.iterator(); it.hasNext(); ) {
						AnStudiu anStudiu = (AnStudiu) it.next();
					%>
						<option value=<%= anStudiu.getId() %> <% if (anStudiu.getId() == stud.getAn_studiu().getId()) out.print("selected"); %>><%= anStudiu.getNume() %></option>	
					<%
					}
				%>
	        	</select>
        		<input type="submit" value="Salvare">
        		<p></p>
        		<a href=<%= application.getContextPath() + "/administrarestudenti.jsp"%>>&#8592; Înapoi</a>
        	</form>
		<%
	} else {
		out.println("Acces interzis. Vă rugăm să vă logați.");
		response.sendRedirect(application.getContextPath() + "/adminlogin.jsp");
	}
%>
</body>
</html>