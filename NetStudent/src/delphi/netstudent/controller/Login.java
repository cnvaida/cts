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
import delphi.netstudent.command.pattern.LoginAdministrator;
import delphi.netstudent.command.pattern.LoginStudent;
import delphi.netstudent.command.pattern.LogoutAdministrator;
import delphi.netstudent.command.pattern.LogoutStudent;

@WebServlet("/AdminLogin")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;   
    private Map<String, Comanda> comenzi;
	
    public Login() {
        super();
        comenzi = new HashMap<String, Comanda>(4);
        comenzi.put("adminLogin", new LoginAdministrator());
        comenzi.put("adminLogout", new LogoutAdministrator());
        comenzi.put("studentLogin", new LoginStudent());
        comenzi.put("logoutStudent", new LogoutStudent());
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetMethod(request, response);
	}
	
	public void doGetMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Comanda comanda = comenzi.get(action);
		if (comanda != null) {
			comanda.executa(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
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
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}
	}
}
