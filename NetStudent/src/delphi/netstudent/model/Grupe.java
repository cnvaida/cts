package delphi.netstudent.model;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="grupe")
public class Grupe {
	@Id @GeneratedValue
	@Column(name="ID_GRUPA")
	private int id;
	
	@Column(name="NUME")
	private String nume;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="id_serie")
	private Serii serie;
	
	@OneToMany(mappedBy="grupa")
	@Cascade({CascadeType.ALL})
	private List<Student> studenti;
	
	public Grupe() {
		
	}
	
	public Grupe(String nume, Serii serie) {
		this.nume = nume;
		this.serie = serie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("ID negativ");
		}
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		if (nume == null || nume.equals("")) {
			throw new IllegalArgumentException("Nume null sau empty");
		}
		this.nume = nume;
	}

	public Serii getSerie() {
		return serie;
	}

	public void setSerie(Serii serie) {
		if (serie == null) {
			throw new IllegalArgumentException("Grupa nu poate fi atribuita unei serii null");
		}
		this.serie = serie;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}
	
	
}
