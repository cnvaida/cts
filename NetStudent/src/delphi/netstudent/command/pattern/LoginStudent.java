package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import delphi.netstudent.business.UserLoginUtil;
import delphi.netstudent.model.Student;

public class LoginStudent implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mobil = request.getParameter("mobil");
		HttpSession session = request.getSession(true);
		String username = request.getParameter("nume");
		String password = request.getParameter("parola");
		if (UserLoginUtil.checkUser(username, password)) {
			Cookie cookie = new Cookie("logat", username);
			cookie.setMaxAge(60*60*2);
			response.addCookie(cookie);
			
			/*
			 * se incearca obtinerea unui obiect Student;
			 * atasarea lui pe sesiune se face doar daca este valid
			 * adica gasit in baza de date;
			 */
			Student currentStudent = UserLoginUtil.getStudent(username, password);
			if (currentStudent != null) {
				session.setAttribute("currStud", currentStudent);
			}
			
			if (mobil != null) {
				response.sendRedirect(request.getContextPath() + "/mstart.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/start.jsp");
			}
		} else {
			session.setAttribute("eroare_login", "Nume de utilizator sau parola gresite.");
			if (mobil != null) {
				response.sendRedirect(request.getContextPath() + "/mlogin.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			}
		}
	}

}
