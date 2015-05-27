package delphi.netstudent.command.pattern;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.business.StudentPersistenceUtil;
import delphi.netstudent.model.Anunt;
import delphi.netstudent.model.Student;
import delphi.netstudent.observer.pattern.Observator;
import delphi.netstudent.observer.pattern.Subiect;
import delphi.netstudent.util.HibernateUtil;

public class AddAnunt implements Comanda, Subiect {

	private Vector<Observator> observatori;
	private String email, mesaj;
	
	public AddAnunt() {
		observatori = new Vector<Observator>();
	}
	
	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession httpSession = request.getSession(true);
		httpSession.setAttribute("panou_deschis", "11");
		String text = request.getParameter("textAnunt");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Anunt anunt = null;
		List studenti = null;
		try {
			tx = session.beginTransaction();
			anunt = new Anunt(text);
			studenti = StudentPersistenceUtil.getStudents();
			session.save(anunt);
			tx.commit();
			for (Object s : studenti) {
				atribuireObservator((Student) s); 
				trimiteEmail(((Student) s).getEmail(), text);
			}
			
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

	@Override
	public void atribuireObservator(Observator o) {
		observatori.add(o);
	}

	@Override
	public void inlaturareObservator(Observator o) {
		observatori.remove(o);
	}

	@Override
	public void notificare() {
		for (Observator o : observatori) {
			o.update(email, mesaj);
		}
	}

	private void trimiteEmail(String email, String mesaj) {
		this.email = email;
		this.mesaj = mesaj;
		notificare();
	}
}
