<%@page import="delphi.netstudent.model.Anunt"%>
<%@page import="delphi.netstudent.business.AnuntPersistenceUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="delphi.netstudent.model.Note"%>
<%@page import="java.util.Set"%>
<%@page import="delphi.netstudent.model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">  
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>Net Student</title>  
    <link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/metro/easyui.css"%>>  
    <link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/icon.css"%>>  
    <script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.min.js"%>></script>  
    <script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.easyui.min.js"%>></script> 
</head>
<body>
<%
	boolean logat = false;
	Cookie prajituri[] = request.getCookies(); 
	if (prajituri != null) {
		for (Cookie cook : prajituri) {
			if (cook.getName().equalsIgnoreCase("logat")) {
				logat = true;
			}	
		}
	}
	
	int anID = 0;
	
	if (logat == true) {
		Student currentStudent = (Student) session.getAttribute("currStud");
		if (currentStudent != null) {
		%>
    <div class="easyui-tabs" data-options="fit:true,border:false,plain:true">
        <div title=<%= currentStudent.getPrenume() %> style="padding:10px">
        	<p style="font-size:14px">Informații student</p>
            <table>
            	<tr>
            		<td>Nume:</td>
            		<td><b><%= currentStudent.getNume() %></b></td>
            	</tr>
            	<tr>
            		<td>Prenume: </td>
            		<td><b><%= currentStudent.getPrenume() %></b></td>
            	</tr>
            	<tr>
            		<td>Cod student:</td>
            		<td><b><%= currentStudent.getCod_student() %></b></td>
            	</tr>
            	<tr>
            		<td>E-mail:</td>
            		<td><b><%= currentStudent.getEmail() %></b></td>
            	</tr>
            	<tr>
            		<td>Specializare:</td>
            		<td><b><%= currentStudent.getSpecializare().getDenumire() %></b></td>
            	</tr>
            	<tr>
            		<td>An studiu:</td>
            		<td><b><%= currentStudent.getAn_studiu().getNume() %></b></td>
            	</tr>
            	<tr>
            		<td>Serie:</td>
            		<td><b><%= currentStudent.getSeria().getDenumire() %></b></td>
            	</tr>
            	<tr>
            		<td>Grupă:</td>
            		<td><b><%= currentStudent.getGrupa().getNume() %></b></td>
            	</tr>
            	<tr>
            		<td>Formă Finanțare:</td>
            		
            		<td><b><%= currentStudent.getForma_finantare().getDenumire() %></b></td>
            	</tr>
            </table>
            <p></p>
            <a href=<%= application.getContextPath() + "/admin.do?action=logoutStudent&type=mobile" %> class="easyui-linkbutton">Logout</a>
        </div>
        <div title="Note" style="padding:10px">
            <table>
            	<tr>
            		<th>Materie</th>
            		<th>Notă</th>
            		<th>Tip</th>
            	</tr>
            	<%
            		int par = 1;
            		Set<Note> note = currentStudent.getNote();
            		for (Iterator it = note.iterator(); it.hasNext();) {
            			Note nota = (Note) it.next();
            			par++;
            			if (par % 2 == 0) {
            			%>
            				<tr style="background-color: #F0F0F0">
            					<td style="width: 250px;"><%= nota.getMaterie().getDenumire() %></td>
            					<td style="width: 50px;"><%= nota.getNota() %></td>
            					<td style="width: 100px;"><%= nota.getCalificativ() %></td>
            				</tr>
            			<%
            			} else {
            				%>
            				<tr style="background-color: #FFFFFF">
            					<td style="width: 250px;"><%= nota.getMaterie().getDenumire() %></td>
            					<td style="width: 50px;"><%= nota.getNota() %></td>
            					<td style="width: 100px;"><%= nota.getCalificativ() %></td>
            				</tr>
            				<%
            			}
            		}
            	%>
            </table>
        </div>
        <div title="Restanțe" style="padding:10px">
        	<%
            	int nrRestante = 0;
            	boolean restantier = false;
            	for (Iterator it = note.iterator(); it.hasNext();) {
            		Note nota = (Note) it.next();
            		if (nota.getNota() < 5) {
            			restantier = true;
            			nrRestante++;
            			%>
            				<p><%= nrRestante + ". " + nota.getMaterie().getDenumire() %></p>
            			<%
            		} 
            	}
            	
            	if (!restantier) {
            		%>
            			<p>Nu aveți nici o restanță.</p>
            		<%
            	}
            	
            %>
        </div>
        <div title="Anunțuri" style="padding:10px">
            <%
            	List anunturi = AnuntPersistenceUtil.getAnunturi();
            	for (Iterator it = anunturi.iterator(); it.hasNext();) {
            		Anunt anunt = (Anunt) it.next();
            		%>
            			<p><%= anunt.getAnunt() %></p>
            			<p></p>
            			<p></p>
            		<%
            	}
            %>
        </div>
    </div>
    <style>
        p{
            line-height:150%;
        }
        
        body {
        	font-family: arial;
        }
    </style>
    <%
		} else {
			response.sendRedirect(application.getContextPath() + "/mlogin.jsp");
		}
    } else {
    	response.sendRedirect(application.getContextPath() + "/mlogin.jsp");	
    }
    %>
</body>    
</html>