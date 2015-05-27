package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.AnStudiu;
import delphi.netstudent.util.HibernateUtil;

public class AddAnStudiu implements Comanda {
	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("panou_deschis", "5");
		String nume = request.getParameter("nume");
		if (nume != null && !nume.isEmpty()) {
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			AnStudiu anStudiu = null;
			try {
				tx = hibernateSession.beginTransaction();
				anStudiu = new AnStudiu(nume);
				hibernateSession.save(anStudiu);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			} finally {
				hibernateSession.close();
			}
		} else {
			session.setAttribute("eroare_date_formular", "Verificați corectitudinea datelor.");
		}
		response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
	}
}
