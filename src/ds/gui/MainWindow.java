package ds.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;

import javax.swing.BoxLayout;
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
	private PresentationTableModel presentationModel;
	private SpeakerTableModel speakerModel;
	
	//Components
	public JPanel presentationPanel = new JPanel();
	private BoxLayout presentationLayout = new BoxLayout(presentationPanel, BoxLayout.PAGE_AXIS);
	private JTable presentationTable;
	private MyButton presentationNew;
	private MyButton presentationEdit;
	private MyButton presentationRemove;
	
	public JPanel speakerPanel = new JPanel();
	private BoxLayout speakerLayout = new BoxLayout(speakerPanel, BoxLayout.PAGE_AXIS);
	private JTable speakerTable;
	private MyButton speakerNew;
	private MyButton speakerEdit;
	private MyButton speakerRemove;	
	
	
	public MainWindow(ControllerGUI controllerGUI) {
		this.controllerGUI = controllerGUI;
		initWindow();
		initListeners();
		initComponents();
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
		presentationNew = new MyButton("New presentation", ButtonsIdentification.PRESENTATION_NEW);
		presentationEdit = new MyButton("Edit selected presentation", ButtonsIdentification.PRESENTATION_EDIT);
		presentationRemove = new MyButton("Remove selected presentation", ButtonsIdentification.PRESENTATION_DELETE);
		
		presentationNew.addMouseListener(mainWindowListener);
		presentationEdit.addMouseListener(mainWindowListener);
		presentationRemove.addMouseListener(mainWindowListener);
		
		presentationPanel.setLayout(presentationLayout);
		
		presentationPanel.add(presentationNew);
		presentationPanel.add(presentationEdit);
		presentationPanel.add(presentationRemove);
		
		speakerNew = new MyButton("Add speaker", ButtonsIdentification.SPEAKER_NEW);
		speakerEdit = new MyButton("Edit selected speaker", ButtonsIdentification.SPEAKER_EDIT);
		speakerRemove = new MyButton("Remove selected speaker", ButtonsIdentification.SPEAKER_DELETE);
		
		speakerNew.addMouseListener(mainWindowListener);
		speakerEdit.addMouseListener(mainWindowListener);
		speakerRemove.addMouseListener(mainWindowListener);
		
		speakerPanel.setLayout(speakerLayout);
		
		speakerPanel.add(speakerNew);
		speakerPanel.add(speakerEdit);
		speakerPanel.add(speakerRemove);		
		
		
		this.add(presentationPanel,BorderLayout.LINE_END);
		this.add(speakerPanel,BorderLayout.LINE_START);
		
	}
	
	public boolean initDataComponents(){
		//Init presentation table
		presentationTable = new JTable();
		presentationModel = new PresentationTableModel();
		presentationTable.setModel(presentationModel);
		
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
		

		speakerTable = new JTable();
		speakerModel = new SpeakerTableModel();
		speakerTable.setModel(speakerModel);
		
		speakerTable.getSelectionModel().addListSelectionListener(mainWindowListener);
		speakerTable.getColumnModel().getSelectionModel().addListSelectionListener(mainWindowListener);
		speakerTable.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		         if (e.getClickCount() == 2){
					JTable target = (JTable)e.getSource();
					controllerGUI.showSpeakeerDetails(target.getSelectedRow());
		         }
		         }
		        } );
		JTableHeader speakerHeader = speakerTable.getTableHeader();
		speakerHeader.setBackground(Color.yellow);		
		speakerTable.setPreferredScrollableViewportSize(new Dimension(300, 450));
		speakerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane speakerScroll = new JScrollPane(speakerTable);
		speakerPanel.add(speakerScroll);

		validateGUI();
		return true;
	}
	
	public void redrawPresentationTable(Object[][] data){
		ApplicationOutput.printLog("Swaping data in presentations");
		presentationModel.swapData(data);
		presentationTable.setModel(presentationModel);
		presentationTable.updateUI();
		validateGUI();
	}
	
	public void redrawSpeakerTable(Object[][] data){
		ApplicationOutput.printLog("Swaping data in speakers");
		speakerModel.swapData(data);
		speakerTable.setModel(speakerModel);
		speakerTable.updateUI();
		validateGUI();
	}	
	
	public void validateGUI(){
		this.validate();
	}
	
	public int[] getSelectedRowPresentation(){
		return presentationTable.getSelectedRows();
	}
	
	public int[] getSelectedRowSpeaker(){
		return speakerTable.getSelectedRows();
	}	

	
	

}
