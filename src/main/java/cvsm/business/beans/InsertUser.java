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

import cvsm.business.services.LoginService;
import cvsm.model.entities.LoginEntity;
import cvsm.model.entities.UserEntity;

@Named
@ConversationScoped
public class InsertUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject private Conversation conversation;
	@Inject Logger log;
	@Inject LoginService loginService;
	
	private UserEntity newUser;
	private LoginEntity newLogin;
	private String tempUsername;
	
	@PostConstruct
	public void init(){
		
		log.info("InsertUser - PostConstruct Call");
	}
	
	// STEP 1: Controllo dell'username
	public String checkUsername(){
		if ( ! loginService.isUserAvaiable(tempUsername)){
			
			newLogin=new LoginEntity();
			newUser=new UserEntity();
			
			newLogin.setUsername(tempUsername);
			newUser.setUsername(tempUsername);
			
		}
		return "";
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
