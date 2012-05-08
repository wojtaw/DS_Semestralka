package ds.start;

import ds.controllers.ControllerGUI;

public class AppDriver {
	private ControllerGUI controllerGUI;

	
	public AppDriver(){
		controllerGUI = new ControllerGUI(this);
		controllerGUI.initMainGUI();
	}

}
