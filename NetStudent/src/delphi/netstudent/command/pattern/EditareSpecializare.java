package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Specializari;
import delphi.netstudent.util.HibernateUtil;

public class EditareSpecializare implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("panou_deschis", "9");
		int idSpecializare = Integer.valueOf(request.getParameter("idSpecializare"));
		String denumire = request.getParameter("nume");
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Specializari specializare = null;
		try {
			tx = hibernateSession.beginTransaction();
			specializare = (Specializari) hibernateSession.get(Specializari.class, idSpecializare);
			specializare.setDenumire(denumire);
			hibernateSession.update(specializare);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			hibernateSession.close();
		}
		response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
	}
	
}
