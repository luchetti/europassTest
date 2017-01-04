package cvsm.business.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cvsm.business.beans.utl.Credentials;
import cvsm.business.customQualifiers.LoggedIn;
import cvsm.business.services.LoginService;
import cvsm.model.entities.UserEntity;

@Named
@SessionScoped
public class Login implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Credentials credentials;
	@Inject
	private LoginService loginService;
	
	private UserEntity user;
	
	@PostConstruct
	public void init(){
		this.user=null;
	}
	
	public String doLogin(){
		
		int userId=loginService.findUserId(credentials.getUsername(), credentials.getPassword());
		
		if(userId!=0){
			user=loginService.getUser(userId);
			return "home?faces-redirect=true";
		}
		else{
			return "index?faces-redirect=true";
		}
	}
	
	public String doLogout(){
		this.user=null;
		return "index?faces-redirect=true";
	}
	
	public boolean isLoggedIn(){
		return user!=null;
	}
	
	@Produces @LoggedIn
	public UserEntity getUser(){
		return this.user;
	}
}
