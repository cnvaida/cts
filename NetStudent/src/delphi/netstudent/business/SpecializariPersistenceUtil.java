package delphi.netstudent.business;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Specializari;
import delphi.netstudent.util.HibernateUtil;

public class SpecializariPersistenceUtil {
	
	public static void addSpecializare(String nume) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Specializari specializare = new Specializari(nume);
			session.save(specializare);
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
	
	public static Specializari getSpecializare(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Specializari specializare = null;
		try {
			tx = session.beginTransaction();
			specializare = (Specializari) session.get(Specializari.class, id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return specializare;
	}
	
	public static List getSpecializari() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List specializari = null;
		try {
			tx = session.beginTransaction();
			specializari = session.createQuery("FROM Specializari").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return specializari;
	}
	
}
