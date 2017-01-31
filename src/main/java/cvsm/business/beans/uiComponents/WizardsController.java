package cvsm.business.beans.uiComponents;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import cvsm.business.interfaces.interceptors.TraceLog;

@Named
@ViewScoped
@TraceLog
public class WizardsController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Logger log;
	private int step;
	
	
	
	public String flow (FlowEvent event){
		log.info("WizardsController - step \""+event.getNewStep()+"\"");
		return event.getNewStep();
	}
	
	public void setStep(int step){	this.step=step;	}
	public int getStep(){	return this.step;	}
}
