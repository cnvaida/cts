package delphi.netstudent.model;

import java.util.Set;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="materii")
public class Materii {
	@Id @GeneratedValue
	@Column(name="id_materie")
	private int id;
	
	@Column(name="DENUMIRE")
	private String denumire;
	
	@OneToMany(mappedBy="materie")
	@Cascade({CascadeType.DELETE_ORPHAN})
	private Set<Note> note;
	
	public Materii() {
		
	}
	
	public Materii(String denumire) {
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

	public Set<Note> getNote() {
		return note;
	}

	public void setNote(Set<Note> note) {
		this.note = note;
	}
	
	
}
