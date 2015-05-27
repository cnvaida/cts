package delphi.netstudent.business;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.AnStudiu;
import delphi.netstudent.model.Serii;
import delphi.netstudent.model.Specializari;
import delphi.netstudent.util.HibernateUtil;

public class SeriiPersistenceUtil {
	public static void addSerie(String nume, Specializari specializare, AnStudiu anStudiu) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Serii serie = new Serii(nume, specializare, anStudiu);
			session.save(serie);
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
	
	public static List getSerii() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List serii = null;
		try {
			tx = session.beginTransaction();
			serii = session.createQuery("FROM Serii").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return serii;
	}
	
	public static Serii getSerie(int idSerie) {
		Serii serie = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			serie = (Serii) session.get(Serii.class, idSerie);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return serie;
	}
}
