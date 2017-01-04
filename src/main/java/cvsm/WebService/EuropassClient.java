package cvsm.WebService;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Stateless
public class EuropassClient{

	@Inject
	Logger log;
	private Client client;
	private Response response;
	
	public String getEuropassCv(byte[] curriculum){
		
		String xml;
		
		try {
			xml=Unirest.post("https://europass.cedefop.europa.eu/rest/v1/document/extraction").header("Content-Type", "application/pdf").body(curriculum).asString().getBody();
		} catch (UnirestException e) {
			log.info("Errore durante chiamata REST Europass");
			return "";
		}
		catch (NullPointerException ex){
			log.info("Curriculum array vuoto");
			return "";
		}
		return xml;
		
	}
	
}
