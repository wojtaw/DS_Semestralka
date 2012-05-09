package ds.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomRendererSpeakers extends DefaultTableCellRenderer {
	
    private final Color MIN_COLOR = Color.GREEN;
    private final Color MAX_COLOR = Color.RED;
    private Boolean isClickState = false;
    private ArrayList<Integer> rowsToHighlight = new ArrayList<Integer>();
	private int selectedRow;
         
    
    public CustomRendererSpeakers(ArrayList<Integer> rowsToHighlight) {
        super(); 
        this.rowsToHighlight = rowsToHighlight;
	}

	public CustomRendererSpeakers(boolean b, int selectedRow) {
		isClickState = b;
		this.selectedRow = selectedRow;
		
	}

	public Component getTableCellRendererComponent(JTable table, Object value,   
            boolean isSelected, boolean hasFocus, int row, int column)   
    {   
		if(rowsToHighlight.contains(row)){         
			setBackground(Color.cyan);		
		}else if(selectedRow == row && isClickState){
			setBackground(Color.green);		
		}else{      
			setBackground(Color.white);            
		}   		
        setText(value !=null ? value.toString() : "");  
        return this;  
    }	

}
