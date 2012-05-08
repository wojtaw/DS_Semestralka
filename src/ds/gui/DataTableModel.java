package ds.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel{
	
	private List<String> columnNames = new ArrayList();
    private List<List> data = new ArrayList();

    public DataTableModel(){
        columnNames.add("Presentation title");
        columnNames.add("Description");
    }	
    
    public void addRow(List rowData)
    {
        data.add(rowData);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }   
    
    public void addData(Object[][] tableData)
    {
    	for (int i = 0; i < tableData.length; i++) {
			data.addRow(Arrays.asList(tableData[i][0].toString(),tableData[i][1].toString()));
		}
        data.add(rowData);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }     

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public boolean isCellEditable(int row, int column) {
       //all cells false
       return false;
    }	
	
	

}
