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

public class StergeAn implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("panou_deschis", "5");
		int idAn = Integer.parseInt(request.getParameter("idAn"));
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		AnStudiu anStudiu = null;
		try {
			tx = hibernateSession.beginTransaction();
			anStudiu = (AnStudiu) hibernateSession.get(AnStudiu.class, idAn);
			hibernateSession.delete(anStudiu);
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
