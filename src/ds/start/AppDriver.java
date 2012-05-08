package ds.start;

import ds.controllers.ControllerGUI;
import ds.util.HibernateProxy;

public class AppDriver {
	private ControllerGUI controllerGUI;
	private HibernateProxy hibernateProxy;

	
	public AppDriver(){
		hibernateProxy = new HibernateProxy();
		controllerGUI = new ControllerGUI(this);
		controllerGUI.initMainGUI(hibernateProxy.retrievePresentationData());
	}

}
