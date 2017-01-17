package cvsm.business.beans;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import cvsm.business.beans.utl.Credentials;
import cvsm.business.customQualifiers.LoggedIn;
import cvsm.business.services.UserService;
import cvsm.model.entities.UserEntity;
import cvsm.utils.PasswordSalter;

@Named
@SessionScoped

public class Login implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject Logger log;
	@Inject Credentials credentials;
	@Inject UserService userService;
	
	private UserEntity user;
	private boolean logged;
	private String originalURL;
	
	@PostConstruct
	public void init(){
		this.user=null;
		this.logged=false;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/secure/home.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
        log.info("OriginalURL: "+originalURL);
	}
	
	public void doLogin() throws IOException{
		
		log.info("Ricerca per username: "+credentials.getUsername());
		
		FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            request.login(credentials.getUsername(), PasswordSalter.saltPassword(credentials.getPassword(), "SHA-256"));
        }
        catch (ServletException e) {
          context.addMessage(null, new FacesMessage("Unknown login"));
        }
        
        logged=true;

        externalContext.redirect(originalURL);
	}
	
	public void doLogout() throws IOException{
		ExternalContext externalContext=FacesContext.getCurrentInstance().getExternalContext();
		
		this.user=null;
		logged = false;
		externalContext.invalidateSession();
		externalContext.redirect(externalContext.getRequestContextPath()+"/index.xhtml");
	}
	
	public boolean isLoggedIn(){
		return logged;
	}
	
	@Produces @LoggedIn
	public UserEntity getUser(){
		if (user == null) {
            Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            if (principal != null) {
                user = userService.find(principal.getName()); // Find User by j_username.
            }
        }
        return user;
	}
}
