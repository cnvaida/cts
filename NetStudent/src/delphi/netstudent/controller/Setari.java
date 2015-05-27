package delphi.netstudent.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delphi.netstudent.command.pattern.AddAnStudiu;
import delphi.netstudent.command.pattern.AddAnunt;
import delphi.netstudent.command.pattern.AddFormaFinantare;
import delphi.netstudent.command.pattern.AddGrupe;
import delphi.netstudent.command.pattern.AddMaterie;
import delphi.netstudent.command.pattern.AddNota;
import delphi.netstudent.command.pattern.AddSerii;
import delphi.netstudent.command.pattern.AddSpecializari;
import delphi.netstudent.command.pattern.AddStudenti;
import delphi.netstudent.command.pattern.Comanda;
import delphi.netstudent.command.pattern.EditareAn;
import delphi.netstudent.command.pattern.EditareFormaFinantare;
import delphi.netstudent.command.pattern.EditareGrupa;
import delphi.netstudent.command.pattern.EditareMaterie;
import delphi.netstudent.command.pattern.EditareNota;
import delphi.netstudent.command.pattern.EditareSerie;
import delphi.netstudent.command.pattern.EditareSpecializare;
import delphi.netstudent.command.pattern.EditareStudenti;
import delphi.netstudent.command.pattern.SchimbaParolaAdmin;
import delphi.netstudent.command.pattern.StergeAn;
import delphi.netstudent.command.pattern.StergeAnunt;
import delphi.netstudent.command.pattern.StergeFormaFinantare;
import delphi.netstudent.command.pattern.StergeGrupa;
import delphi.netstudent.command.pattern.StergeMaterie;
import delphi.netstudent.command.pattern.StergeNota;
import delphi.netstudent.command.pattern.StergeSerie;
import delphi.netstudent.command.pattern.StergeSpecializare;
import delphi.netstudent.command.pattern.StergeStudent;

public class Setari extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, Comanda> comenzi;
 
    public Setari() {
        super();
        comenzi = new HashMap<String, Comanda>(27);
        comenzi.put("addAnStudiu", new AddAnStudiu());
        comenzi.put("addStudenti", new AddStudenti());
        comenzi.put("addSerii", new AddSerii());
        comenzi.put("addGrupe", new AddGrupe());
        comenzi.put("addSpecializari", new AddSpecializari());
        comenzi.put("addFormaFinantare", new AddFormaFinantare());
        comenzi.put("schimbareParolaAdmin", new SchimbaParolaAdmin());
        comenzi.put("stergeStudent", new StergeStudent());
        comenzi.put("editareStudenti", new EditareStudenti());
        comenzi.put("stergeAn", new StergeAn());
        comenzi.put("editareAn", new EditareAn());
        comenzi.put("stergeSpecializare", new StergeSpecializare());
        comenzi.put("editareSpecializare", new EditareSpecializare());
        comenzi.put("editareSerie", new EditareSerie());
        comenzi.put("stergeSerie", new StergeSerie());
        comenzi.put("stergeGrupa", new StergeGrupa());
        comenzi.put("editareGrupa", new EditareGrupa());
        comenzi.put("stergeFormaFinantare", new StergeFormaFinantare());
        comenzi.put("editareFormaFinantare", new EditareFormaFinantare());
        comenzi.put("addMaterie", new AddMaterie());
        comenzi.put("stergeMaterie", new StergeMaterie());
        comenzi.put("editareMaterie", new EditareMaterie());
        comenzi.put("addNota", new AddNota());
        comenzi.put("editareNota", new EditareNota());
        comenzi.put("stergeNota", new StergeNota());
        comenzi.put("addAnunt", new AddAnunt());
        comenzi.put("stergeAnunt", new StergeAnunt());
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPostMethod(request, response);
	}
	
	public void doPostMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Comanda comanda = comenzi.get(action);
		if (comanda != null) {
			comanda.executa(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
		}
	}

}
