package delphi.netstudent.model;

import javax.persistence.*;

import delphi.netstudent.exceptions.InvalidEmailException;
import delphi.netstudent.exceptions.PasswordTooSmallException;
import delphi.netstudent.util.Validator;

@Entity
@Table(name="admins")
public class Admin {
	@Id @GeneratedValue
	@Column(name="id_admin")
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="privilegiu")
	private int privilegiu;
	
	public Admin() {
		
	}
	
	public Admin(String username, String password, String email, int privilegiu) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.privilegiu = privilegiu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username == null || username == "") {
			throw new IllegalArgumentException("Nume username null");
		}
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws PasswordTooSmallException, IllegalArgumentException {
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("Parola null");
		}
		
		if (password.length() < 6) {
			throw new PasswordTooSmallException("Parola este prea scurta!");
		}
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws InvalidEmailException {
		if (email == null || email == "") {
			throw new IllegalArgumentException("Email null");
		}
		
		if (!Validator.isValidEmail(email)) {
			throw new InvalidEmailException("Email incorect");
		}
		this.email = email;
	}

	public int getPrivilegiu() {
		return privilegiu;
	}

	public void setPrivilegiu(int privilegiu) {
		if (privilegiu != 2) {
			throw new IllegalArgumentException("Administratorului nu i se poate schimba statusul!");
		}
		this.privilegiu = privilegiu;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.username);
		sb.append(", ");
		sb.append(this.password);
		sb.append(", ");
		sb.append(this.email);
		sb.append(", ");
		sb.append(this.privilegiu);
		return sb.toString();
	}
	
}
