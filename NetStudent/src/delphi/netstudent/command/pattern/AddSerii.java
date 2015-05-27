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

public class AddSerii implements Comanda {
	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "8");
		String nume = request.getParameter("nume");
		int specializareID = Integer.valueOf(request.getParameter("specializare"));
		int anStudiuID = Integer.valueOf(request.getParameter("an_studiu"));
		Specializari specializare = null;
		AnStudiu anStudiu = null;
		Serii serie = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			specializare = (Specializari) session.get(Specializari.class, specializareID);
			anStudiu = (AnStudiu) session.get(AnStudiu.class, anStudiuID);
			serie = new Serii(nume, specializare, anStudiu);
			session.save(serie);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
	}
}
