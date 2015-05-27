<%@page import="java.util.Iterator"%>
<%@page import="delphi.netstudent.model.Student"%>
<%@page import="java.util.List"%>
<%@page import="delphi.netstudent.business.StudentPersistenceUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/tabele.css" %>>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrare studenti</title>
</head>
<body>
<%
	int nrStudenti = StudentPersistenceUtil.getStudents().size();
	int numarPagina = 1;
	
	if (request.getParameter("pagina") != null) {
		if (Integer.parseInt(request.getParameter("pagina")) < 1) {
			numarPagina = 1;
		} else if (Integer.parseInt(request.getParameter("pagina")) > Math.round(nrStudenti/5)) {
			numarPagina = (int) Math.round((nrStudenti/5) + 0.5);
		} else {
			numarPagina = Integer.parseInt(request.getParameter("pagina"));
		}
	}
	
	String paginaUrmatoare = (numarPagina + 1) + "";
	String paginaPrecedenta = (numarPagina - 1) + "";
	
	List studentache = StudentPersistenceUtil.getStudents(numarPagina);
	
%>
	<table style="width: 910px;">
		<tr>
			<th>Nume</th>
			<th>Prenume</th>
			<th>Cod Student</th>
			<th>Specializare</th>
			<th>An Studiu</th>
			<th>Seria</th>
			<th>Grupă</th>
			<th>Formă Finanțare</th>
			<th>E-mail</th>
			<th>Editare</th>
			<th>Ștergere</th>
		</tr>
		<%
		for (Iterator it = studentache.iterator(); it.hasNext();) {
			Student stud = (Student) it.next();
			%>
			<tr>
				<td><%= stud.getNume() %></td>
				<td><%= stud.getPrenume() %></td>
				<td><%= stud.getCod_student() %></td>
				<td><%= stud.getSpecializare().getDenumire() %></td>
				<td><%= stud.getAn_studiu().getNume() %></td>
				<td><%= stud.getSeria().getDenumire() %></td>
				<td><%= stud.getGrupa().getNume() %></td>
				<td><%= stud.getForma_finantare().getDenumire() %></td>
				<td><%= stud.getEmail() %></td>
				<td><a href=<%= application.getContextPath() + "/editarestudent.jsp?id=" + stud.getId() %>>Editare</a></td>
				<td>
					<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
						<input type="hidden" name="action" value="stergeStudent">
						<input type="hidden" name="idStergere" value=<%= stud.getId() %>>
						<input type="submit" value="Șterge">
					</form>
				</td>
			</tr>
			<% 
		}
		%>
	</table>
	
	
	
	<% 
	/*
	int numarStudenti = StudentPersistenceUtil.getStudents().size();
	int pageNumber = 1;
	if (request.getParameter("page") != null) {
		session.setAttribute("page", request.getParameter("page"));
		pageNumber = Integer.parseInt(request.getParameter("page"));
	} else {
		session.setAttribute("page", "1");
	}
	String nextPage = "1";
	String previous = "1";
	List studentache = StudentPersistenceUtil.getStudents(Integer.valueOf((String)session.getAttribute("page")));
	if (pageNumber > Math.ceil(numarStudenti/5)) {
		pageNumber = 1;
	} else if (pageNumber < 1) {
		nextPage = (pageNumber + 1) + "";
		previous = (pageNumber - 1) + "";
	}
	
	for (Iterator it = studentache.iterator(); it.hasNext();) {
		Student stud = (Student) it.next();
		out.print(stud.getNume() + "<br/>");
	}
	*/
	%>
	
	<br/>
	<div id="sageti_stanga" style="float: left;">
		<a href=<%= "administrarestudenti.jsp?pagina=" + 1 %>>&#8633; Început</a>
	</div>
	<div id="sageti_dreapta" style="float: right">
		<a href=<%= "administrarestudenti.jsp?pagina=" + paginaPrecedenta %>>&#8592; Înapoi</a>&nbsp;
		<a href=<%= "administrarestudenti.jsp?pagina=" + paginaUrmatoare %>>Înainte &#8594;</a>
	</div>
</body>
</html>