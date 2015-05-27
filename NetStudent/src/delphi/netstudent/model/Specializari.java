package delphi.netstudent.model;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="specializari")
public class Specializari {
	@Id @GeneratedValue
	@Column(name="ID_SPECIALIZARE")
	private int id;
	
	@Column(name="DENUMIRE")
	private String denumire;
	
	@OneToMany(mappedBy="specializare")
	@Cascade({CascadeType.DELETE_ORPHAN})
	private List<Serii> serii;
	
	@OneToMany(mappedBy="specializare")
	@Cascade({CascadeType.DELETE_ORPHAN})
	private List<Student> studenti;
	
	
	public List<Serii> getSerii() {
		return serii;
	}

	public void setSerii(List<Serii> serii) {
		this.serii = serii;
	}

	public Specializari() {
		
	}
	
	public Specializari(String denumire) {
		this.denumire = denumire;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}
	
}
