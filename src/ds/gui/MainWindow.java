package ds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import ds.controllers.ControllerGUI;
import ds.util.ApplicationOutput;

public class MainWindow extends JFrame{
	private ControllerGUI controllerGUI;
	private BorderLayout windowLayout = new BorderLayout();	
	private MainWindowListener mainWindowListener;
	private DataTableModel myModel;
	
	//Components
	private JPanel presentationPanel = new JPanel();
	private JTable presentationTable;
	private MyButton presentationSave;
	private MyButton presentationEdit;
	private MyButton presentationRemove;
	
	
	public MainWindow(ControllerGUI controllerGUI) {
		this.controllerGUI = controllerGUI;
		initWindow();
		initComponents();
		initListeners();
	}

	private void initListeners() {
		mainWindowListener = new MainWindowListener(controllerGUI);
		
	}

	private void initWindow() {
		this.setTitle("Presentation - speakers editor");
		this.setSize(900, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(windowLayout);		
	}
	
	
	private void initComponents() {
		presentationSave - new MyButton(text, "PRESENTATION_SAVE");
		this.add(presentationPanel,BorderLayout.CENTER);
		presentationPanel
	}
	
	public boolean initDataComponents(){
		//Init presentation table
		presentationTable = new JTable();
		myModel = new DataTableModel();
		presentationTable.setModel(myModel);
		
		presentationTable.getSelectionModel().addListSelectionListener(mainWindowListener);
		presentationTable.getColumnModel().getSelectionModel().addListSelectionListener(mainWindowListener);
		presentationTable.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		         if (e.getClickCount() == 2){
					JTable target = (JTable)e.getSource();
					controllerGUI.showPresentationDetails(target.getSelectedRow());
		         }
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
		ApplicationOutput.printLog("Swaping data");
		myModel.swapData(data);
		
		presentationTable.setModel(myModel);
		presentationTable.updateUI();
		validateGUI();
	}
	
	public void validateGUI(){
		this.validate();
	}

	
	

}
