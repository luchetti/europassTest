package cvsm.business.services;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cvsm.model.entities.UserEntity;

@Stateless
public class UserService {

	@PersistenceContext(unitName="EuropassTest")
	EntityManager em;
	
	@Inject
	Logger log;
	
	public UserEntity find(String username){
		 return em.find(UserEntity.class, username);
	}
	public void save(UserEntity user){
		em.merge(user);
	}
}
