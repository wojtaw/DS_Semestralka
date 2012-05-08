package ds.gui;

import javax.swing.JFrame;
import javax.swing.JTextField;

import ds.controllers.ControllerGUI;

public class PresentationDetailsWindow extends JFrame{
	private ControllerGUI controllerGUI;
	private JTextField title;
	private JTextField description;

	public PresentationDetailsWindow(ControllerGUI controllerGUI) {
		this.controllerGUI = controllerGUI;
		initWindow();
		initComponents();
	}
	
	private void initComponents() {
		// TODO Auto-generated method stub
		
	}

	private void initWindow() {
		this.setTitle("Presentation - speakers editor");
		this.setSize(400, 200);
		this.setVisible(true);	
	}	

}
