package cvsm.business.services;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cvsm.model.entities.LoginEntity;

@Named
@Stateless
public class LoginService {

	@PersistenceContext(unitName="EuropassTest")
	EntityManager em;
	
	@Inject
	Logger log;
	
	public LoginEntity find(String username){
		 return em.find(LoginEntity.class, username);
	}
	
	public void save(LoginEntity login){
		em.merge(login);
	}
	
	public boolean isUserAvaiable (String username){
		log.info(this.getClass().getName()+"Enter");
		
		return this.find(username) == null? true:false;
	}
	
}
