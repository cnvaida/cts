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

import delphi.netstudent.model.AnStudiu;
import delphi.netstudent.model.FormaFinantare;
import delphi.netstudent.model.Grupe;
import delphi.netstudent.model.Serii;
import delphi.netstudent.model.Specializari;
import delphi.netstudent.model.Student;
import delphi.netstudent.util.HibernateUtil;

public class EditareStudenti implements Comanda {

	@Override
	public void executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("panou_deschis", "3");
		IntegerValidator integerValidator = new IntegerValidator();
		EmailValidator emailValidator = EmailValidator.getInstance();
		String idStudent = request.getParameter("idStudent");
		String nume = request.getParameter("nume");
		String prenume = request.getParameter("prenume");
		String cod_student = request.getParameter("cod_student");
		String form_specializare = request.getParameter("specializare");
		String grupa = request.getParameter("grupa");
		String serie = request.getParameter("serie");
		String forma_finantare = request.getParameter("forma_finantare");
		String an_studiu = request.getParameter("an_studiu");
		String email = request.getParameter("email");
		
		
		if ((nume != null && !nume.isEmpty()) && (prenume != null && !prenume.isEmpty()) && (cod_student != null && !cod_student.isEmpty()) && integerValidator.isValid(form_specializare) && integerValidator.isValid(grupa) && integerValidator.isValid(serie) && integerValidator.isValid(forma_finantare) && integerValidator.isValid(an_studiu) && emailValidator.isValid(email)) {
			Specializari specializare = null;
			AnStudiu anStudiu = null;
			FormaFinantare formaFinantare = null;
			Serii serieS = null;
			Grupe grupaG = null;
			Student stud = null;
			Session sessionS = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			try {
				tx = sessionS.beginTransaction();
				specializare = (Specializari) sessionS.get(Specializari.class, Integer.valueOf(form_specializare));
				anStudiu = (AnStudiu) sessionS.get(AnStudiu.class, Integer.valueOf(an_studiu));
				formaFinantare = (FormaFinantare) sessionS.get(FormaFinantare.class, Integer.valueOf(forma_finantare));
				serieS = (Serii) sessionS.get(Serii.class, Integer.valueOf(serie));
				grupaG = (Grupe) sessionS.get(Grupe.class, Integer.valueOf(grupa));
				stud = (Student) sessionS.get(Student.class, Integer.valueOf(idStudent));
				stud.setNume(nume);
				stud.setPrenume(prenume);
				stud.setCod_student(cod_student);
				stud.setAn_studiu(anStudiu);
				stud.setSpecializare(specializare);
				stud.setSeria(serieS);
				stud.setGrupa(grupaG);
				stud.setForma_finantare(formaFinantare);
				stud.setEmail(email);
				sessionS.update(stud);
				tx.commit();
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
		
		response.sendRedirect(request.getContextPath() + "/administrarestudenti.jsp");
	}
	
}	
