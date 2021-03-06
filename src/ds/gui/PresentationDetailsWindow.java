package ds.gui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import ds.controllers.ControllerGUI;
import ds.entity.Presentations;
import ds.entity.Speakers;

public class PresentationDetailsWindow extends JFrame{
	private ControllerGUI controllerGUI;
	private JLabel title = new JLabel("Title: ");
	private JTextField titleEdit;
	private JLabel description = new JLabel("Description: ");
	private JTextField descriptionEdit;
	private JLabel speakers = new JLabel("Speakers: ");
	private JLabel speakersList;
	private Presentations selectedPresentation;
	private JButton saveDetails;
	private GridLayout myGrid = new GridLayout(0,2);

	public PresentationDetailsWindow(ControllerGUI controllerGUI, Presentations selectedPresentation) {
		this.selectedPresentation = selectedPresentation;
		this.controllerGUI = controllerGUI;
		initWindow();
		initComponents();
	}
	
	private void initComponents() {
		titleEdit = new JTextField(selectedPresentation.getPresentationTitle());
		titleEdit.setEditable(true);
		descriptionEdit = new JTextField(""+selectedPresentation.getPresentationDescription());
		descriptionEdit.setEditable(true);
		speakersList = new JLabel(getSpeakersList());
		
		saveDetails = new JButton("Save details");
		saveDetails.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
					controllerGUI.savePresentationDetails(titleEdit.getText(),descriptionEdit.getText(),selectedPresentation.getPresentationId());
					setVisible(false);
		         }
		     } );
	
		
		this.add(title);
		this.add(titleEdit);
		this.add(description);
		this.add(descriptionEdit);
		this.add(speakers);
		this.add(speakersList);
		this.add(saveDetails);
		this.pack();
		this.validate();
	}

	private String getSpeakersList() {
		StringBuilder returnedString = new StringBuilder();
		for (Iterator iterator = selectedPresentation.getSpeakers().iterator(); iterator.hasNext();) {
			Speakers tmpSpeaker = (Speakers) iterator.next();
			returnedString.append(tmpSpeaker.getSpeakerName()+" | ");
		}	
		if(returnedString.length() < 1) returnedString.append("No speakers added");
		return returnedString.toString();
	}

	private void initWindow() {
		this.setTitle("Presentation details");
		this.setSize(400, 200);
		this.setVisible(true);	
		this.setLayout(myGrid);
		this.setAlwaysOnTop(true);		
		this.setLocationRelativeTo(controllerGUI.getPresentationPanel());		
	}	

}
