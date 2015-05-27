package delphi.netstudent.model;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="serii")
public class Serii {
	@Id @GeneratedValue
	@Column(name="ID_SERIE")
	private int id;
	
	@Column(name="NUME")
	private String nume;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="id_specializare")
	private Specializari specializare;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinColumn(name="id_an_studiu")
	private AnStudiu anStudiu;
	
	@OneToMany(mappedBy="serie")
	@Cascade({CascadeType.ALL})
	private List<Grupe> grupa;
	
	@OneToMany(mappedBy="seria")
	@Cascade({CascadeType.ALL})
	private List<Student> studenti;
	
	public Serii() {
		
	}
		
	public Serii(String nume, Specializari specializare, AnStudiu anStudiu) {
		this.nume = nume;
		this.specializare = specializare;
		this.anStudiu = anStudiu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenumire() {
		return nume;
	}

	public void setDenumire(String denumire) {
		this.nume = denumire;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public Specializari getSpecializare() {
		return specializare;
	}

	public void setSpecializare(Specializari specializare) {
		this.specializare = specializare;
	}

	public AnStudiu getAnStudiu() {
		return anStudiu;
	}

	public void setAnStudiu(AnStudiu anStudiu) {
		this.anStudiu = anStudiu;
	}

	public List<Grupe> getGrupa() {
		return grupa;
	}

	public void setGrupa(List<Grupe> grupa) {
		this.grupa = grupa;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}
	
}
