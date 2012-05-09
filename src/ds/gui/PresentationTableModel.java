package ds.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PresentationTableModel extends AbstractTableModel{
	
	private List<String> columnNames = new ArrayList();
    private Object[][] data = {{"no data","no data","no data"}};

    public PresentationTableModel(){
        columnNames.add("Presentation title");
        columnNames.add("Description");
        columnNames.add("Pres. ID");
    }	
    public void swapData(Object[][] data){
    	this.data = data;
    }
    
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return data[0].length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return data[row][col];
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }	
	
	
	public String toString(){
		return "PRESENTATIONS";
	}
	
	

}
