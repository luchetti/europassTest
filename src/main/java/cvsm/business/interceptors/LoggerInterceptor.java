package cvsm.business.interceptors;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import cvsm.business.interfaces.interceptors.TraceLog;


@TraceLog @Interceptor
public class LoggerInterceptor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject 
	Logger log;
	
	String method;
	String clazz;

	@AroundInvoke
	public Object logAroundInvoke(InvocationContext ctx) throws Exception{
	
		clazz=ctx.getMethod().getDeclaringClass().getName();
		method=ctx.getMethod().getName();
		
		log.entering(clazz, method);
		log.info(clazz+"."+method+" invoked");
		for(Object obj: ctx.getParameters()){
			log.info(clazz+"."+method+" with param: "+obj);
		}
		try{
			Object result=ctx.proceed();
			log.exiting(clazz, method, result);
			log.info(clazz+"."+method+" exit");
			return result;
		}
		catch(Exception ex){
			log.info(clazz+"."+method+" exception");
			log.throwing(clazz, method, ex);
			throw ex;
		}
	}
}
