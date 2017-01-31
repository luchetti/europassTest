package cvsm.business.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import cvsm.business.interfaces.interceptors.TraceLog;
import cvsm.business.interfaces.qualifiers.LoggedIn;
import cvsm.model.entities.UserEntity;

@Named
@SessionScoped
@TraceLog
public class Home implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject @LoggedIn private transient UserEntity currentUser;
	
	public UserEntity getCurrentUser(){
		return this.currentUser;
	}
}
