<%@page import="delphi.netstudent.model.Anunt"%>
<%@page import="delphi.netstudent.business.AnuntPersistenceUtil"%>
<%@page import="delphi.netstudent.model.Materii"%>
<%@page import="delphi.netstudent.business.MateriiPersistenceUtil"%>
<%@page import="delphi.netstudent.model.Admin"%>
<%@page import="delphi.netstudent.model.Student"%>
<%@page import="delphi.netstudent.business.StudentPersistenceUtil"%>
<%@page import="delphi.netstudent.model.AnStudiu"%>
<%@page import="delphi.netstudent.business.AnStudiuPersistenceUtil"%>
<%@page import="delphi.netstudent.model.FormaFinantare"%>
<%@page import="delphi.netstudent.business.FormaFinantarePersistenceUtil"%>
<%@page import="delphi.netstudent.model.Grupe"%>
<%@page import="delphi.netstudent.business.GrupePersistenceUtil"%>
<%@page import="delphi.netstudent.model.Serii"%>
<%@page import="delphi.netstudent.business.SeriiPersistenceUtil"%>
<%@page import="java.util.Iterator"%>
<%@page import="delphi.netstudent.model.Specializari"%>
<%@page import="java.util.List"%>
<%@page import="delphi.netstudent.business.SpecializariPersistenceUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
	String panou_deschis = (String) session.getAttribute("panou_deschis");
	String mesaj_eroare = (String) session.getAttribute("eroare_date_formular");
	List studenti = StudentPersistenceUtil.getStudents();
	List aniStudiu = AnStudiuPersistenceUtil.getAniStudiu();
	List specializari = SpecializariPersistenceUtil.getSpecializari(); 
	List grupe = GrupePersistenceUtil.getGrupe();
	List serii = SeriiPersistenceUtil.getSerii();
	List formeFinantare = FormaFinantarePersistenceUtil.getFormeFinantare();
	List materii = MateriiPersistenceUtil.getMaterii();
	List anunturi = AnuntPersistenceUtil.getAnunturi();
	int numarStudenti = studenti.size();
%>
	<title>Panou de control</title>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/stil.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/gray/easyui.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/themes/icon.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/demo.css" %>>
	<link rel="stylesheet" type="text/css" href=<%= application.getContextPath() + "/css/tabele.css" %>>
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.min.js" %>></script>
	<script type="text/javascript" src=<%= application.getContextPath() + "/js/jquery.easyui.min.js" %>></script>
	
	<script type="text/javascript">
		window.onload = function() {
			$('#aa').accordion({
			    selected : <%= (panou_deschis != null) ? panou_deschis : "0" %>
			});	
		};
		
		function openWindow(url) {
			window.open(url, 'Editare', "height=195,width=150");
		}
		
		
	</script>
</head>
<body>
	<div style="font-size: 32px;margin-left: 20%;margin-top: 2%;margin-bottom: 1%; color: #999999;">Setări</div>
	<div>
		<% if (mesaj_eroare != null) out.println(mesaj_eroare); session.removeAttribute("eroare_date_formular"); %>
	</div>
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
	
	if (logat == true) {
		Admin currentStudent = (Admin) session.getAttribute("currentAdmin");
		if (currentStudent != null) {
			
		} else {
			response.sendRedirect(application.getContextPath() + "/adminlogin.jsp");
		}
		%>
		<div id="aa" class="easyui-accordion" style="width:1000px;height:800px;margin-left:auto;margin-right:auto;">
			<div title="Adăugare Note" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
	        	<h3 style="color:#0099FF;">Adaugă notă</h3>
				<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
					<input type="hidden" name="action" value="addNota">
					<p>Nota:</p>
					<select name="nota">
						<option value="0" selected="selected"></option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
					<p>Materie:</p>
					<select name="materie">
						<option value="0" selected="selected"></option>
						<%
							for (Iterator it = materii.iterator(); it.hasNext(); ) {
								Materii materie = (Materii) it.next();
								%>
									<option value=<%= materie.getId() %>><%= materie.getDenumire() %></option>
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
									<option value=<%= stud.getId() %>><%= stud.getNume() + " " + stud.getPrenume() %></option>
								<%
							}
						%>
					</select>
					<select name="calificativ">
						<option value="Examen">Examen</option>
						<option value="Restanta">Restanță</option>
					</select>
					<p></p>
					<input type="submit" value="Salvare">
				</form>
    		</div>
    		<div title="Catalog Note" data-options="iconCls:'icon-table'" style="padding:10px;">
	        	<h3 style="color:#0099FF;">Catalog note</h3>
				<iframe src="administrarenote.jsp" frameborder="0" width="100%" height="100%"></iframe>
    		</div>
    		<div title="Adăugare Studenți" data-options="iconCls:'icon-student'" style="overflow:auto;padding:10px;">
        		<h3 style="color:#0099FF;">Adaugă student</h3>
        		<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
		        	<input type="hidden" name="action" value="addStudenti">
		        	<p>Nume:</p>
		        	<input type="text" class="easyui-validatebox" name="nume" required="true">
		        	<p>Prenume:</p>
		        	<input type="text" class="easyui-validatebox" name="prenume" required="true">
		        	<p>Cod student:</p>
		        	<input type="text" class="easyui-validatebox" name="cod_student" required="true">
		        	<p>Specializare:</p>
		        	<select name="specializare">
		        		<%
							for (Iterator it = specializari.iterator(); it.hasNext(); ) {
								Specializari specializare = (Specializari) it.next();
							%>
								<option value=<%= specializare.getId() %>><%= specializare.getDenumire() %></option>	
							<%
							}
						%>
		        	</select>
		        	<p>Grupă:</p>
	        		<select name="grupa">
	        		<%
						for (Iterator it = grupe.iterator(); it.hasNext(); ) {
							Grupe grupa = (Grupe) it.next();
						%>
							<option value=<%= grupa.getId() %>><%= grupa.getNume() %></option>	
						<%
						}
					%>
	        		</select>
	        		<p>Seria:</p>
	        		<select name="serie">
	        		<%
						for (Iterator it = serii.iterator(); it.hasNext(); ) {
							Serii serie = (Serii) it.next();
						%>
							<option value=<%= serie.getId() %>><%= serie.getDenumire() %></option>	
						<%
						}
					%>
	        		</select>
	        		<p>Formă finanțare:</p>
	        		<select name="forma_finantare">
	        		<%
						for (Iterator it = formeFinantare.iterator(); it.hasNext(); ) {
							FormaFinantare formaFinantare = (FormaFinantare) it.next();
						%>
							<option value=<%= formaFinantare.getId() %>><%= formaFinantare.getDenumire() %></option>	
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
							<option value=<%= anStudiu.getId() %>><%= anStudiu.getNume() %></option>	
						<%
						}
					%>
	        		</select>
	        		<p>E-mail:</p>
	        		<input type="text" class="easyui-validatebox" name="email" data-options="validType:'email'" required="true">
	        		<p>Parolă:</p>
	        		<input type="password" class="easyui-validatebox" name="parola" required="true">
	        		<p>Categorie:</p>
	        		<select name="categorie" disabled="disabled">
	        			<option value="0">Student</option>
	        		</select>
	        		<p></p>
        		<input type="submit" value="Salvare">
        	</form>
    	</div>
    
	    <div title="Administrare Studenți" data-options="iconCls:'icon-admstud'" style="overflow:auto;padding:10px;">
	        <h3 style="color:#0099FF;">Listă studenți</h3>
			<iframe src="administrarestudenti.jsp" frameborder="0" width="100%" height="100%"></iframe>
	    </div>
     
     	 <div title="Materii" data-options="iconCls:'icon-materii'" style="overflow:auto;padding:10px;">
	        <h3 style="color:#0099FF;">Adaugă materie</h3>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="addMaterie">
				<input type="text" class="easyui-validatebox" name="nume" required="true">
				<input type="submit" value="Salvare">
			</form>
			<br/>
			<hr/>
			<h3 style="color:#0099FF;">Administrare materii</h3>
			<div>
				<table>
					<tr>
						<th>Denumire</th>
						<th>Editare</th>
						<th>Ștergere</th>
					</tr>
					<%
						for (Iterator it = materii.iterator(); it.hasNext(); ) {
								Materii materie = (Materii) it.next();
							%>
								<tr>
									<td><%= materie.getDenumire() %></td>
									<td><a href="#" onclick="openWindow('<%= application.getContextPath() + "/editarematerie.jsp?id=" + materie.getId() %>');">Editare</a></td>
									<td>
		        						<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
		        							<input type="hidden" name="action" value="stergeMaterie">
		        							<input type="hidden" name="idMaterie" value=<%= materie.getId() %>>
		        							<input type="submit" value="Șterge">
		        						</form>
	        						</td>
								</tr>
							<%
						}
					%>
				</table>
			</div>
	    </div>
     
		<div title="Ani Studiu" data-options="iconCls:'icon-ani'" style="padding:10px;">
	    	 <h3 style="color:#0099FF;">Adaugă an de studiu</h3>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="addAnStudiu">
				<input type="text" class="easyui-validatebox" name="nume" required="true">
				<input type="submit" value="Salvare">
			</form>
			<br/>
			<hr/>
			<h3 style="color:#0099FF;">Administrare ani studiu</h3>
			<div style="width: 350px;">
				<table>
					<tr>
						<th>Nume</th>
						<th>Editare</th>
						<th>Ștergere</th>
					</tr>
					<%
					for (Iterator it = aniStudiu.iterator(); it.hasNext(); ) {
						AnStudiu anStudiu = (AnStudiu) it.next();
						%>
							<tr>
								<td>Anul <%= anStudiu.getNume() %></td>
								<td><a href="#" onclick="openWindow('<%= application.getContextPath() + "/editareAn.jsp?id=" + anStudiu.getId() %>');">Editare</a></td>
								<td>
									<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
										<input type="hidden" name="action" value="stergeAn">
										<input type="hidden" name="idAn" value=<%= anStudiu.getId() %>>
										<input type="submit" value="Șterge">
									</form>
								</td>
							</tr>
						<%
					}
					%>
				</table>
			</div>
		</div>
    
	    <div title="Forme Finanțare" data-options="iconCls:'icon-finantare'" style="padding:10px;">
	    	<h3 style="color:#0099FF;">Adaugă forme finanțare</h3>
	        <form action=<%= application.getContextPath() + "/setari.do" %> method="post">
	        	<input type="hidden" name="action" value="addFormaFinantare">
	        	<p>Denumire:</p>
	        	<input type="text" class="easyui-validatebox" name="nume" required="true">
	        	<input type="submit" value="Salvare">
	        </form>
	        <br/>
	        <hr/>
	        <h3 style="color:#0099FF;">Administrare forme finanțare</h3>
	        <div style="width: 500px;">
	        	<table>
	        		<tr>
	        			<th>Denumire</th>
	        			<th>Editare</th>
	        			<th>Ștergere</th>
	        		</tr>
	        		<%
	        			for (Iterator it = formeFinantare.iterator(); it.hasNext(); ) {
	        				FormaFinantare formaFinantare = (FormaFinantare) it.next();
	        			%>
	        				<tr>
	        					<td><%= formaFinantare.getDenumire() %></td>
	        					<td><a href="#" onclick="openWindow('<%= application.getContextPath() + "/editareformafinantare.jsp?id=" + formaFinantare.getId() %>');">Editare</a></td>
	        					<td>
	        						<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
	        							<input type="hidden" name="action" value="stergeFormaFinantare">
	        							<input type="hidden" name="idFormaFinantare" value=<%= formaFinantare.getId() %>>
	        							<input type="submit" value="Șterge">
	        						</form>
	        					</td>
	        				</tr>
	        			<%
	        			}
	        		%>
	        	</table>
	        </div>
	    </div>
    
    	<div title="Grupe" data-options="iconCls:'icon-grupe'" style="padding:10px;">
	   		<h3 style="color:#0099FF;">Adaugă grupe</h3>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="addGrupe">
				<p>Nume grupă:</p>
				<input type="text" class="easyui-validatebox" name="nume" required="true">
				<p>Serie</p>
				<select name="serie">
					<%
						for (Iterator it = serii.iterator(); it.hasNext(); ) {
							Serii serie = (Serii) it.next();
						%>
							<option value=<%= serie.getId() %>><%= "Seria " + serie.getDenumire() + ", " + serie.getSpecializare().getDenumire() + ", an " + serie.getAnStudiu().getNume() %></option>	
						<%
						}
					%>
				</select>
				<input type="submit" value="Salvare">
			</form>
			<p></p>
				
				<br/>
				<hr/>
				<h3 style="color:#0099FF;">Administrare grupe</h3>
				<div style="width: 500px">
					<table>
						<tr>
							<th>Denumire</th>
							<th>Serie</th>
							<th>An</th>
							<th>Specializare</th>
							<th>Editare</th>
							<th>Ștergere</th>
						</tr>
						<%
							for (Iterator it = grupe.iterator(); it.hasNext(); ) {
								Grupe grupa = (Grupe) it.next();
								%>
									<tr>
										<td><%= grupa.getNume() %></td>
										<td><%= grupa.getSerie().getDenumire() %></td>
										<td><%= grupa.getSerie().getAnStudiu().getNume() %></td>
										<td><%= grupa.getSerie().getSpecializare().getDenumire() %></td>
										<td><a href="#" onclick="openWindow('<%= application.getContextPath() + "/editaregrupa.jsp?id=" + grupa.getId() %>');">Editare</a></td>
										<td>
											<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
			    								<input type="hidden" name="action" value="stergeGrupa">
			    								<input type="hidden" name="idGrupa" value=<%= grupa.getId() %>>
			    								<input type="submit" value="Șterge">
			    							</form>
										</td>
									</tr>
								<%
							}
						%>
					</table>
				</div>
		</div>
    
     	<div title="Serii" data-options="iconCls:'icon-serii'" style="padding:10px;">
	        <h3 style="color:#0099FF;">Adăugare serii</h3>
	        <form action=<%= application.getContextPath() + "/setari.do" %> method="post">
	        	<input type="hidden" name="action" value="addSerii">
	        	<p>Nume serie:</p>
	        	<input type="text" class="easyui-validatebox" name="nume" required="true">
	        	<p>Specializare:</p>
	        	<select name="specializare">
	        		<%
						for (Iterator it = specializari.iterator(); it.hasNext(); ) {
							Specializari specializare = (Specializari) it.next();
						%>
							<option value=<%= specializare.getId() %>><%= specializare.getDenumire() %></option>	
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
							<option value=<%= anStudiu.getId() %>><%= anStudiu.getNume() %></option>	
						<%
						}
					%>
	        	</select>
	        	<p></p>
	        	<input type="submit" value="Salvare">
	        </form>   
	        <br/>
			<hr/>
			<h3 style="color:#0099FF;">Administrare serii</h3>
	        <div style="width:400px;">
	        	<table>
	        		<tr>
	        			<th>Nume</th>
	        			<th>Specializare</th>
	        			<th>An</th>
	        			<th>Editare</th>
	        			<th>Ștergere</th>
	        		</tr>
	        		<%
		        		for (Iterator it = serii.iterator(); it.hasNext(); ) {
							Serii serie = (Serii) it.next();
							%>
								<tr>
									<td><%= serie.getDenumire() %></td>
									<td><%= serie.getSpecializare().getDenumire() %></td>
									<td><%= serie.getAnStudiu().getNume() %></td>
									<td><a href="#" onclick="openWindow('<%= application.getContextPath() + "/editareserie.jsp?id=" + serie.getId() %>');">Editare</a></td>
									<td>
										<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
		    								<input type="hidden" name="action" value="stergeSerie">
		    								<input type="hidden" name="idSerie" value=<%= serie.getId() %>>
		    								<input type="submit" value="Șterge">
		    							</form>
									</td>
								</tr>
							<%					
						}
	        		%>
	        	</table>
	    	</div>      
    	</div>
    
		<div title="Specializări" data-options="iconCls:'icon-laborator'" style="padding:10px;">
	    	<h3 style="color:#0099FF;">Adaugă specializari</h3>
	    	<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
	    		<input type="hidden" name="action" value="addSpecializari">
	    		<p>Nume specializare:</p>
	    		<input type="text" class="easyui-validatebox" name="nume" required="true">
	    		<input type="submit" value="Salvare">
	    	</form>      
	    	<br/>
	    	<hr/>
	    	<h3 style="color:#0099FF;">Administrare specializari</h3>
	    	<div style="width:400px;">
	    		<table>
	    			<tr>
	    				<th>Denumire</th>
	    				<th>Editare</th>
	    				<th>Ștergere</th>
	    			</tr>
	    			<% 
		    			for (Iterator it = specializari.iterator(); it.hasNext(); ) {
		    				Specializari specializare = (Specializari) it.next();
		    				%>
		    					<tr>
		    						<td><%= specializare.getDenumire() %></td>
		    						<td><a href="#" onclick="openWindow('<%= application.getContextPath() + "/editarespecializare.jsp?id=" + specializare.getId() %>');">Editare</a></td>
		    	
		    						<td>
		    							<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
		    								<input type="hidden" name="action" value="stergeSpecializare">
		    								<input type="hidden" name="idSpecializare" value=<%= specializare.getId() %>>
		    								<input type="submit" value="Șterge">
		    							</form>
		    						</td>
		    					</tr>
		    				<%
		    			}
	    			%>
	    		</table>
	    		
	    	</div> 
		</div>
		
		<div title="Schimbare parolă administrator" data-options="iconCls:'icon-parola'" style="padding:10px;">
	        <h3 style="color:#0099FF;">Schimbare parolă administrator</h3>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="schimbareParolaAdmin">
				<p>Parola veche:</p>
				<input type="password" class="easyui-validatebox" name="parolaVeche" required="true">
				<p>Parola noua:</p>
				<input type="password" class="easyui-validatebox" name="parolaNoua" required="true">
				<p></p>
				<input type="submit" value="Salvare">
			</form>
    	</div>
    	<div title="Anunțuri" data-options="iconCls:'icon-articol'" style="padding:10px;">
	        <h3 style="color:#0099FF;">Adaugă anunț</h3>
			<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
				<input type="hidden" name="action" value="addAnunt">
			    <textarea name="textAnunt" style="width: 660px; height: 130px; "></textarea>
			    <p></p>
			    <input type="submit" value="Salvare">
			</form>
			<hr>
			<div>
				<table style="width: 70%">
					<tr>
						<th>Anunt</th>
						<th>Ștergere</th>
					</tr>
					<%
					for (Iterator it = anunturi.iterator(); it.hasNext();) {
						Anunt anunt = (Anunt) it.next();
						%>
							<tr>
								<td><%= anunt.getAnunt() %></td>
								<td>
									<form action=<%= application.getContextPath() + "/setari.do" %> method="post">
										<input type="hidden" name="action" value="stergeAnunt">
										<input type="hidden" name="idAnunt" value=<%= anunt.getId() %>>
										<input type="submit" value="Șterge">
									</form>
								</td>
							</tr>
						<%
					}
					%>
				</table>
			</div>
    	</div>
	</div>
	
	<%
	} else {
		out.println("Acces interzis. Vă rugăm să vă logați.");
		response.sendRedirect(application.getContextPath() + "/adminlogin.jsp");
	}
%>
	<div id="logout" style="font-size: 32px;margin-left: 330px;margin-top: 10px;margin-bottom: 20px;">
		<table class="none">
			<tr>
				<td><a href=<%= application.getContextPath() %>>&#8592; Înapoi</a>&nbsp;</td>
				<td>
					<form action=<%= application.getContextPath() + "/admin.do" %> method="post">
					<input type="hidden" name="action" value="adminLogout">
					<input type="submit" value="Logout administrator">
				</form>
				</td>
			</tr>
		</table>
		
		
	</div>
</body>
</html>