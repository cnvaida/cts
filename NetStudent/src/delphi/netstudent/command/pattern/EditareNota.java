package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Materii;
import delphi.netstudent.model.Note;
import delphi.netstudent.model.Student;
import delphi.netstudent.util.HibernateUtil;

public class EditareNota implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "0");
		int notaExamen = Integer.valueOf(request.getParameter("nota"));
		int idMaterie = Integer.valueOf(request.getParameter("materie"));
		int idStudent = Integer.valueOf(request.getParameter("student"));
		long idNota = Long.valueOf(request.getParameter("idNota"));
		String calificativ = request.getParameter("calificativ");
		if (notaExamen != 0 && idMaterie != 0 && idStudent != 0) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			Materii materie = null;
			Student student = null;
			Note nota = null;
			try {
				tx = session.beginTransaction();
				materie = (Materii) session.get(Materii.class, idMaterie);
				student = (Student) session.get(Student.class, idStudent);
				nota = (Note) session.get(Note.class, idNota);
				nota.setNota(notaExamen);
				nota.setCalificativ(calificativ);
				nota.setMaterie(materie);
				nota.setStudent(student);
				session.update(nota);
				tx.commit();
			} catch  (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		response.sendRedirect(request.getContextPath() + "/administrarenote.jsp");
	}

}
