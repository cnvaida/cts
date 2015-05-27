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
import delphi.netstudent.util.HibernateUtil;

public class StergeGrupa implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "7");
		int idGrupa = Integer.valueOf(request.getParameter("idGrupa"));
		Session session =  HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Grupe grupa = null;
		try {
			tx = session.beginTransaction();
			grupa = (Grupe) session.get(Grupe.class, idGrupa);
			session.delete(grupa);
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
