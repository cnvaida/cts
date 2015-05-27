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
import delphi.netstudent.observer.pattern.Observator;
import delphi.netstudent.observer.pattern.Subiect;
import delphi.netstudent.util.HibernateUtil;

public class AddNota implements Comanda, Subiect {

	private Observator o;
	private String email;
	private String message;
	
	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "0");
		int notaExamen = Integer.valueOf(request.getParameter("nota"));
		int idMaterie = Integer.valueOf(request.getParameter("materie"));
		int idStudent = Integer.valueOf(request.getParameter("student"));
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
				nota = new Note(notaExamen, calificativ, materie, student);
				session.save(nota);
				tx.commit();
				atribuireObservator(student);
				trimiteEmail(student.getEmail(), nota.getMaterie().getDenumire() + " " + nota.getNota());
			} catch  (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
	}

	@Override
	public void atribuireObservator(Observator s) {
		o = s;
	}

	@Override
	public void inlaturareObservator(Observator s) {
		
	}

	@Override
	public void notificare() {
		o.update(email, message);
	}

	public void trimiteEmail(String email, String message) {
		this.message = message;
		this.email = email;
		notificare();
	}
}
