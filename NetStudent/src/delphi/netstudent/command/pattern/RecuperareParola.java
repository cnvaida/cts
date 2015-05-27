package delphi.netstudent.command.pattern;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Student;
import delphi.netstudent.util.HibernateUtil;
import delphi.netstudent.util.SendEmail;

public class RecuperareParola implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		String email = request.getParameter("email");
		Transaction tx = null;
		Student stud = null;
		if (email != null && !email.isEmpty()) {
			try {
				tx = hibernateSession.beginTransaction();
				Query query = hibernateSession.createQuery("FROM Student S WHERE S.email = :email");
				query.setParameter("email", email);
				List student = query.list();
				for (Iterator it = student.iterator(); it.hasNext();) {
					stud = (Student) it.next();
				}
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			} finally {
				hibernateSession.close();
			}
		}
		
		if (stud != null) {
			String catre[] = new String[1];
			catre[0] = stud.getEmail();
		
			if (SendEmail.sendEmail("cn.vaida92@gmail.com", "ParolaEmail", "Subiect", "Hello, " + stud.getNume() + " " + stud.getPrenume() + ", numele tau de utilizator este: " + stud.getCod_student() + ", iar parola: " + stud.getParola(), catre)) {
				session.setAttribute("eroare_login", "Parola v-a fost trimisa");
				
			} 
		} else {
			session.setAttribute("eroare_login", "E-mail necunoscut");
			
		}
		
		response.sendRedirect(request.getContextPath() + "/start.jsp");
	}

}
