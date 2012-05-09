package ds.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomRenderer extends DefaultTableCellRenderer 
{
	
    private final Color MIN_COLOR = Color.GREEN;
    private final Color MAX_COLOR = Color.RED;
    private ArrayList<Integer> rowsToHighlight;
         
    
    public CustomRenderer(ArrayList<Integer> rowsToHighlight) {
        super(); 
        this.rowsToHighlight = rowsToHighlight;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,   
            boolean isSelected, boolean hasFocus, int row, int column)   
    {   
		
		/*
		for (int i = 0; i < rowsToHighlight.length; i++) {
			if(row == rowsToHighlight[i]){ 
				System.out.println("FOUND: "+rowsToHighlight[i]);
				setForeground(Color.black);          
				setBackground(Color.red);              
			}else{      
				setBackground(Color.white);      
				setForeground(Color.black);      
			}   
		}
		*/
		
		if(row == 3){ 
			setForeground(Color.black);          
			setBackground(Color.red);
		} else if(row == 4){ 
			setForeground(Color.black);          
			setBackground(Color.red);			
		}else{      
			setBackground(Color.white);      
			setForeground(Color.black);      
		}   		
        setText(value !=null ? value.toString() : "");  
        return this;  
    }  
}
