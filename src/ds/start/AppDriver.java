package ds.start;

import ds.controllers.ControllerGUI;
import ds.util.HibernateProxy;

public class AppDriver {
	private ControllerGUI controllerGUI;
	private HibernateProxy hibernateProxy;

	
	public AppDriver(){
		controllerGUI = new ControllerGUI(this);
		controllerGUI.initMainGUI();
		hibernateProxy = new HibernateProxy();
		controllerGUI.initGUIData(hibernateProxy.retrievePresentationData());
	}

}
