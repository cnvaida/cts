package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutStudent implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase("logat")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}	
		}
		HttpSession session = request.getSession(true);
		session.invalidate();
		if (type != null) {
			response.sendRedirect(request.getContextPath() + "/mlogin.jsp");
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

}
