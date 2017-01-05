package cvsm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="TBL_USER")
public class UserEntity {

	@Id
	@Column(name="USER_USERNAME")
	private String username;
	
	@Column(name="USER_NAME")
	private String name;
	
	@Column(name="USER_SURNAME")
	private String surname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
