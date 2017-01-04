package cvsm.business.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import cvsm.WebService.EuropassClient;
import cvsm.model.dependent.CurriculumVitae;

@Named
@ConversationScoped
public class Upload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Conversation conversation;
	@Inject
	Logger log;
	@Inject
	EuropassClient europass;
	
	
	private Part curriculum;
	private byte[] cv;
	
	
	public void startConversation(){
		if(!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()){
			conversation.begin();
			log.info("Conversazione iniziata, ID: "+conversation.getId());
		}
	}
	public void endConversation(){
		if(conversation.isTransient()){
			conversation.end();
			log.info("Conversazione terminata");
		}
	}
	public String upload(){
		log.info("upload - Enter");
		log.info("curriculum size: "+curriculum.getSize());
		
		return "uploadReview";
	}
	
	public String getXmlFromPdf(){
		
		log.info("getXmlFromPdf - Enter");
		log.info("curriculum size: "+cv.length);
		
		String cvXml = europass.getEuropassCv(cv);
		if(cvXml!=null){	
			log.info("Curriculum convertito correttamente");
			log.info(cvXml);
			return "uploadInsert";
		}else{
			endConversation();
			return "home?faces-redirect=true";
		} 
	}
	
	protected byte[] toByteArray(Part part) throws IOException{
		InputStream is = part.getInputStream();
		byte[] temp = IOUtils.toByteArray(is);
		return temp;
	}
	
	
	
	
	
	
	
	
	
	public void setConversation (Conversation conversation){
		this.conversation=conversation;
	}
	public Conversation getConversation(){
		return this.conversation;
	}
	
	public void setCurriculum (Part curriculum){
		
		  try{this.cv=toByteArray(curriculum);}
		catch(IOException ex){log.info("Errore nel caricamento del file, riprovare");}
	}
	public Part getCurriculum(){
		
		
		return null;
	}
}
