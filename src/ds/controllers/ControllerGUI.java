package ds.controllers;

import java.util.Iterator;
import java.util.List;

import ds.entity.Presentations;
import ds.entity.Speakers;
import ds.gui.MainWindow;
import ds.gui.PresentationDetailsWindow;
import ds.start.AppDriver;
import ds.util.ApplicationOutput;

public class ControllerGUI {
	private AppDriver appDriver;
	private MainWindow mainWindow;
	private PresentationDetailsWindow presentationDetailsWindow;
	private List<Presentations> presentationList;
	

	public ControllerGUI(AppDriver appDriver) {
		this.appDriver = appDriver;
	}
	
	
	public void initMainGUI(){
		mainWindow = new MainWindow(this); 
	}
	
	public void initGUIData(List<Presentations> presentationList){
		this.presentationList = presentationList;
		mainWindow.initDataComponents();
		refreshPresentationTable();
	}


	public void showPresentationDetails(int selectedRow) {
		System.out.println(presentationList.get(selectedRow).getPresentationTitle());
		Presentations tmpPresentation = presentationList.get(selectedRow);
		presentationDetailsWindow = new PresentationDetailsWindow(this,tmpPresentation);
		
	}
	
	private void refreshPresentationTable() {
		//Create and fill data object for presentation table
		Object[][] presentationData = new Object[presentationList.size()][3];
		for (int i = 0; i < presentationList.size(); i++) {
			presentationData[i][0] = presentationList.get(i).getPresentationTitle();
			presentationData[i][1] = presentationList.get(i).getPresentationDescription();
			presentationData[i][2] = presentationList.get(i).getPresentationId();
		}
		mainWindow.redrawPresentationTable(presentationData);
		
	}	


	public void savePresentationDetails(String title, String description, long presentationID) {
		//appDriver.hibernateProxy.updatePresentation(updatedPresentation, id);
		for (Presentations presentation : presentationList){
			//If found with proper id, update fields and save it
			if(presentation.getPresentationId() == presentationID){
				ApplicationOutput.printLog("Now attempt to update presentation,after it was found in list");
				presentation.setPresentationTitle(title);
				presentation.setPresentationDescription(description);
				appDriver.hibernateProxy.updatePresentation(presentation);
				refreshPresentationTable();
			}		
		}
		
	}

}
