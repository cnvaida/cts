package delphi.netstudent.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import delphi.netstudent.command.pattern.Comanda;
import delphi.netstudent.command.pattern.RecuperareParola;
import delphi.netstudent.command.pattern.SchimbareEmail;
import delphi.netstudent.command.pattern.SchimbareParola;

@WebServlet("/SetariStudent")
public class SetariStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Comanda> comenzi;
       
    public SetariStudent() {
        super();
        comenzi = new HashMap<String, Comanda>(3);
        comenzi.put("schimbareParola", new SchimbareParola());
        comenzi.put("schimbareEmail", new SchimbareEmail());
        comenzi.put("recuperareParola", new RecuperareParola());
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
			response.sendRedirect(request.getContextPath() + "/start.jsp");
		}
	}
	
}
