package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Note;
import delphi.netstudent.util.HibernateUtil;

public class StergeNota implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "0");
		long idNota = Long.valueOf(request.getParameter("idNota"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Note nota = null;
		try {
			tx = session.beginTransaction();
			nota = (Note) session.get(Note.class, idNota);
			
			session.delete(nota);
			tx.commit();
		} catch  (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		response.sendRedirect(request.getContextPath() + "/administrarenote.jsp");
	}

}
