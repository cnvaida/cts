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

public class EditareAn implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("panou_deschis", "5");
		String nume = request.getParameter("nume");
		int idAn = Integer.valueOf(request.getParameter("idAn"));
		if (nume != null && !nume.isEmpty()) {
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			AnStudiu an = null;
			try {
				tx = hibernateSession.beginTransaction();
				an = (AnStudiu) hibernateSession.get(AnStudiu.class, idAn);
				an.setNume(nume);
				hibernateSession.update(an);
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
	}
	
}
