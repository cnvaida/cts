package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Student;
import delphi.netstudent.util.HibernateUtil;

public class StergeStudent implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("panou_deschis", "3");
		
		int idStudent = Integer.parseInt(request.getParameter("idStergere"));
		
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = hibernateSession.beginTransaction();
			Student stud = (Student) hibernateSession.get(Student.class, idStudent);
			hibernateSession.delete(stud);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			hibernateSession.close();
		}
		response.sendRedirect(request.getContextPath() + "/administrarestudenti.jsp");
	}

}
