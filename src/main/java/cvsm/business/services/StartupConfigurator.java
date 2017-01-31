package cvsm.business.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cvsm.business.beans.PageNavigator;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;

@Named("app")
@ApplicationScoped
@Startup
public class StartupConfigurator {

	//Bean di configurazione dell'applicazione. Tornerá utile per fare le operazioni di base per l'avvio corretto dell'applicazione
	
	private boolean on;
	
	@PostConstruct
	public void init(){
		System.out.println("Applicazione avviata correttamente, Singleton istanziato");
		on=true;
	}
	
	public boolean isOn(){
		return this.on;
	}
}
