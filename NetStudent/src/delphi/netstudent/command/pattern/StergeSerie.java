package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Serii;
import delphi.netstudent.util.HibernateUtil;

public class StergeSerie implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "8");
		int idSerie = Integer.valueOf(request.getParameter("idSerie"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Serii serie = null;
		try {
			tx = session.beginTransaction();
			serie = (Serii) session.get(Serii.class, idSerie);
			session.delete(serie);
			tx.commit();
		} catch  (HibernateException e) {
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
