package cvsm.business.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import cvsm.business.interfaces.interceptors.TraceLog;
import cvsm.business.services.LoginService;
import cvsm.business.services.UserService;
import cvsm.model.entities.LoginEntity;
import cvsm.model.entities.UserEntity;

@Named
@ConversationScoped
public class InsertUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject private Conversation conversation;
	@Inject Logger log;
	@Inject LoginService loginService;
	@Inject UserService userService;
	
	private UserEntity newUser;
	private LoginEntity newLogin;
	private String tempUsername;
	
	@PostConstruct
	public void init(){
		
		log.info("InsertUser - PostConstruct Call");
	}
	
	// STEP 1: Controllo dell'username
	public String checkUsername(){
		if ( loginService.find(tempUsername) == null){
			
			newLogin=new LoginEntity();
			newUser=new UserEntity();
			
			newLogin.setUsername(tempUsername);
			newUser.setUsername(tempUsername);
			return "NewUserPopulate.xhtml";
		}
		return null;
	}
	
	// STEP 2: Dati popolati
	public String populateUser(){
		if(newLogin.getPassword()!=null && newUser.getName()!=null && newUser.getSurname()!=null){
			return "NewUserConfirmation.xhtml";
		}
		else{
			return null;
		}
	}
	
	//STEP 3: Conferma e salva
	public String confirmAndClose(){
		try{
			loginService.save(newLogin);
			userService.save(newUser);
			log.info("Utente "+newUser.getUsername()+" creato correttamente");
			endConversation();
			return "home.xhtml?faces-redirect=true";
		}
		catch(Exception ex){
			log.info("Errore nella creazione dell'Utente");
			endConversation();
			return null;
		}
	}
	public Conversation getConversation() { return conversation; }
	public void setConversation(Conversation conversation) { this.conversation = conversation; }
	
	public UserEntity getNewUser(){ return this.newUser; }
	public void setNewUser(UserEntity newUser){ this.newUser=newUser; }
	
	public LoginEntity getNewLogin(){ return this.newLogin; }
	public void setNewLogin(LoginEntity newLogin){ this.newLogin=newLogin; }
	
	public String getTempUsername(){ return this.tempUsername; }
	public void setTempUsername(String tempUsername){ this.tempUsername=tempUsername; }
	
	public void startConversation() throws IOException{
		if(!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()){
			conversation.begin();
		}
		else{
			FacesMessage message=new FacesMessage("InsertNewUser - Errore nell'avvio della conversation");
			log.info("InsertNewUser - Errore nell'avvio della conversation");
			FacesContext.getCurrentInstance().addMessage(null, message);
			FacesContext.getCurrentInstance().getExternalContext().redirect("../home.xhtml");
		}
	}
	protected void endConversation(){
		if(!conversation.isTransient()){
			conversation.end();
		}
		
	}
}
