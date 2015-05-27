package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Specializari;
import delphi.netstudent.util.HibernateUtil;

public class AddSpecializari implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("panou_deschis", "9");
		String nume = request.getParameter("nume");
		if (nume != null && !nume.isEmpty()) {
			Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			Specializari specializare = null;
			try {
				tx = hibernateSession.beginTransaction();
				specializare = new Specializari(nume);
				hibernateSession.save(specializare);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			} finally {
				hibernateSession.close();
			}
		} else {
			session.setAttribute("eroare_date_formular", "Verificați corectitudinea datelor.");
		}
		response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
	}

}
