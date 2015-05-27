package delphi.netstudent.business;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Grupe;
import delphi.netstudent.model.Serii;
import delphi.netstudent.util.HibernateUtil;

public class GrupePersistenceUtil {
	public static void addGrupa(String nume, Serii serie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Grupe grupa = new Grupe(nume, serie);
			session.save(grupa);
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
	
	public static List getGrupe() {
		List grupe = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			grupe = session.createQuery("FROM Grupe").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return grupe;
	}
	
	public static Grupe getGrupe(int idGrupa) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Grupe grupa = null;
		try {
			tx = session.beginTransaction();
			grupa = (Grupe) session.get(Grupe.class, idGrupa);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return grupa;
	}
}
