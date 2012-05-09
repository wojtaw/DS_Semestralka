package ds.controllers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import ds.entity.Presentations;
import ds.entity.Speakers;
import ds.gui.MainWindow;
import ds.gui.PresentationDetailsWindow;
import ds.gui.SpeakerDetailsWindow;
import ds.start.AppDriver;
import ds.util.ApplicationOutput;

public class ControllerGUI {
	private AppDriver appDriver;
	private MainWindow mainWindow;
	private PresentationDetailsWindow presentationDetailsWindow;
	private SpeakerDetailsWindow speakerDetailsWindow;
	private List<Presentations> presentationList;
	private List<Speakers> speakersList;
	

	public ControllerGUI(AppDriver appDriver) {
		this.appDriver = appDriver;
	}
	
	
	public void initMainGUI(){
		mainWindow = new MainWindow(this); 
	}
	
	public void initGUIData(List<Presentations> presentationList,List<Speakers> speakersList){
		this.presentationList = presentationList;
		this.speakersList = speakersList;
		mainWindow.initDataComponents();
		refreshPresentationTable();
		refreshSpeakerTable();
	}


	public void showPresentationDetails(int selectedRow) {
		Presentations tmpPresentation = presentationList.get(selectedRow);
		presentationDetailsWindow = new PresentationDetailsWindow(this,tmpPresentation);
	}
	

	public void showSpeakeerDetails(int selectedRow) {
		Speakers tmpSpeaker = speakersList.get(selectedRow);
		speakerDetailsWindow = new SpeakerDetailsWindow(this,tmpSpeaker);
		
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
	
	private void refreshSpeakerTable() {
		//Create and fill data object for presentation table
		Object[][] speakerData = new Object[speakersList.size()][2];
		for (int i = 0; i < speakersList.size(); i++) {
			speakerData[i][0] = speakersList.get(i).getSpeakerName();
			speakerData[i][1] = speakersList.get(i).getSpeakerId();
		}
		mainWindow.redrawSpeakerTable(speakerData);	
	}	


	public void savePresentationDetails(String title, String description, long presentationID) {
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


	public void clickedPresentationDelete() {
		//Check if there is any row selected in table
		if(mainWindow.getSelectedRowPresentation().length <= 0) ApplicationOutput.printLog("YOU DID NOT SELECT ANY PRESENTATION");
		else if(mainWindow.getSelectedRowPresentation().length > 1) ApplicationOutput.printLog("Please select only one presentation to edit");
		else {
			int tmpSelectedRow = mainWindow.getSelectedRowPresentation()[0];
			Presentations tmpPresentation = presentationList.remove(tmpSelectedRow);			
			appDriver.hibernateProxy.deletePresentation(tmpPresentation);	
			refreshPresentationTable();			
		}
	}


	public void clickedPresentationEdit() {
		//Check if there is any row selected in table
		if(mainWindow.getSelectedRowPresentation().length <= 0) ApplicationOutput.printLog("YOU DID NOT SELECT ANY PRESENTATION");
		else if(mainWindow.getSelectedRowPresentation().length > 1) ApplicationOutput.printLog("Please select only one presentation to edit");
		else {
			showPresentationDetails(mainWindow.getSelectedRowPresentation()[0]);
		}
		
	}


	public void clickedPresentationNew() {
		// TODO Auto-generated method stub
		
	}


	public void clickedSpeakerDelete() {

	}


	public void clickedSpeakerEdit() {
		// TODO Auto-generated method stub
		
	}


	public void clickedSpeakerNew() {
		// TODO Auto-generated method stub
		
	}

}
