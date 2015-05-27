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
import delphi.netstudent.model.Serii;
import delphi.netstudent.model.Specializari;
import delphi.netstudent.util.HibernateUtil;

public class EditareSerie implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "8");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		int idSerie = Integer.valueOf(request.getParameter("idSerie"));
		int idAnStudiu = Integer.valueOf(request.getParameter("an_studiu"));
		int idSpecializare = Integer.valueOf(request.getParameter("specializare"));
		String nume = request.getParameter("nume");
		Serii serie = null;
		AnStudiu anStudiu = null;
		Specializari specializare = null;
		if (nume != null && !nume.isEmpty()) {
			try {
				tx = session.beginTransaction();
				serie = (Serii) session.get(Serii.class, idSerie);
				anStudiu = (AnStudiu) session.get(AnStudiu.class, idAnStudiu);
				specializare = (Specializari) session.get(Specializari.class, idSpecializare);
				serie.setNume(nume);
				serie.setAnStudiu(anStudiu);
				serie.setSpecializare(specializare);
				session.update(serie);
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
