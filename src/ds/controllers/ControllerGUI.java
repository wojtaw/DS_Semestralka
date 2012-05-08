package ds.controllers;

import java.util.Iterator;
import java.util.List;

import ds.entity.Presentations;
import ds.entity.Speakers;
import ds.gui.MainWindow;
import ds.start.AppDriver;

public class ControllerGUI {
	private AppDriver appDriver;
	private MainWindow mainWindow;

	public ControllerGUI(AppDriver appDriver) {
		this.appDriver = appDriver;
	}
	
	
	public void initMainGUI(){
		mainWindow = new MainWindow(this); 
	}
	
	public void initGUIData(List<Presentations> presentationList){
		//Create and fill data object for presentation table
		Object[][] presentationData = new Object[presentationList.size()][2];
		for (int i = 0; i < presentationList.size(); i++) {
			presentationData[i][0] = presentationList.get(i).getPresentationTitle();
			presentationData[i][1] = presentationList.get(i).getPresentationTitle();			
		}		
		mainWindow.initDataComponents(presentationData);
	}


	public void showPresentationDetails(int selectedRow) {
		System.out.println();
		
	}

}
