package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Anunt;
import delphi.netstudent.util.HibernateUtil;

public class StergeAnunt implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("idAnunt"));
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "11");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Anunt anunt = null;
		try {
			tx = session.beginTransaction();
			anunt = (Anunt) session.get(Anunt.class, id);
			session.delete(anunt);
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
