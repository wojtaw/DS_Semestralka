package ds.gui;

import javax.swing.JFrame;

import ds.controllers.ControllerGUI;

public class MainWindow extends JFrame{
	private ControllerGUI controllerGUI;

	public MainWindow(ControllerGUI controllerGUI) {
		this.controllerGUI = controllerGUI;
		initWindow();
		initComponents();
	}

	private void initComponents() {
		this.setTitle("Petr Lodì");
        this.setSize(900, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	private void initWindow() {
		
	}
	
	

}
