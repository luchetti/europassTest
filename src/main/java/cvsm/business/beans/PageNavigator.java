package cvsm.business.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class PageNavigator implements Serializable{

	private static final long serialVersionUID = 1L;

	private String page="home.xhtml";
	
	public void setPage(String page){
		this.page=page;
	}
	public String getPage(){
		return this.page;
	}
}
