package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Student;
import delphi.netstudent.util.HibernateUtil;

public class SchimbareEmail implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String email = request.getParameter("email");
		String parola = request.getParameter("parolaNoua");
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Student stud = (Student) session.getAttribute("currStud");
		if (email != null && !email.isEmpty() && parola != null && !parola.isEmpty()) {
			try {
				tx = hibernateSession.beginTransaction();
				if (stud.getParola().equals(parola)) {
					stud.setEmail(email);
					hibernateSession.update(stud);
					session.setAttribute("outcome", "E-mail modificat.");
				}
				tx.commit();
				
			} catch (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			} finally {
				hibernateSession.close();
			}
		}
		response.sendRedirect(request.getContextPath() + "/start.jsp");
	}

}
