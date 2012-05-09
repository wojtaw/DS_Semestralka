package ds.start;

import ds.controllers.ControllerGUI;
import ds.util.HibernateProxy;

public class AppDriver {
	public ControllerGUI controllerGUI;
	public HibernateProxy hibernateProxy;

	
	public AppDriver(){
		controllerGUI = new ControllerGUI(this);
		controllerGUI.initMainGUI();
		hibernateProxy = new HibernateProxy();
		controllerGUI.initGUIData(hibernateProxy.retrievePresentationData());
	}
	
	

}
