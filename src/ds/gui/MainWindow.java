package ds.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import ds.controllers.ControllerGUI;

public class MainWindow extends JFrame{
	private ControllerGUI controllerGUI;
	private BorderLayout windowLayout = new BorderLayout();	
	private String[] presentationComlumnNames = {"Presentation name",
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
		this.add(presentationPanel,BorderLayout.CENTER);
	}
	
	public boolean initDataComponents(Object[][] presentationTableData){
		if(presentationTableData == null) return false;
		//Init presentation table
		presentationTable = new JTable(presentationTableData,presentationComlumnNames);
		presentationTable.setPreferredScrollableViewportSize(new Dimension(300, 450));
		
		//Add all components
		presentationPanel.add(presentationTable);
		validateGUI();
		return true;
	}
	
	public void redrawPresentationTable(Object[][] data){
		presentationTable = new JTable(data, presentationComlumnNames);
		presentationTable.updateUI();
		validateGUI();
	}
	
	public void validateGUI(){
		this.validate();
	}

	
	

}
