package ds.gui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ds.controllers.ControllerGUI;
import ds.entity.Presentations;
import ds.entity.Speakers;

public class SpeakerDetailsWindow extends JFrame{
	private ControllerGUI controllerGUI;
	private JLabel title = new JLabel("Speaker name: ");
	private JTextField titleEdit;
	private JLabel presentations = new JLabel("Presented on: ");
	private JLabel presentationList;
	private Speakers selectedSpeaker;
	private JButton saveDetails;
	private GridLayout myGrid = new GridLayout(0,2);

	public SpeakerDetailsWindow(ControllerGUI controllerGUI, Speakers selectedSpeaker) {
		this.selectedSpeaker = selectedSpeaker;
		this.controllerGUI = controllerGUI;
		initWindow();
		initComponents();
	}
	
	private void initComponents() {
		titleEdit = new JTextField(selectedSpeaker.getSpeakerName());
		titleEdit.setEditable(true);
		presentationList = new JLabel(getPresentationsList());
		
		saveDetails = new JButton("Save details");
		saveDetails.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
					//controllerGUI.savePresentationDetails(titleEdit.getText(),descriptionEdit.getText(),selectedPresentationation.getPresentationId());
					setVisible(false);
		         }
		     } );
	
		
		this.add(title);
		this.add(titleEdit);
		this.add(presentations);
		this.add(presentationList);
		this.add(saveDetails);
		this.pack();
		this.validate();
	}

	private String getPresentationsList() {
		StringBuilder returnedString = new StringBuilder();
		/*
		for (Iterator iterator = selectedSpeaker.getSpeakers().iterator(); iterator.hasNext();) {
			Speakers tmpSpeaker = (Speakers) iterator.next();
			returnedString.append(tmpSpeaker.getSpeakerName()+" | ");
		}
		*/	
		if(returnedString.length() < 1) returnedString.append("No speakers added");
		return returnedString.toString();
	}

	private void initWindow() {
		this.setTitle("Presentation details");
		this.setSize(400, 200);
		this.setVisible(true);	
		this.setLayout(myGrid);
		this.setAlwaysOnTop(true);	
		this.setLocationRelativeTo(controllerGUI.getSpeakerPanel());
	}		

}
