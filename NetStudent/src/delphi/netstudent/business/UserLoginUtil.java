package delphi.netstudent.business;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Admin;
import delphi.netstudent.model.Student;
import delphi.netstudent.util.HibernateUtil;

public class UserLoginUtil {
	/*
	 * Primeste ca parametrii numele studentului si parola obtinute prin form-ul de login
	 * si transferate aici de catre servlet-ul "Login";
	 */
	public static boolean checkUser(String username, String password) {
		boolean inregistrat = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Student stud = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM Student S WHERE S.cod_student = :cod and S.parola = :parola");
			query.setParameter("cod", username);
			query.setParameter("parola", password);
			List student = query.list();
			for (Iterator it = student.iterator(); it.hasNext();) {
				stud = (Student) it.next();
				inregistrat = true;
			}
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return inregistrat;
	}
	
	/*
	 * returneaza un obiect Student (proprietati ale utilizatorului care tocmai s-a logat) 
	 * pentru a putea fi atasat unei sesiuni. Aceasta metoda este accesata doar daca checkUser
	 * e true; 
	 */
	public static Student getStudent(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Student stud= null;
		try {
			tx= session.beginTransaction();
			Query query = session.createQuery("FROM Student S WHERE S.cod_student = :cod and S.parola = :parola");
			query.setParameter("cod", username);
			query.setParameter("parola", password);
			List student = query.list();
			for (Iterator it = student.iterator(); it.hasNext();) {
				stud = (Student) it.next();
			}
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
	
	public static boolean checkAdmin(String username, String password) {
		boolean inregistrat = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Admin stud = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM Admin A WHERE A.username = :user and A.password = :parola");
			query.setParameter("user", username);
			query.setParameter("parola", password);
			List student = query.list();
			for (Iterator it = student.iterator(); it.hasNext();) {
				stud = (Admin) it.next();
				inregistrat = true;
			}
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return inregistrat;
	}
	
	public static Admin getAdmin(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Admin stud= null;
		try {
			tx= session.beginTransaction();
			Query query = session.createQuery("FROM Admin A WHERE A.username = :user and A.password = :parola");
			query.setParameter("user", username);
			query.setParameter("parola", password);
			List student = query.list();
			for (Iterator it = student.iterator(); it.hasNext();) {
				stud = (Admin) it.next();
			}
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
