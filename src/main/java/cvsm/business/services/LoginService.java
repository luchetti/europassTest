package cvsm.business.services;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceContext;

import cvsm.model.entities.LoginEntity;
import cvsm.model.entities.UserEntity;

@Stateless
public class LoginService {

	@PersistenceContext(unitName="EuropassTest")
	EntityManager em;
	
	@Inject
	Logger log;
	
	public UserEntity find(String username){
		 return em.find(UserEntity.class, username);
	}
	public boolean isUserAvaiable (String username){
		log.info(this.getClass().getName()+"Enter");
		
		return this.find(username) == null? true:false;
	}
	public int findUserId(String username, String password){
		
		log.info(this.getClass().getName()+"Enter");
		TypedQuery<LoginEntity> query=em.createNamedQuery("login.findUserId", LoginEntity.class).setParameter("u", username).setParameter("p", password);
		
		try{
			LoginEntity entity=query.getSingleResult();
			return 0;// entity.getId();
		}
		catch(NoResultException ex){
			FacesMessage message=new FacesMessage("User e/o pass non corretti");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return 0;
		}
		
	}
	
	public UserEntity getUser(int userId){
		
		List <UserEntity> user;
		
		TypedQuery<UserEntity> query=em.createNamedQuery("user.getUserById", UserEntity.class).setParameter("userId",userId);
		try{
			user=query.getResultList();
			return user.get(0);
		}
		catch(EntityNotFoundException ex){
			FacesMessage message=new FacesMessage("User non trovato, errore grave");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}	
	}
}
