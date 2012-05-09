package ds.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ds.controllers.ControllerGUI;
import ds.util.ApplicationOutput;

public class MainWindowListener implements ActionListener, MouseListener, ListSelectionListener{
	private ControllerGUI controllerGUI;
	
	public MainWindowListener(ControllerGUI controllerGUI){
		this.controllerGUI = controllerGUI;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ApplicationOutput.printLog("Clicked button "+e.getSource().toString());
		String comparedString = e.getSource().toString();
		if(comparedString.equals(ButtonsIdentification.PRESENTATION_DELETE.toString()))
			controllerGUI.clickedPresentationDelete();
		else if(comparedString.equals(ButtonsIdentification.PRESENTATION_EDIT.toString()))
			controllerGUI.clickedPresentationEdit();
		else if(comparedString.equals(ButtonsIdentification.PRESENTATION_NEW.toString()))
			controllerGUI.clickedPresentationNew();
		else if(comparedString.equals(ButtonsIdentification.SPEAKER_DELETE.toString()))
			controllerGUI.clickedSpeakerDelete();
		else if(comparedString.equals(ButtonsIdentification.SPEAKER_EDIT.toString()))
			controllerGUI.clickedSpeakerEdit();
		else if(comparedString.equals(ButtonsIdentification.SPEAKER_NEW.toString()))
			controllerGUI.clickedSpeakerNew();
		else {
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}

