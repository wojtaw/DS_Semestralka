package ds.controllers;

import ds.gui.MainWindow;
import ds.start.AppDriver;

public class ControllerGUI {
	private AppDriver appDriver;

	public ControllerGUI(AppDriver appDriver) {
		this.appDriver = appDriver;
	}
	
	public void initMainGUI(){
		MainWindow mainWindow = new MainWindow(this); 
	}

}
