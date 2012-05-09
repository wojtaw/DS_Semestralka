package ds.gui;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ds.controllers.ControllerGUI;
import ds.util.ApplicationOutput;

public class PresentationTableListener implements ListSelectionListener{

	private ControllerGUI controllerGUI;
	private JTable table;
	
	public PresentationTableListener(ControllerGUI controllerGUI, JTable table) {
		this.controllerGUI = controllerGUI;
		this.table = table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// If cell selection is enabled, both row and column change events are fired

		/*
        if (e.getSource() == table.getSelectionModel() && table.getRowSelectionAllowed()) {
            // Column selection changed
            int first = e.getFirstIndex();
            int last = e.getLastIndex();
    		ApplicationOutput.printLog("Clicked into table "+first+" - "+last);
        } else if (e.getSource() == table.getColumnModel().getSelectionModel()
               && table.getColumnSelectionAllowed() ){
            // Row selection changed
            int first = e.getFirstIndex();
            int last = e.getLastIndex();
        }	
        */	
		
		
	}

}
