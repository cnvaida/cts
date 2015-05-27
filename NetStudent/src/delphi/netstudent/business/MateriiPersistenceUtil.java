package delphi.netstudent.business;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import delphi.netstudent.model.Materii;
import delphi.netstudent.util.HibernateUtil;

public class MateriiPersistenceUtil {
	public static List getMaterii() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List materii = null;
		try {
			tx = session.beginTransaction();
			materii = session.createQuery("FROM Materii").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return materii;
	}
	
	public static Materii getMaterie(int idMaterie) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Materii materie = null;
		try {
			tx = session.beginTransaction();
			materie = (Materii) session.get(Materii.class, idMaterie);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return materie;
	}
}
