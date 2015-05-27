package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delphi.netstudent.business.UserLoginUtil;
import delphi.netstudent.model.Admin;

public class LoginAdministrator implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String username = request.getParameter("nume");
		String password = request.getParameter("parola");
		
		if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
			if (UserLoginUtil.checkAdmin(username, password)) {
				Cookie cookie = new Cookie("loggedAdmin", username);
				cookie.setMaxAge(60*60*2);
				response.addCookie(cookie);
				
				Admin admin = UserLoginUtil.getAdmin(username, password);
				if (admin != null) {
					session.setAttribute("currentAdmin", admin);
				}
				
				response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
			} else {
				session.setAttribute("eroare_login", "Nume de utilizator sau parola gresite.");
				response.sendRedirect(request.getContextPath() + "/adminlogin.jsp");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/adminlogin.jsp");
		}
	}

}
