package ds.start;

import ds.controllers.ControllerGUI;

public class AppDriver {
	private ControllerGUI controllerGUI;
	
	public AppDriver(){
		ControllerGUI = new ControllerGUI(this);
	}

}
