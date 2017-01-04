package cvsm.business.beans.utl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Credentials {

	private String username;
	private String password;

	public void setUsername(String username){
		this.username=username;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public String getPassword(){
		return this.password;
	}
}
