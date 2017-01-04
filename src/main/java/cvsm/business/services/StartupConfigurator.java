package cvsm.business.services;

import javax.enterprise.context.ApplicationScoped;
import javax.annotation.PostConstruct;
import javax.ejb.Startup;

@ApplicationScoped
@Startup
public class StartupConfigurator {

	//Bean di configurazione dell'applicazione. Tornerá utile per fare le operazioni di base per l'avvio corretto dell'applicazione
	@PostConstruct
	public void init(){
		System.out.println("Applicazione avviata correttamente, Singleton istanziato");
	}
}
