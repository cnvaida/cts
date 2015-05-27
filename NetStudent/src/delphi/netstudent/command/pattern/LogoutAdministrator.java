package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAdministrator implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase("loggedAdmin")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}	
		}
		HttpSession session = request.getSession(true);
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/adminlogin.jsp");
	}

}
