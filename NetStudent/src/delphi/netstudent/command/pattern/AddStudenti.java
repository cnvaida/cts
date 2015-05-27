package delphi.netstudent.command.pattern;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.business.StudentPersistenceUtil;
import delphi.netstudent.model.AnStudiu;
import delphi.netstudent.model.FormaFinantare;
import delphi.netstudent.model.Grupe;
import delphi.netstudent.model.Serii;
import delphi.netstudent.model.Specializari;
import delphi.netstudent.util.HibernateUtil;

public class AddStudenti implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("panou_deschis", "2");
		IntegerValidator integerValidator = new IntegerValidator();
		EmailValidator emailValidator = EmailValidator.getInstance();
		
		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		String cod_student = request.getParameter("cod_student");
		String form_specializare = request.getParameter("specializare");
		String grupa = request.getParameter("grupa");
		String serie = request.getParameter("serie");
		String forma_finantare = request.getParameter("forma_finantare");
		String an_studiu = request.getParameter("an_studiu");
		String email = request.getParameter("email");
		String parola = request.getParameter("parola");
		int categorie = 0;
		
		if ((nume != null && !nume.isEmpty()) && (prenume != null && !prenume.isEmpty()) && (cod_student != null && !cod_student.isEmpty()) && integerValidator.isValid(form_specializare) && integerValidator.isValid(grupa) && integerValidator.isValid(serie) && integerValidator.isValid(forma_finantare) && integerValidator.isValid(an_studiu) && emailValidator.isValid(email) && (parola != null && !parola.isEmpty())) {
			Specializari specializare = null;
			AnStudiu anStudiu = null;
			FormaFinantare formaFinantare = null;
			Serii serieS = null;
			Grupe grupaG = null;
			Session sessionS = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = sessionS.beginTransaction();
				specializare = (Specializari) sessionS.get(Specializari.class, Integer.valueOf(form_specializare));
				anStudiu = (AnStudiu) sessionS.get(AnStudiu.class, Integer.valueOf(an_studiu));
				formaFinantare = (FormaFinantare) sessionS.get(FormaFinantare.class, Integer.valueOf(forma_finantare));
				serieS = (Serii) sessionS.get(Serii.class, Integer.valueOf(serie));
				grupaG = (Grupe) sessionS.get(Grupe.class, Integer.valueOf(grupa));
				tx.commit();
				StudentPersistenceUtil.addStudent(nume, prenume, cod_student, specializare, grupaG, serieS, formaFinantare, anStudiu, email, parola, categorie, sessionS);
			} catch (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				session.setAttribute("eroare_date_formular", e.getMessage());
			} finally {
				if (sessionS.isOpen()) {
					sessionS.close();
				}
			}
		} else {
			session.setAttribute("eroare_date_formular", "Verificați corectitudinea datelor.");
		}
		response.sendRedirect(request.getContextPath() + "/cpanel.jsp");
	}
}
