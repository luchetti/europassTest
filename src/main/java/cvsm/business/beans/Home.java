package cvsm.business.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cvsm.business.customQualifiers.LoggedIn;
import cvsm.model.entities.UserEntity;

@Named
@SessionScoped
public class Home implements Serializable {


	private static final long serialVersionUID = 1L;

	@Inject @LoggedIn private transient UserEntity currentUser;
	
	public UserEntity getCurrentUser(){
		return this.currentUser;
	}
}
