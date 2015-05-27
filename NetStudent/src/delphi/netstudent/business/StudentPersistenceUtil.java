package delphi.netstudent.business;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.AnStudiu;
import delphi.netstudent.model.FormaFinantare;
import delphi.netstudent.model.Grupe;
import delphi.netstudent.model.Serii;
import delphi.netstudent.model.Specializari;
import delphi.netstudent.model.Student;
import delphi.netstudent.util.HibernateUtil;

public class StudentPersistenceUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void addStudent(String nume, String prenume, String cod_student, Specializari specializare, Grupe grupa, Serii seria, FormaFinantare forma_finantare, AnStudiu an_studiu, String email, String parola, int privilegiu, Session session) {
		//Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Student stud = new Student(nume, prenume, cod_student, specializare, grupa, seria, forma_finantare, an_studiu, email, parola, privilegiu);
			session.save(stud);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public static List getStudents(int pageNumber) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List studenti = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("From Student");
			query = query.setFirstResult(5 * (pageNumber - 1));
			query.setMaxResults(5);
			studenti = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studenti;
	}
	
	public static List getStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List studenti = null;
		try {
			tx = session.beginTransaction();
			studenti = session.createQuery("FROM Student").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studenti;
	}
	
	public static Student getStudent(int studentID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Student stud = null;
		try {
			tx = session.beginTransaction();
			stud = (Student) session.get(Student.class, studentID);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stud;
	}
}
