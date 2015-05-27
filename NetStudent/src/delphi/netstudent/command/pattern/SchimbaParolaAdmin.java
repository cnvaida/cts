package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.business.UserLoginUtil;
import delphi.netstudent.exceptions.PasswordTooSmallException;
import delphi.netstudent.model.Admin;
import delphi.netstudent.util.HibernateUtil;

public class SchimbaParolaAdmin implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("panou_deschis", "10");
		String parolaVeche = request.getParameter("parolaVeche");
		String parolaNoua = request.getParameter("parolaNoua");
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		if (parolaVeche != null && !parolaVeche.isEmpty() && parolaNoua != null && !parolaNoua.isEmpty()) {
			Admin admin = (Admin) session.getAttribute("currentAdmin");
			if (admin.getUsername() != null) {
				if (UserLoginUtil.checkAdmin(admin.getUsername(), parolaVeche)) {
					try {
						tx = hibernateSession.beginTransaction();
						Admin adm = (Admin) hibernateSession.get(Admin.class, admin.getId());
						adm.setPassword(parolaNoua);
						hibernateSession.update(adm);
						tx.commit();
					} catch (HibernateException e) {
						if (tx != null) {
							tx.rollback();
						}
						e.printStackTrace();
					} catch (PasswordTooSmallException e) {
						e.printStackTrace();
					} finally {
						hibernateSession.close();
					}
				} else {
					session.setAttribute("eroare_date_formular", "Parola greșită.");
				}
			}
		}
		response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
	}

}
