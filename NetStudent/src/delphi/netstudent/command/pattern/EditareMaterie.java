package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Materii;
import delphi.netstudent.util.HibernateUtil;

public class EditareMaterie implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "4");
		int idMaterie = Integer.valueOf(request.getParameter("idMaterie"));
		String nume = request.getParameter("nume");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Materii materie = null;
		if (nume != null && !nume.isEmpty()) {
			try {
				tx = session.beginTransaction();
				materie = (Materii) session.get(Materii.class, idMaterie);
				materie.setDenumire(nume);
				session.update(materie);
				tx.commit();
			} catch  (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
	}

}
