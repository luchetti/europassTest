package cvsm.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="login.findUserId", query="SELECT l FROM LoginEntity l where l.username=:u AND l.password=:p"),
	@NamedQuery(name="login.isUserAvaiable", query="SELECT l FROM LoginEntity l where l.username=:u")
})
@Table (name ="TBL_LOGIN")
public class LoginEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name ="LOGIN_USER")
	private String username;
	
	@Column (name = "LOGIN_PASS")
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
