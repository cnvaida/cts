package delphi.netstudent.model;

import java.util.List;

import javassist.expr.Instanceof;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="an_studiu")
public class AnStudiu {
	@Id @GeneratedValue
	@Column(name="id_an_studiu")
	private int id;
	
	@Column(name="nume")
	private String nume;
	
	@OneToMany(mappedBy="anStudiu")
	@Cascade({CascadeType.ALL})
	private List<Serii> serii;
	
	@OneToMany(mappedBy="an_studiu")
	@Cascade({CascadeType.ALL})
	private List<Student> studenti;
	
	public List<Serii> getSerii() {
		return serii;
	}

	public void setSerii(List<Serii> serii) {
		this.serii = serii;
	}

	public AnStudiu() {
		
	}

	public AnStudiu(String nume) {
		this.nume = nume;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		if (nume == null || nume.equals("")) {
			throw new IllegalArgumentException("Nume an studiu null");
		}
		this.nume = nume;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}
	
}
