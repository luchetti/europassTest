package cvsm.business.producers;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import cvsm.business.interfaces.interceptors.TraceLog;
 
public class LogFactory {

	@Produces 
	public Logger produceLog(InjectionPoint p){
		return Logger.getLogger(p.getMember().getDeclaringClass().getName());
	}
}
