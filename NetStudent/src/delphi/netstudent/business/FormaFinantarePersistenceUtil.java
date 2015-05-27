package delphi.netstudent.business;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.FormaFinantare;
import delphi.netstudent.util.HibernateUtil;

public class FormaFinantarePersistenceUtil {
	public static void addFormaFinantare(String nume) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			FormaFinantare formaFinantare = new FormaFinantare(nume);
			session.save(formaFinantare);
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
	
	public static List getFormeFinantare() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List formeFinantare = null;
		try {
			tx = session.beginTransaction();
			formeFinantare = session.createQuery("FROM FormaFinantare").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return formeFinantare;
	}
	
	public static FormaFinantare getFormaFinantare(int idFormaFinantare) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		FormaFinantare formaFinantare = null;
		try {
			tx = session.beginTransaction();
			formaFinantare = (FormaFinantare) session.get(FormaFinantare.class, idFormaFinantare);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return formaFinantare;
	}
}
