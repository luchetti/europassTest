package cvsm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries(
	@NamedQuery(name = "user.getUserById", query = "SELECT u FROM UserEntity u WHERE u.username = :username")
)
@Table(name="TBL_USER")
public class UserEntity {

	@Id
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
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
