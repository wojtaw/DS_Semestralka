package ds.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomRenderer extends DefaultTableCellRenderer 
{
	
    private final Color MIN_COLOR = Color.GREEN;
    private final Color MAX_COLOR = Color.RED;
    
    public CustomRenderer()   
    {  
        super();  
    }      
    
    public Component getTableCellRendererComponent(JTable table, Object value,   
            boolean isSelected, boolean hasFocus, int row, int column)   
    {   
        if(Integer.parseInt((String)table.getValueAt(row, 0)) > 5)  
        {  
            setForeground(Color.black);          
            setBackground(Color.red);              
        }      
        else  
        {      
            setBackground(Color.white);      
            setForeground(Color.black);      
        }   
        setText(value !=null ? value.toString() : "");  
        return this;  
    }  
}
