package delphi.netstudent.business;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Note;
import delphi.netstudent.model.Student;
import delphi.netstudent.util.HibernateUtil;

public class NotePersistenceUtil {
	public static List getNote() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List note = null;
		try {
			tx = session.beginTransaction();
			note = session.createQuery("FROM Note").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return note;
	}
	
	public static Note getNota(long idNota) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Note nota = null;
		try {
			tx = session.beginTransaction();
			nota = (Note) session.get(Note.class, idNota);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return nota;
	}
	
	public static List getNote(int pageNumber) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List note = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("From Note");
			query = query.setFirstResult(7 * (pageNumber - 1));
			query.setMaxResults(7);
			note = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return note;
	}
}
