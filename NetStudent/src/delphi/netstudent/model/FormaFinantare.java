package delphi.netstudent.model;

import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="forma_finantare")
public class FormaFinantare {
	
	@Id @GeneratedValue
	@Column(name="id_forma_finantare")
	private int id;
	
	@Column(name="DENUMIRE")
	private String denumire;
	
	@OneToMany(mappedBy="forma_finantare")
	@Cascade({CascadeType.DELETE_ORPHAN})
	private List<Student> studenti;
	
	public FormaFinantare() {
		
	}

	public FormaFinantare(String denumire) {
		this.denumire = denumire;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (id < 0) {
			throw new IllegalArgumentException("Id negativ");
		}
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		if (denumire == null || denumire.equals("")) {
			throw new IllegalArgumentException("Denumire null sau empty");
		}
		this.denumire = denumire;
	}

	public List<Student> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}
	
	
}
