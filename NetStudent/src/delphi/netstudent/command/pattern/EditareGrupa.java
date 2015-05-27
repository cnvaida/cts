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

public class EditareGrupa implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "7");
		String nume = request.getParameter("nume");
		int idSerie = Integer.valueOf(request.getParameter("serie"));
		int idGrupa = Integer.valueOf(request.getParameter("idGrupa"));
		Session session =  HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Grupe grupa = null;
		Serii serie = null;
		if (nume != null && !nume.isEmpty()) {
			try {
				tx = session.beginTransaction();
				grupa = (Grupe) session.get(Grupe.class, idGrupa);
				serie = (Serii) session.get(Serii.class, idSerie);
				grupa.setNume(nume);
				grupa.setSerie(serie);
				session.update(grupa);
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
