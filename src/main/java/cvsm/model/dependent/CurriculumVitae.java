package cvsm.model.dependent;

import javax.enterprise.context.Dependent;
import javax.inject.Named;


public class CurriculumVitae {

	
	private byte [] cvByte;
	private String cvXml;
	
	public byte[] getCvByte() {
		return cvByte;
	}
	public String getCvXml() {
		return cvXml;
	}
	public void setCvByte(byte[] cvByte) {
		this.cvByte = cvByte;
	}
	public void setCvXml(String cvXml) {
		this.cvXml = cvXml;
	}
	
	
}
