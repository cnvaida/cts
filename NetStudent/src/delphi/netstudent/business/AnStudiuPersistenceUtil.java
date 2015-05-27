package delphi.netstudent.business;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.AnStudiu;
import delphi.netstudent.util.HibernateUtil;

public class AnStudiuPersistenceUtil {
	public static void addAnStudiu(String nume_an) {
		if (nume_an == null) {
			throw new NullPointerException("Nume an null");
		}
		
		if (nume_an.length() == 0 || nume_an.equalsIgnoreCase("")) {
			throw new IllegalArgumentException("Numele este gol");
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			AnStudiu an = new AnStudiu(nume_an);
			session.save(an);
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
	
	public static List getAniStudiu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List aniStudiu = null;
		try {
			tx = session.beginTransaction();
			aniStudiu = session.createQuery("FROM AnStudiu").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return aniStudiu;
	}
	
	public static AnStudiu getAnStudiu(int idAn) {
		AnStudiu anStudiu = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			anStudiu = (AnStudiu) session.get(AnStudiu.class, idAn);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return anStudiu;
	}
}
