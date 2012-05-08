package ds.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import ds.controllers.ControllerGUI;

public class MainWindow extends JFrame{
	private ControllerGUI controllerGUI;
	private BorderLayout windowLayout = new BorderLayout();	
	private String[] columnNames = {"Presentation name",
    "Presentation description"};
	
	//Components
	private JPanel presentationPanel = new JPanel();
	private JTable presentationTable;
	
	
	public MainWindow(ControllerGUI controllerGUI) {
		this.controllerGUI = controllerGUI;
		initWindow();
		initComponents();
	}

	private void initWindow() {
		this.setTitle("Presentation - speakers editor");
		this.setSize(900, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(windowLayout);		
	}
	
	
	private void initComponents() {
		//Init presentation table
		Object[][] initData = {{"test","test"},{"test2","test2"}};
		presentationTable = new JTable(initData,columnNames);
		//presentationTable.setPreffe
		
		//Add all components
		presentationPanel.add(presentationTable);
		this.add(presentationPanel,BorderLayout.CENTER);
	}
	
	public void redrawPresentationTable(Object[][] data){
		presentationTable = new JTable(data, columnNames);
		presentationTable.updateUI();
		validateUI();
	}
	
	public void validateUI(){
		this.validate();
		this.validateUI();
	}

	
	

}
