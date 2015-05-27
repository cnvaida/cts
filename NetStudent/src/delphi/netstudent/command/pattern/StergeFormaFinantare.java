package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.FormaFinantare;
import delphi.netstudent.util.HibernateUtil;

public class StergeFormaFinantare implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "6");
		int idFormaFinantare = Integer.valueOf(request.getParameter("idFormaFinantare"));
		Session session =  HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		FormaFinantare formaFinantare = null;
		try {
			tx = session.beginTransaction();
			formaFinantare = (FormaFinantare) session.get(FormaFinantare.class, idFormaFinantare);
			session.delete(formaFinantare);
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
