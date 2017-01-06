package cvsm.business.beans;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cvsm.business.beans.utl.Credentials;
import cvsm.business.customQualifiers.LoggedIn;
import cvsm.business.services.LoginService;
import cvsm.business.services.UserService;
import cvsm.model.entities.LoginEntity;
import cvsm.model.entities.UserEntity;

@Named
@SessionScoped

public class Login implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject Logger log;
	@Inject Credentials credentials;
	@Inject UserService userService;
	@Inject LoginService loginService;
	
	private UserEntity user;
	private LoginEntity login;
	
	@PostConstruct
	public void init(){
		this.user=null;
	}
	
	public String doLogin(){
		log.info("Ricerca per username: "+credentials.getUsername());
		
		login=loginService.find(credentials.getUsername());
		
		if((login!=null) && (login.getPassword().equals(credentials.getPassword()))){
			log.info("Utente trovato!");
			user=userService.find(login.getUsername());
			log.info("User trovato con login: "+user.getUsername());
			
			return "home?faces-redirect=true";
		}
		else{
			log.info("User non trovato");
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
