package delphi.netstudent.model;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import delphi.netstudent.observer.pattern.Observator;
import delphi.netstudent.util.HibernateUtil;
import delphi.netstudent.util.SendEmail;

@Entity
@Table(name="student")
public class Student implements Observator {
	@Id @GeneratedValue
	@Column(name="id_student")
	private int id;
	
	@Column(name="NUME")
	private String nume;
	
	@Column(name="PRENUME")
	private String prenume;
	
	@Column(name="COD_STUDENT")
	private String cod_student;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="id_specializare")
	private Specializari specializare;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="id_grupa")
	private Grupe grupa;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="id_serie")
	private Serii seria;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="id_forma_finantare")
	private FormaFinantare forma_finantare;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="id_an_studiu")
	private AnStudiu an_studiu;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PAROLA")
	private String parola;
	
	@Column(name="PRIVILEGIU")
	private int privilegiu;
	
	@OneToMany(mappedBy="student", fetch = FetchType.EAGER)
	@Cascade({CascadeType.DELETE_ORPHAN})
	private Set<Note> note;
	
	public Student() {
		
	}
	
	public Student(String nume, String prenume, String cod_student, Specializari specializare, Grupe grupa, Serii seria, FormaFinantare forma_finantare, AnStudiu an_studiu, String email, String parola, int privilegiu) {
		this.nume = nume;
		this.prenume = prenume;
		this.cod_student = cod_student;
		this.specializare = specializare;
		this.grupa = grupa;
		this.seria = seria;
		this.forma_finantare = forma_finantare;
		this.an_studiu = an_studiu;
		this.email = email;
		this.parola = parola;
		this.privilegiu = privilegiu;
	}

	public String getCod_student() {
		return cod_student;
	}

	public void setCod_student(String cod_student) {
		this.cod_student = cod_student;
	}

	public Specializari getSpecializare() {
		return specializare;
	}

	public void setSpecializare(Specializari specializare) {
		this.specializare = specializare;
	}

	public Grupe getGrupa() {
		return grupa;
	}

	public void setGrupa(Grupe grupa) {
		this.grupa = grupa;
	}

	public Serii getSeria() {
		return seria;
	}

	public void setSeria(Serii seria) {
		this.seria = seria;
	}

	public FormaFinantare getForma_finantare() {
		return forma_finantare;
	}

	public void setForma_finantare(FormaFinantare forma_finantare) {
		this.forma_finantare = forma_finantare;
	}

	public AnStudiu getAn_studiu() {
		return an_studiu;
	}

	public void setAn_studiu(AnStudiu an_studiu) {
		this.an_studiu = an_studiu;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public int getPrivilegiu() {
		return privilegiu;
	}

	public void setPrivilegiu(int privilegiu) {
		this.privilegiu = privilegiu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Note> getNote() {
		return note;
	}

	public void setNote(Set<Note> note) {
		this.note = note;
	}

	@Override
	public void update(String email, String message) {
		Session hibernateSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Student stud = null;
		if (email != null && !email.isEmpty()) {
			try {
				tx = hibernateSession.beginTransaction();
				Query query = hibernateSession.createQuery("FROM Student S WHERE S.email = :email");
				query.setParameter("email", email);
				List student = query.list();
				for (Iterator it = student.iterator(); it.hasNext();) {
					stud = (Student) it.next();
				}
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null) {
					tx.rollback();
				}
				e.printStackTrace();
			} finally {
				hibernateSession.close();
			}
		}
		
		if (stud != null) {
			String catre[] = new String[1];
			catre[0] = stud.getEmail();
			SendEmail.sendEmail("cont.proba2015", "CalitateSoftware", "Nota introdusa", "Buna ziua, " + stud.getNume() + " " + stud.getPrenume() + ", o noua nota a fost introdusa: " + message, catre);	 
		} 
		
	}
}
