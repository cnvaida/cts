<%@page import="delphi.netstudent.model.Anunt"%>
<%@page import="delphi.netstudent.business.AnuntPersistenceUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="delphi.netstudent.model.Note"%>
<%@page import="java.util.Set"%>
<%@page import="delphi.netstudent.model.Student"%>
<%@page import="delphi.netstudent.model.AnStudiu"%>
<%@page import="delphi.netstudent.business.AnStudiuPersistenceUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NetStudent</title>
<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/default/easyui.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/stil.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/gray/easyui.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/icon.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/demo.css" %>>
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.min.js" %>></script>
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.easyui.min.js" %>></script>
	<script>
		function inchide() {
			window.close();
			window.opener.location.reload();
		}
		
		function goToAdministrare() {
			$('#tt').tabs('select',4);
		}
		
		function openGhid() {
			$('#ghid').window('open'); 
		}
		
		function openContact() {
			$('#contact').window('open'); 
		}
	</script>
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
	
		%>
			<div id="header">
				<span id="header_text">NET STUDENT</span>
			</div>
			
			<div style="padding:0px;border:1px solid #ddd;text-align:center;">
				<a href="index.jsp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-home'">Acasă</a>
			    <a href="#" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-utilizator'">Contul meu</a>
			    <a href="#" class="easyui-menubutton" data-options="menu:'#mm3',iconCls:'icon-help'">Ajutor</a>
			    <a href=<%= application.getContextPath() + "/admin.do?action=logoutStudent" %> class="easyui-linkbutton" data-options="plain:true, iconCls: 'icon-iesire'">Logout</a>
			</div>
			<div id="mm1" style="width:150px;">
				<div data-options="iconCls:'icon-settings'" onclick="goToAdministrare();">Administrare</div>
			</div>
			<div id="mm3" style="width:150px;">
				<div data-options="iconCls: 'icon-ghid'" onclick="openGhid();">Ghid</div>
			    <div data-options="iconCls: 'icon-mail'" onclick="openContact();">Contact</div>
			    <div data-options="iconCls: 'icon-despre'">
			    	<span>Despre</span>
			        <div class="menu-content" style="padding:10px;text-align:left">
			        	
			            <p style="font-size:12px;color:#444">Realizat de Vaida Cătălin pentru <strong><em>Java Programming Competition</em></strong></p>
			        </div>
			    </div>
			</div>
			
			<div id="tt" class="easyui-tabs" style="width: 900px; height: auto; margin-left: auto; margin-right: auto;margin-top: 1%;">
		        <div title=<%= currentStudent.getNume()%> data-options="iconCls:'icon-utilizator'" style="padding-top:10px; padding-left:20px; padding-right:10px; padding-bottom:10px;">
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
		        </div>
		        <div title="Note" data-options="iconCls:'icon-notes'" style="padding:20px">
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
		        <div title="Restante" data-options="iconCls:'icon-schedule'" style="padding:20px">
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
		        <div title="Anunturi" data-options="iconCls:'icon-mail'" style="padding:10px">
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
		        <div title="Administrare" data-options="iconCls:'icon-settings'" style="padding-top:10px; padding-left: 20px; padding-bottom:10px;">
		        	<%
			        	if (session.getAttribute("outcome") != null) {
			    			out.print("<span style=\"color: red\">" + session.getAttribute("outcome") + "</span>");
			    			session.invalidate();
			    		}
		        	%>
		             <h3 style="color:#0099FF;">Schimbare parolă student</h3>
					<form action=<%= application.getContextPath() + "/setariStudent.do" %> method="post">
						<input type="hidden" name="action" value="schimbareParola">
						<p>Parola veche:</p>
						<input type="password" class="easyui-validatebox" name="parolaVeche" required="true">
						<p>Parola noua:</p>
						<input type="password" class="easyui-validatebox" name="parolaNoua" required="true">
						<p></p>
						<input type="submit" value="Salvare">
					</form>
					<hr/>
					 <h3 style="color:#0099FF;">Schimbare e-mail student</h3>
					<form action=<%= application.getContextPath() + "/setariStudent.do" %> method="post">
						<input type="hidden" name="action" value="schimbareEmail">
						<p>E-mail curent:</p>
						<input type="text" name="emailVechi" value=<%= currentStudent.getEmail() %> disabled="disabled">
						<p>E-mail nou:</p>
						<input type="text" class="easyui-validatebox" name="email" data-options="validType:'email'" required="true">
						<p>Parola curentă:</p>
						<input type="password" class="easyui-validatebox" name="parolaNoua" required="true">
						<p></p>
						<input type="submit" value="Salvare">
					</form>
		        </div>
		    </div>
		    
		    <div id="ghid" class="easyui-window" title="Ghid utilizare" data-options="iconCls:'icon-save'" closed="true" style="width:400px;height:700px;padding:10px;">
		        
		    </div>
		    
		    <div id="contact" class="easyui-window" title="Contact" data-options="iconCls:'icon-save'" closed="true" style="width:250px;height:130px;padding:10px;">
		        <p>Pentru orice fel de întrebări:<br/><strong><em>secretariat@facultate.ro</em></strong></p>
		    </div>
		<%
	} else {
		response.sendRedirect(application.getContextPath());		
	}
%>
</body>
</html>