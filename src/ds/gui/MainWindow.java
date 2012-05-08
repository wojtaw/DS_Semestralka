package ds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import ds.controllers.ControllerGUI;

public class MainWindow extends JFrame{
	private ControllerGUI controllerGUI;
	private BorderLayout windowLayout = new BorderLayout();	
	private TableListener tableListeners;
	
	//Components
	private JPanel presentationPanel = new JPanel();
	private JTable presentationTable;
	
	
	public MainWindow(ControllerGUI controllerGUI) {
		this.controllerGUI = controllerGUI;
		initWindow();
		initComponents();
		initListeners();
	}

	private void initListeners() {
		tableListeners = new TableListener(controllerGUI);
		
	}

	private void initWindow() {
		this.setTitle("Presentation - speakers editor");
		this.setSize(900, 700);
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
		presentationTable = new JTable();
		DataTableModel myModel = new DataTableModel();
		myModel.swapData(presentationTableData);
		
		presentationTable.setModel(myModel);
		
		presentationTable.getSelectionModel().addListSelectionListener(tableListeners);
		presentationTable.getColumnModel().getSelectionModel().addListSelectionListener(tableListeners);
		presentationTable.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		         if (e.getClickCount() == 2){
		            System.out.println(" double click" );
		            }
		         JTable target = (JTable)e.getSource();
		         controllerGUI.showPresentationDetails(target.getSelectedRow());
		         }
		        } );
		JTableHeader presentationHeader = presentationTable.getTableHeader();
		presentationHeader.setBackground(Color.yellow);		
		presentationTable.setPreferredScrollableViewportSize(new Dimension(300, 450));
		presentationTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane presentationScroll = new JScrollPane(presentationTable);
		presentationPanel.add(presentationScroll);
		
		//Add all components
		//presentationPanel.add(presentationTable);
		validateGUI();
		return true;
	}
	
	public void redrawPresentationTable(Object[][] data){
		validateGUI();
	}
	
	public void validateGUI(){
		this.validate();
	}

	
	

}
