package delphi.netstudent.model;

import java.util.Set;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="anunturi")
public class Anunt {
	@Id @GeneratedValue
	@Column(name="id_anunt")
	private int id;
	
	@Column(name="anunt")
	private String anunt;
	
	public Anunt() {
		
	}
	
	public Anunt(String text) {
		this.anunt = text;
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

	public String getAnunt() {
		return anunt;
	}

	public void setAnunt(String anunt) {
		if (anunt == null || anunt.equals("")) {
			throw new IllegalArgumentException("Anuntul este null sau gol");
		}
		this.anunt = anunt;
	}
	
	
}
