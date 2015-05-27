package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Grupe;
import delphi.netstudent.model.Serii;
import delphi.netstudent.util.HibernateUtil;

public class AddGrupe implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("panou_deschis", "7");
		String nume = request.getParameter("nume");
		int serie = Integer.valueOf(request.getParameter("serie"));
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Serii serieGrupa = null;
		Grupe grupa = null;
		try {
			tx = hibernateSession.beginTransaction();
			serieGrupa = (Serii) hibernateSession.get(Serii.class, serie);
			grupa = new Grupe(nume, serieGrupa);
			hibernateSession.save(grupa);
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
