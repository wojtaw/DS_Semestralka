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
		this.controllerGUI = controllerGUI;
		this.selectedSpeaker = selectedSpeaker;		
		initWindow();
		initComponents();
	}
	

	public SpeakerDetailsWindow(ControllerGUI controllerGUI) {
		this.controllerGUI = controllerGUI;
		initWindow();
		initInsertionComponents();
	}	
	
	private void initInsertionComponents() {
		titleEdit = new JTextField();
		titleEdit.setEditable(true);
		presentationList = new JLabel("CAN BE EDITED IN MAIN WINDOW");
		
		saveDetails = new JButton("Add speaker");
		saveDetails.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
					controllerGUI.addSpeaker(titleEdit.getText());
					setVisible(false);
		         }
		     } );
		addComponents();		
	}
	
	private void initComponents() {
		titleEdit = new JTextField(selectedSpeaker.getSpeakerName());
		titleEdit.setEditable(true);
		presentationList = new JLabel(getPresentationsList());
		
		saveDetails = new JButton("Save details");
		saveDetails.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
					controllerGUI.saveSpeakerDetails(titleEdit.getText(),selectedSpeaker.getSpeakerId());
					setVisible(false);
		         }
		     } );
		addComponents();		
	}
	
	private void addComponents(){
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
		
		for (Iterator iterator = selectedSpeaker.getPresentations().iterator(); iterator.hasNext();) {
			Presentations tmpPresentation = (Presentations) iterator.next();
			returnedString.append(tmpPresentation.getPresentationTitle()+" | ");
		}
		
		if(returnedString.length() < 1) returnedString.append("No presentations yet");
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
