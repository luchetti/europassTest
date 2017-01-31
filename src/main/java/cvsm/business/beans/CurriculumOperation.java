package cvsm.business.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import cvsm.WebService.EuropassClient;
import cvsm.business.interfaces.interceptors.TraceLog;

@Named
@ConversationScoped
@TraceLog
public class CurriculumOperation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	EuropassClient europassClient;
	@Inject
	Conversation conversation;
	@Inject
	Logger log;
	@Inject
	EuropassClient europass;
	
	private boolean useEuropass=false;
	private UploadedFile uploadedCurriculum;
	private byte[] byteCurriculum;
	private String xmlCurriculum;
	
	protected void startConversation(){
		if(!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()){
			try{
				conversation.begin();
				log.info("Conversazione iniziata, ID: "+conversation.getId());
			}
			catch(Exception ex){
				log.info("errore inizializzazione conversation");
				ex.printStackTrace();
			}
		}
		else{
			log.info("Conversazione già iniziata, ID: "+conversation.getId());
		}
	}
	
	public void fileUpload(FileUploadEvent event){
		if(conversation.getId()!=null){
			startConversation();
		}
		log.info("Conversazione iniziata, id: "+conversation.getId());
		uploadedCurriculum=event.getFile();
		
		try {
			byteCurriculum=IOUtils.toByteArray(uploadedCurriculum.getInputstream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info("Errore nella conversione del curriculum");
		}
		
		if(uploadedCurriculum!=null){
			log.info("Caricato correttamente curriculum di: "+uploadedCurriculum.getSize());
			if(useEuropass){
				log.info("Hai deciso di usare Europass");
				xmlCurriculum=europassClient.getEuropassCv(byteCurriculum);
				
				if(xmlCurriculum!=null){
					log.info("Convertito correttamente in XML");
				}
				else{
					log.info("errore nella conversione in XML");
				}
			}
		}
		else{
			log.info("Errore nell'upload. Riprovare");
		}
		
	}
		
	//Setters/Getters
	public void setConversation (Conversation conversation){	this.conversation=conversation;	}
	public Conversation getConversation(){	return this.conversation;	}
	public void setUseEuropass(boolean useEuropass){	this.useEuropass=useEuropass;	}
	public boolean getUseEuropass(){	return this.useEuropass;	}
	public void setUploadedCurriculum (UploadedFile cv){	this.uploadedCurriculum=cv;	}
	public UploadedFile getUploadedCurriculum(){	return this.uploadedCurriculum;	}
	public void setByteCurriculum(byte[] byteCurriculum){ this.byteCurriculum=byteCurriculum;	}
	public byte[] getByteCurriculum(){	return this.byteCurriculum;	}

}