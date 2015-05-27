package delphi.netstudent.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="note")
public class Note {
	@Id @GeneratedValue
	@Column(name="id_nota")
	private long idNota;
	
	@Column(name="NOTA")
	private int nota;
	
	@Column(name="CALIFICATIV")
	private String calificativ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="ID_MATERIE")
	private Materii materie;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="ID_STUDENT")
	private Student student;
	
	public Note() {
		
	}
	
	public Note(int nota, String calificativ, Materii materie, Student student) {
		this.nota = nota;
		this.calificativ = calificativ;
		this.materie = materie;
		this.student = student;
	}
	
	public Note(int nota, Materii materie, Student student) {
		this.nota = nota;
		this.materie = materie;
		this.student = student;
	}
	
	public Note(String calificativ, Materii materie, Student student) {
		this.calificativ = calificativ;
		this.materie = materie;
		this.student = student;
	}

	public long getIdNota() {
		return idNota;
	}

	public void setIdNota(long idNota) {
		if (idNota < 0) {
			throw new IllegalArgumentException("Id negativ");
		}
		this.idNota = idNota;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		if (nota < 1 || nota > 10) {
			throw new IllegalArgumentException("Nota gresita");
		}
		this.nota = nota;
	}

	public String getCalificativ() {
		return calificativ;
	}

	public void setCalificativ(String calificativ) {
		if (calificativ == null || calificativ.equals("")) {
			throw new IllegalArgumentException("Calificativ null sau empty");
		}
		
		this.calificativ = calificativ;
	}

	public Materii getMaterie() {
		return materie;
	}

	public void setMaterie(Materii materie) {
		if (materie == null) {
			throw new IllegalArgumentException("Null");
		}
		this.materie = materie;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		if (student == null) {
			throw new IllegalArgumentException("Student null");
		}
		this.student = student;
	}

}
